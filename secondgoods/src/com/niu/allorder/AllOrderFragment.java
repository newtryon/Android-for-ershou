package com.niu.allorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.niu.allgoods.GoodsAdapter;
import com.niu.bean.Goods;
import com.niu.bean.Order;
import com.niu.db.Goodsdb;
import com.niu.imageload.GoodsImageUpAct;
import com.niu.imageload.NewGoodsAct;
import com.niu.secondgoods.R;
import com.niu.util.Constant;
import com.niu.util.GetTimeNow;
import com.niu.util.JasonIterator;
import com.niu.util.MyAsyncTask;
import com.niu.util.URLFactory;


public class AllOrderFragment extends Fragment implements OnClickListener{
	Activity activity;//主类上下文
	ListView lv_allOrder;
	List<Order> listOrder = new ArrayList<Order>();//数据
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(com.niu.secondgoods.R.layout.all_order, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		listOrder.clear();
		setOrder();
		//实例化适配器
		OrderAdapter myAdapter = new OrderAdapter(listOrder,activity);
		//加载适配器
		lv_allOrder.setAdapter(myAdapter);
		
	}
	public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	this.activity = activity;
    }
	//点击方法
	@Override
	public void onClick(View arg0) {
		
	}
	public void setOrder(){
		listOrder.add(new Order(1, "12.30", "123", 1, 2, 3,"1","1000"));
		listOrder.add(new Order(2, "12.30", "12345", 1, 2, 3,"1","1000"));
		listOrder.add(new Order(3, "12.30", "12367888", 1, 2, 3,"1","1000"));
	}
	private void initView(View view) {
		lv_allOrder = (ListView) view.findViewById(R.id.showao_listView);
	}
	


}
