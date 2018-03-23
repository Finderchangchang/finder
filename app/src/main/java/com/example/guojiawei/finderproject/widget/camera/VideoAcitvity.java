package com.example.guojiawei.finderproject.widget.camera;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.ui.activity.CenterActivity;
import com.example.guojiawei.finderproject.ui.activity.EditorActivity;
import com.example.guojiawei.finderproject.ui.activity.GifSizeFilter;
import com.example.guojiawei.finderproject.ui.activity.ShowImgActivity;
import com.example.guojiawei.finderproject.util.Constant;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class VideoAcitvity extends BaseActivity {
    private JCameraView jCameraView;
    public static VideoAcitvity main;

    private double mLat = 0;
    private double mLon = 0;
    private String dir = null;

    public static int sWIDTH;

    private String filePath;
    private String photourl;


    private String fileName;
    private File newFile;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (jCameraView != null) {
            jCameraView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (jCameraView != null) {
            jCameraView.stopVideo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

    ImageView close_iv;

    @Override
    public void initViews(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mLat = getIntent().getDoubleExtra(Constant.TAG_LAT, 0);
        mLon = getIntent().getDoubleExtra(Constant.TAG_LON, 0);
        jCameraView = (JCameraView) findViewById(R.id.jcameraview);
        close_iv = (ImageView) findViewById(R.id.close_iv);
        main = this;
        // 设置视频保存路径
        jCameraView.setSaveVideoPath(getSDPath() + File.separator + "JCamera");
        jCameraView.setTip("轻触拍照");
//            jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_RECORDER); // 设置功能
        dir = getSDPath() + "/find/video";
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(2);
                jCameraView.close_camera();
                finish();
            }
        });
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
    }

    private String getSDPath() {
        // TODO Auto-generated method stub
        File sdDir = null;
        File directory_pictures = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
            directory_pictures = new File(sdDir, "/Camera");
        } else {
            directory_pictures = new File(getApplicationContext().getFilesDir().getAbsolutePath());
//            Toast.makeText(getApplicationContext(), "该手机没有SD卡", Toast.LENGTH_SHORT).show();
        }
        return directory_pictures.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    double output1;
    double output2;

    private void initListener() {
        jCameraView.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoAcitvity.this.finish();
            }
        });
        //跳转到相册页面
        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Matisse.from(VideoAcitvity.this)
                        .choose(MimeType.ofAll())
                        .theme(R.style.Matisse_Zhihu)
                        .countable(false)
                        .maxSelectable(1)
                        .imageEngine(new GlideEngine())
                        .forResult(1);
            }
        });
        jCameraView.setErrorLisenter(new ErrorListener() {

            public void onError() {
                // 错误监听
                Log.i("CJT", "camera error");
                Intent intent = new Intent();
                setResult(103, intent);
                finish();
            }


            public void AudioPermissionError() {
                Toast.makeText(VideoAcitvity.this, "给点录音权限可以?", Toast.LENGTH_SHORT).show();
            }
        });
        // JCameraView监听
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                String path = FileUtil.saveBitmap("JCamera", bitmap);
                startActivity(new Intent(VideoAcitvity.this, EditorActivity.class)
                        .putExtra(Constant.TAG_LAT, mLat)
                        .putExtra(Constant.TAG_LON, mLon)
                        .putExtra("path", filePath)
                        .putExtra("url", path)
                        .putExtra("filename", fileName));
                finish();
            }

            @Override
            public void quit() {
                finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                // 获取视频路径
                String path = FileUtil.saveBitmap("JCamera", firstFrame);
                Log.i("CJT", "url = " + url + ", Bitmap = " + path);
                File oldFile = new File(url);

                fileName = System.currentTimeMillis()
                        + ".mp4";
                newFile = new File(dir, fileName);
//                try {
//                    if (!newFile.exists()) {
//                        newFile.createNewFile();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                filePath = newFile.getPath();

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    bis = new BufferedInputStream(new FileInputStream(oldFile));
                    bos = new BufferedOutputStream(new FileOutputStream(newFile));
                    byte[] str = new byte[1024 * 10];
                    int len = 0;
                    while ((len = bis.read(str)) != -1) {
                        bos.write(str, 0, len);
                    } // 刷新此缓冲的输出流 bos.flush();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    try {
                        bis.close();
                        bos.close();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
                }
                if (oldFile.exists()) {
                    oldFile.delete();
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                firstFrame.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bitmapByte = baos.toByteArray();
                startActivity(new Intent(VideoAcitvity.this, EditorActivity.class)
                        .putExtra(Constant.TAG_LAT, mLat)
                        .putExtra(Constant.TAG_LON, mLon)
                        .putExtra("path", filePath)
                        .putExtra("photopath", path)
                        .putExtra("filename", fileName)
                        .putExtra("bitmap", bitmapByte));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List mSelected = Matisse.obtainResult(data);
            if (mSelected.size() > 0) {
                String url = mSelected.get(0).toString();
                double lat = data.getDoubleExtra("lat", 0);
                double lng = data.getDoubleExtra("lng", 0);
                LatLng d = new LatLng(lat, lng);
                startActivityForResult(new Intent(getContext(), ShowImgActivity.class)
                        .putExtra("url", data.getStringExtra("url"))
                        .putExtra("img_path", data.getStringExtra("img_path"))
                        .putExtra("latlng", d), 22);
                finish();
            }
        }
    }

    private void loadGIF() {
//        GalleryFinal.openGallerySingle(0, new GalleryFinal.OnHanlderResultCallback() {
//            @Override
//            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                if (resultList.size() > 0) {
//                    String url = resultList.get(0).getPhotoPath();
//                    LatLng d = load_lat_lng(url);
//                    if (d.latitude == 0) {
//                        showToast("未发现当前图像位置");
//                        loadGIF();
//                    } else {
//                        startActivityForResult(new Intent(getContext(), ShowImgActivity.class)
//                                .putExtra("url", resultList.get(0).getPhotoPath())
//                                .putExtra("latlng", d), 22);
//                    }
//                }
//            }
//
//            @Override
//            public void onHanlderFailure(int requestCode, String errorMsg) {
//
//            }
//        });
    }

    private void clapAnim(View left, View right) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(left, "translationX", 0f, -300f);
        animator.setDuration(300);
        animator.start();


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(right, "translationX", 0f, 300f);
        animator2.setDuration(300);
        animator2.start();
    }


    /**
     * 将gps84转为bd09
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double[] gps84_To_bd09(double lat, double lon) {
        double[] gcj02 = gps84_To_Gcj02(lat, lon);
        double[] bd09 = gcj02_To_Bd09(gcj02[0], gcj02[1]);
        return bd09;
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02_To_Bd09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        double[] gps = {tempLat, tempLon};
        return gps;
    }

    public static double pi = 3.1415926535897932384626;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    /**
     * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double[] gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[]{lat, lon};
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[]{mgLat, mgLon};
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    public LatLng load_lat_lng(String url) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(url);
        } catch (IOException e) {
            finish();
        }
        String datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);// 拍摄时间
        String deviceName = exifInterface.getAttribute(ExifInterface.TAG_MAKE);// 设备品牌
        String deviceModel = exifInterface.getAttribute(ExifInterface.TAG_MODEL); // 设备型号
        String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
        String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
        String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
        String lngRef = exifInterface.getAttribute
                (ExifInterface.TAG_GPS_LONGITUDE_REF);
        if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
            try {
                output1 = convertRationalLatLonToFloat(latValue, latRef);
                output2 = convertRationalLatLonToFloat(lngValue, lngRef);
                double[] dd = gps84_To_bd09(output1, output2);
                return new LatLng(dd[0], dd[1]);
            } catch (IllegalArgumentException e) {
                finish();
            }
        } else {
            output1 = 0.0;
            output2 = 0.0;
            return new LatLng(0, 0);
        }
        return new LatLng(0, 0);
    }

    public float convertRationalLatLonToFloat(
            String rationalString, String ref) {

        String[] parts = rationalString.split(",");

        String[] pair;
        pair = parts[0].split("/");
        double degrees = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        pair = parts[1].split("/");
        double minutes = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        pair = parts[2].split("/");
        double seconds = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
        if ((ref.equals("S") || ref.equals("W"))) {
            return (float) -result;
        }
        return (float) result;
    }
}
