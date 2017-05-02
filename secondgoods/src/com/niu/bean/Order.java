package com.niu.bean;
/**
 * order_info
 * @author 王飞鱼
 *
 */
public class Order {
	private int order_id;
	private String order_time;
	private String buyer_name;
	private int buyer_id;
	private int seller_id;
	private int goods_id;
	private String goods_name;
	private String goods_price;
	//构造
	public Order(int order_id, String order_time, String buyer_name,
			int buyer_id, int seller_id, int goods_id, String goods_name,
			String goods_price) {
		super();
		this.order_id = order_id;
		this.order_time = order_time;
		this.buyer_name = buyer_name;
		this.buyer_id = buyer_id;
		this.seller_id = seller_id;
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
	}
	public Order(String order_time, String buyer_name, int buyer_id,
			int seller_id, int goods_id, String goods_name, String goods_price) {
		super();
		this.order_time = order_time;
		this.buyer_name = buyer_name;
		this.buyer_id = buyer_id;
		this.seller_id = seller_id;
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_time=" + order_time
				+ ", buyer_name=" + buyer_name + ", buyer_id=" + buyer_id
				+ ", seller_id=" + seller_id + ", goods_id=" + goods_id
				+ ", goods_name=" + goods_name + ", goods_price=" + goods_price
				+ "]";
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	
	
	
}
