package com.niu.allgoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.niu.bean.Goods;
import com.niu.db.Goodsdb;
import com.niu.secondgoods.R;
import com.niu.util.Constant;
import com.niu.util.JasonIterator;
import com.niu.util.MyAsyncTask;
import com.niu.util.URLFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ShowAllGoodsFragment extends Fragment{
	Activity activity;
	List<String> typeArray1 = new ArrayList<String>();
	Spinner sp_type;
	ListView lv_allGoods;
	List<Goods> listGoods = new ArrayList<Goods>();//数据
	private List<Map<String, String>> list;
	private MyAsyncTask myAsyncTask;
	GoodsAdapter myAdapter;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == Constant.WHAT_ALLGOODS) {
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.longJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						listGoods.clear();
						//生成当前MessageItem对象
                    	for(int i = 1;i < list.size();i++){
                    		int goods_id = Integer.parseInt(list.get(i).get(Goodsdb.GOODS_ID));
                    		String goods_name = list.get(i).get(Goodsdb.GOODS_NAME);
                    		int goods_type = Integer.parseInt(list.get(i).get(Goodsdb.GOODS_TYPE));
                    		int goods_sign = Integer.parseInt(list.get(i).get(Goodsdb.GOODS_SIGN));
                    		String goods_price = list.get(i).get(Goodsdb.GOODS_PRICE);
                    		String goods_time = list.get(i).get(Goodsdb.GOODS_TIME);
                    		String seller_id = list.get(i).get(Goodsdb.SELLER_ID);
                    		String goods_pic = list.get(i).get(Goodsdb.GOODS_PIC);
                    		String goods_desc = list.get(i).get(Goodsdb.GOODS_DESC);
                    		Goods goods = new Goods(goods_type,goods_price, goods_time,goods_sign, Integer.parseInt(seller_id), goods_desc, goods_pic,goods_name);
                    		listGoods.add(goods);
                    	}
                    	myAdapter.notifyDataSetChanged();//刷新适配器
                    	
						// 关闭线程
						myAsyncTask.cancel(true);
						myAsyncTask = null;

						Toast.makeText(activity.getApplicationContext(),
								"商品查询成功" + listGoods, Toast.LENGTH_SHORT).show();
						/*Intent intent = new Intent(NewGoodsAct.this,GoodsImageUpAct.class);
						Bundle bundle = new Bundle();
						bundle.putString("goods_id", goods.getGoods_id() + "");
						intent.putExtra("goods", bundle);
						startActivity(intent);*/
					} else {
						Toast.makeText(activity.getApplicationContext(), "商品查询失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(activity.getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}

		};
	};
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	return inflater.inflate(R.layout.show_all_goods, container, false);
    }
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	this.activity = activity;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	initView(view);
    	typeArray1.clear();
		setGoods();
		setType();
		//实例化适配器
		myAdapter = new GoodsAdapter(listGoods,activity.getApplication());
		//加载适配器
		lv_allGoods.setAdapter(myAdapter);
		//根据类别查询商品
		sp_type.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String searchGoodsByType = URLFactory.searchGoodsByType(position + "");
				Toast.makeText(activity.getApplicationContext(), searchGoodsByType,
						Toast.LENGTH_SHORT).show();
				myAsyncTask = new MyAsyncTask(handler,Constant.WHAT_ALLGOODS);
				myAsyncTask.execute(searchGoodsByType);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		//进入详细商品界面
		lv_allGoods.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(activity,SingleGoodsAct.class);
            	intent.putExtra("goods", listGoods.get(position));
				startActivity(intent);
			}
			
		});
    }
    public void initView(View view){
		sp_type = (Spinner) view.findViewById(R.id.showag_sp_type);
		lv_allGoods = (ListView) view.findViewById(R.id.showag_listView);
	}
	public void setGoods(){
		listGoods.add(new Goods(1, 0, "11", "11", 1, 1, "wowowoow", "wowowoow", "1"));
		listGoods.add(new Goods(2, 1, "22", "11", 0, 1, "wowowoow", "wowowoow", "2"));
		listGoods.add(new Goods(3, 2, "13", "11", 1, 2, "wowowoow", "wowowoow", "3"));
	}
	public void setType(){
		typeArray1.add("校园代步1");
		typeArray1.add("书籍");
		typeArray1.add("生活日用品");
		typeArray1.add("电子设备");
		ArrayAdapter<String> typeArrayAdapter = new ArrayAdapter<String>(activity,
				R.layout.simple_spinner_item, R.id.spi_tv, typeArray1);
		sp_type.setAdapter(typeArrayAdapter);
	}
}
