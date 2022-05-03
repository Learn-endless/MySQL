import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.javaws.jnl.RContentDesc;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {
    public static void main1(String[] args) throws SQLException {
        //创建数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        //与数据库建立连接
        Connection connection = dataSource.getConnection();

        //创建sql语句
        String sql = "insert into subject values (200,'SpringMVC',3)";
        //将String类型的sql转换为一条”语句对象“
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println(statement); //打印一下sql语句的内容

        //执行sql语句
        int result = statement.executeUpdate();
        System.out.println(result);    //打印一下被影响的行数

        //释放资源  - 先建后放，后建先放
        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }

    public static void main2(String[] args) throws SQLException {
        //设置数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        //与数据库建立连接
        Connection connection = dataSource.getConnection();

        //创建sql语句(查) 这里写 sql 语句不用写分号
        String sql = "select * from subject";
        //装换成 语句对象
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println(statement);      //打印一下sql语句，方便出错时排错

        //执行sql(查操作是使用Query),同时返回的是一个ResultSet类型的结果集
        ResultSet resultSet = statement.executeQuery();

        //遍历 结果集
        while(resultSet.next()){
            //可以使用字段名的方式来获取
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int credit = resultSet.getInt("credit");
            //也可以使用 下标的方式来获取，注意这里的下标是从 1开始
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            int credit = resultSet.getInt(3);

            //将每一行的内容打印出来
            System.out.println("id="+id+"  name="+name+"  credit="+credit);
        }

        //释放掉资源（先创建的后释放，后创建的先释放）
        if(resultSet != null){
            resultSet.close();
        }
        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }

    public static void main3(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入id、name、credit");
        int id = sc.nextInt();
        String name = sc.next();
        int credit = sc.nextInt();

        String sql = "insert into subject values (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);        //注意这里的下标也是从1开始的
        statement.setString(2,name);
        statement.setInt(3,credit);
        System.out.println(statement);

        int result = statement.executeUpdate();
        System.out.println(result);

        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要查询的id:");
        int id = sc.nextInt();

        String sql = "select * from subject where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        System.out.println(statement);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int idSubject = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int credit = resultSet.getInt(3);

            System.out.println("id:"+idSubject+"  name:"+name+"  credit:"+credit);
        }

        if(resultSet != null){
            resultSet.close();
        }
        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
