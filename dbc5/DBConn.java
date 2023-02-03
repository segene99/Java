package dbc5;

import java.sql.*;

public class DBConn {
	private static Connection dbConn;
	
	public static Connection getConnection() { //드라이버 준비, DB연결
		if (dbConn == null) {
			try {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "test";
				String pwd = "1111";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e){
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd) { //드라이버 준비, DB연결
		if (dbConn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}
	public static void close() { //DB연결 종료
		if(dbConn != null) {
			try {
				if (!dbConn.isClosed()) dbConn.close();
			} catch (Exception e){
				System.out.println(e.toString());
			}
		}
		dbConn = null;
	}
}
