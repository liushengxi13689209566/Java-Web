package com.huarun.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int pri_id;
    private String id;
    private String name;
    private String password;

    public UserInfo(int pri_id, String id, String name, String password) {
        this.pri_id = pri_id;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserInfo() {
    }

    public int getPri_id() {
        return pri_id;
    }

    public void setPri_id(int pri_id) {
        this.pri_id = pri_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "pri_id=" + pri_id +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
