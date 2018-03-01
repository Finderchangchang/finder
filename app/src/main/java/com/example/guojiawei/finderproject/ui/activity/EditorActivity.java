package com.example.guojiawei.finderproject.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.vod.upload.VODSVideoUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODSVideoUploadClient;
import com.alibaba.sdk.android.vod.upload.VODSVideoUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.SvideoInfo;
import com.alibaba.sdk.android.vod.upload.session.VodHttpClientConfig;
import com.alibaba.sdk.android.vod.upload.session.VodSessionCreateInfo;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.util.BitMapUtil;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.KeyBoardUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.RoundImageView;
import com.example.guojiawei.finderproject.widget.camera.VideoPreViewAty;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;
import com.maning.mndialoglibrary.MProgressDialog;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/12.
 */

public class EditorActivity extends BaseActivity {
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.publish)
    TextView publish;
    @BindView(R.id.ic_preview)
    RoundImageView icPreview;
    @BindView(R.id.ic_preview_video)
    RoundImageView icPreview_video;
    @BindView(R.id.ic_preview_video1)
    RelativeLayout icPreview_video1;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.iv_preview_img)
    PhotoView ivPreviewImg;
    @BindView(R.id.tv_progress)
    TextView tv_progress;
    private long progress;
    private static final String TAG = "VIDEO_UPLOAD";
    //以下四个值由开发者的服务端提供,参考文档：https://help.aliyun.com/document_detail/28756.html（STS介绍）
    // AppServer STS SDK参考：https://help.aliyun.com/document_detail/28788.html
    private String accessKeyId = "";//子accessKeyId
    private String accessKeySecret = "";//子accessKeySecret
    private String securityToken = "";//STS 的securityToken
    private String expriedTime = "";//STS的过期时间

    private String requestID = "";//传空或传递访问STS返回的requestID

    private double mLat = 0;
    private double mLon = 0;
    private File file;
    private String url;
    private Info mInfo;
    private String filepath;
    private String filename;
    private String photopath;
    private VODSVideoUploadClient vodsVideoUploadClient;
    private VodSessionCreateInfo vodSessionCreateInfo;

    private String VideoId;
    private String ImageUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_editor;
    }

    MProgressDialog mMProgressDialog;

    @Override
    public void initViews(Bundle savedInstanceState) {

        mLat = getIntent().getDoubleExtra(Constant.TAG_LAT, mLat);
        mLon = getIntent().getDoubleExtra(Constant.TAG_LON, mLon);

        url = getIntent().getStringExtra("url");
        filepath = getIntent().getStringExtra("path");
        photopath = getIntent().getStringExtra("photopath");
        filename = getIntent().getStringExtra("filename");
        mMProgressDialog = new MProgressDialog.Builder(this)
                //点击外部是否可以取消
                .isCanceledOnTouchOutside(true)
                //View背景的圆角
                .setCornerRadius(20)
                //View 边框的宽度
//                .setStrokeWidth(2)
                //Progress 宽度
//                .setProgressWidth(3)
                //Progress 内圈宽度
//                .setProgressRimWidth(2)
                //取消的监听
//                .setOnDialogDismissListener(new MProgressDialog.OnDialogDismissListener() {
//                    @Override
//                    public void dismiss() {
//
//                    }
//                })
                .build();
        if (url == null || url.equals("")) {
            if (!(filepath == null || filepath.equals(""))) {
                icPreview.setVisibility(View.GONE);
                icPreview_video1.setVisibility(View.VISIBLE);

                byte[] bis = getIntent().getByteArrayExtra("bitmap");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
                icPreview_video.setImageBitmap(bitmap);
//                file = new File(photopath);
//                BitMapUtil.loadImage(this, file, icPreview_video);
                // 上传视频
                //1.初始化短视频上传对象
                vodsVideoUploadClient = new VODSVideoUploadClientImpl(this.getApplicationContext());
                vodsVideoUploadClient.init();
                getaddress();
            }
        } else {
            icPreview.setVisibility(View.VISIBLE);
            icPreview_video1.setVisibility(View.GONE);
            file = new File(url);
            BitMapUtil.loadImage(this, file, icPreview);
        }
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
                            expriedTime = jsonObject1.getString("Expiration");
                            Log.i("key===============", accessKeyId);
                            Log.i("secret============", accessKeySecret);
                            Log.i("token=============", securityToken);
                            Log.i("time==============", expriedTime);

                        } catch (Exception e) {
                            Log.i("解析异常", e.toString());
                        }
                    }
                });
    }

    private void publish(String user_id, String content, String longitude, String latitude) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("content", content);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        EncryptUtil.EncryptAutoSort(params);
        Observable<Response<String>> observable = OkGo.<String>post(API.PUSHLISH)
                .params(params, false)
                .params("img", file)
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
                        publish.setEnabled(true);
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        mMProgressDialog.dismiss();
                        if (entity.getCode() == 1) {
                            showToast("发送成功");
                            finish();
                        }
                    }
                });
    }

    private void publish1(String user_id, String content, String longitude, String latitude, String video_id, String images_url) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("content", content);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("video_id", video_id);
        params.put("images_url", images_url);
        EncryptUtil.EncryptAutoSort(params);
        Observable<Response<String>> observable = OkGo.<String>post(API.PUSHLISH)
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
                        Log.i("异常", e.toString());
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        publish.setEnabled(true);
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("发送成功");
                            mMProgressDialog.dismiss();
                            finish();
                        }
                    }
                });
    }

    @OnClick({R.id.cancel, R.id.publish, R.id.ic_preview, R.id.ic_preview_video1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.publish:
                publish.setEnabled(false);
                //将输入法隐藏，mPasswordEditText 代表密码输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
                mMProgressDialog.show("保存中，请稍后...");
                content.setCursorVisible(false);
                if (content.getText().toString().isEmpty()) {
                    publish.setEnabled(true);
                    mMProgressDialog.dismiss();
                    return;
                } else {
                    // 已填写的评论
                    if (url == null || url.equals("")) {
                        //图片地址空  判断视频路径
                        if (filepath == null || filepath.equals("")) {
                            mMProgressDialog.dismiss();
                            publish.setEnabled(true);
                            return;
                        } else {
                            //参数请确保存在，如不存在SDK内部将会直接将错误throw Exception
// 文件路径保证存在之外因为Android 6.0之后需要动态获取权限，请开发者自行实现获取"文件读写权限".
                            VodHttpClientConfig vodHttpClientConfig = new VodHttpClientConfig.Builder()
                                    .setMaxRetryCount(2)//重试次数
                                    .setConnectionTimeout(15 * 1000)//连接超时
                                    .setSocketTimeout(15 * 1000)//socket超时
                                    .build();
                            //构建短视频VideoInfo,常见的描述，标题，详情都可以设置
                            SvideoInfo svideoInfo = new SvideoInfo();
                            svideoInfo.setTitle(filename);
                            svideoInfo.setDesc("");
                            svideoInfo.setCateId(1);
                            //构建点播上传参数(重要)
                            vodSessionCreateInfo = new VodSessionCreateInfo.Builder()
                                    .setImagePath(photopath)//图片地址
                                    .setVideoPath(filepath)//视频地址
                                    .setAccessKeyId(accessKeyId)//临时accessKeyId
                                    .setAccessKeySecret(accessKeySecret)//临时accessKeySecret
                                    .setSecurityToken(securityToken)//securityToken
                                    .setExpriedTime(expriedTime)//STStoken过期时间
                                    .setRequestID(requestID)//requestID，开发者可以传将获取STS返回的requestID设置也可以不设.
                                    .setIsTranscode(true)//是否转码.如开启转码请AppSever务必监听服务端转码成功的通知
                                    .setSvideoInfo(svideoInfo)//短视频视频信息
                                    .setVodHttpClientConfig(vodHttpClientConfig)//网络参数
                                    .build();
                            tv_progress.setVisibility(View.VISIBLE);
                            vodsVideoUploadClient.uploadWithVideoAndImg(vodSessionCreateInfo, new VODSVideoUploadCallback() {
                                @Override
                                public void onUploadSucceed(String videoId, String imageUrl) {
                                    //上传成功返回视频ID和图片URL.
                                    Log.d(TAG, "onUploadSucceed" + "videoId:" + videoId + "imageUrl" + imageUrl);
//                                    tv_progress.setVisibility(View.GONE);
                                    VideoId = videoId;
                                    ImageUrl = imageUrl;
                                    publish1(UserStatusUtil.getUserId(), content.getText().toString(), mLon + "", mLat + "", VideoId, ImageUrl);
                                }

                                @Override
                                public void onUploadFailed(String code, String message) {
                                    //上传失败返回错误码和message.错误码有详细的错误信息请开发者仔细阅读
                                    Log.d(TAG, "上传失败" + "code" + code + "message" + message);
                                    mMProgressDialog.dismiss();
                                    publish.setEnabled(true);
                                }

                                @Override
                                public void onUploadProgress(long uploadedSize, long totalSize) {
                                    //上传的进度回调,非UI线程
                                    Log.d(TAG, "上传进度" + uploadedSize * 100 / totalSize);
                                    progress = uploadedSize * 100 / totalSize;
                                    handler.sendEmptyMessage(0);
                                }

                                @Override
                                public void onSTSTokenExpried() {
                                    Log.d(TAG, "onSTSTokenExpried");
                                    //STS token过期之后刷新STStoken，如正在上传将会断点续传
                                    vodsVideoUploadClient.refreshSTSToken(accessKeyId, accessKeySecret, securityToken, expriedTime);
                                }

                                @Override
                                public void onUploadRetry(String code, String message) {
                                    //上传重试的提醒
                                    Log.d(TAG, "onUploadRetry" + "code" + code + "message" + message);
                                }

                                @Override
                                public void onUploadRetryResume() {
                                    //上传重试成功的回调.告知用户重试成功
                                    Log.d(TAG, "重新上传成功");
                                    mMProgressDialog.dismiss();
                                    publish.setEnabled(true);
                                }
                            });


                        }
                    } else {
                        //图片地址有
                        if (filepath == null || filepath.equals("")) {
                            tv_progress.setVisibility(View.GONE);
                            //上传图片
                            publish(UserStatusUtil.getUserId(), content.getText().toString(), mLon + "", mLat + "");
                            mMProgressDialog.show("保存中，请稍后...");
                            publish.setEnabled(false);
                        } else {
                            Toast.makeText(EditorActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.ic_preview:
                showPreview();
                break;
            case R.id.ic_preview_video1:
                VideoPreViewAty.actionStart(EditorActivity.this, filepath);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (filepath == null || filepath.equals("")) {

        } else {
            vodsVideoUploadClient.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (filepath == null || filepath.equals("")) {

        } else {
            vodsVideoUploadClient.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (filepath == null || filepath.equals("")) {

        } else {
            vodsVideoUploadClient.release();
        }
    }

    private void showPreview() {
        KeyBoardUtil.closeKeybord(content, getContext());
        //获取img1的信息
        mInfo = PhotoView.getImageViewInfo(icPreview);
        ivPreviewImg.setVisibility(View.VISIBLE);
        ivPreviewImg.animaFrom(mInfo);
        content.setCursorVisible(false);
        ivPreviewImg.setImageDrawable(null);
        Glide.with(getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                ivPreviewImg.setImageBitmap(resource);
                //获取img1的信息
                mInfo = PhotoView.getImageViewInfo(icPreview);
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
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.setCursorVisible(true);
            }
        });
    }


    // UI只允许在主线程更新。
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //更新进度
            tv_progress.setText("上传进度：" + String.valueOf(progress));
        }
    };
}
