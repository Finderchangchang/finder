package com.example.guojiawei.finderproject.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 验证码定时器
 * com.subzero.travel.utils
 * Created by Administrator on 2016/8/10.
 */
public class TimeCount extends CountDownTimer {
    private TextView tvGetcode;

    public TimeCount(long millisInFuture, long countDownInterval, TextView view) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.tvGetcode = view;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        tvGetcode.setText("重新获取");
        tvGetcode.setEnabled(true);// 按钮获取焦点
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        tvGetcode.setEnabled(false);// 按钮不获取焦点
        tvGetcode.setText(millisUntilFinished / 1000 + "s");
    }
}
