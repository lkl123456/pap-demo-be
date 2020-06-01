package com.yonyou.base.pattern.creation.singleton;

public class Singleton2 {  
	  
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */  
    private static Singleton2 instance = null;  
  
    /* 私有构造方法，防止被实例化 */  
    private Singleton2() {  
    }  
  
    /* 静态工程方法，创建实例  线程安全 
     * synchronized关键字锁住的是这个对象，这样的用法，
     * 在性能上会有所下降，因为每次调用getInstance()，都要对对象上锁
     * */    
    public static synchronized Singleton2 getInstance() {  
        if (instance == null) {  
            instance = new Singleton2();  
        }  
        return instance;  
    } 
    
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return instance;  
    }  
}  
