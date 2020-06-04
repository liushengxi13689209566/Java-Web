package com.huarun.OtherStructure;

public class UserAllFaceInfo {
    private String name;
    private String sex;
    private String major_id;
    private String class_id;
    private String identity_card;
    private String addr_img;

    public UserAllFaceInfo(String name, String sex, String major_id, String class_id, String identity_card, String addr_img) {
        this.name = name;
        this.sex = sex;
        this.major_id = major_id;
        this.class_id = class_id;
        this.identity_card = identity_card;
        this.addr_img = addr_img;
    }

    public UserAllFaceInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public String getAddr_img() {
        return addr_img;
    }

    public void setAddr_img(String addr_img) {
        this.addr_img = addr_img;
    }

    @Override
    public String toString() {
        return "UserAllFaceInfo{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", major_id='" + major_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", identity_card='" + identity_card + '\'' +
                ", addr_img='" + addr_img + '\'' +
                '}';
    }
}
