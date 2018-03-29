package com.example.guojiawei.finderproject.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.PublishRecyclerAdapter;
import com.example.guojiawei.finderproject.adapter.listener.OnPublishItemButtonListener;
import com.example.guojiawei.finderproject.adapter.listener.OnRecyclerViewItemClickListener;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.entity.PublishEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.ui.Video_playAty;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.cjt2325.cameralibrary.SharedPreferencesUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.DividerItemDecoration;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelector;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelectorListener;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/9.
 */

public class PeoplePublishHistoryActivity extends BaseActivity {
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.iv_preview_img)
    PhotoView ivPreviewImg;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rl_messages)
    RelativeLayout rlMessages;
    @BindView(R.id.main_bar)
    RelativeLayout mainBar;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    boolean right=false;

    private PublishRecyclerAdapter mainRecyclerAdapter;

    //lat
    private String mLat;
    //lon
    private String mLon;
    //address info
    private String mGpsInfo = "";
    //首次加载页数
    private int default_page = 1;

    private int page = 1;

    private Info mInfo;
    private String userId = "";
    private String nickname = "";
    ImageView right_iv;
    ImageView right_ivs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_history;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        setRecyclerView();
        userId = getIntent().getStringExtra("userid");
        nickname = getIntent().getStringExtra("name");
        mLat = (String) SharedPreferencesUtil.getData(this, Constant.TAG_LAT, "");
        mLon = (String) SharedPreferencesUtil.getData(this, Constant.TAG_LON, "");
        right_iv= (ImageView) findViewById(R.id.right_iv);
        right_ivs= (ImageView) findViewById(R.id.right_ivs);
        loadHistoryData(userId, default_page);
        title.setText(nickname);
        right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PeoplePublishHistoryActivity.this, MapImageActivity.class)
                        .putExtra(Constant.TAG_LAT, Double.valueOf(mLat))
                        .putExtra(Constant.TAG_LON, Double.valueOf(mLon))
                        .putExtra("url", ""));
            }
        });
        right_iv.setVisibility(View.GONE);
        right_ivs.setVisibility(View.VISIBLE);
    }

    /**
     * create Header View
     */
    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        return view;
    }

    private void setRecyclerView() {
        mainRecyclerAdapter = new PublishRecyclerAdapter(PeoplePublishHistoryActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mainRecyclerAdapter);


        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        // add headerView
        swipeRefreshLayout.setHeaderView(createHeaderView());
        // add FooterView
        swipeRefreshLayout.setFooterView(createHeaderView());
        //下拉刷新
        setOnPullRefresh();
        //上拉加载更多
        setOnPushLoadMore();

        /**
         * item 整个item点击监听
         */
        mainRecyclerAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(new Intent(PeoplePublishHistoryActivity.this, DetailActivity.class)
                        .putExtra(Constant.TAG_MOOD_ID, mainRecyclerAdapter.getDatas().get(position).getId()));
            }
        });

        /**
         * item 点赞 评论 举报等按钮监听点击
         */
        mainRecyclerAdapter.setOnItemButtonListener(new OnPublishItemButtonListener() {
            @Override
            public void head(int position) {

            }

            @Override
            public void zan(int position) {
                PublishEntity.DataBean.RowsBean bean = mainRecyclerAdapter.getDatas().get(position);

                dianZan(UserStatusUtil.getUserId(), bean.getId());
                String thing = mainRecyclerAdapter.getDatas().get(position).getThing();
                if (!UserStatusUtil.isLogin()) {
                    startActivity(new Intent(PeoplePublishHistoryActivity.this, LoginActivity.class));
                    return;
                }
                if ("0".equals(thing)) {
                    mainRecyclerAdapter.getDatas().get(position).setThing("1");
                    mainRecyclerAdapter.getDatas().get(position).setThing_num(Integer.valueOf(mainRecyclerAdapter.getDatas().get(position).getThing_num()) + 1 + "");
                    mainRecyclerAdapter.notifyItemChanged(position);
                }
                if ("1".equals(thing)) {
                    mainRecyclerAdapter.getDatas().get(position).setThing("0");
                    mainRecyclerAdapter.getDatas().get(position).setThing_num(Integer.valueOf(mainRecyclerAdapter.getDatas().get(position).getThing_num()) - 1 + "");

                    mainRecyclerAdapter.notifyItemChanged(position);
                }
            }
            public void play(int position) {
                PublishEntity.DataBean.RowsBean bean = mainRecyclerAdapter.getDatas().get(position);
                String videoid = bean.getVideo_id();
                Intent intent = new Intent(PeoplePublishHistoryActivity.this, Video_playAty.class);
                intent.putExtra("videoid", videoid);
                startActivity(intent);

            }
            @Override
            public void pinglun(int position) {
                PublishEntity.DataBean.RowsBean bean = mainRecyclerAdapter.getDatas().get(position);
                startActivity(new Intent(PeoplePublishHistoryActivity.this, ReplyMessageActivity.class)
                        .putExtra(Constant.TAG_MOOD_ID, bean.getId())
                        .putExtra(Constant.TAG_COMMENT_ID, ""));
            }

            @Override
            public void more(final int position) {
                final PublishEntity.DataBean.RowsBean bean = mainRecyclerAdapter.getDatas().get(position);
                if (bean.getUser_id().equals(UserStatusUtil.getUserId())) {
                    final DialogSelector dialogSelector = new DialogSelector(PeoplePublishHistoryActivity.this);
                    dialogSelector.setTitle("删除");
                    dialogSelector.setListener(new DialogSelectorListener() {
                        @Override
                        public void onSure() {
                            del(UserStatusUtil.getUserId(), bean.getId(), position);
                        }

                        @Override
                        public void onCancel() {
                            dialogSelector.dismiss();
                        }
                    });
                    dialogSelector.show();
                } else {
                    final DialogSelector dialogSelector = new DialogSelector(PeoplePublishHistoryActivity.this);
                    dialogSelector.setTitle("举报");
                    dialogSelector.setListener(new DialogSelectorListener() {
                        @Override
                        public void onSure() {
                            report(UserStatusUtil.getUserId(), bean.getId(), "", bean.getContent());
                        }

                        @Override
                        public void onCancel() {
                            dialogSelector.dismiss();
                        }
                    });
                    dialogSelector.show();
                }
            }

            @Override
            public void juli(int position) {
                PublishEntity.DataBean.RowsBean bean = mainRecyclerAdapter.getDatas().get(position);
                startActivity(new Intent(PeoplePublishHistoryActivity.this, MapImageActivity.class)
                        .putExtra(Constant.TAG_LAT, Double.valueOf(bean.getLatitude()))
                        .putExtra(Constant.TAG_LON, Double.valueOf(bean.getLongitude()))
                        .putExtra("url", bean.getImg_s()));
            }

            @Override
            public void imgPreview(View v, int position) {
                final ImageView pv = (ImageView) v;
                //设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
                pv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //获取img1的信息
                        mInfo = PhotoView.getImageViewInfo(pv);
                        ivPreviewImg.setVisibility(View.VISIBLE);
                        ivPreviewImg.animaFrom(mInfo);
                    }
                });
                PublishEntity.DataBean.RowsBean bean = mainRecyclerAdapter.getDatas().get(position);
                Glide.with(PeoplePublishHistoryActivity.this).load(bean.getImg()).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        ivPreviewImg.setImageBitmap(resource);
                        //获取img1的信息
                        mInfo = PhotoView.getImageViewInfo(pv);
                        ivPreviewImg.setVisibility(View.VISIBLE);
                        ivPreviewImg.animaFrom(mInfo);
                    }
                });
                ivPreviewImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 让img2从自身位置变换到原来img1图片的位置大小
                        ivPreviewImg.animaTo(mInfo, new Runnable() {
                            @Override
                            public void run() {
                                ivPreviewImg.setVisibility(View.GONE);
                            }
                        });
                    }
                });
                ivPreviewImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 让img2从自身位置变换到原来img1图片的位置大小
                        ivPreviewImg.animaTo(mInfo, new Runnable() {
                            @Override
                            public void run() {
                                ivPreviewImg.setVisibility(View.GONE);
                            }
                        });
                    }
                });
            }
        });

    }

    private void setOnPullRefresh() {
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
                        page = 1;
                        loadHistoryData(userId, default_page);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        //TODO 下拉距离
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        //TODO 下拉过程中，下拉的距离是否足够出发刷新
                    }
                });
    }

    private void setOnPushLoadMore() {
        swipeRefreshLayout
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

                    @Override
                    public void onLoadMore() {
                        page++;
                        loadHistoryData(userId, page);

                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        //TODO 上拉过程中，上拉的距离是否足够出发刷新
                    }

                    @Override
                    public void onPushDistance(int distance) {
                        // TODO 上拉距离

                    }

                });
    }


    private void loadHistoryData(String user_id, final int page) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("my_user_id", UserStatusUtil.getUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("latitude", mLat + "");
        params.put("longitude", mLon + "");
        EncryptUtil.EncryptAutoSort(params);


        Observable<Response<String>> observable = OkGo.<String>post(API.MY_MOOD)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        PublishEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), PublishEntity.class);
                        if (entity.getCode() == 1 && entity.getData().getRows().size() > 0) {
                            swipeRefreshLayout.setRefreshing(false);
                            tvNothing.setVisibility(View.GONE);
                            swipeRefreshLayout.setLoadMore(false);
                            swipeRefreshLayout.setVisibility(View.VISIBLE);
                            if (page == 1) {
                                mainRecyclerAdapter.refreshDatas(entity.getData().getRows());
                            } else {
                                mainRecyclerAdapter.addItems(entity.getData().getRows());
                            }

                        }
                    }
                });
    }


    private void report(String user_id, String mood_id, String comment_id, String content) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("mood_id", mood_id);
        params.put("content", content);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.REPORT)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("举报失败，请重试");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("举报成功");
                        }
                    }
                });
    }

    private void dianZan(String user_id, String mood_id) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("mood_id", mood_id);

        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.ZAN)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("网络错误，请重试");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {

                        }
                    }
                });
    }

    private void del(String user_id, String mood_id, final int postion) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("mood_id", mood_id);

        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.DEL_CONTENT)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("网络错误，请重试");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("删除成功");
                            mainRecyclerAdapter.remove(postion);
                        }
                    }
                });
    }


    @OnClick({R.id.im_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                finish();
                break;

        }
    }


}
