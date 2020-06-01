package com.yonyou.base.pattern.creation.factory;

public class FactoryTest {  
	  
    public static void main(String[] args) {  
    	//1.
        SendFactory factory = new SendFactory();  
        Sender sender = factory.produce("sms");  
        sender.Send();  
        
        //2.
        Sender smsSender = factory.produceMail();  
        sender.Send();  
    }  
}  
