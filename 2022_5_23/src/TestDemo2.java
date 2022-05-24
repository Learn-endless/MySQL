import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class TestDemo2 {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/tttttt?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        Connection connection = dataSource.getConnection();

        String sql = "select id as UserId,date as Birthday from user";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        ResultSetMetaData metaData = statement.getMetaData();
        int columnCount = metaData.getColumnCount();

        while(resultSet.next()){
            for (int i = 0; i < columnCount; i++) {

                System.out.println(metaData.getColumnLabel(i + 1));
//                System.out.println(metaData.getColumnName(i+1));

                System.out.println(resultSet.getObject(i+1));
            }
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
