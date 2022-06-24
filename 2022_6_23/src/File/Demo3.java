package File;

import java.io.File;
import java.util.Arrays;

public class Demo3 {
    public static void main(String[] args) {
//        File file = new File("./aaa");

        File file = new File("./aaa/bbb/ccc/ddd");

//        //创建一级目录
//        boolean mkdir = file.mkdir();
//        System.out.println(mkdir);

        //可以一下创建多级目录
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);

        //拿到当前目录下的所有文件名
        File file1 = new File("./");
        String[] list = file1.list();
        System.out.println(Arrays.toString(list));

        System.out.println("============================================");
        //效果一样，就是返回类型不同 为 File 类型
        File[] files = file1.listFiles();
        System.out.println(Arrays.toString(files));
    }
}
