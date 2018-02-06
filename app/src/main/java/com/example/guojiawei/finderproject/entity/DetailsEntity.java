package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guojiawei on 2017/11/22.
 */

public class DetailsEntity {
    private HeadEntity headEntity;

    private MessEntity.DataBean messageEntities;


    public HeadEntity getHeadEntity() {
        return headEntity;
    }

    public void setHeadEntity(HeadEntity headEntity) {
        this.headEntity = headEntity;
    }

    public MessEntity.DataBean getMessageEntities() {
        return messageEntities;
    }

    public void setMessageEntities(MessEntity.DataBean messageEntities) {
        this.messageEntities = messageEntities;
    }

    public static class HeadEntity{

        /**
         * code : 1
         * msg : 获取成功
         * data : {"id":"394","user_id":"161","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171119/20171119160424_46161.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=z8Xln7eOskIGA1tBcVj1Ucq2x64%3D","content":"园林还看我大苏州","longitude":"120.635532","latitude":"31.330229","comment_num":"0","thing_num":"4","inper":"admin","insert_time":"19日 12:13","sort":"0","del":"0","juli":"71218","gjuli":"71218","user":{"id":"161","nickname":"鬼魅","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119115308_69556.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=VXttFWBiqVeQVRB7KulqYsU4KtQ%3D","@":0,"head_img_y":"http://img.zl-finder.com/user/20171119/20171119115308_69556.jpeg"},"img_y":"http://img.zl-finder.com/mood/20171119/20171119160424_46161.jpeg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171119/20171119160424_46161.jpeg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=%2BZzIy1dzmBhWqt0UWD2rlGkHNuo%3D","thing":"0"}
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
             * id : 394
             * user_id : 161
             * img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171119/20171119160424_46161.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=z8Xln7eOskIGA1tBcVj1Ucq2x64%3D
             * content : 园林还看我大苏州
             * longitude : 120.635532
             * latitude : 31.330229
             * comment_num : 0
             * thing_num : 4
             * inper : admin
             * insert_time : 19日 12:13
             * sort : 0
             * del : 0
             * juli : 71218
             * gjuli : 71218
             * user : {"id":"161","nickname":"鬼魅","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119115308_69556.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=VXttFWBiqVeQVRB7KulqYsU4KtQ%3D","@":0,"head_img_y":"http://img.zl-finder.com/user/20171119/20171119115308_69556.jpeg"}
             * img_y : http://img.zl-finder.com/mood/20171119/20171119160424_46161.jpeg
             * img_s : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171119/20171119160424_46161.jpeg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=%2BZzIy1dzmBhWqt0UWD2rlGkHNuo%3D
             * thing : 0
             */

            private String id;
            private String user_id;
            private String img;
            private String content;
            private String longitude;
            private String latitude;
            private String comment_num;
            private String thing_num;
            private String inper;
            private String insert_time;
            private String sort;
            private String del;
            private String juli;
            private String gjuli;
            private UserBean user;
            private String img_y;

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            private String img_s;
            private String thing;
            private String video_id;
            private String image_url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getThing_num() {
                return thing_num;
            }

            public void setThing_num(String thing_num) {
                this.thing_num = thing_num;
            }

            public String getInper() {
                return inper;
            }

            public void setInper(String inper) {
                this.inper = inper;
            }

            public String getInsert_time() {
                return insert_time;
            }

            public void setInsert_time(String insert_time) {
                this.insert_time = insert_time;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getDel() {
                return del;
            }

            public void setDel(String del) {
                this.del = del;
            }

            public String getJuli() {
                return juli;
            }

            public void setJuli(String juli) {
                this.juli = juli;
            }

            public String getGjuli() {
                return gjuli;
            }

            public void setGjuli(String gjuli) {
                this.gjuli = gjuli;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getImg_y() {
                return img_y;
            }

            public void setImg_y(String img_y) {
                this.img_y = img_y;
            }

            public String getImg_s() {
                return img_s;
            }

            public void setImg_s(String img_s) {
                this.img_s = img_s;
            }

            public String getThing() {
                return thing;
            }

            public void setThing(String thing) {
                this.thing = thing;
            }

            public static class UserBean {
                /**
                 * id : 161
                 * nickname : 鬼魅
                 * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119115308_69556.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511337571&Signature=VXttFWBiqVeQVRB7KulqYsU4KtQ%3D
                 * @ : 0
                 * head_img_y : http://img.zl-finder.com/user/20171119/20171119115308_69556.jpeg
                 */

                private String id;
                private String nickname;
                private String head_img;
                @SerializedName("@")
                private int _$93; // FIXME check this code
                private String head_img_y;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getHead_img() {
                    return head_img;
                }

                public void setHead_img(String head_img) {
                    this.head_img = head_img;
                }

                public int get_$93() {
                    return _$93;
                }

                public void set_$93(int _$93) {
                    this._$93 = _$93;
                }

                public String getHead_img_y() {
                    return head_img_y;
                }

                public void setHead_img_y(String head_img_y) {
                    this.head_img_y = head_img_y;
                }
            }
        }
    }

    public static class MessEntity{

        /**
         * code : 1
         * msg : 获取成功
         * data : {"offset":0,"sort":"id","order":"desc","total":"73","page":" ","row":5,"rows":[{"id":"520","mood_id":"343","user_id":"39","comment_id":"418","user_re_id":"19","content":"嗯","inper":"","insert_time":"15:24","sort":"0","show":"0","del":"0","user":{"id":"39","nickname":"笑哈哈哈哈","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=3UaDzpwZWWzrsGYBYCr0ndmPOyc%3D","@":3,"head_img_y":"http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg"},"user_re":{"id":"19","nickname":"Fred","head_img":"","@":3,"head_img_y":""},"mood":{"id":"343","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=Xd4Z%2BmXLBQ98%2F%2Fr90okAryLEw14%3D","content":"你好，finder\u2026","comment_num":"3","thing_num":"11","img_y":"http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=uYyaEL4uvUyA4Fh0Yp6JgyuOS8w%3D"}},{"id":"519","mood_id":"455","user_id":"143","comment_id":"517","user_re_id":"16","content":"自己的，无所谓","inper":"","insert_time":"21日 18:50","sort":"0","show":"1","del":"0","user":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=el76jjeT15N4Vo1fz93YYae%2FOcU%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"user_re":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"mood":{"id":"455","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=IqwiYRgbLY7dJptgPJWyGal2WcY%3D","content":"一览众山小","comment_num":"1","thing_num":"1","img_y":"http://img.zl-finder.com/mood/20171121/20171121160533_68713.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=wjHsfa6XKS2EDMSfA6RWCy3WCA8%3D"}},{"id":"518","mood_id":"466","user_id":"16","comment_id":"0","user_re_id":"213","content":"666","inper":"","insert_time":"21日 17:29","sort":"0","show":"0","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"user_re":{"id":"213","nickname":"拆散的谎言","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171121/20171121164935_24872.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=JXzKAKTxZ5wVZxGO1cZ2wtu%2Bft4%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171121/20171121164935_24872.jpeg"},"mood":{"id":"466","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121170057_18547.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=eULc9ulXsu%2F%2FKVtcQjgT37HoGGE%3D","content":"我不知道你知不知道我知道你不知道😄","comment_num":"1","thing_num":"1","img_y":"http://img.zl-finder.com/mood/20171121/20171121170057_18547.jpeg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121170057_18547.jpeg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=llXh8%2BSv0s%2FC%2BlHRaYKN6t%2FQVb8%3D"}},{"id":"517","mood_id":"455","user_id":"16","comment_id":"0","user_re_id":"143","content":"你上山免费吧 呵呵","inper":"","insert_time":"21日 16:06","sort":"0","show":"1","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"user_re":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=el76jjeT15N4Vo1fz93YYae%2FOcU%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"mood":{"id":"455","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=IqwiYRgbLY7dJptgPJWyGal2WcY%3D","content":"一览众山小","comment_num":"1","thing_num":"1","img_y":"http://img.zl-finder.com/mood/20171121/20171121160533_68713.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=wjHsfa6XKS2EDMSfA6RWCy3WCA8%3D"}},{"id":"516","mood_id":"425","user_id":"16","comment_id":"0","user_re_id":"180","content":"❤","inper":"","insert_time":"21日 15:27","sort":"0","show":"0","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"user_re":{"id":"180","nickname":"逆倒尘光","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171120/20171120165705_15083.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=LCmHvtfiuhN6R4qJJ9m%2Fgvr7DCE%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171120/20171120165705_15083.jpeg"},"mood":{"id":"425","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171120/20171120174256_58141.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=Z88Eko%2BaWQLopk0NDDkucgyLbbk%3D","content":"仙🐦","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171120/20171120174256_58141.jpeg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171120/20171120174256_58141.jpeg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=NvvMKNmWE2x9cjThozcYFdafdoY%3D"}}]}
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
             * offset : 0
             * sort : id
             * order : desc
             * total : 73
             * page :
             * row : 5
             * rows : [{"id":"520","mood_id":"343","user_id":"39","comment_id":"418","user_re_id":"19","content":"嗯","inper":"","insert_time":"15:24","sort":"0","show":"0","del":"0","user":{"id":"39","nickname":"笑哈哈哈哈","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=3UaDzpwZWWzrsGYBYCr0ndmPOyc%3D","@":3,"head_img_y":"http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg"},"user_re":{"id":"19","nickname":"Fred","head_img":"","@":3,"head_img_y":""},"mood":{"id":"343","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=Xd4Z%2BmXLBQ98%2F%2Fr90okAryLEw14%3D","content":"你好，finder\u2026","comment_num":"3","thing_num":"11","img_y":"http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=uYyaEL4uvUyA4Fh0Yp6JgyuOS8w%3D"}},{"id":"519","mood_id":"455","user_id":"143","comment_id":"517","user_re_id":"16","content":"自己的，无所谓","inper":"","insert_time":"21日 18:50","sort":"0","show":"1","del":"0","user":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=el76jjeT15N4Vo1fz93YYae%2FOcU%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"user_re":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"mood":{"id":"455","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=IqwiYRgbLY7dJptgPJWyGal2WcY%3D","content":"一览众山小","comment_num":"1","thing_num":"1","img_y":"http://img.zl-finder.com/mood/20171121/20171121160533_68713.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=wjHsfa6XKS2EDMSfA6RWCy3WCA8%3D"}},{"id":"518","mood_id":"466","user_id":"16","comment_id":"0","user_re_id":"213","content":"666","inper":"","insert_time":"21日 17:29","sort":"0","show":"0","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"user_re":{"id":"213","nickname":"拆散的谎言","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171121/20171121164935_24872.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=JXzKAKTxZ5wVZxGO1cZ2wtu%2Bft4%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171121/20171121164935_24872.jpeg"},"mood":{"id":"466","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121170057_18547.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=eULc9ulXsu%2F%2FKVtcQjgT37HoGGE%3D","content":"我不知道你知不知道我知道你不知道😄","comment_num":"1","thing_num":"1","img_y":"http://img.zl-finder.com/mood/20171121/20171121170057_18547.jpeg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121170057_18547.jpeg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=llXh8%2BSv0s%2FC%2BlHRaYKN6t%2FQVb8%3D"}},{"id":"517","mood_id":"455","user_id":"16","comment_id":"0","user_re_id":"143","content":"你上山免费吧 呵呵","inper":"","insert_time":"21日 16:06","sort":"0","show":"1","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"user_re":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=el76jjeT15N4Vo1fz93YYae%2FOcU%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"mood":{"id":"455","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=IqwiYRgbLY7dJptgPJWyGal2WcY%3D","content":"一览众山小","comment_num":"1","thing_num":"1","img_y":"http://img.zl-finder.com/mood/20171121/20171121160533_68713.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121160533_68713.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=wjHsfa6XKS2EDMSfA6RWCy3WCA8%3D"}},{"id":"516","mood_id":"425","user_id":"16","comment_id":"0","user_re_id":"180","content":"❤","inper":"","insert_time":"21日 15:27","sort":"0","show":"0","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=XEdRLc3erQgcxcRweFJKFLMwmZ8%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171027/20171027123531_38111.jpg"},"user_re":{"id":"180","nickname":"逆倒尘光","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171120/20171120165705_15083.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=LCmHvtfiuhN6R4qJJ9m%2Fgvr7DCE%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171120/20171120165705_15083.jpeg"},"mood":{"id":"425","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171120/20171120174256_58141.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=Z88Eko%2BaWQLopk0NDDkucgyLbbk%3D","content":"仙🐦","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171120/20171120174256_58141.jpeg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171120/20171120174256_58141.jpeg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=NvvMKNmWE2x9cjThozcYFdafdoY%3D"}}]
             */

            private int offset;
            private String sort;
            private String order;
            private String total;
            private String page;
            private int row;
            private List<RowsBean> rows;

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * id : 520
                 * mood_id : 343
                 * user_id : 39
                 * comment_id : 418
                 * user_re_id : 19
                 * content : 嗯
                 * inper :
                 * insert_time : 15:24
                 * sort : 0
                 * show : 0
                 * del : 0
                 * user : {"id":"39","nickname":"笑哈哈哈哈","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=3UaDzpwZWWzrsGYBYCr0ndmPOyc%3D","@":3,"head_img_y":"http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg"}
                 * user_re : {"id":"19","nickname":"Fred","head_img":"","@":3,"head_img_y":""}
                 * mood : {"id":"343","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=Xd4Z%2BmXLBQ98%2F%2Fr90okAryLEw14%3D","content":"你好，finder\u2026","comment_num":"3","thing_num":"11","img_y":"http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=uYyaEL4uvUyA4Fh0Yp6JgyuOS8w%3D"}
                 */

                private String id;
                private String mood_id;
                private String user_id;
                private String comment_id;
                private String user_re_id;
                private String content;
                private String inper;
                private String insert_time;
                private String sort;
                private String show;
                private String del;
                private UserBean user;
                private UserReBean user_re;
                private MoodBean mood;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMood_id() {
                    return mood_id;
                }

                public void setMood_id(String mood_id) {
                    this.mood_id = mood_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getComment_id() {
                    return comment_id;
                }

                public void setComment_id(String comment_id) {
                    this.comment_id = comment_id;
                }

                public String getUser_re_id() {
                    return user_re_id;
                }

                public void setUser_re_id(String user_re_id) {
                    this.user_re_id = user_re_id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getInper() {
                    return inper;
                }

                public void setInper(String inper) {
                    this.inper = inper;
                }

                public String getInsert_time() {
                    return insert_time;
                }

                public void setInsert_time(String insert_time) {
                    this.insert_time = insert_time;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public String getDel() {
                    return del;
                }

                public void setDel(String del) {
                    this.del = del;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public UserReBean getUser_re() {
                    return user_re;
                }

                public void setUser_re(UserReBean user_re) {
                    this.user_re = user_re;
                }

                public MoodBean getMood() {
                    return mood;
                }

                public void setMood(MoodBean mood) {
                    this.mood = mood;
                }

                public static class UserBean {
                    /**
                     * id : 39
                     * nickname : 笑哈哈哈哈
                     * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=3UaDzpwZWWzrsGYBYCr0ndmPOyc%3D
                     * @ : 3
                     * head_img_y : http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg
                     */

                    private String id;
                    private String nickname;
                    private String head_img;
                    @SerializedName("@")
                    private int _$102; // FIXME check this code
                    private String head_img_y;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getHead_img() {
                        return head_img;
                    }

                    public void setHead_img(String head_img) {
                        this.head_img = head_img;
                    }

                    public int get_$102() {
                        return _$102;
                    }

                    public void set_$102(int _$102) {
                        this._$102 = _$102;
                    }

                    public String getHead_img_y() {
                        return head_img_y;
                    }

                    public void setHead_img_y(String head_img_y) {
                        this.head_img_y = head_img_y;
                    }
                }

                public static class UserReBean {
                    /**
                     * id : 19
                     * nickname : Fred
                     * head_img :
                     * @ : 3
                     * head_img_y :
                     */

                    private String id;
                    private String nickname;
                    private String head_img;
                    @SerializedName("@")
                    private int _$98; // FIXME check this code
                    private String head_img_y;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getHead_img() {
                        return head_img;
                    }

                    public void setHead_img(String head_img) {
                        this.head_img = head_img;
                    }

                    public int get_$98() {
                        return _$98;
                    }

                    public void set_$98(int _$98) {
                        this._$98 = _$98;
                    }

                    public String getHead_img_y() {
                        return head_img_y;
                    }

                    public void setHead_img_y(String head_img_y) {
                        this.head_img_y = head_img_y;
                    }
                }

                public static class MoodBean {
                    /**
                     * id : 343
                     * img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=Xd4Z%2BmXLBQ98%2F%2Fr90okAryLEw14%3D
                     * content : 你好，finder…
                     * comment_num : 3
                     * thing_num : 11
                     * img_y : http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg
                     * img_s : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511344099&Signature=uYyaEL4uvUyA4Fh0Yp6JgyuOS8w%3D
                     */

                    private String id;
                    private String img;
                    private String content;
                    private String comment_num;
                    private String thing_num;
                    private String img_y;
                    private String img_s;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getComment_num() {
                        return comment_num;
                    }

                    public void setComment_num(String comment_num) {
                        this.comment_num = comment_num;
                    }

                    public String getThing_num() {
                        return thing_num;
                    }

                    public void setThing_num(String thing_num) {
                        this.thing_num = thing_num;
                    }

                    public String getImg_y() {
                        return img_y;
                    }

                    public void setImg_y(String img_y) {
                        this.img_y = img_y;
                    }

                    public String getImg_s() {
                        return img_s;
                    }

                    public void setImg_s(String img_s) {
                        this.img_s = img_s;
                    }
                }
            }
        }
    }
}

