package File;

import java.io.File;
import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) throws IOException {
        //为了好观察效果，这里使用 相对路径
        File file = new File("./test.txt");

        //返回 file 的父目录文件路径
        String parent = file.getParent();
        System.out.println(parent);

        //返回 file 的纯文件名称
        String name = file.getName();
        System.out.println(name);

        //获取 file 的文件路径
        String path = file.getPath();
        System.out.println(path);

        //获取 file 的绝对路径
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        //获取 file 对象修饰过的绝对路径
        String canonicalPath = file.getCanonicalPath(); //有 IO 异常
        System.out.println(canonicalPath);

        //判断 file 对象描述的文件是否存在
        boolean exists = file.exists();
        System.out.println(exists); //我并没有在 当前路径 下创建一个 test.txt 的文件，所以值应给为 false

        //判断当前 file 对对象描述的文件是否是一个目录文件
        boolean directory = file.isDirectory();
        System.out.println(directory);

        //判断当前文件是否是一个 普通文件
        boolean file1 = file.isFile();
        System.out.println(file1);
    }
}
