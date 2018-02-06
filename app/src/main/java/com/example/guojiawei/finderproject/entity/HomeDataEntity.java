package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guojiawei on 2017/11/15.
 */

public class HomeDataEntity {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"offset":0,"sort":"gjuli","order":"asc","total":"27","row":5,"rows":[{"gjuli":"47","list":[{"id":"343","user_id":"16","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=QZAkhD8b7%2Fav48f107xWyI7qpJo%3D","content":"你好，finder\u2026","longitude":"116.405542611079","latitude":"39.91435106561615","comment_num":"3","thing_num":"10","inper":"","insert_time":"10月27日 20:47","sort":"0","del":"0","juli":"47","gjuli":"47","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=sU80uCZRIm6W8LZ7uX0VPRQrQSk%3D","@":12},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=FtXAuMyyrnrstEavlSm3QcKRLTg%3D"}]},{"gjuli":"61","list":[{"id":"375","user_id":"38","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171112/20171112132019_67733.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=%2BxfSaZc0Zm2Vt8o%2FR8VfaujKp8Y%3D","content":"依旧这么绿","longitude":"116.4490471932153","latitude":"39.97372035739194","comment_num":"0","thing_num":"5","inper":"","insert_time":"12日 13:20","sort":"0","del":"0","juli":"61","gjuli":"61","user":{"id":"38","nickname":"当讲求","head_img":"","@":4},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171112/20171112132019_67733.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=Fv5jhrwh7dISFRuaNBSrXz%2B0gW8%3D"}]},{"gjuli":"76","list":[{"id":"372","user_id":"49","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171110/20171110151220_50004.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=2A6VmTEH1L%2BPW0lwq1TpAfCsha8%3D","content":"老古董们😌","longitude":"116.5172707444262","latitude":"39.97175491844771","comment_num":"1","thing_num":"3","inper":"","insert_time":"10日 15:12","sort":"0","del":"0","juli":"76","gjuli":"76","user":{"id":"49","nickname":"空心港°","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171107/20171107174644_66646.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=PgKoMud7Mcj0vct7SO9QRFW3u54%3D","@":4},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171110/20171110151220_50004.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=TWNXj8Z%2BmVNo7ApxBGNEgcD%2F0P4%3D"}]},{"gjuli":"102","list":[{"id":"365","user_id":"50","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=MFgKGOXaVgYoEgI6OTlKoSQGsnk%3D","content":"夜空下总会有✨","longitude":"116.4777818361217","latitude":"40.01089743968681","comment_num":"1","thing_num":"5","inper":"","insert_time":"08日 20:41","sort":"0","del":"0","juli":"102","gjuli":"102","user":{"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=s9WzjWX0u1K%2FZeWRhMOA1iwpmVM%3D","@":5},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=EDL77jDPA67GlgyUeRslMXJGI30%3D"}]},{"gjuli":"177","list":[{"id":"362","user_id":"49","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171107/20171107174356_21778.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=S9FLtjJXwYfgHebcRdEwWfkGejU%3D","content":"生活需要小憩😌","longitude":"116.5690695629136","latitude":"40.05565382044717","comment_num":"3","thing_num":"4","inper":"","insert_time":"07日 17:43","sort":"0","del":"0","juli":"177","gjuli":"177","user":{"id":"49","nickname":"空心港°","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171107/20171107174644_66646.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=PgKoMud7Mcj0vct7SO9QRFW3u54%3D","@":4},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171107/20171107174356_21778.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=ywWI3DnAkQR6dryGZDKhP0jdOvM%3D"}]}]}
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
         * sort : gjuli
         * order : asc
         * total : 27
         * row : 5
         * rows : [{"gjuli":"47","list":[{"id":"343","user_id":"16","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=QZAkhD8b7%2Fav48f107xWyI7qpJo%3D","content":"你好，finder\u2026","longitude":"116.405542611079","latitude":"39.91435106561615","comment_num":"3","thing_num":"10","inper":"","insert_time":"10月27日 20:47","sort":"0","del":"0","juli":"47","gjuli":"47","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=sU80uCZRIm6W8LZ7uX0VPRQrQSk%3D","@":12},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=FtXAuMyyrnrstEavlSm3QcKRLTg%3D"}]},{"gjuli":"61","list":[{"id":"375","user_id":"38","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171112/20171112132019_67733.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=%2BxfSaZc0Zm2Vt8o%2FR8VfaujKp8Y%3D","content":"依旧这么绿","longitude":"116.4490471932153","latitude":"39.97372035739194","comment_num":"0","thing_num":"5","inper":"","insert_time":"12日 13:20","sort":"0","del":"0","juli":"61","gjuli":"61","user":{"id":"38","nickname":"当讲求","head_img":"","@":4},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171112/20171112132019_67733.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=Fv5jhrwh7dISFRuaNBSrXz%2B0gW8%3D"}]},{"gjuli":"76","list":[{"id":"372","user_id":"49","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171110/20171110151220_50004.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=2A6VmTEH1L%2BPW0lwq1TpAfCsha8%3D","content":"老古董们😌","longitude":"116.5172707444262","latitude":"39.97175491844771","comment_num":"1","thing_num":"3","inper":"","insert_time":"10日 15:12","sort":"0","del":"0","juli":"76","gjuli":"76","user":{"id":"49","nickname":"空心港°","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171107/20171107174644_66646.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=PgKoMud7Mcj0vct7SO9QRFW3u54%3D","@":4},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171110/20171110151220_50004.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=TWNXj8Z%2BmVNo7ApxBGNEgcD%2F0P4%3D"}]},{"gjuli":"102","list":[{"id":"365","user_id":"50","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=MFgKGOXaVgYoEgI6OTlKoSQGsnk%3D","content":"夜空下总会有✨","longitude":"116.4777818361217","latitude":"40.01089743968681","comment_num":"1","thing_num":"5","inper":"","insert_time":"08日 20:41","sort":"0","del":"0","juli":"102","gjuli":"102","user":{"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=s9WzjWX0u1K%2FZeWRhMOA1iwpmVM%3D","@":5},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=EDL77jDPA67GlgyUeRslMXJGI30%3D"}]},{"gjuli":"177","list":[{"id":"362","user_id":"49","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171107/20171107174356_21778.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=S9FLtjJXwYfgHebcRdEwWfkGejU%3D","content":"生活需要小憩😌","longitude":"116.5690695629136","latitude":"40.05565382044717","comment_num":"3","thing_num":"4","inper":"","insert_time":"07日 17:43","sort":"0","del":"0","juli":"177","gjuli":"177","user":{"id":"49","nickname":"空心港°","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171107/20171107174644_66646.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=PgKoMud7Mcj0vct7SO9QRFW3u54%3D","@":4},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171107/20171107174356_21778.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=ywWI3DnAkQR6dryGZDKhP0jdOvM%3D"}]}]
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
             * gjuli : 47
             * list : [{"id":"343","user_id":"16","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=QZAkhD8b7%2Fav48f107xWyI7qpJo%3D","content":"你好，finder\u2026","longitude":"116.405542611079","latitude":"39.91435106561615","comment_num":"3","thing_num":"10","inper":"","insert_time":"10月27日 20:47","sort":"0","del":"0","juli":"47","gjuli":"47","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=sU80uCZRIm6W8LZ7uX0VPRQrQSk%3D","@":12},"img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=FtXAuMyyrnrstEavlSm3QcKRLTg%3D"}]
             */

            private String gjuli;
            private List<ListBean> list;

            public String getGjuli() {
                return gjuli;
            }

            public void setGjuli(String gjuli) {
                this.gjuli = gjuli;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 343
                 * user_id : 16
                 * img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=QZAkhD8b7%2Fav48f107xWyI7qpJo%3D
                 * content : 你好，finder…
                 * longitude : 116.405542611079
                 * latitude : 39.91435106561615
                 * comment_num : 3
                 * thing_num : 10
                 * inper :
                 * insert_time : 10月27日 20:47
                 * sort : 0
                 * del : 0
                 * juli : 47
                 * gjuli : 47
                 * user : {"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=sU80uCZRIm6W8LZ7uX0VPRQrQSk%3D","@":12}
                 * img_s : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=FtXAuMyyrnrstEavlSm3QcKRLTg%3D
                 * "video_id": "ab4d8d2a52e6469088e58eaff3cf80bc",
                 * "image_url": "http:\/\/video.zl-finder.com\/image\/cover\/14AB4BA584E3473CB2E94F109414236B-6-2.png",
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
                private String img_s;
                private String thing;
                private String video_id;

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
                     * id : 16
                     * nickname : black
                     * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171027/20171027123531_38111.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1510903298&Signature=sU80uCZRIm6W8LZ7uX0VPRQrQSk%3D
                     *
                     * @ : 12
                     */

                    private String id;
                    private String nickname;
                    private String head_img;
                    @SerializedName("@")
                    private int _$36; // FIXME check this code

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

                    public int get_$36() {
                        return _$36;
                    }

                    public void set_$36(int _$36) {
                        this._$36 = _$36;
                    }
                }
            }
        }
    }
}
