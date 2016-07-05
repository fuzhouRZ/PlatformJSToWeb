package com.ie1e.platformjstoweb.JsAndJavaInteractive;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.ie1e.platformjstoweb.AliPay.AliPay;
import com.ie1e.platformjstoweb.Application.MyApplication;
import com.ie1e.platformjstoweb.param.Constant;
import com.ie1e.platformjstoweb.utils.VolleyListenerInterface;
import com.ie1e.platformjstoweb.utils.VolleyUtils;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * JS调用Java的本地方法
 * Created by zhengxizhen on 16/6/30.
 */
public class JsAndJavaInteractive {
    Context mContxt;

    public JsAndJavaInteractive(Context mContxt) {
        this.mContxt = mContxt;
    }

    /**
     * 分享至微信
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funShareWeiXin(String params){

    }

    /**
     * 分享新浪微博
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funShareWeiBo(String params){

    }

    /**
     * 微信授权登录
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funWeiXinOauth2Login(){

    }

    /**
     * QQ授权登录
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funQQOauth2Login(){
        Tencent tencent = Tencent.createInstance(Constant.TENCENT_APP_ID,mContxt);
        tencent.login((Activity) mContxt,"all",new BaseIUiListener());
    }

    /**
     * 新浪微博授权登录
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funWeiBoOauth2Login(String params){

    }
    /**
     * 微信支付
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funPayFromWeiXin(){
        VolleyUtils.RequestGet(mContxt, "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android", new VolleyListenerInterface(mContxt,VolleyListenerInterface.mListener,VolleyListenerInterface.mErrorListener) {
            @Override
            public void onMySuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    PayReq req = new PayReq();
                    req.appId = json.getString("appid");
                    req.partnerId = json.getString("partnerid");
                    req.packageValue = json.getString("package");
                    req.nonceStr = json.getString("noncestr");
                    req.timeStamp = json.getString("timestamp");
                    req.prepayId = json.getString("prepayid");
                    req.sign = json.getString("sign");
                    req.extData = "app data";
                    MyApplication.getApi().sendReq(req);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onMyError(VolleyError error) {
            }
        });

    }

    /**
     * 支付宝支付
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funPayFromAli(String name,String content,String price){
        AliPay pay = new AliPay(mContxt);
        pay.pay(name, content, price);
    }

    class BaseIUiListener implements IUiListener
    {
        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(UiError uiError) {

        }
    }


}
