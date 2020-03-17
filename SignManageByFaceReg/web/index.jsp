<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/3/10
  Time: 4:01 下午
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <title>首页</title>--%>
<%--  <style type="text/css">--%>
<%--    a {--%>
<%--      text-decoration: none;--%>
<%--      color: black;--%>
<%--      font-size: 18px;--%>
<%--    }--%>
<%--    h3 {--%>
<%--      width: 180px;--%>
<%--      height: 38px;--%>
<%--      margin: 100px auto;--%>
<%--      text-align: center;--%>
<%--      line-height: 38px;--%>
<%--      background: deepskyblue;--%>
<%--      border-radius: 4px;--%>
<%--    }--%>
<%--  </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h3>--%>
<%--  <a href="${pageContext.request.contextPath}/book/allBook">点击进入列表页</a>--%>
<%--</h3>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>人脸识别</title>
</head>
<body>
<div>
    <div>
        <%--    * 前端页面需要使用到两个标签，一个是<video>一个是<canvas>，--%>
        <%--    * 这两个标签，video是将摄像头获取到的数据呈现成视频，canvas是画布，将视频中的画面换成图片--%>
        <video id="video" width="400" height="300"></video>
        <canvas id="canvas" width="400" height="300" style="display: none"></canvas>
    </div>
    <button id="SignIn"> 考勤</button>
</div>
</body>


<script src="//api.html5media.info/1.2.2/html5media.min.js"></script>
<%--解决 navigator.mediaDevices.getUserMedia 兼容浏览器问题--%>
<script src="/static/jquery/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    var video = document.getElementById("video");
    var canvas = document.getElementById("canvas");
    var pen = canvas.getContext("2d");
    /*画笔*/
    /*
    *用户是否允许使用摄像头
    *允许：将流赋值给video.src属性让其显示
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

    /*
    * 考勤调用方法
    * */
    document.getElementById("SignIn").addEventListener('click', function () {
        goPath("FaceSignIn");
    });

    /*
    *通过 canvas 画笔，将 vedio 截图（画一幅）
    * canvas.toDataURL() 将数据装换成 base64 类型数据（我们的面部数据）
    * ajax（异步刷新）实现数据以 json 格式传到后台
    */
    function goPath(path) {
        console.log("path==" + path);
        pen.drawImage(video, 0, 0, 400, 300);
        var url = canvas.toDataURL();
        var result = url.split(",")[1];
        $.ajax({
            url: '${pageContext.request.contextPath}/' + path,
            data: JSON.stringify({face: result}),
            contentType: 'application/json;charset=UTF-8',
            method: 'post',
            dataType: 'json',
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
                alert("请重试！");
            }
        })
    }
</script>
</html>

