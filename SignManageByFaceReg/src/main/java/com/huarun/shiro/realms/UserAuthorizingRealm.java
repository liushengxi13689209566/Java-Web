package com.huarun.shiro.realms;

import com.huarun.controller.AccountController;
import com.huarun.pojo.UserInfo;
import com.huarun.service.UserInfoService;
import com.huarun.utils.EncryptingModel;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户的认证与授权
 */
public class UserAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AccountController accountController;

    public static final String STUDENT = "student";
    public static final String TEACHER = "teacher";
    public static final String ADMIN = "admin";

    /**
     * 对用户进行角色授权
     *
     * @param principalCollection 用户信息
     * @return 返回用户授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("当前登录的角色是：" + accountController.getRole());

        // 创建存放用户角色的 Set
        Set<String> roles = new HashSet<>();
        roles.add(accountController.getRole());
        return new SimpleAuthorizationInfo(roles);
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 用户凭证
     * @return 返回用户的认证信息
     * @throws AuthenticationException 用户认证异常信息
     *                                 Realm的认证方法，自动将token传入，比较token与数据库的数据是否匹配
     *                                 验证逻辑是先根据用户名查询用户，
     *                                 如果查询到的话再将查询到的用户名和密码放到SimpleAuthenticationInfo对象中，
     *                                 Shiro会自动根据用户输入的密码和查询到的密码进行匹配，如果匹配不上就会抛出异常，
     *                                 匹配上之后就会执行 doGetAuthorizationInfo() 进行相应的权限验证。
     */
    //1. 把AuthenticationToken 转换为UsernamePasswordToken
    //2.从UsernamePasswordToken 中来获取username
    //3. 调用数据 库的方法，从数据库中查询 username 对应的用户记录
    //4.若用户不存在，则可以抛出 UnknownAccountException异常
    //5. 根据用户信息的情况，决定是否需要抛出其他的 AuthenticationException 异常。
    //6. 根据用户的情况，来构建 AuthenticationInfo 对象并返回.
    @SuppressWarnings("unchecked")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(" authenticationToken ====" + authenticationToken);
        System.out.println("当前登录的角色是：" + accountController.getRole());
        String credentials = "";

        // 获取用户名对应的用户账户信息
        try {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
            String userID = usernamePasswordToken.getUsername();
            System.out.println("username == " + userID);
            System.out.println("password == " + usernamePasswordToken.getPassword());

            if (!StringUtils.isNumeric(userID)) {
                throw new UnknownAccountException("用户不存在");
            }

            //业务层进行区分！！！
            UserInfo userInfo;
            if (accountController.getRole().equals(STUDENT)) {
                userInfo = userInfoService.getUserInfoByStuID(userID);
            } else if (accountController.getRole().equals(TEACHER)) {
                userInfo = userInfoService.getUserInfoByTeaID(userID);
            } else {
                userInfo = userInfoService.getUserInfoByAdmID(userID);
            }
            System.out.println(userInfo);

            if (userInfo == null) {
                throw new UnknownAccountException("用户不存在");
            }

            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();

            // 设置部分用户信息到 Session
            session.setAttribute("userID", userID);
            session.setAttribute("userName", userInfo.getName());

            // 结合验证码对密码进行处理
            String checkCode = (String) session.getAttribute("checkCode");
            String password = userInfo.getPassword();
            String id = userInfo.getId();

            if (checkCode != null && password != null) {
                checkCode = checkCode.toUpperCase();
                credentials = EncryptingModel.MD5(id + password + checkCode);
                System.out.println("credent ==== " + credentials);
            }
            //比对账号密码
            //principal 前端传来 userid
            //credentials 为数据库的密码，加 checkcode 和 user_id 的 MD5 加密
            //realmName为 com.huarun.security.realms.UserAuthorizingRealm_0
            return new SimpleAuthenticationInfo(userID, credentials, getName());

        } catch (NullPointerException | NoSuchAlgorithmException e) {
            throw new AuthenticationException();
        }
    }
}
