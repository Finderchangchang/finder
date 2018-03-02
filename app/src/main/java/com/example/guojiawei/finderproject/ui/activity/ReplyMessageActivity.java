package com.example.guojiawei.finderproject.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cjt2325.cameralibrary.SharedPreferencesUtil;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;
import com.maning.mndialoglibrary.MProgressDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/19.
 */

public class ReplyMessageActivity extends BaseActivity {
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.publish)
    TextView publish;
    @BindView(R.id.et_content)
    EditText etContent;

    MProgressDialog mMProgressDialog;

    private String mMoodId = "";
    private String mCommentId = "";
    private String mReId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_reply_message;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mMoodId = getIntent().getStringExtra(Constant.TAG_MOOD_ID);
        mCommentId = getIntent().getStringExtra(Constant.TAG_COMMENT_ID);
        mReId = getIntent().getStringExtra("reid");
        if (mCommentId.isEmpty()) {
            publish.setText("评论");
            etContent.setHint("发表评论");
        } else {
            String userName = getIntent().getStringExtra("name");
            etContent.setHint("回复" + userName);
            publish.setText("回复");
        }
        mMProgressDialog = new MProgressDialog.Builder(this)
                //点击外部是否可以取消
                .isCanceledOnTouchOutside(true)
                //View背景的圆角
//                .setCornerRadius(20)
                //View 边框的宽度
//                .setStrokeWidth(2)
                //Progress 宽度
//                .setProgressWidth(3)
                //Progress 内圈颜色
//                .setProgressRimColor(Color.YELLOW)
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
    }

    private void reply(String user_id, String mood_id, String content, String comment_id) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("content", content);
        params.put("mood_id", mood_id);
        if (!"".equals(comment_id)) {
            params.put("comment_id", comment_id);
        }
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.REPLY_MESS)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {
                        mMProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("网络错误，请重试");
                        mMProgressDialog.dismiss();
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("发送成功");
                            if (!"".equals(mCommentId)) {
                                startActivity(new Intent(getContext(), MessageDetailActivity.class)
                                        .putExtra("user_re_id", mReId)
                                        .putExtra("nickname", getIntent().getStringExtra("nickname")));
                            }
                            SharedPreferencesUtil.saveData(getContext(), "refresh", true);
                            setResult(10);
                            finish();
                            mMProgressDialog.dismiss();
                        }
                    }
                });
    }

    @OnClick({R.id.cancel, R.id.publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.publish:
                if (etContent.getText().toString().isEmpty()) {
                    return;
                }
                mMProgressDialog.show("保存中，请稍后...");
                reply(UserStatusUtil.getUserId(), mMoodId, etContent.getText().toString(), mCommentId);
                break;
        }
    }
}
