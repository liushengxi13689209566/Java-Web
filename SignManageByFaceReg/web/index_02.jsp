<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/3/10
  Time: 4:01 下午
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>基于人脸识别的实验室考勤管理系统</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <style>
        .login_div {
            width: 500px;
            margin: 20px auto;
        }
    </style>
</head>
<body>


<%--获取下拉框的数据--%>
<script>
    //这里不需要登录，从后台获取实验课程即可。
    // （这里主要服务的是实验课，所以我这里不用分专业进行选择框的设计，不需要登录是为了与后台进行一个区分）
    $.ajax({
        type: "GET",
        url: 'course/getAllCourse',
        dataType: 'json',
        contentType: 'application/json',
        data: {
            // offset: -1,
            // limit: -1,
            // searchType: 'searchByName',
            // keyWord: request.term
        },
        success: function (response) {
            console.log(response)
            $.each(response.rows, function (index, elem) {
                $('#course_selector').append("<option value='" + elem.course_name + "'>" + elem.course_name + "</option>");
            });
        },
        error: function (response) {
            $('#course_selector').append("<tr><td>" + "加载失败" + "</td></tr>");
        }
    });
</script>


<div class="container">
    <div class="row">
        <div class="col-md-4 col-sm-3"></div>
        <!-- 这一列为签到表单 -->
        <div class="sign_div">
            <div class="panel panel-default">

                <!-- 登陆面板的标题 -->
                <div class="panel-title" style="text-align: center">
                    <h2>人脸识别考勤签到端</h2>
                </div>

                <!-- 登陆面板的主体 -->
                <div class="panel-body">
                    <div>
                        <%--    * 前端页面需要使用到两个标签，一个是<video>一个是<canvas>，--%>
                        <%--    * 这两个标签，video是将摄像头获取到的数据呈现成视频，canvas是画布，将视频中的画面换成图片--%>
                        <video id="video" width="400" height="300"></video>
                        <canvas id="canvas" width="400" height="300" style="display: none"></canvas>
                    </div>
                    <!-- 表单 -->
                    <%--                    <div class="form-group">--%>
                    <%--                        <div class="col-md-7 col-sm-7">--%>

                    <%--                        </div>--%>
                    <%--                    </div>--%>

                    <%--                    <form id="sign_form" class="form-horizontal" style="">--%>
                    <%--                        <div class="form-group">--%>
                    <%--                            <div class="col-md-7 col-sm-7">--%>
                    <%--                                <label class="control-label col-md-4 col-sm-4">--%>
                    <%--                                    所考勤课程：--%>
                    <%--                                </label>--%>
                    <%--                                <select id="course_selector" class="form-control">--%>
                    <%--                                    <option value="">请选择对应课程:</option>--%>
                    <%--                                </select>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                        <div>--%>
                    <%--                            <div class="col-md-4 col-sm-4"></div>--%>
                    <%--                            <div class="col-md-4 col-sm-4">--%>
                    <%--                                <button id="SignIn" class="btn btn-primary">--%>
                    <%--                                    &nbsp;&nbsp;&nbsp;&nbsp;考勤 &nbsp;&nbsp;&nbsp;--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="col-md-4 col-sm-4"></div>--%>
                    <%--                        </div>--%>
                    <%--                    </form>--%>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-sm-3"></div>
    </div>
</div>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.md5.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/mainPage.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>

