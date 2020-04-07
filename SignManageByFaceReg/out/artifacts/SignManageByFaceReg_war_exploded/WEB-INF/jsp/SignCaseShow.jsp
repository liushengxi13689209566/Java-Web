<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .table > thead > tr > th {
            text-align: center;
        }
    </style>
</head>
<body>
<script>
    function timestampToTime(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
        var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
        var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
        var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
        return Y + M + D + h + m + s;
    }

    function transToMsg(ch) {
        var msg;
        if (ch == '0')
            msg = "未考勤"
        if (ch == '1')
            msg = "考勤成功";
        if (ch == '2')
            msg = "迟到";
        return msg;
    }
</script>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>第几节课</th>
                    <th>上课开始时间</th>
                    <th>上课结束时间</th>
                    <th>签到状态</th>
                    <%--                    <th>书籍详情</th>--%>
                    <%--                    <th>操作</th>--%>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="signCaseRecord" items="${requestScope.get('list')}">
                    <tr>
                        <td>${signCaseRecord.getId()}</td>
                        <td>${signCaseRecord.getCourse_start_timestamp()}</td>
                        <td>${signCaseRecord.getCourse_end_timestamp()}</td>
                        <td>${signCaseRecord.getSign_case_flag()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>