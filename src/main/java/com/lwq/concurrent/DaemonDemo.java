package com.lwq.concurrent;

/**
 * Created by Administrator on 2019/5/16.
 */
public class DaemonDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    System.out.println(count);
                    count++;
                    Thread.yield();
                }
            }
        };
        Thread thread1 = new Thread(task);
        thread1.setName("守护线程");
        thread1.setDaemon(true);
        thread1.start();
        Thread.sleep(3000);
    }
}
