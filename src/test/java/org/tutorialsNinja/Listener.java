package org.tutorialsNinja;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.tutorialsNinja.resources.Base;
import org.tutorialsNinja.resources.ExtentReporterNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends Base implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
    // TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		System.out.println("Start from Listener.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
    // TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
		System.out.println("On test Success from Listener.\n");
	}

	@Override
	public void onTestFailure(ITestResult result) {

    // TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();
		WebDriver driver = null;

		try {
			System.out.println("On test Failure from Listener.");
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), testMethodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
    // TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    // TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
    // TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
    // TODO Auto-generated method stub
		extent.flush();
	}

}
