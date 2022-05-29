package com.demo1;

//单例模式 - 懒汉模式
class SingleTon{
    //还是使用static关键字，将它编程属于类的
    private volatile static SingleTon singleTon = null;
    //将它的构造方法私有化
    private SingleTon(){};
    //提供一个接口，可以在外面拿到实例对象
    public static SingleTon getInstance(){

        //虽然使用了双重if+synchronized但是还有问题：在第一个if中可能因为编译器优化，出现内存不可见问题
        //解决：给 singleTon 加上 volatile 关键字。

        //还有问题，下方的代码中出现线程安全问题的是部分情况下(还没有实例的情况)，直接加synchronized属于无脑上锁，降低效率
        //优化：在外面进行一个判断,这个是来判断当前是否有对象了
        if(singleTon == null){
            //在这里会出现线程安全问题，所以需要上锁
            synchronized(SingleTon.class){     //锁对象就是SingleTon，static修饰的属性是类的
                if(singleTon == null){
                    //创建对象
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }
}
public class Demo1 {
    public static void main(String[] args) {
        //拿到单例对象
        SingleTon instance = SingleTon.getInstance();
    }
}
