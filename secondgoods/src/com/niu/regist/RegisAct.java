package com.niu.regist;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.niu.bean.Grade;
import com.niu.bean.User;
import com.niu.db.Gradedb;
import com.niu.login.LoginAct;
import com.niu.secondgoods.R;
import com.niu.util.Constant;
import com.niu.util.JasonIterator;
import com.niu.util.MyAsyncTask;
import com.niu.util.URLFactory;
/**
 * 注册
 * @author 王飞鱼
 *
 */
public class RegisAct extends Activity implements OnClickListener{
	int collegeSpinnerSelect = 0;//学院默认选择1
	int majorSpinnerSelect = 0;//专业
	int numSpinnerSelect = 0;//班级
	ArrayAdapter<String> colArrayAdapter;
	ArrayAdapter<String> majorArrayAdapter;
	ArrayAdapter<String> numArrayAdapter;
	List<String> colArray = new ArrayList<String>();
	List<String> majorArray = new ArrayList<String>();
	List<String> numArray = new ArrayList<String>();
	private TextView tv_name;
	private EditText et_name;
	private TextView tv_age;
	private EditText et_age;
	private TextView tv_phone;
	private EditText et_phone;
	private TextView tv_qq;
	private EditText et_qq;
	private TextView tv_pwd;
	private EditText et_pwd;
	private TextView tv_pwd_again;
	private EditText et_pwd_again;
	private TextView tv_num;
	private EditText et_num;
	private RadioGroup rg_gender;//性别单选按钮
	private Spinner sp_collage;
	private Spinner sp_major;
	private Spinner sp_num;
	private Button btn_insert;
	
	String gender = "男";
	private List<Map<String, String>> list;
	private MyAsyncTask myAsyncTask;
	private MyAsyncTask myAsyncTaskMajor;
	private MyAsyncTask myAsyncTaskNum;
	private User user = new User();
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == Constant.WHAT_SEARCH_MAJOR) {
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.longJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						List<Grade> gradeList = new ArrayList<Grade>();//存储所有查到的班级对象
						for (int i = 1; i < list.size(); i++) {
							// 生成当前Grade对象
							String major = null;
							Grade grade = new Grade(Integer.parseInt(list.get(i).get(Gradedb.GRADE_ID)),
									list.get(i).get(Gradedb.GRADE_NUM),
									list.get(i).get(Gradedb.GRADE_MAJOR),
									list.get(i).get(Gradedb.GRADE_COLLEGE));
							gradeList.add(grade);
							/*Toast.makeText(getApplicationContext(), "登录成功" + grade,
									Toast.LENGTH_SHORT).show();*/
						}
						//获取此学院所有专业字符串数组
						majorArray.clear();
						for (int j = 0; j < gradeList.size(); j++) {//去重
							if(!majorArray.contains(gradeList.get(j).getGrade_major())){
								majorArray.add(gradeList.get(j).getGrade_major());
							}
						}
						Toast.makeText(getApplicationContext(), majorArray.toString(),
								Toast.LENGTH_SHORT).show();
						majorArrayAdapter.notifyDataSetChanged();
						// 关闭线程
						myAsyncTaskMajor.cancel(true);
						myAsyncTaskMajor = null;
						
					} else {
						Toast.makeText(getApplicationContext(), "查询专业失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}else if(msg.what == Constant.WHAT_SEARCH_NUM){
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.longJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						List<Grade> gradeList = new ArrayList<Grade>();//存储所有查到的班级对象
						for (int i = 1; i < list.size(); i++) {
							// 生成当前Grade对象
							Grade grade = new Grade(Integer.parseInt(list.get(i).get(Gradedb.GRADE_ID)),
									list.get(i).get(Gradedb.GRADE_NUM),
									list.get(i).get(Gradedb.GRADE_MAJOR),
									list.get(i).get(Gradedb.GRADE_COLLEGE));
							gradeList.add(grade);
							/*Toast.makeText(getApplicationContext(), "登录成功" + grade,
									Toast.LENGTH_SHORT).show();*/
						}
						//获取此学院所有班级字符串数组
						numArray.clear();
						for (int j = 0; j < gradeList.size(); j++) {//去重
							if(!numArray.contains(gradeList.get(j).getGrade_num())){
								numArray.add(gradeList.get(j).getGrade_num());
							}
						}
						Toast.makeText(getApplicationContext(), numArray.toString(),
								Toast.LENGTH_SHORT).show();
						numArrayAdapter.notifyDataSetChanged();
						// 关闭线程
						myAsyncTaskNum.cancel(true);
						myAsyncTaskNum = null;
						
					} else {
						Toast.makeText(getApplicationContext(), "查询班级失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}else if(msg.what == Constant.WHAT_REGISTER){
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.simpleJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
						// 关闭线程
						myAsyncTask.cancel(true);
						myAsyncTask = null;
						//跳转登录界面
						Intent intent = new Intent(RegisAct.this, LoginAct.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(), "注册失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}

		};
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		initView();//关联控件
		MySetAdapter1();
		btn_insert.setOnClickListener(this);
		//获取性别
		rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.insert_rb_man:
					gender = "男";
					break;
				case R.id.insert_rb_woman:
					gender = "女";
					break;
				}
			}
		});
		//获取选中学院，刷新专业
		sp_collage.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				collegeSpinnerSelect = position;
				String searchMajorByCollage = URLFactory.searchMajorByCollage(colArray.get(position));
				Log.i("tag", searchMajorByCollage);
				Toast.makeText(getApplicationContext(), searchMajorByCollage, Toast.LENGTH_SHORT).show();
				myAsyncTaskMajor = new MyAsyncTask(handler, Constant.WHAT_SEARCH_MAJOR);
				myAsyncTaskMajor.execute(searchMajorByCollage);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		//获取选中专业，刷新班级
		sp_major.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				majorSpinnerSelect = position;
				String searchNumByMajor = URLFactory.searchNumByMajor(majorArray.get(position));
				Log.i("tag", searchNumByMajor);
				Toast.makeText(getApplicationContext(), searchNumByMajor, Toast.LENGTH_SHORT).show();
				myAsyncTaskNum = new MyAsyncTask(handler, Constant.WHAT_SEARCH_NUM);
				myAsyncTaskNum.execute(searchNumByMajor);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		//获取选中班级
		sp_num.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				numSpinnerSelect = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		/*majorArray.add("1111");
		majorArrayAdapter.notifyDataSetChanged();*/
	}
	
	public void initView(){
		tv_name = (TextView) findViewById(R.id.insert_tv_name);
		et_name = (EditText) findViewById(R.id.insert_et_name);
		tv_age = (TextView) findViewById(R.id.insert_et_age);
		et_age = (EditText) findViewById(R.id.insert_et_age);
		tv_phone = (TextView) findViewById(R.id.insert_tv_phone);
		et_phone = (EditText) findViewById(R.id.insert_et_phone);
		tv_qq = (TextView) findViewById(R.id.insert_tv_qq);
		et_qq = (EditText) findViewById(R.id.insert_et_qq);
		tv_pwd = (TextView) findViewById(R.id.insert_tv_pwd);
		et_pwd = (EditText) findViewById(R.id.insert_et_pwd);
		tv_pwd_again = (TextView) findViewById(R.id.insert_tv_pwd_again);
		et_pwd_again = (EditText) findViewById(R.id.insert_et_pwd_again);
		tv_num = (TextView) findViewById(R.id.insert_tv_num);
		et_num = (EditText) findViewById(R.id.insert_et_num);
		rg_gender = (RadioGroup) findViewById(R.id.insert_rg_gender);
		sp_collage = (Spinner) findViewById(R.id.insert_sp_collage);
		sp_major = (Spinner) findViewById(R.id.insert_sp_major);
		sp_num = (Spinner) findViewById(R.id.insert_sp_num);
		btn_insert = (Button) findViewById(R.id.insert_btn_insert);
	}

	@Override
	public void onClick(View v) {
		String name = et_name.getText().toString();
		String age = et_age.getText().toString();
		String phone = et_phone.getText().toString();
		String qq = et_qq.getText().toString();
		String pwd = et_pwd.getText().toString();
		String pwd_again = et_pwd_again.getText().toString();
		String num = et_num.getText().toString();
		//Toast.makeText(this, 1+ name + 1, Toast.LENGTH_SHORT).show();
		if(name.equals("")  || age.equals("")  || phone.equals("")  || qq.equals("")  || pwd.equals("")  || pwd_again.equals("")  || num.equals("")){
			Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
		}else if(!pwd.equals(pwd_again)){
			Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
		}else{
			regist();
		}
		
	}
	//spinner设置adapter
	public void MySetAdapter1(){
		colArray.add("软件学院");
		colArray.add("体育学院");
		colArray.add("音乐学院");
		majorArray.add("软件工程1");
		majorArray.add("网络工程1");
		majorArray.add("电子信息1");
		numArray.add("141");
		numArray.add("42");
		numArray.add("143");
		/*String[] colArray = {"软件学院","体育学院","音乐学院"};
		String[] majorArray = {"软件工程","网络工程","电子信息"};
		String[] gradeArray = {"131","132","133"};*/
		colArrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, R.id.spi_tv, colArray);
//		colArrayAdapter.notifyDataSetChanged();
		sp_collage.setAdapter(colArrayAdapter);
		
		majorArrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, R.id.spi_tv, majorArray);
		sp_major.setAdapter(majorArrayAdapter);
		/*majorArray.add("1111");
		majorArrayAdapter.notifyDataSetChanged();*/
		numArrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, R.id.spi_tv, numArray);
		sp_num.setAdapter(numArrayAdapter);
	}
	/*public void MySetAdapter2(){
		majorArrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, R.id.spi_tv, majorArray);
		sp_major.setAdapter(majorArrayAdapter);
	}*/
	public void regist(){
		String name = et_name.getText().toString();
		String age = et_age.getText().toString();
		String phone = et_phone.getText().toString();
		String qq = et_qq.getText().toString();
		String pwd = et_pwd.getText().toString();
		String pwd_again = et_pwd_again.getText().toString();
		String user_num = et_num.getText().toString();
		String college = colArray.get(collegeSpinnerSelect);
		String major = majorArray.get(majorSpinnerSelect);
		String grade_num = numArray.get(numSpinnerSelect);
		
		
		
		String registUrl = URLFactory.register(name, age, phone, gender, qq, pwd_again, user_num, college, major, grade_num);
		Toast.makeText(getApplicationContext(), registUrl, Toast.LENGTH_SHORT).show();
		myAsyncTask = new MyAsyncTask(handler, Constant.WHAT_REGISTER);
		myAsyncTask.execute(registUrl);
	}
}
