package com.yonyou.base.pattern.creation.abstractFactory;

public class SendSmsFactory implements Provider{  
	  
    @Override  
    public Sender produce() {  
        return new SmsSender();  
    }  
}  
