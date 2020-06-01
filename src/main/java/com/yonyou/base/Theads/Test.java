package com.yonyou.base.Theads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	
	public static void main(String[] args) {
		//test0_1();//线程t1&t2同时执行
//		test1_1();//顺序执行线程t1&t2,t1执行完毕，执行t2
		test2_1();
	}
	
	public static void test0_1() {
		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				test0_2("t1");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				test0_2("t2");
			}
		});
		t1.start();
		t2.start();
	}
	
	public static void test0_2(String ThreadName) {
		int i=0;
		while (i++<5) {
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(ThreadName+"print"+i);
		}
	}
//###############################################################################
	
	public static void test1_1() {
		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				test1_2("t1");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t2等t1先执行完毕，在执行。。。");
				try {
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				test1_2("t2");
			}
		});
		t1.start();
		t2.start();
	}
	
	public static void test1_2(String ThreadName) {
		int i=0;
		while (i++<5) {
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(ThreadName+"print"+i);
		}
	}
//#############################################################################
	static Lock lock = new ReentrantLock();
	public static void test2_1() {
		//final Object lock = new Object();
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (lock) {
						System.out.println("t1_1");
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("t1_2");
					}
				}
			});
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (lock) {
						System.out.println("t2_1");
						System.out.println("t2_2");
						lock.notify();
					}
				}
			});
			t1.start();
			t2.start();
	}
	
	/*public static void test2_2(String ThreadName) {
		int i=0;
		while (i++<5) {
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(ThreadName+"print"+i);
		}
	}*/
	
	
}
