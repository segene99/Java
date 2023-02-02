package dbc1;

import java.sql.*;

public class QuizInsert {


	
	public QuizInsert (int deptno, String dname, String loc) {
		
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		User user = new User();
		String url = user.getUrl();
		String account = user.getUser();
		String pwd = user.getPwd();
		String driver = user.getDriver();
		PreparedStatement pst;
		Connection con;
		
		con = DriverManager.getConnection(url, account, pwd);
		System.out.println("db 연결 완료");
		
		String sql = "insert into dept(deptno, dname, loc) values (?, ?, ?)";
		pst = con.prepareStatement(sql);
		pst.setInt(1, deptno);
		pst.setString(2, dname);
		pst.setString(3, loc);
		int result = pst.executeUpdate();
		System.out.println("insert 완료 : " + result);
		
		pst.close();
		con.close();

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		new QuizInsert (50, "개발", "서울");
	}
}
