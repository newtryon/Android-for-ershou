package com.niu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.niu.bean.User;
import com.niu.conn.ConnectionFactory;

public class UserDao extends ConnectionFactory{
	/**
	 * ע���û�
	 */
	public boolean insertUser(User user) {
		String sql = "insert into USER_INFO(user_name,user_pwd,user_phone,user_age,user_qq," +
				"user_num,grade_id,user_type,user_money,user_gender)values(?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUser_name());
		params.add(user.getUser_pwd());
		params.add(user.getUser_phone());
		params.add(user.getUser_age());
		params.add(user.getUser_qq());
		params.add(user.getUser_num());
		params.add(user.getGrade_id());
		params.add(user.getUser_type());
		params.add(user.getUser_money());
		params.add(user.getUser_gender());
		boolean boo = this.operUpdate(sql, params);
		return boo;
	}
	/**
	 * ��¼����
	 * @param user
	 * @return
	 */
	public Map<String, String> loginUser(User user) {
		List<User> uList = new ArrayList<User>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		String sql = "select * from USER_INFO where user_name = ? and user_pwd = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUser_name());
		params.add(user.getUser_pwd());
		try {
			uList = this.operQuery(sql, params, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (uList == null || uList.size() < 1) {
			System.out.println("uList.size()" + uList.size());//����
			boo = "false";
			map.put("result", boo);
			map.put("ObjList", uList);
			return map;
		}
		boo = "true";
		map.put("result", boo);
		map.put("ObjList", uList);
		return map;
	}
	/**
	 * 根据id查询用户
	 * @param user
	 * @return
	 */
	public Map<String, String> searchUserById(User user) {
		List<User> uList = new ArrayList<User>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		String sql = "select * from USER_INFO where user_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUser_id());
		try {
			uList = this.operQuery(sql, params, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (uList == null || uList.size() < 1) {
			System.out.println("uList.size()" + uList.size());//����
			boo = "false";
			map.put("result", boo);
			map.put("ObjList", uList);
			return map;
		}
		boo = "true";
		map.put("result", boo);
		map.put("ObjList", uList);
		return map;
	}
}
