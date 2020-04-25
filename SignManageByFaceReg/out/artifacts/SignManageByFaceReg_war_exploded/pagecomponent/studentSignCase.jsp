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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .table > thead > tr > th {
            text-align: center;
        }
    </style>
</head>

<body>


<script>

    //立即函数
    $(function () {
        courseListInit();
    })

    // courseList表格
    function courseListInit() {
        console.log("初始化了！！！！")
        $('#courseList').bootstrapTable(
            {
                url: 'course/getMyCourse',
                method: 'GET',
                // queryParams: queryParamsFun,
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
                    console.log(res)
                    $.each(res.rows, function (i, row) {
                        console.log(row)
                        $.inArray(row)
                    })
                    return res;
                },
                columns: [
                    {
                        field: 'course_id',
                        title: '序号'
                        //sortable: true
                    },
                    {
                        field: 'course_name',
                        title: '课程名称'
                    },
                    {
                        field: 'course_times',
                        title: '课时'
                    },
                    {
                        field: 'course_credit',
                        title: '学分'
                    },
                    {
                        field: 'course_start',
                        title: '起始时间',
                        formatter: timestampToTime
                    },
                    {
                        field: 'course_end',
                        title: '结束时间',
                        formatter: timestampToTime
                    },
                    {
                        field: 'operation',
                        title: '操作',
                        formatter: function (value, row, index) {
                            // var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                            // return s;
                            return [
                                '<a href="javascript:void(0)" ' +
                                'class="btn btn-info btn-sm look" ' +
                                'name="pagecomponent/signCaseShow.jsp">查看考勤</a>'
                                // '<a href="javascript:void(0)" class="edit">查看课程学生</a>'
                            ].join('')
                        },
                        events: {
                            'click .look': function (e, value, row, index) {
                                console.log("进入查看考勤！！！")
                                // $('#courseList').bootstrapTable('destroy')
                                // selectID = row.course_id;
                                console.log("row.id == " + row.course_id)

                                // ajax 请求设置服务端 course_id。实在是没办法了
                                // setSessionCourseID(row.course_id)

                                // var url = "course/getOneCourseAllStudent?course_id=" + selectID;

                                var url = $(this).attr("name");
                                console.log(url)
                                //重新绘制页面
                                $('#panel').load(url, {
                                    'course_id': row.course_id,
                                    'course_name': row.course_name
                                });
                            }
                        }
                    }],
                // 操作列中编辑按钮的动作
                // 'click .edit': function (e, value, row, index) {
                //     selectID = row.id;
                //     rowEditOperation(row);
                // },
                // 'click .delete': function (e, value, row, index) {
                //     selectID = row.id;
                //     $('#deleteWarning_modal').modal(
                //         'show');
                // }
            });
    }

    // 表格刷新
    function tableRefresh() {
        $('#courseList').bootstrapTable('refresh', {
            query: {}
        });
    }

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        return Y + M + D;
    }
</script>


<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>实验课考勤信息查看</li>
    </ol>
    <div class="panel-body">
        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="courseList" class="table table-striped"></table>
                </table>
            </div>
        </div>
    </div>
</div>


<%--<div class="row clearfix">--%>
<%--    <div class="col-md-12 column">--%>
<%--        <table class="table table-hover table-striped">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>序号</th>--%>
<%--                <th>课程名称</th>--%>
<%--                <th>课时</th>--%>
<%--                <th>学分</th>--%>
<%--                <th>起始时间</th>--%>
<%--                <th>结束时间</th>--%>
<%--                <th>操作</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody id="course_table">--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script>--%>
<%--    function timestampToTime(timestamp) {--%>
<%--        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000--%>
<%--        var Y = date.getFullYear() + '-';--%>
<%--        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';--%>
<%--        var D = date.getDate() + ' ';--%>
<%--        return Y + M + D;--%>
<%--    }--%>

<%--    function click_item(e) {--%>
<%--        var url = $(e).attr('name');--%>
<%--        console.log(url)--%>
<%--        $('#panel').load(url);--%>
<%--    }--%>
<%--</script>--%>
<%--<script>--%>
<%--    $.ajax({--%>
<%--        type: 'GET',--%>
<%--        url: 'course/getMyCourse',--%>
<%--        dataType: 'json',--%>
<%--        contentType: 'application/json',--%>
<%--        data: {--%>
<%--            // searchType: 'searchAll',--%>
<%--            // keyWord: '',--%>
<%--            // offset: -1,--%>
<%--            // limit: -1--%>
<%--        },--%>
<%--        success: function (response) {--%>
<%--            console.log(response)--%>
<%--            $.each(response.rows, function (index, elem) {--%>
<%--                console.log(elem)--%>
<%--                $('#course_table').append(--%>
<%--                    "<tr><td>" + elem.course_id--%>
<%--                    + "</td><td>" + elem.course_name--%>
<%--                    + "</td><td>" + elem.course_times--%>
<%--                    + "</td><td>" + elem.course_credit--%>
<%--                    + "</td><td>" + timestampToTime(elem.course_start)--%>
<%--                    + "</td><td>" + timestampToTime(elem.course_end)--%>
<%--                    + "</td><td><a class='menu_item' name='${pageContext.request.contextPath}/SignCase/OneCourseSignCase/getOneStuSignCase?course_id=" + elem.course_id + "' onclick='click_item(this)'>查看考勤</a></td></tr>"--%>
<%--                );--%>
<%--            });--%>
<%--        },--%>
<%--        error: function (response) {--%>
<%--            // $('#course_table').append("<option value='-1'>加载失败</option>");--%>
<%--            $('#course_table').append("<tr><td>" + "加载失败" + "</td></tr>");--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>
</body>
</html>