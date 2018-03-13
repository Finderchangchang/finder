package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Finder丶畅畅 on 2018/3/14 07:44
 * QQ群481606175
 */

public class MapModel {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"0":{"gjuli":"1670","user_id":"16","img":"http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg","longitude":"116.405542611079","latitude":"39.91435106561615"},"1":{"gjuli":"1909","user_id":"183","img":"http://img.zl-finder.com/mood/20171121/20171121134120_91652.jpeg","longitude":"116.417115","latitude":"39.886376"}}
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * gjuli : 1670
         * user_id : 16
         * img : http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg
         * longitude : 116.405542611079
         * latitude : 39.91435106561615
         */

        private String gjuli;
        private String user_id;
        private String img;
        private String longitude;
        private String latitude;

        public String getGjuli() {
            return gjuli;
        }

        public void setGjuli(String gjuli) {
            this.gjuli = gjuli;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
