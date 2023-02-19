package com.store.utils;

import org.apache.commons.lang3.RandomUtils;

public class RandomUtilss {

	public static void main(String[] args) {
		RandomUtils rm = new RandomUtils();
		
		String newName = "" ;
		String v=newName + rm.nextInt();
		System.out.println(v);
/*		System.out.println(v);
		for(int i=0;i <= rm.nextInt();i++) {
			String B=newName + rm.nextInt();
			System.out.println(B);
			System.out.println(rm.nextInt());
			
		}*/
	}

}
