package File;

import java.io.*;

public class Demo18 {
    public static void main(String[] args) {
        //true 表示是追加写，false/不加参数 表示不是追加写
        try(OutputStream outputStream = new FileOutputStream("f:/test.txt",true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)){

            printWriter.print("fdag");
            printWriter.println("aaaa");
            printWriter.printf("\r\n%d 等于 %d 的绝对值。",5,-5);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
