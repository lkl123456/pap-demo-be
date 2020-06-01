package com.yonyou.base.Theads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交叉执行
 * @author yany
 *
 */
public class ThreadCommunication2 {
    
    private static class Syn{
    	int a = 0;

		/**
		 * @return the a
		 */
		public int getA() {
			return a;
		}

		/**
		 * @param a the a to set
		 */
		public void setA(int a) {
			this.a = a;
		}
    	
    }
    
    public static void main(String[] args) {
    	
    	final CountDownLatch countDownLatch  = new CountDownLatch(2);
    	
    	final Syn syn = new Syn();
    	final Lock lock = new ReentrantLock();
    	final Condition conditionA = lock.newCondition();
    	final Condition conditionB = lock.newCondition();
    	
    	final List<String> lsResult = new ArrayList<String>();

        Thread threadA = new Thread(new Runnable() {

            @Override
            public void run() {
            	List<String> lsA = new ArrayList<String>();
            	lsA.add("a");
            	lsA.add("b");
            	lsA.add("c");
            	lsA.add("d");
            	
            	for (String char1 : lsA) {
//            	while(true) {
            		try {
            			lock.lock();//获取同步器状态，如果不成功，则进入同步对象，当前线程阻塞在锁对象上
            			while(syn.getA() == 1) {
            				//条件不满足时(当前线程已经执行过了，需要等另一个线程执行后才能执行本线程)：
            				//将当前线程加入等待队列，同时修改同步器状态，唤醒阻塞在锁对象上的线程，当前线程阻塞在条件对象上。
            				conditionA.await();
            			}
            			lsResult.add(char1);//执行业务
            			System.out.println("A");//执行业务
            			Thread.sleep(1000);//模拟耗时操作
            			syn.setA(1);//修改判断条件，代表我已经执行过了，其他人可以执行别的了
            			conditionB.signal();//唤醒另一个条件对象上的线程
            		} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}finally {
            			lock.unlock();
            		}
				}
            	countDownLatch.countDown();
            }
        });
        Thread threadB = new Thread(new Runnable() {

            @Override
            public void run() {
            	List<String> lsA = new ArrayList<String>();
            	lsA.add("1");
            	lsA.add("2");
            	lsA.add("3");
            	lsA.add("4");
            	
            	for (String char1 : lsA) {
//            	while(true) {
            		try {
            			lock.lock();
            			while(syn.getA() == 0) {
            				conditionB.await();
            			}
            			lsResult.add(char1);
            			System.out.println("B");
            			Thread.sleep(1000);//模拟耗时操作
            			syn.setA(0);
            			conditionA.signal();
            		} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}finally {
            			lock.unlock();
            		}
				}
            	countDownLatch.countDown();
            }
        });
        //同时开启3 个线程
        threadA.start();
        threadB.start();
        
        try {
			countDownLatch.await();//等两个线程都执行完
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
        
        for (String result : lsResult) {
        	System.out.println(result);
		}

    }
}