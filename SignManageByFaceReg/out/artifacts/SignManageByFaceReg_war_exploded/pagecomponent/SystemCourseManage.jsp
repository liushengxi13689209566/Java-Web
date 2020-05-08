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
    <title>课程管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .table > thead > tr > th {
            text-align: center;
        }

        .form-control {
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            margin: 5px;
        }

        .control-label {
            margin: 10px 0;
        }
    </style>
</head>
<body>

<script>

    //立即函数
    $(function () {
        allCourseListInit();
        datePickerInit();
        bootstrapValidatorInit();

        addCourseAction();
    })

    function deleteOneCourse(course_id) {
        var data = {
            "course_id": course_id
        }
        $.ajax({
            type: "GET",
            url: "course/deleteOneCourse",
            dataType: "json",
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response)
                if (response.status_code == 0) {
                    alert("删除成功！")
                    //减少后端请求，前端页面刷新即可
                    $('#allCourseList').bootstrapTable('remove', {
                        field: 'course_id',
                        values: [course_id]
                    });
                } else {
                    alert(response.msg);
                }
            }, error: function (response) {
            }
        })
    }

    // allCourseList表格
    function allCourseListInit() {
        console.log("初始化了！！！！")
        $('#allCourseList').bootstrapTable(
            {
                url: 'course/getAllCourse',
                method: 'GET',
                sidePagination: "server",
                dataType: 'json',
                // 分页选项
                // pagination: true,
                // pageNumber: 1,
                // pageSize: 5,
                // pageList: [5, 10, 25, 50, 100],
                //单选选项
                // clickToSelect: true
                //排序选项
                sortClass: "table-active",
                sortable: true,
                sortName: 'course_id',
                sortOrder: 'asc',
                //搜索功能
                search: true,
                //怎么不显示呐？
                showSearchClearButton: true,
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
                        title: '课程号'
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
                                if (confirm('确定删除该课程吗 ? ') == false)
                                    return false;

                                console.log("确认删除了！！！")
                                deleteOneCourse(row.course_id)
                            }
                        }
                    },
                    {
                        field: 'look operation',
                        title: '查看',
                        formatter: function (value, row, index) {
                            return [
                                '<a href="javascript:void(0)" ' +
                                'class="btn btn-info btn-sm look" ' +
                                'name="pagecomponent/CourseTimeTableShow.jsp">查看课程时间表</a>'
                            ].join('')
                        },
                        events: {
                            'click .look': function (e, value, row, index) {

                                console.log("点击了！！！！！！！")

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
            });
    }

    // 表格刷新
    function tableRefresh() {
        $('#allCourseList').bootstrapTable('refresh', {
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

    // 日期选择器初始化
    function datePickerInit() {
        $('.form_date').datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh-CN',
            weekStart: 1, //一周从 礼拜1 开始
            todayBtn: 1, //今天的按钮
            autoClose: 1,//当选择一个日期之后是否立即关闭此日期时间选择器。
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            minView: 2
        });
    }

    // 操作结果提示模态框
    function infoModal(type, msg) {
        $('#info_success').removeClass("hide");
        $('#info_error').removeClass("hide");
        if (type == 0) {
            $('#info_error').addClass("hide");
        } else {
            $('#info_success').addClass("hide");
        }
        $('#info_content').text(msg);
        $('#info_modal').modal("show");
    }

    //上一步与下一步的设定
    var import_step = 1;
    var import_start = 1;
    var import_end = 3;

    //整体导入课程信息
    function addCourseAction() {
        $('#add_one_student').click(function () {
            $('#import_modal').modal("show");
            $('#course_form').bootstrapValidator("resetForm", true);
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

        $('#course_time_table').on("change", function () {
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

            //构建 FormData 对象
            var formData = new FormData(document.getElementById("course_form"));
            console.log(formData)

            console.log("start_date == " + $('#start_date').val());
            console.log("end_date == " + $('#end_date').val());

            $.ajax({
                url: "course/addOneCourse",
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
                    var msg1 = "课程信息导入成功";
                    var msg2 = "课程信息导入失败";
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
            // importModalReset();
            tableRefresh();
        })
    }

    // 行编辑操作
    function rowEditOperation(row) {
        $('#edit_modal').modal("show");
        // load info
        $('#course_form_edit').bootstrapValidator("resetForm", true);
        $('#course_name_edit').val(row.course_name);
        $('#course_times_edit').val(row.course_times);
        $('#course_credit_edit').val(row.course_credit);
        $('#start_date_edit').val(timestampToTime(row.course_start));
        $('#end_date_edit').val(timestampToTime(row.course_end));

        $('#edit_modal_submit').click(function () {
                console.log("点击了确认提交按钮！！")

                $('#course_form_edit').data('bootstrapValidator')
                    .validate();

                console.log(" 判断是否已验证通过 " + $('#course_form_edit').data('bootstrapValidator').isValid())

                if (!$('#course_form_edit').data('bootstrapValidator')
                    .isValid()) {
                    return;
                }

                var data = {
                    course_id: row.course_id,
                    course_name: $('#course_name_edit').val(),
                    course_times: $('#course_times_edit').val(),
                    course_credit: $('#course_credit_edit').val(),
                    course_start: $('#start_date_edit').val(),
                    course_end: $('#end_date_edit').val()
                }
                // ajax
                $.ajax({
                    type: "POST",
                    url: 'course/updateOneCourse',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        $('#edit_modal').modal("hide");
                        infoModal(response.status_code, response.msg);
                        tableRefresh()
                    },
                    error: function (response) {
                    }
                });
            }
        )
    }

    // 课程信息数据校验
    function bootstrapValidatorInit() {
        $("#course_form,#course_form_edit").bootstrapValidator({
            message: 'This is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: [':disabled'],
            fields: {
                course_name: {
                    validators: {
                        notEmpty: {
                            message: '课程名称不能为空'
                        }
                    }
                },
                course_times: {
                    validators: {
                        notEmpty: {
                            message: '课时不能为空'
                        },
                        digits: {
                            message: '课时必须是正整数'
                        },
                        lessThan: {
                            value: 100,
                            message: '课时最大输入为 100'
                        }
                    }
                },
                course_credit: {
                    validators: {
                        notEmpty: {
                            message: '课程学分不能为空'
                        },
                        digits: {
                            message: '课程学分必须是正整数'
                        },
                        lessThan: {
                            value: 20,
                            message: '课程学分最大输入为 20'
                        }
                    }
                }
            }
        })
    }


</script>


<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>所教课程管理</li>
    </ol>
    <div class="panel-body">

        <div class="row">
            <div class="col-md-5">
                <button class="btn btn-sm btn-info" id="add_one_student">
                    <span class="glyphicon glyphicon-plus"></span> <span>新增一门课程</span>
                </button>
            </div>
            <div class="col-md-5"></div>
        </div>

        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="allCourseList" class="table table-striped"></table>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- 提示消息模态框 -->
<div class="modal fade" id="info_modal" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-4 col-sm-4"></div>
                    <div class="col-md-4 col-sm-4">
                        <div id="info_success" class=" hide" style="text-align: center;">
                            <img src="media/icons/success-icon.png" alt=""
                                 style="width: 100px; height: 100px;">
                        </div>
                        <div id="info_error" style="text-align: center;">
                            <img src="media/icons/error-icon.png" alt=""
                                 style="width: 100px; height: 100px;">
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4"></div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-4"></div>
                    <div class="col-md-4" style="text-align: center;">
                        <h4 id="info_content"></h4>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;</span>
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
                <h4 class="modal-title">添加课程信息</h4>
            </div>
            <div class="modal-body">
                <div id="step1">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-10 col-sm-10">
                            <div>
                                <h4>点击下面的下载按钮，下载课程上课时间表</h4>
                            </div>
                            <div style="margin-top: 30px; margin-buttom: 15px">
                                <a class="btn btn-info"
                                   href="download/courseTimeTable.xlsx"
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
                                <h4>请按照课程上课时间表中指定的格式填写需要添加的一个或多个时刻表信息</h4>
                            </div>
                            <div class="alert alert-info"
                                 style="margin-top: 10px; margin-buttom: 30px">
                                <p>注意：表格中各个列均不能为空，若存在未填写的项，则该条信息将不能成功导入</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="step3" class="hide">
                    <div class="row">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-8 col-sm-8">
                            <div>
                                <h4>请点击下面选择文件按钮，上传填写好的课程上课时间表</h4>
                            </div>
                            <form enctype="multipart/form-data" id="course_form">
                                <div class="form-group">
                                    <label class="control-label col-md-4 col-sm-4"> <span>课程名称：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="course_name"
                                               name="course_name" placeholder="课程名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4 col-sm-4"> <span>课时：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="course_times"
                                               name="course_times" placeholder="课时">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4 col-sm-4"> <span>学分：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="course_credit"
                                               name="course_credit" placeholder="学分">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4 col-sm-4"> <span>起始时间：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input class="form_date form-control" id="start_date"
                                               name="start_date" placeholder="起始时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4 col-sm-4"> <span>结束时间：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input class="form_date form-control" id="end_date"
                                               name="end_date" placeholder="结束时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4 col-sm-4"> <span>课程时刻表：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="file" class="form-control" id="course_time_table"
                                               name="course_time_table" placeholder="课程时刻表">
                                    </div>
                                </div>
                            </form>
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


<!-- 编辑课程信息模态框 -->
<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">更改课程信息</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">

                        <form id="course_form_edit">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>课程名称：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="course_name_edit"
                                           name="course_name" placeholder="课程名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>课时：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="course_times_edit"
                                           name="course_times" placeholder="课时">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>学分：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="course_credit_edit"
                                           name="course_credit" placeholder="学分">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>起始时间：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="start_date_edit"
                                           name="start_date" placeholder="起始时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>结束时间：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="end_date_edit"
                                           name="end_date" placeholder="结束时间">
                                </div>
                            </div>
                            <%--                            <div class="form-group">--%>
                            <%--                                <label class="control-label col-md-4 col-sm-4"> <span>课程时刻表：</span>--%>
                            <%--                                </label>--%>
                            <%--                                <div class="col-md-8 col-sm-8">--%>
                            <%--                                    <input type="file" class="form-control" id="course_time_table_edit"--%>
                            <%--                                           name="course_time_table_edit" placeholder="课程时刻表">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
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