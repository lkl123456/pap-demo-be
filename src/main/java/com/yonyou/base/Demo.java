package com.yonyou.base;

import java.lang.reflect.Constructor;

public class Demo {
	public static void main(String[] args) {
		try {
			// 根据类名获取Class对象
			Class c = Class.forName("Test");
			// 参数类型数组
			Class[] parameterTypes = { int.class, int.class };
			// 根据参数类型获取相应的构造函数
			Constructor constructor = c.getConstructor(parameterTypes);
			// 参数数组
			Object[] parameters = { 1, 1 };
			// 根据获取的构造函数和参数，创建实例
			Object o = constructor.newInstance(parameters);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Test {
	public Test(int x, int y) {
		System.out.println(x + y);
	}
}
