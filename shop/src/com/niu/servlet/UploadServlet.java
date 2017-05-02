package com.niu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.niu.bean.Goods;
import com.niu.dao.GoodsDao;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goods_id  = null;
		String goods_pic  = null;
		request.setCharacterEncoding("utf-8"); // ���ñ���
		// ��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��ȡ�ļ���Ҫ�ϴ�����·��
		String path = request.getRealPath("/upload");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		factory.setRepository(new File(path));
		// ���� ����Ĵ�С�����ϴ��ļ���������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024 * 1024 * 10);
		// ��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// �����ϴ�����ļ�
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// ��ȡ�?����������
				String name = item.getFieldName();
				// ����ȡ�� �?��Ϣ����ͨ�� �ı� ��Ϣ
				if (item.isFormField()) {
					// ��ȡ�û�����������ַ� ���������ͦ�ã���Ϊ�?�ύ��4���� �ַ����͵�
					String value = item.getString();
					request.setAttribute(name, value);
				} else {
					// ��ȡ·����
					String value = item.getName();
					// �������һ��б��
					int start = value.lastIndexOf("\\");
					// ��ȡ �ϴ��ļ��� �ַ����֣���1�� ȥ��б�ܣ�
					String filename = value.substring(start + 1);
					goods_id = filename;
					goods_pic = value;
					request.setAttribute(name, filename);
					// д��������
					item.write(new File(path, filename));// �����ṩ��
					System.out.println("上传成功" + filename);
					response.getWriter().print(filename);// ��·�����ظ�ͻ���
				}
			}

		} catch (Exception e) {
			System.out.println("上传失败");
			response.getWriter().print("�ϴ�ʧ�ܣ�" + e.getMessage());
		}
		Goods goods = new Goods();
		String[] idArray = goods_id.split("\\.");
		String id = idArray[0];
		goods.setGoods_id(Integer.parseInt(id));
		goods.setGoods_pic(goods_pic);
		boolean boo= new GoodsDao().updateGoodsImage(goods);
		System.out.println(boo);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		if(boo){
			map.put("result", "true");
		}else{
			map.put("result", "false");
		}
		out.print(gson.toJson(map));
		out.flush();
		out.close();
	}

}
