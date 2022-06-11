package com.demo14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        //创建一个固定线程数目的线程池,参数指定线程个数
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        //创建一个可以自动扩容的线程池,根据任务量自动扩容
//        Executors.newCachedThreadPool();
//        //创建一个只有一个线程的线程池
//        Executors.newSingleThreadExecutor();
//        //创建一个带有定时器功能的线程池
//        Executors.newScheduledThreadPool();

        //循环注册52个任务
        for(int i = 0; i < 50; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("正在工作中...");
                }
            });
        }
    }
}
