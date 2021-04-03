package com.hzmyself.crm.utils;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID(){
		//32位随机串
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
}
