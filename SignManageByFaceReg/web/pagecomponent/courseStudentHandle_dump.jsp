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

    var selectID;

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
                // sidePagination: "server",
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
                        // visible: false
                    },
                    {
                        field: 'course_end',
                        title: '结束时间',
                        // visible: false
                    },
                    {
                        field: 'operation',
                        title: '操作',
                        formatter: function (value, row, index) {
                            return [
                                '<a href="javascript:void(0)" class="btn btn-info btn-sm edit">查看课程学生</a>'].join('')
                        },
                    }],
                events: {
                    'click .edit': function (e, value, row, index) {
                        console.log("进入查看课程学生！！！")
                        selectID = row.id;
                        $('#oneCourseAllStudentList').bootstrapTable('refresh', {
                            url: 'course/getOneCourseAllStudent'
                        })
                    }
                }
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


    //一门课学生列表表格
    // $('#oneCourseAllStudentList').bootstrapTable(
    //     {
    //         url: 'course/getOneCourseAllStudent',
    //         method: 'GET',
    //         queryParams: getCourseID,
    //         // sidePagination: "server",
    //         dataType: 'json',
    //         // 分页选项
    //         // pagination: true,
    //         // pageNumber: 1,
    //         // pageSize: 5,
    //         // pageList: [5, 10, 25, 50, 100],
    //         //单选选项
    //         // clickToSelect: true
    //         responseHandler: function (res) {
    //             $.each(res.rows, function (row) {
    //                 $.inArray(row)
    //             })
    //             return res;
    //         },
    //         columns: [
    //             {
    //                 field: 'id',
    //                 title: '学号'
    //                 //sortable: true
    //             },
    //             {
    //                 field: 'name',
    //                 title: '姓名'
    //             },
    //             {
    //                 field: 'major_times',
    //                 title: '专业'
    //             },
    //             {
    //                 field: 'class_name',
    //                 title: '班级'
    //             },
    //             {
    //                 field: 'operation',
    //                 title: '操作',
    //                 formatter: function (value, row, index) {
    //                     return [
    //                         '<a href="javascript:void(0)" class="btn btn-info btn-sm edit" >查看课程学生</a>'
    //                     ].join('')
    //                 },
    //             }],
    //         events: {
    //             'click .edit': function (e, value, row, index) {
    //                 selectID = row.id;
    //                 //
    //                 // getOneCourseAllStudent(row);
    //             },
    //             // 操作列中编辑按钮的动作
    //             // 'click .edit': function (e, value, row, index) {
    //             //     selectID = row.id;
    //             //     rowEditOperation(row);
    //             // },
    //             // 'click .delete': function (e, value, row, index) {
    //             //     selectID = row.id;
    //             //     $('#deleteWarning_modal').modal(
    //             //         'show');
    //             // }
    //         }
    //     });


    // 表格刷新
    function tableRefresh() {
        $('#courseList').bootstrapTable('refresh', {
            query: {}
        });
    }

    function getCourseID(params) {
        return selectID;
    }

</script>


<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>课程学生管理</li>
    </ol>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-1 col-sm-2">
                <div class="btn-group">
                    <button class="btn btn-default dropdown-toggle"
                            data-toggle="dropdown">
                        <span id="search_type">查询方式</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0)" class="dropOption">客户ID</a></li>
                        <li><a href="javascript:void(0)" class="dropOption">客户名称</a></li>
                        <li><a href="javascript:void(0)" class="dropOption">所有</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-9 col-sm-9">
                <div>
                    <div class="col-md-3 col-sm-4">
                        <input id="search_input" type="text" class="form-control"
                               placeholder="客户ID">
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <button id="search_button" class="btn btn-success">
                            <span class="glyphicon glyphicon-search"></span> <span>查询</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 25px">
            <div class="col-md-5">
                <button class="btn btn-sm btn-default" id="add_customer">
                    <span class="glyphicon glyphicon-plus"></span> <span>添加客户</span>
                </button>
                <button class="btn btn-sm btn-default" id="import_customer">
                    <span class="glyphicon glyphicon-import"></span> <span>导入</span>
                </button>
                <button class="btn btn-sm btn-default" id="export_customer">
                    <span class="glyphicon glyphicon-export"></span> <span>导出</span>
                </button>
            </div>
            <div class="col-md-5"></div>
        </div>

        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="courseList" class="table table-striped"></table>
            </div>
        </div>
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

    <%--function click_item(e) {--%>
    <%--    var url = $(e).attr('name');--%>
    <%--    console.log(url)--%>
    <%--    $('#panel').load(url);--%>
    <%--}--%>

    <%--$.ajax({--%>
    <%--    type: 'GET',--%>
    <%--    url: 'course/getMyTeaCourse',--%>
    <%--    dataType: 'json',--%>
    <%--    contentType: 'application/json',--%>
    <%--    data: {--%>
    <%--        // searchType: 'searchAll',--%>
    <%--        // keyWord: '',--%>
    <%--        // offset: -1,--%>
    <%--        // limit: -1--%>
    <%--    },--%>
    <%--    success: function (response) {--%>
    <%--        console.log(response)--%>
    <%--        $.each(response.rows, function (index, elem) {--%>
    <%--            console.log(elem)--%>
    <%--            $('#course_table').append(--%>
    <%--                "<tr><td>" + elem.course_id--%>
    <%--                + "</td><td>" + elem.course_name--%>
    <%--                + "</td><td>" + elem.course_times--%>
    <%--                + "</td><td>" + elem.course_credit--%>
    <%--                + "</td><td>" + timestampToTime(elem.course_start)--%>
    <%--                + "</td><td>" + timestampToTime(elem.course_end)--%>
    <%--                + "</td><td><a class='menu_item' name='${pageContext.request.contextPath}/course/getOneCourseAllStudent?course_id=" + elem.course_id + "' onclick='click_item(this)'>查看课程学生</a></td></tr>"--%>
    <%--            );--%>
    <%--        });--%>
    <%--    },--%>
    <%--    error: function (response) {--%>
    <%--        // $('#course_table').append("<option value='-1'>加载失败</option>");--%>
    <%--        $('#course_table').append("<tr><td>" + "加载失败" + "</td></tr>");--%>
    <%--    }--%>
    <%--})--%>
</script>
</body>
</html>



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
    $(function () {
        courseListInit();
    })


    // 表格初始化
    function courseListInit() {
        console.log("初始化了！！！！")
        $('#courseList')
            .bootstrapTable(
                {
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
                            // visible: false
                        },
                        {
                            field: 'course_end',
                            title: '结束时间',
                            // visible: false
                        },
                        {
                            field: 'operation',
                            title: '操作',
                            formatter: function (value, row, index) {
                                var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                                var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                                var fun = '';
                                return s + ' ' + d;
                            },
                        }],
                    responseHandler: function (res) {
                        $.each(res.rows, function (i, row) {
                            $.inArray(row)
                        })
                        return res;
                    },
                    // events: {
                    //     // 操作列中编辑按钮的动作
                    //     'click .edit': function (e, value,
                    //                              row, index) {
                    //         selectID = row.id;
                    //         rowEditOperation(row);
                    //     },
                    //     'click .delete': function (e,
                    //                                value, row, index) {
                    //         selectID = row.id;
                    //         $('#deleteWarning_modal').modal(
                    //             'show');
                    //     }
                    // }

                    url: 'course/getMyTeaCourse',
                    method: 'GET',
                    // queryParams: '',
                    // sidePagination: "server",
                    dataType: 'json',
                    // pagination: true,
                    // pageNumber: 1,
                    // pageSize: 5,
                    // pageList: [5, 10, 25, 50, 100],
                    // clickToSelect: true
                });
    }

    // 表格刷新
    function tableRefresh() {
        $('#courseList').bootstrapTable('refresh', {
            query: {}
        });
    }

</script>


<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>课程学生管理</li>
    </ol>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-1 col-sm-2">
                <div class="btn-group">
                    <button class="btn btn-default dropdown-toggle"
                            data-toggle="dropdown">
                        <span id="search_type">查询方式</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0)" class="dropOption">客户ID</a></li>
                        <li><a href="javascript:void(0)" class="dropOption">客户名称</a></li>
                        <li><a href="javascript:void(0)" class="dropOption">所有</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-9 col-sm-9">
                <div>
                    <div class="col-md-3 col-sm-4">
                        <input id="search_input" type="text" class="form-control"
                               placeholder="客户ID">
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <button id="search_button" class="btn btn-success">
                            <span class="glyphicon glyphicon-search"></span> <span>查询</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 25px">
            <div class="col-md-5">
                <button class="btn btn-sm btn-default" id="add_customer">
                    <span class="glyphicon glyphicon-plus"></span> <span>添加客户</span>
                </button>
                <button class="btn btn-sm btn-default" id="import_customer">
                    <span class="glyphicon glyphicon-import"></span> <span>导入</span>
                </button>
                <button class="btn btn-sm btn-default" id="export_customer">
                    <span class="glyphicon glyphicon-export"></span> <span>导出</span>
                </button>
            </div>
            <div class="col-md-5"></div>
        </div>

        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="courseList" class="table table-striped"></table>
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

<script>
    // 表格初始化
    function courseListInit() {
        console.log("初始化了！！！！")
        $('#courseList')
            .bootstrapTable(
                {
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
                            // visible: false
                        },
                        {
                            field: 'course_end',
                            title: '结束时间',
                            // visible: false
                        },
                        {
                            field: 'operation',
                            title: '操作',
                            formatter: function (value, row, index) {
                                var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                                var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                                var fun = '';
                                return s + ' ' + d;
                            },
                        }],
                    responseHandler: function (res) {
                        $.each(res.rows, function (i, row) {
                            $.inArray(row)
                        })
                        return res;
                    },
                    // events: {
                    //     // 操作列中编辑按钮的动作
                    //     'click .edit': function (e, value,
                    //                              row, index) {
                    //         selectID = row.id;
                    //         rowEditOperation(row);
                    //     },
                    //     'click .delete': function (e,
                    //                                value, row, index) {
                    //         selectID = row.id;
                    //         $('#deleteWarning_modal').modal(
                    //             'show');
                    //     }
                    // }

                    url: 'course/getMyTeaCourse',
                    method: 'GET',
                    queryParams: queryParams,
                    sidePagination: "server",
                    dataType: 'json',
                    pagination: true,
                    // pageNumber: 1,
                    // pageSize: 5,
                    // pageList: [5, 10, 25, 50, 100],
                    clickToSelect: true
                });
    }

    // 表格刷新
    function tableRefresh() {
        $('#courseList').bootstrapTable('refresh', {
            query: {}
        });
    }

</script>


<script>
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        return Y + M + D;
    }

    <%--function click_item(e) {--%>
    <%--    var url = $(e).attr('name');--%>
    <%--    console.log(url)--%>
    <%--    $('#panel').load(url);--%>
    <%--}--%>

    <%--$.ajax({--%>
    <%--    type: 'GET',--%>
    <%--    url: 'course/getMyTeaCourse',--%>
    <%--    dataType: 'json',--%>
    <%--    contentType: 'application/json',--%>
    <%--    data: {--%>
    <%--        // searchType: 'searchAll',--%>
    <%--        // keyWord: '',--%>
    <%--        // offset: -1,--%>
    <%--        // limit: -1--%>
    <%--    },--%>
    <%--    success: function (response) {--%>
    <%--        console.log(response)--%>
    <%--        $.each(response.rows, function (index, elem) {--%>
    <%--            console.log(elem)--%>
    <%--            $('#course_table').append(--%>
    <%--                "<tr><td>" + elem.course_id--%>
    <%--                + "</td><td>" + elem.course_name--%>
    <%--                + "</td><td>" + elem.course_times--%>
    <%--                + "</td><td>" + elem.course_credit--%>
    <%--                + "</td><td>" + timestampToTime(elem.course_start)--%>
    <%--                + "</td><td>" + timestampToTime(elem.course_end)--%>
    <%--                + "</td><td><a class='menu_item' name='${pageContext.request.contextPath}/course/getOneCourseAllStudent?course_id=" + elem.course_id + "' onclick='click_item(this)'>查看课程学生</a></td></tr>"--%>
    <%--            );--%>
    <%--        });--%>
    <%--    },--%>
    <%--    error: function (response) {--%>
    <%--        // $('#course_table').append("<option value='-1'>加载失败</option>");--%>
    <%--        $('#course_table').append("<tr><td>" + "加载失败" + "</td></tr>");--%>
    <%--    }--%>
    <%--})--%>
</script>
</body>
</html>

