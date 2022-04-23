import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        //创建数据源
        DataSource dataSource = new MysqlDataSource();
        //设置url
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        //设置 用户名
        ((MysqlDataSource)dataSource).setUser("root");
        //设置 密码
        ((MysqlDataSource)dataSource).setPassword("1234");

        //获取连接  (如果连接失败,就会向上抛出一个异常)
        Connection connection = dataSource.getConnection();

        //控制台输入需要插入的 id name credit
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入id:");
        int id = scanner.nextInt();
        System.out.println("请输入name:");
        String name = scanner.next();
        System.out.println("请输入credit:");
        int credit = scanner.nextInt();

        //创建sql语句 (注意在 java 中操作数据库时,不用加上 ;)
        String sql = "insert into subject values (?,?,?)";
        //将String类型的 sql 语句 转换成 一个 "语句对象";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //动态的插入数据
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3,credit);
        System.out.println(preparedStatement);

        //执行sql语句 (会返回一个int,记录被影响数据的行数)
        int result = preparedStatement.executeUpdate();
        System.out.println(result);

        //释放资源 (先创建的后释放,后创建的先释放)
        preparedStatement.close();

        connection.close();

    }
}
