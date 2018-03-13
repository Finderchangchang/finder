package com.example.guojiawei.finderproject.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.guojiawei.finderproject.R;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by Finder丶畅畅 on 2018/3/13 21:19
 * QQ群481606175
 */

public class MyBottomDialog extends Dialog {
    public interface click1 {
        void click(int position);
    }

    click1 click;

    public void setClick(click1 click) {
        this.click = click;
    }

    public MyBottomDialog(Activity context) {
        this(context, R.style.MyAnimDialog);
    }


    public MyBottomDialog(final Activity context, int themeResId) {
        super(context, themeResId);
        //加载布局并给布局的控件设置点击事件
        View contentView = getLayoutInflater().inflate(R.layout.dialog_custom3, null);
        //朋友圈
        contentView.findViewById(R.id.ll1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(1);
            }
        });
        //微博
        contentView.findViewById(R.id.ll2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(2);
            }
        });
        //QQ空间
        contentView.findViewById(R.id.ll3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(3);
            }
        });
        //微信
        contentView.findViewById(R.id.ll4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(4);
            }
        });
        //QQ
        contentView.findViewById(R.id.ll5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(5);
            }
        });
        super.setContentView(contentView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 预先设置Dialog的一些属性
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        getWindow().setAttributes(p);
        p.height = (int) (d.getHeight() * 0.1);
        p.width = d.getWidth();
        p.gravity = Gravity.LEFT | Gravity.BOTTOM;
        dialogWindow.setAttributes(p);
    }
}
