package com.demo16;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        //初始化值为0
        AtomicInteger integer = new AtomicInteger(0);

        //创建线程 t1 将 integer 自增 50000
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 50000; i++) {
                //相当于 integer++ 操作
                integer.getAndIncrement();
            }
        });

        //创建线程 t2 将 integer 再自增 50000
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 50000; i++) {
                //相当于 integer++ 操作
                integer.getAndIncrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(integer.get());
    }
}
