package com.example.guojiawei.finderproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guojiawei on 2017/11/28.
 */

public class MessageDetailEntity {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"offset":0,"sort":"id","order":"desc","total":"80","page":" ","row":5,"rows":[{"id":"533","mood_id":"526","user_id":"50","comment_id":"0","user_re_id":"264","content":"和我的好像😄","inper":"","insert_time":"27日 11:39","sort":"0","show":"0","del":"0","user":{"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=KkHo4ycnzSzXGrtZaWQfxNbi4mQ%3D","@":9,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108201345_47416.jpg"},"user_re":{"id":"264","nickname":"小猪抱抱","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171127/20171127111914_25671.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=OZcZUz%2B%2B36HeRj4XDsNllwwQUh0%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171127/20171127111914_25671.jpg"},"mood":{"id":"526","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=0tY6yHFYksJN66UrOpiWVv7nyW4%3D","content":"上班偷个闲","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171127/20171127113500_77533.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=cYOMoIohFnwQqHSkWVUPV4h2Ops%3D"}},{"id":"531","mood_id":"365","user_id":"33","comment_id":"0","user_re_id":"50","content":"🙂","inper":"","insert_time":"25日 11:23","sort":"0","show":"1","del":"0","user":{"id":"33","nickname":"竹宫悠由子","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125145405_33096.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=Z%2BwhE9vog4xNPRXZMwZXqlKIr4g%3D","@":5,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125145405_33096.jpg"},"user_re":{"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=KkHo4ycnzSzXGrtZaWQfxNbi4mQ%3D","@":9,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108201345_47416.jpg"},"mood":{"id":"365","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=y1Sn8RQbxttvFBcGZcv5kT5dHQI%3D","content":"夜空下总会有✨","comment_num":"2","thing_num":"7","img_y":"http://img.zl-finder.com/mood/20171108/20171108204119_74249.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=zNLsP61sxrjsHmtl%2B9bvQ%2F3GNi4%3D"}},{"id":"530","mood_id":"486","user_id":"143","comment_id":"528","user_re_id":"16","content":"美滴很","inper":"","insert_time":"24日 22:21","sort":"0","show":"1","del":"0","user":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=g0nCm30cLri6927q%2FZLyzh2Qe3Y%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"user_re":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125124831_25012.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=MDJ1f1ok1RZYiy1iSF2M3PNRcPc%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125124831_25012.jpg"},"mood":{"id":"486","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=81HQvcObN8HQY%2BZ3OLDJ4zxZXLA%3D","content":"臊子面，肉夹馍，聊咋咧","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171124/20171124130216_76222.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=YUAOHJXwCGoE1lZSXtI1203XN0w%3D"}},{"id":"529","mood_id":"343","user_id":"49","comment_id":"0","user_re_id":"16","content":"🇨🇳","inper":"","insert_time":"24日 22:01","sort":"0","show":"1","del":"0","user":{"id":"49","nickname":"空心港°","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171107/20171107174644_66646.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=WYVoO8QDFOfVe5WTOESW9t56bUo%3D","@":6,"head_img_y":"http://img.zl-finder.com/files/20171107/20171107174644_66646.jpg"},"user_re":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125124831_25012.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=MDJ1f1ok1RZYiy1iSF2M3PNRcPc%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125124831_25012.jpg"},"mood":{"id":"343","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=HKg3AyTAnwy6Uu3yayHixztHeDw%3D","content":"你好，finder\u2026","comment_num":"3","thing_num":"11","img_y":"http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=jbzlPHrNImtR5cqG6nDolVVv%2FKY%3D"}},{"id":"528","mood_id":"486","user_id":"16","comment_id":"0","user_re_id":"143","content":"忒色😎","inper":"","insert_time":"24日 17:12","sort":"0","show":"1","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125124831_25012.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=MDJ1f1ok1RZYiy1iSF2M3PNRcPc%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125124831_25012.jpg"},"user_re":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=g0nCm30cLri6927q%2FZLyzh2Qe3Y%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"mood":{"id":"486","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=81HQvcObN8HQY%2BZ3OLDJ4zxZXLA%3D","content":"臊子面，肉夹馍，聊咋咧","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171124/20171124130216_76222.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=YUAOHJXwCGoE1lZSXtI1203XN0w%3D"}}]}
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
         * total : 80
         * page :
         * row : 5
         * rows : [{"id":"533","mood_id":"526","user_id":"50","comment_id":"0","user_re_id":"264","content":"和我的好像😄","inper":"","insert_time":"27日 11:39","sort":"0","show":"0","del":"0","user":{"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=KkHo4ycnzSzXGrtZaWQfxNbi4mQ%3D","@":9,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108201345_47416.jpg"},"user_re":{"id":"264","nickname":"小猪抱抱","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171127/20171127111914_25671.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=OZcZUz%2B%2B36HeRj4XDsNllwwQUh0%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171127/20171127111914_25671.jpg"},"mood":{"id":"526","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=0tY6yHFYksJN66UrOpiWVv7nyW4%3D","content":"上班偷个闲","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171127/20171127113500_77533.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=cYOMoIohFnwQqHSkWVUPV4h2Ops%3D"}},{"id":"531","mood_id":"365","user_id":"33","comment_id":"0","user_re_id":"50","content":"🙂","inper":"","insert_time":"25日 11:23","sort":"0","show":"1","del":"0","user":{"id":"33","nickname":"竹宫悠由子","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125145405_33096.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=Z%2BwhE9vog4xNPRXZMwZXqlKIr4g%3D","@":5,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125145405_33096.jpg"},"user_re":{"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=KkHo4ycnzSzXGrtZaWQfxNbi4mQ%3D","@":9,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108201345_47416.jpg"},"mood":{"id":"365","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=y1Sn8RQbxttvFBcGZcv5kT5dHQI%3D","content":"夜空下总会有✨","comment_num":"2","thing_num":"7","img_y":"http://img.zl-finder.com/mood/20171108/20171108204119_74249.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171108/20171108204119_74249.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=zNLsP61sxrjsHmtl%2B9bvQ%2F3GNi4%3D"}},{"id":"530","mood_id":"486","user_id":"143","comment_id":"528","user_re_id":"16","content":"美滴很","inper":"","insert_time":"24日 22:21","sort":"0","show":"1","del":"0","user":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=g0nCm30cLri6927q%2FZLyzh2Qe3Y%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"user_re":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125124831_25012.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=MDJ1f1ok1RZYiy1iSF2M3PNRcPc%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125124831_25012.jpg"},"mood":{"id":"486","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=81HQvcObN8HQY%2BZ3OLDJ4zxZXLA%3D","content":"臊子面，肉夹馍，聊咋咧","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171124/20171124130216_76222.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=YUAOHJXwCGoE1lZSXtI1203XN0w%3D"}},{"id":"529","mood_id":"343","user_id":"49","comment_id":"0","user_re_id":"16","content":"🇨🇳","inper":"","insert_time":"24日 22:01","sort":"0","show":"1","del":"0","user":{"id":"49","nickname":"空心港°","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171107/20171107174644_66646.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=WYVoO8QDFOfVe5WTOESW9t56bUo%3D","@":6,"head_img_y":"http://img.zl-finder.com/files/20171107/20171107174644_66646.jpg"},"user_re":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125124831_25012.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=MDJ1f1ok1RZYiy1iSF2M3PNRcPc%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125124831_25012.jpg"},"mood":{"id":"343","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=HKg3AyTAnwy6Uu3yayHixztHeDw%3D","content":"你好，finder\u2026","comment_num":"3","thing_num":"11","img_y":"http://img.zl-finder.com/mood/20171027/20171027204713_24115.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171027/20171027204713_24115.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=jbzlPHrNImtR5cqG6nDolVVv%2FKY%3D"}},{"id":"528","mood_id":"486","user_id":"16","comment_id":"0","user_re_id":"143","content":"忒色😎","inper":"","insert_time":"24日 17:12","sort":"0","show":"1","del":"0","user":{"id":"16","nickname":"black","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171125/20171125124831_25012.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=MDJ1f1ok1RZYiy1iSF2M3PNRcPc%3D","@":18,"head_img_y":"http://img.zl-finder.com/files/20171125/20171125124831_25012.jpg"},"user_re":{"id":"143","nickname":"carol","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108203401_32204.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=g0nCm30cLri6927q%2FZLyzh2Qe3Y%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108203401_32204.jpg"},"mood":{"id":"486","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=81HQvcObN8HQY%2BZ3OLDJ4zxZXLA%3D","content":"臊子面，肉夹馍，聊咋咧","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171124/20171124130216_76222.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171124/20171124130216_76222.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=YUAOHJXwCGoE1lZSXtI1203XN0w%3D"}}]
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
             * id : 533
             * mood_id : 526
             * user_id : 50
             * comment_id : 0
             * user_re_id : 264
             * content : 和我的好像😄
             * inper :
             * insert_time : 27日 11:39
             * sort : 0
             * show : 0
             * del : 0
             * user : {"id":"50","nickname":"Emma","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=KkHo4ycnzSzXGrtZaWQfxNbi4mQ%3D","@":9,"head_img_y":"http://img.zl-finder.com/files/20171108/20171108201345_47416.jpg"}
             * user_re : {"id":"264","nickname":"小猪抱抱","head_img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171127/20171127111914_25671.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=OZcZUz%2B%2B36HeRj4XDsNllwwQUh0%3D","@":1,"head_img_y":"http://img.zl-finder.com/files/20171127/20171127111914_25671.jpg"}
             * mood : {"id":"526","img":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=0tY6yHFYksJN66UrOpiWVv7nyW4%3D","content":"上班偷个闲","comment_num":"1","thing_num":"2","img_y":"http://img.zl-finder.com/mood/20171127/20171127113500_77533.jpg","img_s":"http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=cYOMoIohFnwQqHSkWVUPV4h2Ops%3D"}
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
                 * id : 50
                 * nickname : Emma
                 * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171108/20171108201345_47416.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=KkHo4ycnzSzXGrtZaWQfxNbi4mQ%3D
                 * @ : 9
                 * head_img_y : http://img.zl-finder.com/files/20171108/20171108201345_47416.jpg
                 */

                private String id;
                private String nickname;
                private String head_img;
                @SerializedName("@")
                private int _$302; // FIXME check this code
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

                public int get_$302() {
                    return _$302;
                }

                public void set_$302(int _$302) {
                    this._$302 = _$302;
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
                 * id : 264
                 * nickname : 小猪抱抱
                 * head_img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/files/20171127/20171127111914_25671.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=OZcZUz%2B%2B36HeRj4XDsNllwwQUh0%3D
                 * @ : 1
                 * head_img_y : http://img.zl-finder.com/files/20171127/20171127111914_25671.jpg
                 */

                private String id;
                private String nickname;
                private String head_img;
                @SerializedName("@")
                private int _$27; // FIXME check this code
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

                public int get_$27() {
                    return _$27;
                }

                public void set_$27(int _$27) {
                    this._$27 = _$27;
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
                 * id : 526
                 * img : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=0tY6yHFYksJN66UrOpiWVv7nyW4%3D
                 * content : 上班偷个闲
                 * comment_num : 1
                 * thing_num : 2
                 * img_y : http://img.zl-finder.com/mood/20171127/20171127113500_77533.jpg
                 * img_s : http://xiaohei3576.oss-cn-beijing.aliyuncs.com/mood/20171127/20171127113500_77533.jpg%40%21h_356?OSSAccessKeyId=LTAIVIGdQoo0oH5A&Expires=1511858098&Signature=cYOMoIohFnwQqHSkWVUPV4h2Ops%3D
                 */

                private String id;
                private String img;
                private String content;
                private String comment_num;
                private String thing_num;
                private String img_y;
                private String img_s;

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

                private String video_id;
                private String image_url;

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
