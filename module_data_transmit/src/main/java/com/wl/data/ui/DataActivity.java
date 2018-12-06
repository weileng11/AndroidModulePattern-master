package com.wl.data.ui;

import android.os.Bundle;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.guiying.module.common.base.BaseActionBarActivity;
import com.guiying.module.common.bean.EventBusBean;
import com.guiying.module.common.bean.JavaBean;
import com.wl.data.R;
import java.util.List;
import java.util.Map;

/**
 组件化数传递  ARouter.getInstance().inject(this); 注意添加注解
 */
@Route(path = "/datalist/data")
public class DataActivity extends BaseActionBarActivity
{
	@Autowired
	String name = "hahahha";

	@Autowired
	int age;

	@Autowired(name = "boy")//映射参数名
			boolean sex;

	@Autowired
	long high;

	@Autowired
	String url;

	@Autowired
	EventBusBean pac;

	@Autowired
	JavaBean obj;

	@Autowired
	List<JavaBean> objList;

	@Autowired
	Map<String, List<JavaBean>> map;

	@Autowired
	int height = 21;//上页面没有传递

	TextView txvDataContent;
	
	@Override
	protected int setTitleId(){
		return R.string.datas;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		txvDataContent=findViewById(R.id.txv_data_content);
		//String data=getIntent().getStringExtra("data");
		//txvDataContent.setText(data);
		initView();
	}
	
	private void initView(){
		String params = String.format(
				"name=%s,\n age=%s, \n height=%s,\n girl=%s,\n high=%s,\n url=%s,\n pac=%s,\n obj=%s \n" +
				"  objList=%s, \n map=%s",
				name,
				age,
				height,
				sex,
				high,
				url,
				pac.getProject(),
				obj.getName(),
				objList.get(0).getName(),
				map.get("testMap").get(0).getName()
		);
		txvDataContent.setText(params);
	}
}
