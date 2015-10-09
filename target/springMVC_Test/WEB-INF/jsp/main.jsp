<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/3/30
  Time: 下午1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html ng-app="myApp" lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>主页</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">

  <!--AngularJS-->
  <script src="<%=basePath%>js/angular.js"></script>
  <!-- hotCityHotelController.js -->
  <script src="<%=basePath%>controllerjs/hotCityHotelController.js"></script>


  <style type="text/css">
    .search_input_row {
      padding: 10px;
      margin-bottom: 10px;
      background: #A6FFFF;
    }

    /*.alist { background: red; height: 100%;}
    .alist ul{list-style:none; }
    .alist li{float:left; line-height:30px; text-align:center;}
    .alist li a{display:inline-block; width:80px;  }
    .alist li a:hover{background:yellow;}*/



    .citylist div button {
      margin-top: 15px;
      padding: 6px 10px;
      border-width: 0px;
    }

    .cityHotelList {
      padding: 20px 10px;
      border-width: 10px;
      border-color: black;
    }

    .cityHotelList div{
      padding-top: 10px;
      padding-right: 10px;
    }

    .cityHotelList div dt a {
      text-decoration: none;
    }

    .cityHotelList div dd a {
      text-decoration: none;
      color: black;
      margin-right: 10px;
    }
    .cityHotelList div dd a:hover {
      color: blue;
    }

    .cheapHotelPanel {
      padding: 10px 0px;
    }

    .city-panel {
      z-index: 99;
      width: 500px;
      min-height: 100px;
      border:thin solid;
      position: absolute;
      left: 230px;
      top:120px;

      background-color: white;

    }

    .city-panel-head {
      padding: 10px 0px;
    }

    .city-panel-head a {
      padding:1px 2px;
      font-size: 13px;
    }
    .city-panel-head a:hover{
      border-bottom: thin solid;
    }

    .cityTable tr td {
      text-align: inherit;
    }

    .cityTable tr td a {
      font-size: 11px;
    }
    .cityTable tr td span{
      color:blue;

    }

  </style>


</head>
<body  ng-controller="cityHotelController">


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






<!--搜索表单-->
<div class="row search_input_row">
  <div class="col-lg-8 col-lg-offset-2" style="border-width: 10px;">
{{conditionData}}
    <form name="searchForm" class="form-inline" action="<%=basePath%>hotelSearch/search.html" method="post">
      <div class="form-group">
        <input type="text" class="form-control" id="cityId" name="cityId" placeholder="城市" ng-model="conditionData.cityId"/>
      </div>
      <div class="form-group" ng-class="{'has-error':searchForm.comeDate.$invalid}">
        <input type="text" class="form-control" id="comeDate" name="comeDate" placeholder="入住日期" ng-model="conditionData.comeDate" ng-pattern="/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/"/>
      </div>
      <div class="form-group" ng-class="{'has-error':searchForm.leaveDate.$invalid}">
        <input type="text" class="form-control" id="leaveDate" name="leaveDate" placeholder="离店日期" ng-model="conditionData.leaveDate" ng-pattern="/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/"/>
      </div>
      <button type="submit" class="btn btn-default" ng-disabled="!((conditionData.comeDate=='' && conditionData.leaveDate=='') || (conditionData.comeDate!='' && conditionData.leaveDate!='' && searchForm.comeDate.$valid && searchForm.leaveDate.$valid))">搜索</button>
    </form>
  </div>
</div>



<!-- 城市选择面板 -->
<!-- .city-panel>(.city-panel-head>a.btn{$}*26)+(.cityPanelBody[style="display: none"]>a.btn{$}*10)*26 -->
<div class="city-panel" ng-show="cityPanelShow">

  <div class="city-panel-head row">
    <a href="" class="btn col-lg-2 col-lg-offset-1" ng-click="setLetters(['A','B','C','D','E'])">ABCDE</a>
    <a href="" class="btn col-lg-2" ng-click="setLetters(['F','G','H','I','J'])">FGHIJ</a>
    <a href="" class="btn col-lg-2" ng-click="setLetters(['K','L','M','N','O'])">KLMNO</a>
    <a href="" class="btn col-lg-2" ng-click="setLetters(['P','Q','R','S','T'])">PQRST</a>
    <a href="" class="btn col-lg-2" ng-click="setLetters(['U','V','W','X','Y','Z'])">UVWXYZ</a>
  </div>

  <div class="cityPanelBody">
    <table class="cityTable" width="100%">
      <tr ng-repeat="letter in letters">
        <td>
          <span>{{letterCityList.letters[$index]}}</span>
        </td>
        <td>
          <a href="" class="btn col-lg-2" ng-repeat="city in letterCityList.citys[$index]">{{city.cityName}}</a>
        </td>
      </tr>
    </table>
  </div>

</div>
<!-- ./ 城市选择面板 -->

<!--主体-->
<div class="row body_main ">
  <div class="col-lg-12">

    <!--左侧主体-->
    <div class="col-lg-10 col-lg-offset-1 body_left">
      <div class="row" style="padding:0px 50px;">
        <div class="col-lg-8 col-lg-offset-2">
          <!--轮播图片-->
          <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Pictures -->
            <div class="carousel-inner" role="listbox">
              <div class="item active">
                <img src="<%=basePath%>img/1.jpg" alt="...">
                <div class="carousel-caption">
                  <h3>First</h3>
                  <p>the first picture!</p>
                </div>
              </div>

              <div class="item">
                <img src="<%=basePath%>img/2.jpg" alt="...">
                <div class="carousel-caption">
                  <h3>Second</h3>
                  <p>the second picture!</p>
                </div>
              </div>

              <div class="item">
                <img src="<%=basePath%>img/2.jpg" alt="...">
                <div class="carousel-caption">
                  <h3>Third</h3>
                  <p>the third picture!</p>
                </div>
              </div>
              <!--可在此处添加文本-->
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
        </div>


      <div class="col-lg-10 col-lg-offset-1">
        <!--City Hot Hotel Composite-->
        <div>

          <!--Head-->
          <div class="row">

            <div class="col-lg-2">
              <h3>酒店目录</h3>
            </div>
            <div class="col-lg-9 col-lg-offset-1 citylist">
              <div class="btn-group" role="group" aria-label="...">

                <button type="button" class="btn btn-default"
                        ng-class="{active:$index == selectedHotCity}"
                        ng-repeat="city in hotCityList"
                        ng-click="changeCityForHot($index)">
                  {{city.cityName}}
                </button>

              </div>
            </div>

          </div>
          <!--Body-->
          <div class="row cityHotelList">

            <!-- hot city list -->
            <div class="col-lg-3" ng-repeat="branch in hotBranchList">
              <a target="_blank" href="<%=basePath%>hotelDetail/hotelDetailPage.html?branchId={{branch.branchId}}">{{branch.branchName}}</a>
            </div>

          </div>

        </div>


        <!--cheap Hotel Composite-->
        <div>

          <!--Head-->
          <div class="row">

            <div class="col-lg-2">
              <h3>超值酒店</h3>
            </div>

            <div class="col-lg-9 col-lg-offset-1 citylist">
              <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-default"
                        ng-class="{active:$index == selectedCheapCity}"
                        ng-repeat="city in hotCityList"
                        ng-click="changeCityForCheap($index)">
                  {{city.cityName}}
                </button>

              </div>
            </div>

          </div>
          <!--Body-->
          <div class="row cityHotelList">
            <div class="row panel panel-info cheapHotelPanel">

              <div class="col-lg-4" ng-repeat="branch in cheapBranchList">
                <a target="_blank" href="<%=basePath%>hotelDetail/hotelDetailPage.html?branchId={{branch.branchInfo.branchId}}"><span style="color:red;">￥{{branch.roomPrice}}</span>&nbsp;&nbsp;&nbsp;&nbsp;{{branch.branchInfo.branchName}}</a>
              </div>

            </div>
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
