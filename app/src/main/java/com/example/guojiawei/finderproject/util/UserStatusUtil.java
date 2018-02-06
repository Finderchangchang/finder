package com.example.guojiawei.finderproject.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by guojiawei on 2017/11/14.
 */

public class UserStatusUtil {
    private final static String TAG_ID = "USERID";

    private final static String TAG_COMMENT_NUM = "NUM";

    private static boolean mLogiStatu = false;

    private static Application mContext;

    private static String userId = "";

    public static void init(Application context) {
        mContext = context;
    }

    public static boolean isLogin() {
        getUserId();
        if (userId.isEmpty()) {
            mLogiStatu = false;
        } else {
            mLogiStatu = true;
        }
        return mLogiStatu;
    }

    public static String getUserId() {
        userId = (String) SharedPreferencesUtil.getData(mContext, TAG_ID, "");
        return userId;
    }

    public static void Login(String id) {
        SharedPreferencesUtil.saveData(mContext, TAG_ID, id);

    }

    public static void setCommentNum(String num) {
        SharedPreferencesUtil.saveData(mContext, TAG_COMMENT_NUM, num);

    }

    public static String getCommentNum() {
        return (String) SharedPreferencesUtil.getData(mContext, TAG_COMMENT_NUM, "0");
    }


    public static void backLogin() {
        SharedPreferencesUtil.clearData(mContext);
    }

}
