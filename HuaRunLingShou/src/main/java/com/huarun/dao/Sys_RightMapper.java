package com.huarun.dao;

import com.huarun.pojo.Sys_Right;
import com.huarun.pojo.Sys_Role;

import java.util.List;

public interface Sys_RightMapper {
    //增加角色
    int addsys_right(Sys_Right sys_right);

    //删除角色
    int deletesys_rightById(int id);

    //更新角色
    int updatesys_right(Sys_Right sys_right);

    //查询角色
    Sys_Right querysys_rightById(int id);

    //查询全部的角色
    List<Sys_Right> queryAllsys_right();
}
