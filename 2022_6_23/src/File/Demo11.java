package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo11 {
    public static void main(String[] args) {
        //和 OutputStream 的实现类一样，如果不多加一个 true 参数，则表示打开文件先清空然后写
        try(Writer writer = new FileWriter("f:/test.txt")){
            //写一个字符
            writer.write(97);

            //写一个字符串
            writer.write("hello word");

            //写一个字符数组
            char[] ch = new char[]{'a','a','a'};
            writer.write(ch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
