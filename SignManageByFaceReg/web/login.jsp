<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>实验课考勤管理系统</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/model/login/login.css">
    <style>
        .login_div{
            width:500px;
            margin: 20px auto;
        }
    </style>
</head>
<body>
<!-- 定义容器 -->
<div class="container">
    <div class="row">
        <div class="col-md-4 col-sm-3"></div>
        <!-- 这一列为登陆表单 -->
<%--        <div class="col-md-4 col-sm-6">--%>
            <div class="login_div">
            <div class="panel panel-default">

                <!-- 登陆面板的标题 -->
                <div class="panel-title" style="text-align: center">
                    <h2>实验课考勤管理系统</h2>
                </div>

                <!-- 登陆面板的主体 -->
                <div class="panel-body">

                    <!-- 表单 -->
                    <form id="login_form" class="form-horizontal" style="">

                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4">用户ID：</label>
                            <div class="col-md-7 col-sm-7">
                                <input type="text" id="userID" class="form-control"
                                       placeholder="用户ID" name="userID"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4">
                                <!-- <span class="glyphicon glyphicon-lock"></span> -->
                                密码：
                            </label>
                            <div class="col-md-7 col-sm-7">
                                <input type="password" id="password" class="form-control"
                                       placeholder="密码" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4">
                                角色：
                            </label>
                            <div class="col-md-7 col-sm-7">
                                <select id="role" class="form-control" name="role">
                                    <option value="student">学生</option>
                                    <option value="teacher">任课老师</option>
                                    <option value="admin">系统管理员</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4 col-sm-4">
                                <!-- <span class="glyphicon glyphicon-lock"></span> -->
                                验证码：
                            </label>
                            <div class="col-md-5 col-sm-4">
                                <input type="text" id="checkCode" class="form-control"
                                       placeholder="验证码" name="checkCode">
                            </div>
                            <div>
                                <img style="width: 100px;" id="checkCodeImg" alt="checkCodeImg"
                                     src="account/checkCode/1">
                            </div>
                        </div>

                        <div>
                            <div class="col-md-4 col-sm-4"></div>
                            <div class="col-md-4 col-sm-4">
                                <button id="submit" type="submit" class="btn btn-primary">
                                    &nbsp;&nbsp;&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;&nbsp;
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

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.md5.js"></script>

<script>
    $(function () {
        validatorInit();
        refreshCheckCode();
    });

    // 刷新图形验证码
    function refreshCheckCode() {
        $('#checkCodeImg').click(function () {
            var timestamp = new Date().getTime();
            //被 AccountHandler 类拦截
            $(this).attr("src", "account/checkCode/" + timestamp)
        })
    }

    // 登陆信息加密模块
    function infoEncrypt(userID, password, checkCode) {
        var str3 = $.md5(userID+ password + checkCode.toUpperCase());
        console.log("str3== " + str3);
        return str3;
    }

    //bootstrapValidator 插件对表单进行封装，表单不用加action
    function validatorInit() {
        $('#login_form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userID: {
                    validators: {
                        notEmpty: {
                            message: '用户ID不能为空'
                        }, regexp: {
                            regexp: '[0-9]+',
                            message: '只允许输入数字'
                        },
                        callback: {}
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        callback: {}
                    }
                },
                checkCode: {
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        }
                    }
                }
            }
        })
            .on('success.form.bv', function (e) {
                // 禁用默认表单提交
                e.preventDefault();

                // 获取 form 实例
                var $form = $(e.target);
                // 获取 bootstrapValidator 实例
                var bv = $form.data('bootstrapValidator');

                // 发送数据到后端 进行验证
                var userID = $('#userID').val();
                var password = $('#password').val();
                var checkCode = $('#checkCode').val();
                var role = $('#role').val();

                // 加密
                password = infoEncrypt(userID, password, checkCode)

                var data = {
                    "id": userID,
                    "password": password,
                    "role": role,
                }
                //JSON.stringify(data)序列化
                $.ajax({
                    type: "POST",
                    url: "account/login",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        // 接收到后端响应
                        // 分析返回的 JSON 数据
                        console.log(response);

                        if (response.status_code != 0) {
                            var field;
                            if (response.status_code == 3) {
                                field = "userID";
                            } else if (response.status_code == 4) {
                                field = "password";
                                $('#password').val("");
                            } else {
                                field = "password";
                                $('#password').val("");
                            }
                            // 更新 callback 错误信息，以及为错误对应的字段添加 错误信息
                            bv.updateMessage(field, 'callback', response.msg);
                            bv.updateStatus(field, 'INVALID', 'callback');
                            bv.updateStatus("checkCode", 'INVALID', 'callback');
                            //更新验证码
                            $('#checkCodeImg').attr("src", "account/checkCode/" + new Date().getTime());
                            $('#checkCode').val("");
                        } else {
                            // 页面跳转
                            window.location.href = "${pageContext.request.contextPath}/book/allBook";
                        }
                    },
                    error: function (data) {
                        // 处理错误
                    }
                });
            });
    }
</script>
</body>
</html>



