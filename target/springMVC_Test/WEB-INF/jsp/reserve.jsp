<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/4/2
  Time: 下午1:38
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
  <title>订单信息</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <!--AngularJS-->
  <script src="<%=basePath%>js/angular.js"></script>
  <!-- hotCityHotelController.js -->
  <script src="<%=basePath%>controllerjs/hotCityHotelController.js?randomId=<%=Math.random()%>"></script>


  <style type="text/css">
    .main_body {
      padding: 5px;
      margin: 50px 200px;
    }

    .bookInfo table {
      width: 100%;
    }

    .bookInfo table tr td{
      padding: 10px 0px;
    }

  </style>

</head>
<body ng-controller="reservePageController" ng-init="reserveInfo.comeDate='${comeDate}';reserveInfo.leaveDate='${leaveDate}';reserveInfo.branchId='${conditionInfo.branchId}';reserveInfo.roomTypeId='${roomInfo.roomType.roomTypeId}';reserveInfo.name='${sessionScope.member.name}';setTotalPrice()">

<!--顶部菜单-->
<nav class="navbar navbar-default" ng-controller="loginController" ng-init="member.name='${sessionScope.member.name}';login.status=${sessionScope.member!=null};">

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


    <%--<!-- 右侧菜单-->--%>
    <%--<ul class="nav navbar-nav navbar-right">--%>
      <%--<li ng-show="!login.status"><a href="javascript:;"  data-toggle="modal" data-target=".myModal">登录</a></li>--%>
      <%--<li ng-show="login.status"><a href="<%=basePath%>person/personCenter.html">{{member.name}}</a></li>--%>
      <%--<li><a href="javascript:;" ng-click="logout()">退出</a></li>--%>
    <%--</ul>--%>

  </div>

  <%--<!--登录框-->--%>

  <%--<div class="modal fade myModal"  data-toggle="modal">--%>
    <%--<div class="modal-dialog">--%>
      <%--<div class="modal-content">--%>
        <%--<!--登录框 头部-->--%>
        <%--<div class="modal-header">--%>
          <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
          <%--<h4 class="modal-title">用户登录</h4>--%>
        <%--</div>--%>
        <%--<!--登录框 主体-->--%>
        <%--<form role="form" name="loginForm" ng-submit="loginSubmit()">--%>
          <%--<div class="modal-body">--%>
            <%--<div class="form-group" ng-class="{'has-error':loginForm.account.$invalid}">--%>
              <%--<label for="account" class="control-label">账号：</label>--%>
              <%--<input type="text" class="form-control" id="account" name="account" ng-model="login.account" placeholder="请输入账号" required />--%>
            <%--</div>--%>
            <%--<div class="form-group" ng-class="{'has-error':loginForm.pwd.$invalid}">--%>
              <%--<label for="pwd" class="control-label">密码：</label>--%>
              <%--<input type="password" class="form-control" id="pwd" name="pwd" ng-model="login.pwd" placeholder="请输入密码" required />--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<!--登录框 脚部-->--%>
          <%--<div class="modal-footer">--%>
            <%--<span style="color: red" ng-show="login.error">账号或密码错误！</span>--%>
            <%--<button type="submit" class="btn btn-primary" ng-disabled="loginForm.username.$invalid||loginForm.pwd.$invalid">登录</button>--%>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
          <%--</div>--%>
        <%--</form>--%>
      <%--</div><!-- /.modal-content -->--%>
    <%--</div><!-- /.modal-dialog -->--%>
  <%--</div><!-- /.modal -->--%>

</nav>






<div class="main_body">
  <div class="row">

    <!--预订信息表单-->
    <form ng-submit="submitReserve()">
      <div class="col-lg-8">

        <!--预订信息部分-->
        <div class="bookInfo">
          <h3>预订信息</h3>
          <hr>
          <table>
            <tr>
              <td>房间类型：</td>
              <td>${roomInfo.roomType.typeName}</td>
            </tr>
            <tr>
              <td>入住日期：</td>
              <td>
                <input type="text" class="form-control" id="comeDate" placeholder="入住日期" ng-model="reserveInfo.comeDate" disabled />
              </td>
            </tr>
            <tr>
              <td>离店日期：</td>
              <td>
                <input type="text" class="form-control" id="LeaveDate" placeholder="离开日期" ng-model="reserveInfo.leaveDate" disabled />
              </td>
            </tr>
            <tr>
              <td>房间数量：</td>
              <td>
                <select ng-model="reserveInfo.roomCount"  ng-change="setTotalPrice()" class="form-control">
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>单价/天：</td>
              <td>
                <span>¥${roomInfo.roomPrice}</span>
              </td>
            </tr>
            <tr>
              <td>总价：</td>
              <td>
                <span>¥{{reserveInfo.totalPrice}}</span>
              </td>
            </tr>
          </table>
        </div>

        <!--入住信息部分-->
        <div class="bookInfo">
          <h3>入住信息</h3>
          <hr>
          <table>
            <tr>
              <td>预定人姓名：</td>
              <td><input type="text" name="name" class="form-control" id="name" ng-model="reserveInfo.name"  disabled/></td>
            </tr>
            <tr>
              <td>联系电话：</td>
              <td><input type="text" name="tel" class="form-control" id="tel" placeholder="电话" ng-model="reserveInfo.tel"/></td>
            </tr>
            <tr>
              <td>最晚到店时间：</td>
              <td>
                <select class="form-control" ng-model="reserveInfo.latestTime">
                  <option value="14:00">14:00</option>
                  <option value="16:00">16:00</option>
                  <option value="20:00">20:00</option>
                  <option value="22:00">22:00</option>
                </select>
              </td>
            <tr>
              <td colspan="2" ng-show="reserveInfo.latestTime" style="color:red;">
                <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                如果 {{reserveInfo.latestTime}} 前不能到达，房间可能会被取消，如需延时请及时联系酒店或商家。
              </td>
            </tr>
            </tr>
          </table>
        </div>

        <!--提交按钮-->
        <div>
          <input class="col-lg-4 col-lg-offset-4 btn btn-info" type="submit" value="提交订单" />
        </div>

      </div>
    </form>
    <!-- ./ 预订信息表单-->



    <div class="col-lg-4">

      <!--推荐条目-->
      <div class="row">
        <div class="col-lg-12">
          <div class="thumbnail">
            <img src="<%=basePath%>img/2.jpg" alt="...">
            <div class="caption">
              <h3><a href="">${branchInfo.branchName}</a></h3>
              <p>${branchInfo.address}</p>
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
