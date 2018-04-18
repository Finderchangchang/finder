package com.example.guojiawei.finderproject.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.PreviewPagerAdapter;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.MapModel;
import com.example.guojiawei.finderproject.entity.UserIdEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.ui.Video_playAty;
import com.example.guojiawei.finderproject.ui.fragment.ContentFragment;
import com.example.guojiawei.finderproject.ui.fragment.ImageFragment;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.CornersTransform;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.MapDistance;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.RoundImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/16.
 */

public class MapImageActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.map_content)
    RelativeLayout mapContent;
    @BindView(R.id.mTexturemap)
    MapView mMapView;
    @BindView(R.id.zoom_jia)
    ImageView zoomJia;
    @BindView(R.id.zoom_jian)
    ImageView zoomJian;
    @BindView(R.id.local)
    ImageView local;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private BaiduMap mBaiduMap;
    private double lat;
    private double lon;
    private String imgUrl;
    private String imgUrls;
    private String content;
    private ImageFragment imageFragment;
    private ContentFragment contentFragment;
    private PreviewPagerAdapter adapter;
    private List<Fragment> datas;
    private GestureDetector tapGestureDetector;

    private String video_id;
    private String img_url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
    }

    boolean is_location = false;//true定位成功 地图不再描点

    @Override
    public void initViews(Bundle savedInstanceState) {
        setMap();
        lat = getIntent().getDoubleExtra(Constant.TAG_LAT, 0);
        lon = getIntent().getDoubleExtra(Constant.TAG_LON, 0);
        imgUrls = getIntent().getStringExtra("url_s");
        imgUrl = getIntent().getStringExtra("url");
        content = getIntent().getStringExtra("content");
        video_id = getIntent().getStringExtra("video_id");
        img_url = getIntent().getStringExtra("Image_url");

        if (lat == 0 || lon == 0) {
            showToast("位置信息获取失败 ");
        } else {
            // setLocation();
            //setMarker();
            loadMaps(lat + "", lon + "");

        }

        setPageClickListener();


    }

    private void setMap() {
        local.setVisibility(View.INVISIBLE);
        mBaiduMap = mMapView.getMap();
        mMapView.showZoomControls(false); // 设置是否显示缩放控件
        mMapView.showScaleControl(false); // 设置是否显示缩放控件
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(18f));
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                LatLng mCenterLatLng = mapStatus.target;
                /**获取经纬度*/
                double lat2 = mCenterLatLng.latitude;
                double lng2 = mCenterLatLng.longitude;
                loadMaps(lat2 + "", lng2 + "");
            }
        });
    }

    private void loadMaps(String lat2, String lng2) {
        //两个经纬度的距离
        //String jl = MapDistance.getInstance().getShortDistance(lat, lon, lat2, lng2);
        Map<String, String> params = new HashMap<>();
        //params.put("user_id", UserStatusUtil.getUserId());
        params.put("latitude", lat2 + "");
        params.put("longitude", lng2 + "");
        params.put("juli", "10000");
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.MOOD_MAP)
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
                        showToast("发送失败");
                    }

                    /**
                     * @param stringResponse
                     */
                    @Override
                    public void onNext(Response<String> stringResponse) {
                        MapModel entity = GsonUtil.GosnToEntity(stringResponse.body(), MapModel.class);
                        if (entity.getData() != null && entity.getData().size() > 0) {
                            mark(entity.getData());
                        }
                    }
                });
    }

    LatLng point;

    public void mark(final List<MapModel.DataBean> list) {
        mBaiduMap.clear();
        setMarker();
        for (int i = 0; i < list.size(); i++) {
            final MapModel.DataBean model = list.get(i);
            Log.i("lat-lng", model.getLatitude() + "--" + model.getLongitude());
            //构建Marker图标

//            //在地图上添加Marker，并显示
//            mBaiduMap.addOverlay(option);
            final View view = LayoutInflater.from(this).inflate(R.layout.view_img_locations, null);
            final RoundImageView iv = (RoundImageView) view.findViewById(R.id.ic_img);
            final int finalI = i;
            Glide.with(MapImageActivity.this).load(model.getImg_s()).asBitmap().
                    transform(new CornersTransform(MapImageActivity.this)).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    BitmapDescriptorFactory.fromBitmap(resource);
                    //构建MarkerOption，用于在地图上添加Marker
                    iv.setImageBitmap(resource);
                    //在地图上添加Marker，并显示
//                    InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), point, -47, new InfoWindow.OnInfoWindowClickListener() {
//                        @Override
//                        public void onInfoWindowClick() {
//                            initFragment(imgUrls, content);
//                            viewpager.setVisibility(View.VISIBLE);
//                        }
//                    });
//                    BitmapDescriptor bdOpen_iv =BitmapDescriptorFactory.fromBitmap(resource);
//                    iv.setAlpha(0.5f);
                    BitmapDescriptor bdOpen_iv =
                            BitmapDescriptorFactory.fromView(view);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_logo144);
                    MapModel.DataBean model = list.get(finalI);
                    point = new LatLng(Double.parseDouble(model.getLatitude()), Double.parseDouble(model.getLongitude()));

//                    OverlayOptions option = new MarkerOptions()
//                            .position(point)
//                            .icon(bitmap);
//                    mBaiduMap.addOverlay(option);

                    OverlayOptions option1 = new MarkerOptions()
                            .position(point)
                            .icon(bdOpen_iv);
//                            .icon(BitmapDescriptorFactory.fromBitmap(resource));
                    //显示InfoWindow
                    //mBaiduMap.showInfoWindow(mInfoWindow);
                    mBaiduMap.addOverlay(option1);

                }
            });

        }
    }

    private void setLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .direction(100).latitude(lat)
                .longitude(lon).build();

        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_normal_gps);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);

    }


    private void setMarker() {
        final View view = LayoutInflater.from(this).inflate(R.layout.view_img_location, null);
        final RoundImageView iv = (RoundImageView) view.findViewById(R.id.ic_img);
        final ImageView video_iv = (ImageView) view.findViewById(R.id.video_iv);
        final RelativeLayout ic_preview_video1 = (RelativeLayout) view.findViewById(R.id.ic_preview_video1);
        final RoundImageView ic_preview_video = (RoundImageView) view.findViewById(R.id.ic_preview_video);

        //定义用于显示该InfoWindow的坐标点
        final LatLng ll = new LatLng(lat, lon);
        if (TextUtils.isEmpty(img_url)) img_url = imgUrl;
        Glide.with(MapImageActivity.this).load(img_url).asBitmap().transform(new CornersTransform(MapImageActivity.this))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        iv.setImageBitmap(resource);
                        if (video_id == null || video_id.equals("")) {
                            //iv.setVisibility(View.VISIBLE);
                            video_iv.setVisibility(View.GONE);
                            InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), ll, -47, new InfoWindow.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick() {
                                    initFragment(imgUrls, content);
                                    viewpager.setVisibility(View.VISIBLE);
                                }
                            });
                            //显示InfoWindow
                            mBaiduMap.showInfoWindow(mInfoWindow);
                        } else {
                            //iv.setVisibility(View.GONE);
                            video_iv.setVisibility(View.VISIBLE);
                            //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                            InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), ll, -47, new InfoWindow.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick() {
                                    String videoid = video_id;
                                    Intent intent = new Intent(MapImageActivity.this, Video_playAty.class);
                                    intent.putExtra("videoid", videoid);
                                    startActivity(intent);
                                }
                            });
                            //显示InfoWindow
                            mBaiduMap.showInfoWindow(mInfoWindow);
                        }
                        //定义Maker坐标点


                    }
                });


//        Glide.with(MapImageActivity.this).load(imgUrl).asBitmap().transform(new CornersTransform(MapImageActivity.this)).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                iv.setImageBitmap(resource);
//                //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
//                InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), ll, -47, new InfoWindow.OnInfoWindowClickListener() {
//                    @Override
//                    public void onInfoWindowClick() {
//                        initFragment(imgUrls, content);
//                        viewpager.setVisibility(View.VISIBLE);
//                    }
//                });
//                //显示InfoWindow
//                mBaiduMap.showInfoWindow(mInfoWindow);
//            }
//        });

        if (!is_location) {
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(new LatLng(lat, lon));
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            is_location = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }


    @OnClick({R.id.zoom_jia, R.id.zoom_jian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zoom_jia:
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                break;
            case R.id.zoom_jian:
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                break;

        }
    }

    private void setPageClickListener() {
        tapGestureDetector = new GestureDetector(this, new TapGestureListener());
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });


    }


    class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // Your Code here
            viewpager.setVisibility(View.GONE);
            return true;
        }
    }

    class FlipHorizontalTransformer extends TransformAdapter {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onTransform(View view, float position) {
            float rotation = 180f * position;

            view.setTranslationX(view.getWidth() * -position);
            view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
            view.setPivotX(view.getWidth() * 0.5f);
            view.setPivotY(view.getHeight() * 0.5f);
            view.setRotationY(rotation);

            if (position > -0.5f && position < 0.5f) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    abstract class TransformAdapter implements ViewPager.PageTransformer {

        private boolean debug = true;

        @Override
        public void transformPage(View page, float position) {

            if (position > 0 && position <= 1) {
                onRightScorlling(page, position);
            } else if (position < 0 && position >= -1) {
                onLeftScorlling(page, position);
            } else if (position == 0) {
                onCenterIdle(page);
            }
            onTransform(page, position);
        }

        /**
         * @param view     right view
         * @param position right to center 1->0
         *                 center to right 0->1
         */
        public void onRightScorlling(View view, float position) {

        }

        /**
         * @param view     left view
         * @param position left to center  -1->0
         *                 center to left  0->-1
         */
        public void onLeftScorlling(View view, float position) {

        }

        public void onCenterIdle(View view) {

        }

        /**
         * @param view     left and right view both callback
         * @param position [-1,1]
         */
        public void onTransform(View view, float position) {

        }

        public void log(Class<? extends MessageDetailActivity.TransformAdapter> clazz, String msg) {
            if (debug) {
                Log.d(clazz.getSimpleName(), msg);
            }
        }
    }

    private void initFragment(String url, String Content) {
        imageFragment = new ImageFragment(url);
        contentFragment = new ContentFragment(Content);
        datas = new ArrayList<>();
        datas.add(imageFragment);
        datas.add(contentFragment);
        initPagerAdapter();
        contentFragment.setOnPagerContentClickListener(new ContentFragment.OnPagerContentClickListener() {
            @Override
            public void onClick() {
                viewpager.setVisibility(View.GONE);
            }
        });
    }

    private void initPagerAdapter() {
        viewpager.setPageTransformer(true, new FlipHorizontalTransformer());
        adapter = new PreviewPagerAdapter(getSupportFragmentManager(), datas);
        viewpager.setAdapter(adapter);
    }
}
