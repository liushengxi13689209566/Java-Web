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
        courseListInit();

        addOneCourse();
    })

    // 表格刷新
    function courseListTableRefresh() {
        $('#courseList').bootstrapTable('refresh');
    }

    function addOneCourse() {
        $('#add_one_course').click(function () {
            $('#add_modal').modal("show");
        });
        $('#add_modal_submit').click(function () {
            console.log("点击了提交按钮了")
            var data = {
                course_id: $('#course_id').val()
            }
            // ajax
            $.ajax({
                url: "course/addOneCourseToTea",
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                data: data,
                success: function (response) {
                    $('#add_modal').modal("hide");
                    if (response.status_code == 0) {
                        alert("success! 课程添加成功");
                    } else {
                        alert(response.msg);
                    }
                    courseListTableRefresh();
                    // reset
                    $('#student_id').val("");
                },
                error: function (response) {
                }
            })
        })
    }

    function deleteOnCourse(course_id) {
        var data = {
            "course_id": course_id
        }
        $.ajax({
            type: "GET",
            url: "course/deleteOneCourseInTea",
            dataType: "json",
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response)
                alert("删除成功！")
                if (response.status_code == 0) {
                    //减少后端请求，前端页面刷新即可
                    $('#courseList').bootstrapTable('remove', {
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

    // courseList 表格
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
                            // var s = '<button class="btn btn-info btn-sm edit"><span>更改</span></button>';
                            var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            // var fun = '';
                            // return s + ' ' + d;
                            return d;
                        },
                        events: {
                            // 操作列中编辑按钮的动作
                            // 'click .edit': function (e, value, row, index) {
                            //     rowEditOperation(row);
                            // },
                            'click .delete': function (e, value, row, index) {
                                if (confirm('确定删除该课程吗 ? ') == false)
                                    return false;

                                console.log("确认删除了！！！")
                                deleteOnCourse(row.course_id)
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
        <li>所教课程管理</li>
    </ol>
    <div class="panel-body">

        <div class="row">
            <div class="col-md-5">
                <button class="btn btn-sm btn-info" id="add_one_course">
                    <span class="glyphicon glyphicon-plus"></span> <span>新增一门课程</span>
                </button>
            </div>
            <div class="col-md-5"></div>
        </div>

        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <table id="courseList" class="table table-striped"></table>
                </table>
            </div>
        </div>
    </div>
</div>
<%--添加课程模态框--%>
<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">添加课程</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <%--  所有数据的校验由前端之后来做--%>
                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4"> <span>课程ID：</span>
                            </label>
                            <div class="col-md-8 col-sm-8">
                                <input type="text" class="form-control" id="course_id"
                                       name="course_id" placeholder="课程ID">
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