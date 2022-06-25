package File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Demo19 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        try(InputStream inputStream = new FileInputStream("f:/test.txt");
            Scanner scanner = new Scanner(inputStream,"utf-8")){

            //一行一行的读取数据
            while(scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine());
                //每读取一行就添加一个换行符
                stringBuilder.append("\r\n");
            }

            //打印出读到的内容
            System.out.println(stringBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
