package com.niu.bean;

import java.io.Serializable;

/**
 * goods_info
 * @author 王飞鱼
 *
 */
public class Goods implements Serializable{
	private int goods_id;
	private int goods_type;//是那种类型的商品
	private String goods_price;
	private String goods_time;
	private int goods_sign;//已售标记 1代表未售，2代表已售
	private int seller_id;
	private String goods_desc;//商品描述
	private String goods_pic;//商品描述
	private String goods_name;//商品名称
	
	public Goods(int goods_type, String goods_price, String goods_time,
			int goods_sign, int seller_id, String goods_desc, String goods_pic,
			String goods_name) {
		super();
		this.goods_type = goods_type;
		this.goods_price = goods_price;
		this.goods_time = goods_time;
		this.goods_sign = goods_sign;
		this.seller_id = seller_id;
		this.goods_desc = goods_desc;
		this.goods_pic = goods_pic;
		this.goods_name = goods_name;
	}
	public Goods(int goods_id, int goods_type, String goods_price,
			String goods_time, int goods_sign, int seller_id,
			String goods_desc, String goods_pic, String goods_name) {
		super();
		this.goods_id = goods_id;
		this.goods_type = goods_type;
		this.goods_price = goods_price;
		this.goods_time = goods_time;
		this.goods_sign = goods_sign;
		this.seller_id = seller_id;
		this.goods_desc = goods_desc;
		this.goods_pic = goods_pic;
		this.goods_name = goods_name;
	}
	public Goods() {
		super();
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_time() {
		return goods_time;
	}
	public void setGoods_time(String goods_time) {
		this.goods_time = goods_time;
	}
	public int getGoods_sign() {
		return goods_sign;
	}
	public void setGoods_sign(int goods_sign) {
		this.goods_sign = goods_sign;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getGoods_desc() {
		return goods_desc;
	}
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	public String getGoods_pic() {
		return goods_pic;
	}
	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_type=" + goods_type
				+ ", goods_price=" + goods_price + ", goods_time=" + goods_time
				+ ", goods_sign=" + goods_sign + ", seller_id=" + seller_id
				+ ", goods_desc=" + goods_desc + ", goods_pic=" + goods_pic
				+ ", goods_name=" + goods_name + "]";
	}
	
}
