public class TestDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(true){
                System.out.println("hello thread1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread1...");
        t1.start();
        Thread t2 = new Thread(()->{
            while(true){
                System.out.println("hello thread2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread2...");   //可以给线程起别名,这样在调试的时候能更好的区分
        t2.start();
    }
}
