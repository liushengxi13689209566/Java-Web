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
        *允许：将流赋值给 video.src 属性让其显示
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
        // $('#userID').on('input propertychange', function () {
        //     getMyCourse();
        // })
        $('#sign_button').click(function () {
            sendData(pen, canvas)
        })
    }

    //
    // function getMyCourse() {
    //     //这里不需要登录，从后台获取实验课程即可。
    //     // （这里主要服务的是实验课，所以我这里不用分专业进行选择框的设计，不需要登录是为了与后台进行一个区分）
    //     console.log("  bbhvhhjbsvhbfv == " + $("#userID").val())
    //     var data = {
    //         "id": $("#userID").val()
    //     }
    //     $.ajax({
    //         type: "GET",
    //         url: 'course/getMyCourse',
    //         dataType: 'json',
    //         contentType: 'application/json',
    //         data: data,
    //         success: function (response) {
    //             console.log(response)
    //             $.each(response.rows, function (index, elem) {
    //                 $('#course_selector').append("<option value='" + elem.course_id + "'>" + elem.course_name + "</option>");
    //             });
    //         },
    //         error: function (response) {
    //             $('#course_selector').append("<tr><td>" + "加载失败" + "</td></tr>");
    //         }
    //     });
    // }

    /*
   *通过 canvas 画笔，将 vedio 截图（画一幅）
   * canvas.toDataURL() 将数据装换成 base64 类型数据（我们的面部数据）
   * ajax（异步刷新）实现数据以 json 格式传到后台
   */
    function sendData(pen, canvas) {
        console.log("点击了考勤按钮！！！")

        $('#sign_modal').modal("show");

        pen.drawImage(video, 0, 0, 400, 300);
        var url = canvas.toDataURL();
        var result = url.split(",")[1];
        // console.log("course_selector == " + $("#course_selector").val())
        // console.log("  userID == " + $("#userID").val())
        // console.log("  result == " + result)

        var formData = new FormData(document.getElementById("sign_form"));
        formData.append("face_image", result); //将处理后的人脸照片添加进去
        formData.append("timestamp", Date.parse(new Date())) //只精确到秒的时间戳
        console.log("time == " + Date.parse(new Date()))
        $.ajax({
            type: "GET",
            url: 'FaceSignIn',
            type: "post",
            data: formData,
            async: true,
            cache: false,
            dataType: "json",
            contentType: false,
            processData: false,
            success: function (response) {
                console.log(response);
                $('#sign_modal').modal("hide");

                console.log("response.flag == bdfvbkfbvkfb ");
                // if (response.status_code != 0) {
                //     alert(response.msg);
                // } else {
                alert(response.msg);
                <%--window.location.href = "${pageContext.request.contextPath}/book/allBook";--%>
                // }
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
                    <form id="sign_form" class="form-horizontal" style="" enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="col-md-4"></div>
                            <div class="col-md-7 col-sm-7">
                                <div id="face_image" style="margin: 10px auto;">
                                    <%--    * 前端页面需要使用到两个标签，一个是<video>一个是<canvas>，--%>
                                    <%--    * 这两个标签，video是将摄像头获取到的数据呈现成视频，canvas是画布，将视频中的画面换成图片--%>
                                    <video id="video" width="400" height="300"></video>
                                    <canvas id="canvas" width="400" height="300" style="display: none"></canvas>
                                </div>
                            </div class="col-md-7 col-sm-7">
                        </div>
                        <div>
                            <div class="col-md-4 col-sm-4"></div>
                            <div class="col-md-4 col-sm-4">
                                <button id="sign_button" style="margin-right: 90px;" type="button"
                                        class="btn btn-primary">
                                    &nbsp;&nbsp;&nbsp;&nbsp;考勤&nbsp;&nbsp;&nbsp;&nbsp;
                                </button>
                                <a href="./login.jsp" type="button" class="btn btn-primary">
                                    &nbsp;&nbsp;&nbsp;&nbsp;点击登录考勤后台&nbsp;&nbsp;&nbsp;&nbsp;
                                </a>
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


<%--识别过程摸态框--%>
<div id="sign_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">人脸识别中...</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
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
            </div>
        </div>
    </div>
</div>

</body>
</html>