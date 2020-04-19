package com.huarun.utils;


//    人脸的类型
//    LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
//    IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片
//    WATERMARK 表示带水印证件照：一般为带水印的小图，如公安网小图
//    CERT 表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
//            默认LIVE
public enum FacePictureType {

    LIVE("LIVE", 1),
    IDCARD("IDCARD", 2),
    WATERMARK("WATERMARK", 3),
    CERT("CERT", 4);

    private String type;
    private int index;

    FacePictureType(String type, int index) {
        this.type = type;
        this.index = index;
    }

    FacePictureType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "FacePictureType{" +
                "type='" + type + '\'' +
                ", index=" + index +
                '}';
    }
}
