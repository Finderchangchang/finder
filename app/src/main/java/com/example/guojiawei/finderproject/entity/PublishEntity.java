package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guojiawei on 2017/11/21.
 */

public class PublishEntity {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"offset":0,"sort":"id","order":"desc","total":"1","row":10,"rows":[{"id":"449","user_id":"39","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121153400_83299.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=FHPGE3NYDwJVUyvtcJ9Ea2CSdRU%3D","content":"电脑","longitude":"112.5673498752733","latitude":"37.89156973223466","comment_num":"0","thing_num":"0","inper":"","insert_time":"15:34","sort":"0","del":"0","juli":"119813","gjuli":"119813","user":{"id":"39","nickname":"笑哈哈哈哈","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=mmm6EaCvPbAb0dLAMB0hrgKzOOA%3D","@":2,"head_img_y":"http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg"},"img_y":"http://img.zl-finder.com/mood/20171121/20171121153400_83299.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121153400_83299.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=pxIjfLzJcEWHYYnV%2BKicw4bWKZs%3D"}]}
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
         * total : 1
         * row : 10
         * rows : [{"id":"449","user_id":"39","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121153400_83299.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=FHPGE3NYDwJVUyvtcJ9Ea2CSdRU%3D","content":"电脑","longitude":"112.5673498752733","latitude":"37.89156973223466","comment_num":"0","thing_num":"0","inper":"","insert_time":"15:34","sort":"0","del":"0","juli":"119813","gjuli":"119813","user":{"id":"39","nickname":"笑哈哈哈哈","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=mmm6EaCvPbAb0dLAMB0hrgKzOOA%3D","@":2,"head_img_y":"http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg"},"img_y":"http://img.zl-finder.com/mood/20171121/20171121153400_83299.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121153400_83299.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=pxIjfLzJcEWHYYnV%2BKicw4bWKZs%3D"}]
         */

        private int offset;
        private String sort;
        private String order;
        private String total;
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
             * id : 449
             * user_id : 39
             * img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121153400_83299.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=FHPGE3NYDwJVUyvtcJ9Ea2CSdRU%3D
             * content : 电脑
             * longitude : 112.5673498752733
             * latitude : 37.89156973223466
             * comment_num : 0
             * thing_num : 0
             * inper :
             * insert_time : 15:34
             * sort : 0
             * del : 0
             * juli : 119813
             * gjuli : 119813
             * user : {"id":"39","nickname":"笑哈哈哈哈","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=mmm6EaCvPbAb0dLAMB0hrgKzOOA%3D","@":2,"head_img_y":"http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg"}
             * img_y : http://img.zl-finder.com/mood/20171121/20171121153400_83299.jpg
             * img_s : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171121/20171121153400_83299.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=pxIjfLzJcEWHYYnV%2BKicw4bWKZs%3D
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
            private String thing;
            private String img_s;
            private String image_url;
            private String video_id;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

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
                 * id : 39
                 * nickname : 笑哈哈哈哈
                 * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171121/20171121110832_16465.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511253397&Signature=mmm6EaCvPbAb0dLAMB0hrgKzOOA%3D
                 * @ : 2
                 * head_img_y : http://img.zl-finder.com/files/20171121/20171121110832_16465.jpg
                 */

                private String id;
                private String nickname;
                private String head_img;
                @SerializedName("@")
                private int _$219; // FIXME check this code
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

                public int get_$219() {
                    return _$219;
                }

                public void set_$219(int _$219) {
                    this._$219 = _$219;
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
}
