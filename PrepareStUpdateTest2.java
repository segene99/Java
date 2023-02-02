package dbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareStUpdateTest2 {
	private Connection con;
	private PreparedStatement st;
	
	public PrepareStUpdateTest2(int empno, String ename, double sal) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			User user = new User();
			Connection con = DriverManager.getConnection(user.getUrl(),user.getUser(), user.getPwd());
			System.out.println("DB 연결 성공");
			

			String sql = "update emp set ename = :a , sal = :a where empno = :a";
			
			st = con.prepareStatement(sql);
			st.setInt(1, empno);
			st.setString(2, ename);
			st.setDouble(3, sal);
			int result = st.executeUpdate();
			
			System.out.println("처리된 레코드 개수: " + result);
			st.close(); 
			con.close();
			} catch (ClassNotFoundException e) {
				System.out.println(e + "=> 드라이버 로드 실패");
			} catch (SQLException e) {
				System.out.println(e + "=> sql 예외 ");
			} catch (Exception e) {
				System.out.println(e + "=> 일반 예외");
			} 
	}
	
	public static void main(String[] args) {
		new PrepareStUpdateTest2(1, "Javaking", 900);
	}
	
}
