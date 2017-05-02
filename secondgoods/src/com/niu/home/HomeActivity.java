package com.niu.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import com.niu.secondgoods.R;

public class HomeActivity extends FragmentActivity implements OnTabChangeListener,OnPageChangeListener{
	private TabHost tabHost;//
	private ViewPager pager;// 可切view
	TabContentFactory tf = new TabContentFactory() {
		@Override
		public View createTabContent(String arg0) {
			View ret = new View(getApplicationContext());
			ret.setMinimumHeight(0);
			ret.setMinimumWidth(0);
			return ret;
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		initView();// 关联控件
		// TabHost ViewPager
		pager = (ViewPager) findViewById(R.id.viewpager);
		// 放置适配器
		pager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("商品").setIndicator("商品").setContent(tf));
		//tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.search);
		tabHost.addTab(tabHost.newTabSpec("订单").setIndicator("订单").setContent(tf));
		tabHost.addTab(tabHost.newTabSpec("发布").setIndicator("发布").setContent(tf));
		tabHost.addTab(tabHost.newTabSpec("我的").setIndicator("我的").setContent(tf));
		/*for (int i = 0; i < 3; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = tabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			tabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			tabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);
		}*/
		/*Intent intent = getIntent();
		int tabNum = intent.getIntExtra("tabNum", 1);*/
		tabHost.setCurrentTab(1);//设置默认tab
		pager.setCurrentItem(1);//设置默认页
		//放置监听
		tabHost.setOnTabChangedListener(this);
		pager.setOnPageChangeListener(this);
	}
	
	@Override
	public void onTabChanged(String arg0) {
		if(arg0.equals("商品")){
			pager.setCurrentItem(0);
		}else if(arg0.equals("订单")){
			pager.setCurrentItem(1);
		}else if(arg0.equals("发布")){
			pager.setCurrentItem(2);
		}else{
			pager.setCurrentItem(3);
		}
	}
	

	/**
	 * 关联控件
	 */
	public void initView() {
		pager = (ViewPager) findViewById(R.id.viewpager);
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		tabHost.setCurrentTab(arg0);
		
	}
	/**
	 * 确认退出dialog
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					HomeActivity.this);
			alertDialog.setTitle(HomeActivity.this
					.getString(R.string.app_close));
			alertDialog.setPositiveButton(
					HomeActivity.this.getString(R.string.btn_ok),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// SysApplication.getInstance().addActivity(this);
							finish();
							System.exit(0);
						}
					});
			alertDialog.setNegativeButton(
					HomeActivity.this.getString(R.string.btn_cancel),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			alertDialog.show();
		}
		return true;
	}
}
