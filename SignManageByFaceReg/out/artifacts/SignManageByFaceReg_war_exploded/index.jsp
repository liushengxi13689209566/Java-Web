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
    <title>人脸识别</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/model/login/login.css">

    <script type="text/javascript"
            src="//api.html5media.info/1.2.2/html5media.min.js"></script>
    <%--解决 navigator.mediaDevices.getUserMedia 兼容浏览器问题--%>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>

<script>
    //立即函数
    $(function () {
        start();
    })

    function start() {
        var video = document.getElementById("video");
        var canvas = document.getElementById("canvas");
        var pen = canvas.getContext("2d");
        /*画笔*/
        /*
        *用户是否允许使用摄像头
        *允许：将流赋值给video.src 属性让其显示
        * onloadedmetadata  video 加载完成开始播放
        */
        var constraints = {video: true};
        navigator.mediaDevices.getUserMedia(constraints)
            .then(function (stream) {
                video.srcObject = stream;
                /* 使用这个stream  */
                video.onloadedmetadata = function (e) {
                    video.play();
                }
            })
            .catch(function (err) {
                console.log(err.name || err);
                /* 处理error */
            });
        $('#sign_button').click(function () {
            sendData(pen, canvas)
        })
        $('#userID').on('input propertychange', function () {
            getMyCourse();
        })
    }

    function getMyCourse() {
        //这里不需要登录，从后台获取实验课程即可。
        // （这里主要服务的是实验课，所以我这里不用分专业进行选择框的设计，不需要登录是为了与后台进行一个区分）
        console.log("  bbhvhhjbsvhbfv == " + $("#userID").val())
        $.ajax({
            type: "GET",
            url: 'course/getMyCourse',
            dataType: 'json',
            contentType: 'application/json',
            data: {
                id: $("#userID").val()
                // offset: -1,
                // limit: -1,
                // searchType: 'searchByName',
                // keyWord: request.term
            },
            success: function (response) {
                console.log(response)
                $.each(response.rows, function (index, elem) {
                    $('#course_selector').append("<option value='" + elem.course_id + "'>" + elem.course_name + "</option>");
                });
            },
            error: function (response) {
                $('#course_selector').append("<tr><td>" + "加载失败" + "</td></tr>");
            }
        });
    }

    /*
   *通过 canvas 画笔，将 vedio 截图（画一幅）
   * canvas.toDataURL() 将数据装换成 base64 类型数据（我们的面部数据）
   * ajax（异步刷新）实现数据以 json 格式传到后台
   */
    function sendData(pen, canvas) {
        console.log("点击了考勤按钮！！！")

        <%--console.log("path==" + path);--%>
        <%--console.log("${pageContext.request.contextPath} ==" ${pageContext.request.contextPath});--%>

        pen.drawImage(video, 0, 0, 400, 300);
        var url = canvas.toDataURL();
        var result = url.split(",")[1];

        // var data = {
        //     "face_image": result,
        //     "course_id": document.getElementById("course_selector"),
        //     "userID": document.getElementById("userID")
        // }
        var data;
        $.ajax({
            type: "POST",
            url: '/FaceSignIn',
            dataType: "json",
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response);
                console.log("response.flag == bdfvbkfbvkfb ");
                if (response.status_code != 0) {
                    alert(response.msg);
                } else {
                    alert(response.msg);
                    window.location.href = "${pageContext.request.contextPath}/book/allBook";
                }
            },
            fail: function () {
                alert(" 请重试！");
            }
        })

    }
</script>
<!-- 定义容器 -->
<div class="container">
    <div class="row">
        <div class="col-md-4 col-sm-3"></div>
        <div class="sign_div">
            <div class="panel panel-default">
                <!-- 登陆面板的标题 -->
                <div class="panel-title" style="text-align: center">
                    <h2>人脸识别考勤签到端</h2>
                </div>
                <!-- 登陆面板的主体 -->
                <div class="panel-body">
                    <!-- 表单 -->
                    <form id="sign_form" class="form-horizontal" style="">
                        <div class="form-group">
                            <div class="col-md-7 col-sm-7">
                                <div id="image">
                                    <%--    * 前端页面需要使用到两个标签，一个是<video>一个是<canvas>，--%>
                                    <%--    * 这两个标签，video是将摄像头获取到的数据呈现成视频，canvas是画布，将视频中的画面换成图片--%>
                                    <video id="video" width="400" height="300"></video>
                                    <canvas id="canvas" width="400" height="300" style="display: none"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4">学号：</label>
                            <div class="col-md-7 col-sm-7">
                                <input type="text" id="userID" class="form-control"
                                       placeholder="学号" name="userID"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4">
                                所考勤课程：
                            </label>
                            <div class="col-md-7 col-sm-7">
                                <select id="course_selector" class="form-control" onchange="getMyCourse()">
                                    <option value="">请选择对应课程:</option>
                                </select>
                            </div>
                        </div>
                        <div>
                            <div class="col-md-4 col-sm-4"></div>
                            <div class="col-md-4 col-sm-4">
                                <button id="sign_button" type="submit" class="btn btn-primary">
                                    &nbsp;&nbsp;&nbsp;&nbsp;考勤&nbsp;&nbsp;&nbsp;&nbsp;
                                </button>
                            </div>
                            <div class="col-md-4 col-sm-4"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-3"></div>
    </div>
</div>

<%--<button id="SignIn"> 考勤</button>--%>
<%--<a href="login.jsp"> 我要登录 </a><br/>--%>
<%--<a href="/WEB-INF/jsp/test.html"> 测试 </a><br/>--%>
<%--<a href="${pageContext.request.contextPath}/book/allBook">点击进入列表页</a><br/>--%>

</body>
</html>