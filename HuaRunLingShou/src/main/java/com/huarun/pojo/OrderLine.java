package com.huarun.pojo;

public class OrderLine {
    private int oddID;//系统自动生成(标识列、主键);
    private int oddOrderID;//订单编号（cst_order表odr_id）
    private int oddProdID;//产品编号（pro_product表prod_id）
    private int oddCount;//数量
    private String oddUnit;//单位
    private double oddPrice;//价格

    public OrderLine(int oddID, int oddOrderID, int oddProdID, int oddCount, String oddUnit, double oddPrice) {
        this.oddID = oddID;
        this.oddOrderID = oddOrderID;
        this.oddProdID = oddProdID;
        this.oddCount = oddCount;
        this.oddUnit = oddUnit;
        this.oddPrice = oddPrice;
    }

    public OrderLine() {
    }

    public int getOddID() {
        return oddID;
    }

    public void setOddID(int oddID) {
        this.oddID = oddID;
    }

    public int getOddOrderID() {
        return oddOrderID;
    }

    public void setOddOrderID(int oddOrderID) {
        this.oddOrderID = oddOrderID;
    }

    public int getOddProdID() {
        return oddProdID;
    }

    public void setOddProdID(int oddProdID) {
        this.oddProdID = oddProdID;
    }

    public int getOddCount() {
        return oddCount;
    }

    public void setOddCount(int oddCount) {
        this.oddCount = oddCount;
    }

    public String getOddUnit() {
        return oddUnit;
    }

    public void setOddUnit(String oddUnit) {
        this.oddUnit = oddUnit;
    }

    public double getOddPrice() {
        return oddPrice;
    }

    public void setOddPrice(double oddPrice) {
        this.oddPrice = oddPrice;
    }
}
