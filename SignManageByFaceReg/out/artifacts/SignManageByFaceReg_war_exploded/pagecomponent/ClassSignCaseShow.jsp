<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/28
  Time: 11:37 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实验课考勤信息查看详情</title>
</head>


<body>
<%--获取参数--%>
<%
    String course_id = request.getParameter("course_id");
    String course_name = request.getParameter("course_name");
    System.out.println("course_name == " + course_name);

    String major_id = request.getParameter("major_id");
    String major_name = request.getParameter("major_name");
    System.out.println("major_name == " + major_name);

    String class_id = request.getParameter("class_id");
    String class_name = request.getParameter("class_name");
    System.out.println("class_name == " + class_name);


//    System.out.println(course_name);
    String bitmap_idx = request.getParameter("bitmap_idx");
    System.out.println("bitmap_idx == " + bitmap_idx);

    String course_start_timestamp = request.getParameter("course_start_timestamp");
//    System.out.println(course_name);
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
        ClassignCaseListInit();
    })

    // ClassignCaseList 表格
    function ClassignCaseListInit() {
        console.log("进行！！！！")
        $('#ClassSignCaseList').bootstrapTable(
            {
                toolbar: '.toolbar',
                url: 'SignCase/getOneDayClassSignCase',
                method: 'GET',
                queryParams: getParams,
                // sidePagination: "server",
                dataType: 'json',
                // 分页选项
                // pagination: true,
                // pageNumber: 1,
                // pageSize: 5,
                // pageList: [5, 10, 25, 50, 100],
                //单选选项
                // clickToSelect: true
                sortClass: "table-active",
                sortable: true,
                sortName: 'id',
                sortOrder: 'asc',
                //搜索功能
                search: true,
                //怎么不显示呐？
                showSearchClearButton: true,
                responseHandler: function (res) {
                    console.log("res == " + res)
                    $.each(res, function (i, row) {
                        console.log(row)
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
                        field: 'sign_case_flag',
                        title: '签到状态',
                        formatter: transToMsg
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
                                // rowEditOperation(row);
                            },
                            'click .delete': function (e, value, row, index) {
                                // // selectID = row.id;
                                // if (confirm('确定删除 ? ') == false)
                                //     return false;
                                //
                                // console.log("确认删除了！！！")
                                <%--deleteOnCourseOneStudent(row.id, <%=course_id%>)--%>
                            }
                        }
                    }],
            });
    }

    $('#sign_flag_select').change(function () {
        var flag = $('#sign_flag_select').val();
        if (flag == "666") {
            $('#ClassSignCaseList').bootstrapTable('filterBy', {})
        } else {
            $('#ClassSignCaseList').bootstrapTable('filterBy', {
                sign_case_flag: $('#sign_flag_select').val()
            })
        }
    })

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        return Y + M + D;
    }

    function transToMsg(ch) {
        var msg;
        if (ch == '0')
            msg = "未考勤"
        if (ch == '1')
            msg = "考勤成功";
        if (ch == '2')
            msg = "迟到";
        return msg;
    }

    function getParams() {
        var temp = {
            course_id:<%=course_id%>,
            major_id:<%=major_id%>,
            class_id:<%=class_id%>,
            bitmap_idx:<%=bitmap_idx%>, //很重要的一个参数
        }
        return temp;
    }

    var time_1 = $('#lsx_time').html()
    $('#lsx_time').html(timestampToTime(parseFloat(time_1)))

</script>

<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><%=major_name%><%=class_name%> | <span id="lsx_time"><%=course_start_timestamp%></span>| <%=course_name%>
            考勤信息查看
        </li>
    </ol>
    <div class="panel-body">
        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <div class="toolbar">
                    <select id="sign_flag_select" name="sign_flag_select" class="form-control">
                        <option value="666">全部考勤情况:</option>
                        <option value="0">未考勤</option>
                        <option value="2">迟到</option>
                        <option value="1">考勤成功</option>
                    </select>
                </div>
                <table id="ClassSignCaseList" class="table table-striped"></table>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
