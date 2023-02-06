package dbc6;

import java.sql.*;

public class TransactionTest {
	
	public static void main(String[] args) {
		TransactionTest tt = new TransactionTest();
		tt.insert();
	}
	
	Connection con; //Connection 객체 생성
		
		static { //초기화 블록: 딱 한번 실행
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 준비
			} catch (ClassNotFoundException cne) {
				cne.printStackTrace();
			}
		}
		
	public void connect() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
			con = DriverManager.getConnection(url, "test", "1111"); //드라이버 연결 유저 연결
			
			//자동커밋여부 제어
			con.setAutoCommit(false); //자동커밋 안됨으로 설정
			System.out.println("Connection Succeeded");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	
	}
	
	public void insert() {
		connect();
		Statement stmt = null;
		boolean isOrderSuccess = false;
		try {
			stmt = con.createStatement(); //statement 생성
			String sql = "insert into pay values('bbb', '001', 1000)"; //sql 문 
			stmt.executeUpdate(sql); //sql문 실행
			sql = "insert into orderlist values ('001', 'p2014001')"; //sql 문 
			stmt.executeUpdate(sql); //sql문 실행
			isOrderSuccess = true; //true로 변경
		} catch (SQLException se){
			System.out.println("추가 실패"); se.printStackTrace();
		} finally { //무조건 실행
			if (isOrderSuccess) {
					try {
						con.commit(); //commit : 전체 확정
					} catch (Exception e){
						e.printStackTrace();
					}
			} else {
				try {
					con.rollback(); //rollback: 전체 뒤로
				} catch (Exception e) {}
			}
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}	
	}
	
	
	
}		



