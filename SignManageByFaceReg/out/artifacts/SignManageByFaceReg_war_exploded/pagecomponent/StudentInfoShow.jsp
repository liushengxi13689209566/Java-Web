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

<%--<div class="container">--%>
<%--    &lt;%&ndash;添加学生与整体导入学生信息的接口&ndash;%&gt;--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-4 column">--%>
<%--            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增一名学生</a>--%>
<%--        </div>--%>

<%--        <div class="col-md-4 column">--%>
<%--            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增一名学生</a>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="row clearfix">--%>
<%--        <div class="col-md-12 column">--%>
<%--            <table class="table table-hover table-striped">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>学号</th>--%>
<%--                    <th>姓名</th>--%>
<%--                    <th>专业</th>--%>
<%--                    <th>班级</th>--%>
<%--                    <th>操作</th>--%>
<%--                    &lt;%&ndash;添加课程学生，删除课程学生，查看课程学生。&ndash;%&gt;--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="studentInfoShow" items="${requestScope.get('list')}">--%>
<%--                    <tr>--%>
<%--                        <td>${studentInfoShow.getId()}</td>--%>
<%--                        <td>${studentInfoShow.getName()}</td>--%>
<%--                        <td>${studentInfoShow.getMajor_name()}</td>--%>
<%--                        <td>${studentInfoShow.getClass_name()}</td>--%>
<%--                        <td>--%>
<%--                                &lt;%&ndash; 没有权利去更改学生信息吧 &ndash;%&gt;--%>
<%--                            <a class='menu_item'--%>
<%--                               name="${pageContext.request.contextPath}/student/delOneStudentInCourse/?student_id=${studentInfoShow.getId()}&course_id=${requestScope.get('course_id')}"--%>
<%--                               onclick="click_item(this)">删除</a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>

<%--<script>--%>
<%--    $.ajax({--%>
<%--        type: 'GET',--%>
<%--        url: 'student/delOneStudentInCourse/?student_id=${studentInfoShow.getId()}&course_id=${requestScope.get('course_id')}',--%>
<%--        dataType: 'json',--%>
<%--        contentType: 'application/json',--%>
<%--        success: function (response) {--%>
<%--            console.log(response)--%>
<%--            alert("删除成功")--%>
<%--        },--%>
<%--        error: function (response) {--%>
<%--            console.log(response)--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>
<%--获取参数--%>
<%
    String course_id = request.getParameter("course_id");
    System.out.println(course_id);
%>
<script>
    // 使用参数
    var id = "<%=course_id%>";
    console.log("user == fvnjdfvjbfkjvbfkdvfbddkbkdbf")
    console.log("id == " + id)
</script>

<script>

    $(function () {
        OneCourseAllStudentListInit();
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
            query: {}
        });
    }

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
            url: "student/delOneStudentInCourse",
            dataType: "json",
            contentType: "application/json",
            data: data,
            success: function (response) {
                // $('#deleteWarning_modal').modal("hide");
                console.log(response)
                if (response.status_code == 0) {
                    // infoModal(type, msg);
                    //减少后端请求，前端页面刷新即可
                    $('#oneCourseAllStudentList').bootstrapTable('remove', {
                        field: 'id',
                        values: [student_id]
                    });
                    // oneCourseAllStudentListTableRefresh();
                }
            }, error: function (response) {
            }
        })
    }


    // 一门课学生列表表格
    function OneCourseAllStudentListInit() {
        $('#oneCourseAllStudentList').bootstrapTable(
            {
                url: 'course/getOneCourseAllStudent',
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
                <%--                <table id="courseList" class="table table-striped"></table>--%>
                <table id="oneCourseAllStudentList" class="table table-striped">
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


<!-- 删除提示模态框 -->
<div class="modal fade" id="deleteWarning_modal" table-index="-1"
     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">警告</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3 col-sm-3" style="text-align: center;">
                        <img src="media/icons/warning-icon.png" alt=""
                             style="width: 70px; height: 70px; margin-top: 20px;">
                    </div>
                    <div class="col-md-8 col-sm-8">
                        <h3>是否确认删除该条客户信息</h3>
                        <p>(注意：若该客户已经有仓库出库记录，则该客户信息将不能删除成功。如需删除该客户的信息，请先删除该客户的出库记录)</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>取消</span>
                </button>
                <button class="btn btn-danger" type="button" id="delete_confirm">
                    <span>确认删除</span>
                </button>
            </div>
        </div>
    </div>
</div>

</body>

</html>
