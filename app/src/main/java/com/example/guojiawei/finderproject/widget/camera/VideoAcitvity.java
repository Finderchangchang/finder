package com.example.guojiawei.finderproject.widget.camera;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.ui.activity.EditorActivity;
import com.example.guojiawei.finderproject.util.Constant;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class VideoAcitvity extends BaseActivity {
    private JCameraView jCameraView;


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

    private void initListener() {
        jCameraView.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoAcitvity.this.finish();
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


    private void clapAnim(View left, View right) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(left, "translationX", 0f, -300f);
        animator.setDuration(300);
        animator.start();


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(right, "translationX", 0f, 300f);
        animator2.setDuration(300);
        animator2.start();
    }
}
