# 1. 理解JDBC

JDBC就是官方为了使我们能从容的连接市面上的数据库而设计的一组接口，我们程序员在写代码时可以使用JDBC这组接口来连接我们所需要使用的数据库。

* JDBC 的基本写法（增删改）

  ```java
          //创建数据源
          DataSource dataSource = new MysqlDataSource();
          ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
          ((MysqlDataSource)dataSource).setUser("root");       //mysql的用户名
          ((MysqlDataSource)dataSource).setPassword("1234");   //mysql的密码
  
          //与数据库建立连接
          Connection connection = dataSource.getConnection();
  
          //创建sql语句（增删改）在这里写 sql 语句不用写分号
          String sql = "insert into subject values (20,'SpringMVC',3)";
          //将String类型的sql转换为一条”语句对象“
          PreparedStatement statement = connection.prepareStatement(sql);
          System.out.println(statement);  //打印一下sql语句的内容
  
          //执行sql语句（增删改用Update）
          int result = statement.executeUpdate();
          System.out.println(result);     //打印一下被影响的行数
  
          //释放资源  - 先创建后释放，后创建先释放
          if(statement != null){
              statement.close();
          }
          if(connection != null){
              connection.close();
          }
  ```

  * mysql ： 想要连接的数据库。

  * 127.0.0.1：这里填写IP地址（一个主机在互联网上的地址），127.0.0.1是一个环回IP表示自己的主机，同时也可以使用  localhost  表示，两者皆可。

  * 3306：这是一个端口号，主机上每一个服务程序都对应一个端口号。（我们在安装 mysql 时默认的端口号就是 3306）。

  * test : 这里填写我们需要连接的数据库名。

  * characterEncoding=utf8 ： 这里是指定字符编码为utf8。

  * useSSL=false ： 这里指传输是否加密，false 为 否， true 为 是。

    

* JDBC 的基本写法（查）

```java
        //设置数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");      //mysql用户名
        ((MysqlDataSource)dataSource).setPassword("1234");  //mysql密码

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
```

