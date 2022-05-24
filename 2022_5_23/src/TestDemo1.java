import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo1 {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/tttttt?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

//        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String format = spf.format(date);
//        System.out.println(format);
//
//        String sql = "insert into user values(5,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setObject(1,format);

        String sql = "select * from user where id = 5";
        PreparedStatement statement = connection.prepareStatement(sql);

//        int result = statement.executeUpdate();
//        System.out.println(result);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Object date = resultSet.getObject("date");
            System.out.println(date+"");
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
