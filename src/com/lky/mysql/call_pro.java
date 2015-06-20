package com.lky.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class call_pro {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		mysql_Con mysql = new mysql_Con("test");
		Connection conn = null;
		CallableStatement cStatement=null;
		conn = mysql.getConn();
		try {
			cStatement=conn.prepareCall("{call p1_test(?,?)}");
			cStatement.setInt(1,1);
			cStatement.registerOutParameter(2, java.sql.Types.CHAR);
			cStatement.executeQuery();
			String resultString=cStatement.getString(2);
			System.out.println(resultString);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(cStatement!=null){
				try {
					cStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
