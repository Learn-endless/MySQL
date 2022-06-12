package com.demo15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//自定义创建一个线程池
class MyThreadPool{
    //1.描述一个任务，直接使用 Runnable
    //2.组织一些任务，使用阻塞队列
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    //3.具体的描述一个线程
    static class MyThread extends Thread{
        private BlockingQueue<Runnable> queue = null;

        public MyThread(BlockingQueue<Runnable> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            //每个线程的工作内容：从任务队列中循环拿任务，然后进行运行
            while(true){
                //由于这里我们需要使用 MyThreadPool 类中的 queue 任务队列
                //所以使用构造方法传参的方式来解决
                try {
                    //队列为空，阻塞
                    Runnable take = queue.take();
                    take.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //4.组织多个线程
    private List<Thread> list = new ArrayList<>();
    //提供一个构造方法来指定初始化线程的个数
    public MyThreadPool(int number){
        //创建线程 -> 启动线程 -> 添加到线程数组中
        MyThread myThread = new MyThread(queue);
        myThread.start();
        list.add(myThread);
    }

    //5.提供一个submit方法来让程序员可以注测任务
    public void submit(Runnable runnable){
        //将任务添加到任务队列中
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//进行自定义线程池的测试
public class Test {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(10);
        for (int i = 0; i < 50; i++) {
            myThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello word...");
                }
            });
        }
    }
}
