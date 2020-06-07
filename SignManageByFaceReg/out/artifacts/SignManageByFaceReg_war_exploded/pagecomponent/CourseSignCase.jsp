<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/26
  Time: 12:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实验课考勤信息查看</title>
</head>
<body>

<script>
    //立即函数
    $(function () {
        CourseSignCaseListInit();
    })

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        return Y + M + D;
    }

    // courseSignCaseList 表格
    function CourseSignCaseListInit() {
        console.log("初始化了！！！！")
        $('#courseSignCaseList').bootstrapTable(
            {
                toolbar: '.toolbar',
                url: 'SignCase/getMyCourseSignCase',
                method: 'GET',
                queryParams: {"interval_time": "7 day"}, //默认七天的数据
                // sidePagination: "server",
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
                sortName: 'course_start_timestamp',
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
                        title: '编号',
                        sortable: true
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
                        field: 'course_name',
                        title: '课程名称'
                    },
                    {
                        field: 'course_start_timestamp',
                        title: '考勤时间',
                        formatter: timestampToTime,
                        sortable: true
                    },
                    {
                        field: 'late_count',
                        title: '迟到人数',
                    },
                    {
                        field: 'truancy_count',
                        title: '旷课人数',
                    },
                    {
                        field: 'success_count',
                        title: '出勤人数',
                    },
                    {
                        field: 'operation',
                        title: '操作',
                        formatter: function (value, row, index) {
                            // var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                            // return s;
                            return [
                                '<a href="javascript:void(0)" ' +
                                'class="btn btn-info btn-sm look" ' +
                                'name="pagecomponent/ClassSignCaseShow.jsp">查看详情</a>'
                                // '<a href="javascript:void(0)" class="edit">查看课程学生</a>'
                            ].join('')
                        },
                        events: {
                            'click .look': function (e, value, row, index) {
                                console.log("进入 查看详情！！！")
                                var url = $(this).attr("name");
                                console.log(url)
                                //重新绘制页面
                                $('#panel').load(url, {
                                    'course_id': row.course_id,
                                    'course_name': row.course_name,
                                    'major_id': row.major_id,
                                    'major_name': row.major_name,
                                    'class_id': row.class_id,
                                    'class_name': row.class_name,
                                    'bitmap_idx': row.bitmap_idx,
                                    'course_start_timestamp': row.course_start_timestamp
                                });
                            }
                        }
                    }],
            });
    }

    $('#interval_time_select').change(function () {
        console.log("$('#interval_time_select').val() == " + $('#interval_time_select').val())

        $('#courseSignCaseList').bootstrapTable('refresh', {
            query: {
                interval_time: $('#interval_time_select').val()
            }
        })
    })
</script>


<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>实验课考勤信息查看</li>
    </ol>
    <div class="panel-body">
        <div class="row" style="margin-top: 15px">
            <div class="col-md-12">
                <div class="toolbar">
                    <select id="interval_time_select" name="interval_time_select" class="form-control">
                        <option value="7 day" selected>一周内</option>
                        <option value="1 month">过去一个月</option>
                        <option value="3 month">过去三个月</option>
                    </select>
                </div>
                <table id="courseSignCaseList" class="table table-striped"></table>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
