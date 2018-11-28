package com.jyblife.logic.wl.ops.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.contants.PrintConstants;

/**
 * 油站打印工具类
 */
public class PrintUtils{

	
	/**
	 * 打印方法
	 */
	public static String printMethod(String content,String printSn) {
		//通过POST请求，发送打印信息到服务器
		RequestConfig requestConfig = RequestConfig.custom()  
				.setSocketTimeout(30000)//读取超时  
				.setConnectTimeout(30000)//连接超时
				.build();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig)
				.build();	

		HttpPost post = new HttpPost(PrintConstants.URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user",PrintConstants.USER));
		String STIME = String.valueOf(System.currentTimeMillis()/1000);
		nvps.add(new BasicNameValuePair("stime",STIME));
		nvps.add(new BasicNameValuePair("sig",DigestUtils.sha1Hex(PrintConstants.USER+PrintConstants.UKEY+STIME)));
		nvps.add(new BasicNameValuePair("apiname","Open_printMsg"));//固定值,不需要修改
		nvps.add(new BasicNameValuePair("sn",printSn));
		nvps.add(new BasicNameValuePair("content",content));
		nvps.add(new BasicNameValuePair("times","2"));//打印联数

		CloseableHttpResponse response = null;
		String result = null;
		try
		{
			post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			response = httpClient.execute(post);
			int statecode = response.getStatusLine().getStatusCode();
			if(statecode == 200){
				HttpEntity httpentity = response.getEntity(); 
				if (httpentity != null){
					//服务器返回的JSON字符串，建议要当做日志记录起来
					result = EntityUtils.toString(httpentity);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				post.abort();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 获取待打印数量
	 */
	public static String printNums(String sn) {
		//通过POST请求，发送打印信息到服务器
		RequestConfig requestConfig = RequestConfig.custom()  
				.setSocketTimeout(30000)//读取超时  
				.setConnectTimeout(30000)//连接超时
				.build();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig)
				.build();	

		HttpPost post = new HttpPost(PrintConstants.URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user",PrintConstants.USER));
		String STIME = String.valueOf(System.currentTimeMillis()/1000);
		nvps.add(new BasicNameValuePair("stime",STIME));
		nvps.add(new BasicNameValuePair("sig",DigestUtils.sha1Hex(PrintConstants.USER+PrintConstants.UKEY+STIME)));
		nvps.add(new BasicNameValuePair("apiname","Open_queryOrderInfoByDate"));//固定值,不需要修改

		nvps.add(new BasicNameValuePair("sn",sn));
		nvps.add(new BasicNameValuePair("date",DateUtil.DATE_FORMAT_DATE.format(new Date())));//固定值,不需要修改

		CloseableHttpResponse response = null;
		String result = null;
		try
		{
			post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			response = httpClient.execute(post);
			int statecode = response.getStatusLine().getStatusCode();
			if(statecode == 200){
				HttpEntity httpentity = response.getEntity(); 
				if (httpentity != null){
					//服务器返回的JSON字符串，建议要当做日志记录起来
					result = EntityUtils.toString(httpentity);
					JSONObject jsonObj=JSONObject.parseObject(result);
					if(null == jsonObj.getJSONObject("data")) {
						result = jsonObj.getString("msg");
					}else {
						result = jsonObj.getJSONObject("data").getString("waiting");
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				post.abort();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	/**
	 * 获取打印机状态
	 */
	public static String printStatus(String sn) {
		//通过POST请求，发送打印信息到服务器
		RequestConfig requestConfig = RequestConfig.custom()  
				.setSocketTimeout(30000)//读取超时  
				.setConnectTimeout(30000)//连接超时
				.build();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig)
				.build();	

		HttpPost post = new HttpPost(PrintConstants.URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user",PrintConstants.USER));
		String STIME = String.valueOf(System.currentTimeMillis()/1000);
		nvps.add(new BasicNameValuePair("stime",STIME));
		nvps.add(new BasicNameValuePair("sig",DigestUtils.sha1Hex(PrintConstants.USER+PrintConstants.UKEY+STIME)));
		nvps.add(new BasicNameValuePair("apiname","Open_queryPrinterStatus"));//固定值,不需要修改

		nvps.add(new BasicNameValuePair("sn",sn));

		CloseableHttpResponse response = null;
		String result = null;
		try
		{
			post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			response = httpClient.execute(post);
			int statecode = response.getStatusLine().getStatusCode();
			if(statecode == 200){
				HttpEntity httpentity = response.getEntity(); 
				if (httpentity != null){
					//服务器返回的JSON字符串，建议要当做日志记录起来
					result = EntityUtils.toString(httpentity);
					JSONObject jsonObj=JSONObject.parseObject(result);
					if(null == jsonObj.getJSONObject("data")) {
						result = jsonObj.getString("msg");
					}else {
						result = jsonObj.getString("data");
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				post.abort();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

}
