<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/3/31
  Time: 下午3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="myApp">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>分店信息</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <!--AngularJS-->
  <script src="<%=basePath%>js/angular.js"></script>
  <!-- hotCityHotelController.js -->
  <script src="<%=basePath%>controllerjs/hotCityHotelController.js?randomId=<%=Math.random()%>"></script>


  <style type="text/css">
    .search_input_row {
      padding: 10px;
      margin-bottom: 10px;
      background: #A6FFFF;
    }
    .main_body {
      margin: 0 200px;
    }

    .pic_show {
      margin: 20px 0px;
    }

    .home_info_item {
      padding: 20px 0px;
      border-bottom: solid;
      border-width: 1px;
    }

    .home_info_item:hover {
      background: #ECFFFF;
    }

    .home_info_item span {
      padding: 5px 0px;
      font-size: 20px;
    }


  </style>




</head>
<body ng-controller="hotelDetailController" ng-init="basePath='<%=basePath%>';condition.comeDate='${comeDate}';condition.leaveDate='${leaveDate}';branchId='${branchInfo.branchId}';showRoomTypeList();checkReady();">


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

  <div class="modal fade myModal"  data-toggle="modal" >
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













<div class="main_body">

  <input type="hidden" >
  <!--路径导航-->
  <div class="row">
    <ol class="breadcrumb">
      <li><a href="#">酒店主页</a></li>
      <li><a href="#">${branchInfo.city.cityName}酒店</a></li>
      <li class="active">${branchInfo.branchName}</li>
    </ol>
  </div>
  <!--头部-->
  <div class="row main_head">
    <h1>${branchInfo.branchName}</h1>
    <span>${branchInfo.address}</span>
  </div>


  <!--图片展示-->
  <div class="row pic_show">
    <!--图片-->
    <div class="col-lg-6">
      <a href="#" class="thumbnail">
        <img src="<%=basePath%>img/2.jpg" alt="...">
      </a>
    </div>
    <!--图片-->
    <div class="col-lg-6">
      <a href="#" class="thumbnail">
        <img src="<%=basePath%>img/2.jpg" alt="...">
      </a>
    </div>
  </div>



  <!--预定面板-->
  <div class="panel panel-info">
    <!--预定面板头-->
    <div class="panel-heading">
      <h3 class="panel-title">房间预订</h3>
      <form role="form" name="conditionForm" class="form-inline">

        <div class="conditionPart form-group form-inline" ng-class="{'has-error':conditionForm.comeDate.$invalid}">
          <label class="control-label" for="comeDate">入住日期：</label>
          <input id="comeDate" name="comeDate" class="form-control" type="text" ng-pattern="/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/" ng-model="condition.comeDate" ng-change="cancelReady()" placeholder="入住日期" required/>&nbsp;&nbsp;
        </div>

        <div class="conditionPart form-group form-inline" ng-class="{'has-error':conditionForm.leaveDate.$invalid}">
          <label class="control-label" for="leaveDate">离店日期：</label>
          <input id="leaveDate"name="leaveDate" class="form-control" type="text" ng-model="condition.leaveDate" ng-change="cancelReady()" ng-pattern="/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/" placeholder="离店日期" required/>
        </div>

        <button type="submit" class="btn btn-info" ng-click="showRoomTypeList();setReady()" ng-disabled="conditionForm.comeDate.$invalid || conditionForm.leaveDate.$invalid">搜索</button>
      </form>
    </div>

    <!--预定面板主体-->
    <div class="panel-body">
      <!--房间类别信息条目-->
      <a href="javascript:;" class="col-lg-12 home_info_item" ng-repeat="type in roomTypeList">
        <span class="col-lg-2">{{type.roomType.typeName}}</span>
        <span class="col-lg-2 col-lg-offset-6" style="color:red;">¥{{type.roomPrice}}</span>
        <button type="button" class="col-lg-2 btn btn-primary" ng-click="reservePage(type.branchRoomTypeId)" ng-disabled="conditionForm.comeDate.$invalid || conditionForm.leaveDate.$invalid||!isReady">预订</button>
      </a>

    </div>

  </div>

</div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>js/jquery-1.11.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>


</body>
</html>
