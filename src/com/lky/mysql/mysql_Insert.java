package com.lky.mysql;

import java.sql.Connection;
import java.sql.SQLException;

public class mysql_Insert {

	/**
	 * 插入数据
	 */
	public Boolean Insert(String id, String name) {

		mysql_Con mysql = new mysql_Con("test");
		Connection conn = null;
		java.sql.Statement statement = null;
		conn = mysql.getConn();

		try {
			statement = conn.createStatement();
			String sql = "insert into student values('" + id + "','" + name
					+ "')";
			int i = statement.executeUpdate(sql);
			System.out.println("成功添加" + i + "行");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		mysql_Insert oInsert = new mysql_Insert();
		Boolean flagBoolean = oInsert.Insert("52", "珞珞");
		if (flagBoolean)
			System.out.println("插入数据成功");
		else
			System.out.println("出入数据失败!!!");
	}

}
