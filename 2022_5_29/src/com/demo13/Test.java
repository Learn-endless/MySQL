package com.demo13;

import java.util.concurrent.PriorityBlockingQueue;

//描述一个任务
class MyTimerTask implements Comparable<MyTimerTask>{
    //任务的内容
    private Runnable runnable;
    //任务开始的时间
    private long time;

    //有参构造方法,来创建任务
    public MyTimerTask(Runnable runnable,long delay){
        this.runnable = runnable;
        this.time = System.currentTimeMillis() + delay;
    }

    //获取毫秒级时间
    public long getTime(){
        return time;
    }

    //执行任务的方法
    public void run(){
        runnable.run();
    }

    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }
}

//自定义定时器
class MyTimer{
    //存放多个任务
    private PriorityBlockingQueue<MyTimerTask> queue = new PriorityBlockingQueue<>();

    //创建一个锁对象
    private Object locker = new Object();

    //使用构造方法来检测当前任务是否到达了需要执行的时间
    public MyTimer(){
        //创建一个线程
        Thread thread = new Thread(()->{
            //循环进行检测
            while(true){
                try {
                    //拿到了队首任务
                    MyTimerTask take = queue.take();
                    //获取当前时间,并进行判断
                    long curTime = System.currentTimeMillis();
                    if(curTime < take.getTime()){
                        //还没有到时间，重新放回去
                        queue.put(take);
                        //如果时间还没到，就等待当前时间和任务开始的时间的差值
                        synchronized(locker){
                            locker.wait(take.getTime()-curTime);
                        }
                    }else{
                        //时间到了,开始执行任务。
                        take.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //启动线程
        thread.start();
    }

    //注册任务
    public void schedule(Runnable runnable,long delay){
        //创建一个任务
        MyTimerTask myTimerTask = new MyTimerTask(runnable, delay);
        //将任务放到队列中
        queue.put(myTimerTask);
        //每次插入一个新任务，就需要重新检测一遍
        synchronized (locker){
            locker.notify();
        }
    }
}
public class Test {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello MyTimer...");
            }
        },3000);
        System.out.println("main...");
    }
}
