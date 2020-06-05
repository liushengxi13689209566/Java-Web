<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/16
  Time: 11:58 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统学生信息管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>


<script>

    //立即函数
    $(function () {

        studentsListInit();
        //获取所有专业
        majorSelectInit();
        //获取所有班级
        classSelectorInit();
        bootstrapValidatorInit();

        addstudentAction();
        importAllStudentsInfo();
        exportCustomerAction();

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
                        field: 'sex',
                        title: '性别'
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
                        field: 'identity_card',
                        title: '身份证号',
                        formatter: function (value, row, index) {
                            var len = value.length;
                            var count = len - 9;// 前 4 后5
                            var str = "";
                            for (i = 0; i < count; i++) {
                                str += '*';
                            }
                            console.log("str == " + str)
                            return value.substring(0, 4) + str + value.substring(len - 5);
                            // 620123199602285119
                        }
                    },
                    {
                        field: 'operation',
                        title: '操作',
                        formatter: function (value, row, index) {
                            var s = '<button class="btn btn-info btn-sm edit"><span>更改</span></button>';
                            var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            var fun = '';
                            return s + ' ' + d;
                        },
                        events: {
                            // 操作列中编辑按钮的动作
                            'click .edit': function (e, value, row, index) {
                                rowEditOperation(row);
                            },
                            'click .delete': function (e, value, row, index) {
                                // selectID = row.id;
                                if (confirm('确定删除 ? ') == false)
                                    return false;

                                console.log("确认删除了！！！")
                                deleteOneStudent(row.id)
                            }
                        }
                    }],
            })
    }

    function deleteOneStudent(id) {
        var data = {
            "id": id
        }
        $.ajax({
            type: "GET",
            url: "student/deleteOneStudent",
            dataType: "json",
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response)
                if (response.status_code == 0) {
                    alert("删除成功！")
                    //减少后端请求，前端页面刷新即可
                    $('#studentsList').bootstrapTable('remove', {
                        field: 'id',
                        values: [id]
                    });
                } else {
                    alert(response.msg);
                }
            }, error: function (response) {
            }
        })
    }

    //获取所有专业
    function majorSelectInit() {
        $.ajax({
            type: 'GET',
            url: 'major/getAllMajor',
            dataType: 'json',
            contentType: 'application/json',
            // data: {
            //     searchType: 'searchAll',
            //     keyWord: '',¬
            //     offset: -1,
            //     limit: -1
            // },
            success: function (response) {
                $.each(response.rows, function (index, elem) {
                    // console.log("elem==")
                    // console.log(elem)
                    $('#major_id,#student_major_edit').append("<option value='" + elem.major_id + "'>" + elem.major_name + "</option>");
                });
            },
            error: function (response) {
                $('#major_id,#student_major_edit').append("<option value='-1'>加载失败</option>");
            }

        })
    }

    //获取所有班级
    function classSelectorInit() {
        $.ajax({
            type: 'GET',
            url: 'class/getAllClass',
            dataType: 'json',
            contentType: 'application/json',
            // data: {
            //     searchType: 'searchAll',
            //     keyWord: '',
            //     offset: -1,
            //     limit: -1
            // },
            success: function (response) {
                $.each(response.rows, function (index, elem) {
                    $('#class_id,#student_class_edit').append("<option value='" + elem.class_id + "'>" + elem.class_name + "</option>");
                });
            },
            error: function (response) {
                $('#class_id,#student_class_edit').append("<option value='-1'>加载失败</option>");
            }

        })
    }

    // 表格刷新
    function studentsListTableRefresh() {
        $('#studentsList').bootstrapTable('refresh');
    }

    // 添加学生信息
    function addstudentAction() {
        $('#add_one_student').click(function () {
            $('#add_modal').modal("show");
        });
        $('#add_modal_submit').click(function () {
            console.log("点击了提交按钮了")
            //构建 FormData 对象
            var formData = new FormData(document.getElementById("student_form"));
            console.log(formData)
            // ajax
            $.ajax({
                url: "student/addOneStudent",
                type: "post",
                data: formData,
                async: true,
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function (response) {
                    $('#add_modal').modal("hide");
                    if (response.status_code == 0) {
                        alert("success! 学生添加成功");
                    } else {
                        alert("sorry! 学生添加失败");
                    }
                    studentsListTableRefresh();
                    // reset
                    $('#student_name').val("");
                    $('#student_sex').val("");
                    $('#student_id_card').val("");
                    $('#major_id').val("");
                    $('#class_id').val("");
                    $('#student_picture').val("");
                    $('#student_form').bootstrapValidator("resetForm", true);
                },
                error: function (response) {
                }
            })
        })
    }

    //上一步与下一步的设定
    var import_step = 1;
    var import_start = 1;
    var import_end = 3;

    //整体导入学生信息
    function importAllStudentsInfo() {
        $('#import_students').click(function () {
            $('#import_modal').modal("show");
        });

        $('#previous').click(function () {
            if (import_step > import_start) {
                var preID = "step" + (import_step - 1)
                var nowID = "step" + import_step;

                $('#' + nowID).addClass("hide");
                $('#' + preID).removeClass("hide");
                import_step--;
            }
        })

        $('#next').click(function () {
            if (import_step < import_end) {
                var nowID = "step" + import_step;
                var nextID = "step" + (import_step + 1);

                $('#' + nowID).addClass("hide");
                $('#' + nextID).removeClass("hide");
                import_step++;
            }
        })

        $('#file').on("change", function () {
            $('#previous').addClass("hide");
            $('#next').addClass("hide");
            $('#submit').removeClass("hide");
        })

        $('#submit').click(function () {
            var nowID = "step" + import_end;
            $('#' + nowID).addClass("hide");
            $('#uploading').removeClass("hide");

            // next
            $('#confirm').removeClass("hide");
            $('#submit').addClass("hide");

            // var fileObj = document.getElementById("import_file_upload"); // js 获取文件对象
            // console.log(fileObj)

            //构建 FormData 对象
            var formData = new FormData(document.getElementById("import_file_upload"));

            // formData.append("file", fileObj);
            <%--formData.append("course_id", <%=course_id%>)--%>

            console.log(formData)

            $.ajax({
                url: "student/importAllStudentsInfo",
                type: "post",
                data: formData,
                async: true,
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function (data, status) {
                    var total = 0;
                    var available = 0;
                    var msg1 = "学生信息导入成功";
                    var msg2 = "学生信息导入失败";
                    var info;

                    $('#import_progress_bar').addClass("hide");
                    if (data.status_code == 0) {
                        total = data.total;
                        available = data.available;
                        info = msg1;
                        $('#import_success').removeClass('hide');
                    } else {
                        info = msg2
                        $('#import_error').removeClass('hide');
                    }
                    info = info + ",总条数：" + total + ",有效条数:" + available;
                    $('#import_result').removeClass('hide');
                    $('#import_info').text(info);
                    $('#confirm').removeClass('disabled');
                }, error: function (data, status) {
                }
            })
        })

        $('#confirm').click(function () {
            // modal dissmiss
            importModalReset();
            oneCourseAllStudentListTableRefresh()
        })
    }

    // 导出学生信息
    function exportCustomerAction() {
        $('#export_students').click(function () {
            $('#export_modal').modal("show");
        })

        $('#export_students_download').click(function () {
            // var data = {
            //     searchType: search_type_customer,
            //     keyWord: search_keyWord
            // }
            // var url = "student/exportStudents?" + $.param(data)
            var url = "student/exportStudents";
            window.open(url, '_blank');
            $('#export_modal').modal("hide");
        })
    }

    // 行编辑操作
    function rowEditOperation(row) {
        $('#edit_modal').modal("show");
        // load info
        $('#student_form_edit').bootstrapValidator("resetForm", true);
        // $('#student_id_edit').val(row.id);
        $('#student_name_edit').val(row.name);
        $('#student_sex_edit').val(row.sex);
        $('#student_id_card_edit').val(row.identity_card);

        console.log("major_name = " + row.major_name)
        console.log("class_name = " + row.class_name)

        $('#student_major_edit').val(row.major_name);
        $('#student_class_edit').val(row.class_name);
        $('#student_picture_edit').val();

        $('#edit_modal_submit').click(function () {
            console.log("点击了确认提交按钮！！")

            $('#student_form_edit').data('bootstrapValidator')
                .validate();

            console.log(" 判断是否已验证通过 " + $('#student_form_edit').data('bootstrapValidator').isValid())

            if (!$('#student_form_edit').data('bootstrapValidator')
                .isValid()) {
                return;
            }

            var formData = new FormData(document.getElementById("student_form_edit"));
            formData.append("id", row.id) //加入学生的 id
            console.log(formData)

            // ajax
            $.ajax({
                url: "student/updateOneStudent",
                type: "post",
                data: formData,
                async: true,
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function (response) {
                    $('#edit_modal').modal("hide");
                    alert(response.msg)
                    studentsListTableRefresh();
                    // reset
                    $('#student_name_edit').val("");
                    $('#student_sex_edit').val("");
                    $('#student_id_card_edit').val("");
                    $('#student_major_edit').val("");
                    $('#student_class_edit').val("");
                    $('#student_picture_edit').val("");
                    $('#student_form_edit').bootstrapValidator("resetForm", true);
                },
                error: function (response) {
                }
            })
        })
    }

    // 课程信息数据校验
    function bootstrapValidatorInit() {
        $("#student_form,#student_form_edit").bootstrapValidator({
            message: 'This is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: [':disabled'],
            fields: {
                student_name: {
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        }
                    }
                },
                student_sex: {
                    validators: {
                        notEmpty: {
                            message: '性别不能为空'
                        },
                    }
                },
                major_id: {
                    validators: {
                        notEmpty: {
                            message: '专业不能为空'
                        },
                    }
                },
                class_id: {
                    validators: {
                        notEmpty: {
                            message: '班级不能为空'
                        },
                    }
                },
                student_id_card: {
                    validators: {
                        notEmpty: {
                            message: '身份证号不能为空'
                        },
                        digits: {
                            message: '身份证号只能是数字'
                        }
                    }
                }
            }
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
                <h4 class="modal-title">添加学生信息</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <%--  所有数据的校验由前端之后来做--%>
                        <form enctype="multipart/form-data" id="student_form"
                              class="form-horizontal" style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_name"
                                           name="student_name" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>性别：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="student_sex" name="student_sex" class="form-control">
                                        <option value="">请选择性别:</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>身份证号：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_id_card"
                                           name="student_id_card" placeholder="身份证号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>专业：</span>
                                    <%--                                    //从数据库中获取--%>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="major_id" name="major_id" class="form-control">
                                        <option value="">请选择对应专业:</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>班级：</span>
                                    <%--                                    //从数据库中获取--%>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="class_id" name="class_id" class="form-control">
                                        <option value="">请选择对应班级:</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>人脸照片：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <%--    <span class="btn btn-info btn-file"> <span> <span
                                                class="glyphicon glyphicon-upload"></span> <span>上传照片</span>
                                        </span>--%>
                                    <input type="file" class="form-control" id="student_picture"
                                           name="student_picture" placeholder="人脸照片">
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

<!-- 导入信息模态框 -->
<div class="modal fade" id="import_modal" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">导入学生信息</h4>
            </div>
            <div class="modal-body">
                <div id="step1">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-10 col-sm-10">
                            <div>
                                <h4>点击下面的下载按钮，下载学生信息电子表格</h4>
                            </div>
                            <div style="margin-top: 30px; margin-buttom: 15px">
                                <a class="btn btn-info"
                                   href="download/studentsInfoList.xlsx"
                                   target="_blank"> <span class="glyphicon glyphicon-download"></span>
                                    <span>下载</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="step2" class="hide">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-10 col-sm-10">
                            <div>
                                <h4>请按照学生信息电子表格中指定的格式填写需要添加的一个或多个学生信息</h4>
                            </div>
                            <div class="alert alert-info"
                                 style="margin-top: 10px; margin-buttom: 30px">
                                <p>注意：表格中各个列均不能为空，若存在未填写的项，则该条信息将不能成功导入</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="step3" class="hide">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-8 col-sm-10">
                            <div>
                                <div>
                                    <h4>请点击下面上传文件按钮，上传填写好的学生信息电子表格</h4>
                                </div>
                                <div style="margin-top: 30px; margin-buttom: 15px">
									<span class="btn btn-info btn-file"> <span> <span
                                            class="glyphicon glyphicon-upload"></span> <span>上传文件</span>
									</span>
									<form id="import_file_upload" enctype="multipart/form-data">
                                        <input type="file" id="file" name="file">
                                    </form>
									</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hide" id="uploading">
                    <div class="row" style="margin-top: 15px" id="import_progress_bar">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-10 col-sm-10"
                             style="margin-top: 30px; margin-bottom: 30px">
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-success"
                                     role="progreessbar" aria-valuenow="60" aria-valuemin="0"
                                     aria-valuemax="100" style="width: 100%;">
                                    <span class="sr-only">请稍后...</span>
                                </div>
                            </div>
                            <!--
                            <div style="text-align: center">
                                <h4 id="import_info"></h4>
                            </div>
                             -->
                        </div>
                        <div class="col-md-1 col-sm-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4"></div>
                        <div class="col-md-4 col-sm-4">
                            <div id="import_result" class="hide">
                                <div id="import_success" class="hide" style="text-align: center;">
                                    <img src="media/icons/success-icon.png" alt=""
                                         style="width: 100px; height: 100px;">
                                </div>
                                <div id="import_error" class="hide" style="text-align: center;">
                                    <img src="media/icons/error-icon.png" alt=""
                                         style="width: 100px; height: 100px;">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4"></div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-md-3"></div>
                        <div class="col-md-6" style="text-align: center;">
                            <h4 id="import_info"></h4>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn ben-default" type="button" id="previous">
                    <span>上一步</span>
                </button>
                <button class="btn btn-success" type="button" id="next">
                    <span>下一步</span>
                </button>
                <button class="btn btn-success hide" type="button" id="submit">
                    <span>&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;</span>
                </button>
                <button class="btn btn-success hide disabled" type="button"
                        id="confirm" data-dismiss="modal">
                    <span>&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;</span>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 导出学生信息模态框 -->
<div class="modal fade" id="export_modal" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">导出学生信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3 col-sm-3" style="text-align: center;">
                        <img src="media/icons/warning-icon.png" alt=""
                             style="width: 70px; height: 70px; margin-top: 20px;">
                    </div>
                    <div class="col-md-8 col-sm-8">
                        <h3>是否确认导出学生信息</h3>
                        <p>(注意：请确定要导出的学生信息，导出的内容为当前列表的搜索结果)</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>取消</span>
                </button>
                <button class="btn btn-success" type="button" id="export_students_download">
                    <span>确认下载</span>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 编辑学生信息模态框 -->
<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">更改学生信息</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <form enctype="multipart/form-data"
                              class="form-horizontal" role="form" id="student_form_edit" style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_name_edit"
                                           name="student_name" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>性别：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="student_sex_edit" name="student_sex" class="form-control">
                                        <option value="">请选择性别:</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>身份证号：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="student_id_card_edit"
                                           name="student_id_card" placeholder="身份证号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>专业：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="student_major_edit" name="major_id" class="form-control">
                                        <option value="">请选择对应专业:</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>班级：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id="student_class_edit" name="class_id" class="form-control">
                                        <option value="">请选择对应班级:</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>人脸照片更新：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="file" class="form-control" id="student_picture_edit"
                                           name="student_picture" placeholder="人脸照片更新">
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
                <button class="btn btn-success" type="button" id="edit_modal_submit">
                    <span>确认更改</span>
                </button>
            </div>
        </div>
    </div>
</div>


</body>
</html>
