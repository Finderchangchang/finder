package com.example.guojiawei.finderproject.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.DetailsAdapter;
import com.example.guojiawei.finderproject.adapter.base.BaseRecyclerViewAdapater;
import com.example.guojiawei.finderproject.adapter.listener.OnItemButtonListener;
import com.example.guojiawei.finderproject.adapter.listener.OnRecyclerViewItemClickListener;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.entity.DetailsEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.ui.Video_playAty;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.SharedPreferencesUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.MessageDetailDividerItemDecoration;
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
 * Created by guojiawei on 2017/11/10.
 */

public class DetailActivity extends BaseActivity {
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.iv_preview_img)
    PhotoView ivPreviewImg;

    private DetailsAdapter detailsAdapter;
    private String lat = "";
    private String lon = "";
    private String moodId = "";
    private Info mInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        lat = (String) SharedPreferencesUtil.getData(this, Constant.TAG_LAT, "");
        lon = (String) SharedPreferencesUtil.getData(this, Constant.TAG_LON, "");
        moodId = getIntent().getStringExtra(Constant.TAG_MOOD_ID);
        setRecyclerView();
        detailsAdapter = new DetailsAdapter(DetailActivity.this);

        getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);

        detailsAdapter.setOnItemButtonListener(new OnItemButtonListener() {
            @Override
            public void head(BaseRecyclerViewAdapater adapaterm, View v, int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;
                DetailsEntity.HeadEntity.DataBean bean = adapter.getHeadEntity().getData();
                final ImageView pv = (ImageView) v;
                //设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
                ivPreviewImg.setImageDrawable(null);
                // BitMapUtil.loadImage(MainActivity.this, pv.getDrawingCache(), ivPreviewImg);
                Glide.with(DetailActivity.this).load(bean.getUser().getHead_img()).asBitmap().into(new SimpleTarget<Bitmap>() {
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

            }

            @Override
            public void zan(BaseRecyclerViewAdapater adapaterm, int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;
                DetailsEntity.HeadEntity.DataBean bean = adapter.getHeadEntity().getData();
                dianZan(UserStatusUtil.getUserId(), bean.getId());

                String thing = bean.getThing();
                if (!UserStatusUtil.isLogin()) {
                    startActivity(new Intent(DetailActivity.this, LoginActivity.class));
                    return;
                }

                if (thing.equals("0")) {
                    bean.setThing("1");
                    bean.setThing_num(Integer.valueOf(bean.getThing_num()) + 1 + "");
                    adapaterm.notifyItemChanged(position);
                }
                if (thing.equals("1")) {
                    bean.setThing("0");
                    bean.setThing_num(Integer.valueOf(bean.getThing_num()) - 1 + "");
                    adapaterm.notifyItemChanged(position);
                }

            }

            public void play(BaseRecyclerViewAdapater adapaterm, int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;
                String videoid = adapter.getHeadEntity().getData().getVideo_id();
                Intent intent = new Intent(DetailActivity.this, Video_playAty.class);
                intent.putExtra("videoid", videoid);
                startActivity(intent);

            }

            @Override
            public void pinglun(BaseRecyclerViewAdapater adapaterm, int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;
                if (UserStatusUtil.isLogin()) {
                    DetailsEntity.HeadEntity.DataBean bean = adapter.getHeadEntity().getData();
                    startActivityForResult(new Intent(DetailActivity.this, ReplyMessageActivity.class)
                            .putExtra(Constant.TAG_MOOD_ID, bean.getId())
                            .putExtra(Constant.TAG_COMMENT_ID, ""), 11);
                } else {
                    startActivity(new Intent(DetailActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void more(BaseRecyclerViewAdapater adapaterm, final int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;

                final DetailsEntity.HeadEntity.DataBean bean = adapter.getHeadEntity().getData();
                if (bean.getUser_id().equals(UserStatusUtil.getUserId())) {
                    final DialogSelector dialogSelector = new DialogSelector(DetailActivity.this);
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
                    final DialogSelector dialogSelector = new DialogSelector(DetailActivity.this);
                    dialogSelector.setTitle("举报");
                    dialogSelector.setListener(new DialogSelectorListener() {
                        @Override
                        public void onSure() {
                            startActivity(new Intent(DetailActivity.this, ReplyJuBaoActivity.class)
                                    .putExtra(Constant.TAG_MOOD_ID, bean.getId()));
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
            public void juli(BaseRecyclerViewAdapater adapaterm, int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;
                DetailsEntity.HeadEntity.DataBean bean = adapter.getHeadEntity().getData();
                startActivity(new Intent(DetailActivity.this, MapImageActivity.class)
                        .putExtra(Constant.TAG_LAT, Double.valueOf(bean.getLatitude()))
                        .putExtra(Constant.TAG_LON, Double.valueOf(bean.getLongitude()))
                        .putExtra("url", bean.getImg_s())
                        .putExtra("url_s", bean.getImg())
                        .putExtra("content", bean.getContent()));
            }

            @Override
            public void imgPreview(BaseRecyclerViewAdapater adapaterm, View v, int position) {
                DetailsAdapter adapter = (DetailsAdapter) adapaterm;
                DetailsEntity.HeadEntity.DataBean bean = adapter.getHeadEntity().getData();
                final ImageView pv = (ImageView) v;
                //设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
                ivPreviewImg.setImageDrawable(null);
                // BitMapUtil.loadImage(MainActivity.this, pv.getDrawingCache(), ivPreviewImg);
                Glide.with(DetailActivity.this).load(bean.getImg()).asBitmap().into(new SimpleTarget<Bitmap>() {
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
            }
        });

        detailsAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                if (!UserStatusUtil.getUserId().equals(detailsAdapter.getDatas().get(position).getUser_id())) {
                    startActivity(new Intent(DetailActivity.this, ReplyMessageActivity.class)
                            .putExtra(Constant.TAG_MOOD_ID, detailsAdapter.getDatas().get(position).getMood_id())
                            .putExtra(Constant.TAG_COMMENT_ID, detailsAdapter.getDatas().get(position).getId())
                            .putExtra("name", detailsAdapter.getDatas().get(position).getUser().getNickname())
                            .putExtra("reid", detailsAdapter.getDatas().get(position).getUser_id())
                            .putExtra("nickname", detailsAdapter.getDatas().get(position).getUser().getNickname()));

                } else {
                    final DialogSelector dialogSelector = new DialogSelector(DetailActivity.this);
                    dialogSelector.setTitle("删除");
                    dialogSelector.setListener(new DialogSelectorListener() {
                        @Override
                        public void onSure() {
                            delMessage(UserStatusUtil.getUserId(), detailsAdapter.getDatas().get(position).getId(), position);
                        }

                        @Override
                        public void onCancel() {
                            dialogSelector.dismiss();
                        }
                    });
                    dialogSelector.show();
                }
            }
        });

        detailsAdapter.setOnReplyMessageHeadOnclickListener(new DetailsAdapter.OnReplyMessageHeadOnclickListener() {
            @Override
            public void onClickHead(int position) {
                if (!UserStatusUtil.getUserId().equals(detailsAdapter.getDatas().get(position).getUser_id())) {
                    startActivity(new Intent(getContext(), PeoplePublishHistoryActivity.class)
                            .putExtra("userid", detailsAdapter.getDatas().get(position).getUser_id())
                            .putExtra("name", detailsAdapter.getDatas().get(position).getUser().getNickname()));

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);
        }
    }

    @OnClick(R.id.ic_back)
    public void onViewClicked() {
        finish();
    }

    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        return view;
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MessageDetailDividerItemDecoration(this, MessageDetailDividerItemDecoration.VERTICAL_LIST));
        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        // add headerView
        swipeRefreshLayout.setHeaderView(createHeaderView());
        //下拉刷新
        setOnPullRefresh();


    }

    private void setOnPullRefresh() {
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
                        swipeRefreshLayout.setRefreshing(false);
                        getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);
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


    private void getMoodDetatils(String id, String user_id, String latitude, String longitude) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("user_id", user_id);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.MOOD_INFO)
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

                        DetailsEntity.HeadEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), DetailsEntity.HeadEntity.class);
                        detailsAdapter.setHeadEntity(entity);
                        recyclerView.setAdapter(detailsAdapter);
                        getMoodMessage("1", "10", entity.getData().getId());
                    }
                });
    }


    private void getMoodMessage(String page, String rows, String mood_id) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("mood_id", mood_id);
        params.put("comment", "0");
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.MOOD_MESSAGE)
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
                        showToast("发送失败，请重试");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        DetailsEntity.MessEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), DetailsEntity.MessEntity.class);
                        if (entity.getCode() == 1) {
                            if (entity.getData() != null) {
                                detailsAdapter.refreshDatas(entity.getData().getRows());
                            }
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
                            finish();
                            //mainRecyclerAdapter.remove(postion);
                        }
                    }
                });
    }


    private void delMessage(String user_id, String comment_id, final int postion) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("comment_id", comment_id);

        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.DEL_MESSAGE)
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
                            detailsAdapter.remove(postion);
                        }
                    }
                });
    }
}
