package com.huarun.pojo;

public class SignCase {
    private int sign_case_id;
    private int course_id;
    private String student_id;
    private String sign_case_bitmap;

    public SignCase(int sign_case_id, int course_id, String student_id, String sign_case_bitmap) {
        this.sign_case_id = sign_case_id;
        this.course_id = course_id;
        this.student_id = student_id;
        this.sign_case_bitmap = sign_case_bitmap;
    }

    public SignCase() {
    }

    public int getSign_case_id() {
        return sign_case_id;
    }

    public void setSign_case_id(int sign_case_id) {
        this.sign_case_id = sign_case_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSign_case_bitmap() {
        return sign_case_bitmap;
    }

    public void setSign_case_bitmap(String sign_case_bitmap) {
        this.sign_case_bitmap = sign_case_bitmap;
    }

    @Override
    public String toString() {
        return "SignCase{" +
                "sign_case_id=" + sign_case_id +
                ", course_id=" + course_id +
                ", student_id='" + student_id + '\'' +
                ", sign_case_bitmap='" + sign_case_bitmap + '\'' +
                '}';
    }
}
