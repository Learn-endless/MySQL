package com.demo17;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用 callable 来描述一个任务的具体内容
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i <=1000 ; i++) {
                    sum += i;
                }
                return sum;
            }
        };

        //每个 FutureTask 可以对应一个 Callable 任务。这样我们就可以通过 FutureTask 来获取我们所需要的 Callable 任务的返回结果
        FutureTask<Integer> task = new FutureTask<>(callable);

        //将 FutureTask 传给 Thread，并启动线程
        Thread thread = new Thread(task);
        thread.start();

        //通过 FutureTask 的 get 方法来获取对应的 Callable 任务的返回值。
        //注意如果 Callable 任务没有执行完，则 get 就会进行阻塞，直到任务执行完毕，才会真正执行 get 方法，拿到具体的返回值。
        System.out.println(task.get());
    }
}
