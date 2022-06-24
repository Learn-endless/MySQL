package File;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Demo10 {
    public static void main(String[] args) {
        //创建字符流对象
        try(Reader reader = new FileReader("f:/test.txt")){
            while(true){
                //使用字符数组来存储读取的数据
                char[] ch = new char[1024];
                int read = reader.read(ch);
                //返回值为 -1 时，说明读到文件末尾了
                if(read == -1){
                    break;
                }
                //循环打印每次读取的字符
                for (int i = 0; i < read; i++) {
                    System.out.println(ch[i]);
                }
                //也可以直接将读到的数据转成一个字符串
                String str = new String(ch,0,read);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
