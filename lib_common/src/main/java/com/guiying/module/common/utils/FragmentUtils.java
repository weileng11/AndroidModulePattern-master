package com.guiying.module.common.utils;

import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.launcher.ARouter;
import com.guiying.module.common.base.BaseFragment;

/**
 * @author: ${bruce}
 * @project: AndroidModulePattern-master
 * @package: com.guiying.module.common.utils
 * @description:
 * @date: 2018/12/5 0005  
 * @time: 下午 5:39
 */
public class FragmentUtils
{
	public static Fragment getHomeFragment() {
		Fragment fragment = (BaseFragment) ARouter.getInstance().build("/girls/girl").navigation();
		return fragment;
	}
	
	public static Fragment getChatFragment() {
		Fragment fragment = (Fragment) ARouter.getInstance().build("/news/new").navigation();
		return fragment;
	}

}
