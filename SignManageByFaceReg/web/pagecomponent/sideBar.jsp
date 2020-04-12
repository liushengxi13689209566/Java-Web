<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/12
  Time: 2:24 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/mainPage.css">
</head>
<body>

<!-- 左侧导航栏 -->
<div id="sideBar" class="col-md-2 col-sm-3">
    <!--  此处加载左侧导航栏 -->
    <!-- 左侧导航栏  -->
    <!-- 依照 accordion 为 parent -->
    <div class="panel-group" id="accordion">
        <%--1。个人信息管理--%>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <!--<a> 链接至 class 为 collapse1 的 div，即库存管理与出入库管理-->
                    <a href="#collapse1" data-toggle="collapse" data-parent="#accordion"
                    <%--class="parentMenuTitle collapseHead">库存管理</a>--%>
                    <%--                                    个人信息管理每一种身份都需要--%>
                       class="parentMenuTitle collapseHead">个人信息管理</a>
                    <div class="pull-right">
                        <span class="caret"></span>
                    </div>
                </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse collapseBody">
                <div class="panel-body">
                    <ul class="list-group">
                        <!--若为普通管理员-->
                        <%--                                <shiro:hasRole name="commonsAdmin">--%>
                        <li class="list-group-item">
                            <a href="javascript:void(0)" id="" class="menu_item"
                               name="pagecomponent/storageManagementCommon.jsp">修改密码</a>
                        </li>
                        <%--                                </shiro:hasRole>--%>
                        <!--若为超级管理员-->
                        <%--                                <shiro:hasRole name="systemAdmin">--%>
                        <li class="list-group-item">
                            <a href="javascript:void(0)" class="menu_item"
                               name="pagecomponent/storageManagement.jsp">更新人脸照片</a>
                        </li>
                        <%-- <li class="list-group-item">--%>
                        <%-- <a href="javascript:void(0)" id=""--%>
                        <%--class="menu_item"--%>
                        <%-- name="pagecomponent/stockRecordManagement.html">出入库记录</a>--%>
                        </li>
                        <%--                                </shiro:hasRole>--%>
                    </ul>
                </div>
            </div>
        </div>

        <%--2。实验课考勤信息管理功能--%>
        <shiro:hasAnyRoles name="student,teacher">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a href="#collapse2" data-toggle="collapse" data-parent="#accordion"
                           class="parentMenuTitle collapseHead">实验课考勤信息管理</a>
                        <div class="pull-right">
                            <span class="caret"></span>
                        </div>
                    </h4>
                </div>
                <div id="collapse2" class="panel-collapse collapse collapseBody in">
                    <div class="panel-body">
                        <shiro:hasRole name="student">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="javascript:void(0)" class="menu_item"
                                        <%--name="pagecomponent/stock-inManagement.jsp">实验课考勤信息查看</a>--%>
                                       name="pagecomponent/student-sign-case.jsp">实验课考勤信息查看</a>
                                </li>

                                <li class="list-group-item">
                                    <a href="javascript:void(0)" class="menu_item"
                                        <%--name="pagecomponent/stock-inManagement.jsp">实验课考勤信息查看</a>--%>
                                       name="pagecomponent/test.jsp">测试考勤数据</a>
                                </li>
                            </ul>
                        </shiro:hasRole>
                        <shiro:hasRole name="teacher">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="javascript:void(0)" class="menu_item"
                                        <%-- name="pagecomponent/stock-outManagement.jsp">实验课考勤信息查看</a>--%>
                                       name="pagecomponent/course-sign-case.jsp">实验课考勤信息查看</a>
                                </li>
                            </ul>
                        </shiro:hasRole>
                    </div>
                </div>
            </div>
        </shiro:hasAnyRoles>


        <%--3。课程信息管理功能--%>
        <shiro:hasAnyRoles name="teacher,admin">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a href="#collapse3" data-toggle="collapse" data-parent="#accordion"
                           class="parentMenuTitle collapseHead">课程信息管理</a>
                        <div class="pull-right">
                            <span class="caret"></span>
                        </div>
                    </h4>
                </div>
                <div id="collapse3" class="panel-collapse collapse collapseBody">
                    <div class="panel-body">
                        <ul class="list-group">
                            <!--若为普通管理员-->
                            <shiro:hasRole name="teacher">
                                <li class="list-group-item">
                                    <a href="javascript:void(0)" class="menu_item"
                                       name="pagecomponent/courseStudentHandle.jsp">学生管理</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:void(0)" class="menu_item"
                                       name="pagecomponent/courseHandle.jsp">课程管理</a>
                                </li>
                            </shiro:hasRole>
                            <!--若为超级管理员-->
                                <%--                                <shiro:hasRole name="systemAdmin">--%>
                                <%--                                    <li class="list-group-item">--%>
                                <%--                                        <a href="javascript:void(0)" class="menu_item"--%>
                                <%--                                           name="pagecomponent/storageManagement.jsp">更新人脸照片</a>--%>
                                <%--                                    </li>--%>
                                <%-- <li class="list-group-item">--%>
                                <%-- <a href="javascript:void(0)" id=""--%>
                                <%--class="menu_item"--%>
                                <%-- name="pagecomponent/stockRecordManagement.html">出入库记录</a>--%>
                                <%--</li>--%>
                                <%--</shiro:hasRole>--%>
                        </ul>
                    </div>
                </div>
            </div>
        </shiro:hasAnyRoles>


    </div>
</div>

</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.md5.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/mainPage.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
</html>
