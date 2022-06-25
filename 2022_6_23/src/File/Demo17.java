package File;

import java.io.*;
import java.util.Scanner;

//复习案例3
public class Demo17 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入扫描路径:");
        String srcPath = scanner.next();
        System.out.println("请输入内容关键字:");
        String word = scanner.next();

        //1.检查扫描路径是否有误
        File file = new File(srcPath);
        if(!file.isDirectory()){
            System.out.println("扫描路劲有误!");
            return;
        }

        //2.循环遍历该路径下所有的文件内容，并进行判定
        findAll(file,word);
    }

    private static void findAll(File file, String word) throws IOException {
        //1.拿到当前目录下所有的文件
        File[] files = file.listFiles();
        if(files == null){
            return;
        }

        //2.判断相应的情况，并做出对应的操作
        for (File f:files) {
            if(f.isFile()){
                //3.检查其中内容是否包含关键字
                boolean flag = check(f, word);
                if(flag){
                    System.out.println(f.getCanonicalPath());
                }
            }else if(f.isDirectory()){
                findAll(f,word);
            }
        }
    }

    private static boolean check(File f, String word) {
        StringBuilder stringBuilder = new StringBuilder();
        //1.读取文件中的内容到 stringBuilder 中，并进行判断
        try(InputStream inputStream = new FileInputStream(f);
            Scanner scanner = new Scanner(inputStream,"utf-8")){

            //一行一行的读取
            while(scanner.hasNextLine()){
                //添加每一行的内容
                stringBuilder.append(scanner.nextLine());
                //添加换行符
                stringBuilder.append("\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.indexOf(word) != -1;
    }
}

