package com.example.guojiawei.finderproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.cjt2325.cameralibrary.TypeButton;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.widget.camera.VideoAcitvity;

import java.io.File;

public class ShowImgActivity extends AppCompatActivity {

    String url = "";//当前图片地址		
    double output1;
    double output2;
    ImageView img;

    private TypeButton btn_confirm;         //确认按钮
    private TypeButton btn_cancle;         //取消按钮

    private int button_size;
    private LinearLayout left_ll;
    private LinearLayout right_ll;
    LatLng latLng;
    VideoView aty_video_preview_videoView;
    String img_path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        aty_video_preview_videoView = (VideoView) findViewById(R.id.aty_video_preview_videoView);

        left_ll = (LinearLayout) findViewById(R.id.left_ll);
        right_ll = (LinearLayout) findViewById(R.id.right_ll);
        latLng = getIntent().getParcelableExtra("latlng");
        url = getIntent().getStringExtra("url");
        img_path = getIntent().getStringExtra("img_path");
        img = (ImageView) findViewById(R.id.img);
        //        latLng = load_lat_lng(url);//获得当前图片的路径
        if (TextUtils.isEmpty(img_path)) {
            img.setImageURI(Uri.fromFile(new File(url)));
        } else {
            aty_video_preview_videoView.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
            initData();
            initListener();
        }
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        button_size = screenWidth;

        btn_confirm = new TypeButton(getBaseContext(), TypeButton.TYPE_CANCEL, (int) (button_size / 1.9f));
        btn_confirm.setBackgroundResource(R.drawable.ic_camera_cancel);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        left_ll.addView(btn_confirm);
        btn_cancle = new TypeButton(getBaseContext(), TypeButton.TYPE_CONFIRM, (int) (button_size / 1.9f));
        btn_cancle.setBackgroundResource(R.drawable.ic_camera_true);
        //确定按钮
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoAcitvity.main.finish();
                Intent intent = new Intent(ShowImgActivity.this, EditorActivity.class)
                        .putExtra(Constant.TAG_LAT, latLng.latitude)
                        .putExtra(Constant.TAG_LON, latLng.longitude);
                if (!TextUtils.isEmpty(img_path)) {
                    intent.putExtra("photopath", img_path);
                    intent.putExtra("path", url);
                    intent.putExtra("filename", "name");
                } else {
                    intent.putExtra("url", url);
                }
                startActivity(intent);
                finish();
            }
        });
        right_ll.addView(btn_cancle);
    }

    private void initData() {
        // TODO Auto-generated method stub
        Uri videoUri = Uri.parse(url);
        aty_video_preview_videoView.setVideoURI(videoUri);
        MediaController controller = new MediaController(ShowImgActivity.this);
        aty_video_preview_videoView.start();
    }

    private void initListener() {
        // TODO Auto-generated method stub
        aty_video_preview_videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                aty_video_preview_videoView.stopPlayback();
                //finish();
                return false;
            }
        });
        aty_video_preview_videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放结束后的动作
                //finish();
                aty_video_preview_videoView.start();
            }
        });
    }
}
