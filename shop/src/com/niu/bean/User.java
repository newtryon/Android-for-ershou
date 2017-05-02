package com.niu.bean;
/**
 * user_info
 * @author 王飞鱼
 *
 */
public class User {
	private int user_id;
	private String user_name;
	private String user_age;
	private String user_pwd;
	private String user_phone;
	private String user_qq;
	private String user_gender;
	private int user_money;
	private int user_type;
	private int grade_id;
	private int user_num;
	//构造
	
	
	
	public User(String user_name, String user_age, String user_pwd,
			String user_phone, String user_qq, String user_gender,
			int user_money, int user_type, int grade_id, int user_num) {
		super();
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_pwd = user_pwd;
		this.user_phone = user_phone;
		this.user_qq = user_qq;
		this.user_gender = user_gender;
		this.user_money = user_money;
		this.user_type = user_type;
		this.grade_id = grade_id;
		this.user_num = user_num;
	}
	public User(int user_id, String user_name, String user_age,
			String user_pwd, String user_phone, String user_qq,
			String user_gender, int user_money, int user_type, int grade_id,
			int user_num) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_pwd = user_pwd;
		this.user_phone = user_phone;
		this.user_qq = user_qq;
		this.user_gender = user_gender;
		this.user_money = user_money;
		this.user_type = user_type;
		this.grade_id = grade_id;
		this.user_num = user_num;
	}
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}
	public User() {
		super();
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_qq() {
		return user_qq;
	}
	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public int getUser_money() {
		return user_money;
	}
	public void setUser_money(int user_money) {
		this.user_money = user_money;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_pwd=" + user_pwd + ", user_phone=" + user_phone
				+ ", user_qq=" + user_qq + ", user_gender=" + user_gender
				+ ", user_money=" + user_money + ", user_type=" + user_type
				+ ", grade_id=" + grade_id + ", user_num=" + user_num + "]";
	}
	
}
