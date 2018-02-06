package com.example.guojiawei.finderproject.util;

import android.util.Log;

import com.example.guojiawei.finderproject.AppConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by guojiawei on 2017/11/14.
 */

public class EncryptUtil {
    private static String sign = "";

    public static Map<String, String> EncryptAutoSort(Map<String, String> params) {
        params.put("uptime", getUpTime());
        StringBuffer sb = new StringBuffer();

        Set<String> set = params.keySet();
        String[] tempKeys = new String[set.size()];
        set.toArray(tempKeys);

        //正序排序
        Arrays.sort(tempKeys);

        for (int i = 0; i < tempKeys.length; i++) {
            try {
                Log.e("key", tempKeys[i]);
                if (i == tempKeys.length - 1) {
                    sb.append(tempKeys[i] + "=" + params.get(tempKeys[i]).toString());
                } else {
                    sb.append(tempKeys[i] + "=" + params.get(tempKeys[i]).toString() + "&");
                }
            }catch (Exception e){
                    Log.e("参数加密异常", tempKeys[i] + "  字段异常");
            }

        }
        sb.append(AppConfig.URLKEY);
        Log.e("OkGo", sb.toString());
        sign = MD5Util.MD5(sb.toString());

        params.put("sign", sign);
        return params;
    }


    public static String getUpTime() {
        String javatime = System.currentTimeMillis() + "";
        String uptime = javatime.substring(0, 10);
        return uptime;
    }
}
