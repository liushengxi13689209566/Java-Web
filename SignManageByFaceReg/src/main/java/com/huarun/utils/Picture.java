package com.huarun.utils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Picture {

    @NotNull(message = "传入的人脸照片为 null")
    @NotEmpty(message = "传入的人脸照片为 empty")
    private String image;
    private String imageType;

    public Picture(@NotNull(message = "传入的人脸照片为 null") @NotEmpty(message = "传入的人脸照片为 empty") String image, String imageType) {
        this.image = image;
        this.imageType = imageType;
    }

    public Picture() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                '}';
    }

    public boolean isEmpty() {
        return image.isEmpty();
    }
}
