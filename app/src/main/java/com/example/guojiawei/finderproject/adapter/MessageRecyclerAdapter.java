package com.example.guojiawei.finderproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.base.BaseRecyclerViewAdapater;
import com.example.guojiawei.finderproject.entity.MessageEntity;
import com.example.guojiawei.finderproject.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojiawei on 2017/11/10.
 */

public class MessageRecyclerAdapter extends BaseRecyclerViewAdapater<MessageEntity.DataBean.RowsBean> {

    private static final String UN_SHOW ="0";
    private Context mContext;

    public MessageRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_message, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnRecyclerViewItemClickListener().onItemClick(v, (Integer) v.getTag());
            }
        });
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MessageViewHolder) {
            MessageViewHolder h = (MessageViewHolder) holder;
            h.itemView.setTag(position);
            Glide.with(h.itemView.getContext()).load(getDatas().get(position).getUser().getHead_img()).placeholder(R.drawable.ic_head_load).into(h.icHead);
            h.tvContent.setText(getDatas().get(position).getCon().getContent());
            h.tvTime.setText(getDatas().get(position).getCon().getInsert_time());
            h.tvTitle.setText(getDatas().get(position).getUser().getNickname());
            if (!UN_SHOW.equals(getDatas().get(position).getUnshow())) {
                if (h.messageNum.getVisibility() == View.GONE) {
                    h.messageNum.setVisibility(View.VISIBLE);
                }
                h.messageNum.setText(getDatas().get(position).getUnshow());
            }
            if(UN_SHOW.equals(getDatas().get(position).getUnshow())){
                h.messageNum.setVisibility(View.GONE);
            }
        }
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_head)
        CircleImageView icHead;
        @BindView(R.id.message_num)
        TextView messageNum;
        @BindView(R.id.rl_head)
        RelativeLayout rlHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;

        MessageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
