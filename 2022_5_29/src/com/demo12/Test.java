package com.demo12;

import java.util.Timer;
import java.util.TimerTask;

public class Test {
    public static void main(String[] args) {
        //创建一个Timer类型的实例
        Timer timer = new Timer();
        //调用它的schedule方法
        // 该方法有两个参数：
        // 1. 需要完成的任务是什么，这个参数的类型为TimerTask类型，它是一个抽象类，实现了Runnable接口。
        // 2. 多少时间间隔后开始执行任务。
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是一个TimerTask任务!!!");
            }
        },3000);
        System.out.println("main...");
    }
}
