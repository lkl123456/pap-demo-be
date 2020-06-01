package com.yonyou.base.pattern.creation.singleton;
/**
 * 考虑性能的话，整个程序只需创建一次实例，所以性能也不会有什么影响。
* @Description:  
* @author: lkl 
* @date: 2019年12月19日 下午8:55:18
 */
public class Singleton5 {  
	  
    private static Singleton5 instance = null;  
  
    private Singleton5() {  
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new Singleton5();  
        }  
    }  
  
    public static Singleton5 getInstance() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }  
}  
