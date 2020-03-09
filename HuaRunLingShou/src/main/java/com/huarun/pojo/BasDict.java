package com.huarun.pojo;

import java.util.BitSet;

public class BasDict {
    private int dictID;    //bigint		系统自动生成(标识列、主键)
    private String dictType;//	nvarchar	50	数据字典类别
    private String dictItem;//	  nvarchar	50	数据字典条目
    private String dictValue;//	nvarchar	50	数据字典数据（所要得到的值）
    private Boolean dictIsEditable; //	bit		是否可以添加（1、是。0、否。）

    public BasDict(int dictID, String dictType, String dictItem, String dictValue, Boolean dictIsEditable) {
        this.dictID = dictID;
        this.dictType = dictType;
        this.dictItem = dictItem;
        this.dictValue = dictValue;
        this.dictIsEditable = dictIsEditable;
    }

    public BasDict() {

    }

    public int getDictID() {
        return dictID;
    }

    public void setDictID(int dictID) {
        this.dictID = dictID;
    }

    public String getdictType() {
        return dictType;
    }

    public void setdictType(String dictType) {
        this.dictType = dictType;
    }

    public String getdictItem() {
        return dictItem;
    }

    public void setdictItem(String dictItem) {
        this.dictItem = dictItem;
    }

    public String getdictValue() {
        return dictValue;
    }

    public void setdictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public Boolean getdictIsEditable() {
        return dictIsEditable;
    }

    public void setdictIsEditable(Boolean dictIsEditable) {
        this.dictIsEditable = dictIsEditable;
    }
}
