package com.yonyou.base.pattern.creation.factory;

public class SendFactory {
	//1
	public Sender produce(String type) {  
        if ("mail".equals(type)) {  
            return new MailSender();  
        } else if ("sms".equals(type)) {  
            return new SmsSender();  
        } else {  
            System.out.println("请输入正确的类型!");  
            return null;  
        }  
    }  
	
	//2
	public Sender produceMail(){  
        return new MailSender();  
    }  
    public Sender produceSms(){  
        return new SmsSender();  
    }  
}
