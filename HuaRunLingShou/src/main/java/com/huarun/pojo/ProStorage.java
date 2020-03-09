package com.huarun.pojo;

public class ProStorage {
    private int stkID;  //(库存编号)系统自动生成(标识列、主键)
    private int stkProdID; //产品编号（pro_product表prod_id）
    private String stkWareHouse;//仓库
    private String stkWare;//货位
    private int stkCount; //件数
    private String stkMemo;//备注

    public ProStorage() {
    }

    public ProStorage(int stkID, int stkProdID, String stkWareHouse, String stkWare, int stkCount, String stkMemo) {
        this.stkID = stkID;
        this.stkProdID = stkProdID;
        this.stkWareHouse = stkWareHouse;
        this.stkWare = stkWare;
        this.stkCount = stkCount;
        this.stkMemo = stkMemo;
    }

    public int getStkID() {
        return stkID;
    }

    public void setStkID(int stkID) {
        this.stkID = stkID;
    }

    public String getstkWareHouse() {
        return stkWareHouse;
    }

    public void setstkWareHouse(String stkWareHouse) {
        this.stkWareHouse = stkWareHouse;
    }

    public int getStkCount() {
        return stkCount;
    }

    public void setStkCount(int stkCount) {
        this.stkCount = stkCount;
    }

    public int getStkProdID() {
        return stkProdID;
    }

    public void setStkProdID(int stkProdID) {
        this.stkProdID = stkProdID;
    }

    public String getStkWare() {
        return stkWare;
    }

    public void setStkWare(String stkWare) {
        this.stkWare = stkWare;
    }

    public String getStkMemo() {
        return stkMemo;
    }

    public void setStkMemo(String stkMemo) {
        this.stkMemo = stkMemo;
    }
}
