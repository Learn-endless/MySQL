package File;

import java.io.*;
import java.util.Scanner;

//案例2：普通文件的复制
public class Demo13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入被复制文件的路径:");
        String srcPath = scanner.next();
        System.out.println("请输入复制到的目的地:");
        String destPath = scanner.next();

        //判断源文件是否有问题
        File file = new File(srcPath);
        if(!file.isFile()){
            System.out.println("源路径输入有误!!!");
            return;
        }

        //先读取文件信息，然后写入目的地
        try(InputStream input = new FileInputStream(srcPath);
            OutputStream output = new FileOutputStream(destPath)){

            //存放读取的数据
            byte[] bytes = new byte[1024];
            //循环操作
            while(true){
                int read = input.read(bytes);
                //判断文件是否读完了
                if(read == -1){
                    break;
                }
                //将数据写入目的地
                output.write(bytes,0,read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
