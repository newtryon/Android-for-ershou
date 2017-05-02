package com.niu.dao;

import java.util.ArrayList;
import java.util.List;

import com.niu.bean.Order;
import com.niu.conn.ConnectionFactory;

public class OrderDao extends ConnectionFactory{
	public boolean insertOrder(Order order) {
		String sql = "insert into ORDER_INFO(order_time,buyer_name,buyer_id,seller_id," +
		"goods_id,goods_name,goods_price)values(?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(order.getOrder_time());
		params.add(order.getBuyer_name());
		params.add(order.getBuyer_id());
		params.add(order.getSeller_id());
		params.add(order.getGoods_id());
		params.add(order.getGoods_name());
		params.add(order.getGoods_price());
		boolean boo = this.operUpdate(sql, params);
		return boo;
	}
}
