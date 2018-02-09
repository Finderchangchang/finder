package com.example.guojiawei.finderproject.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.MainContentAdapter;
import com.example.guojiawei.finderproject.adapter.MainRecyclerAdapter;
import com.example.guojiawei.finderproject.adapter.base.BaseRecyclerViewAdapater;
import com.example.guojiawei.finderproject.adapter.listener.OnItemButtonListener;
import com.example.guojiawei.finderproject.adapter.listener.OnMainItemClickListener;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.entity.HomeDataEntity;
import com.example.guojiawei.finderproject.entity.NumEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.ui.activity.CenterActivity;
import com.example.guojiawei.finderproject.ui.activity.DetailActivity;
import com.example.guojiawei.finderproject.ui.activity.LoginActivity;
import com.example.guojiawei.finderproject.ui.activity.MapActivity;
import com.example.guojiawei.finderproject.ui.activity.MapImageActivity;
import com.example.guojiawei.finderproject.ui.activity.PeoplePublishHistoryActivity;
import com.example.guojiawei.finderproject.ui.activity.ReplyJuBaoActivity;
import com.example.guojiawei.finderproject.ui.activity.ReplyMessageActivity;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.LocationService;
import com.cjt2325.cameralibrary.SharedPreferencesUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.DividerItemDecoration;
import com.example.guojiawei.finderproject.widget.camera.CameraActivity;
import com.example.guojiawei.finderproject.widget.camera.VideoAcitvity;
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

public class MainActivity extends BaseActivity {

    @BindView(R.id.ic_main_gps)
    ImageView icMainGps;
    @BindView(R.id.tv_main_address)
    TextView tvMainAddress;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.rl_gps)
    RelativeLayout rlGps;
    @BindView(R.id.ic_login_state)
    ImageView icLoginState;
    @BindView(R.id.main_bar)
    RelativeLayout mainBar;
    @BindView(R.id.iv_preview_img)
    PhotoView ivPreviewImg;
    @BindView(R.id.message_num)
    TextView messageNum;
    public static MainActivity main;
    private MainContentAdapter mainRecyclerAdapter;
    /**
     * 百度地图定位服务
     */
    public LocationService mLocationService = null;
    /**
     * lat
     */
    private double mLat;
    /**
     * lon
     */
    private double mLon;
    /**
     * address info
     */
    private String mGpsInfo = "";
    /**
     * 首次加载页数
     */
    private int default_page = 1;
    private int page = 1;
    private Info mInfo;
    private TimeCount timeCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        main = this;
        setCommentNum();
        //设置首页列表
        setRecyclerView();
        //启动百度地图定位
        initLocation();
        //启动定时轮询
        timeCount = new TimeCount(3000, 100);
        timeCount.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserStatusUtil.isLogin()) {
            tvCenter.setTextColor(getResources().getColor(R.color.themeColor));
            icLoginState.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_login_select));
        } else {
            tvCenter.setTextColor(getResources().getColor(R.color.black));
            icLoginState.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_normal_login));

        }

    }

    /**
     * create Header View
     *
     * @return 返回一个自定义的下拉刷新头部view
     */
    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        return view;
    }

    private void setRecyclerView() {
        mainRecyclerAdapter = new MainContentAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
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
        mainRecyclerAdapter.setOnMainItemClickListener(new OnMainItemClickListener() {
            @Override
            public void onClickItem(BaseRecyclerViewAdapater madapater, int postion) {
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) madapater;
                startActivityForResult(new Intent(getContext(), DetailActivity.class)
                        .putExtra(Constant.TAG_MOOD_ID, adapter.getDatas().get(postion).getId()), 77);
                SharedPreferencesUtil.saveData(getContext(), "refresh", false);
            }
        });

        /**
         * item 点赞 评论 举报等按钮监听点击
         */

        mainRecyclerAdapter.setOnButtonListener(new OnItemButtonListener() {
            @Override
            public void head(BaseRecyclerViewAdapater adapaterm, View v, int position) {
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                HomeDataEntity.DataBean.RowsBean.ListBean bean = adapter.getDatas().get(position);
                startActivity(new Intent(getContext(), PeoplePublishHistoryActivity.class)
                        .putExtra("userid", bean.getUser_id())
                        .putExtra("name", bean.getUser().getNickname()));
            }

            @Override
            public void zan(BaseRecyclerViewAdapater adapaterm, int position) {
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                HomeDataEntity.DataBean.RowsBean.ListBean bean = adapter.getDatas().get(position);
                dianZan(UserStatusUtil.getUserId(), bean.getId());

                String thing = bean.getThing();
                if (!UserStatusUtil.isLogin()) {
                    startActivityForResult(new Intent(getContext(), LoginActivity.class), 100);
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
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                String videoid = adapter.getDatas().get(position).getVideo_id();
                Intent intent = new Intent(MainActivity.this, Video_playAty.class);
                intent.putExtra("videoid", videoid);
                startActivity(intent);

            }

            @Override
            public void pinglun(BaseRecyclerViewAdapater adapaterm, int position) {
                if (UserStatusUtil.isLogin()) {
                    MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                    HomeDataEntity.DataBean.RowsBean.ListBean bean = adapter.getDatas().get(position);
                    startActivityForResult(new Intent(getContext(), ReplyMessageActivity.class)
                            .putExtra(Constant.TAG_MOOD_ID, bean.getId())
                            .putExtra(Constant.TAG_COMMENT_ID, ""), 2);
                } else {
                    startActivityForResult(new Intent(getContext(), LoginActivity.class), 100);
                }

            }

            @Override
            public void more(BaseRecyclerViewAdapater adapaterm, final int position) {
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                final HomeDataEntity.DataBean.RowsBean.ListBean bean = adapter.getDatas().get(position);
                if (bean.getUser_id().equals(UserStatusUtil.getUserId())) {
                    final DialogSelector dialogSelector = new DialogSelector(getContext());
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
                    final DialogSelector dialogSelector = new DialogSelector(getContext());
                    dialogSelector.setTitle("举报");
                    dialogSelector.setListener(new DialogSelectorListener() {
                        @Override
                        public void onSure() {
                            if (UserStatusUtil.isLogin()) {
                                startActivity(new Intent(getContext(), ReplyJuBaoActivity.class)
                                        .putExtra(Constant.TAG_MOOD_ID, bean.getId()));
                            } else {
                                startActivityForResult(new Intent(getContext(), LoginActivity.class), 100);
                            }
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
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                HomeDataEntity.DataBean.RowsBean.ListBean bean = adapter.getDatas().get(position);
                startActivity(new Intent(getContext(), MapImageActivity.class)
                        .putExtra(Constant.TAG_LAT, Double.valueOf(bean.getLatitude()))
                        .putExtra(Constant.TAG_LON, Double.valueOf(bean.getLongitude()))
                        .putExtra("url", bean.getImg_s())
                        .putExtra("url_s", bean.getImg())
                        .putExtra("Image_url", bean.getImage_url())
                        .putExtra("video_id", bean.getVideo_id())
                        .putExtra("content", bean.getContent()));


            }

            @Override
            public void imgPreview(BaseRecyclerViewAdapater adapaterm, View v, int position) {

                final ImageView pv = (ImageView) v;
                //设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
                pv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //获取img1的信息
                        mInfo = PhotoView.getImageViewInfo((ImageView) v);
                        ivPreviewImg.setVisibility(View.VISIBLE);
                        ivPreviewImg.animaFrom(mInfo);
                    }
                });

                //获取img1的信息
                mInfo = PhotoView.getImageViewInfo(pv);
                ivPreviewImg.setVisibility(View.VISIBLE);
                ivPreviewImg.animaFrom(mInfo);
                MainRecyclerAdapter adapter = (MainRecyclerAdapter) adapaterm;
                HomeDataEntity.DataBean.RowsBean.ListBean bean = adapter.getDatas().get(position);
                ivPreviewImg.setImageDrawable(null);
                Glide.with(getContext()).load(bean.getImg()).asBitmap().into(new SimpleTarget<Bitmap>() {
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
//        icLoginState.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (UserStatusUtil.isLogin()) {
//                    startActivityForResult(new Intent(MainActivity.this, VideoAcitvity.class)
//                            .putExtra(Constant.TAG_LAT, mLat)
//                            .putExtra(Constant.TAG_LON, mLon), 2);
//                    SharedPreferencesUtil.saveData(getContext(), "refresh", false);
//
//                } else {
//                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class)
//                            .putExtra(Constant.TAG_LAT, mLat)
//                            .putExtra(Constant.TAG_LON, mLon), 100);
//                }
//                return true;
//            }
//        });
    }

    private void setOnPullRefresh() {
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
                        page = 1;
                        loadHomeData(default_page);
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
                        loadHomeData(page);

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


    private void loadHomeData(final int page) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", UserStatusUtil.getUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("latitude", mLat + "");
        params.put("longitude", mLon + "");
        EncryptUtil.EncryptAutoSort(params);


        Observable<Response<String>> observable = OkGo.<String>post(API.HOME)
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
                        HomeDataEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), HomeDataEntity.class);
                        if (entity.getCode() == 1) {
                            swipeRefreshLayout.setRefreshing(false);
                            swipeRefreshLayout.setLoadMore(false);
                            if (page == 1) {
                                mainRecyclerAdapter.refreshDatas(entity.getData().getRows());
                            } else {
                                mainRecyclerAdapter.addItems(entity.getData().getRows());
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
                            mainRecyclerAdapter.remove(postion);
                        }
                    }
                });
    }

    @OnClick({R.id.tv_center, R.id.ic_login_state, R.id.rl_gps})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_gps:
                startActivityForResult(new Intent(this, MapActivity.class)
                        .putExtra(Constant.TAG_LAT, mLat)
                        .putExtra(Constant.TAG_LON, mLon)
                        .putExtra(Constant.TAG_ADDRESS, mGpsInfo), 100);
                break;
            case R.id.tv_center:
                if (UserStatusUtil.isLogin()) {
                    startActivity(new Intent(this, CenterActivity.class));
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class)
                            .putExtra(Constant.TAG_LAT, mLat)
                            .putExtra(Constant.TAG_LON, mLon), 100);
                }
                break;
            case R.id.ic_login_state:
                if (UserStatusUtil.isLogin()) {
                    startActivityForResult(new Intent(MainActivity.this, VideoAcitvity.class)
                            .putExtra(Constant.TAG_LAT, mLat)
                            .putExtra(Constant.TAG_LON, mLon), 2);
                    SharedPreferencesUtil.saveData(getContext(), "refresh", false);
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class)
                            .putExtra(Constant.TAG_LAT, mLat)
                            .putExtra(Constant.TAG_LON, mLon), 100);
                }
                break;
            default:
                break;
        }
    }


    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
    //原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
    public void initLocation() {
        mLocationService = new LocationService(getApplicationContext());
        mLocationService.registerListener(mListener);
        mLocationService.setLocationOption(mLocationService.getDefaultLocationClientOption());
        mLocationService.start();

    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mLocationService.stop();
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                tvMainAddress.setText(location.getLocationDescribe().replace("在", "").replace("附近", ""));
                mGpsInfo = location.getLocationDescribe();
                mLat = location.getLatitude();
                mLon = location.getLongitude();

                loadHomeData(default_page);

                SharedPreferencesUtil.saveData(getContext(), Constant.TAG_LAT, mLat + "");
                SharedPreferencesUtil.saveData(getContext(), Constant.TAG_LON, mLon + "");
            } else {
                loadHomeData(default_page);
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 1000) {
            mLat = Double.valueOf((String) SharedPreferencesUtil.getData(getContext(), Constant.TAG_LAT, ""));
            mLon = Double.valueOf((String) SharedPreferencesUtil.getData(getContext(), Constant.TAG_LON, ""));
            mGpsInfo = SharedPreferencesUtil.getData(getContext(), Constant.TAG_LOCAL_NAME, "").toString().replace("附近", "");
            tvMainAddress.setText(mGpsInfo);
            loadHomeData(1);
        }
        if (requestCode == 100 && resultCode == 1001) {
            mLocationService.start();
        }
        if (requestCode == 1 && resultCode == 1) {//相机跳转到摄像
            startActivityForResult(new Intent(MainActivity.this, VideoAcitvity.class)
                    .putExtra(Constant.TAG_LAT, mLat)
                    .putExtra(Constant.TAG_LON, mLon), 2);
            SharedPreferencesUtil.saveData(getContext(), "refresh", false);
        }
        if (requestCode == 2 && resultCode == 2) {//摄像跳转到相机
            startActivityForResult(new Intent(MainActivity.this, CameraActivity.class)
                    .putExtra(Constant.TAG_LAT, mLat)
                    .putExtra(Constant.TAG_LON, mLon), 1);
            SharedPreferencesUtil.saveData(getContext(), "refresh", false);

        }
        if (requestCode == 77 || requestCode == 2) {
            page = 1;
            loadHomeData(page);
        }

    }

    private void setCommentNum() {
        //判断我的消息数量
        if (UserStatusUtil.getCommentNum().equals("0")) {
            messageNum.setVisibility(View.GONE);
        } else {
            messageNum.setVisibility(View.VISIBLE);
            messageNum.setText(UserStatusUtil.getCommentNum());
        }
    }

    class TimeCount extends CountDownTimer {
        private TextView tvGetcode;

        public TimeCount(long millisInFuture, long countDownInterval) {
            // 参数依次为总时长,和计时的时间间隔
            super(millisInFuture, countDownInterval);
        }

        // 计时完毕时触发
        @Override
        public void onFinish() {
            if (UserStatusUtil.isLogin()) {
                getCommentNum(UserStatusUtil.getUserId());
                setCommentNum();
            }
            timeCount.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示

        }
    }

    private void getCommentNum(String user_id) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.COMMENT_NUM)
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
                        NumEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), NumEntity.class);
                        if (entity.getCode() == 1) {
                            UserStatusUtil.setCommentNum(entity.getData());
                        }
                    }
                });
    }
}
