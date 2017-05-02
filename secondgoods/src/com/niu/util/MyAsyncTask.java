package com.niu.util;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
//�Զ����첽�߳�
public class MyAsyncTask extends AsyncTask<String, String, String>{
		private Handler handler;
		private int what;//���� message.what
		public MyAsyncTask(Handler handler,int what) {
			this.handler = handler;
			this.what = what;
		}

		@Override
		protected String doInBackground(String... arg0) {
			//��ȡurl
			String url = arg0[0];
			//��ȡ�ַ�
			String result = Serlet.getHttpdoGet(url);
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(result == null){
				Log.i("BufferedReader","������ַ��ȡʧ��");
			}else{
				//new message �洢����
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putString("json", result);
				message.what = what;//�����Ϣ����
				Log.i("BufferedReader","what");
				
				message.setData(bundle);
				//����
				handler.sendMessage(message);
			}
		}
		
	}

