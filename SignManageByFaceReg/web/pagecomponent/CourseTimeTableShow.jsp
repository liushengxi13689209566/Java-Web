<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/30
  Time: 6:38 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body><%--获取参数--%>
<%
    String course_id = request.getParameter("course_id");
    System.out.println(course_id);

    String course_name = request.getParameter("course_name");
    System.out.println(course_name);
%>

<script>
    //立即函数
    $(function () {
        CourseTimeTableListInit();
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

    function CourseTimeTableListInit() {
        var count = 1;
        $('#CourseTimeTableList').bootstrapTable(
            {
                url: 'course/getAllCourseTime',
                method: 'GET',
                queryParams: getCourseID,
                //必须有，不然就会渲染不出来
                // sidePagination: "server",
                dataType: 'json',
                // 分页选项
                // pagination: true,
                // pageNumber: 1,
                // pageSize: 5,
                // pageList: [5, 10, 25, 50, 100],
                //单选选项
                // clickToSelect: true
                //搜索功能
                search: true,
                //怎么不显示呐？
                showSearchClearButton: true,
                responseHandler: function (res) {
                    return res;
                },
                columns: [
                    {
                        field: 'id',
                        title: '第几节课',
                        formatter: function (value) {
                            return count++;
                        }
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
                    }],
            })
    }

</script>

<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><%=course_name%> 课程时间表</li>
    </ol>
    <div class="panel-body">
        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="CourseTimeTableList" class="table table-striped"></table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
