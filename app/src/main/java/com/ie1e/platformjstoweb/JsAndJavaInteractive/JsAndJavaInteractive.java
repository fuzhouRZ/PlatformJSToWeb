package com.ie1e.platformjstoweb.JsAndJavaInteractive;

import android.content.Context;
import android.webkit.JavascriptInterface;

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
    public void funPayFromWeiXin(String params){

    }

    /**
     * 分享新浪微博
     * */
    @JavascriptInterface //sdk17版本以上加上注解
    public void funPayFromAli(String params){

    }


}
