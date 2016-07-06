package com.ie1e.platformjstoweb.wxapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.security.mobile.module.commonutils.LOG;
import com.android.volley.VolleyError;
import com.ie1e.platformjstoweb.Application.MyApplication;
import com.ie1e.platformjstoweb.MainActivity;
import com.ie1e.platformjstoweb.R;
import com.ie1e.platformjstoweb.param.Constant;
import com.ie1e.platformjstoweb.utils.VolleyListenerInterface;
import com.ie1e.platformjstoweb.utils.VolleyUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    TextView tv_weixin;
    static String codes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin_result);
        tv_weixin = (TextView) findViewById(R.id.tv_weixin);
        api = WXAPIFactory.createWXAPI(this, Constant.WEIXIN_APP_ID, true);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                codes = sendResp.code;
                Constant.WEIXIN_CODE = codes;
                VolleyUtils.RequestGet(WXEntryActivity.this, "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.WEIXIN_APP_ID + "&secret=" + Constant.WEIXIN_APP_SECRET + "&code=" + Constant.WEIXIN_CODE + "&grant_type=authorization_code", new VolleyListenerInterface(WXEntryActivity.this, VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        try {
                            JSONObject response = new JSONObject(result);
                            String accessToken = response.getString("access_token");
                            String openId = response.getString("openid");
                            //accessToken和openId再请求一次
                            VolleyUtils.RequestGet(WXEntryActivity.this, "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId, new VolleyListenerInterface(WXEntryActivity.this, VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorListener) {
                                @Override
                                public void onMySuccess(String result) {//result 包括头像，昵称，地区等。。。


                                }

                                @Override
                                public void onMyError(VolleyError error) {

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
                MainActivity.mWebView.post(new Runnable() {
                    @Override
                    public void run() {

                        tv_weixin.setText("登录成功");
                        MainActivity.mWebView.loadUrl("javascript:LoginToJs('登录成功')");
                    }
                });
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                MainActivity.mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_weixin.setText("登录取消");
                        MainActivity.mWebView.loadUrl("javascript:LoginToJs('登录取消')");
                    }
                });
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                MainActivity.mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_weixin.setText("登录被拒绝");
                        MainActivity.mWebView.loadUrl("javascript:LoginToJs('登录被拒绝')");
                    }
                });
                break;
            default:
                MainActivity.mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_weixin.setText("登录失败");
                        MainActivity.mWebView.loadUrl("javascript:LoginToJs('登录失败')");
                    }
                });
                break;
        }


    }

}