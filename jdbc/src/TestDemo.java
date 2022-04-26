public class TestDemo {
    /**
     * 通过 Thread.interrupted() 静态方法 或 Thread.currentThread().isInterrupted() 实例方法来中断线程
     */
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("hello Thread...");
                try {
                    Thread.sleep(1000);      //在处于 休眠 时,执行了 interrupt()方法,就会被 强制唤醒 , 并 抛异常,然后继续循环
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("做一些收尾的工作...");
                    break;   //退出循环,结束线程
                }
            }
        });
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }
}
