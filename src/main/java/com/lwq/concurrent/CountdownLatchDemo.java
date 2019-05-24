package com.lwq.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/5/17.
 * CountdownLatch类似于计数器，用于一个线程等待多个线程完成任务
 */
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int count = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
//                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    try {
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "完成任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + "等待其他线程完成任务");
        countDownLatch.await();
        System.out.println("其他线程完成任务," + Thread.currentThread().getName() + "继续执行");

    }
}
