package com.niu.imageload;



import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.niu.secondgoods.R;

public class GoodsImageUpAct extends Activity {
	String goods_id;
	ProgressDialog dialog=null;
	String result="";
	
	Button btn=null;
	Button btn_down=null;
	TextView tv=null;
    ImageView iv=null;
    
    FinalBitmap fb=null;
	
    Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			dialog.dismiss();
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), "图片上传成功，界面跳转", Toast.LENGTH_SHORT).show();
				tv.setText("上传成功,图片路径："+Const.DOWNLOAD_URL+result);
//				fb.display(iv, Const.DOWNLOAD_URL+result);
				//界面跳转
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "图片上传失败", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_image_up);
		Intent i = getIntent();
		Bundle bundleExtra = i.getBundleExtra("goods");
		goods_id = bundleExtra.getString("goods_id");
		fb=FinalBitmap.create(this);
		iv=(ImageView)findViewById(R.id.iv);
		btn_down = (Button)findViewById(R.id.btn_down);
		dialog=new ProgressDialog(this);
		dialog.setTitle("请稍后...");
		
		tv=(TextView)findViewById(R.id.tv);
		btn=(Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
				openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(openAlbumIntent, 1);
			}
		});
		btn_down.setOnClickListener(new OnClickListener() {
		 
			public void onClick(View arg0) {
				fb.display(iv, Const.DOWNLOAD_URL+result);
				 /*String httpUrl = Const.DOWNLOAD_URL + "IMG_20170425_162650.jpg";
			    Bitmap bitmap = null;
			    HttpGet httpRequest = new HttpGet(httpUrl);
			    //取得HttpClient 对象
			    HttpClient httpclient = new DefaultHttpClient();
			    try {
			      //请求httpClient ，取得HttpRestponse
			      HttpResponse httpResponse = httpclient.execute(httpRequest);
			      if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			        //取得相关信息 取得HttpEntiy
			        HttpEntity httpEntity = httpResponse.getEntity();
			        InputStream is = httpEntity.getContent();
			        bitmap = BitmapFactory.decodeStream(is);
			        is.close();
			      }else{
			         Toast.makeText(getApplicationContext(), "连接失败!", Toast.LENGTH_SHORT).show();
			      }
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    iv.setImageBitmap(bitmap);*/
			}
		});
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}
		Uri uri = null;
		switch (requestCode) {
		case 1:// 相册
			if (data == null) {
				return;
			}
			uri = data.getData();
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(uri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			final String path = cursor.getString(column_index);// 图片在的路径
			dialog.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
						result = ServerUtils.formUpload(Const.UPLOAD_URL, path ,goods_id);
						Log.e("jj", "result:"+result);
						if(!TextUtils.isEmpty(result)){
							handler.sendEmptyMessage(1);
						}else{
							handler.sendEmptyMessage(2);
						}
				}
			}).start();
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


}
