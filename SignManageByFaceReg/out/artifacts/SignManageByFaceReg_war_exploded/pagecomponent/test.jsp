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
</head>
<body>

<div class="row clearfix">
    <div class="col-md-12 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>start</th>
                <th>end</th>
                <th>签到状态</th>
                <%--                    <th>书籍详情</th>--%>
                <%--                    <th>操作</th>--%>
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
    $.ajax({
        type: 'GET',
        url: 'SignCase/OneCourseSignCase/getOneStuSignCase',
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
                    "<tr><td>" + elem.course_start_timestamp
                    + "</td><td>" + elem.course_end_timestamp
                    + "</td><td>" + elem.sign_case_flag
                    + "</td></tr>"
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