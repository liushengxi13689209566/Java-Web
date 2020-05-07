package com.huarun.OtherStructure;


public class CourseTimeStamp {
    //课程序号
    private String id;
    private String course_start_timestamp;
    private String course_end_timestamp;

    public CourseTimeStamp(String id, String course_start_timestamp, String course_end_timestamp) {
        this.id = id;
        this.course_start_timestamp = course_start_timestamp;
        this.course_end_timestamp = course_end_timestamp;
    }

    public CourseTimeStamp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_start_timestamp() {
        return course_start_timestamp;
    }

    public void setCourse_start_timestamp(String course_start_timestamp) {
        this.course_start_timestamp = course_start_timestamp;
    }

    public String getCourse_end_timestamp() {
        return course_end_timestamp;
    }

    public void setCourse_end_timestamp(String course_end_timestamp) {
        this.course_end_timestamp = course_end_timestamp;
    }

    //    HashSet是如何保证元素唯一性的呢？
//    是通过元素的两个方法，hashCode和equals来完成。
//    如果元素的HashCode值相同，才会判断equals是否为true。
//    如果元素的hashcode值不同，不会调用equals。
    @Override
    public int hashCode() {
        return 666;
    }

    //所有的比较都进入这里
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CourseTimeStamp)) {
            return false;
        }
        CourseTimeStamp pn = (CourseTimeStamp) obj;
        // 这里是 或者 的关系。因为两节课必然不会重复在一起
        return pn.course_start_timestamp.equals(course_start_timestamp) || pn.course_end_timestamp.equals(course_end_timestamp);
    }

    @Override
    public String toString() {
        return "CourseTimeStamp{" +
                "id='" + id + '\'' +
                ", course_start_timestamp=" + course_start_timestamp +
                ", course_end_timestamp=" + course_end_timestamp +
                '}';
    }
}
