package com.demo5;

public class demo {
    public static void main(String[] args) {
        //修饰demo类对象
        synchronized (demo.class){

        }
    }
    public void func(){
        //修饰当前demo实例对象
        synchronized (this){

        }
    }
}
