package com.xcode.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class JDBCUtil {
	static String dbdriver="test";
	static String dbname="test";
	static String dburl="jdbc:mysql://127.0.0.1:3306/";
	static String dbuser="root";
	static String dbpassword="yasmmi";
	static{
		ResourceBundle rb=ResourceBundle.getBundle("xcode");
		dbdriver=rb.getString("db.driver");
		dbname=rb.getString("db.name");
		dburl=rb.getString("db.url");
		dbuser=rb.getString("db.user");
		dbpassword=rb.getString("db.password");
	}
	
	public static ArrayList getAllTable() {
		String sql = "select table_name,table_comment from information_schema.TABLES where TABLE_SCHEMA='"+dbname+"'";
//		String sql = "show tables";
		Connection conn=getConnection();
		ResultSet rs=exceSql(sql,conn);
		ArrayList list=new ArrayList();
		HashMap hp=null;
		try {
			while (rs.next()) {
				hp=new HashMap();
				hp.put("tname",rs.getString("table_name") );
				hp.put("tcomment",rs.getString("table_comment") );
				list.add(hp);
//				System.out.println(rs.getString("table_name")+"--"+rs.getString("table_comment"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn,rs) ;
		}
		return list;
	}
	
	
	public static ArrayList getAllCol(String tablename) {
		String sql = "show full columns from "+tablename.toLowerCase();
		Connection conn=getConnection();
		ResultSet rs=exceSql(sql,conn);
		ArrayList list=new ArrayList();
		HashMap hp=null;
		try {
			while (rs.next()) {
				hp=new HashMap();
				hp.put("field",rs.getString("field").toUpperCase());
				hp.put("type",rs.getString("type").toUpperCase());
				hp.put("isNULL",rs.getString("Null").toUpperCase());
				hp.put("key",rs.getString("key"));
				hp.put("default",rs.getString("default"));
				hp.put("comment",rs.getString("comment"));
				list.add(hp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(conn,rs) ;
		}
		return list;
	}
	
	public static void main(String[] args) {
		String sql = "select table_name,table_comment from information_schema.TABLES where TABLE_SCHEMA='"+dbname+"'";
//		String sql = "show tables";
		Connection conn=getConnection();
		ResultSet rs=exceSql(sql,conn);
		System.out.println("-----------------");
		System.out.println("-----------------");
		String name = null;
		try {
			while (rs.next()) {
				System.out.println(rs.getString("table_name")+"--"+rs.getString("table_comment"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn,rs) ;
		}
	}
	public static ResultSet exceSql(String sql,Connection conn){
		ResultSet rs =null;
		try {
			// 连续数据库
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			//
			// DatabaseMetaData dd=conn.getMetaData();
			// System.out.println(dd.getDatabaseProductName());
			// System.out.println(dd.getDatabaseMajorVersion());
			// ResultSet rsets=dd.getAttributes("", "", "", "");
			// while(rsets.next()){
			// System.err.println((rsets.getString("TABLE_NAME")));
			// }
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			// 要执行的SQL语句
			// 结果集
			rs= statement.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("Sorry!");
			e.printStackTrace();
		}
		return rs;
	}
	
	public static Connection getConnection() {
				// 驱动程序名
//				String driver = "com.mysql.jdbc.Driver";
//				// URL指向要访问的数据库名scutcs
//				String url = "jdbc:mysql://127.0.0.1:3306/"+dbname+"";
////				String url = "jdbc:mysql://112.126.75.161:3306/whz";
//				// MySQL配置时的用户名
//				String user = "root";
//				// MySQL配置时的密码
//				String password = "yaosmmima";
				Connection	conn =null;
				try {
					// 加载驱动程序
					Class.forName(dbdriver);
					// 连续数据库
				conn	= DriverManager.getConnection(dburl+dbname, dbuser, dbpassword);
				}catch(Exception e){
					System.out.println("Sorry,can`t find the Driver!");
				}
				return conn;
	}
	public static void closeAll(Connection conn,ResultSet rs) {
		try {
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
