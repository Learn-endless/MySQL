package com.demo2;

public class TestDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("线程一...");
        });
        System.out.println(t.getState());    //获取t线程还没启动时的状态

        t.start();                           //在内存中真正创建线程并启动
        System.out.println(t.getState());    //在此查看t线程的状态

        t.join();       //main线程等待t线程执行完毕
        System.out.println(t.getState());
    }

    public static void main1(String[] args) {
        for(Thread.State res :Thread.State.values()){
            System.out.println(res);
        }
    }
}
