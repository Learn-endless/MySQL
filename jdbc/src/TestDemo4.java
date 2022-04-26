public class TestDemo4 {
    /**
     * 通过标识符可以使同一进程内的线程提前结束
     */
    private static boolean isStop = false;

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(!isStop){
                System.out.println("hello Thread...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        //让main方法线程 5 秒内不执行
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //改变标志位
        isStop = true;                  //表示结束
        System.out.println("结束...");
    }
}
