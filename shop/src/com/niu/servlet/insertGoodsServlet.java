package com.niu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.niu.bean.Goods;
import com.niu.bean.User;
import com.niu.dao.GoodsDao;
import com.niu.dao.UserDao;
import com.niu.db.Goodsdb;
import com.niu.db.Userdb;

public class insertGoodsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public insertGoodsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		Gson gson = new Gson();
		out = response.getWriter();
		/*//byte[] b = request.getParameter(Gradedb.GRADE_COLLEGE).getBytes();
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]+" ,");
		}*/
		//班级表
		// "insert into GOODS_INFO(goods_name,goods_type,goods_price,goods_time,goods_sign,seller_id,goods_desc)values(?,?,?,?,?,?,?)";
		String goods_name = new String(request.getParameter(Goodsdb.GOODS_NAME).getBytes("iso-8859-1"),"UTF-8");
		String goods_type = new String(request.getParameter(Goodsdb.GOODS_TYPE).getBytes("iso-8859-1"),"UTF-8");
		String goods_price = new String(request.getParameter(Goodsdb.GOODS_PRICE).getBytes("iso-8859-1"),"UTF-8");
		String goods_time = new String(request.getParameter(Goodsdb.GOODS_TIME).getBytes("iso-8859-1"),"UTF-8");
		String seller_id = new String(request.getParameter(Goodsdb.SELLER_ID).getBytes("iso-8859-1"),"UTF-8");
		String goods_desc = new String(request.getParameter(Goodsdb.GOODS_DESC).getBytes("iso-8859-1"),"UTF-8");
		Goods goods = new Goods(Integer.parseInt(goods_type),goods_price, goods_time,0, Integer.parseInt(seller_id), goods_desc, null,goods_name);
		System.out.println(goods);
		Map<String, String> map = new HashMap<String, String>();
		GoodsDao gd = new GoodsDao();
		boolean boo = gd.insertGoods(goods);
		map = gd.queryGoodsByAll(goods);
		
		out.print(gson.toJson(map));
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
