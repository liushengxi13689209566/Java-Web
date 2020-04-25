<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤信息查看</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .table > thead > tr > th {
            text-align: center;
        }
    </style>
</head>


<body>
<%--获取参数--%>
<%
    String course_id = request.getParameter("course_id");
    System.out.println(course_id);

    String course_name = request.getParameter("course_name");
    System.out.println(course_name);
%>
<%--<script>--%>
<%--    // 使用参数--%>
<%--    var id = "<%=course_id%>";--%>
<%--    console.log("user == fvnjdfvjbfkjvbfkdvfbddkbkdbf")--%>
<%--    console.log("id == " + id)--%>
<%--</script>--%>

<script>
    //立即函数
    $(function () {
        signCaseRecordInit();
        // importAllStudentsInfo();
    })

    function getCourseID() {
        var temp = {
            course_id: <%=course_id%>
        }
        console.log(temp)
        return temp;
    }

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
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

    // 一门课学生列表表格初始化
    function signCaseRecordInit() {
        $('#signCaseRecord').bootstrapTable(
            {
                url: 'SignCase/OneCourseSignCase/getOneStuSignCase',
                method: 'GET',
                queryParams: getCourseID,
                //必须有，不然就会渲染不出来
                sidePagination: "server",
                dataType: 'json',
                // 分页选项
                // pagination: true,
                // pageNumber: 1,
                // pageSize: 5,
                // pageList: [5, 10, 25, 50, 100],
                //单选选项
                // clickToSelect: true
                responseHandler: function (res) {
                    $.each(res.rows, function (row) {
                        console.log(row)
                        $.inArray(row)
                    })
                    return res;
                },
                columns: [
                    {
                        field: 'id',
                        title: '第几节课'
                        //sortable: true
                    },
                    {
                        field: 'course_start_timestamp',
                        title: '课程开始时间',
                        formatter: timestampToTime
                    },
                    {
                        field: 'course_end_timestamp',
                        title: '课程结束时间',
                        formatter: timestampToTime
                    },
                    {
                        field: 'sign_case_flag',
                        title: '签到状态',
                        formatter: transToMsg
                    },
                    {
                        field: 'operation',
                        title: '操作',
                        formatter: function (value, row, index) {
                            return [
                                '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>'
                            ].join('')
                        },
                        events: {
                            'click .delete': function (e, value, row, index) {
                                // selectID = row.id;
                                if (confirm('确定删除 ? ') == false)
                                    return false;

                                console.log("确认删除了！！！")
                                <%--deleteOnCourseOneStudent(row.id, <%=course_id%>)--%>
                            }
                        }
                    }],
            })
    }

</script>
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><%=course_name%>考勤信息查看</li>
    </ol>
    <div class="panel-body">
        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="signCaseRecord" class="table table-striped"></table>
            </div>
        </div>
    </div>
</div>


<%--<div class="container">--%>
<%--    <div class="row clearfix">--%>
<%--        <div class="col-md-12 column">--%>
<%--            <table class="table table-hover table-striped">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>第几节课</th>--%>
<%--                    <th>上课开始时间</th>--%>
<%--                    <th>上课结束时间</th>--%>
<%--                    &lt;%&ndash;                    <th>签到状态&ndash;%&gt;--%>
<%--                    <th>--%>
<%--                        <div class="dropdown">--%>
<%--                            <button class="btn btn-primary" type="button" id="dropdownMenu2"--%>
<%--                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                                签到状态--%>
<%--                                <span class="caret"></span>--%>
<%--                            </button>--%>
<%--                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">--%>
<%--                                <li><a href="#">迟到</a></li>--%>
<%--                                <li><a href="#">未考勤</a></li>--%>
<%--                                <li><a href="#">考勤成功</a></li>--%>
<%--                            </ul>--%>
<%--                        </div>--%>

<%--                    </th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="signCaseRecord" items="${requestScope.get('list')}">--%>
<%--                    <tr>--%>
<%--                        <td>${signCaseRecord.getId()}</td>--%>
<%--                        <td>${signCaseRecord.getCourse_start_timestamp()}</td>--%>
<%--                        <td>${signCaseRecord.getCourse_end_timestamp()}</td>--%>
<%--                        <td class="tran_str">${signCaseRecord.getSign_case_flag()}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<script>

    var da = document.getElementsByClassName('tran_str')
    for (let i = 0; i < da.length; i++) {
        //console.log(da[i].innerHTML)
        da[i].innerHTML = transToMsg(da[i].innerHTML)
    }


</script>
</body>
</html>