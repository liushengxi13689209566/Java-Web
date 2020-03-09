package com.huarun.pojo;

/**
 * 角色表
 * 描述：系统管理员、销售主管、客户经理和高管；
 */
public class Sys_Role {
    int role_id;//角色主键
    String role_name;//角色名称
    String role_desc;//角色描述
    int  role_flag;//角色状态（1或0，1表示可用）

    public Sys_Role(int role_id, String role_name, String role_desc, int role_flag) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_desc = role_desc;
        this.role_flag = role_flag;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }

    public int getRole_flag() {
        return role_flag;
    }

    public void setRole_flag(int role_flag) {
        this.role_flag = role_flag;
    }
}
