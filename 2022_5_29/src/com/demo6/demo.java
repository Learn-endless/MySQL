package com.demo6;

public class demo {

    public int count = 0;
    //使用 synchronized 两次加锁
    synchronized public void func(){
        synchronized (this){
            count++;
        }
    }
    public static void main(String[] args) {
        demo demo = new demo();
        demo.func();

        System.out.println(demo.count);
    }
}
