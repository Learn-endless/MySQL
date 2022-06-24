package File;

import java.io.*;
import java.util.Scanner;

//案例3：扫描指定文件，并找到内容中有指定关键字的文件路径
public class Demo14 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要扫描的路径:");
        String srcPath = scanner.next();
        System.out.println("请输入关键字:");
        String word = scanner.next();

        //检查输入的路径是否合法
        File file = new File(srcPath);
        if(!file.isDirectory()){
            System.out.println("扫描路径非法!");
            return;
        }

        //遍历所有文件并查找
        findAll(file,word);
    }

    //递归寻找所有的普通文件
    private static void findAll(File file, String word) throws IOException {
        //拿到当前目录下所有的文件
        File[] files = file.listFiles();
        //检查是否是一个空目录文件
        if(files == null){
            return;
        }

        //遍历所有的目录文件
        for (File f:files) {
            //判断是否是普通文件
            if(f.isFile()){
                //通过 check 方法来判断该文件中是否有关键字
                if(check(f,word)) {
                    System.out.println(f.getCanonicalPath());
                }
            }else if(f.isDirectory()){
                //是目录文件后，进行递归
                findAll(f,word);
            }
        }
    }

    //检查关键字是否在当前的普通文件中
    private static boolean check(File f, String word) {
        //用来存放当前文件中的数据
        StringBuilder stringBuilder = new StringBuilder();
        try(Reader reader = new FileReader(f)){

            char[] chars = new char[1024];
            while(true){
                int read = reader.read(chars);
                //判断是否到达了文件末尾
                if(read == -1){
                    break;
                }
                //将每次读取的数据添加到 StringBuilder 中
                stringBuilder.append(chars,0,read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果关键字能在字符串中找到，返回 true，反之则返回 false
        return stringBuilder.indexOf(word) != -1;
    }
}
