package com.demo3;

public class TestDemo {
    //线程不安全问题 —— 针对变量的操作不是原子的
    private static int count = 0;
    public synchronized static void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                add();
            }
        });

        Thread t2 = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                add();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }
}
