package File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo7 {
    public static void main(String[] args) {
        try(InputStream inputStream = new FileInputStream("f:/test.txt")){
            while(true){
                //用来存放每次读取的数据
                byte[] bytes = new byte[1024];
                int read = inputStream.read(bytes);
                //判断是否读完了
                if(read == -1){
                    break;
                }
                //循环打印 bytes 中的数据
                for (int i = 0; i < read; i++) {
                    System.out.println(bytes[i]);
                }
                //可以将数据转换成一个字符串，并设置字符编码集
                String str = new String(bytes,0,read,"utf-8");
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
