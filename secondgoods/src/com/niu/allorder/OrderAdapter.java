package com.niu.allorder;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.niu.bean.Order;
import com.niu.secondgoods.R;
/**
 * 商品适配器
 * @author 王飞鱼
 *
 */
public class OrderAdapter extends BaseAdapter{
	List<Order> list;//数据
	private Context context;
	public OrderAdapter(List<Order> list,Context context) {
		super();
		this.list = list;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//关联布局
		View row = LayoutInflater.from(context).inflate(R.layout.all_order_items, parent,false);
		//关联控件
		TextView tvName = (TextView) row.findViewById(R.id.all_ot_name);
		TextView tvPrice = (TextView) row.findViewById(R.id.all_ot_price);
		TextView tvTime = (TextView) row.findViewById(R.id.all_ot_time);
		//赋值
		tvName.setText("商品名称： "+list.get(position).getGoods_name() + "");
		tvPrice.setText("订单价格： "+list.get(position).getGoods_price() + "");
		tvTime.setText("订单日期： "+list.get(position).getOrder_time() + "");
		//修改后返回
		return row;

	}

}
