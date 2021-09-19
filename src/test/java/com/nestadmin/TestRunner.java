package com.nestadmin;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.TestNG;
public class TestRunner {

	static TestNG testNg;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ExtentReportListener ext = new ExtentReportListener();
		//static ExtentReports e=;
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("a");
		testNg = new TestNG();
		testNg.setTestClasses(new Class[] { NestExtentReportsClass.class });
		//testNg.addListener(ext);
		testNg.run();
	}

}
