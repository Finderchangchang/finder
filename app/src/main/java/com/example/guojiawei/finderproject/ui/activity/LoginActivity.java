package com.example.guojiawei.finderproject.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.UserIdEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.TimeCount;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/8.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.bt_two)
    Button btTwo;
    @BindView(R.id.bt_one)
    Button btOne;
    @BindView(R.id.bt_three)
    Button btThree;
    @BindView(R.id.bt_five)
    Button btFive;
    @BindView(R.id.bt_four)
    Button btFour;
    @BindView(R.id.bt_six)
    Button btSix;
    @BindView(R.id.bt_eight)
    Button btEight;
    @BindView(R.id.bt_seven)
    Button btSeven;
    @BindView(R.id.bt_nine)
    Button btNine;
    @BindView(R.id.bt_zero)
    Button btZero;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_time)
    TextView tvTime;

    //手机号验证码存储
    private List<String> numbers = new ArrayList<>();
    //手机号验证码存储
    private List<String> code = new ArrayList<>();
    //手机号输入最大数值
    private int maxNumber = 11;
    //验证码输入最大数值
    private int maxCode = 6;
    //是否换行
    private boolean isNext = false;
    //是否可以登录
    private boolean isLogin = false;
    //是否返回
    private boolean isBack = true;
    //是否可以点击登录
    private boolean isClickLogin = false;

    String JpushID = JPushInterface.getRegistrationID(this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        tvPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //手机号开始输入返回键变为false
                isBack = false;
                //如果换行为false则还是输入手机号
                if (isNext == false) {
                    //如果手机号吗已经输入11位 按键变为发送
                    if (maxNumber - numbers.size() == 0) {
                        isNext = true;
                        btNext.setText("发送");
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果输入手机号为0 为空则设置删除键为返回键
                if (numbers.size() == 0) {
                    btDelete.setText("返回");
                    isBack = true;
                    return;
                }
                //如果不为空则设置返回键为删除键
                btDelete.setText("删除");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        tvCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //如果输入换行则开始输入验证码
                if (isNext) {
                    if (maxCode - code.size() == 0) {
                        isLogin = true;
                        btNext.setText("登录");
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void clickNumber(int number) {
        if (numbers.size() + code.size() == maxNumber - 1) {
            isNext = false;
        } else if (numbers.size() + code.size() > maxNumber - 1) {
            isNext = true;
        }

        if (isNext == false) {
            numbers.add(number + "");
            refushPhoneNumber();
        } else if (isNext) {
            if (isLogin) {
                return;
            }
            code.add(number + "");
            refushCodeNumber();
        }
    }


    public void deleteNumber() {
        if (numbers.size() + code.size() <= maxNumber) {
            isNext = false;
            if (numbers.size() > 0) {
                numbers.remove(numbers.size() - 1);
                refushPhoneNumber();
            }
        } else if (numbers.size() + code.size() > maxNumber - 1) {
            isNext = true;
            if (code.size() > 0) {
                code.remove(code.size() - 1);
                refushCodeNumber();
                if (code.size() == 0) {
                    // isNext = false;
                    isLogin = false;
                    btNext.setText("发送");
                }
            }
        }


    }

    private void refushPhoneNumber() {
        StringBuffer sb = new StringBuffer();
        for (String ignored : numbers) {
            sb.append(ignored);
        }
        int i = maxNumber - numbers.size();
        for (int j = 0; j <i ; j++) {
            sb.append("*");
        }
        tvPhone.setText(sb.toString());
    }

    private void refushCodeNumber() {
        StringBuffer sb = new StringBuffer();
        for (String ignored : code) {
            sb.append(ignored);
        }
        int i = maxCode - code.size();
        for (int j = 0; j <i ; j++) {
            sb.append("*");
        }
        tvCode.setText(sb.toString());
    }

    private void sendCode(String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        EncryptUtil.EncryptAutoSort(params);
        Observable<Response<String>> observable = OkGo.<String>post(API.GET_CODE)
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
                        showToast("发送失败");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        showToast("发送成功");
                    }
                });
    }

    private void onLogin(String mobile, String code, String jpushId, String lat, String lon) {
        Map<String, String> params = new HashMap<>();
        params.put("RegistrationID", jpushId);
        params.put("password", "");
        params.put("mobile", mobile);
        params.put("longitude", lon);
        params.put("latitude", lat);
        params.put("code", code);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.LOGIN)
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
                        showToast("发送失败");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        UserIdEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), UserIdEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("登录成功");
                            UserStatusUtil.Login(entity.getData());
                            setResult(1001);
                            finish();
                        } else if (entity.getCode() == 0) {
                            showToast("验证码错误");
                        }
                    }
                });
    }

    @OnClick({R.id.bt_two, R.id.bt_one, R.id.bt_three, R.id.bt_five, R.id.bt_four, R.id.bt_six, R.id.bt_eight, R.id.bt_seven, R.id.bt_nine, R.id.bt_zero, R.id.bt_delete, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_two:
                clickNumber(2);
                break;
            case R.id.bt_one:
                clickNumber(1);
                break;
            case R.id.bt_three:
                clickNumber(3);
                break;
            case R.id.bt_five:
                clickNumber(5);
                break;
            case R.id.bt_four:
                clickNumber(4);
                break;
            case R.id.bt_six:
                clickNumber(6);
                break;
            case R.id.bt_eight:
                clickNumber(8);
                break;
            case R.id.bt_seven:
                clickNumber(7);
                break;
            case R.id.bt_nine:
                clickNumber(9);
                break;
            case R.id.bt_zero:
                clickNumber(0);
                break;
            case R.id.bt_delete:
                if (isBack) {
                    finish();
                } else {
                    deleteNumber();
                }
                break;
            case R.id.bt_next:
                if (isNext && isLogin == false) {
                    sendCode(tvPhone.getText().toString().trim());
                    TimeCount timeCount = new TimeCount(60000, 1000, tvTime);
                    timeCount.start();
                } else if (isLogin) {
                    double lat = getIntent().getDoubleExtra(Constant.TAG_LAT, 0);
                    double lon = getIntent().getDoubleExtra(Constant.TAG_LON, 0);
                    onLogin(tvPhone.getText().toString().trim(), tvCode.getText().toString().trim(), JpushID, lat + "", lon + "");
                }
                break;
        }
    }


}
