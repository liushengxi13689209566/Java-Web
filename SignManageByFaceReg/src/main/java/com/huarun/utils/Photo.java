package com.huarun.utils;

public class Photo {
    private String image;
    private String imageType;

    public Photo(String image, String imageType) {
        this.image = image;
        this.imageType = imageType;
    }

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }
}
