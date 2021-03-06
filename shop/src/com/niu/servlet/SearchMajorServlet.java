package com.niu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.niu.bean.Grade;
import com.niu.dao.GradeDao;
import com.niu.db.Gradedb;

public class SearchMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


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
		Grade grade = new Grade();
		out = response.getWriter();
		/*//byte[] b = request.getParameter(Gradedb.GRADE_COLLEGE).getBytes();
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]+" ,");
		}*/
		String college = new String(request.getParameter(Gradedb.GRADE_COLLEGE).getBytes("iso-8859-1"),"UTF-8");
		//String college = request.getParameter(Gradedb.GRADE_COLLEGE);
		grade.setGrade_college(college);
		System.out.println("SearchMajorServlet" + grade);
		Map map = null;
		map= new GradeDao().searchMajor(grade);
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

		this.doGet(request, response);
	}


}
