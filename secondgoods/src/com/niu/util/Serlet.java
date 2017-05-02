package com.niu.util;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
//��������
public class Serlet {
	//public static String servletC(String strurl){
		/*URL url = null;
		try {
			// ����һ��URL����
			url = new URL(strurl);
			// ����HttpURLConnection����������л�ȡ��ҳ���:
			HttpURLConnection urlconn = (HttpURLConnection) url
					.openConnection();
			// �������ӳ�ʱʱ��:
			urlconn.setConnectTimeout(6 * 1000);
			// ����Ӧ������ж�:
			if (urlconn.getResponseCode() != 200) {
				Log.i("TAG", "���糬ʱ");
			}
			// �õ����緵�ص�������:
			InputStreamReader in = new InputStreamReader(
					urlconn.getInputStream());
			StringBuffer result = new StringBuffer();
			BufferedReader bufferReader = new BufferedReader(in);
			String len = null;
			while ((len = bufferReader.readLine()) != null) {
				result.append(len);
			}
			// �ر���������������ӣ��ͷ���Դ
			in.close();
			urlconn.disconnect();
			Log.i("TAG", result.toString());
			return result.toString();
		} catch (Exception e) {
			Log.i("TAG", "�����쳣");
			e.printStackTrace();
		}
		return null;
	}*/
	/*	try {
			//����htttpget���󣬲���Ϊurl
			Log.i("BufferedReader","11111111111");
			HttpGet get = new HttpGet(strurl);
			//����httpclient����DefaultHttpClient��
			HttpClient client = new DefaultHttpClient();
			Log.i("BufferedReader","222222222222");
			//ͨ��httpClient�����execute����������Ϊhttpget���󣩻�ȡHttpResponse����
			HttpResponse httpResponse = client.execute(get);
			
			Log.i("BufferedReader","33333333333333");
			//ͨ��httpResponse.getStatusLine().getStatusCode() == 200  �ж��Ƿ����ӳɹ�
			Log.i("BufferedReader","2222");
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				//ͨ��httpResponse�����ȡHttpEntity
				InputStream is = httpResponse.getEntity().getContent();
				StringBuffer sb = new StringBuffer();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String len = null;
				while((len = br.readLine()) != null){
					sb.append(len);
				}
				Log.i("BufferedReader","111");
				Log.i("BufferedReader",sb.toString() );
				return sb.toString();
			}
		} catch (ClientProtocolException e) {
			Log.i("BufferedReader","ClientProtocolException");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			Log.i("BufferedReader","IllegalStateException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.i("BufferedReader","IOException");
			e.printStackTrace();
		}
		return null;
		
	}*/
	public static String getHttpdoGet(String strurl) throws RuntimeException {
		URL url = null;
		try {
			Log.i("TAG", "---------getHttpdoGet");
			// ����һ��URL����
			url = new URL(strurl);
			// ����HttpURLConnection����������л�ȡ��ҳ���:
			HttpURLConnection urlconn = (HttpURLConnection) url
					.openConnection();
			// �������ӳ�ʱʱ��:
			urlconn.setConnectTimeout(6 * 1000);
			// ����Ӧ������ж�:
			Log.i("TAG", "---------getResponseCode" + urlconn.getResponseCode());
			if (urlconn.getResponseCode() != 200) {
				throw new RuntimeException("����urlʧ��"+urlconn.getResponseCode());
			}
			Log.i("TAG", "---------getHttpdoGet" + urlconn.getResponseCode());
			// �õ����緵�ص�������:
			InputStreamReader in = new InputStreamReader(
					urlconn.getInputStream());
			BufferedReader bufferReader = new BufferedReader(in);
			String result = "";
			String readLine = null;
			while ((readLine = bufferReader.readLine()) != null) {
				result += readLine;
			}
			Log.i("TAG", "result" + result);
			// �ر���������������ӣ��ͷ���Դ
			in.close();
			urlconn.disconnect();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /*
     * Function  :   ����Post���󵽷�����
     * Param     :   params���������ݣ�encode�����ʽ
     */
    public static String getHttpdoPost(String strUrlPath,Map<String, String> params, String encode) {
        
        byte[] data = getRequestData(params, encode).toString().getBytes();//���������
        try { 
            URL url = new URL(strUrlPath);  
             
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     		//�������ӳ�ʱʱ��
            httpURLConnection.setDoInput(true);                  //�����������Ա�ӷ�������ȡ���
            httpURLConnection.setDoOutput(true);                 //����������Ա���������ύ���
            httpURLConnection.setRequestMethod("POST");     	 //������Post��ʽ�ύ���
            httpURLConnection.setUseCaches(false);               //ʹ��Post��ʽ����ʹ�û���
            //������������������ı�����
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //����������ĳ���
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //�����������������д�����
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);
            
            int response = httpURLConnection.getResponseCode();            //��÷���������Ӧ��
            if(response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //�������������Ӧ���
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "err: " + e.getMessage().toString();
        }
        return "-1";
    }
    
    /*
     * Function  :   ��װ��������Ϣ
     * Param     :   params���������ݣ�encode�����ʽ
     */
   public static StringBuffer getRequestData(Map<String, String> params, String encode) {
      StringBuffer stringBuffer = new StringBuffer();        //�洢��װ�õ���������Ϣ
      try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                      .append("=")
                      .append(URLEncoder.encode(entry.getValue(), encode))
                      .append("&");
            }
           stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //ɾ������һ��"&"
        } catch (Exception e) {
           e.printStackTrace();
       }
       return stringBuffer;
    }
    
   /*
    * Function  :   �������������Ӧ���������ת�����ַ�
    * Param     :   inputStream����������Ӧ������
    */
   public static String dealResponseResult(InputStream inputStream) {
      String resultData = null;      //�洢������
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      byte[] data = new byte[1024];
      int len = 0;
       try {
          while((len = inputStream.read(data)) != -1) {
             byteArrayOutputStream.write(data, 0, len);
          }
     } catch (IOException e) {
         e.printStackTrace();
        }
       resultData = new String(byteArrayOutputStream.toByteArray());    
       return resultData;
   }    	
}
