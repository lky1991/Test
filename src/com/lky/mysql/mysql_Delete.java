package com.lky.mysql;

import java.sql.Connection;
import java.sql.SQLException;

public class mysql_Delete {

	/**
	 * 删除数据
	 */
	public Boolean Delete(String name) {
		mysql_Con mysql = new mysql_Con("test");
		Connection conn = null;
		java.sql.Statement statement = null;
		conn = mysql.getConn();

		try {
			statement = conn.createStatement();
			String sqlString = "delete from student where name='" + name + "'";
			int i = statement.executeUpdate(sqlString);
			System.out.println("成功删除" + i + "行");

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
		mysql_Delete oDelete = new mysql_Delete();
		Boolean aBoolean = oDelete.Delete("");
		if (aBoolean) {
			System.out.println("输出数据成功");

		} else {
			System.out.println("删除数据失败！！！！");
		}
	}

}
