<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/3/29
  Time: 4:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>实验课考勤信息查看</title>
    <%--    <style>--%>
    <%--        .menu_item{--%>
    <%--            cursor:pointer;--%>
    <%--        }--%>
    <%--    </style>--%>
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
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="course_table">
            </tbody>
        </table>
    </div>
</div>

<script>
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        return Y + M + D;
    }
</script>

<script>
    function click_item(e) {
        var url = $(e).attr('name');
        console.log(url)
        $('#panel').load(url);
    }
</script>
<script>
    $.ajax({
        type: 'GET',
        url: 'course/getMyCourse',
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
                    + "</td><td><a  href='javascript:void(0)' class='menu_item' name='${pageContext.request.contextPath}/SignCase/OneCourseSignCase/getOneStuSignCase?course_id=" + elem.course_id + "' onclick='click_item(this)'>查看考勤</a></td></tr>"
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