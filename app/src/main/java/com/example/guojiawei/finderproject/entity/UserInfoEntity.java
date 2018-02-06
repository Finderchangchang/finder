package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guojiawei on 2017/11/20.
 */

public class UserInfoEntity {

    /**
     * code : 1
     * msg : 更新成功
     * data : {"id":"39","registrationid":"1517bfd3f7f97fec6ec","nickname":"此五味","mobile":"18003543318","head_img":"","longitude":"","latitude":"","insert_time":"2017-11-06 15:25:12","@":2,"head_img_y":""}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 39
         * registrationid : 1517bfd3f7f97fec6ec
         * nickname : 此五味
         * mobile : 18003543318
         * head_img :
         * longitude :
         * latitude :
         * insert_time : 2017-11-06 15:25:12
         * @ : 2
         * head_img_y :
         */

        private String id;
        private String registrationid;
        private String nickname;
        private String mobile;
        private String head_img;
        private String longitude;
        private String latitude;
        private String insert_time;
        @SerializedName("@")
        private int _$117; // FIXME check this code
        private String head_img_y;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegistrationid() {
            return registrationid;
        }

        public void setRegistrationid(String registrationid) {
            this.registrationid = registrationid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
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

        public String getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(String insert_time) {
            this.insert_time = insert_time;
        }

        public int get_$117() {
            return _$117;
        }

        public void set_$117(int _$117) {
            this._$117 = _$117;
        }

        public String getHead_img_y() {
            return head_img_y;
        }

        public void setHead_img_y(String head_img_y) {
            this.head_img_y = head_img_y;
        }
    }
}
