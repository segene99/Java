package dbc6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreviousTest1 {
	Connection con;
	
	static { //객체생성과 함께 초기화블록 실행
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
			// preparedstatement 의 경우: preparedstatement(sql, type명, concur명)
			String sql = "select * from member"; //sql문
			rs = stmt.executeQuery(sql); //sql 실행
			
			System.out.println("한줄씩 전체 출력");
			while (rs.next()) { //다음 레코드로 이동 후, 레코드 값이 있는지 물음
				for (int i=1;i<=6;i++)
					System.out.print("\t" + rs.getString(i)); //column 가져오기
				System.out.println();
			}
			
			System.out.println("아무것도 출력 안함");
			while(rs.next()) { 
				for (int i=1;i<=6;i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			
			rs.beforeFirst();
			System.out.println("전체 출력");
			while(rs.next()) { 
				for (int i=1;i<=6;i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			
			System.out.println("역순으로 출력");
			while (rs.previous()) { 
				for (int i = 1; i<=6; i++) {
					System.out.print("\t" + rs.getString(i));
				}
				System.out.println();
			}
			
			rs.last(); //마지막 행 출력
			System.out.println("총 줄 수: " +  rs.getRow()); //5 출력
			
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
	
	
	
	public static void main(String[] args) {
		PreviousTest1 pt = new PreviousTest1();
		pt.connect();
		pt.select();
	
	}

}
