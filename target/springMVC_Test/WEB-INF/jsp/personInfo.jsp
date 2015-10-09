<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 15/4/5
  Time: 下午10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.text.SimpleDateFormat" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="myApp">
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
  <script src="<%=basePath%>controllerjs/hotCityHotelController.js?randomId=<%=Math.random()%>"></script>


  <style type="text/css">
    .main_body {
      padding: 5px;
      margin: 50px 100px;
    }

    /* Custom Styles */
    ul.nav-tabs{
      width: 140px;
      margin-top: 20px;
      border-radius: 4px;
      border: 1px solid #ddd;
      box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
    }
    ul.nav-tabs li{
      margin: 0;
      border-top: 1px solid #ddd;
    }
    ul.nav-tabs li:first-child{
      border-top: none;
    }
    ul.nav-tabs li a{
      margin: 0;
      padding: 8px 16px;
      border-radius: 0;
    }
    ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
      color: #fff;
      background: #0088cc;
      border: 1px solid #0088cc;
    }
    ul.nav-tabs li:first-child a{
      border-radius: 4px 4px 0 0;
    }
    ul.nav-tabs li:last-child a{
      border-radius: 0 0 4px 4px;
    }
    ul.nav-tabs.affix{
      top: 30px; /* Set the top position of pinned element */
    }




  </style>

</head>
<body ng-controller="personInfoController" data-spy="scroll" data-target="#myScrollspy" ng-init="member.name='${sessionScope.member.name}';login.status=${sessionScope.member!=null};">


<!--顶部菜单-->
<nav class="navbar navbar-default ">

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
      <li class="active"><a href="<%=basePath%>hotelSearch/main.html">主页 <span class="sr-only">(current)</span></a></li>
      <li><a href="<%=basePath%>hotelSearch/search.html">酒店</a></li>
    </ul>


    <%--<!-- 右侧菜单-->--%>
    <%--<ul class="nav navbar-nav navbar-right">--%>
      <%--<li ng-show="!login.status"><a href="javascript:;"  data-toggle="modal" data-target=".myModal">登录</a></li>--%>
      <%--<li ng-show="login.status"><a href="<%=basePath%>person/personCenter.html">{{member.name}}</a></li>--%>
      <%--<li><a href="javascript:;" ng-click="logout()">退出</a></li>--%>
    <%--</ul>--%>

  </div>
</nav>

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
      <%--<div class="modal-body">--%>
        <%--<div class="form-group">--%>
          <%--<label for="exampleInputEmail1">账号：</label>--%>
          <%--<input type="text" class="form-control" id="username" placeholder="请输入账号">--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
          <%--<label for="exampleInputPassword1">密码：</label>--%>
          <%--<input type="password" class="form-control" id="pwd" placeholder="请输入密码">--%>
        <%--</div>--%>
      <%--</div>--%>
      <%--<!--登录框 脚部-->--%>
      <%--<div class="modal-footer">--%>
        <%--<button type="button" class="btn btn-primary">登录</button>--%>
        <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
      <%--</div>--%>
    <%--</div><!-- /.modal-content -->--%>
  <%--</div><!-- /.modal-dialog -->--%>
<%--</div><!-- /.modal -->--%>







<div class="main_body"  ng-init="personInfo.name='${sessionScope.member.name}';personInfo.idNumber='${sessionScope.member.memberId}';personInfo.city=${sessionScope.member.city.cityId};personInfo.province=${sessionScope.member.province.provinceId};personInfo.sex='${sessionScope.member.sex}'">

  <div class="row">

    <!--功能列表-->
    <div class="col-lg-2" id="myScrollspy">
      <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="125">
        <li class="active"><a href="#personInfoPart">个人信息</a></li>
        <li><a href="#pwdPart">密码管理</a></li>
        <li><a href="#reserveInfoPart">订单信息</a></li>
        <li><a href="#historyReserveInfoPart">历史订单</a></li>
      </ul>
    </div>

    <!--功能主体-->
    <div class="col-lg-10">

      <!-- 个人信息 面板 -->
      <!--信息面板-->
      <div class="panel panel-info" id="personInfoPart">
        <!--面板头部-->
        <div class="panel-heading">
          <h3 class="panel-title">个人信息</h3>
        </div>

        <!--面板主体-->
        <div class="panel-body personInfo">
          <!-- form.form-horizontal>(.form-group>(label.col-lg-2.col-lg-offset-2.control-label{Label}+(.col-lg-6>input[type="text" value="text"])))*10  -->
          <form name="personInfoForm" class="form-horizontal">
            <div class="form-group" ng-class="{'has-error':personInfoForm.name.$invalid}">
              <label for="name" class="col-lg-2 col-lg-offset-2 control-label">姓名</label>
              <div class="col-lg-6"><input type="text" id="name" name="name" ng-model="personInfo.name" class="form-control" placeholder="姓名" required></div>
            </div>
            <div class="form-group" ng-class="{'has-error':personInfoForm.idNumber.$invalid}">
              <label for="idNumber" class="col-lg-2 col-lg-offset-2 control-label">身份证号</label>
              <div class="col-lg-6"><input type="text" id="idNumber" name="idNumber" ng-model="personInfo.idNumber" class="form-control" placeholder="身份证号" required></div>
            </div>
            <div class="form-group">
              <label for="sex" class="col-lg-2 col-lg-offset-2 control-label">性别</label>
              <div class="col-lg-6">
                <select class="form-control" id="sex" name="sex" ng-model="personInfo.sex">
                  <option value="男">男</option>
                  <option value="女">女</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="province" class="col-lg-2 col-lg-offset-2 control-label">省</label>
              <div class="col-lg-6">
                <select class="form-control" id="province" name="province" ng-model="personInfo.province">
                  <option value="1">吉林</option>
                  <option value="2">辽宁</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="city" class="col-lg-2 col-lg-offset-2 control-label">市</label>
              <div class="col-lg-6">
                <select class="form-control" id="city" name="city" ng-model="personInfo.city">
                  <option value="1">长春</option>
                  <option value="2">通化</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <button class="btn btn-info col-lg-6 col-lg-offset-3"
                      ng-click="updatePersonInfo()"
                      ng-disabled="(personInfoForm.name.$pristine || personInfoForm.name.$invalid) && (personInfoForm.idNumber.$pristine || personInfoForm.idNumber.$invalid) && personInfoForm.sex.$pristine && personInfoForm.province.$pristine && personInfoForm.city.$pristine">更改</button>
            </div>
          </form>
        </div>
      </div>
      <!-- ./ 个人信息 面板 -->


      <!-- 密码修改 面板 -->
      <!--信息面板-->
      <div class="panel panel-info" id="pwdPart">
        <!--面板头部-->
        <div class="panel-heading">
          <h3 class="panel-title">密码管理</h3>
        </div>

        <!--面板主体-->
        <div class="panel-body personInfo">
          <!-- form.form-horizontal>(.form-group>(label.col-lg-2.col-lg-offset-2.control-label{Label}+(.col-lg-6>input[type="text" value="text"])))*10  -->
          <form name="pwdForm" ng-submit="" class="form-horizontal">
            <div class="form-group">
              <label for="oldPwd" class="col-lg-2 col-lg-offset-2 control-label">旧密码</label>
              <div class="col-lg-6"><input type="password" id="oldPwd" name="oldPwd" ng-model="pwd.oldPwd" class="form-control" placeholder="旧密码" required></div>
            </div>
            <div class="form-group">
              <label for="newPwd" class="col-lg-2 col-lg-offset-2 control-label">新密码</label>
              <div class="col-lg-6"><input type="password" id="newPwd" name="newPwd" ng-model="pwd.newPwd" class="form-control" placeholder="新密码" required></div>
            </div>
            <div class="form-group">
              <label for="againPwd" class="col-lg-2 col-lg-offset-2 control-label">确认密码</label>
              <div class="col-lg-6">
                <input type="password" id="againPwd" name="againPwd" ng-model="pwd.againPwd" class="form-control" placeholder="确认密码" required>
                <span style="color:red;" ng-show="pwdForm.againPwd.$dirty&&(pwd.newPwd!=pwd.againPwd)">两次输入的密码不匹配！</span>
              </div>
            </div>

            <div class="form-group">
              <button class="btn btn-info col-lg-6 col-lg-offset-3" ng-disabled="pwdForm.oldPwd.$invalid||pwdForm.newPwd.$invalid||pwdForm.againPwd.$invalid||(pwd.newPwd!=pwd.againPwd)">更改</button>
            </div>
          </form>
        </div>

      </div>
      <!-- ./ 密码修改 面板 -->


      <!-- 订单信息 面板 -->
      <!--信息面板-->
      <div class="panel panel-info" id="reserveInfoPart">
        <!--面板头部-->
        <div class="panel-heading">
          <h3 class="panel-title">订单信息</h3>
        </div>

        <!--面板主体-->
        <div class="panel-body">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th>分店</th>
              <th>房间类别</th>
              <th>房间数量</th>
              <th>到店日期</th>
              <th>离店日期</th>
              <th>退订</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reserve" items="${reserveInfoList}" step="1">
              <tr>
                <td>${reserve.branchRoomTypeInfo.branchInfo.branchName}</td>
                <td>${reserve.branchRoomTypeInfo.roomType.typeName}</td>
                <td>${reserve.reserveCount}</td>
                <td><fmt:formatDate value="${reserve.reserveTime}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${reserve.reserveReturnRoomTime}" pattern="yyyy-MM-dd"/></td>
                <td>
                  <button class="btn btn-info" ng-click="cancelReserve(${reserve.reserveId})">取消订单</button>
                </td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>

      </div>
      <!-- ./ 订单信息 面板 -->


      <!-- 历史订单信息 面板 -->
      <!--信息面板-->
      <div class="panel panel-info" id="historyReserveInfoPart">
        <!--面板头部-->
        <div class="panel-heading">
          <h3 class="panel-title">历史订单</h3>
        </div>

        <!--面板主体-->
        <div class="panel-body reserveInfoPart">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th>分店</th>
              <th>房间类别</th>
              <th>房间数量</th>
              <th>到店日期</th>
              <th>离店日期</th>
              <th>状态</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="reserve" items="${historyList}" step="1">
              <tr>
                <td>${reserve.branchRoomTypeInfo.branchInfo.branchName}</td>
                <td>${reserve.branchRoomTypeInfo.roomType.typeName}</td>
                <td>${reserve.reserveCount}</td>
                <td><fmt:formatDate value="${reserve.reserveTime}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${reserve.reserveReturnRoomTime}" pattern="yyyy-MM-dd"/></td>
                <td>${reserve.reserveState}</td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>

      </div>
      <!-- ./ 历史订单信息 面板 -->

    </div>
  </div>
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>js/jquery-1.11.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>


</body>
</html>
