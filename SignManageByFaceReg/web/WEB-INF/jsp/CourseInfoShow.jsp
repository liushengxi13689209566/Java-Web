<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/12
  Time: 2:30 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<iframe src="${pageContext.request.contextPath}/pagecomponent/navBar.jsp" name="topFrame" scrolling="no"
        noresize="noresize" id="topFrame">
</iframe>

<iframe src="${pageContext.request.contextPath}/pagecomponent/sideBar.jsp" name="leftFrame" scrolling="no"
        noresize="noresize" id="leftFrame">

</iframe>
<div class="row clearfix">
    <div class="col-md-12 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程名称</th>
                <th>课时</th>
                <th>学分</th>
                <th>起始时间</th>
                <th>结束时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="courseInfoShow" items="${requestScope.get('list')}">
                <tr>
                    <td>${courseInfoShow.getCourse_id()}</td>
                    <td>${courseInfoShow.getCourse_name()}</td>
                    <td>${courseInfoShow.getCourse_times()}</td>
                    <td>${courseInfoShow.getCourse_credit()}</td>
                    <td>${courseInfoShow.getCourse_start()}</td>
                    <td>${courseInfoShow.getCourse_end()}</td>
                        <%--                    <td>--%>
                        <%--                            &lt;%&ndash; 没有权利去更改学生信息吧 &ndash;%&gt;--%>
                        <%--                        <a class='menu_item'--%>
                        <%--                           name="${pageContext.request.contextPath}/student/delOneStudentInCourse/?student_id=${courseInfoShow.getId()}&course_id=${requestScope.get('course_id')}"--%>
                        <%--                        >删除</a>--%>
                        <%--                    </td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
