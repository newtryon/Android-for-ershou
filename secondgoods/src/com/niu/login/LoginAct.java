package com.niu.login;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.niu.bean.User;
import com.niu.regist.RegisAct;
import com.niu.secondgoods.R;
import com.niu.util.Constant;
import com.niu.util.JasonIterator;
import com.niu.util.MyAsyncTask;
import com.niu.util.URLFactory;

public class LoginAct extends Activity {

	private User user = new User();

	private EditText et_name;
	private EditText et_pwd;
	private ImageView iv_namecls;
	private ImageView iv_pwdcls;
	private CheckBox cb;
	private Button btn_login;
	private Button btn_regit;
	private Button btn_scan;
	private TextView tv_findback;
	private List<Map<String, String>> list;
	private MyAsyncTask myAsyncTask;
	private SharedPreferences sp;// 数据存储参数，简单的
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == Constant.WHAT_LOGIN) {
				Bundle data = msg.getData();
				String reslut = data.getString("json");// 从message取值
				if (!reslut.equals("null")) {
					Log.i("TAG", reslut);
					list = JasonIterator.informationJason(reslut);// json解析
					Map<String, String> map1 = list.get(0);
					String result = map1.get("result");
					if (result.equals("true")) {
						// 生成当前user对象
						user.setUser_name(map1.get("user_name"));
						user.setUser_pwd(map1.get("user_pwd"));
						// 关闭线程
						myAsyncTask.cancel(true);
						myAsyncTask = null;

						Toast.makeText(getApplicationContext(), "登录成功" + user,
								Toast.LENGTH_SHORT).show();
						// Intent intent = new
						// Intent(LoginAct.this,HomeActivity.class);
						// intent.putExtra("user", user);
						// startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(), "登录失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "网络连接超时",
							Toast.LENGTH_SHORT).show();
				}
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		// 若点击了保存密码，下次可以直接显示出来。
		sp = getSharedPreferences("userinfo", 0);
		String name = sp.getString("name", "");
		String pwd = sp.getString("pwd", "");
		boolean result = sp.getBoolean("ischecked", false);
		if (result) {
			cb.setChecked(true);
		}
		et_name.setText(name);
		et_pwd.setText(pwd);
		// 清空输入框
		iv_namecls.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				et_name.setText("");
			}
		});
		iv_pwdcls.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				et_pwd.setText("");
			}
		});
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// 获取输入账号密码
				String name_input = et_name.getText() + "";
				String pwd_input = et_pwd.getText() + "";
				if (name_input.equals("")) {
					Toast.makeText(getApplicationContext(), "账号不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (pwd_input.equals("")) {
					Toast.makeText(getApplicationContext(), "密码不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					login();
					Log.i("LoginAct", "判断账号或密码");
				}

			}
		});
		btn_regit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(LoginAct.this,RegisAct.class);
			}
		});
		/*
		 * //注册账号 tv_newuser.setOnClickListener(new OnClickListener() { public
		 * void onClick(View arg0) { Intent intent = new
		 * Intent(LoginAct.this,NewUserPhone.class); startActivity(intent); }
		 * }); //找回密码 tv_findback.setOnClickListener(new OnClickListener() {
		 * public void onClick(View arg0) { Intent intent = new
		 * Intent(LoginAct.this,FindbackNum.class); startActivity(intent); } });
		 */

	}

	// 关联控件
	public void initView() {
		et_name = (EditText) findViewById(R.id.log_et_name);
		et_pwd = (EditText) findViewById(R.id.log_et_pwd);
		iv_namecls = (ImageView) findViewById(R.id.log_iv_nameclr);
		iv_pwdcls = (ImageView) findViewById(R.id.log_iv_pwdclr);
		cb = (CheckBox) findViewById(R.id.log_cb);
		btn_login = (Button) findViewById(R.id.log_btn_login);
		tv_findback = (TextView) findViewById(R.id.log_tv_findback);
		btn_regit = (Button) findViewById(R.id.log_btn_regit);
		btn_scan = (Button) findViewById(R.id.log_btn_scan);
	}

	// 登录
	public void login() {
		String name = et_name.getText() + "";
		String pwd = et_pwd.getText() + "";
		String loginUrl = URLFactory.login(name, pwd);
		System.out.println(loginUrl);
		myAsyncTask = new MyAsyncTask(handler, Constant.WHAT_LOGIN);
		myAsyncTask.execute(loginUrl);

		if (cb.isChecked()) {// 是否记住密码的操作
			// SharedPreferences sp = getSharedPreferences("userinfo",
			// 1);//将来生成userinfo.xml文件,在data/data目录下
			Editor edit = sp.edit();
			edit.putString("name", name);// 设置的键值对形式
			edit.putString("pwd", pwd);
			edit.putBoolean("ischecked", true);
			edit.commit();
		}

	}

}
