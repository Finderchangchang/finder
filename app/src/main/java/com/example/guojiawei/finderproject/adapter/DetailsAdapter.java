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
import com.example.guojiawei.finderproject.adapter.listener.OnItemButtonListener;
import com.example.guojiawei.finderproject.entity.DetailsEntity;
import com.example.guojiawei.finderproject.util.BitMapUtil;
import com.example.guojiawei.finderproject.widget.CircleImageView;
import com.example.guojiawei.finderproject.widget.dialog.ContentPreviewDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojiawei on 2017/11/22.
 */

public class DetailsAdapter extends BaseRecyclerViewAdapater<DetailsEntity.MessEntity.DataBean.RowsBean> {

    private final int TYPE_HEAD = 0;
    private final int TYPE_MESS = 1;
    private DetailsEntity.HeadEntity headEntity;

    private Context mContext;

    private View head;

    private OnItemButtonListener onItemButtonListener;

    private OnReplyMessageHeadOnclickListener onReplyMessageHeadOnclickListener;

    public DetailsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        head = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_content, parent, false);
        View message = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnRecyclerViewItemClickListener().onItemClick(v, (Integer) v.getTag());
            }
        });
        switch (viewType) {
            case TYPE_HEAD:
                return new HeadViewHolder(head);
            case TYPE_MESS:
                return new MessageViewHolder(message);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        return TYPE_MESS;
    }

    @Override
    public int getItemCount() {
        if (head != null) {
            return getDatas().size() + 1;
        }

        return getDatas().size() + 1;
    }

    public void setHeadEntity(DetailsEntity.HeadEntity headEntity) {
        this.headEntity = headEntity;
        notifyItemChanged(0);
    }

    public DetailsEntity.HeadEntity getHeadEntity() {
        return headEntity;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeadViewHolder) {
            final HeadViewHolder h = (HeadViewHolder) holder;
            if (headEntity.getData().getImg_s() == null || headEntity.getData().getImg_s().equals("")) {
                h.icPreviewImg.setVisibility(View.GONE);
                h.ic_preview_video1.setVisibility(View.VISIBLE);
                if (headEntity.getData().getImage_url() == null || headEntity.getData().getImage_url().equals("")) {

                } else {
                    BitMapUtil.loadImage(mContext, headEntity.getData().getImage_url(), h.ic_preview_video);
//                    videoId = getDatas().get(position).getVideo_id();
                }
            } else {
                h.icPreviewImg.setVisibility(View.VISIBLE);
                h.ic_preview_video1.setVisibility(View.GONE);
                BitMapUtil.loadImage(mContext, headEntity.getData().getImg_s(), h.icPreviewImg);
            }
            BitMapUtil.loadImage(mContext, headEntity.getData().getImg_s(), h.icPreviewImg);
            h.tvName.setText(headEntity.getData().getUser().toString());
            h.tvContent.setText(headEntity.getData().getContent().toString());
            Glide.with(mContext).load(headEntity.getData().getUser().getHead_img()).placeholder(R.drawable.ic_head_load).into(h.icHead);
            h.tvName.setText(headEntity.getData().getUser().getNickname());
            h.tvZan.setText(headEntity.getData().getThing_num());
            h.tvPinglun.setText(headEntity.getData().getComment_num());
            h.tvJuli.setText(Double.valueOf(headEntity.getData().getJuli()) / 10 + "km");
            h.tvAite.setText(headEntity.getData().getUser().get_$93() + "");
            h.tvTime.setText(headEntity.getData().getInsert_time());

            if ("".equals(headEntity.getData().getThing())) {
                return;
            }
            if ("0".equals(headEntity.getData().getThing())) {
                BitMapUtil.loadImage(mContext, R.drawable.ic_zan, h.icZan);
            }
            if ("1".equals(headEntity.getData().getThing())) {
                BitMapUtil.loadImage(mContext, R.drawable.ic_zan_select, h.icZan);
            }
            if (onItemButtonListener != null) {
                h.icHead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.head(DetailsAdapter.this, v, position);
                    }
                });
                h.rlAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.juli(DetailsAdapter.this, position);
                    }
                });
                h.rlDianzan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.zan(DetailsAdapter.this, position);
                    }
                });

                h.rlPinglun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.pinglun(DetailsAdapter.this, position);
                    }
                });
                h.ic_preview_video1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.play(DetailsAdapter.this, position);
                    }
                });
                h.icPreviewImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.imgPreview(DetailsAdapter.this, h.icPreviewImg, position);
                    }
                });
                h.icMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.more(DetailsAdapter.this, position);
                    }
                });
                h.tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ContentPreviewDialog dialog = new ContentPreviewDialog(mContext, headEntity.getData().getContent().toString());
                        dialog.show();
                    }
                });
            }
        }
        if (holder instanceof MessageViewHolder) {
            MessageViewHolder h = (MessageViewHolder) holder;
            h.itemView.setTag(position - 1);
            Glide.with(mContext).load(getDatas().get(position - 1).getUser().getHead_img()).placeholder(R.drawable.ic_head_load).into(h.icHead);

            h.tvContent.setText(getDatas().get(position - 1).getContent());
            h.tvTime.setText(getDatas().get(position - 1).getInsert_time());
            h.tvTitle.setText(getDatas().get(position - 1).getUser().getNickname());
            h.icHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onReplyMessageHeadOnclickListener != null) {
                        onReplyMessageHeadOnclickListener.onClickHead(position - 1);
                    }
                }
            });
        }
    }

    public void setOnItemButtonListener(OnItemButtonListener onItemButtonListener) {
        this.onItemButtonListener = onItemButtonListener;
    }


    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_head)
        CircleImageView icHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_aite)
        TextView tvAite;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.ic_more)
        ImageView icMore;
        @BindView(R.id.ic_preview_img)
        ImageView icPreviewImg;
        @BindView(R.id.ic_preview_video1)
        RelativeLayout ic_preview_video1;
        @BindView(R.id.ic_preview_video)
        ImageView ic_preview_video;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ic_gps)
        ImageView icGps;
        @BindView(R.id.tv_juli)
        TextView tvJuli;
        @BindView(R.id.rl_address)
        RelativeLayout rlAddress;
        @BindView(R.id.ic_pinglun)
        ImageView icPinglun;
        @BindView(R.id.tv_pinglun)
        TextView tvPinglun;
        @BindView(R.id.rl_pinglun)
        RelativeLayout rlPinglun;
        @BindView(R.id.ic_zan)
        ImageView icZan;
        @BindView(R.id.tv_zan)
        TextView tvZan;
        @BindView(R.id.rl_dianzan)
        RelativeLayout rlDianzan;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_head)
        CircleImageView icHead;
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

    public interface OnReplyMessageHeadOnclickListener {
        void onClickHead(int postion);
    }

    public void setOnReplyMessageHeadOnclickListener(OnReplyMessageHeadOnclickListener onReplyMessageHeadOnclickListener) {
        this.onReplyMessageHeadOnclickListener = onReplyMessageHeadOnclickListener;
    }
}
