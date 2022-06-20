package com.demo19;

import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        //可以指定可用资源的个数
        Semaphore semaphore = new Semaphore(4);
//        //申请资源
//        semaphore.acquire();
//        //释放资源
//        semaphore.release();

        //也可以指定申请/释放的资源个数
//        semaphore.acquire(4);
//        semaphore.release(4);

        //如果可用资源为 0 时继续申请资源，就会进行阻塞
        semaphore.acquire();
        System.out.println("申请资源1");
        semaphore.acquire();
        System.out.println("申请资源2");
        semaphore.acquire();
        System.out.println("申请资源3");
        semaphore.acquire();
        System.out.println("申请资源4");
        semaphore.acquire();
        System.out.println("申请资源5");
    }
}
