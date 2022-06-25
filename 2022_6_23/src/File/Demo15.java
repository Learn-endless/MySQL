package File;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//复习案例1
public class Demo15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要扫描的路径:");
        String srcPath = scanner.next();
        System.out.println("请输入需要查找的文件名中的关键字:");
        String word = scanner.next();

        //1.检查路径是否正确
        File file = new File(srcPath);
        if(!file.isDirectory()){
            System.out.println("扫描路径有误!");
            return;
        }

        //2.遍历所有的文件并进行查找删除操作
        findAll(file,word);
    }

    private static void findAll(File file, String word) {
        //1.先拿到文件列表,并检查是否为空
        File[] files = file.listFiles();
        if(files == null){
            return;
        }

        //2.判断是否是普通文件，然后执行相应的操作
        for (File f : files) {
            if(f.isFile()){
                if(f.getName().contains(word)){
                    //3.删除操作
                    delete(f);
                }
            }else if(f.isDirectory()){
                findAll(f,word);
            }
        }
    }

    private static void delete(File f) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(f.getCanonicalPath()+" 是否确认删除该文件(Y/N):");
            String inputStr = scanner.next();
            if(inputStr.equals("Y")||inputStr.equals("y")){
                boolean delete = f.delete();
                if(delete){
                    System.out.println("删除成功!");
                }else{
                    System.out.println("删除失败!!!");
                }
            }else{
                System.out.println("取消删除!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
