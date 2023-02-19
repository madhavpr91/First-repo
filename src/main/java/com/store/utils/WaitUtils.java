package com.store.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

public static void constantWait(int millis) {
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			
		}
		
	}

public boolean waitForElementToVisible(WebElement element, int timeOut) {
	
	try {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	} catch(Exception e) {
		System.out.println("Element: " + element.toString()+ " was not visible even after waiting for "+ timeOut + " seconds.");
	}
	
	return false;	
}

public boolean waitForElementToVisible(By by, int timeOut) {
	
	try {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return true;
	} catch(Exception e) {
		System.out.println("Element: " + by.toString()+ " was not visible even after waiting for "+ GlobalVariables.DEFAULT_EXPLICIT_WAIT + " seconds.");
	}
	
	return false;	
}

public boolean waitForElementToVisible(WebElement element) {
	
	try {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_WAIT));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	} catch(Exception e) {
		System.out.println("Element: " + element.toString()+ " was not visible even after waiting for "+ GlobalVariables.DEFAULT_EXPLICIT_WAIT + " seconds.");
	}
	
	return false;	
}

public boolean waitForElementToEnable(WebElement element, int timeOut) {
	
	try {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return true;
	} catch(Exception e) {
		System.out.println("Element: " + element.toString()+ " was not enabled even after waiting for "+ timeOut + " seconds.");
	}
	
	return false;	
}
	
}
