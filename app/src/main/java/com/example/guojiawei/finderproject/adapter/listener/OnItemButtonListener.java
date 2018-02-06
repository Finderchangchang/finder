package com.example.guojiawei.finderproject.adapter.listener;

import android.view.View;

import com.example.guojiawei.finderproject.adapter.base.BaseRecyclerViewAdapater;

/**
 * Created by guojiawei on 2017/11/16.
 */

public interface  OnItemButtonListener {
    void head(BaseRecyclerViewAdapater adapaterm,View v, int position);

    void zan(BaseRecyclerViewAdapater adapaterm, int position);

    void pinglun(BaseRecyclerViewAdapater adapaterm, int position);

    void more(BaseRecyclerViewAdapater adapaterm, int position);

    void juli(BaseRecyclerViewAdapater adapaterm, int position);

    void imgPreview(BaseRecyclerViewAdapater adapaterm, View v, int position);

    void  play(BaseRecyclerViewAdapater adapaterm, int position);
}
