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
import com.niu.bean.Grade;
import com.niu.bean.User;
import com.niu.dao.GradeDao;
import com.niu.dao.UserDao;
import com.niu.db.Gradedb;
import com.niu.db.Userdb;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
		String college = new String(request.getParameter(Gradedb.GRADE_COLLEGE).getBytes("iso-8859-1"),"UTF-8");
		String major = new String(request.getParameter(Gradedb.GRADE_MAJOR).getBytes("iso-8859-1"),"UTF-8");
		String grade_num = new String(request.getParameter(Gradedb.GRADE_NUM).getBytes("iso-8859-1"),"UTF-8");
		Grade grade = new Grade(grade_num,major,college);
		System.out.println(grade);
		Map<String, String> map = new HashMap<String, String>();
		int grade_id = new GradeDao().searchGrade(grade);
		//用户对象
		String name = new String(request.getParameter(Userdb.USER_NAME).getBytes("iso-8859-1"),"UTF-8");
		String age = new String(request.getParameter(Userdb.USER_AGE).getBytes("iso-8859-1"),"UTF-8");
		String phone = new String(request.getParameter(Userdb.USER_PHONE).getBytes("iso-8859-1"),"UTF-8");
		String qq = new String(request.getParameter(Userdb.USER_QQ).getBytes("iso-8859-1"),"UTF-8");
		String pwd = new String(request.getParameter(Userdb.USER_PWD).getBytes("iso-8859-1"),"UTF-8");
		String user_num = new String(request.getParameter(Userdb.USER_NUM).getBytes("iso-8859-1"),"UTF-8");
		int user_num_int = Integer.parseInt(user_num);
		String gender = new String(request.getParameter(Userdb.USER_GENDER).getBytes("iso-8859-1"),"UTF-8");
		User user = new User(name, age,pwd, phone, qq,gender,100,1, grade_id,user_num_int);
		System.out.println(user);
		boolean boo = new UserDao().insertUser(user);
		if(boo){
			map.put("result", "true");
		}else{
			map.put("result", "false");
		}
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
