<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/9
  Time: 9:43 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程管理</title>
    <style>
        .menu_item {
            cursor: pointer;
        }
    </style>
</head>
<body>

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
                <%--                <th>学生管理操作</th>--%>
                <%--                添加课程学生，删除课程学生，查看课程学生。--%>
                <th>课程管理操作</th>
            </tr>
            </thead>
            <tbody id="course_table">
            </tbody>
        </table>
    </div>
</div>

<script>
    $.ajax({
        type: 'GET',
        url: 'course/getMyTeaCourse',
        dataType: 'json',
        contentType: 'application/json',
        data: {
            // searchType: 'searchAll',
            // keyWord: '',
            // offset: -1,
            // limit: -1
        },
        success: function (response) {
            console.log(response)
            $.each(response.rows, function (index, elem) {
                console.log(elem)
                $('#course_table').append(
                    "<tr><td>" + elem.course_id
                    + "</td><td>" + elem.course_name
                    + "</td><td>" + elem.course_times
                    + "</td><td>" + elem.course_credit
                    + "</td><td>" + timestampToTime(elem.course_start)
                    + "</td><td>" + timestampToTime(elem.course_end)
                    + "</td><td><a name='${pageContext.request.contextPath}/SignCase/OneCourseSignCase/getOneStuSignCase?course_id=" + elem.course_id + "' onclick='click_item(this)'>查看考勤</a></td></tr>"
                );
            });
        },
        error: function (response) {
            // $('#course_table').append("<option value='-1'>加载失败</option>");
            $('#course_table').append("<tr><td>" + "加载失败" + "</td></tr>");
        }
    })
</script>

</body>
</html>
