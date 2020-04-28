package com.huarun.OtherStructure;

import com.huarun.pojo.StudentDO;

public class OneDayClassSignCaseRecord {
    private int number;
    private String id;
    private String name;
    private String sex;
    private char sign_case_flag;

    public OneDayClassSignCaseRecord(int number, String id, String name, String sex, char sign_case_flag) {
        this.number = number;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.sign_case_flag = sign_case_flag;
    }

    public OneDayClassSignCaseRecord(int number, StudentDO studentDO, char sign_case_flag) {
        this.number = number;
        this.id = studentDO.getId();
        this.name = studentDO.getName();
        this.sex = studentDO.getSex();
        this.sign_case_flag = sign_case_flag;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public char getSign_case_flag() {
        return sign_case_flag;
    }

    public void setSign_case_flag(char sign_case_flag) {
        this.sign_case_flag = sign_case_flag;
    }

    @Override
    public String toString() {
        return "OneDayClassSignCaseRecord{" +
                "number=" + number +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", sign_case_flag=" + sign_case_flag +
                '}';
    }
}
