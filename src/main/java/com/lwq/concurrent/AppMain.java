package com.lwq.concurrent;

/**
 * Created by Administrator on 2019/5/16.
 */
public class AppMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadStateTask myTask = new ThreadStateTask();
        myTask.start();
    }
}
