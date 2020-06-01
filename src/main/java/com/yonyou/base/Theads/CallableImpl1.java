package com.yonyou.base.Theads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableImpl1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "callllllll";
	}
	
	public static void main(String[] args) {
		FutureTask<String> future2 = new FutureTask<>(new CallableImpl1());
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("runnnnnnn");
			}
		};
		
		new Thread(future2).start();
		try {
			System.out.println(future2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
