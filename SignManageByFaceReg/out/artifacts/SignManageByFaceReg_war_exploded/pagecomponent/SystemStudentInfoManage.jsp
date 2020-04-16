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

    //立即函数
    $(function () {
        studentsListInit();
        addstudentAction();
    })

    // studentsList表格
    function studentsListInit() {
        console.log("初始化了！！！！")
        $('#studentsList').bootstrapTable(
            {
                url: 'student/getSystemAllStudents',
                method: 'GET',
                // queryParams: queryParams,
                dataType: 'json',
                // 分页选项
                // pagination: true,
                // pageNumber: 1,
                // pageSize: 5,
                // pageList: [5, 10, 25, 50, 100],
                //单选选项
                // clickToSelect: true,
                //排序选项
                sortClass: "table-active",
                sortable: true,
                sortName: 'id',
                sortOrder: 'asc',
                //搜索功能
                search: true,
                //怎么不显示呐？
                showSearchClearButton: true,
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

    // 添加学生信息
    function addstudentAction() {
        $('#add_one_student').click(function () {
            $('#add_modal').modal("show");
        });

        $('#add_modal_submit').click(function () {
            var data = {
                name: $('#student_name').val(),
                personInCharge: $('#student_person').val(),
                tel: $('#student_tel').val(),
                email: $('#student_email').val(),
                address: $('#student_address').val()
            }
            // ajax
            $.ajax({
                type: "POST",
                url: "studentManage/addstudent",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (response) {
                    $('#add_modal').modal("hide");
                    var msg;
                    var type;
                    if (response.result == "success") {
                        type = "success";
                        msg = "客户添加成功";
                    } else if (response.result == "error") {
                        type = "error";
                        msg = "客户添加失败";
                    }
                    infoModal(type, msg);
                    tableRefresh();

                    // reset
                    $('#student_name').val("");
                    $('#student_person').val("");
                    $('#student_tel').val("");
                    $('#student_email').val("");
                    $('#student_address').val("");
                    $('#student_form').bootstrapValidator("resetForm", true);
                },
                error: function (response) {
                }
            })
        })
    }


</script>

<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>系统学生信息管理</li>
    </ol>
    <div class="panel-body">
        <%-- 添加一个学生与批量 导入，导出功能--%>
        <div class="row">
            <div class="col-md-5">
                <button class="btn btn-sm btn-info" id="add_one_student">
                    <span class="glyphicon glyphicon-plus"></span> <span>新增一名学生</span>
                </button>
                <button class="btn btn-sm btn-primary" id="import_students">
                    <span class="glyphicon glyphicon-import"></span> <span>整体学生信息导入</span>
                </button>
                <button class="btn btn-sm btn-primary" id="export_students">
                    <span class="glyphicon glyphicon-import"></span> <span>整体学生信息导出</span>
                </button>
            </div>
            <div class="col-md-5"></div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <table id="studentsList" class="table table-striped">
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 添加学生信息模态框 -->
<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">添加学生信息</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">

                        <form class="form-horizontal" role="form" id="student_form"
                              style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_name"
                                           name="student_name" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>专业：</span>
                                    <%--                                    //从数据库中获取--%>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="major_selector" class="form-control">
                                        <option value="">请选择对应课程:</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>班级：</span>
                                    <%--                                    //从数据库中获取--%>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_tel"
                                           name="student_tel" placeholder="班级">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>人脸照片：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_email"
                                           name="student_email" placeholder="电子邮件">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-1 col-sm-1"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>取消</span>
                </button>
                <button class="btn btn-success" type="button" id="add_modal_submit">
                    <span>提交</span>
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
