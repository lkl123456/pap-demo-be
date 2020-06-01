package com.yonyou.base.pattern.creation.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 工业生产中通常是指在量产之前研发出的概念实现，如果可行性满足即可参照原型进行量产
 * 浅拷贝和深拷贝
* @Description:  
* @author: lkl 
* @date: 2019年12月20日 下午8:29:14
 */
public class ClientTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		List<EnemyPlane> enemyPlanes = new ArrayList<EnemyPlane>();

		for (int i = 0; i < 50; i++) {
			// 此处随机位置产生敌机
			EnemyPlane instance = EnemyPlaneFactory.getInstance(new Random().nextInt(200));
			enemyPlanes.add(instance);
		}

	}
}
