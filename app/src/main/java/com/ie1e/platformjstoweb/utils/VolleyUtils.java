package com.ie1e.platformjstoweb.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.ie1e.platformjstoweb.Application.MyApplication;

import java.util.Map;




/**
 * 作者：zf on 2016/6/28 16:12
 * 邮箱：752323877@qq.com
 * Volley get/post请求工具类
 */
public class VolleyUtils {
    public static Context context;
    public static StringRequest stringRequest;
    public static JsonObjectRequest jsonObjectRequest;

    /**
     *
     * @param context
     * @param url

     * @param listener
     */
    public static void RequestGet(Context context,String url,VolleyListenerInterface listener)
    {

        stringRequest = new StringRequest(Request.Method.GET,url,listener.responseListener(),listener.errorListener());
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();
    }
    
    /**
     * post
     * @param mContext
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void RequestPost(Context mContext, String url, String tag, final Map<String,String> params, VolleyListenerInterface listener){
        MyApplication.getRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST,url,listener.responseListener(),listener.errorListener()){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();

    }




}
