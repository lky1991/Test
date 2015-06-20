package com.lky.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mysql_Select {

	/**
	 * 数据库查询
	 */
	public void Select(String name){
		mysql_Con mysql = new mysql_Con("test");
		Connection conn = null;
		java.sql.Statement statement = null;
		conn = mysql.getConn();
		ResultSet rSet=null;
		try {
			statement = conn.createStatement();
			String sql ="select * from student where name='"+name+"'";
			rSet=statement.executeQuery(sql);
			int i=0;
			
			while(rSet.next()){
				String id=rSet.getString("id");
				String namesString=rSet.getString("name");
				System.out.println(id+": "+namesString);
				i++;
			}
			System.out.println("共获取"+i+"条数据");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询失败");
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
	}
	public static void main(String[] args) {
		mysql_Select oMysql_Select =new mysql_Select();
		oMysql_Select.Select("珞珞");
	}

}
