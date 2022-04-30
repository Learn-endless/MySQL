import java.util.Scanner;

public class TestDemo6 {
    /**
     * 线程不安全问题：
     * 1. 线程是抢占性执行的，线程间的调度充满了随机性。 （线程不安全问题的根本原因）
     * 2. 多个线程修改同一个变量。（多个线程修改不同的变量没问题，多个线程读取同一个变量也没问题）
     * 3. 多线程的操作不是原子的：比如有些操作是多条指令构成，这就不是原子的  解决：通过上锁将多条指令打包成一个操作
     * 4. 内存不可见问题：一个线程不停的读取一个内存中的数据，另一个线程在合适的时候对该数据进行修改。这时由于编译器的优化，所以读数据的线程感知不到内存中数据的变化。
     */

    public static int count = 0;

    synchronized public static void add(){   //通过加 synchronized(锁) 既可以解决 问题2 问题3 也可以解决 问题 4
        count++;
    }
    /**
     * 问题 2 和 问题 3  ： 多个线程修改同一个变量导致线程不安全问题。（根本原因为操作的指令不是原子的）
     */
    public static void main1(String[] args) throws InterruptedException {
        //线程1 进行 加1操作 ，执行 50000 次
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                add();
            }
        });
        //线程2 进行 加1操作 ，执行 50000 次
        Thread t2 = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                add();
            }
        });

        t1.start();
        t2.start();

        //等 线程1 和 线程2 都执行完毕，再来执行 main线程
        t1.join();
        t2.join();
        System.out.println(count);
    }

    /**
     * 问题 4 ： 内存不可见问题实例
     */
    public static int isTrue = 0;

    synchronized public static int getIsTrue(){
        return isTrue;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(getIsTrue() == 0){

            }
            System.out.println("线程t1结束》》》");
        });
        t1.start();

        System.out.println("请更改isTrue的值：");
        Scanner scanner = new Scanner(System.in);
        isTrue  = scanner.nextInt();
        System.out.println("main线程执行完毕！");
    }
}
