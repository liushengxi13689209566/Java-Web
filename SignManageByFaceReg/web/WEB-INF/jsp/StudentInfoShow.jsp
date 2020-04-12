<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/10
  Time: 2:04 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>查看课程学生</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .table > thead > tr > th {
            text-align: center;
        }

        .menu_item {
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="container">
    <%--添加学生与整体导入学生信息的接口--%>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增一名学生</a>
        </div>

        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增一名学生</a>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>操作</th>
                    <%--添加课程学生，删除课程学生，查看课程学生。--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="studentInfoShow" items="${requestScope.get('list')}">
                    <tr>
                        <td>${studentInfoShow.getId()}</td>
                        <td>${studentInfoShow.getName()}</td>
                        <td>${studentInfoShow.getMajor_name()}</td>
                        <td>${studentInfoShow.getClass_name()}</td>
                        <td>
                                <%-- 没有权利去更改学生信息吧 --%>
                            <a class='menu_item'
                               name="${pageContext.request.contextPath}/student/delOneStudentInCourse/?student_id=${studentInfoShow.getId()}&course_id=${requestScope.get('course_id')}"
                               onclick="click_item(this)">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>

<script>
    $.ajax({
        type: 'GET',
        url: 'student/delOneStudentInCourse/?student_id=${studentInfoShow.getId()}&course_id=${requestScope.get('course_id')}',
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            console.log(response)
            alert("删除成功")
        },
        error: function (response) {
            console.log(response)
        }
    })
</script>

</html>
