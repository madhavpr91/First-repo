package com.store.utils;

import java.io.File;
import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReporterUtil extends GenericUtil{
	
	private ExtentReports extentReports;
	private ExtentTest testCase;

	
	private static ThreadLocal<ReporterUtil> threadLocal = new ThreadLocal<>();
	
	public static void set(ReporterUtil reportUtil) {
		threadLocal.set(reportUtil);
	}
	
	public static ReporterUtil get() {
		return threadLocal.get();
	}
	
public void initializeReport(String testSuiteName) {
		
		String reportFilePath = REPORT_FOLDER_PATH+testSuiteName+"_"+getTimeStamp()+".html";
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Exucuration Results for "+ testSuiteName+ "-"+ getTimeStamp());
		sparkReporter.config().setTimelineEnabled(true);
		sparkReporter.config().setTimeStampFormat("MM-dd-yyyy hh:mm:ss");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Execution Date", getDateTimeInFormat(new Date(), "MM-dd-yyyy hh:mm"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		try {
			InetAddress ip = InetAddress.getLocalHost();		
	        extentReports.setSystemInfo("IP Address", ip.getHostAddress());	
			extentReports.setSystemInfo("Local Host Name", ip.getHostName());	
		} catch(Exception e) {
			
		}
		
		
	}
	
	public void finalizeReport() {
		extentReports.flush();
	}
	
	public void addTest(String testCaseName) {
		testCase = extentReports.createTest(testCaseName);
	}
	
	public String getScreenShot(String screenShotName) {
		
		TakesScreenshot ts = (TakesScreenshot) Driver.get();
		try {
			File img = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.moveFile(img, new File(SCREENSHOT_FOLDER_PATH+screenShotName+"_"+getTimeStamp()+".png"));
		} catch (Exception e) {
			System.out.println("couldn't capture and save the screenshot with name : " + screenShotName);
		}
		
		
		return ts.getScreenshotAs(OutputType.BASE64);
	}
	
	public void writeLog(Status status, String stepInfo) {
		testCase.log(status, stepInfo);
	}
	
	public void writeLog(Status status, String stepInfo, String screenshotName) {
		testCase.log(status, stepInfo, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShot(screenshotName)).build());
	}
	
}
