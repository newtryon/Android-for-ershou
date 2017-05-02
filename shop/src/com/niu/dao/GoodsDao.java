package com.niu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.niu.bean.Goods;
import com.niu.bean.Grade;
import com.niu.bean.User;
import com.niu.conn.ConnectionFactory;


public class GoodsDao extends ConnectionFactory{
	/**
	 * 根据id更新商品图片
	 * @param goods
	 * @return
	 */
	public boolean updateGoodsImage(Goods goods) {
		String sql = "update GOODS_INFO set goods_pic = ? where goods_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(goods.getGoods_pic());
		params.add(goods.getGoods_id());
		boolean boo = this.operUpdate(sql, params);
		return boo;
	}
	
	public boolean insertGoods(Goods goods) {
		String sql = "insert into GOODS_INFO(goods_name,goods_type,goods_price,goods_time," +
		"goods_sign,seller_id,goods_desc)values(?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(goods.getGoods_name());
		params.add(goods.getGoods_type());
		params.add(goods.getGoods_price());
		params.add(goods.getGoods_time());
		params.add(goods.getGoods_sign());
		params.add(goods.getSeller_id());
		params.add(goods.getGoods_desc());
		boolean boo = this.operUpdate(sql, params);
		return boo;
	}
	
	public Map<String,String > queryGoodsByAll(Goods goods) {
		List<Goods> gList = new ArrayList<Goods>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		
		String sql = "select * from GOODS_INFO where goods_name = ? and goods_type = ? and goods_price = ? and goods_time= ? and " +
		"goods_sign = ? and seller_id = ? and goods_desc = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(goods.getGoods_name());
		params.add(goods.getGoods_type());
		params.add(goods.getGoods_price());
		params.add(goods.getGoods_time());
		params.add(goods.getGoods_sign());
		params.add(goods.getSeller_id());
		params.add(goods.getGoods_desc());
		try {
			gList = this.operQuery(sql, params, Goods.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (gList == null || gList.size() < 1) {
			System.out.println("uList.size()" + gList.size());//����
			boo = "false";
			map.put("result", boo);
			map.put("ObjList", gList);
			return map;
		}
		boo = "true";
		map.put("result", boo);
		map.put("ObjList", gList);
		return map;
	}
	/**
	 * 根据商品类型查询所有
	 * @param goods
	 * @return
	 */
	public Map<String, String> searchGoodsByType(Goods goods) {
		List<Goods> uList = new ArrayList<Goods>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		String sql = "select * from GOODS_INFO where goods_type = ? and goods_sign = 0";
		List<Object> params = new ArrayList<Object>();
		params.add(goods.getGoods_type());
		try {
			uList = this.operQuery(sql, params, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (uList == null || uList.size() < 1) {
			System.out.println("uList.size()" + uList.size());
			boo = "false";
			map.put("result", boo);
			map.put("ObjList", uList);
			return map;
		}
		boo = "true";
		map.put("result", boo);
		map.put("ObjList", uList);
		System.out.println("searchGoodsByType" + true);
		return map;
	}
}
