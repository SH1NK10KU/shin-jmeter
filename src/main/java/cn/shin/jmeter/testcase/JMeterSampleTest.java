package cn.shin.jmeter.testcase;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import cn.shin.jmeter.util.impl.HttpClientUtilImpl;

/**
 * A JMeter sample
 * 
 * @author Shin Feng
 * @date 2015-07-01
 *
 */
public class JMeterSampleTest extends AbstractJavaSamplerClient {

	private static String label = "JMeterSampleTest ";
	private HttpClientUtilImpl httpClientUtil = new HttpClientUtilImpl();

	public void setupTest() {
		// 定义测试初始值，setupTest只在测试开始前使用
		System.out.println("setupTest");
	}

	@Override
	public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
		SampleResult sampleResult = new SampleResult();
		sampleResult.setSampleLabel(label);
		sampleResult.setEncodingAndType("UTF-8");
		String url = javaSamplerContext.getParameter("url");
		String wd = javaSamplerContext.getParameter("wd");

		sampleResult.sampleStart();

		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("wd", wd);
			HttpResponse httpResponse = httpClientUtil.getHttpGetResponse(url, data);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				sampleResult.setSuccessful(true);
			} else {
				sampleResult.setSuccessful(false);
			}
		} catch (Exception e) {
			sampleResult.setSuccessful(false);
		}
		
		sampleResult.sampleEnd();
		return sampleResult;
	}

	public void teardownTest(JavaSamplerContext javaSamplerContext) {
		super.teardownTest(javaSamplerContext);
	}

	public Arguments getDefaultParameters() {
		// 参数定义，显示在前台，也可以不定义
		Arguments params = new Arguments();
		params.addArgument("url", "http://www.baidu.com/s");
		params.addArgument("wd", "test");
		return params;
	}

}
