package File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo5 {
    public static void main(String[] args) throws FileNotFoundException {
        //提出来定义,好方便在 finally 里使用
        InputStream inputStream = null;
        try {
            //创建流对象
            inputStream = new FileInputStream("f:/test.txt");
            //一个一个字节的读取文件
            while(true){
                int read = inputStream.read();
                if(read == -1){
                    break;
                }
                //打印一下读取到的字节（字节所对应的int）
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //写在 finally 里后，即使触发了异常，也会释放资源
            try {
                //断言一下 inputStream 是否为 null
                assert inputStream != null;
                //还要记得释放资源
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
