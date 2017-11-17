package org.jeffrey.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jeffrey on 2017-11-13.
 * http请求工具类
 */
public abstract class HttpRequestUtil {

  /**
   * post请求
   * @param url
   * @param jsonParam
   * @return
   */
  public static JSONObject httpPost(String url, JSONObject jsonParam){
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    JSONObject jsonObject = new JSONObject();
    if(jsonParam!=null){
      Iterator<String> sIterator = jsonParam.keySet().iterator();
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      while(sIterator.hasNext()){
        String key = sIterator.next();
        String value = jsonParam.getString(key);
        nvps.add(new BasicNameValuePair(key,value));
      }
      try {
        post.setEntity(new UrlEncodedFormEntity(nvps));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    try {
      CloseableHttpResponse response = httpclient.execute(post);
      HttpEntity entity = response.getEntity();
      jsonObject.put("status",response.getStatusLine());
      EntityUtils.consume(entity);
      jsonObject.put("responseText",entity.getContent());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonObject;
  }

  /**
   * get请求
   * @param url
   * @return
   */
  public static JSONObject httpGet(String url){
    CloseableHttpClient httpclient = HttpClients.createDefault();
    JSONObject jsonObject = new JSONObject();
    HttpGet httpGet = new HttpGet(url);
    try {
      CloseableHttpResponse response = httpclient.execute(httpGet);
      jsonObject.put("status",response.getStatusLine());
      HttpEntity entity = response.getEntity();
      EntityUtils.consume(entity);
      jsonObject.put("responseText",entity.getContent());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonObject;
  }

  /**
   * 获取Nginx的连接数
   * @param url
   * @return
   */
  public static JSONObject httpGetNginxRequestNums(String url){
    CloseableHttpClient httpclient = HttpClients.createDefault();
    JSONObject jsonObject = new JSONObject();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String total = "0";
    HttpGet httpGet = new HttpGet(url);
    try {
      CloseableHttpResponse response = httpclient.execute(httpGet);
      BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      StringBuffer sb = new StringBuffer("");
      String line = "";
      String NL = System.getProperty("line.separator");
      while ((line = in.readLine()) != null) {
        sb.append(line + NL);
      }
      in.close();
      String content = sb.toString();
      if(content.length()>1){
        String[] contentArr = content.split("\r\n");
        total = contentArr[0].split(": ")[1];
        total = total.split(" ")[0];
        total = total.trim();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String nowDate = simpleDateFormat.format(new Date());
    jsonObject.put("date",nowDate.split(" ")[0]);
    jsonObject.put("time",nowDate.split(" ")[1]);
    jsonObject.put("total",total);
    return jsonObject;
  }
}
