package com.store.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.store.utils.Driver;
import com.store.utils.EventUtil;
import com.store.utils.GlobalVariables;
import com.store.utils.ReporterUtil;

public class PurchaseProductPage extends EventUtil{
	
	private WebDriver driver;
	
	private static ThreadLocal<PurchaseProductPage> threadLocal = new ThreadLocal<>();
	
	public static void set(PurchaseProductPage purchaseProductPage) {
		threadLocal.set(purchaseProductPage);
	}
	
	public static PurchaseProductPage get() {
		return threadLocal.get();
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	@FindBy(xpath = "//h2[text()='Monitors']/..//a[text()='Apple Cinema 30\"']")
	private WebElement lnkAppleCinema;
	
	@FindBy(xpath = "//div[@id='content']//h1[text()='Apple Cinema 30\"']")
	private WebElement purchaseProductName;
	
	@FindBy(xpath = "//div[@class='radio']//input")
	private WebElement btnRadio;
	
	@FindBy(xpath = "(//div[@class='checkbox']//input[@type='checkbox'])[2]")
	private WebElement clkCheckbox;
	
	@FindBy(xpath = "//label[text()='Textarea']/following-sibling::textarea")
	private WebElement txtArea;
	
	@FindBy(xpath = "//label[text()='Select']/following-sibling::select")
	private WebElement selectOptions;
	
	@FindBy(xpath = "//label[text()='Qty']/following-sibling::input[@name='quantity']")
	private WebElement enterQuantity;
	
	@FindBy(xpath = "//button[text()='Add to Cart']")
	private WebElement btnAddToCart;
	
	@FindBy(xpath = "//div[text()='File required!']")
	private WebElement fileRequired;
	
	@FindBy(xpath = "//h1[text()='Apple Cinema 30\"']/..//li/h2")
	private WebElement productPrice;
	
	@FindBy(id = "button-upload222")
	private WebElement btnUpload;
	
	public PurchaseProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public boolean navigateToPurchaseProductPage() {
		clickElement(lnkAppleCinema);
		return checkElementExists(purchaseProductName, GlobalVariables.DEFAULT_EXPLICIT_WAIT);
	}
	
	public boolean displayFileRequiredMessage() {
		jsClick(btnAddToCart);
		return checkElementExists(fileRequired, GlobalVariables.DEFAULT_EXPLICIT_WAIT);
	}
	
	public void purchaseOrderData(Map<String, String> data) throws AWTException, IOException {
//		System.out.println("AllData 'data' of and browser = "+data);
		selectValueOfPrice(productPrice);
		clickElement(btnRadio);
		selectCheckBox(clkCheckbox);
		selectValueFromList(selectOptions, GlobalVariables.tcData.get("SELECT"));
		constantWait(4000);
		ReporterUtil.get(). writeLog(Status.PASS ,"Application is Select CheckBox message.", "Select Check box message");
//		enterValue(txtArea, GlobalVariables.tcData.get("TEXT_AREA"));
		textCopyAndPaste(txtArea, GlobalVariables.tcData.get("FILE"));
		enterValue(enterQuantity, GlobalVariables.tcData.get("QTY"));
		constantWait(4000);
		ReporterUtil.get(). writeLog(Status.PASS ,"Application Select Text-Area message.", "Text Area- Atmosol message");
		
		
	}
	
	public void purchaseOrderData2(Map<String, String> data) throws AWTException {
		 
		try {
			if(btnUpload .equals(btnAddToCart))
				selectImageFromFiles(btnUpload, "D:\\zip files\\harsha.zip");
				ReporterUtil.get(). writeLog(Status.PASS ,"file is upload.", "file is upload.");
				
		}catch(Exception e) {
			ReporterUtil.get().writeLog(Status.FAIL ,"file is NOT upload.", "file is NOT upload.");
			Assert.fail("Purchase Product page is file not upload ");
		}
	  
	}
	
	

}
