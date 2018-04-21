package com.example.guojiawei.finderproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.example.guojiawei.finderproject.Constant_web;
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
import com.example.guojiawei.finderproject.util.BitMapUtil;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.cjt2325.cameralibrary.SharedPreferencesUtil;
import com.example.guojiawei.finderproject.util.MyBottomDialog;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.MessageDetailDividerItemDecoration;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelector;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelectorListener;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MultiImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
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

public class DetailActivity extends BaseActivity implements WbShareCallback {
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.iv_preview_img)
    PhotoView ivPreviewImg;
    @BindView(R.id.share_iv)
    ImageView share_iv;
    private DetailsAdapter detailsAdapter;
    private String lat = "";
    private String lon = "";
    private String moodId = "";
    private Info mInfo;
    Tencent mTencent;
    String share_title = "Finder";
    String share_content = "来自 人 的Finder文";
    String share_img = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    Bundle params;
    private static final String APP_ID = "wxf4d9d01961dc2174";    //这个APP_ID就是注册APP的时候生成的

    public IWXAPI api;      //这个对象是专门用来向微信发送数据的一个重要接口,使用强引用持有,所有的信息发送都是基于这个对象的
    SsoHandler mSsoHandler;
    WbShareHandler shareHandler;
    Bitmap map;

    public void registerWeChat() {   //向微信注册app
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent, this);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        registerWeChat();
        lat = (String) SharedPreferencesUtil.getData(this, Constant.TAG_LAT, "");
        lon = (String) SharedPreferencesUtil.getData(this, Constant.TAG_LON, "");
        moodId = getIntent().getStringExtra(Constant.TAG_MOOD_ID);
        mTencent = Tencent.createInstance("1106567682", this);
        AuthInfo mAuthInfo = new AuthInfo(DetailActivity.this, Constant_web.APP_KEY, Constant_web.REDIRECT_URL, Constant_web.SCOPE);
        WbSdk.install(DetailActivity.this, mAuthInfo);
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        setRecyclerView();
        detailsAdapter = new DetailsAdapter(DetailActivity.this);
        share_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBottomDialog dialog = new MyBottomDialog(DetailActivity.this);
                dialog.setClick(new MyBottomDialog.click1() {
                    @Override
                    public void click(int position) {
                        switch (position) {
                            case 1:
                                sharePicByFile(true, "");
                                break;
                            case 2:
                                TextObject textObject = new TextObject();
                                textObject.text = share_content + " " + share_url + " @finder分享眼前世界";
                                WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
                                weiboMultiMessage.textObject = textObject;
//                                Bitmap largeImg = bitmap;
//                                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.f_wen_false);
//
//                                bmp.recycle();
                                int MAX_SIZE_LARGE_BYTE = 1 << 21;
//                                if (largeImg.getByteCount() > MAX_SIZE_LARGE_BYTE) {
//                                    double scale = Math.sqrt(1.0 * largeImg.getByteCount() / MAX_SIZE_LARGE_BYTE);
//                                    int scaledW = (int) (largeImg.getWidth() / scale);
//                                    int scaledH = (int) (largeImg.getHeight() / scale);
//
//                                    largeImg = Bitmap.createScaledBitmap(bitmap, scaledW, scaledH, true);
                                ImageObject imageObject = new ImageObject();
                                imageObject.setImageObject(bitmaps);
                                weiboMultiMessage.imageObject = imageObject;
//                                }

                                shareHandler.shareMessage(weiboMultiMessage, false);
                                break;
                            case 3:
                                params = new Bundle();
                                params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                                params.putString(QzoneShare.SHARE_TO_QQ_TITLE, share_title);// 标题
                                params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, share_content);// 摘要
                                params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, share_url);// 内容地址
                                ArrayList<String> imgUrlList = new ArrayList<>();
                                imgUrlList.add(img_url);
                                params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);// 图片地址
                                mTencent.shareToQzone(DetailActivity.this, params, mIUiListener);
                                break;
                            case 4:
                                sharePicByFile(false, "");
                                break;
                            case 5:
                                params = new Bundle();
                                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                                params.putString(QQShare.SHARE_TO_QQ_TITLE, share_title);// 标题
                                params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, share_content);// 摘要
                                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, share_url);// 内容地址
                                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, img_url);// 网络图片地址params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
                                mTencent.shareToQQ(DetailActivity.this, params, mIUiListener);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);
        share_url = "http://111.231.194.105/share/?id=" + moodId;
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
                SharedPreferencesUtil.saveData(getContext(), "refresh", true);
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
                        .putExtra("Image_url", bean.getImage_url())
                        .putExtra("video_id", bean.getVideo_id())
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
                        //bitmap = resource;
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

    Bitmap bitmaps;

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void sharePicByFile(boolean is_quan, String tag) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = share_url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = share_title;
        msg.description = share_content;
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.f_wen_false);
        //Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);

        msg.thumbData = Bitmap2Bytes(bitmaps);
        //bitmap.recycle();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        //api.sendReq(req);
        if (is_quan) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
        }
        req.transaction = tag;  //这个tag要唯一,用于在回调中分辨是哪个分享请求
        boolean b = api.sendReq(req);   //如果调用成功微信,会返回true
    }

    public byte[] Bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > 32&& options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            SharedPreferencesUtil.saveData(getContext(), "refresh", true);//评论
            getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);
        }
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
//        if (requestCode == Constants.REQUEST_API) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE ||
                    resultCode == Constants.REQUEST_QZONE_SHARE ||
                    resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
//        }
    }

    MyIUiListener mIUiListener = new MyIUiListener();

    @Override
    public void onWbShareSuccess() {
        showToast("分享成功");
    }

    @Override
    public void onWbShareCancel() {
        showToast("分享已取消");
    }

    @Override
    public void onWbShareFail() {
        showToast("分享失败");
    }

    class MyIUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
// 操作成功
            showToast("分享成功");

        }

        @Override
        public void onError(UiError uiError) {
            showToast("分享失败，请稍后重试");
// 分享异常
        }

        @Override


        public void onCancel() {
            String a = "";
            showToast("分享已取消");
            // 取消分享
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
                        img_url = entity.getData().getImage_url();
                        message = entity.getData().getContent();
                        recyclerView.setAdapter(detailsAdapter);
                        getMoodMessage("1", "10", entity.getData().getId());//获得评论的信息
                        DetailsEntity.HeadEntity.DataBean bean = detailsAdapter.getHeadEntity().getData();
                        share_content = "来自 " + bean.getUser().getNickname() + " 的f文";
                        String url = "";
                        if (bean.getImage_url() == null || bean.getImage_url().equals("")) {
                            url = bean.getImg_s();
                        } else {
                            url = bean.getImage_url();
                        }
                        Glide.with(DetailActivity.this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                bitmaps = resource;
                            }
                        });
                        share_title = bean.getContent();
                    }
                });
    }

    String img_url = "";//图片路径
    String message = "";//分享的内容
    String share_url;//分享之后的url

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
                        getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("网络错误，请重试");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
//                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
//                        if (entity.getCode() == 1) {
                        getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);

//                        }
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
                            getMoodDetatils(moodId, UserStatusUtil.getUserId(), lat, lon);

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
