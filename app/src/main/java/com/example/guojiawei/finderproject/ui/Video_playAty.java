package com.example.guojiawei.finderproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.aliyun.vodplayer.media.AliyunVidSts;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.net.API;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Video_playAty extends BaseActivity {

    private AliyunVodPlayer aliyunVodPlayer;
    private TextView cancel;
    private String videoid;
    private String accessKeyId = "";//子accessKeyId
    private String accessKeySecret = "";//子accessKeySecret
    private String securityToken = "";//STS 的securityToken
    private SurfaceView surfaceView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_play_aty;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        videoid = intent.getStringExtra("videoid");
        aliyunVodPlayer = new AliyunVodPlayer(Video_playAty.this);
        cancel = (TextView) findViewById(R.id.cancel);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        getaddress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    protected void onDestroy() {
        aliyunVodPlayer.stop();
        super.onDestroy();
    }

    private void initListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video_playAty.this.finish();

            }
        });

        aliyunVodPlayer.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                //准备完成触发
                Log.i("准备完成", "开始");
                aliyunVodPlayer.start();

            }
        });
        aliyunVodPlayer.setOnFirstFrameStartListener(new IAliyunVodPlayer.OnFirstFrameStartListener() {
            @Override
            public void onFirstFrameStart() {
                //首帧显示触发
            }
        });
        aliyunVodPlayer.setOnErrorListener(new IAliyunVodPlayer.OnErrorListener() {
            @Override
            public void onError(int arg0, int arg1, String msg) {
                //出错时处理，查看接口文档中的错误码和错误消息
                Log.i("报错", "异常========" + msg);
            }
        });
        aliyunVodPlayer.setOnCompletionListener(new IAliyunVodPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
                //播放正常完成时触发
                Log.i("播放完成", "播放完成");
                Video_playAty.this.finish();
            }
        });
        aliyunVodPlayer.setOnSeekCompleteListener(new IAliyunVodPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete() {
                //seek完成时触发
            }
        });
        aliyunVodPlayer.setOnStoppedListner(new IAliyunVodPlayer.OnStoppedListener() {
            @Override
            public void onStopped() {
                //使用stop功能时触发
            }
        });
        aliyunVodPlayer.setOnChangeQualityListener(new IAliyunVodPlayer.OnChangeQualityListener() {
            @Override
            public void onChangeQualitySuccess(String finalQuality) {
                //视频清晰度切换成功后触发
            }

            @Override
            public void onChangeQualityFail(int code, String msg) {
                //视频清晰度切换失败时触发
            }
        });
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("创建", "surfaceCreated");
                aliyunVodPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d("改变", "surfaceChanged");
                aliyunVodPlayer.surfaceChanged();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("销毁", "surfaceDestroyed");
            }
        });


    }

    private void getaddress() {
        Observable<Response<String>> observable = OkGo.<String>post(API.GET_ADDRESS)
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
                        Log.i("异常", e.toString());
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
//                        Log.i("返回参数", stringResponse.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(stringResponse.body());
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("Credentials"));
                            accessKeyId = jsonObject1.getString("AccessKeyId");
                            accessKeySecret = jsonObject1.getString("AccessKeySecret");
                            securityToken = jsonObject1.getString("SecurityToken");
                            Log.i("key===============", accessKeyId);
                            Log.i("secret============", accessKeySecret);
                            Log.i("token=============", securityToken);
                            String mVid = videoid;
                            AliyunVidSts mVidSts = new AliyunVidSts();
                            mVidSts.setVid(mVid);
                            mVidSts.setAcId(accessKeyId);
                            mVidSts.setAkSceret(accessKeySecret);
                            mVidSts.setSecurityToken(securityToken);
                            aliyunVodPlayer.prepareAsync(mVidSts);
                        } catch (Exception e) {
                            Log.i("解析异常", e.toString());
                        }
                    }
                });
    }

}
