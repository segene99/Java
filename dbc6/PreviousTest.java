package dbc6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreviousTest {
	Connection con;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 준비
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "Test", "1111"); //db연결 계정 접속
		} catch (SQLException se){
			se.printStackTrace();
		}
	}
	
	public void select() {
		Statement stmt = null; //Statement 객체 생성
		ResultSet rs = null; //ResultSet 객체 생성
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); //statement 생성
			String sql = "select * from member"; //sql문
			rs = stmt.executeQuery(sql); //sql 실행
			
			System.out.println("앞으로 이동하면서 출력");
			while (rs.next()) { //다음 레코드로 이동
				for (int i=1;i<=6;i++)
					System.out.print("\t" + rs.getString(i)); //column 가져오기
				System.out.println();
			}
			// afterlast에 커서가 있음
			// rs.beforeFirst(); 넣으면 출력안됨. 커서위로 아무것도 없어서
			System.out.println("뒤로 이동하면서 출력");
			while(rs.previous()) { //위로 이동
				for (int i=1;i<=6;i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			
			System.out.println("첫 번째 레코드");
			if (rs.first()) { //첫 행
				for (int i = 1; i<=6; i++) {
					System.out.print("\t" + rs.getString(i));
				}
				System.out.println();
			}
			
			System.out.println("마지막 레코드");
			if (rs.last()) { //마지막 행
				for (int i = 1; i<=6; i++) {
					System.out.print("\t" + rs.getString(i));
				}
				System.out.println();

			}
			
			System.out.println("세 번째 레코드");
			if (rs.absolute(3)) { //세번째 행
				for (int i = 1; i<=6; i++) {
					System.out.print("\t" + rs.getString(i));
				}
				System.out.println();
			}
			
		} catch (SQLException se){
			se.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String [] args) {
		PreviousTest pt = new PreviousTest();
		pt.connect();
		pt.select();
	}
	
}
