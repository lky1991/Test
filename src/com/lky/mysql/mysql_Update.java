package com.lky.mysql;

import java.sql.Connection;
import java.sql.SQLException;

public class mysql_Update {

	/**
	 * 修改数据
	 */
	public Boolean Update(String sid, String name) {
		mysql_Con mysql = new mysql_Con("test");
		Connection conn = null;
		java.sql.Statement statement = null;
		conn = mysql.getConn();

		try {
			statement = conn.createStatement();
			String sqlString = "update student set name='" + name
					+ "'where id='" + sid + "'";
			int i = statement.executeUpdate(sqlString);
			System.out.println("成功修改" + i + "行");
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
		mysql_Update oMysql_Update = new mysql_Update();
		Boolean aBoolean = oMysql_Update.Update("0", "珞珞");
		if (aBoolean) {
			System.out.println("更新数据成功");
		} else {
			System.out.println("更新数据失败");
		}
	}

}
