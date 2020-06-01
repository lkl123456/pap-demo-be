package com.yonyou.base.support.plugin;

import java.text.MessageFormat;

public class Demo2T {
	
	private static String messageTemplate = "The {0} \"{1}\" parameter must exist.";
	public static void main(String[] args) {
		
		System.out.println(MessageFormat.format(messageTemplate,"asdsd","ahhhh"));
	}
	
}
