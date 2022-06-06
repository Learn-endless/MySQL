package com.demo8;

//单例模式 — 饿汉模式
public class Test {
    //在类加载的时候创建唯一的对象
    private static Test test= new Test();
    //将无参构造方法设置为私有的，不允许在类外调用
    private Test(){};
    //给一个公共的接口，用来在类外获取唯一的对象
    public static Test getTest(){
        return test;
    }

    public static void main(String[] args) {
        //拿到了那个单例对象
        Test test = Test.getTest();
    }
}
