package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guojiawei on 2017/11/21.
 */

public class MessageEntity {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"offset":0,"sort":"`show` desc,id","order":"desc","total":2,"page":"","row":10,"rows":[{"user_id":"165","ids":"514","unshow":"0","user":{"id":"165","nickname":"莫小熙≈ ","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119161741_53577.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511248119&Signature=9SFHUhVr5Ix1e6CSY8waHIDFlxs%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171119/20171119161741_53577.jpeg"},"con":{"content":"漂亮","insert_time":"20日 11:12"}},{"user_id":"148","ids":"497","unshow":"0","user":{"id":"148","nickname":"老何为","head_img":"","@":1,"head_img_y":""},"con":{"content":"哈","insert_time":"16日 15:44"}}]}
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
         * sort : `show` desc,id
         * order : desc
         * total : 2
         * page :
         * row : 10
         * rows : [{"user_id":"165","ids":"514","unshow":"0","user":{"id":"165","nickname":"莫小熙≈ ","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119161741_53577.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511248119&Signature=9SFHUhVr5Ix1e6CSY8waHIDFlxs%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171119/20171119161741_53577.jpeg"},"con":{"content":"漂亮","insert_time":"20日 11:12"}},{"user_id":"148","ids":"497","unshow":"0","user":{"id":"148","nickname":"老何为","head_img":"","@":1,"head_img_y":""},"con":{"content":"哈","insert_time":"16日 15:44"}}]
         */

        private int offset;
        private String sort;
        private String order;
        private int total;
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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
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
             * user_id : 165
             * ids : 514
             * unshow : 0
             * user : {"id":"165","nickname":"莫小熙≈ ","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119161741_53577.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511248119&Signature=9SFHUhVr5Ix1e6CSY8waHIDFlxs%3D","@":1,"head_img_y":"http://img.zl-finder.com/user/20171119/20171119161741_53577.jpeg"}
             * con : {"content":"漂亮","insert_time":"20日 11:12"}
             */

            private String user_id;
            private String ids;
            private String unshow;
            private UserBean user;
            private ConBean con;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getUnshow() {
                return unshow;
            }

            public void setUnshow(String unshow) {
                this.unshow = unshow;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public ConBean getCon() {
                return con;
            }

            public void setCon(ConBean con) {
                this.con = con;
            }

            public static class UserBean {
                /**
                 * id : 165
                 * nickname : 莫小熙≈
                 * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/user/20171119/20171119161741_53577.jpeg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511248119&Signature=9SFHUhVr5Ix1e6CSY8waHIDFlxs%3D
                 * @ : 1
                 * head_img_y : http://img.zl-finder.com/user/20171119/20171119161741_53577.jpeg
                 */

                private String id;
                private String nickname;
                private String head_img;
                @SerializedName("@")
                private int _$31; // FIXME check this code
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

                public int get_$31() {
                    return _$31;
                }

                public void set_$31(int _$31) {
                    this._$31 = _$31;
                }

                public String getHead_img_y() {
                    return head_img_y;
                }

                public void setHead_img_y(String head_img_y) {
                    this.head_img_y = head_img_y;
                }
            }

            public static class ConBean {
                /**
                 * content : 漂亮
                 * insert_time : 20日 11:12
                 */

                private String content;
                private String insert_time;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getInsert_time() {
                    return insert_time;
                }

                public void setInsert_time(String insert_time) {
                    this.insert_time = insert_time;
                }
            }
        }
    }
}
