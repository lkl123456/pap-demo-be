package com.yonyou.base.limit.policy;

import com.google.common.util.concurrent.RateLimiter;

/**
 * acquire:acquire会阻塞当前线程直到获取到令牌， 也就是如果任务没有获取到令牌，会一直等待。
 * 那么请求就会卡在我们限定的时间内才可以继续往下走，这个方法返回的是线程具体等待的时间
 * 
 * @Description:
 * @author: lkl
 * @date: 2019年12月11日 下午12:24:05
 */
public class TestRateLimiter {

	public static void main(String[] args) {
		// 1秒产生1个令牌
		final RateLimiter rateLimiter = RateLimiter.create(2);
		for (int i = 0; i < 10; i++) {
			// 该方法会阻塞线程，直到令牌桶中能取到令牌为止才继续向下执行。
			double waitTime = rateLimiter.acquire();
			System.out.println("任务执行" + i + "等待时间" + waitTime);
		}
		System.out.println("执行结束");
	}
	// 执行后可以看到任务执行的过程中，第1个是无需等待的，因为已经在开始的第1秒生产出了令牌。
	// 接下来的任务请求就必须等到令牌桶产生了令牌才可以继续往下执行。
}