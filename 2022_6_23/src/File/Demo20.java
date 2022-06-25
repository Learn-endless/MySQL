package File;

import java.util.Scanner;

public class Demo20 {

    public static int isFlag = 0;

    public static Object object = new Object();
    //内存可见性问题
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            //t线程一直读取isFlag的值，然后与0进行判断
            synchronized (object) {
                while (true) {
                    if (isFlag == 0) {

                    } else
                        break;
                }
            }
            //走到这里，说明t线程感知到了isFlag的变化，t线程结束
            System.out.println("t线程结束了...");
        });
        //开启t线程
        t.start();

        //通过main线程来修改isFlag的值，观察t线程能否感知到
        Scanner scanner = new Scanner(System.in);
        System.out.println("请修改isFlag的值:");
        isFlag = scanner.nextInt();
        System.out.println("isFlag的值已经被修改...");
    }
}
