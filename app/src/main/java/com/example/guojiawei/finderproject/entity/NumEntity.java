package com.example.guojiawei.finderproject.entity;

/**
 * Created by guojiawei on 2017/11/23.
 */

public class NumEntity {

    /**
     * code : 1
     * msg : 获取成功
     * data : 10
     */

    private int code;
    private String msg;
    private String data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
