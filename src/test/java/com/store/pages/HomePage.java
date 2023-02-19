package com.store.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.store.pages.HomePage;
import com.store.utils.EventUtil;
import com.store.utils.GlobalVariables;

public class HomePage extends EventUtil{
	
	private WebDriver driver;
	
	private static ThreadLocal<HomePage> threadLocal = new ThreadLocal<>();
	
	public static void set(HomePage homePage) {
		threadLocal.set(homePage);
	}
	
	public static HomePage get() {
		return threadLocal.get();
	}
	
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	@FindBy(xpath = "//div[@id='logo']//a[text()='Your Store']")
	private WebElement yourStoreHeader;
	
	@FindBy(xpath = "//nav[@id='menu']//a[text()='Components']")
	private WebElement clkComponents;
	
	

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public boolean navigateToHomePage() {
		clickElement(clkComponents);
		return checkElementExists(yourStoreHeader, GlobalVariables.DEFAULT_EXPLICIT_WAIT);
		
	}

}
