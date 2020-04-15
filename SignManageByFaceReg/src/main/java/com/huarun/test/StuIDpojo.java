package com.huarun.test;

public class StuIDpojo {
    private String id;

    public StuIDpojo(String id) {
        this.id = id;
    }

    public StuIDpojo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StuIDpojo{" +
                "id='" + id + '\'' +
                '}';
    }
}
