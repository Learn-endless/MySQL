package com.demo11;

//这里为了简洁就不使用泛型，默认存放的元素为 int
class MyLockingQueue{
    //使用数组来存放元素，固定大小 1000
    private int[] array = new int[1000];
    //统计当前队列中元素的多少
    private int size = 0;
    //队头和队尾
    private int head = 0;
    private int tail = 0;

    //创建一个锁对象
    private Object locker = new Object();

    //入队方法(使用 synchronized 关键字上锁)
    public void put(int value) throws InterruptedException {
        //使用同步锁，手动指定锁对象为 locker
        synchronized(locker){
            //先判断是否满了
            if(size == array.length){
                //如果队列满了，还要入队列就进行阻塞等待。
                locker.wait();
            }
            //将元素放到队列中
            array[tail] = value;
            tail++;    //队尾往后移
            //循环处理队尾
            if(tail >= array.length){
                tail = 0;
            }
            //统计元素的值加1;
            size++;
            //入队列成功，说明里面有元素了，进行通知
            locker.notify();
        }
    }

    //出队方法(使用 synchronized 关键字上锁)
    public Integer take() throws InterruptedException {
        synchronized(locker){
            //先判断队列是否为空
            if(size == 0){
                //如果队列为空了，还要出队列，也进行阻塞等待
                locker.wait();
            }
            //拿到队首元素
            int result = array[head];
            //队首往后移
            head++;
            //循环队首的处理
            if(head >= array.length){
                head = 0;
            }
            //记录减一，并返回拿到的元素
            size--;
            //出队列成功，说明当前队列不为满了，进行唤醒
            locker.notify();
            return result;
        }
    }
}

public class Test {
    //实现一个简单的生产者消费者模型
    public static void main(String[] args) {
        MyLockingQueue queue = new MyLockingQueue();

        //一个生产者线程
        Thread producer = new Thread(()->{
            int number = 1;
            while(true){
                //入队
                try {
                    //生产
                    queue.put(number);
                    System.out.println("生产了第 "+number+" 个资源");
                    number++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();

        //一个消费者线程
        Thread consumer = new Thread(()->{
            while(true){
                //入队
                try {
                    //消费
                    Integer take = queue.take();
                    System.out.println("消费了第 "+take+" 个资源");
//                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.start();
    }

    public static void main1(String[] args) throws InterruptedException {
        MyLockingQueue queue = new MyLockingQueue();
        queue.put(1);
        queue.put(2);
        queue.put(3);

        Integer take = 0;
        take = queue.take();
        System.out.println(take);
        take = queue.take();
        System.out.println(take);
        take = queue.take();
        System.out.println(take);
    }
}
