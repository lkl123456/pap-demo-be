package com.yonyou.base.pattern.creation.abstractFactory;

public class Test {  
	  
    public static void main(String[] args) {  
        Provider provider = new SendMailFactory();  
        Sender sender = provider.produce();  
        sender.Send();  
    }  
}  
