package com.ie1e.platformjstoweb.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ie1e.platformjstoweb.MainActivity;
import com.ie1e.platformjstoweb.R;
import com.ie1e.platformjstoweb.param.Constant;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private TextView tv_payresult;
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
		tv_payresult = (TextView) findViewById(R.id.tv_payresult);
    	api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
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
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX)
		{
			switch (resp.errCode)
			{
				case 0:
					tv_payresult.setText("支付成功");
					MainActivity.mWebView.post(new Runnable() {
						@Override
						public void run() {
							MainActivity.mWebView.loadUrl("javascript:AliPayToJs('支付成功')");
						}
					});
					break;
				case -1:
					tv_payresult.setText("支付错误");
					MainActivity.mWebView.post(new Runnable() {
						@Override
						public void run() {
							MainActivity.mWebView.loadUrl("javascript:AliPayToJs('支付错误')");
						}
					});
					break;
				case -2:
					tv_payresult.setText("用户取消");
					MainActivity.mWebView.post(new Runnable() {
						@Override
						public void run() {
							MainActivity.mWebView.loadUrl("javascript:AliPayToJs('用户取消')");
						}
					});
					break;
			}
		}
	}
}