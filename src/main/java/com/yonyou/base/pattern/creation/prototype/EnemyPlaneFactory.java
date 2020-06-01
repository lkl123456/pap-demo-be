package com.yonyou.base.pattern.creation.prototype;

public class EnemyPlaneFactory {
	// 此处用痴汉模式造一个敌机原型
	private static EnemyPlane protoType = new EnemyPlane(200);

	// 获取敌机克隆实例
	public static EnemyPlane getInstance(int x) throws CloneNotSupportedException {
		EnemyPlane clone = protoType.clone();// 复制原型机
		clone.setX(x);// 重新设置克隆机的x坐标
		return clone;
	}
}
