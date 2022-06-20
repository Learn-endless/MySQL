package com.demo20;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        //表示有 10 个线程一起完成一个游戏的下载（每个下载部分）
        CountDownLatch countDownLatch = new CountDownLatch(10);

        //循环创建10个线程，并分配任务
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(()->{
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"部分资源下载完成...");
                    //表示游戏的该部分资源下载完成
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            //启动线程
            t.start();
        }

        //只要有一个线程没有下载完自己负责的资源，游戏就没有下载完成。（也就是在这里进行阻塞）
        countDownLatch.await();
        System.out.println("游戏下载完成!");
    }
}
