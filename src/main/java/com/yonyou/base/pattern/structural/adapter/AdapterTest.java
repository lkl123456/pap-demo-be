package com.yonyou.base.pattern.structural.adapter;
/**
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，
 * 目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。首先，我们来看看类的适配器模式
 * 有一个Source类，拥有一个方法，待适配，目标接口时Targetable，通过Adapter类，将Source
* @Description:  
* @author: lkl 
* @date: 2019年12月19日 下午9:20:18
 */
public class AdapterTest {  
	  
    public static void main(String[] args) {  
    	
    	/**
    	 * 类的适配器模式
    	 * 有一个Source类，拥有一个方法，待适配，
    	 * 目标接口时Targetable，通过Adapter类，将Source的功能扩展到Targetable里
    	 */
        Targetable target = new Adapter();  
        target.method1();  
        target.method2();  
        
        
        /**
         * 对象的适配器模式
         * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，
         * 而是持有Source类的实例，以达到解决兼容性的问题。
         */
        Source source = new Source();  
        Targetable target2 = new Wrapper(source);  
        target2.method1();  
        target2.method2();  
    }  
}  
