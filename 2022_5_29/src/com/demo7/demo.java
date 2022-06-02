package com.demo7;

class A implements Runnable{
    @Override
    public void run() {
        synchronized (this){
            System.out.println("wait开始了...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait结束了...");
        }
    }
}

public class demo {
    public static void main(String[] args) throws InterruptedException {
        //创建一个 A 实例
        A a = new A();
        Thread t = new Thread(a);
        t.start();

        //5秒后进行notify通知
        Thread.sleep(5000);
        synchronized (a){
            a.notify();
            System.out.println("main线程进行了通知...");
        }
    }
}
