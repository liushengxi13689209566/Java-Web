package com.huarun.OtherStructure;

public class CourseSignCaseKK {
    //尽量保证一下 key 的简单，提高执行效率
    private int major_id;
    private int class_id;

    public CourseSignCaseKK(int major_id, int class_id) {
        this.major_id = major_id;
        this.class_id = class_id;
    }

    public CourseSignCaseKK() {
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "CourseSignCaseKK{" +
                "major_id=" + major_id +
                ", class_id=" + class_id +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + major_id;
        result = 31 * result + class_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CourseSignCaseKK)) {
            return false;
        }
        CourseSignCaseKK pn = (CourseSignCaseKK) obj;
        return pn.major_id == major_id && pn.class_id == class_id;
    }
}
