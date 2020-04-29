<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/10
  Time: 2:04 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>查看课程学生</title>
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
<%--获取参数--%>
<%
    String course_id = request.getParameter("course_id");
    System.out.println(course_id);
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
        OneCourseAllStudentListInit();

        addstudentAction();
        importAllStudentsInfo();

    })

    function getCourseID() {
        var temp = {
            course_id: <%=course_id%>
        }
        console.log(temp)
        return temp;
    }

    // 表格刷新
    function oneCourseAllStudentListTableRefresh() {
        $('#oneCourseAllStudentList').bootstrapTable('refresh', {
            query: {getCourseID}
        });
    }

    // 添加学生信息
    function addstudentAction() {
        $('#add_one_student').click(function () {
            $('#add_modal').modal("show");
        });
        $('#add_modal_submit').click(function () {
            console.log("点击了提交按钮了")
            var data = {
                student_id: $('#student_id').val(),
                course_id: <%=course_id%>
            }
            // ajax
            $.ajax({
                url: "course/addOneStudentInCourse",
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                data: data,
                success: function (response) {
                    $('#add_modal').modal("hide");
                    if (response.status_code == 0) {
                        alert("success! 学生添加成功");
                    } else {
                        alert(response.msg);
                    }
                    oneCourseAllStudentListTableRefresh();
                    // reset
                    $('#student_id').val("");
                },
                error: function (response) {
                }
            })
        })
    }

    // 删除一门课的一个学生
    function deleteOnCourseOneStudent(student_id, course_id) {
        var data = {
            "student_id": student_id,
            "course_id": course_id
        }
        // ajax
        $.ajax({
            type: "GET",
            // 因为 @RequestParam无法处理json,创建实体类又太夸张
            // 所以我们直接构造
            url: "course/delOneStudentInCourse",
            dataType: "json",
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response)
                if (response.status_code == 0) {
                    // infoModal(type, msg);
                    //减少后端请求，前端页面刷新即可
                    $('#oneCourseAllStudentList').bootstrapTable('remove', {
                        field: 'id',
                        values: [student_id]
                    });
                } else {
                    alert(response.msg);
                }
            }, error: function (response) {
            }
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

            var fileObj = document.getElementById("import_file_upload"); // js 获取文件对象
            console.log(fileObj)

            //构建 FormData 对象
            var formData = new FormData(document.getElementById("import_file_upload"));

            // formData.append("file", fileObj);
            formData.append("course_id", <%=course_id%>)

            console.log(formData)

            $.ajax({
                url: "course/importOneCourseStudents",
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

    // 导入 students 模态框重置
    function importModalReset() {
        var i;
        for (i = import_start; i <= import_end; i++) {
            var step = "step" + i;
            $('#' + step).removeClass("hide")
        }
        for (i = import_start; i <= import_end; i++) {
            var step = "step" + i;
            $('#' + step).addClass("hide")
        }
        $('#step' + import_start).removeClass("hide");

        $('#import_progress_bar').removeClass("hide");
        $('#import_result').removeClass("hide");
        $('#import_success').removeClass('hide');
        $('#import_error').removeClass('hide');
        $('#import_progress_bar').addClass("hide");
        $('#import_result').addClass("hide");
        $('#import_success').addClass('hide');
        $('#import_error').addClass('hide');
        $('#import_info').text("");
        $('#file').val("");

        $('#previous').removeClass("hide");
        $('#next').removeClass("hide");
        $('#submit').removeClass("hide");
        $('#confirm').removeClass("hide");
        $('#submit').addClass("hide");
        $('#confirm').addClass("hide");

        //$('#file').wrap('<form>').closest('form').get(0).reset();
        //$('#file').unwrap();
        //var control = $('#file');
        //control.replaceWith( control = control.clone( true ) );
        $('#file').on("change", function () {
            $('#previous').addClass("hide");
            $('#next').addClass("hide");
            $('#submit').removeClass("hide");
        })

        import_step = 1;
    }

    // 一门课学生列表表格初始化
    function OneCourseAllStudentListInit() {
        $('#oneCourseAllStudentList').bootstrapTable(
            {
                url: 'student/getOneCourseAllStudent',
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
                        title: '学号'
                        //sortable: true
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
                                deleteOnCourseOneStudent(row.id, <%=course_id%>)
                            }
                        }
                    }],
            })
    }


</script>
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>课程学生管理</li>
    </ol>
    <div class="panel-body">
        <%-- 一门课添加一个学生与批量导入功能--%>
        <div class="row">
            <div class="col-md-5">
                <button class="btn btn-sm btn-info" id="add_one_student">
                    <span class="glyphicon glyphicon-plus"></span> <span>新增一名学生</span>
                </button>
                <button class="btn btn-sm btn-primary" id="import_students">
                    <span class="glyphicon glyphicon-import"></span> <span>整体学生信息导入</span>
                </button>
            </div>
            <div class="col-md-5"></div>
        </div>

        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="oneCourseAllStudentList" class="table table-striped"></table>
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
                <h4 class="modal-title" id="myModalLabel">导入学生信息</h4>
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
                                   href="download/studentIDList.xlsx"
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

<!-- 添加学生模态框 -->
<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">添加学生</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <%--  所有数据的校验由前端之后来做--%>
                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4"> <span>学号：</span>
                            </label>
                            <div class="col-md-8 col-sm-8">
                                <input type="text" class="form-control" id="student_id"
                                       name="student_id" placeholder="学号">
                            </div>
                        </div>
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
