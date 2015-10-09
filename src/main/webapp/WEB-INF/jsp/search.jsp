<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/3/31
  Time: 下午12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN"  ng-app="myApp">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>查询结果</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <!--AngularJS-->
  <script src="<%=basePath%>js/angular.js"></script>
  <!-- hotCityHotelController.js -->
  <script src="<%=basePath%>controllerjs/hotCityHotelController.js?randomId=<%=Math.random()%>"></script>


  <style type="text/css">

    .search_hotel_item {
      padding-bottom: 10px;
      border-bottom: solid 0.5px;
    }

    .item_info p a {
      font-size: 20px;
      text-decoration: none;
    }

    .search_input_row {
      padding: 10px;
      margin-bottom: 10px;
      background: #A6FFFF;
    }

    .search_condition_row {
      margin-bottom: 30px;
    }

    .search_condition_row div{
      border-style: solid;
      border-width: 1px;
        /*background: #A6FFFF;*/
    }

    .search_condition_row_table {
      width: 100%;
    }

    .search_condition_row_tr {
      border-bottom: solid;
      border-width: 1px;
      height: 30px;
    }

    .show_pic {
      max-width: 240px;
      max-height: 200px;
    }

    .search_hotel_item {
      margin-top: 10px;
    }

    .item_price p a {
      display: block;
      padding-top: 10px;
      padding-bottom: 10px;
      font-size: 20px;
    }
  </style>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body  ng-controller="searchPageController" ng-init="conditionData.cityId=${city};conditionData.comeDate='${comeDate}';conditionData.leaveDate='${leaveDate}'">


<!--顶部菜单-->
<nav class="navbar navbar-default " ng-controller="loginController" ng-init="member.name='${sessionScope.member.name}';login.status=${sessionScope.member!=null};">

  <!--Logo-->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">王符金酒店</a>
  </div>



  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

    <!--第一段菜单-->
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">主页 <span class="sr-only">(current)</span></a></li>
      <li><a href="#">酒店</a></li>
    </ul>


    <!-- 右侧菜单-->
    <ul class="nav navbar-nav navbar-right">
      <li ng-show="!login.status"><a href="javascript:;"  data-toggle="modal" data-target=".myModal">登录</a></li>
      <li ng-show="login.status"><a href="<%=basePath%>person/personCenter.html">{{member.name}}</a></li>
      <li><a href="javascript:;" ng-click="logout()">退出</a></li>
    </ul>

  </div>


  <!--登录框-->

  <div class="modal fade myModal"  data-toggle="modal">
    <div class="modal-dialog">
      <div class="modal-content">
        <!--登录框 头部-->
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">用户登录</h4>
        </div>
        <!--登录框 主体-->
        <form role="form" name="loginForm" ng-submit="loginSubmit()">
          <div class="modal-body">
            <div class="form-group" ng-class="{'has-error':loginForm.account.$invalid}">
              <label for="account" class="control-label">账号：</label>
              <input type="text" class="form-control" id="account" name="account" ng-model="login.account" placeholder="请输入账号" required />
            </div>
            <div class="form-group" ng-class="{'has-error':loginForm.pwd.$invalid}">
              <label for="pwd" class="control-label">密码：</label>
              <input type="password" class="form-control" id="pwd" name="pwd" ng-model="login.pwd" placeholder="请输入密码" required />
            </div>
          </div>
          <!--登录框 脚部-->
          <div class="modal-footer">
            <span style="color: red" ng-show="login.error">账号或密码错误！</span>
            <button type="submit" class="btn btn-primary" ng-disabled="loginForm.username.$invalid||loginForm.pwd.$invalid">登录</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

</nav>






<input type="hidden" ng-init="basePath='<%=basePath%>';conditionData.cityId='${cityId}';conditionData.comeDate='${comeDate}';conditionData.leaveDate='${leaveDate}';searchFormSubmit()"/>
{{conditionData}}
<!--搜索表单-->
<div class="row search_input_row">
  <div class="col-lg-8 col-lg-offset-2" style="border-width: 10px;">

    <form name="searchForm" class="form-inline" ng-submit="searchFormSubmit()">
      <div class="form-group">
        <label class="sr-only" for="city">城市</label>
        <input type="text" class="form-control" id="city" placeholder="城市" ng-model="conditionData.cityId"/>
      </div>
      <div class="form-group" ng-class="{'has-error':searchForm.comeDate.$invalid}">
        <label class="sr-only" for="comeDate">入住日期</label>
        <input type="text" class="form-control" id="comeDate" name="comeDate" placeholder="入住日期" ng-model="conditionData.comeDate" ng-pattern="/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/"/>
      </div>
      <div class="form-group" ng-class="{'has-error':searchForm.leaveDate.$invalid}">
        <label class="sr-only" for="leaveDate">离店日期</label>
        <input type="text" class="form-control" id="leaveDate" name="leaveDate" placeholder="离店日期" ng-model="conditionData.leaveDate" ng-pattern="/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/"/>
      </div>
      <button type="submit" class="btn btn-default" ng-disabled="!((conditionData.comeDate=='' && conditionData.leaveDate=='') || (conditionData.comeDate!='' && conditionData.leaveDate!='' && searchForm.comeDate.$valid && searchForm.leaveDate.$valid))">搜索</button>
    </form>
  </div>
</div>




<!--条件表单-->
<div class="row search_condition_row">
  <div class="col-lg-10 col-lg-offset-1 ">
    <table class="search_condition_row_table">
      <tr class="search_condition_row_tr">
        <td>
          <span>价格范围</span>
        </td>
        <td>
          <input type="radio" name="priceCheckBox" ng-click="setPrice(0,200)"/>&nbsp;200以下&nbsp;&nbsp;&nbsp;
          <input type="radio" name="priceCheckBox" ng-click="setPrice(200,300)"/>&nbsp;200-300&nbsp;&nbsp;&nbsp;
          <input type="radio" name="priceCheckBox" ng-click="setPrice(300,1000)"/>&nbsp;300-1000&nbsp;&nbsp;&nbsp;
          <input type="radio" name="priceCheckBox" ng-click="setPrice(1000,99999)"/>&nbsp;1000以上&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
      <tr class="search_condition_row_tr">
        <td>
          <span>房间类别</span>
        </td>
        <td>
          <span ng-repeat="type in roomTypeList"><input type="radio" ng-click="setType($index)"/>&nbsp;{{type.typeName}}&nbsp;&nbsp;&nbsp;</span>
        </td>
      </tr>
    </table>
  </div>
</div>




<!--主体-->
<div class="row search_main_body">

  <!--左侧  搜索结果部分-->
  <div class="col-lg-7 col-lg-offset-1 panel panel-info">
    <!--搜索结果头部-->
    <div class="panel-heading" class="col-lg-12">
      <span style="color:red;">{{count}}</span><span>家酒店满足条件</span>
    </div>

    <!--搜索结果主体-->
    <div class="panel-body">


      <!--搜索结果 条目-->
      <div class="row search_hotel_item" ng-repeat="hotel in hotelList">
        <!--左侧图片-->
        <div class="col-lg-4 item_pic">
          <a href="#" class="thumbnail">
            <img src="<%=basePath%>img/2.jpg" alt="...">
          </a>
        </div>
        <!--中间信息-->
        <div class="col-lg-6 item_info">
          <p><a href="javascript:;" ng-click="goDetailPage(hotel.branchInfo.branchId)">{{hotel.branchInfo.branchName}}</a></p>
          <p>{{hotel.roomType.typeName}}</p>
          <p>{{hotel.branchInfo.address}}</p>
        </div>
        <!--右侧价格-->
        <div class="col-lg-2 item_price">
          <p><a href="javascript:;" ng-click="goDetailPage(hotel.branchInfo.branchId)">¥{{hotel.roomPrice}} 起></a></p>
        </div>
      </div>
      <!--./ 搜索结果 条目 -->

    </div>
  </div>

  <!--右侧-->
  <div class="col-lg-2 col-lg-offset-1">

    <!--推荐条目-->
    <div class="row">
      <div class="col-lg-12">
        <div class="thumbnail">
          <img src="<%=basePath%>img/2.jpg" alt="...">
          <div class="caption">
            <h3><a href="">火车站店</a></h3>
            <p>位于大兴区，在黄村火车站地铁站C口附近</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>js/jquery-1.11.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
</body>
</html>
