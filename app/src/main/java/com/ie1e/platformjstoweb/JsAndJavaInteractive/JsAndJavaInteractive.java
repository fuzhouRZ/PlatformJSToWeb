package com.ie1e.platformjstoweb.JsAndJavaInteractive;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.android.volley.VolleyError;
import com.ie1e.platformjstoweb.AliPay.AliPay;
import com.ie1e.platformjstoweb.Application.MyApplication;
import com.ie1e.platformjstoweb.utils.VolleyListenerInterface;
import com.ie1e.platformjstoweb.utils.VolleyUtils;
import com.tencent.mm.sdk.modelpay.PayReq;

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
    public void funWeiXinOauth2Login(String params){

    }

    /**
     * QQ授权登录
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funQQOauth2Login(String params){

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


}
