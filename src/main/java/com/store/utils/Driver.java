package com.store.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.store.exceptions.InvalidBrowserException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private WebDriver driver;
	private String strMainWindowHandle;
	
	private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
	
	public static void set(WebDriver driver) {
		threadLocal.set(driver);
	}
	
	public static WebDriver get() {
		return threadLocal.get();
	}
	
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	
	public void initializeDriver(String browser, String url) {
		browser = (browser == null)?"chrome":browser.toLowerCase();
		
		switch (browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				throw new InvalidBrowserException("Browser : " + browser + " is invalid. Browser should be ");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariables.DEFAULT_IMPLICIT_WAIT));
		driver.manage().window().maximize();
		
		driver.get(url);
		strMainWindowHandle = driver.getWindowHandle();
		
	}
	
	public void switchToMainWindow() {
		driver.switchTo().window(strMainWindowHandle);
	}
	
	public void quitWindow() {
		driver.quit();
	}
	
	
}
