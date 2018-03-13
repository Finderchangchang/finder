package com.example.guojiawei.finderproject.net;


/**
 * Created by guojiawei on 2017/11/13.
 */

public interface API {


    String BASE_URL = "http:www.zl-finder.com";

    String GET_ADDRESS = BASE_URL + "/index.php?m=api&c=AliVideo&a=get_auth_sts";

    String GET_CODE = BASE_URL + "/api.php?c=index&a=sms_code";

    String LOGIN = BASE_URL + "/api.php?c=index&a=codeLogin";

    String HOME = BASE_URL + "/api.php?c=Mood&a=glist";

    String REPORT = BASE_URL + "/api.php?c=tip&a=updata";

    String ZAN = BASE_URL + "/api.php?c=Thing&a=updata";

    String PUSHLISH = BASE_URL + "/api.php?c=Mood&a=updata";

    String REPLY_MESS = BASE_URL + "/api.php?c=Comment&a=updata";

    String USER_INFO = BASE_URL + "/api.php?c=index&a=userInfo";

    String LOGIN_OUT = BASE_URL + "/api.php?c=index&a=loginout";

    String USER_INFO_UPDATA = BASE_URL + "/api.php?c=index&a=upUserInfo";

    String DEL_CONTENT = BASE_URL + "/api.php?c=Mood&a=del";

    String MY_MESSAGE = BASE_URL + "/api.php?c=Comment&a=mylist";

    String MY_MOOD = BASE_URL + "/api.php?c=Mood&a=mylist";//朋友消息mylist
    String MY_ALL_MOOD = BASE_URL + "/api.php?c=Mood&a=follow_from";//朋友消息mylist

    String MY_FOLLOW_MY = BASE_URL + "/api.php?c=Comment&a=follow_my";//关注我的F友

    String MY_FOLLOW_TO = BASE_URL + "/api.php?c=Comment&a=follow_to";//我关注的列表F友

    String MY_FOLLOW_FROM = BASE_URL + "/api.php?c=Mood&a=follow_from";//我关注的文章
    String MY_NUMS = BASE_URL + "/api.php?c=Comment&a=nums";//获取未读数据


    String MOOD_INFO = BASE_URL + "/api.php?c=Mood&a=info";

    String MOOD_MESSAGE = BASE_URL + "/api.php?c=Comment&a=lists";

    String COMMENT_NUM = BASE_URL + "/api.php?c=Comment&a=num";

    String DEL_MESSAGE = BASE_URL + "/api.php?c=Comment&a=del";
    String MOOD_MAP = BASE_URL + "/api.php?c=Mood&a=juli_s";//地图接口

}
