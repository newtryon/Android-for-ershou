package com.niu.bean;
/**
 * comm_info
 * @author 王飞鱼
 *
 */
public class Comm {
	private int comm_id;
	private String comm_text;
	private String comm_time;
	private int goods_id;
	private int user_id;
	public Comm(int comm_id, String comm_text, String comm_time, int goods_id,
			int user_id) {
		super();
		this.comm_id = comm_id;
		this.comm_text = comm_text;
		this.comm_time = comm_time;
		this.goods_id = goods_id;
		this.user_id = user_id;
	}
	public Comm() {
		super();
	}
	public int getComm_id() {
		return comm_id;
	}
	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}
	public String getComm_text() {
		return comm_text;
	}
	public void setComm_text(String comm_text) {
		this.comm_text = comm_text;
	}
	public String getComm_time() {
		return comm_time;
	}
	public void setComm_time(String comm_time) {
		this.comm_time = comm_time;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Comm [comm_id=" + comm_id + ", comm_text=" + comm_text
				+ ", comm_time=" + comm_time + ", goods_id=" + goods_id
				+ ", user_id=" + user_id + "]";
	}
	
}
