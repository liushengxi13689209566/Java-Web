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

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>第几节课</th>
                    <th>上课开始时间</th>
                    <th>上课结束时间</th>
                    <%--                    <th>签到状态--%>
                    <th>
                        <div class="dropdown">
                            <button class="btn btn-primary" type="button" id="dropdownMenu2"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                签到状态
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <li><a href="#">迟到</a></li>
                                <li><a href="#">未考勤</a></li>
                                <li><a href="#">考勤成功</a></li>
                            </ul>
                        </div>

                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="signCaseRecord" items="${requestScope.get('list')}">
                    <tr>
                        <td>${signCaseRecord.getId()}</td>
                        <td>${signCaseRecord.getCourse_start_timestamp()}</td>
                        <td>${signCaseRecord.getCourse_end_timestamp()}</td>
                        <td class="tran_str">${signCaseRecord.getSign_case_flag()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>

    var da = document.getElementsByClassName('tran_str')
    for (let i = 0; i < da.length; i++) {
        //console.log(da[i].innerHTML)
        da[i].innerHTML = transToMsg(da[i].innerHTML)
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
</body>
</html>