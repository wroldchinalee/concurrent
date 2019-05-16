package com.lwq.concurrent;

/**
 * java线程状态转换
 * 在java中线程分为6种状态，分别为:new,runnable,waiting,time_waiting,blocked,terminated
 */
public class ThreadStateTask {
    private Object lock = new Object();

    public void start() {
        // 我们观察线程1的状态
        Task1 task1 = new Task1();
        Thread thread1 = new Thread(task1);
        thread1.setName("Thread1");
        // 1.新建线程还没有启动，状态为new
        System.out.println(thread1.getName() + " state:" + thread1.getState());
        thread1.start();
        // 2.线程启动，状态为runnable
        System.out.println(thread1.getName() + " state:" + thread1.getState());

        // 创建线程2
        Task2 task2 = new Task2();
        Thread thread2 = new Thread(task2);
        thread2.setName("Thread2");
        thread2.start();
        try {
            // 主线程休眠1秒，这时候线程1也在休眠，状态为timed_waiting
            Thread.sleep(1000);
            System.out.println(thread1.getName() + " state:" + thread1.getState());
            // 线程1已经休眠结束，开始获取lock，但是线程2现在持有lock，所以状态为blocked
            Thread.sleep(2500);
            System.out.println(thread1.getName() + " state:" + thread1.getState());
            // 线程1获取锁之后调用了wait方法，进入无限等待状态waiting
            Thread.sleep(1000);
            System.out.println(thread1.getName() + " state:" + thread1.getState());
            // 主线程等待线程2运行完成
            thread2.join();
            //  线程1被线程2的notifyAll方法唤醒，线程1继续执行，后面没有代码了，结束，进入terminated状态
            System.out.println(thread1.getName() + " state:" + thread1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class Task1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                synchronized (lock){
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }


    class Task2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                synchronized (lock){
                    Thread.sleep(2000);
                }
                Thread.sleep(1500);
                synchronized (lock){
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
