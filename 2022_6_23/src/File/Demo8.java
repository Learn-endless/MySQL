package File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Demo8 {
    public static void main(String[] args) {
        //这样创建实例的话，每次打开文件后，就会将文件里的内容给清空。
        try(OutputStream outputStream = new FileOutputStream("f:/test.txt")){
            //想要追加写，就需要在 路径 后每添加一个参数 true，表示追加写
            //OutputStream outputStream = new FileOutputStream("f:/test.txt",true);

            //一个字节一个字节的写
            outputStream.write(97);
            outputStream.write(98);
            outputStream.write(99);

            //一次写入多个字节
            byte[] bytes = new byte[]{100,101,102};
            outputStream.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
