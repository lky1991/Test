package com.lky.mysql;
import java.sql.*;

public class mysql_Con {
	private final String url = "jdbc:mysql://localhost:3306/";
	private final String user = "root";
	private final String pwd = "root";
	private Connection conn;
	
	public mysql_Con(String dbName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("mysql驱动加载失败！！！");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url + dbName+"?auotReconnect=true&useUnicode=true&characterEncoding=utf8", user, pwd);
		} catch (Exception e) {
			System.out.println("连接数据库失败！！");
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取一个有效连接
	 */
	public Connection getConn() {
		return conn;
	}

}
