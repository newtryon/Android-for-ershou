package com.niu.util;

import com.niu.bean.Order;
import com.niu.db.Goodsdb;
import com.niu.db.Gradedb;
import com.niu.db.Orderdb;
import com.niu.db.Userdb;

/**
 * 网址拼接工厂
 * @author 王飞鱼
 *
 */
public class URLFactory {
	/**
	 * 注册
	 * @param phoneNum
	 * @param name
	 * @param psw
	 * @param famous
	 * @param friend
	 * @param birthday
	 * @return
	 */
	public static String register(String name, String age, String phone, String gender,String qq, String pwd, String user_num,
			String college,String major,String grade_num)
	{
		String registerUrl = "http://"+Constant.SERVER_IP+":8080/shop/RegisterServlet?"
							+ Userdb.USER_NAME+"="+name
							+ "&" + Userdb.USER_AGE+"="+age
							+ "&" + Userdb.USER_GENDER+"="+gender
							+ "&" + Userdb.USER_PHONE+"="+phone
							+ "&" + Userdb.USER_QQ+"="+qq
							+ "&" + Userdb.USER_PWD+"="+pwd
							+ "&" + Userdb.USER_NUM+"="+user_num
							+ "&" + Gradedb.GRADE_COLLEGE+"="+college
							+ "&" + Gradedb.GRADE_MAJOR+"="+major
							+ "&" + Gradedb.GRADE_NUM+"="+grade_num;
		return registerUrl;
	}
	/**
	 * 登录
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public static String login(String userName, String userPwd)
	{
		String loginUrl="http://"+Constant.SERVER_IP+":8080/shop/LoginServlet.action?"
							+ "user_name="+userName
							+ "&user_pwd="+userPwd;
		return loginUrl;
	}
	/**
	 * 根据学院名获取专业名集合
	 * @param collage
	 * @return
	 */
	public static String searchMajorByCollage(String collage){
		String searchMajorByCollageUrl="http://"+Constant.SERVER_IP+":8080/shop/SearchMajorServlet?"
				+ Gradedb.GRADE_COLLEGE + "=" +collage;
		return searchMajorByCollageUrl;
	}
	/**
	 * 根据专业查找班级
	 * @param major
	 * @return
	 */
	public static String searchNumByMajor(String major){
		String searchNumByMajorUrl="http://"+Constant.SERVER_IP+":8080/shop/SearchNumServlet?"
				+ Gradedb.GRADE_MAJOR + "=" +major;
		return searchNumByMajorUrl;
	}
	
	public static String insertGoodsInfo(String name,String price,String desc,String type,String seller_id,String time){
		String insertGoodsInfoUrl="http://"+Constant.SERVER_IP+":8080/shop/insertGoodsServlet?"
				+ Goodsdb.GOODS_NAME + "=" +name
				+ "&" + Goodsdb.GOODS_PRICE+"="+price
				+ "&" + Goodsdb.SELLER_ID+"="+seller_id
				+ "&" + Goodsdb.GOODS_TIME+"="+time
				+ "&" + Goodsdb.GOODS_DESC+"="+desc
				+ "&" + Goodsdb.GOODS_TYPE+"="+type;
		return insertGoodsInfoUrl;
	}
	/**
	 * 根据类型查询物品
	 * @param type
	 * @return
	 */
	public static String searchGoodsByType(String type){
		String searchGoodsByTypeUrl="http://"+Constant.SERVER_IP+":8080/shop/searchGoodsByTypeServlet?"
				+ Goodsdb.GOODS_TYPE + "=" +type;
		return searchGoodsByTypeUrl;
	}
	
	public static String searchUserById(String user_id){
		String searchUserByIdUrl="http://"+Constant.SERVER_IP+":8080/shop/searchUserByIdServlet?"
				+ Userdb.USER_ID + "=" +user_id;
		return searchUserByIdUrl;
	}
	/**
	 * 添加订单
	 * @param buyer_name
	 * @param time
	 * @param seller_id
	 * @param buyer_id
	 * @param goods_id
	 * @return
	 */
	public static String insertOrderInfo(String buyer_name,String time,String seller_id,String buyer_id,String goods_id,String goods_name,String goods_price){
		String insertOrderInfoUrl="http://"+Constant.SERVER_IP+":8080/shop/insertOrderInfoServlet?"
				+ Orderdb.BUYER_NAME + "=" +buyer_name
				+ "&" + Orderdb.ORDER_TIME + "="+time
				+ "&" + Orderdb.SELLER_ID + "="+seller_id
				+ "&" + Orderdb.BUYER_ID + "="+buyer_id
				+ "&" + Orderdb.GOODS_ID + "="+goods_id
				+ "&" + Orderdb.GOODS_NAME + "="+goods_name
				+ "&" + Orderdb.GOODS_PRICE + "="+goods_price
				;
		return insertOrderInfoUrl;
	}
	/**
	 * ������Ӻ�����Ϣ
	 * http://192.168.191.1:8080/qqServer/AddFriendsServlet.action?add_from_id=1&
	 * add_to_id=33&add_success=0&add_message=���&add_time=17-45&add_name=zunzun&add_group_name=�ҵĺ���
	 *//*
	public static String addFriend(int add_from_id,int add_to_id,int add_success,String add_message,String add_time,String add_name,String add_group_name){
		String addFriendUrl = "http://"+Constant.SERVER_IP+":8080/qqServer/AddFriendsServlet.action?"
				+ "&" + Friendsdb.FRIENDS_UID+"="+add_from_id
				+ "&" + Friendsdb.FRIENDS_TOUID+"="+add_to_id
				+ "&" + Friendsdb.FRIENDS_SUCCESS+"="+add_success
				+ "&" + Friendsdb.FRIENDS_MESSAGE+"="+add_message
				+ "&" + Friendsdb.FRIENDS_TIME+"="+add_time
				+ "&" + Friendsdb.FRIENDS_NAME+"="+add_name
				+ "&" + Friendsdb.FRIENDS_GROUP_NAME+"="+add_group_name
				;
		return addFriendUrl;
	}
	*//**
	 * �޸ĸ�����Ϣ
	 * @param name
	 * @param age
	 * @param sex
	 * @param address
	 * @return
	 *//*
	public static String updateInfo(String name, String age, String sex, String address,String id)
	{
		String updateInfoUrl = "http://"+Constant.SERVER_IP+":8080/qqServer/UpdateInfoServlet.action?"
							+ "&" + Userdb.USER_NICKNAME+"="+name
							+ "&" + Userdb.USER_AGE+"="+age
							+ "&" + Userdb.USER_SEX+"="+sex
							+ "&" + Userdb.USER_ADDRESS+"="+address
							+ "&" + Userdb.USER_ID+"="+id
							;
		return updateInfoUrl;
	}
	*//**
	 * ��ȡ��������
	 * @param id
	 * @return
	 *//*
	public static String quesInfo(String id)
	{
		String quesInfoUrl = "http://"+Constant.SERVER_IP+":8080/qqServer/QuesInfoServlet.action?"
							+ "&" + Userdb.USER_ID+"="+id
							;
		return quesInfoUrl;
	}
	*//**
	 * �޸�����
	 * @param id
	 * @param psw
	 * @return
	 *//*
	public static String pswInfo(String id, String psw)
	{
		String pswInfoUrl = "http://"+Constant.SERVER_IP+":8080/qqServer/UpdatePasswordServlet.action?"
							+ "&" + Userdb.USER_ID+"="+id
							+ "&" + Userdb.USER_PASSWORD+"="+psw
							;
		return pswInfoUrl;
	}
	*//**
	 * ��ѯ�������
	 * @param id
	 * @return
	 *//*
	public static String getFriend(String id){
		String pswInfoUrl = "http://"+Constant.SERVER_IP+":8080/qqServer/GetFriendsServlet.action?"
				+ "&" + Friendsdb.FRIENDS_UID+"="+id
				;
				return pswInfoUrl;
	}
	*//**
	 * �½���Ϣ
	 * @param to_id
	 * @param from_id
	 * @param content
	 * @return
	 *//*
	public static String sendMessage(String to_id,String from_id,String content,String time){
		String sendUrl = "http://"+Constant.SERVER_IP+":8080/qqServer/AddMessageServlet.action?"
				+ "&" + Messagedb.MESSAGE_RECIPIENTID+"="+to_id
				+ "&" + Messagedb.MESSAGE_SENDERID+"="+from_id
				+ "&" + Messagedb.MESSAGE_CONTENT+"="+content
				+ "&" + Messagedb.MESSAGE_TIMES+"="+time
				;
				return sendUrl;
	}*/
}
