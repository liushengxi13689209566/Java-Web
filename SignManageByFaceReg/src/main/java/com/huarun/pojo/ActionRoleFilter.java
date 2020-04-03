package com.huarun.pojo;

import java.io.Serializable;

public class ActionRoleFilter implements Serializable {
    private int action_id;
    private String action_url;
    private String action_filter;
    private String action_desc;
    private String action_role;

    public ActionRoleFilter() {
    }

    public ActionRoleFilter(int action_id, String action_url, String action_filter, String action_desc, String action_role) {
        this.action_id = action_id;
        this.action_url = action_url;
        this.action_filter = action_filter;
        this.action_desc = action_desc;
        this.action_role = action_role;
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public String getAction_url() {
        return action_url;
    }

    public void setAction_url(String action_url) {
        this.action_url = action_url;
    }

    public String getAction_filter() {
        return action_filter;
    }

    public void setAction_filter(String action_filter) {
        this.action_filter = action_filter;
    }

    public String getAction_desc() {
        return action_desc;
    }

    public void setAction_desc(String action_desc) {
        this.action_desc = action_desc;
    }

    public String getAction_role() {
        return action_role;
    }

    public void setAction_role(String action_role) {
        this.action_role = action_role;
    }

    @Override
    public String toString() {
        return "ActionRoleFilter{" +
                "action_id=" + action_id +
                ", action_url='" + action_url + '\'' +
                ", action_filter='" + action_filter + '\'' +
                ", action_desc='" + action_desc + '\'' +
                ", action_role='" + action_role + '\'' +
                '}';
    }
}
