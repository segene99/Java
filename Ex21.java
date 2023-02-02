package dbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex21 {
	public static void main(String[] args) {
		User user = new User();
		String driver = user.getDriver();
		String url = user.getUrl();
		String userid = user.getUser();
		String passwd = user.getPwd();
		Connection con = null; 
		Statement stmt = null; 
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
		
			//String query = "select empno,ename,job,hiredate from emp";
			String query = "select * from emp";
			
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String hiredate = rs.getString("hiredate");
				System.out.println(empno + " " + ename + " " + job + " " + hiredate);
			} 
		}	catch (Exception e) {e.printStackTrace();
		} 	finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

