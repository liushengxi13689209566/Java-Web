package com.huarun.service;

import com.huarun.pojo.Sys_Role;

import java.util.List;

public interface Sys_RoleService {
    int addsys_role(Sys_Role sys_role);

    //删除角色
    int deletesys_roleById(int id);

    //更新角色
    int updatesys_role(Sys_Role sys_role);

    //查询角色
    Sys_Role querysys_roleById(int id);

    //查询全部的角色
    List<Sys_Role> queryAllsys_role();
}
