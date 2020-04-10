package com.huarun.SignInTerminal.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

public class FaceSignInData implements Serializable {

    @NotNull(message = "传入的人脸照片为 null")
    @NotEmpty(message = "传入的人脸照片为 empty")
    private String faceImage;

    @NotNull(message = "传入的课程ID为 null")
    private int course_id;

    private Timestamp timestamp;

    public FaceSignInData(@NotNull(message = "传入的人脸照片为 null") @NotEmpty(message = "传入的人脸照片为 empty") String faceImage, @NotNull(message = "传入的课程ID为 null") int course_id, Timestamp timestamp) {
        this.faceImage = faceImage;
        this.course_id = course_id;
        this.timestamp = timestamp;
    }

    public FaceSignInData() {
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FaceSignInData{" +
                "faceImage='" + faceImage + '\'' +
                ", course_id=" + course_id +
                ", timestamp=" + timestamp +
                '}';
    }
}
