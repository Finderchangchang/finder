package com.example.guojiawei.finderproject.widget.camera;

/**
 * =====================================
 * <p>
 * 描    述： 自定义视频播放器activity intent 传递"path" 进来 就能播放 逻辑已完成
 * =====================================
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.guojiawei.finderproject.R;

public class VideoPreViewAty extends Activity {


    private VideoView videoView;

    @SuppressWarnings("unused")

    private String url;

    private PopupWindow popupWindow;

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            handler.postDelayed(runnable, 1000);
            if (videoView.isPlaying()) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_video_preview);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        // TODO Auto-generated method stub
        Intent intent = getIntent();
        url = intent.getStringExtra("path");
        videoView = (VideoView) findViewById(R.id.aty_video_preview_videoView);
        handler.postDelayed(runnable, 1000);
    }

    private void initData() {
        // TODO Auto-generated method stub
        Uri videoUri = Uri.parse(url);
        videoView.setVideoURI(videoUri);
        MediaController controller = new MediaController(VideoPreViewAty.this);
        videoView.start();
    }

    private void initListener() {
        // TODO Auto-generated method stub
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                videoView.stopPlayback();
                finish();
                return false;
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放结束后的动作
                finish();
            }
        });
    }

    /**
     * 删除布局
     */
    @SuppressLint("InflateParams")
    @SuppressWarnings("deprecation")
    private void TitleShow() {
        View view = LayoutInflater.from(this).inflate(
                R.layout.video_preview_pop, null);
        @SuppressWarnings("unused")
        View rootView = findViewById(R.id.aty_video_preview_layout); // 當前頁面的根佈局
        View lineView = findViewById(R.id.aty_video_preview_line);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
        setBackgroundAlpha(0.5f);// 设置屏幕透明度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 顯示在根佈局的底部
//		popupWindow.showAtLocation(rootView, Gravity.TOP, 0, 0);
        popupWindow.showAsDropDown(lineView);
        popupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        TextView backBtn = (TextView) view.findViewById(R.id.video_preview_pop_back);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) this).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) this).getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    /**
     * 页面跳转
     *
     * @param context 上下文对象
     * @param path    链接地址或本地路径
     */
    public static void actionStart(Context context, String path) {
        Intent intent = new Intent(context, VideoPreViewAty.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }

}
