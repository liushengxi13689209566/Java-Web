package com.huarun.baidu;


import com.baidu.aip.face.AipFace;

public class AiFaceObject {
    //设置APPID/AK/SK
    private static String APP_ID = "18754642";
    private static String API_KEY = "i3m7miF047QRKLudmVWU61SX";
    private static String SECRET_KEY = "0bNBrPSpT0rTndGduFa1cq8qQtk5XmXv";
    //这上面的东西在你申请百度接口的时候 都会给的

    //创建一个aipface对象
    private static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

    //创建单例的原因是避免多次获取sdk

    public static AipFace getClient() {
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

}

