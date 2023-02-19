package com.store.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.store.pages.Configuration;
import com.store.pages.HomePage;
import com.store.pages.MonitorsPage;
import com.store.pages.PurchaseProductPage;
import com.store.utils.ReporterUtil;
import com.store.utils.WaitUtils;

public class ProductPurchaseTest extends Configuration{
	@Test
	public void yourStorePurchaseProduct() throws AWTException, IOException {
		
	
		WaitUtils.constantWait(4000);
		if (HomePage.get().navigateToHomePage()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Application is navigated to Home Page.", "HomePage");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Application is NOT navigated to Home Page.", "HomePage");
			Assert.fail("Home page is NOT displayed after clicking on Home Page.");
		}
		
		WaitUtils.constantWait(4000); 
		if (MonitorsPage.get().navigateToMonitorsPage()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Application is navigated to Monitors  Page.", "MonitorsPage");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Application is NOT navigated to Monitors  Page.", "MonitorsPage");
			Assert.fail("Monitors page is NOT displayed after clicking on  Home page.");
		}
		WaitUtils.constantWait(4000);
		
		MonitorsPage.get().monitorPageOfPriceData(tcData);
		
		
		WaitUtils.constantWait(4000);
		if (PurchaseProductPage.get().navigateToPurchaseProductPage()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Application is navigated to Purchase Product  Page.", "Purchase ProductPage");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Application is NOT navigated to Purchase Product  Page.", "Purchase ProductPage");
			Assert.fail("Purchase Product page is NOT displayed after clicking on Monitors page.");
		}
		WaitUtils.constantWait(4000);
		PurchaseProductPage.get().purchaseOrderData(envData);
		WaitUtils.constantWait(4000);
		
		if (PurchaseProductPage.get().displayFileRequiredMessage()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Application is file required! message.", "file required! message");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Application is NOT file required! message.", "file required! message");
			Assert.fail("file required! message is NOT displayed after clicking on Add to Cart. button ");
		
		}
		WaitUtils.constantWait(4000);
		
		PurchaseProductPage.get().purchaseOrderData2(tcData);
		WaitUtils.constantWait(4000);
	}

}
