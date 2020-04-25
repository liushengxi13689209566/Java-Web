package com.huarun.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.UserInfo;
import com.huarun.service.UserInfoService;
import com.huarun.utils.CheckCodeGenerator;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 用户账户请求 Handler
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private static Logger log = Logger.getLogger("application");

    private static final String USER_ID = "id";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE = "role";
    private String role;

    public static final String STUDENT = "student";
    public static final String TEACHER = "teacher";
    public static final String ADMIN = "admin";

    public String getRole() {
        return role;
    }

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取图形验证码 将返回一个包含4位字符（字母或数字）的图形验证码，并且将图形验证码的值设置到用户的 session 中
     *
     * @param time     时间戳
     * @param response 返回的 HttpServletResponse 响应
     */
    @RequestMapping(value = "checkCode/{time}", method = RequestMethod.GET)
    public void getCheckCode(@PathVariable("time") String time, HttpServletResponse response, HttpServletRequest request) {
//        System.out.println(time);
        BufferedImage checkCodeImage = null;
        String checkCodeString = null;

        // 获取图形验证码，依赖于 util/CheckCodeGenerator
        Map<String, Object> checkCode = CheckCodeGenerator.generlateCheckCode();

//        System.out.println("刘生爱哈哈哈哈");
//        System.out.println(checkCode);

        if (checkCode != null) {
            checkCodeString = (String) checkCode.get("checkCodeString");
            checkCodeImage = (BufferedImage) checkCode.get("checkCodeImage");
        }

        if (checkCodeString != null && checkCodeImage != null) {
            //获取response.getOutputStream()
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                // 设置 Session
                HttpSession session = request.getSession();
                session.setAttribute("checkCode", checkCodeString);

                // 将验证码输出
                ImageIO.write(checkCodeImage, "png", outputStream);

                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setContentType("image/png");
            } catch (IOException e) {
                log.error("fail to get the ServletOutputStream");
            }
        }
    }

    /**
     * 登陆账户
     *
     * @param user 账户信息 传入的密码是前端加密之后的
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public
    @ResponseBody
    void login(@RequestBody Map<String, Object> user, HttpServletResponse response) throws Exception {

        System.out.println(JSON.toJSONString(user));
        role = (String) user.get(USER_ROLE);
        System.out.println("role == " + role);

        JSONObject result = new JSONObject();
        int status_code = 0;
        String msg = "";

        // 获取当前的用户的 Subject，shiro
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser == null) {
            System.out.println("NULLLLLLLL");
        }
        // 判断用户是否已经登陆
//        if (currentUser != null && !currentUser.isAuthenticated()) {

        String id = (String) user.get(USER_ID);
        String password = (String) user.get(USER_PASSWORD);
        UsernamePasswordToken token = new UsernamePasswordToken(id, password);

        // 执行登陆操作
        try {
            //会调用 realms/UserAuthorizingRealm 中的 doGetAuthenticationInfo 方法
            currentUser.login(token);

            // 设置登陆状态并记录
            Session session = currentUser.getSession();
            session.setAttribute("isAuthenticate", "true");

            String userID = (String) session.getAttribute("userID");
            String userName = (String) session.getAttribute("userName");
            String accessIP = session.getHost();

            System.out.println("uservjdfbf,,fj,fb" + userID);
            System.out.println(userName);
            System.out.println(accessIP);

            System.out.println(id);
            System.out.println(password);
            System.out.println(token);

            status_code = StatusCode.SUCCESS;
            msg = "登录成功啦！！！";
            //用户名错误
        } catch (UnknownAccountException e) {
            status_code = StatusCode.UNKNOWN_ACCOUNT;
            msg = "用户名错误";
            //密码错误
        } catch (IncorrectCredentialsException e) {
            status_code = StatusCode.INCORRECT_CREDENTIALS;
            msg = "密码或验证码错误";
            //总的异常
        } catch (AuthenticationException e) {
            status_code = StatusCode.AUTHENTICATION_ERROR;
            msg = "服务器错误";
        }
//        } else {
//            status_code = StatusCode.ALREADY_LOGIN;
//            msg = "对不起，您已经登录";
//        }
        result.put("status_code", status_code);
        result.put("msg", msg);
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    /**
     * 账户退出
     *
     * @return 返回一个 Map 对象，键值为 result 的内容代表注销操作的结果，值为 success 或 error
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response) throws Exception {
        Subject currentSubject = SecurityUtils.getSubject();
        if (currentSubject != null && currentSubject.isAuthenticated()) {
            // 执行账户注销操作
            currentSubject.logout();
            //重定向到 /
            return "redirect:/";
        } else {
            return "";
        }
    }

    /**
     * 修改账户密码
     *
     * @param passwordInfo 密码信息 传入加密后的密码
     * @param request      请求
     * @return 返回一个 Map 对象，其中键值为 result 代表修改密码操作的结果，
     * 值为 success 或 error；键值为 msg 代表需要返回给用户的信息
     */
    @RequestMapping(value = "passwordModify", method = RequestMethod.POST)
    public void passwordModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();

        String msg = null;
        int status_code = 0;

        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");
        System.out.println("role == " + role);
        System.out.println("userID == " + userID);
        System.out.println("old_password == " + request.getParameter("old_password"));
        System.out.println("new_password == " + request.getParameter("new_password"));

        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");

        // 更改密码
        if (role.equals(STUDENT)) {
            UserInfo userInfo = userInfoService.getUserInfoByStuID(userID);
            //输入的旧密码不正确
            if (!userInfo.getPassword().equals(old_password)) {
                result.put("status_code", StatusCode.PASSWORD_INCORRECT);
                result.put("msg", "原始密码输入错误，请重新输入!");
                ResponseUtil.write(response, result);
                return;
            }
            userInfoService.stuPasswordModify(userID, new_password);
        } else if (role.equals(TEACHER)) {
            UserInfo userInfo = userInfoService.getUserInfoByTeaID(userID);
            //输入的旧密码不正确
            if (!userInfo.getPassword().equals(old_password)) {
                result.put("status_code", StatusCode.PASSWORD_INCORRECT);
                result.put("msg", "原始密码输入错误，请重新输入!");
                ResponseUtil.write(response, result);
                return;
            }
            userInfoService.teaPasswordModify(userID, new_password);
        } else {
            UserInfo userInfo = userInfoService.getUserInfoByAdmID(userID);
            //输入的旧密码不正确
            if (!userInfo.getPassword().equals(old_password)) {
                result.put("status_code", StatusCode.PASSWORD_INCORRECT);
                result.put("msg", "原始密码输入错误，请重新输入!");
                ResponseUtil.write(response, result);
                return;
            }
            userInfoService.admPasswordModify(userID, new_password);
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }
}
