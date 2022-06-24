package File;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//案例1：指定扫描具体的文件目录，删除文件名称中包含指定字符的普通文件（不包含目录文件）
public class Demo12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要扫描的文件目录:");
        String srcPath = scanner.next();
        System.out.println("请输入需要删除的包好的字符:");
        String str = scanner.next();

        File file = new File(srcPath);
        //检查输入的是否是一个目录文件
        if(!file.isDirectory()){
            //不是，直接返回
            return;
        }
        //通过一个方法来完成查找并删除的操作
        findAll(file,str);
    }

    private static void findAll(File file, String str) {
        //首先拿到当前目录下的所有文件
        File[] files = file.listFiles();
        //判断一下是否是空目录文件
        if(files == null){
            //直接返回
            return;
        }
        //遍历 files 数组中所有元素，进行递归
        for (File f : files) {
            //判断当前的 file 描述的是不是一个普通文件
            if(f.isFile()){
                //判断文件的名字是否包含指定的字符
                if(f.getName().contains(str)){
                    //删除操作
                    delete(f);
                }
            }else if(f.isDirectory()){
                //如果是一个目录文件，进行递归
                findAll(f,str);
            }
        }
    }

    private static void delete(File f) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(f.getCanonicalPath()+" 是否确认要删除这个文件 (Y/N)");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = scanner.next();
        if(s.equals("Y")||s.equals("y")){
            boolean delete = f.delete();
            if(delete){
                System.out.println("删除文件成功!!!");
            }else{
                System.out.println("删除文件失败...");
            }
        }else{
            System.out.println("删除文件操作取消》》》");
        }
    }
}
