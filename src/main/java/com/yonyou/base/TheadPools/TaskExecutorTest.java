package com.yonyou.base.TheadPools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskExecutorTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// 创建一个Callable，3秒后返回String类型
		Callable myCallable = new Callable() {
			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				System.out.println("calld方法执行了");
				return "call方法返回值";
			}
		};
		Callable myCallable2 = new Callable() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				System.out.println("calld2方法执行了");
				return "call2方法返回值";
			}
		};
		
		Runnable myRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(6000);
					System.out.println("myRunnable方法执行了"+Thread.currentThread().getName() + " run time: " + System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable myRunnable2 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(4000);
					System.out.println("myRunnable2方法执行了"+Thread.currentThread().getName() + " run time: " + System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
			
			
		System.out.println("提交任务之前 "+getStringDate());
		Future future3 = executor.submit(myRunnable);
		Future future4 = executor.submit(myRunnable2);
		Future future = executor.submit(myCallable);
		Future future2 = executor.submit(myCallable2);
		System.out.println("提交任务之后 "+getStringDate());
		System.out.println("开始获取第一个返回值 "+getStringDate());
		System.out.println("获取返回值: "+future.get());
		System.out.println("获取第一个返回值结束，开始获取第二个返回值 "+getStringDate());
		System.out.println("获取返回值2: "+future2.get());
		System.out.println("获取第2个返回值结束，开始获取第3个返回值 "+getStringDate());
		System.out.println("获取的返回值3： "+future3.get());
		System.out.println("获取第3个返回值结束，开始获取第4个返回值 "+getStringDate());
		System.out.println("获取的返回值3： "+future4.get());
		System.out.println("获取第4个返回值结束 "+getStringDate());
	}

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}
