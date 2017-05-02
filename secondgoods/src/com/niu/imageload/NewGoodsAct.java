package com.niu.imageload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.niu.bean.Goods;
import com.niu.db.Goodsdb;
import com.niu.login.LoginAct;
import com.niu.secondgoods.R;
import com.niu.util.Constant;
import com.niu.util.GetTimeNow;
import com.niu.util.JasonIterator;
import com.niu.util.MyAsyncTask;
import com.niu.util.URLFactory;

public class NewGoodsAct extends Activity {
	int typeSpinnerSelect = 0;// 商品种类默认选择0
	List<String> typeArray = new ArrayList<String>();
	// 控件声明
	EditText et_name;
	EditText et_price;
	EditText et_desc;// 商品描述
	Button btn_goImage;
	Spinner sp_type;
	private List<Map<String, String>> list;
	private MyAsyncTask myAsyncTask;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == Constant.WHAT_INSERT_GOODS) {
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.informationJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						// 生成当前user对象
						Goods goods = new Goods();
						goods.setGoods_id(Integer.parseInt(map1.get(Goodsdb.GOODS_ID)));
						// 关闭线程
						myAsyncTask.cancel(true);
						myAsyncTask = null;

						Toast.makeText(getApplicationContext(),
								"商品入表成功" + goods, Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(NewGoodsAct.this,GoodsImageUpAct.class);
						Bundle bundle = new Bundle();
						bundle.putString("goods_id", goods.getGoods_id() + "");
						intent.putExtra("goods", bundle);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(), "商品入表失败",
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
		setContentView(R.layout.new_goods);
		initView();
		MySetAdapter1();
		sp_type.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				typeSpinnerSelect = position;// 记录商品种类
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		btn_goImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = et_name.getText().toString();
				String price = et_price.getText().toString();
				String desc = et_desc.getText().toString();
				if (name.equals("") || price.equals("") || desc.equals("")) {
					Toast.makeText(getApplicationContext(), "输入框不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					// 开启异步
					//String type = typeArray.get(typeSpinnerSelect);
					int seller_id = 3;//模拟卖家id
					String time = GetTimeNow.getTime();//发布时间
					//拼接url
					String insertGoodsInfoUrl = URLFactory.insertGoodsInfo(
							name, price, desc, typeSpinnerSelect + "", seller_id+"", time);
					Toast.makeText(getApplicationContext(), insertGoodsInfoUrl,
							Toast.LENGTH_SHORT).show();
					myAsyncTask = new MyAsyncTask(handler,
							Constant.WHAT_INSERT_GOODS);
					myAsyncTask.execute(insertGoodsInfoUrl);
				}
			}
		});

	}

	public void initView() {
		et_name = (EditText) findViewById(R.id.newg_et_name);
		et_price = (EditText) findViewById(R.id.newg_et_price);
		et_desc = (EditText) findViewById(R.id.newg_et_desc);
		btn_goImage = (Button) findViewById(R.id.newg_btn_goimage);
		sp_type = (Spinner) findViewById(R.id.newg_sp_type);
	}

	// spinner设置adapter
	public void MySetAdapter1() {
		typeArray.add("校园代步");
		typeArray.add("书籍");
		typeArray.add("生活日用品");
		typeArray.add("电子设备");
		ArrayAdapter<String> typeArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.simple_spinner_item, R.id.spi_tv, typeArray);
		sp_type.setAdapter(typeArrayAdapter);
	}
	public void insertGoods(){
		
	}
}
