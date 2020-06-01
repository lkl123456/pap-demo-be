package com.yonyou.base.pattern.creation.abstractFactory;

public class MailSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is mailsender!");  
    }  
}  
