package com.store.pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.store.pages.MonitorsPage;
import com.store.utils.EventUtil;
import com.store.utils.GlobalVariables;

public class MonitorsPage extends EventUtil {
	
	private WebDriver driver;
	
	private static ThreadLocal<MonitorsPage> threadLocal = new ThreadLocal<>();
	
	public static void set(MonitorsPage monitorPage) {
		threadLocal.set(monitorPage);
	}
	
	public static MonitorsPage get() {
		return threadLocal.get();
	}
	
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	@FindBy(xpath = "//div[@id='content']/h2[text()='Monitors']")
	private WebElement lblMonitorsPageHeader;
	
	
	
	@FindBy(xpath = "//h2[text()='Monitors']/..//a[text()='Apple Cinema 30\"']/..//following-sibling::p[@class='price']/span[@class='price-new' or text()='$110.00']")
	private WebElement priceOfMonitor;
	
	@FindBy(xpath = "//a[text()='Components']/..//li/a[text()='Monitors (2)']")
	private WebElement lnkMonitors;
	
//	@FindBy(xpath = "//a[text()='Components']/..//li/a[text()='Monitors (2)']")
//	private WindowType lnkMonitorss; 
	
	public MonitorsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		
	}
	
	public boolean navigateToMonitorsPage() {
		clickElement(lnkMonitors);
		return checkElementExists(lblMonitorsPageHeader);
		
	}
	
	public void monitorPageOfPriceData(Map<String, String> data) {
		jsClick(priceOfMonitor);
		selectValueOfPrice(priceOfMonitor);
	}

}
