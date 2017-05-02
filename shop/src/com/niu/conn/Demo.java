package com.niu.conn;

import java.sql.Connection;

/**
 * 测试数据库连接
 * @author 王飞鱼
 *
 */
public class Demo {
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConn();
		if(conn==null){
			System.out.println("--数据库连接失败--");
		}else{
			System.out.println("--数据库连接成功--");
		}
	}
}
