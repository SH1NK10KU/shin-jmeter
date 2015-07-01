package cn.shin.jmeter.util.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cn.shin.jmeter.util.IHttpClientUtil;

/**
 * Provide the basic methods for HTTP post/get. (JMeter use httpclient-4.2.6 and httpcore-4.2.5)
 * 
 * @author Shin Feng
 * @date 2015-07-01
 *
 */
public class HttpClientUtilImpl implements IHttpClientUtil {
	private static final String CONTENT_CHARSET = "UTF-8";

	/**
	 * Get response string via HTTP GET.
	 * 
	 * @param url
	 * @param params
	 *            HashMap for parameters
	 * @return
	 */
	public HttpResponse getHttpGetResponse(String url, Map<String, String> params) {
		HttpResponse response = null;
		try {
			Iterator<?> iterator = params.entrySet().iterator();
			url += "?";
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				url += (String) entry.getKey() + "="
						+ (String) entry.getValue() + "&";
			}

			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Get response string via HTTP POST.
	 * 
	 * @param url
	 * @param params
	 *            HashMap for parameters
	 * @return
	 */
	public HttpResponse getHttpPostResponse(String url, Map<String, String> params) {
		HttpResponse response = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			Iterator<?> iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				urlParameters.add(new BasicNameValuePair((String) entry
						.getKey(), (String) entry.getValue()));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
			response = httpClient.execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}