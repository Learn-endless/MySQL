package File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo6 {
    public static void main(String[] args) {
        //在括号里创建 输入流对象
        try(InputStream inputStream = new FileInputStream("f:/test.txt")){
            //用字节的方式循环读取文件中的数据
            while(true){
                int read = inputStream.read();
                //如果返回值为 -1，表示已经读到文件的末尾了。
                //read返回的值范围 -1 ~ 255
                if(read == -1){
                    break;
                }
                //打印一下这个字节
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
