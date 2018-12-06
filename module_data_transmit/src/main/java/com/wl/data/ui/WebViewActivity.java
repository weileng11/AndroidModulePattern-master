package com.wl.data.ui;

import android.os.Bundle;
import android.webkit.WebView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.guiying.module.common.base.BaseActionBarActivity;
import com.guiying.module.common.utils.RouteUtils;
import com.wl.data.R;

/**
 * @author: ${bruce}
 * @project: AndroidModulePattern-master
 * @package: com.wl.data.data
 * @description:
 * @date: 2018/12/6 0006  
 * @time: 上午 10:08
 */
@Route(path = RouteUtils.Me_WebView)
public class WebViewActivity extends BaseActionBarActivity
{
	
	private WebView mWebview;
	
	@Override
	protected int setTitleId(){
		return R.string.datas;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		initView();
	}
	
	private void initView() {
		mWebview = (WebView) findViewById(R.id.webview);
		mWebview.loadUrl(getIntent().getStringExtra("url"));
	}
}