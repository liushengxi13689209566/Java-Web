<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/4/23
  Time: 5:59 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<script>
    //动态处理密码是否为空
    $(function () {
        $('#modify_form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                old_password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                },
                new_pass_1: {
                    validators: {
                        identical: {
                            field: 'new_pass_2',
                            message: '两次输入的密码不相符'
                        }
                    }
                },
                new_pass_2: {
                    validators: {
                        identical: {
                            field: 'new_pass_1',
                            message: '两次输入的密码不相符'
                        }
                    }
                }
            }
        });
    });

</script>
<script>
    $('#modify_btn').on('click', function (e) {
        e.preventDefault()
        formData = $(this).serialize()
        // console.log(formData)
        var old_password = $('input[name="old_password"]').val()
        var new_pass_1 = $('input[name="new_pass_1"]').val()
        var new_pass_2 = $('input[name="new_pass_2"]').val()
        //console.log(old_password,new_pass_1,new_pass_2)
        if (old_password == "" || new_pass_1 == "") {
            window.alert('密码不能为空，请重新输入!')
            window.location.replace(window.location.href)
            return;
        }
        // console.log(old_password, new_pass_1)
        //console.log('1111')
        $.ajax({
            url: 'account/passwordModify',
            type: 'post',
            data: {
                "old_password": old_password,
                "new_password": new_pass_1
            },
            dataType: 'json',
            success: function (data) {
                var err_code = data.status_code
                if (err_code === 0) {
                    window.alert("密码修改成功，请重新登录!")
                    window.location.href = '${pageContext.request.contextPath}/login.jsp'
                } else if (err_code === 15) {
                    window.alert("原始密码输入错误，请重新输入!")
                    // window.location.replace(window.location.href)
                } else if (err_code === 500) {
                    window.alert('服务器忙，请稍后重试!')
                }
            }
        })
        return false;
    })
</script>


<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>修改密码</li>
    </ol>
    <div class="panel-body">
        <form id="modify_form">
            <div class="form-group">
                <label>当前密码</label>
                <input type="password" class="form-control" name="old_password" placeholder="请输入原始密码" autofocus>
            </div>
            <div class="form-group">
                <label>新的密码</label>
                <input type="password" class="form-control" name="new_pass_1" placeholder="请输入新密码">
            </div>
            <div class="form-group">
                <label>确认密码</label>
                <input type="password" class="form-control" name="new_pass_2" placeholder="请确认新密码">
            </div>
            <button type="submit" id="modify_btn" class="btn btn-success">保存</button>
        </form>
    </div>
</div>
</div>

</body>
</html>
