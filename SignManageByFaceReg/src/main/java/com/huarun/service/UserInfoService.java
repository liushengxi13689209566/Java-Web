package com.huarun.service;

import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息 service
 */
public interface UserInfoService {

    /**
     * 获取指定用户ID对应的用户账户信息
     *
     * @param userID 用户ID,
     * @return 返回用户账户信息
     */
    UserInfo getUserInfoByStuID(String userID);

    UserInfo getUserInfoByTeaID(String userID);

    UserInfo getUserInfoByAdmID(String userID);

    // 修改密码
    void stuPasswordModify(@Param("userID") String userID, @Param("new_pass") String new_pass);

    void teaPasswordModify(@Param("userID") String userID, @Param("new_pass") String new_pass);

    void admPasswordModify(@Param("userID") String userID, @Param("new_pass") String new_pass);

//    /**
//     * 获取指定 userName 对应的用户账户信息
//     *
//     * @param userName 用户名
//     * @return 返回用户账户信息
//     */
//    UserInfo getUserInfo(String userName);
//
//    /**
//     * 获取所有用户账户信息
//     *
//     * @return 返回所有的用户账户信息
//     */
//    List<UserInfo> getAllUserInfo();
//
//    /**
//     * 更新用户的账户信息
//     *
//     * @param UserInfo 用户账户信息
//     */
//    void updateUserInfo(UserInfo UserInfo);
//
//    /**
//     * 删除指定 userID 的用户账户信息
//     *
//     * @param userID 指定的用户ID
//     */
//    void deleteUserInfo(Integer userID);
//
//    /**
//     * 添加一条用户账户信息
//     *
//     * @param UserInfo 需要添加的用户账户信息
//     */
//    boolean insertUserInfo(UserInfo userInfoDTO);
//
//    /**
//     * 获取用户的权限角色
//     *
//     * @param userID 用户 ID
//     * @return 返回一个保存有用户角色的 Set，若该用户没有任何角色，则返回一个不包含任何元素的 Set
//     */
//    Set<String> getUserRoles(Integer userID);
}

