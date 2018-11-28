package com.jyblife.logic.wl.ops.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class HttpUtils {

	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	private static PoolingHttpClientConnectionManager connMgr;
	@SuppressWarnings("unused")
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 60000;

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}
	
	/**
	 * 发送http json post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postJson(String api, String params) {
		String result = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(api);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			// 设置文件类型:
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// 设置接收类型否则返回415错误
			// conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
			conn.setRequestProperty("accept", "application/json");
			conn.setUseCaches(false);
			conn.setConnectTimeout(60000); 
			conn.setReadTimeout(60000);   
			// 往服务器里面发送数据
			if (!StringUtils.isBlank(params)) {
				byte[] writebytes = params.getBytes();
				// 设置文件长度
				conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
				OutputStream outwritestream = conn.getOutputStream();
				outwritestream.write(params.getBytes());
				outwritestream.flush();
				outwritestream.close();
			}
			if (conn.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				result = reader.readLine();
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null) {
					buffer.append(line + "\n");
				}
				logger.info("postJson 响应错误编码异常信息： " + buffer.toString());
			}
			logger.info("==============post json 响应数据信息====================");
			logger.info("url: " + api + "   result: " + result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return result;
	}
	
	/**
	 * 发送http post请求
	 * 
	 * @param api
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("rawtypes") 
	public static String post(String api, Map params, String encoding) {
		String result = "";
		// 建立HttpPost对象
		HttpPost httppost = new HttpPost(api);
		try {
			// 添加参数
			List<NameValuePair> form = new ArrayList<NameValuePair>();  
			if(params != null && !params.isEmpty()) {
				for (Object name : params.keySet()) {  
					form.add(new BasicNameValuePair(String.valueOf(name), String.valueOf(params.get(name))));  
				}
			}
			
			httppost.setEntity(new UrlEncodedFormEntity(form, encoding));
			HttpClient httpClient = HttpClients.createDefault();
			// 设置编码
			HttpResponse response =httpClient.execute(httppost);// new DefaultHttpClient().execute(httppost);
			logger.debug("接口请求:["+ api +"],返回状态码:" + response.getStatusLine().getStatusCode());
			// 发送Post,并返回一个HttpResponse对象
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());
			}
			
		} catch (Exception e) {
			logger.error("Http post请求异常---api：" + api);
			logger.error("Http post请求异常---params：" + params.toString());
			logger.error(e.getMessage());
		} finally {
			if(httppost!=null) {
				httppost.releaseConnection();
			}
		}
		return result;
	}
	
}
