package com.store.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.store.exceptions.CheckBoxCannotBeSelectedException;
import com.store.exceptions.ClickElementFailedException;
import com.store.exceptions.DataNotPasteInField;
import com.store.exceptions.ElementValueCanNotBeSetException;
import com.store.exceptions.GetTextCannotBeMacthBothElementsException;
import com.store.exceptions.GetTextCannotBeSelectedException;
import com.store.exceptions.ValueInListBoxNotFoundException;



public class EventUtil  extends WaitUtils{
	
	protected boolean checkElementExists(WebElement elem) {	
			return waitForElementToVisible(elem);
	}
	
	protected boolean checkElementExists(WebElement elem, int timeOut) {
			elementBorder(elem);
			return waitForElementToVisible(elem,timeOut);		
	}
	
	public void enterValue(WebElement element, String valueToEnter) {
			
		
			if (element != null) {
				if (waitForElementToVisible(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT) 
						&& waitForElementToEnable(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT)) {
					element.clear();
					elementBorder(element);
					element.sendKeys(valueToEnter);
				}else {
					throw new ElementValueCanNotBeSetException ("unable to enter the value : " +valueToEnter+" as the Element " + element.toString()+" either not enabled or not visible. Unable to click on the element.");
				}
			} else {
				throw new ElementValueCanNotBeSetException ("unable to enter the value " + valueToEnter+" on the element as given element is null.");
			}
			
		}
	
	public void selectCheckBox(WebElement element) {		
		
		if (element != null) {
			if (waitForElementToVisible(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT) 
					&& waitForElementToEnable(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT)) {
				if (!element.isSelected())
					elementBorder(element);
						element.click();
			}else {
				throw new CheckBoxCannotBeSelectedException("Element " + element.toString()+" either not enabled or not visible. Unable to click on the element.");
			}
		} else {
			throw new CheckBoxCannotBeSelectedException("unable to select the checkbox  as given element is null.");
		}
		
	}
		
	public static void killProcesses() {
		String[] allProcesses = {"chrome.exe","firefox.exe","msedge.exe","chromedriver.exe","geckodriver.exe","msedgedriver.exe"};
		for (String process: allProcesses) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM "+ process);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	
	}
	
	public boolean switchToWindow(String windowTitle) {
		boolean isWindowFound = false;
		Set<String> allHandles = Driver.get().getWindowHandles();
		
		for (String handle: allHandles) {
			Driver.get().switchTo().window(handle);
			if (Driver.get().getTitle().trim().contains(windowTitle)) {
				isWindowFound = true;
				break;
			}
		}
		
		return isWindowFound;
	
	}
	
	public boolean switchToWindows() {
		boolean isWindowFound = false;
		Set<String> allHandles = Driver.get().getWindowHandles();
		
		for (String handle: allHandles) {
			Driver.get().switchTo().window(handle);
			if (Driver.get().getTitle().trim().contains("")) {
				isWindowFound = true;
				break;
			}
		}
		
		return isWindowFound;
	
	}
	
	public void selectValueFromList(WebElement listBox, String valueToSelect) throws ValueInListBoxNotFoundException  {
		boolean isOptionFound = false;
		elementBorder(listBox);
		clickElement(listBox);
		
		List<WebElement> allOptions = listBox.findElements(By.tagName("option"));
		
		for (WebElement optionElem: allOptions) {
			if (optionElem.getText().trim().contains(valueToSelect)) {
				
				optionElem.click();
				isOptionFound = true;
				break;
			}
		} 
		
		if (!isOptionFound) {
			throw new ValueInListBoxNotFoundException("given value : "+ valueToSelect+" could not be selected in the listbox "+ listBox.toString()+" as the value is not found in the list.");
		}
		
	}
	
	public void clickElement(WebElement element) {
		
		
		if (element != null) {
			if (waitForElementToVisible(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT) 
					&& waitForElementToEnable(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT)) {
				elementBorder(element);
				element.click();
			}else {
				throw new ClickElementFailedException("Element " + element.toString()+" either not enabled or not visible. Unable to click on the element.");
			}
		} else {
			throw new ClickElementFailedException("unable to click on the element as given element is null.");
		}
		
	}
	
	public void clickElements(WebElement element) {
		
		
		if (element != null) {
			if (waitForElementToVisible(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT) 
					&& waitForElementToEnable(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT)) {
				newTab(element);
				elementBorder(element);
				element.click();
			}else {
				throw new ClickElementFailedException("Element " + element.toString()+" either not enabled or not visible. Unable to click on the element.");
			}
		} else {
			throw new ClickElementFailedException("unable to click on the element as given element is null.");
		}
		
	}
	

	
	public void selectValueOfPrice(WebElement element) {		
		
		if (element != null) {
			if (waitForElementToVisible(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT) 
					&& waitForElementToEnable(element, GlobalVariables.DEFAULT_EXPLICIT_WAIT)) {
				if (!element.isSelected())
						element.getText();
					
			}else {
				throw new GetTextCannotBeSelectedException("Element " + element.toString()+" either not enabled or not visible. Unable to click on the element.");
			}
		} else {
			throw new GetTextCannotBeSelectedException("unable to select the price  as given element is null.");
		}
		
	}
	
	public void selectValueOfPProductPrice(WebElement priceData) {
			
		if (priceData != null) {
			if (waitForElementToVisible(priceData, GlobalVariables.DEFAULT_EXPLICIT_WAIT) 
					&& waitForElementToEnable(priceData, GlobalVariables.DEFAULT_EXPLICIT_WAIT)) {
				if (!priceData.isSelected())
					priceData.getText();
				
			}else {
				throw new GetTextCannotBeSelectedException("Element " + priceData.toString()+" either not enabled or not visible. Unable to click on the element.");
			}
		} else {
			throw new GetTextCannotBeSelectedException("unable to select the price  as given element is null.");
		}
		
	}
	
	public void jsClick(WebElement element) {
		
		
		if (element != null) {
			JavascriptExecutor js = (JavascriptExecutor)Driver.get();
			elementBorder(element);
			screenShot();
			js.executeScript("arguments[0].click();", element);
		} else {
			throw new ClickElementFailedException ("unable to click on the element as given element is null.");
		}
		
	}
	
	public void elementBorder(WebElement element) {
		
		
		if (element != null) {
			JavascriptExecutor js = (JavascriptExecutor)Driver.get();
			js.executeScript("arguments[0].style.border='3px solid red'", element);
		} 
		
	}
	@Test
	public void screenShot() {
		ReporterUtil.get().writeLog(Status.PASS ,"Application Select Text-Area message.", "screenShotsYS");
	}
	
	public void scrollDownClick(WebElement element) {
		
		JavascriptExecutor jse =  (JavascriptExecutor) Driver.get();
		
		while(true) {
			try {
				jse.executeScript("arguments[0].click();", element); 
				break;
			}catch (Exception e){
				jse.executeScript("window.scrollBy(0,500);");
			}
		}
		
	}
		
		public void newTab(WebElement element) {
			Driver.get().switchTo().window("element");
			
			
		}
	
	
		public void selectImageFromFiles(WebElement fileBox , String filePath ) throws AWTException{
			
			clickElement(fileBox);
			
	        StringSelection ss = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
			Robot robot = new Robot();
			robot.delay(5000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(4000);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			    
			
		}
		
		@Test
		public void dragAndDrop(String item, WebElement target) {
	        String dragAndDropJS = "C:\\Users\\tchan\\OneDrive\\Desktop\\ABC.csv";
			((JavascriptExecutor)Driver.get()).executeScript(dragAndDropJS +" $('#"+item+"').simulateDragDrop({ dropTarget: '#"+target+"'});");
	    }
		
		@Test
		public void copyPaste(WebElement element ) throws AWTException {
			
			String string = "Keyboard and Mouse \r\n"
					+ "Abc and Xyz\r\n"
					+ "World's\r\n"
					+ "Abcdefghijklmno\r\n"
					+ "pqrstuvwxyz  + ";
			clickElement(element);
			
			StringSelection ss = new StringSelection(string);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot = new Robot();
			try {				
				  	 robot.keyPress(KeyEvent.VK_CONTROL);
				        robot.keyPress(KeyEvent.VK_V);
				        Thread.sleep(2000);
				        robot.keyRelease(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_CONTROL); 	
				  	
			}catch (Exception e) {
				
			}
			
		}
		
		public  void textCopyAndPaste(WebElement element, String filePath) throws IOException, AWTException {
			clickElement(element);
			Robot robot = new Robot();
			File f =new File(filePath);	
//			File f =new File("C:\\Users\\tchan\\Downloads\\naukrki updtae.txt");	
			FileReader fr = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fr);
			 String textLine ;
			 textLine = bfr.readLine();

			while (textLine != null){				
				
					// --->2. This is using of second line printing(textLine = bfr.readLine();)
//					textLine = bfr.readLine();					
					String string =textLine;				
					StringSelection ss = new StringSelection(string);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
					
					try {					  	
						  	 robot.keyPress(KeyEvent.VK_CONTROL);
						        robot.keyPress(KeyEvent.VK_V);
						        Thread.sleep(2000);
						        robot.keyRelease(KeyEvent.VK_V);
						        robot.keyRelease(KeyEvent.VK_CONTROL);
						        robot.keyPress(KeyEvent.VK_ENTER);
						        robot.keyRelease(KeyEvent.VK_ENTER);
						  	
					}catch (Exception e) {
						throw new DataNotPasteInField ("unable to not paste as given element is null.");
					}
					//--->1. This is the using the first line printing(textLine = bfr.readLine();)
					textLine = bfr.readLine();   

			}	
			
		}
		
		public static void textCopy(WebElement element) throws IOException, AWTException {
			Robot robot = new Robot();
//			File f =new File("D:\\Keyboard and mouse.txt");	
//			File f =new File("C:\\Users\\tchan\\OneDrive\\Desktop\\ABC.csv");
			File f =new File("C:\\Users\\tchan\\OneDrive\\Desktop\\testfile.json");
			FileReader fr = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fr);
			 String textLine ;
			 textLine = bfr.readLine();

			while (textLine != null){
				if(textLine != null) {
					
					// --->2. This is using of second line printing(textLine = bfr.readLine();)
//					textLine = bfr.readLine();
					
					String string =textLine;				
					StringSelection ss = new StringSelection(string);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
					
					try {					  	
						  	 robot.keyPress(KeyEvent.VK_CONTROL);
						        robot.keyPress(KeyEvent.VK_V);
						        Thread.sleep(2000);
						        robot.keyRelease(KeyEvent.VK_V);
						        robot.keyRelease(KeyEvent.VK_CONTROL);
						        robot.keyPress(KeyEvent.VK_ENTER);
						        robot.keyRelease(KeyEvent.VK_ENTER);
						  	
					}catch (Exception e) {
						throw new DataNotPasteInField ("unable to not paste as given element is null.");
					}
					//--->1. This is the using the first line printing(textLine = bfr.readLine();)
					textLine = bfr.readLine();   
				}
			}	
			
		}

}
