package com.example.guojiawei.finderproject.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.base.BaseRecyclerViewAdapater;
import com.example.guojiawei.finderproject.adapter.listener.OnItemButtonListener;
import com.example.guojiawei.finderproject.adapter.listener.OnMainItemClickListener;
import com.example.guojiawei.finderproject.entity.HomeDataEntity;
import com.example.guojiawei.finderproject.widget.DividerItemDecoration;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojiawei on 2017/11/21.
 */

public class MainContentAdapter extends BaseRecyclerViewAdapater<HomeDataEntity.DataBean.RowsBean> {

    private Context mContext;

    private MainRecyclerAdapter mainRecyclerAdapter;

    private OnItemButtonListener onButtonListener;

    private OnMainItemClickListener onMainItemClickListener;

    public MainContentAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_content, parent, false);

        return new MainContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainContentViewHolder) {
            mainRecyclerAdapter = new MainRecyclerAdapter(mContext);
            MainContentViewHolder h = (MainContentViewHolder) holder;

            //  h.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST));
            h.recyclerview.setAdapter(mainRecyclerAdapter);
            mainRecyclerAdapter.refreshDatas(getDatas().get(position).getList());
            h.recyclerview.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
                @Override
                public void OnPageChanged(int i, int i1) {
                    System.out.print("i" + i + "--i1:" + i1);
                }
            });
            mainRecyclerAdapter.setOnItemButtonListener(onButtonListener);
            mainRecyclerAdapter.setOnMainItemClickListener(onMainItemClickListener);

        }
    }

    public void setOnButtonListener(OnItemButtonListener onButtonListener) {
        this.onButtonListener = onButtonListener;
    }

    public void setOnMainItemClickListener(OnMainItemClickListener onMainItemClickListener) {
        this.onMainItemClickListener = onMainItemClickListener;
    }

    static class MainContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recyclerview)
        RecyclerViewPager recyclerview;
        private LinearLayoutManager lm;

        MainContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            lm = new LinearLayoutManager(view.getContext());
            lm.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerview.setLayoutManager(lm);
            recyclerview.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL_LIST));
            recyclerview.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
                @Override
                public void OnPageChanged(int i, int i1) {
                    Log.i("11-----------", i + "--" + i1);

                }
            });
        }
    }
}
