package com.demo10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        //java中的阻塞队列(可以通过泛型来指定所要入队的元素的类型)
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        //如果使用数组的形式来实现阻塞队列，那么在初始化的时候就需要指定大小
        BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(10);

        //带阻塞的入队方法（需要声明异常）
        queue.put("hello");
        //出队方法
        String take = queue.take();
        System.out.println(take);
    }
}
