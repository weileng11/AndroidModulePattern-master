package com.guiying.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.guiying.module.common.base.BaseActivity;
import com.guiying.module.common.base.ViewManager;
import com.guiying.module.common.utils.ToastUtils;
import com.guiying.module.main.BottomNavigationActivity;

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
        setContentView(com.guiying.module.main.R.layout.activity_main);
        findViewById(com.guiying.module.main.R.id.news_button).setOnClickListener(this);
        findViewById(com.guiying.module.main.R.id.girls_button).setOnClickListener(this);
        findViewById(com.guiying.module.main.R.id.fragment_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==com.guiying.module.main.R.id.news_button) {
            //跳转到NewsCenterActivity
            ARouter.getInstance().build("/news/center").navigation();
        } else if (view.getId()==com.guiying.module.main.R.id.girls_button) {
            //跳转到GirlsActivity
            ARouter.getInstance().build("/girls/list").navigation();
        } else if (view.getId()==com.guiying.module.main.R.id.fragment_button) {
            startActivity(new Intent(this, BottomNavigationActivity.class));
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
