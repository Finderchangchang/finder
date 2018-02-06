package com.example.guojiawei.finderproject.util;

import android.util.Log;

import com.google.gson.Gson;


public class GsonUtil {
    static Gson gson=new Gson();
    public static<T> T GosnToEntity(String json, Class<T> cls){
        T t=null;
        try {
            t= gson.fromJson(json,cls);
        }catch (Exception e){
            Log.e("gson解析异常",e.getMessage()+"  "+t.getClass().getName());
        }
        return t;
    }
}
