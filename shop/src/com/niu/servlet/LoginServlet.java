package com.niu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.niu.bean.User;
import com.niu.dao.UserDao;
import com.niu.db.Gradedb;
import com.niu.db.Userdb;


public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(12312312);
		// ���տͻ��˴�4�Ĳ���
		// ��¼����������һ��user����
		// ��user����ת����json��ʽ
		// DAOFactory.getUserDAO().MovieLogin(userName, userPwd);
		// ��json���󷵻ظ�ͻ��� 
		//����ֵ���룻����servlet���롾���ֻ�˲���������룬��ɾ����}�д��롿
		resp.setContentType("text/html; charset=UTF-8"); 
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		Gson gson = new Gson();
		User user = new User();
		//��ȡ����l�ӵ������
		out = resp.getWriter();
		//���ܿͻ��˵�����
		user.setUser_name(new String(req.getParameter(Userdb.USER_NAME).getBytes("iso-8859-1"),"UTF-8"));
		user.setUser_pwd(req.getParameter(Userdb.USER_PWD));
		System.out.println(user);
		//������ݿⷽ�����в�����ܷ���ֵ
		Map map = null;
		map= new UserDao().loginUser(user);
		out.print(gson.toJson(map));
		out.flush();
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
