package com.yonyou.base.TheadLocal;
/**
 * 线程同步机制是多个线程共享同一个变量，而ThreadLocal是为每个线程创建一个单独的变量副本，每个线程都可以改变自己的变量副本而不影响其它线程所对应的副本
 * 该类提供了线程局部(thread-local)变量。这些变量不同于它们的普通对应物，
 * 因为访问某个变量（通过其 get 或 set 方法）的每个线程都有自己的局部变量，
 * 它独立于变量的初始化副本。ThreadLocal实例通常是类中的 private static 字段，
 * 它们希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。
* @Description:  
* @author: lkl 
* @date: 2019年10月18日 上午11:24:46
 */
public class SeqCount {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int nextSeq() {
        seqCount.set(seqCount.get() +1);
        return seqCount.get();
    }

    public static void main(String [] args) {
        SeqCount seqCount = new SeqCount();

        SeqThread seqThread1 = new SeqThread(seqCount);
        SeqThread seqThread2 = new SeqThread(seqCount);
        SeqThread seqThread3 = new SeqThread(seqCount);
        SeqThread seqThread4 = new SeqThread(seqCount);

        seqThread1.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();
    }

    public static class SeqThread extends Thread {

        private SeqCount seqCount;
        public SeqThread(SeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(Thread.currentThread().getName()+" seqCount:"+seqCount.nextSeq());
            }
        }
    }
 }
