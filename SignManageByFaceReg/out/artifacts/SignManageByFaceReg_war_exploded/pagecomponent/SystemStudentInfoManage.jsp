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
                    $('#major_id').append("<option value='" + elem.major_id + "'>" + elem.major_name + "</option>");
                });
            },
            error: function (response) {
                $('#major_id').append("<option value='-1'>加载失败</option>");
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
                    $('#class_id').append("<option value='" + elem.class_id + "'>" + elem.class_name + "</option>");
                });
            },
            error: function (response) {
                $('#class_id').append("<option value='-1'>加载失败</option>");
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

</body>
</html>
