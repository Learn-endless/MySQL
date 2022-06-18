package com.demo18;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        //创建一把 ReentrantLock 锁
        ReentrantLock locker = new ReentrantLock();

        //上锁
        locker.lock();
        try{
            //工作内容...
        }finally {
            //通过 finally，使它即使在工作时出现异常也会进行解锁操作
            locker.unlock();
        }
    }
}
