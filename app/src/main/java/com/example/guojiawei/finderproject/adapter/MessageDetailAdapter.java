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
import com.example.guojiawei.finderproject.entity.MessageDetailEntity;
import com.example.guojiawei.finderproject.util.BitMapUtil;
import com.example.guojiawei.finderproject.widget.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojiawei on 2017/11/28.
 */

public class MessageDetailAdapter extends BaseRecyclerViewAdapater<MessageDetailEntity.DataBean.RowsBean> {
    private Context mContext;
    private OnImagePreviewClickListener onImagePreviewClickListener;

    public MessageDetailAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_reply, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnRecyclerViewItemClickListener().onItemClick(v, (Integer) v.getTag());
            }
        });
        return new MessageDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MessageDetailViewHolder) {
            MessageDetailViewHolder h = (MessageDetailViewHolder) holder;
            h.itemView.setTag(position);
            Glide.with(h.itemView.getContext()).load(getDatas().get(position).getUser().getHead_img()).placeholder(R.drawable.ic_head_load).into(h.icHead);
//            BitMapUtil.loadImage(h.itemView.getContext(), getDatas().get(position).getMood().getImg_s(), h.icPreview);
            if (getDatas().get(position).getMood().getImg_s() == null || getDatas().get(position).getMood().getImg_s().equals("")) {
                h.icPreview.setVisibility(View.GONE);
                h.ic_preview_video1.setVisibility(View.VISIBLE);
                if (getDatas().get(position).getMood().getImage_url() == null || getDatas().get(position).getMood().getImage_url().equals("")) {

                } else {
                    BitMapUtil.loadImage(mContext, getDatas().get(position).getMood().getImage_url(), h.ic_preview_video);
//                    videoId = getDatas().get(position).getVideo_id();
                }
            } else {
                h.icPreview.setVisibility(View.VISIBLE);
                h.ic_preview_video1.setVisibility(View.GONE);
                BitMapUtil.loadImage(mContext, getDatas().get(position).getMood().getImg_s(), h.icPreview);
            }


            h.tvTime.setText(getDatas().get(position).getInsert_time());
            h.tvContent.setText(getDatas().get(position).getContent());
            h.tvTitle.setText(getDatas().get(position).getUser().getNickname());
            h.icPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onImagePreviewClickListener != null) {
                        onImagePreviewClickListener.onClick(v, getDatas().get(position).getMood().getImg(), position);
                    }
                }
            });
            h.ic_preview_video1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onImagePreviewClickListener != null) {
                        onImagePreviewClickListener.play(v, position);
                    }
                }
            });
        }
    }

    public void setOnImagePreviewClickListener(OnImagePreviewClickListener onImagePreviewClickListener) {
        this.onImagePreviewClickListener = onImagePreviewClickListener;
    }

    static class MessageDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_head)
        ImageView icHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ic_preview)
        ImageView icPreview;
        @BindView(R.id.ic_preview_video1)
        RelativeLayout ic_preview_video1;
        @BindView(R.id.ic_preview_video)
        RoundImageView ic_preview_video;

        MessageDetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnImagePreviewClickListener {
        void onClick(View v, String url, int postion);
        void play(View v,int position);
    }
}
