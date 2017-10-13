package ${basepackage}.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class WebClientUtil {
	
	/**
	 * 通过post获取网络资源
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Map<String,String> params)  {
		String parameterData=null;		
		
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;        
        try {
        	if(params!=null){
        		parameterData="";
    			for(String key:params.keySet()){			 
    				parameterData+=(parameterData.equals("")?"":"&")+ key+"="+URLEncoder.encode( params.get(key), "UTF8");
    			}
    		} 
            URL localURL = new URL(url);        
        	URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;        
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterData==null?0:parameterData.length()));        
          
        	if(parameterData!=null){
	            outputStream = httpURLConnection.getOutputStream();
	            outputStreamWriter = new OutputStreamWriter(outputStream);	            
	            outputStreamWriter.write(parameterData.toString());
	            outputStreamWriter.flush();
        	}            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }            
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        }catch(Exception e){
        	e.printStackTrace();
        } finally {            
        	
            if (outputStreamWriter != null) {
                try {
					outputStreamWriter.close();
				} catch (IOException e) {
				 
					e.printStackTrace();
				}
            }            
            if (outputStream != null) {
                try {
					outputStream.close();
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
            }            
            if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
            }            
            if (inputStreamReader != null) {
                try {
					inputStreamReader.close();
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
            }            
            if (inputStream != null) {
                try {
					inputStream.close();
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
            }            
        }

        return resultBuffer.toString();
    }

}
