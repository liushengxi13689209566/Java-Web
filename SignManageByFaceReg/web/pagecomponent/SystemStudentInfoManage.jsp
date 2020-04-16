<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/16
  Time: 11:58 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统学生信息管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .table > thead > tr > th {
            text-align: center;
        }
    </style>
</head>
<body>

<script>

    var search_keyWord = "";

    //立即函数
    $(function () {
        studentsListInit();
        searchAction();
    })

    // 查询参数
    function queryParams() {
        var temp = {
            keyWord: search_keyWord
        }
        return temp;
    }

    // 表格刷新
    function studentsListTableRefresh() {
        $('#studentsList').bootstrapTable('refresh', {
            query: {}
        });
    }

    // 搜索动作
    function searchAction() {
        $('#search_button').click(function () {
            search_keyWord = $('#search_input').val();

            console.log("search_keyWrod == " + search_keyWord)

            // $('#studentsList').bootstrapTable('refresh', {
            //     query: {keyWord: search_keyWord}
            // });
            studentsListTableRefresh()
        })
    }

    // studentsList表格
    function studentsListInit() {
        console.log("初始化了！！！！")
        $('#studentsList').bootstrapTable(
            {
                url: 'student/getSystemAllStudents',
                method: 'GET',
                queryParams: queryParams,
                dataType: 'json',
                // 分页选项
                // pagination: true,
                // pageNumber: 1,
                // pageSize: 5,
                // pageList: [5, 10, 25, 50, 100],
                //单选选项
                // clickToSelect: true,
                sortClass: "table-active",
                sortable: true,
                sortName: 'id',
                sortOrder: 'asc',
                responseHandler: function (res) {
                    console.log(res)
                    $.each(res, function (row) {
                        $.inArray(row)
                    })
                    return res;
                },
                columns: [
                    {
                        field: 'id',
                        title: '学号',
                        sortable: true,
                        // search: true,
                        // strictSearch: true
                    },
                    {
                        field: 'name',
                        title: '姓名'
                    },
                    {
                        field: 'major_name',
                        title: '专业'
                    },
                    {
                        field: 'class_name',
                        title: '班级'
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
        <li>系统学生信息管理</li>
    </ol>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-9 col-sm-9">
                <div>
                    <div class="col-md-3 col-sm-4">
                        <input id="search_input" type="text" class="form-control"
                               placeholder="学号">
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
                    <span class="glyphicon glyphicon-plus"></span> <span>添加学生</span>
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
                <table id="studentsList" class="table table-striped">
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
