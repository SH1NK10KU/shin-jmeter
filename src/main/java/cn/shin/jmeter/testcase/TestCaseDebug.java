package cn.shin.jmeter.testcase;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

/**
 * Main method for debugging the test case.
 * 
 * @author Shin Feng
 * @date 2015-07-01
 *
 */
public class TestCaseDebug {

	public static void main(String[] args) {
		// 把测试接口类new一个对象
		JMeterSampleTest jMeterSampleTest = new JMeterSampleTest();
		JavaSamplerContext javaSamplerContext = new JavaSamplerContext(
				jMeterSampleTest.getDefaultParameters());
		// 初始值执行
		jMeterSampleTest.setupTest(javaSamplerContext);
		// 执行部分调用
		jMeterSampleTest.runTest(javaSamplerContext);
		// 执行结束处理
		jMeterSampleTest.teardownTest(javaSamplerContext);
	}
}
