package com.lwq.concurrent;

/**
 * Created by Administrator on 2019/5/16.
 */
public class ThreadStateMain {
    public static void main(String[] args) {
        ThreadStateTask myTask = new ThreadStateTask();
        myTask.start();
    }
}
