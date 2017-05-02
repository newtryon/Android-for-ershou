package com.niu.allgoods;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.niu.bean.Goods;
import com.niu.secondgoods.R;
/**
 * 商品适配器
 * @author 王飞鱼
 *
 */
public class GoodsAdapter extends BaseAdapter{
	List<Goods> list;//数据
	private Context context;
	public GoodsAdapter(List<Goods> list,Context context) {
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
		View row = LayoutInflater.from(context).inflate(R.layout.all_goods_items, parent,false);
		//关联控件
		TextView tvName = (TextView) row.findViewById(R.id.all_goods_items_tv_name);
		TextView tvPrice = (TextView) row.findViewById(R.id.all_goods_items_tv_price);
		TextView tvDesc = (TextView) row.findViewById(R.id.all_goods_items_tv_desc);
		//赋值
		tvName.setText("商品名称： "+list.get(position).getGoods_name() + "");
		tvPrice.setText("商品价格： "+list.get(position).getGoods_price() + "");
		tvDesc.setText("商品价格： "+list.get(position).getGoods_desc() + "");
		//修改后返回
		return row;

	}

}
