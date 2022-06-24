package File;

import java.io.File;
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("f:/test.txt");

        //根据 file 对象创建一个 空文件 ，成功便返回 true，反之则是 false
        boolean newFile = file.createNewFile();
        System.out.println(newFile);

        //删除 file 对象路径下的文件，成功 true，反之则是 false
        //注意 该方法是 立即删除
        boolean delete = file.delete();
        System.out.println(delete);

        //这个方法也是删除文件 ，但它是 程序退出之后再删除
        file.deleteOnExit();
    }
}
