public class TestDemo5 {
    public static void main(String[] args) {
        new Thread("t1线程"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(this.getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                //System.out.println(this.getName);   //报错   Runnable 只是一个任务
            }
        },"t2线程").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            //System.out.println(this.getName);       //报错   同理
        },"t3线程").start();
    }
}
