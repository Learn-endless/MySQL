package File;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Demo9 {
    public static void main(String[] args) {
        try(Reader reader = new FileReader("f:/test.txt")){
            //每次读取一个字符,返回实际读取的字符的ASCII值。读完了返回 -1
            while(true){
                int read = reader.read();
                //判断是否读到了文件末尾
                if(read == -1){
                    break;
                }
                //打印读到的数据
                System.out.println((char)read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
