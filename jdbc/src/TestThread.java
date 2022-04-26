
class A extends Thread{
    @Override
    public void run(){
        int i = 0;
        while(i < 10){
            System.out.println("A="+i);
            i++;
        }
    }
}
class B extends Thread{
    @Override
    public void run(){
        int i = 0;
        while(i < 10){
            System.out.println("B="+i);
            i++;
        }
    }
}

public class TestThread {
    public static void main(String[] args) {
        Thread t1 = new A();
        Thread t2 = new B();
        t1.start();
        t2.start();
    }
}
