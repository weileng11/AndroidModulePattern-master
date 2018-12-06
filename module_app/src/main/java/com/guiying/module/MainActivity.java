package com.guiying.module;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.KeyEvent;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.guiying.module.common.base.BaseActivity;
import com.guiying.module.common.base.ViewManager;
import com.guiying.module.common.bean.EventBusBean;
import com.guiying.module.common.bean.JavaBean;
import com.guiying.module.common.utils.RouteUtils;
import com.guiying.module.common.utils.ToastUtils;
import com.guiying.module.common.utils.UIUtils;
import com.guiying.module.main.BottomNavigationActivity;
import java.util.*;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/7/1 13:13
 * @version V1.2.0
 * @name MainActivity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.news_button).setOnClickListener(this);
        findViewById(R.id.girls_button).setOnClickListener(this);
        findViewById(R.id.fragment_button).setOnClickListener(this);
        findViewById(R.id.data_button).setOnClickListener(this);
	    findViewById(R.id.data_aiam_button).setOnClickListener(this);
	    findViewById(R.id.data_url_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.news_button) {
            //跳转到NewsCenterActivity
            ARouter.getInstance().build("/news/center").navigation();
        } else if (view.getId()==R.id.girls_button) {
            //跳转到GirlsActivity
            ARouter.getInstance().build("/girls/list").navigation();
        } else if (view.getId()==R.id.fragment_button) {
            startActivity(new Intent(this, BottomNavigationActivity.class));
        }else if (view.getId()==R.id.data_button) {
	        setDatatransmit();
        }else if (view.getId()==R.id.data_aiam_button) {
        	//带动画
	        startAnimToAct(view);
        }else if (view.getId()==R.id.data_url_button) {
	        //通过URL跳转（webview）
	        ARouter.getInstance()
			        .build(RouteUtils.Me_WebView)
			        .withString("url", "file:///android_asset/schame-test.html")
			        .navigation();
        }
    }

    //路由传递数据
    public void setDatatransmit(){
		/**
		 * 序列化过得
		 * 必须先初始化JsonServiceImpl实现SerializationService
		 */
		EventBusBean eventBusBean = new EventBusBean();
		eventBusBean.setProject("android");
		eventBusBean.setNum(3);

		/**
		 * 普通的javaBean
		 */
		JavaBean javaBean = new JavaBean();
		javaBean.setName("huangxiaoguo");
		javaBean.setAge(25);

		List<JavaBean> objList = new ArrayList<>();
		objList.add(javaBean);

		Map<String, List<JavaBean>> map = new HashMap<>();
		map.put("testMap", objList);

		ARouter.getInstance().build("/datalist/data")
				//.withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)//带动画
				//.navigation(getContext());//context上下文不传无效果
				.withString("name", "老王")
				.withInt("age", 18)
				.withBoolean("boy", true)
				.withLong("high", 180)
				.withString("url", "https://www.baidu.com")
				.withParcelable("pac", eventBusBean)
				.withObject("obj", javaBean)
				.withObject("objList", objList)
				.withObject("map", map)
				.navigation();
		//Intent intent=new Intent(this,DataActivity.class);
		//intent.putExtra("data","datas");
		//startActivity(intent);
	}
	
	private void startAnimToAct(View v){
		/**
		 * 序列化过得
		 * 必须先初始化JsonServiceImpl实现SerializationService
		 */
		EventBusBean eventBusBean = new EventBusBean();
		eventBusBean.setProject("android");
		eventBusBean.setNum(3);
		
		/**
		 * 普通的javaBean
		 */
		JavaBean javaBean = new JavaBean();
		javaBean.setName("huangxiaoguo");
		javaBean.setAge(25);
		
		List<JavaBean> objList = new ArrayList<>();
		objList.add(javaBean);
		
		Map<String, List<JavaBean>> map = new HashMap<>();
		map.put("testMap", objList);
		
		////旧版本转场动画
		//ARouter.getInstance()
		//		.build("/dataAnim/data")
		//		.withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
		//		.withString("name", "老王")
		//		.withInt("age", 18)
		//		.withBoolean("boy", true)
		//		.withLong("high", 180)
		//		.withString("url", "https://www.baidu.com")
		//		.withParcelable("pac", eventBusBean)
		//		.withObject("obj", javaBean)
		//		.withObject("objList", objList)
		//		.withObject("map", map)
		//		.navigation(this);//context上下文不传无效果
		
		//新版本转场动画
		if (Build.VERSION.SDK_INT>=16) {
			ActivityOptionsCompat compat = ActivityOptionsCompat.
					makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
			ARouter.getInstance()
					.build("/dataAnim/data")
					.withOptionsCompat(compat)
					.withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
					.withString("name", "老王")
					.withInt("age", 18)
					.withBoolean("boy", true)
					.withLong("high", 180)
					.withString("url", "https://www.baidu.com")
					.withParcelable("pac", eventBusBean)
					.withObject("obj", javaBean)
					.withObject("objList", objList)
					.withObject("map", map)
					.navigation();
		} else {
			UIUtils.showToast("API < 16,不支持新版本动画");
		}
		
	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShortToast(getString(com.guiying.module.main.R.string.app_exit_hint));
                mExitTime = System.currentTimeMillis();
            } else {
                ViewManager.getInstance().exitApp(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
