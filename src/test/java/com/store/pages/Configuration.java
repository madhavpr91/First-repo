package com.store.pages;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.store.utils.DataUtil;
import com.store.utils.Driver;
import com.store.utils.EventUtil;
import com.store.utils.GlobalVariables;
import com.store.utils.ReporterUtil;

public class Configuration extends GlobalVariables{
	@Parameters("env")
	@BeforeSuite
	public void setUpExecution(@Optional String env) {
		env = (env==null)?"qa":env.toLowerCase();
		EventUtil.killProcesses();
		switch (env) {
		case "qa":
			envData = (new DataUtil()).getPropertyData(CONFIG_FOLDER_PATH+"\\EnvInfo_QA.properties");
			break;
		case "dev":
			envData = (new DataUtil()).getPropertyData(CONFIG_FOLDER_PATH+"\\EnvInfo_Dev.properties");
			break;
		default:
			envData = (new DataUtil()).getPropertyData(CONFIG_FOLDER_PATH+"\\EnvInfo_stage.properties");
			break;
		}
	}
	
	
	@BeforeSuite
	public void setUpExecutions(@Optional String tcdata) {
//		tcData = (new DataUtil()).getPropertyData(DATA_FILES_PATH+"\\ProductData_QA.properties");
		
		tcdata = (tcdata==null)?"qa":tcdata.toLowerCase();
		EventUtil.killProcesses();
		switch (tcdata) {
		case "qa":
			tcData = (new DataUtil()).getPropertyData(DATA_FILES_PATH+"ProductData_QA.properties");
			break;
		case "dev":
			tcData = (new DataUtil()).getPropertyData(DATA_FILES_PATH+"ProductData.properties");
			break;
		default:
			tcData = (new DataUtil()).getPropertyData(DATA_FILES_PATH+"EnvInfo_stage.properties");
			break;
		}
	}
		
	
	@BeforeTest
	public void initializeReport(ITestContext context) {
		ReporterUtil.set(new ReporterUtil());
		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		String testName = context.getCurrentXmlTest().getName();
		ReporterUtil.get().initializeReport(suiteName+"_"+ testName);

	}
	
	@AfterTest
	public void finalizeExecution() {
		ReporterUtil.get().finalizeReport();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void intializeTest(@Optional String browser, Method method, ITestContext context) {
		Driver driverUtil = new Driver();
		System.out.println(envData);
		driverUtil.initializeDriver(envData.get("browser"), envData.get("landingPage"));
		Driver.set(driverUtil.getWebDriver());
		GlobalVariables.set(new GlobalVariables());
		GlobalVariables.get().tcName = method.getName();	
		ReporterUtil.get().addTest(GlobalVariables.get().tcName);
	}
	
	@BeforeMethod
	public void setUpPages() {
		DataUtil.set(new DataUtil());
		HomePage.set(new HomePage(Driver.get()));
		MonitorsPage.set(new MonitorsPage(Driver.get()));
		PurchaseProductPage.set(new PurchaseProductPage(Driver.get()));
		String tcId = GlobalVariables.get().tcName.split("_")[0];	
//		GlobalVariables.get().tcData = DataUtil.get().getTCData(DATA_FILES_PATH+"ProductData.xlsx", tcId.replaceAll("[0-9]{1,}", ""), tcId);
		
	}
	
	@AfterMethod
	public void afterMethod() {
		Driver.get().quit();
	}
}
