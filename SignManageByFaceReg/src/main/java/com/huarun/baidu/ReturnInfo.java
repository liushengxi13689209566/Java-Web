package com.huarun.baidu;

import org.json.JSONObject;

public class ReturnInfo {
    private JSONObject res;
    private int error_code;

    public ReturnInfo(JSONObject res, int error_code) {
        this.res = res;
        this.error_code = error_code;
    }

    public JSONObject getRes() {
        return res;
    }

    public int getError_code() {
        return error_code;
    }
}
