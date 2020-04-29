<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/9
  Time: 10:53 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理</title>
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
                url: 'course/getMyTeaCourse',
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
                                'class="btn btn-info btn-sm edit" ' +
                                'name="pagecomponent/CourseStudentInfoShow.jsp">查看课程学生</a>'
                                // '<a href="javascript:void(0)" class="edit">查看课程学生</a>'
                            ].join('')
                        },
                        events: {
                            'click .edit': function (e, value, row, index) {
                                console.log("进入查看课程学生！！！")
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

                                // $('#oneCourseAllStudentList').bootstrapTable('refresh', {
                                //     url: 'course/getOneCourseAllStudent'
                                // })
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
        <li>课程学生管理</li>
    </ol>
    <div class="panel-body">
        <%--        <div class="row">--%>
        <%--            <div class="col-md-1 col-sm-2">--%>
        <%--                <div class="btn-group">--%>
        <%--                    <button class="btn btn-default dropdown-toggle"--%>
        <%--                            data-toggle="dropdown">--%>
        <%--                        <span id="search_type">查询方式</span> <span class="caret"></span>--%>
        <%--                    </button>--%>
        <%--                    <ul class="dropdown-menu" role="menu">--%>
        <%--                        <li><a href="javascript:void(0)" class="dropOption">客户ID</a></li>--%>
        <%--                        <li><a href="javascript:void(0)" class="dropOption">客户名称</a></li>--%>
        <%--                        <li><a href="javascript:void(0)" class="dropOption">所有</a></li>--%>
        <%--                    </ul>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="col-md-9 col-sm-9">--%>
        <%--                <div>--%>
        <%--                    <div class="col-md-3 col-sm-4">--%>
        <%--                        <input id="search_input" type="text" class="form-control"--%>
        <%--                               placeholder="客户ID">--%>
        <%--                    </div>--%>
        <%--                    <div class="col-md-2 col-sm-2">--%>
        <%--                        <button id="search_button" class="btn btn-success">--%>
        <%--                            <span class="glyphicon glyphicon-search"></span> <span>查询</span>--%>
        <%--                        </button>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--        <div class="row" style="margin-top: 25px">--%>
        <%--            <div class="col-md-5">--%>
        <%--                <button class="btn btn-sm btn-default" id="add_customer">--%>
        <%--                    <span class="glyphicon glyphicon-plus"></span> <span>添加客户</span>--%>
        <%--                </button>--%>
        <%--                <button class="btn btn-sm btn-default" id="import_customer">--%>
        <%--                    <span class="glyphicon glyphicon-import"></span> <span>导入</span>--%>
        <%--                </button>--%>
        <%--                <button class="btn btn-sm btn-default" id="export_customer">--%>
        <%--                    <span class="glyphicon glyphicon-export"></span> <span>导出</span>--%>
        <%--                </button>--%>
        <%--            </div>--%>
        <%--            <div class="col-md-5"></div>--%>
        <%--        </div>--%>

        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="courseList" class="table table-striped"></table>
                <%--                <table id="oneCourseAllStudentList" class="table table-striped">--%>
                <%--                    <div class="row">--%>
                <%--                        <div class="col-md-4 column">--%>
                <%--                            <a class="btn btn-primary"--%>
                <%--                               href="${pageContext.request.contextPath}/book/toAddBook">新增一名学生</a>--%>
                <%--                        </div>--%>

                <%--                        <div class="col-md-4 column">--%>
                <%--                            <a class="btn btn-primary"--%>
                <%--                               href="${pageContext.request.contextPath}/book/toAddBook">新增一名学生</a>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>