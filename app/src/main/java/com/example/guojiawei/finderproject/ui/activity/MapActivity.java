package com.example.guojiawei.finderproject.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.MapModel;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.util.Constant;
import com.cjt2325.cameralibrary.SharedPreferencesUtil;
import com.example.guojiawei.finderproject.util.CornersTransform;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.RoundImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.baidu.mapapi.map.InfoWindow.*;
import static com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;


/**
 * Created by guojiawei on 2017/11/12.
 */

public class MapActivity extends BaseActivity {
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
    private BaiduMap mBaiduMap;

    private double lat;
    private double lon;


    private String gpsInfo;
    private GeoCoder mSearch; // 搜索模块，也可去掉地图模块独立使用
    private String changeLocalName = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        setMap();
    }

    private void setMap() {
        mSearch = GeoCoder.newInstance();
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(18f));
        mMapView.showZoomControls(false); // 设置是否显示缩放控件
        mMapView.showScaleControl(false); // 设置是否显示缩放控件
        //绘制位置涂层
        setOverlay();
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                search(latLng);
                mBaiduMap.clear();
                DecimalFormat df = new DecimalFormat("#.000");
                changeLocalName = df.format(latLng.latitude) + " " + df.format(latLng.longitude).replace("附近", "");
                setMarker(df.format(latLng.latitude) + " " + df.format(latLng.longitude), latLng);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {

                return false;
            }
        });
        mMapView.showZoomControls(false); // 设置是否显示缩放控件
        mMapView.showScaleControl(false); // 设置是否显示缩放控件
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
                //两个经纬度的距离
                //String jl = MapDistance.getInstance().getShortDistance(lat, lon, lat2, lng2);
                load_map(lat2+"",lng2+"");
            }
        });

    }
    private void  load_map(String lat,String lng){
        Map<String, String> params = new HashMap<>();
        //params.put("user_id", UserStatusUtil.getUserId());
        params.put("latitude", lat + "");
        params.put("longitude", lng + "");
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
    private void setOverlay() {
        lat = getIntent().getDoubleExtra(Constant.TAG_LAT, 0);
        lon = getIntent().getDoubleExtra(Constant.TAG_LON, 0);
        gpsInfo = getIntent().getStringExtra(Constant.TAG_ADDRESS);

        if (lat == 0 || lon == 0) {
            showToast("定位信息获取失败");
        } else {
            setLocation(lat, lon);
            LatLng pt = new LatLng(lat, lon);
            setMarker(gpsInfo, pt);
        }
        load_map(lat+"",lon+"");
    }

    // 初始化搜索模块，注册事件监听
    private void search(LatLng ll) {
        mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(ll).newVersion(1));
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if(reverseGeoCodeResult.getAddress().isEmpty()){
                    return;
                }
                mBaiduMap.clear();
                changeLocalName = reverseGeoCodeResult.getAddress().replace("附近", "");
                setMarker(reverseGeoCodeResult.getAddress().replace("附近", ""), reverseGeoCodeResult.getLocation());
                setLocation(reverseGeoCodeResult.getLocation().latitude, reverseGeoCodeResult.getLocation().longitude);
            }
        });
    }

    private void setLocation(double lat, double lng) {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .direction(100).latitude(lat)
                .longitude(lng).build();

        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_user_location);
        MyLocationConfiguration config = new MyLocationConfiguration(LocationMode.FOLLOWING, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);


    }

    private void setMarker(String localName, final LatLng ll) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_location, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_user_info);
        tv.setText(localName.replace("在", "").replace("附近", ""));


        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        final InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), ll, -47, new OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {
                lat = ll.latitude;
                lon = ll.longitude;
                SharedPreferencesUtil.saveData(MapActivity.this, Constant.TAG_LAT, lat + "");
                SharedPreferencesUtil.saveData(MapActivity.this, Constant.TAG_LON, lon + "");
                SharedPreferencesUtil.saveData(MapActivity.this, Constant.TAG_LOCAL_NAME, changeLocalName);

                setResult(1000);
                finish();

            }
        });

        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);

    }
    LatLng point;

    public void mark(List<MapModel.DataBean> list) {
        mBaiduMap.clear();
        for (int i = 0; i < list.size(); i++) {
            MapModel.DataBean model = list.get(i);
            Log.i("lat-lng", model.getLatitude() + "--" + model.getLongitude());
            point = new LatLng(Double.parseDouble(model.getLatitude()), Double.parseDouble(model.getLongitude()));
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_back);
//            OverlayOptions option = new MarkerOptions()
//                    .position(point)
//                    .icon(bitmap);
//            //在地图上添加Marker，并显示
//            mBaiduMap.addOverlay(option);
            final View view = LayoutInflater.from(this).inflate(R.layout.view_img_location, null);
            final RoundImageView iv = (RoundImageView) view.findViewById(R.id.ic_img);
            Glide.with(MapActivity.this).load(model.getImg_s()).asBitmap().
                    transform(new CornersTransform(MapActivity.this)).into(new SimpleTarget<Bitmap>() {
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
                    BitmapDescriptor bdOpen_iv =
                            BitmapDescriptorFactory.fromView(view);
                    OverlayOptions option = new MarkerOptions()
                            .position(point)
                            .icon(bdOpen_iv);
//                            .icon(BitmapDescriptorFactory.fromBitmap(resource));
                    //显示InfoWindow
                    //mBaiduMap.showInfoWindow(mInfoWindow);
                    mBaiduMap.addOverlay(option);
                }
            });

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mSearch.destroy();
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

    @OnClick({R.id.zoom_jia, R.id.zoom_jian, R.id.local, R.id.bt_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zoom_jia:
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                break;
            case R.id.zoom_jian:
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                break;
            case R.id.local:
                setResult(1001);
                finish();
                break;
            case R.id.bt_back:
                finish();
                break;
        }
    }
}
