package com.niu.allgoods;

import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.niu.bean.Goods;
import com.niu.bean.User;
import com.niu.imageload.Const;
import com.niu.login.LoginAct;
import com.niu.regist.RegisAct;
import com.niu.secondgoods.R;
import com.niu.util.Constant;
import com.niu.util.GetTimeNow;
import com.niu.util.JasonIterator;
import com.niu.util.MyAsyncTask;
import com.niu.util.URLFactory;

public class SingleGoodsAct extends Activity{
	User seller;//根据商品id查询到的卖家
	User buyer = new User(1, "王", "1", "1", "1", "1", "123", 1, 2, 123);//intent传过来的买家
	Goods goods;//当前商品
	
	TextView tv_name;
	TextView tv_price;
	TextView tv_desc;
	TextView tv_seller_name;
	TextView tv_phone;
	TextView tv_qq;
	Button btn_buy;
	ImageView iv_pic;
	private MyAsyncTask myAsyncTask_goods;
	private MyAsyncTask myAsyncTask_order;
	List<Map<String, String>> list;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == Constant.WHAT_SINGLEGOODS) {
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.informationJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						// 生成当前user对象
						seller = new User();
						seller.setUser_name(map1.get("user_name"));
						seller.setUser_phone(map1.get("user_phone"));
						seller.setUser_qq(map1.get("user_qq"));
						seller.setUser_id(Integer.parseInt(map1.get("user_id")));
						// 关闭线程
						myAsyncTask_goods.cancel(true);
						myAsyncTask_goods = null;
						tv_seller_name.setText(seller.getUser_name());
						tv_phone.setText(seller.getUser_phone());
						tv_qq.setText(seller.getUser_qq());
						Toast.makeText(getApplicationContext(),
								"卖家查询成功" + seller, Toast.LENGTH_SHORT).show();
						/*Intent intent = new Intent(NewGoodsAct.this,GoodsImageUpAct.class);
						Bundle bundle = new Bundle();
						bundle.putString("goods_id", goods.getGoods_id() + "");
						intent.putExtra("goods", bundle);
						startActivity(intent);*/
					} else {
						Toast.makeText(getApplicationContext(), "卖家查询失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}else if (msg.what == Constant.WHAT_INSERT_ORDER) {
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.simpleJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						Toast.makeText(getApplicationContext(), "下单成功", Toast.LENGTH_SHORT).show();
						// 关闭线程
						myAsyncTask_order.cancel(true);
						myAsyncTask_order = null;
						//跳转界面
						/*Intent intent = new Intent(RegisAct.this, LoginAct.class);
						startActivity(intent);*/
					} else {
						Toast.makeText(getApplicationContext(), "下单失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}

		};
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_info);
		initView();
		FinalBitmap fb=FinalBitmap.create(this);
		Intent intent = getIntent();
		goods = (Goods) intent.getSerializableExtra("goods");
		String searchUserById = URLFactory.searchUserById(goods.getSeller_id()+"");
		Toast.makeText(getApplicationContext(), searchUserById,
				Toast.LENGTH_SHORT).show();
		myAsyncTask_goods = new MyAsyncTask(handler,Constant.WHAT_SINGLEGOODS);
		myAsyncTask_goods.execute(searchUserById);
		Toast.makeText(getApplicationContext(), goods.getGoods_pic(),
				Toast.LENGTH_SHORT).show();
		String picUrl = Const.DOWNLOAD_URL+goods.getGoods_pic();
		fb.display(iv_pic, picUrl);
		tv_name.setText(goods.getGoods_name());
		tv_price.setText(goods.getGoods_price());
		tv_desc.setText(goods.getGoods_desc());
		//购买
		btn_buy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String buyer_name = buyer.getUser_name();
				String time = GetTimeNow.getTime();
				int seller_id = seller.getUser_id();
				int buyer_id = buyer.getUser_id();
				int goods_id = goods.getGoods_id();
				String goods_name = goods.getGoods_name();
				String goods_price = goods.getGoods_price();
				String insertOrderInfo = URLFactory.insertOrderInfo(buyer_name, time, seller_id+"", buyer_id+"", goods_id+"",goods_name,goods_price);
				Toast.makeText(getApplicationContext(), insertOrderInfo,
						Toast.LENGTH_SHORT).show();
				myAsyncTask_order = new MyAsyncTask(handler,Constant.WHAT_INSERT_ORDER);
				myAsyncTask_order.execute(insertOrderInfo);
			}
		});
	}
	public void initView(){
		tv_name = (TextView) findViewById(R.id.goods_info_tv_name);
		tv_price = (TextView) findViewById(R.id.goods_info_tv_price);
		tv_desc = (TextView) findViewById(R.id.goods_info_tv_desc);
		tv_phone = (TextView) findViewById(R.id.goods_info_tv_seller_phone);
		tv_seller_name = (TextView) findViewById(R.id.goods_info_tv_seller_name);
		tv_qq = (TextView) findViewById(R.id.goods_info_tv_seller_qq);
		btn_buy = (Button) findViewById(R.id.goods_info_btn_buy);
		iv_pic = (ImageView) findViewById(R.id.goods_info_iv);
	}
}
