package com.example.finderproject.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.guojiawei.finderproject.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_wxentry);
        api = WXAPIFactory.createWXAPI(this, "wxf4d9d01961dc2174", false);
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onResp(BaseResp resp) { //在这个方法中处理微信传回的数据
        //形参resp 有下面两个个属性比较重要
        //1.resp.errCode
        //2.resp.transaction则是在分享数据的时候手动指定的字符创,用来分辨是那次分享(参照4.中req.transaction)
        switch (resp.errCode) { //根据需要的情况进行处理
            case BaseResp.ErrCode.ERR_OK:
                share(true);
                //正确返回
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                share(false);

                //用户取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                share(false);

                //认证被否决
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                share(false);

                //发送失败
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                share(false);

                //不支持错误
                break;
            case BaseResp.ErrCode.ERR_COMM:
                share(false);

                //一般错误
                break;
            default:
                //其他不可名状的情况
                share(false);
                break;
        }
    }

    private void share(boolean result) {
        String message = "分享已取消";
        if (result) message = "分享成功";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onReq(BaseReq req) {
        String a = "";
        //......这里是用来处理接收的请求,暂不做讨论
    }
}
