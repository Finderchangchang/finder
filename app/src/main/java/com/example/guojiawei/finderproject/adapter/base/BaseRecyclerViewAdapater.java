package com.example.guojiawei.finderproject.adapter.base;


import android.support.v7.widget.RecyclerView;


import com.example.guojiawei.finderproject.adapter.listener.OnRecyclerViewItemClickListener;
import com.example.guojiawei.finderproject.entity.DetailsEntity;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRecyclerViewAdapater<T> extends RecyclerView.Adapter {
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    private List<T> datas = new ArrayList<>();

    public List<T> getDatas() {
        return datas;
    }


    public void refreshDatas(List<T> list) {
        if (this.datas != null) {
            this.datas.clear();
        }
        this.datas.addAll(list);
        notifyDataSetChanged();
    }

    public void addItems(List<T> list) {
        int index = this.datas.size();
        this.datas.addAll(list);
        notifyItemInserted(index);
    }

    public void remove(int postion) {
        this.datas.remove(postion);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return this.datas.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public OnRecyclerViewItemClickListener getOnRecyclerViewItemClickListener() {
        return onRecyclerViewItemClickListener;
    }


}
