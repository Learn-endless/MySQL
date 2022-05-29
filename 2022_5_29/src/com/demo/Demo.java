package com.demo;

//线程安全的单例模式
//单例模式 - 饿汉模式
class SingleTon {
    //实例化一个静态的对象，它是属于类的，只存在一份
    private static SingleTon singleTon = new SingleTon();
    //防止在类外有线程实例化,将它的构造方法私有化
    private SingleTon(){};
    //提供一个接口，让使用着可以拿到这个实例
    public static SingleTon getInstance(){
        return singleTon;
    }
}

public class Demo {
    public static void main(String[] args) {
        //拿到单例对象
        SingleTon instance = SingleTon.getInstance();
    }
}
