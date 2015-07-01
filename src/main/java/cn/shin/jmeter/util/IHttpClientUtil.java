package cn.shin.jmeter.util;

import java.util.Map;

import org.apache.http.HttpResponse;

/**
 * Interface for IHttpUtilImpl.
 * 
 * @author Shin Feng
 * @date 2014-11-07
 *
 */
public interface IHttpClientUtil {
	/**
	 * Get response string via HTTP GET.
	 * 
	 * @param url
	 * @param data
	 *            Map for parameters
	 * @return
	 */
	public HttpResponse getHttpGetResponse(String url, Map<String, String> data);

	/**
	 * Get response string via HTTP POST.
	 * 
	 * @param url
	 * @param data
	 *            Map for parameters
	 * @return
	 */
	public HttpResponse getHttpPostResponse(String url, Map<String, String> data);
}
