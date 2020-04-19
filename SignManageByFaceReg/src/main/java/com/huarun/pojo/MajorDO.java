package com.huarun.pojo;

import java.io.Serializable;

public class MajorDO implements Serializable {
    private int major_id;
    private String major_name;
    private String major_desc;

    public MajorDO(int major_id, String major_name, String major_desc) {
        this.major_id = major_id;
        this.major_name = major_name;
        this.major_desc = major_desc;
    }

    public MajorDO() {
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getMajor_desc() {
        return major_desc;
    }

    public void setMajor_desc(String major_desc) {
        this.major_desc = major_desc;
    }

    @Override
    public String toString() {
        return "MajorDO{" +
                "major_id=" + major_id +
                ", major_name='" + major_name + '\'' +
                ", major_desc='" + major_desc + '\'' +
                '}';
    }
}
