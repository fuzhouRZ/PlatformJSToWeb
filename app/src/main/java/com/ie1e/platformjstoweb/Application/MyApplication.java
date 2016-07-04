package com.ie1e.platformjstoweb.Application;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ie1e.platformjstoweb.param.Constant;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 作者：zf on 2016/7/4 16:22
 * 邮箱：752323877@qq.com
 */
public class MyApplication extends Application{

    public static IWXAPI api;

    private static Context context;


    public static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
        regToWx();

    }
    private void regToWx()
    {
        //通过WXAPIFactory工厂,获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID,true);
        //将应用的appId注册到微信
        api.registerApp(Constant.APP_ID);
    }
    public static IWXAPI getApi()
    {
        return  api;
    }
    public static Context getContext()
    {
        return context;
    }
    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }
}
