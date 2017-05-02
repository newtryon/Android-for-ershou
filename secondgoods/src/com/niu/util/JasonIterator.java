package com.niu.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
/**
 * 
 * @author ������
 *
 */
public class JasonIterator {
	/**
	 * 单个对象解析
	 * {"result":"true","ObjList":[{"user_id":1,"user_nickName":"海同","user_email":"2958365570@qq.com",
	 * "user_isVip":0,"user_webcam":0}]}
	 * @param result
	 * @return 
	 */
	public static List<Map<String,String>> informationJason(String result){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		try {
			Log.i("JasonDemo", "List<Map<String,String>>");
			Log.i("JasonDemo", result);
		 	//获取最外层对象
			JSONObject jsonObject = new JSONObject(result);
			Log.i("JasonDemo", "userResult");
			//获取sql语句执行成功或失败
			String success = jsonObject.getString("result");//�ж� �Ƿ�ִ�гɹ�
			Log.i("JasonDemo", "userJson");
			Map<String,String> map = new HashMap<String,String>();
			map.put("result", success);
			//获取数据集
			JSONArray array = jsonObject.getJSONArray("ObjList");
			//返回空
			if(success.equals("false")){
				list.add(map);
				return list;
			}
			if(array == null){
				list.add(map);
				return list;
			}
			//获取第一个对象
			JSONObject jsonObject2 = array.getJSONObject(0);
			//迭代获取键和值
			Iterator<String> keys = jsonObject2.keys();
			Log.i("JasonDemo", keys+"");
			while (keys.hasNext()) {
				String key = keys.next().toString();
				String value = jsonObject2.getString(key);
				map.put(key, value);//存入map
				Log.i("JasonDemo", key+"");
			}			
			Log.i("JasonDemo", list.toString());
			list.add(map);
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * {"result":"true"}
	 * @param result
	 * @return
	 */
	public static List<Map<String,String>> simpleJason(String result){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		try {
			Log.i("JasonDemo", "List<Map<String,String>>");
			Log.i("JasonDemo", result);
			//��һ��
			JSONObject jsonObject = new JSONObject(result);
			Log.i("JasonDemo", "userResult");
			//��ȡresult
			String success = jsonObject.getString("result");//�ж� �Ƿ�ִ�гɹ�
			Log.i("JasonDemo", "userJson");
			Map<String,String> map = new HashMap<String,String>();
			map.put("result", success);
			
			list.add(map);
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//�����
	public static List<Map<String,String>> longJason(String result){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		try {
			Log.i("JasonDemo", "List<Map<String,String>>");
			Log.i("JasonDemo", result);
			//��һ��
			JSONObject jsonObject = new JSONObject(result);
			Log.i("JasonDemo", "userResult");
			//��ȡresult
			String success = jsonObject.getString("result");//�ж� �Ƿ�ִ�гɹ�
			Log.i("JasonDemo", "userJson");
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("result", success);
			list.add(map1);
			String userJson = jsonObject.getString("ObjList");
			Log.i("JasonDemo", "userJson");
			Log.i("JasonDemo", userJson);
			//��ȡ����
			JSONArray ja = new JSONArray(userJson);
			int length = ja.length();
			Log.i("JasonDemo", length+"");
			for (int i = 0; i < length; i++) {//����
				Map<String,String> map = new HashMap<String,String>();
				JSONObject oj = ja.getJSONObject(i);
				Iterator<String> keys = oj.keys();
				Log.i("JasonDemo", keys+"");
				
				while (keys.hasNext()) {
					String key = keys.next().toString();
					String value = oj.get(key)+"";
					map.put(key, value);
					Log.i("JasonDemo", key+"");
				}
				list.add(map);
				Log.i("JasonDemo", list.toString());
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
