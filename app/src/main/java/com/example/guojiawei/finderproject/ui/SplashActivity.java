package com.example.guojiawei.finderproject.ui;


import android.content.Intent;
import android.os.Bundle;

import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by guojiawei on 2017/11/8.
 */

public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
               /* Bugly SDK初始化
        * 参数1：上下文对象
        * 参数2：APPID，平台注册时得到,注意替换成你的appId
        * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
        */
//        if (MainActivity.main == null) {
        CrashReport.initCrashReport(getApplicationContext(), "15c1486262", true);
        setUpSplash();
//        } else {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
//        }
    }

    /**
     * 设置闪屏页跳转
     */
    private void setUpSplash() {
        Observable.timer(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                });


    }

}
