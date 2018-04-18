package com.example.guojiawei.finderproject.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.entity.UserInfoEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GlideCircleTransform;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelector;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelectorListener;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

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
 * Created by guojiawei on 2017/11/9.
 */

public class CenterActivity extends BaseActivity {
    private static final String UN_SHOW = "0";

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.main_bar)
    RelativeLayout mainBar;
    @BindView(R.id.rl_head)
    LinearLayout rlHead;
    @BindView(R.id.rl_room)
    RelativeLayout rlRoom;
    @BindView(R.id.ic_head)
    ImageView icHead;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.message_num)
    TextView messageNum;
    @BindView(R.id.f_message_num)
    TextView f_messageNum;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;
    @BindView(R.id.center)
    TextView center;
    @BindView(R.id.tv_message_num)
    TextView tvMessageNum;

    private DialogSelector dialogSelector;
    private ImagePicker imagePicker;

    @Override
    public int getLayoutId() {
        return R.layout.activity_center;
    }


    @Override
    public void initViews(Bundle savedInstanceState) {
        if (UserStatusUtil.getCommentNum().equals("0") && UserStatusUtil.getFCommentNum().equals("0")) {
            messageNum.setVisibility(View.GONE);
            tvMessageNum.setVisibility(View.GONE);
            f_messageNum.setVisibility(View.GONE);
        } else {
            int num = 0;
            String num1 = UserStatusUtil.getCommentNum();
            String num2 = UserStatusUtil.getFCommentNum();
            if (!num1.equals("0")) {
                num += Integer.parseInt(num1);
                messageNum.setVisibility(View.VISIBLE);
                messageNum.setText(num1);
            } else {
                messageNum.setVisibility(View.GONE);
            }
            if (!num2.equals("0")) {
                num += Integer.parseInt(num2);
                f_messageNum.setVisibility(View.VISIBLE);
                f_messageNum.setText(num1);
            }else {
                f_messageNum.setVisibility(View.GONE);
            }
            tvMessageNum.setVisibility(View.VISIBLE);
            tvMessageNum.setText(num + "");

        }

        getUserInfo(UserStatusUtil.getUserId());

        initDialog();

        etName.addTextChangedListener(new TextWatcher() {
            String before = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                before = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (before.equals(etName.getText().toString())) {
                    return;
                } else {
                    updataUserInfo(UserStatusUtil.getUserId(), etName.getText().toString(), null);
                }
            }
        });


    }

    @OnClick({R.id.center, R.id.tv_center, R.id.rl_head, R.id.rl_room, R.id.rl_my, R.id.ic_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.center:
                finish();
                break;
            case R.id.tv_center:
                dialogSelector.show();
                break;
            case R.id.rl_head:
                Matisse.from(CenterActivity.this)
                        .choose(MimeType.ofAll())
                        .countable(true)
                        // 图片选择的最多数量
                        .maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        // 缩略图的比例
                        .thumbnailScale(0.85f)
                        // 使用的图片加载引擎
                        .imageEngine(new GlideEngine())
                        // 设置作为标记的请求码
                        .forResult(10001);
                break;
            case R.id.rl_room:
//                startActivity(new Intent(this, PeopleHistoryActivity.class));
                startActivity(new Intent(this, PublishHistoryActivity.class));
                break;
            case R.id.rl_my:
                messageNum.setVisibility(View.GONE);
                tvMessageNum.setVisibility(View.GONE);
                startActivity(new Intent(this, MessagesActivity.class));
                break;
            case R.id.ic_head:
                startImagePicker();
                break;
            default:
                break;
        }
    }

    private void initDialog() {
        dialogSelector = new DialogSelector(this);
        dialogSelector.setTitle("退出登录");
        dialogSelector.setListener(new ImplOndialogListener());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    private void startImagePicker() {
        imagePicker = new ImagePicker();
        // 设置标题
        imagePicker.setTitle("设置头像");
        // 设置是否裁剪图片
        imagePicker.setCropImage(true);
        // 启动图片选择器
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            // 选择图片回调
            @Override
            public void onPickImage(Uri imageUri) {

            }

            // 裁剪图片回调
            @Override
            public void onCropImage(Uri imageUri) {
                String path = getRealFilePath(CenterActivity.this, imageUri);
                Glide.with(CenterActivity.this).load(imageUri).transform(new GlideCircleTransform(CenterActivity.this)).into(icHead);
                updataUserInfo(UserStatusUtil.getUserId(), etName.getText().toString(), new File(path));
            }

            // 自定义裁剪配置
            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape.OVAL)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(540, 540)
                        // 宽高比
                        .setAspectRatio(16, 16);
            }

            // 用户拒绝授权回调
            @Override
            public void onPermissionDenied(int requestCode, String[] permissions,
                                           int[] grantResults) {
            }
        });
    }


    final class ImplOndialogListener implements DialogSelectorListener {
        @Override
        public void onSure() {
            outLogin(UserStatusUtil.getUserId());
        }

        @Override
        public void onCancel() {
            dialogSelector.dismiss();
        }
    }

    private void outLogin(String user_id) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.LOGIN_OUT)
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
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            UserStatusUtil.backLogin();
                            showToast("退出成功");
                            finish();
                        } else {
                            showToast("退出失败");
                        }
                    }
                });
    }

    private void getUserInfo(String user_id) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.USER_INFO)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        UserInfoEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), UserInfoEntity.class);
                        if (entity.getCode() == 1) {
                            Glide.with(CenterActivity.this).load(entity.getData().getHead_img()).transform(new GlideCircleTransform(CenterActivity.this)).placeholder(R.drawable.ic_head_load)
                                    .into(icHead);
                            etName.setText(entity.getData().getNickname());
                        }
                    }
                });
    }


    /**
     * @param user_id
     * @param nickname
     * @param head_img
     */
    private void updataUserInfo(String user_id, String nickname, File head_img) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("nickname", nickname);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable;
        if (head_img == null) {
            observable = OkGo.<String>post(API.USER_INFO_UPDATA)
                    .params(params, false)
                    .converter(new StringConvert())
                    .adapt(new ObservableResponse<String>());
        } else {
            observable = OkGo.<String>post(API.USER_INFO_UPDATA)
                    .params(params, false)
                    .params("head_img", head_img)
                    .converter(new StringConvert())
                    .adapt(new ObservableResponse<String>());
        }

        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        UserInfoEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), UserInfoEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("修改成功");
                        }
                    }
                });
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
