package com.niu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.niu.bean.Grade;
import com.niu.bean.User;
import com.niu.conn.ConnectionFactory;

public class GradeDao extends ConnectionFactory{
	/**
	 * 根据学院查专业
	 * @param grade
	 * @return
	 */
	public Map<String, String> searchMajor(Grade grade) {
		List<Grade> uList = new ArrayList<Grade>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		String sql = "select * from GRADE_INFO where grade_college = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(grade.getGrade_college());
		try {
			uList = this.operQuery(sql, params, Grade.class);
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
		System.out.println("searchMajor" + true);
		return map;
	}
	/**
	 * 根据专业查班级
	 * @param grade
	 * @return
	 */
	public Map<String, String> searchNum(Grade grade) {
		List<Grade> uList = new ArrayList<Grade>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		String sql = "select * from GRADE_INFO where grade_major = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(grade.getGrade_major());
		try {
			uList = this.operQuery(sql, params, Grade.class);
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
		return map;
	}
	/**
	 * 根据三项内容查询Grade
	 * @param grade
	 * @return
	 */
	public int searchGrade(Grade grade) {
		List<Grade> uList = new ArrayList<Grade>();
		String boo = "false";
		Map map = new HashMap <String, String>();
		String sql = "select * from GRADE_INFO where grade_college = ? and grade_major = ? and grade_num = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(grade.getGrade_college());
		params.add(grade.getGrade_major());
		params.add(grade.getGrade_num());
		try {
			uList = this.operQuery(sql, params, Grade.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*if (uList == null || uList.size() < 1) {
			System.out.println("uList.size()" + uList.size());
			boo = "false";
			map.put("result", boo);
			map.put("ObjList", uList);
			return map;
		}
		boo = "true";
		map.put("result", boo);
		map.put("ObjList", uList);*/
		if (uList == null || uList.size() < 1) {
			return -1;
		}
		return uList.get(0).getGrade_id();
	}
}
