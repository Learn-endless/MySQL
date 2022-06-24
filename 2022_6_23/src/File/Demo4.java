package File;

import java.io.File;

public class Demo4 {
    public static void main(String[] args) {
        File file1 = new File("./aaa");
        File file2 = new File("./zzz");

        //将 file1 描述的文件 重命名 为 file2描述的文件
        boolean b = file1.renameTo(file2);
        System.out.println(b);
    }
}
