package com.demo9;

//单例模式 — 懒汉模式
public class Test {
    //给一个属于类的字段存放对象的引用,加上 volatile 关键字，解决内存可见性问题
    private volatile static Test test = null;   //刚开始没有人要使用它
    //构造方法私有化，不让其在类外能实例化对象
    private Test(){};
    //给一个公共的接口，让其他线程能获取单利对象
    public static Test getTest(){
        //判断是否是第一次调用
        if(test == null){
            synchronized(Test.class){ //因为这个方法是静态的，故可以直接指定锁对象为 Test.class
                //首先判断是否是第一次调用
                if(test == null){
                    //创建对象
                    test = new Test();
                }
            }
        }
        return test;
    }

    public static void main(String[] args) {
        //和饿汉模式一样的使用方法
        Test test = Test.getTest();
    }
}
