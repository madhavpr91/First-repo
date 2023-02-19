package com.store.utils;

import java.util.Map;

import com.store.utils.GlobalVariables;

public class GlobalVariables {

private static ThreadLocal<GlobalVariables> threadLocal = new ThreadLocal<>();
	
	public static void set(GlobalVariables globalVariables) {
		threadLocal.set(globalVariables);
	}
	
	public static GlobalVariables get() {
		return threadLocal.get();
	}
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String REPORT_FOLDER_PATH = PROJECT_PATH+"\\reports\\";
	public static final String SCREENSHOT_FOLDER_PATH = PROJECT_PATH+"\\screenshots\\";
	public static final String CONFIG_FOLDER_PATH = PROJECT_PATH+"\\src\\test\\resources\\Config\\";
	public static final String DATA_FILES_PATH = PROJECT_PATH+"\\src\\test\\resources\\DataFiles\\";
	public static final String REPOSITORY_FILE_PATH = PROJECT_PATH+"\\src\\test\\resources\\Repositories\\";
	public static final int DEFAULT_IMPLICIT_WAIT = 20;
	public static final int DEFAULT_EXPLICIT_WAIT = 20;
	public static Map<String, String> envData;
	public String tcName;
	public String currentTestName;
	public static Map<String, String> tcData;
	public static Map<String, String> priceData;
	
}
