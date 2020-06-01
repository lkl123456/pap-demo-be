package com.yonyou.base.pattern.behavioral.template;
/**
 * 解释一下模板方法模式，就是指：一个抽象类中，有一个主方法，再定义1...n个方法，可以是抽象的，
 * 也可以是实际的方法，
 * 定义一个类，继承该抽象类，重写抽象方法，通过调用抽象类，实现对子类的调用
* @Description:  
* @author: lkl 
* @date: 2019年12月20日 上午11:02:48
 */
public class TemplateTest {
	public static void main(String[] args) {
		/**
		 * 就是在AbstractCalculator类中定义一个主方法calculate，
		 * calculate()调用spilt()等，Plus和Minus分别继承AbstractCalculator类，
		 * 通过对AbstractCalculator的调用实现对子类的调用
		 */
        String exp = "8+8";  
        AbstractCalculator cal = new Plus();  
        int result = cal.calculate(exp, "\\+");  
        System.out.println(result);  
    }  
}
