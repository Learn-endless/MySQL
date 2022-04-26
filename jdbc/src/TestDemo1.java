import java.util.Comparator;
import java.util.PriorityQueue;

public class TestDemo1 {

    public static void main3(String[] args) {
        Thread t = new Thread(()-> System.out.println("hello word"));
        t.start();
    }

    public static void main2(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable()...");
            }
        });
        t.start();
    }

    public static void main1(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("hello word");
            }
        };
        t.start();
    }
}
