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
import com.example.guojiawei.finderproject.entity.PublishEntity;
import com.example.guojiawei.finderproject.util.BitMapUtil;
import com.example.guojiawei.finderproject.widget.CircleImageView;
import com.example.guojiawei.finderproject.widget.RoundImageView;
import com.example.guojiawei.finderproject.widget.dialog.ContentPreviewDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojiawei on 2017/11/22.
 */

public class YouDetailsAdapter extends BaseRecyclerViewAdapater<PublishEntity.DataBean.RowsBean> {

    private final int TYPE_HEAD = 0;
    private final int TYPE_MESS = 1;
    private PublishEntity.DataBean headEntity;

    private Context mContext;

    private View head;

    private OnItemButtonListener onItemButtonListener;

    private OnReplyMessageHeadOnclickListener onReplyMessageHeadOnclickListener;

    public YouDetailsAdapter(Context context) {
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

    public void setHeadEntity(PublishEntity.DataBean headEntity) {
        this.headEntity = headEntity;
    }

    public PublishEntity.DataBean getHeadEntity() {
        return headEntity;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final PublishEntity.DataBean.RowsBean bean = headEntity.getRows().get(position);
        if (holder instanceof HeadViewHolder) {
            final HeadViewHolder h = (HeadViewHolder) holder;
//            if (bean.getImg_s() == null || bean.getImg_s().equals("")) {
//                h.icPreviewImg.setVisibility(View.GONE);
//                h.ic_preview_video1.setVisibility(View.VISIBLE);
//                if (bean.getImage_url() == null || bean.getImage_url().equals("")) {
//
//                } else {
//                    BitMapUtil.loadImage(mContext, bean.getImage_url(), h.ic_preview_video);
////                    videoId = getDatas().get(position).getVideo_id();
//                }
//            } else {
//                h.icPreviewImg.setVisibility(View.VISIBLE);
//                h.ic_preview_video1.setVisibility(View.GONE);
//                BitMapUtil.loadImage(mContext, bean.getImg_s(), h.icPreviewImg);
//            }
            BitMapUtil.loadImage(mContext, bean.getImg_s(), h.icPreviewImg);
            h.tvName.setText(bean.getUser().toString());
            h.tvContent.setText(bean.getContent());
            Glide.with(mContext).load(bean.getUser().getHead_img()).placeholder(R.drawable.ic_head_load).into(h.icHead);
            h.tvName.setText(bean.getUser().getNickname());
            h.tvZan.setText(bean.getThing_num());
            h.tvPinglun.setText(bean.getComment_num());
            //h.tvJuli.setText(Double.valueOf(bean.getJuli()) / 10 + "km");
            //h.tvAite.setText(bean.getUser().get_$93() + "");
            h.tvTime.setText(bean.getInsert_time());

            if ("".equals(bean.getThing())) {
                return;
            }
            if ("0".equals(bean.getThing())) {
                BitMapUtil.loadImage(mContext, R.drawable.ic_zan, h.icZan);
            }
            if ("1".equals(bean.getThing())) {
                BitMapUtil.loadImage(mContext, R.drawable.ic_zan_select, h.icZan);
            }
            if (onItemButtonListener != null) {
                h.icHead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.head(YouDetailsAdapter.this, v, position);
                    }
                });
                h.rlAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.juli(YouDetailsAdapter.this, position);
                    }
                });
                h.rlDianzan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.zan(YouDetailsAdapter.this, position);
                    }
                });

                h.rlPinglun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.pinglun(YouDetailsAdapter.this, position);
                    }
                });
                h.ic_preview_video1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.play(YouDetailsAdapter.this, position);
                    }
                });
                h.icPreviewImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.imgPreview(YouDetailsAdapter.this, h.icPreviewImg, position);
                    }
                });
                h.icMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.more(YouDetailsAdapter.this, position);
                    }
                });
                h.tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ContentPreviewDialog dialog = new ContentPreviewDialog(mContext, bean.getContent().toString());
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
        RoundImageView icPreviewImg;
        @BindView(R.id.ic_preview_video1)
        RelativeLayout ic_preview_video1;
        @BindView(R.id.ic_preview_video)
        RoundImageView ic_preview_video;
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
