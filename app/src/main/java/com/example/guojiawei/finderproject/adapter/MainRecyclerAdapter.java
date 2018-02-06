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
import com.example.guojiawei.finderproject.adapter.listener.OnMainItemClickListener;
import com.example.guojiawei.finderproject.entity.HomeDataEntity;
import com.example.guojiawei.finderproject.util.BitMapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojiawei on 2017/11/8.
 */

public class MainRecyclerAdapter extends BaseRecyclerViewAdapater<HomeDataEntity.DataBean.RowsBean.ListBean> {

    private OnItemButtonListener onItemButtonListener;

    private OnMainItemClickListener onMainItemClickListener;

    private Context mContext;

    private String videoId;

    public MainRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnRecyclerViewItemClickListener() != null) {
                    getOnRecyclerViewItemClickListener().onItemClick(v, (Integer) v.getTag());
                }
                if (onMainItemClickListener != null) {
                    onMainItemClickListener.onClickItem(MainRecyclerAdapter.this, (Integer) v.getTag());
                }
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder h = (ViewHolder) holder;
            h.itemView.setTag(position);
            if (getDatas().get(position).getImg_s() == null || getDatas().get(position).getImg_s().equals("")) {
                h.icPreviewImg.setVisibility(View.GONE);
                h.ic_preview_video1.setVisibility(View.VISIBLE);
                if (getDatas().get(position).getImage_url() == null || getDatas().get(position).getImage_url().equals("")) {

                } else {
                    BitMapUtil.loadImage(mContext, getDatas().get(position).getImage_url(), h.ic_preview_video);
//                    videoId = getDatas().get(position).getVideo_id();
                }
            } else {
                h.icPreviewImg.setVisibility(View.VISIBLE);
                h.ic_preview_video1.setVisibility(View.GONE);
                BitMapUtil.loadImage(mContext, getDatas().get(position).getImg_s(), h.icPreviewImg);
            }
            h.tvName.setText(getDatas().get(position).getUser().toString());
            h.tvContent.setText(getDatas().get(position).getContent().toString());
            Glide.with(h.itemView.getContext()).load(getDatas().get(position).getUser().getHead_img()).placeholder(R.drawable.ic_head_load).into(h.icHead);
            h.tvName.setText(getDatas().get(position).getUser().getNickname());
            h.tvZan.setText(getDatas().get(position).getThing_num());
            h.tvPinglun.setText(getDatas().get(position).getComment_num());
            h.tvJuli.setText(Double.valueOf(getDatas().get(0).getJuli()) / 10 + "km");
            h.tvAite.setText(getDatas().get(position).getUser().get_$36() + "");
            h.tvTime.setText(getDatas().get(position).getInsert_time());
            if ("".equals(getDatas().get(position).getThing())) {
                return;
            }
            if ("0".equals(getDatas().get(position).getThing())) {
                BitMapUtil.loadImage(mContext, R.drawable.ic_zan, h.icZan);
            }
            if ("1".equals(getDatas().get(position).getThing())) {
                BitMapUtil.loadImage(mContext, R.drawable.ic_zan_select, h.icZan);
            }
            if (onItemButtonListener != null) {
                h.rlAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.juli(MainRecyclerAdapter.this, position);
                    }
                });
                h.rlDianzan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.zan(MainRecyclerAdapter.this, position);
                    }
                });

                h.rlPinglun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.pinglun(MainRecyclerAdapter.this, position);
                    }
                });
                h.icPreviewImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.imgPreview(MainRecyclerAdapter.this, h.icPreviewImg, position);
                    }
                });
                h.ic_preview_video1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.play(MainRecyclerAdapter.this,position);
                    }
                });
                h.icMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.more(MainRecyclerAdapter.this, position);
                    }
                });
                h.icHead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemButtonListener.head(MainRecyclerAdapter.this, h.icHead, position);
                    }
                });

            }
        }
    }

    public void setOnItemButtonListener(OnItemButtonListener onItemButtonListener) {
        this.onItemButtonListener = onItemButtonListener;
    }

    public void setOnMainItemClickListener(OnMainItemClickListener onMainItemClickListener) {
        this.onMainItemClickListener = onMainItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ic_head)
        ImageView icHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_aite)
        TextView tvAite;
        @BindView(R.id.tv_time)
        TextView tvTime;
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
        @BindView(R.id.ic_more)
        ImageView icMore;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
