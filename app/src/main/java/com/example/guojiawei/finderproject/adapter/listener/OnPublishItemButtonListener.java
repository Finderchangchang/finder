package com.example.guojiawei.finderproject.adapter.listener;

import android.view.View;

import com.example.guojiawei.finderproject.adapter.base.BaseRecyclerViewAdapater;

/**
 * Created by guojiawei on 2017/11/16.
 */

public interface OnPublishItemButtonListener {
    void head(int position);
    void zan(int position);

    void pinglun(int position);

    void more( int position);

    void juli(int position);

    void play(int position);

    void imgPreview(View v, int position);
}
