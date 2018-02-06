package com.example.guojiawei.finderproject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/19.
 */

public class ReplyJuBaoActivity extends BaseActivity {
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.publish)
    TextView publish;
    @BindView(R.id.et_content)
    EditText etContent;


    private String moodId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_reply_message;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        moodId = getIntent().getStringExtra(Constant.TAG_MOOD_ID);

        etContent.setHint("举报内容");
        publish.setText("举报");

    }

    private void report(String user_id, String mood_id, String content) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("mood_id", mood_id);
        params.put("content", content);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.REPORT)
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
                        showToast("举报失败");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("举报成功");
                            finish();
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
                report(UserStatusUtil.getUserId(), moodId, etContent.getText().toString());
                break;
        }
    }
}
