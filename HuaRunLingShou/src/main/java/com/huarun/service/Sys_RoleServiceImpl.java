package com.huarun.service;

import com.huarun.dao.sys_roleMapper;
import com.huarun.pojo.Sys_Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Sys_RoleServiceImpl implements Sys_RoleService {
    @Autowired
    private sys_roleMapper sys_roleMapper;

    public sys_roleMapper getSys_roleMapper() {
        return sys_roleMapper;
    }

    public void setSys_roleMapper(com.huarun.dao.sys_roleMapper sys_roleMapper) {
        this.sys_roleMapper = sys_roleMapper;
    }

    @Override
    public int addsys_role(Sys_Role sys_role) {
        return 0;
    }

    @Override
    public int deletesys_roleById(int id) {
        return 0;
    }

    @Override
    public int updatesys_role(Sys_Role sys_role) {
        return 0;
    }

    @Override
    public Sys_Role querysys_roleById(int id) {
        return null;
    }

    @Override
    public List<Sys_Role> queryAllsys_role() {
        return null;
    }
}
