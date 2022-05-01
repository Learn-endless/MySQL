package edu.wdu.helper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class OldJdbc{
	public static void main(String[] args){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/studb?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
			String username = "root";
			String password = "123456";
			
			Connection conn = DriverManager.getConnection(url, username, password);
			
			String sql = "insert into subject values (null,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, "张三");	
			statement.setInt(2, 5);
			System.out.println(statement);
			
			result = statement.executeUpdate();
			System.out.println(result);
			
			if(statement != null){
				statement.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}