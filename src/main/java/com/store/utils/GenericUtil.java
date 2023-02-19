package com.store.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenericUtil extends GlobalVariables {
	
	public void createFolder(String path) {
		File f = new File(path);
		
		if (!f.exists())
			f.mkdirs();
		
	}
	
	public boolean checkFileExists(String path) {
		return new File(path).exists();
		
	}
	
	public void deleteAllFiles(String path) {
		if (checkFileExists(path)) {
			File f = new File(path);
			File[] files = f.listFiles();
			
			for (File file: files) {
				try {
					file.delete();
				} catch (Exception e) {
					
				}
				
			}
		}
	}

	public static String getTimeStamp() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return ""+c.get(2)+c.get(5)+c.get(10)+c.get(12)+c.get(13);
		
	}
	
	public static String getDateTimeInFormat(Date dt, String dateTimeFormat) {		
		SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
		return sdf.format(dt);
		
	}
	
}
