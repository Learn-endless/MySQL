package File;

import java.io.*;
import java.util.Scanner;

//复习案例2
public class Demo16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入拷贝源:");
        String srcPath = scanner.next();
        System.out.println("请输入目的地路径:");
        String destPath = scanner.next();

        //1.检查拷贝源是不是一个普通文件路径
        File file = new File(srcPath);
        if(!file.isFile()){
            System.out.println("拷贝源路径输入有误!");
            return;
        }

        //2.读取文件内容并写入到目的地
        readToWriter(file,destPath);
    }

    private static void readToWriter(File file,String destPath) {
        //1.先读后写
        try(InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = new FileOutputStream(destPath)){

            byte[] bytes = new byte[1024];
            while(true){
                int len = inputStream.read(bytes);
                //检查是否读到了文件末尾
                if(len == -1){
                    break;
                }
                //将内容写到目的地
                outputStream.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
