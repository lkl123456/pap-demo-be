package com.yonyou.base.pattern.creation.singleton;

import java.util.Vector;

/**
 * 采用"影子实例"的办法为单例对象的属性同步更新
* @Description:  
* @author: lkl 
* @date: 2019年12月19日 下午8:51:17
 */
@SuppressWarnings("all")
public class Singleton6 {  
	  
    private static Singleton6 instance = null;  
    private Vector properties = null;  
  
    public Vector getProperties() {  
        return properties;  
    }  
  
    private Singleton6() {  
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new Singleton6();  
        }  
    }  
  
    public static Singleton6 getInstance() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }  
  
    public void updateProperties() {  
        Singleton6 shadow = new Singleton6();  
        properties = shadow.getProperties();  
    }  
}  
