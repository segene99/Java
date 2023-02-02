package dbc1;

import java.sql.*;

public class QuizDelete {

	private Connection con = null;
	private PreparedStatement pst = null;
	
	public QuizDelete (int deptno) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			User user = new User();
			String url = user.getUrl();
			String account = user.getUser();
			String pwd = user.getPwd();
			String driver = user.getDriver();
			
			con = DriverManager.getConnection(url, account, pwd);
			System.out.println("db 연결 완료");
			
			String sql = "delete from dept where deptno = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, deptno);
			int result = pst.executeUpdate();
			System.out.println("delete완료 : " + result);
			
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new QuizDelete (50);
	}
}
