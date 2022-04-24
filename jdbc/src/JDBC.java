import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC {

    /**
     * 向数据库中插入数据 : insert
     * @param id      学号
     * @param name    姓名
     * @param credit  课程号
     * @return        插入结果
     * @throws SQLException  插入是否成功
     */
    public static int insertMySQL(int id, String name, int credit) throws SQLException {
        //创建数据源
        DataSource dataSource = new MysqlDataSource();
        //设置url
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        //设置user
        ((MysqlDataSource)dataSource).setUser("root");
        //设置password
        ((MysqlDataSource)dataSource).setPassword("1234");

        //获取连接数据库
        Connection connection = dataSource.getConnection();

        //创建 sql 语句
        String sql = "insert into subject values (?,?,?)";

        //将其转换成一个 "语句对象"
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //确定需要插入的值
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3,credit);

        //打印一下 preparedStatement 对象中的 sql 语句
        System.out.println(preparedStatement);

        //执行 sql 语句
        int result = preparedStatement.executeUpdate();

        //释放空间
        preparedStatement.close();
        connection.close();

        //返回提示 : 影响的行数
        return result;
    }

    public static int deleteMySQL(int id) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

        String sql = "delete from subject where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,id);
        System.out.println(statement);

        int result = statement.executeUpdate();

        statement.close();
        connection.close();

        return result;
    }

    public static int updateMySQL(int id,String newName) throws SQLException {
        DataSource dataSource = new MysqlDataSource();

        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

        String sql = "update subject set name = ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,id);

        System.out.println(preparedStatement);

        int result = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return result;
    }

    public static void selectMySQL() throws SQLException {
        DataSource dataSource = new MysqlDataSource();

        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

        String sql = "select * from subject";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int credit = resultSet.getInt("credit");
            System.out.println("id="+id+",name="+name+",credit="+credit);
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入id>>");
        int id  = scanner.nextInt();
//        System.out.println("请输入name>>");
//        String name = scanner.next();
//        System.out.println("请输入credit>>");
//        int credit = scanner.nextInt();

        //将输入的值添加到数据库中
        try {
//            int result = insertMySQL(id, name, credit);
//            int result = updateMySQL(id,"盘古大神");
//            int result = deleteMySQL(id);
//            System.out.println(result);
            selectMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("插入失败!!!");
        }
    }
}
