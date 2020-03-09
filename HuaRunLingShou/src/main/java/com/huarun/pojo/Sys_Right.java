package com.huarun.pojo;

/**
 * 菜单表
 * 营销管理、客户管理、服务管理、统计报表、基础数据和权限管理六个模块；
 * 		营销管理：销售机会管理、客户开发计划；
 * 		客户管理：客户信息管理、客户流失管理；
 * 		服务管理：服务创建、服务分配、服务处理、服务反馈、服务归档；
 * 		统计报表：客户贡献分析、客户构成分析、客户服务分析、客户流失分析；
 * 		基本数据：数据字典管理、查询产品信息、查询库存；
 * 		权限管理：用户管理、角色管理；
 */
public class Sys_Right {
    int right_code;         //菜单编码(主键)
    int right_parent_code;  //父菜单编码
    int right_type;         //菜单类型
    String right_text;      //菜单文本
    String right_url;       //菜单访问路径
    String right_tip;       //菜单提示

    public Sys_Right(int right_code, int right_parent_code,
                     int right_type, String right_text,
                     String right_url, String right_tip) {
        this.right_code = right_code;
        this.right_parent_code = right_parent_code;
        this.right_type = right_type;
        this.right_text = right_text;
        this.right_url = right_url;
        this.right_tip = right_tip;
    }

    public int getRight_code() {
        return right_code;
    }

    public void setRight_code(int right_code) {
        this.right_code = right_code;
    }

    public int getRight_parent_code() {
        return right_parent_code;
    }

    public void setRight_parent_code(int right_parent_code) {
        this.right_parent_code = right_parent_code;
    }

    public int getRight_type() {
        return right_type;
    }

    public void setRight_type(int right_type) {
        this.right_type = right_type;
    }

    public String getRight_text() {
        return right_text;
    }

    public void setRight_text(String right_text) {
        this.right_text = right_text;
    }

    public String getRight_url() {
        return right_url;
    }

    public void setRight_url(String right_url) {
        this.right_url = right_url;
    }

    public String getRight_tip() {
        return right_tip;
    }

    public void setRight_tip(String right_tip) {
        this.right_tip = right_tip;
    }
}
