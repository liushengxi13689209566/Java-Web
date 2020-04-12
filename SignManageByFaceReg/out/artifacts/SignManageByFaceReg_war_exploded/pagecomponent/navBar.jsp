<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/12
  Time: 2:23 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- 导航栏 -->
<div id="navBar">
    <!-- 此处加载顶部导航栏 -->
    <!-- 导航栏 -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- 导航栏标题 -->
            <div class="navbar-header">
                <a href="javascript:void(0)" class="navbar-brand home">实验课考勤管理系统</a>
            </div>

            <!-- 导航栏下拉菜单：注销登陆 -->
            <div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle"
                           data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
                            <span>欢迎&nbsp;</span> <span id="nav_userName">用户名:${sessionScope.userName}</span>
                            <!--小三角-->
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <%--                            <!-通过 session 获取的 name 的值-->--%>
                            <%--                            <shiro:hasRole name="commonsAdmin">--%>
                            <%--                                <li>--%>
                            <%--                                    <a href="#" id="editInfo"> <span--%>
                            <%--                                            class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;修改个人信息</a></li>--%>
                            <%--                            </shiro:hasRole>--%>
                            <li>
                                <a href="javascript:void(0)" id="signOut"> <span
                                        class="glyphicon glyphicon-off"></span> &nbsp;&nbsp;注销登录
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

</body>
</html>
