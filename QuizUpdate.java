package dbc1;

import java.sql.*;

public class QuizUpdate {

	public QuizUpdate (int deptno, String dname, String loc) {
		
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		User user = new User();
		String url = user.getUrl();
		String account = user.getUser();
		String pwd = user.getPwd();
		String driver = user.getDriver();
		
		Connection con = DriverManager.getConnection(url, account, pwd);
		System.out.println("db 연결 완료");
		
		String sql = "update dept set dname = ? , loc = ? where deptno = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, dname);
		pst.setString(2, loc);
		pst.setInt(3, deptno);
		int result = pst.executeUpdate();
		System.out.println("update 완료 : " + result);
		
		if(result>0) {
			System.out.println("데이터가 정상적으로 처리되었습니다. 행수: " + result);
		} else {
			System.out.println("처리된 데이터가 없습니다.");
		}
		
		pst.close();
		con.close();
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();	
	} catch (SQLException e) {
		e.printStackTrace();
	} 
}	
	public static void main(String[] args) {
		new QuizUpdate (50, "인사", "부산");
	}
}
