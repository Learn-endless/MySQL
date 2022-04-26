import java.util.Date;

public class TestDemo2 {

    public static long count = 100_0000_0000L;

    public static void serizal(){
        long beg = System.currentTimeMillis();
        long sum1 = 0;
        for(long i = 0; i < count; i++){
            sum1++;
        }
        long sum2 = 0;
        for(long i = 0; i < count; i++){
            sum2++;
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间:"+(end-beg)+"ms");
    }

    public static void func() throws InterruptedException {
        long beg = System.currentTimeMillis();
        Thread t1 = new Thread(()->{
            long sum1 = 0;
            for(long i = 0; i < count; i++){
                sum1++;
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            long sum2 = 0;
            for(long i = 0; i < count; i++){
                sum2++;
            }
        });
        t2.start();

        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("消耗时间:"+(end-beg)+"ms");
    }
    public static void main(String[] args) throws InterruptedException {
//        serizal();
        func();
    }
}
