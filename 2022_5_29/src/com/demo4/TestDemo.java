package com.demo4;

import java.util.Scanner;

public class TestDemo {
    public volatile static int isFlag = 0;

    //内存可见性问题
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(isFlag == 0){

            }
            System.out.println("t线程结束了...");
        });
        //开启t线程
        t.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请修改isFlag的值:");
        isFlag = scanner.nextInt();
        System.out.println("isFlag的值已经被修改...");
    }
}
