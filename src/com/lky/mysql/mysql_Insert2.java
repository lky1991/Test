package com.lky.mysql;

import java.sql.Connection;
import java.sql.SQLException;


public class mysql_Insert2 {

	/**
	 * 利用preparedStatement进行插入操作
	 */
	
	public void Insert2(){
		mysql_Con mysql = new mysql_Con("test");
		Connection conn = null;
		java.sql.PreparedStatement pStatement=null;
		conn = mysql.getConn();
		try {
			String id= javax.swing.JOptionPane.showInputDialog(null, "输入图书的id");
			String name=javax.swing.JOptionPane.showInputDialog(null,"输入图书的名字");
			String price=javax.swing.JOptionPane.showInputDialog(null, "输入图书的价格");
			String sql="insert into t_book values(?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, id);
			pStatement.setString(2, name);
			pStatement.setDouble(3, Double.parseDouble(price));
			int i=pStatement.executeUpdate();
			System.out.println("成功添加"+i+"条数据");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("插入数据库失败");
		} finally {
			if (pStatement != null) {
				try {
					pStatement.close();
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
		int n=10;
		mysql_Insert2 oInsert2=new mysql_Insert2();
		while(n!=0){
			oInsert2.Insert2();
			n--;
		}
		System.out.println("done!!");
	}

}
