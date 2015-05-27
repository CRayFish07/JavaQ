package com.xcode.whz.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class TestSql {
	public static final String url = "jdbc:mysql://127.0.0.1/whz";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "yaosmmima";

		public static String TestQuery() {
		try {

			Class.forName(name);//指定连接类型
			Connection conn = DriverManager.getConnection(url, user, password);//获取连接
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery("select entrusttext from entrustinfo");
		    while(rst.next())
		    {
		       System.out.println(rst.getString("entrusttext"));
		       return rst.getString("entrusttext");
		    }
		    conn.close();
		    stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		public static void TestInsert(String enStr) {
			try {

				Class.forName(name);//指定连接类型
				Connection conn = DriverManager.getConnection(url, user, password);//获取连接
				Statement stmt=conn.createStatement();
				boolean rst=stmt.execute("INSERT INTO entrustinfo (entrustid,custid,entrusttext)VALUES(nextval('EntrustSeq'),'10000002','"+enStr+"')");
				System.err.println(rst);
			    conn.close();
			    stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public static void main(String[] args) {
		TestInsert("1110");
		TestQuery();
	}
}
