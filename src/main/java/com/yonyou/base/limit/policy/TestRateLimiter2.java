package com.yonyou.base.limit.policy;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 接口限流策略:令牌桶算法 优点：当频繁调用接口时，tryAcquire这个方法的主要作用是设定一个超时的时间，
 * 如果在指定的时间内预估(注意是预估并不会真实的等待)， 如果能拿到令牌就返回true，如果拿不到就返回false
 * 然后我们让无效的直接跳过，这里设定每秒生产1个令牌，让每个任务尝试在0.5秒获取令牌，
 * 如果获取不到,就直接跳过这个任务(放在秒杀环境里就是直接抛弃这个请求)
 * 
 * @Description:
 * @author: lkl
 * @date: 2019年12月11日 下午12:21:38
 */
public class TestRateLimiter2 {

	public static void main(String[] args) throws InterruptedException {
		final RateLimiter rateLimiter = RateLimiter.create(1);// 每秒创建多少个令牌
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread();
			t.sleep(500);// 请求间隔
			long timeOut = (long) 0.5;// 每个任务尝试在多少秒获取令牌
			boolean isValid = rateLimiter.tryAcquire(timeOut, TimeUnit.SECONDS);
			System.out.println("任务" + i + "执行是否有效:" + isValid);
			if (!isValid) {
				continue;
			}
			System.out.println("任务" + i + "在执行");
		}
		System.out.println("结束");
	}
	// 只有第1个获取到了令牌，顺利执行了，下面的基本都直接抛弃了，
	// 因为0.5秒内，令牌桶(1秒1个)来不及生产就肯定获取不到返回false了。
}
