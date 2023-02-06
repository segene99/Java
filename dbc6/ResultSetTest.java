package dbc6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ResultSetTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test";
		String pw = "1111";
		Connection conn = DriverManager.getConnection(url, user, pw);
//		Statement stmt = conn.createStatement(); //ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY
//		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); //커서이동 및 민감하게 최근것도 반응, 커서객체로 데이터 갱신 가능 
//		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); //커서이동 변화감지못함,  커서객체로 데이터 갱신 가능
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("select empno, ename, sal from emp01");
		showProperty(rs);
		
		System.out.println("처음 select 쿼리문 실행: ");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
		}
		
		System.out.println("\n==================================\n");
	
//		doSomeChanges(conn);
//		rs.beforeFirst();		//커서를 이전에 기억된 위치로 이동. 맨처음전
//		System.out.println("insert후 select 쿼리문 실행: ");
//		while (rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//		}
//		System.out.println("\n==================================\n");
		
//		rs.beforeFirst();
//		while(rs.next()) {
//			if(rs.getInt("empno") == 7900) {
//				rs.updateInt("sal", 9050); //커서객체를 통해 update 실행 int자료형 시, insentive여도 DB반영
//				rs.updateRow(); //update 확정
//			}
//		}
//		System.out.println("\n==================================\n");
//		
//		rs.beforeFirst();
//		System.out.println("Update후 select 쿼리문 실행: ");
//		while (rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//		}
//		System.out.println("\n==================================\n");
		
//		cleanup(conn);
//		rs.beforeFirst();
//		System.out.println("cleanup후 select 쿼리문 실행: ");
//		while (rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));		
//		}
//		System.out.println("\n==================================\n");
		
//		rs.moveToInsertRow(); //커서를 삽입 행으로 이동
//		rs.updateInt("empno", 8000);
//		rs.updateString("ename", "test");
//		rs.updateInt("sal", 1000);
//		rs.insertRow(); //insert 확정
//		rs.moveToCurrentRow();
//		System.out.println("moveToCurrentRow후 select 쿼리문 실행: ");
//		while (rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//		}
//		System.out.println();
//		
//		rs.beforeFirst();
//		System.out.println("insertRow후 select 쿼리문 실행: ");
//		while (rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//		}
//		System.out.println();
		
		rs.close();
		stmt.close();
		conn.close();
	}
	
	public static void doSomeChanges(Connection conn) throws SQLException {
		System.out.println("doSomeChanges() 메소드 실행");
		Statement otherStmt = conn.createStatement();
		otherStmt.execute("update EMP01 set sal = sal + 500 where empno = 7900");
		//otherStmt.execute("commit");
		otherStmt.close();
	}
	
	public static void showProperty(ResultSet rs) throws SQLException {
		switch(rs.getType()) {
		case ResultSet.TYPE_FORWARD_ONLY:
			System.out.println("Reselt set type: TYPE_FORWARD_ONLY"); break;
		case ResultSet.TYPE_SCROLL_INSENSITIVE:
			System.out.println("Reselt set type: TYPE_SCROLL_INSENSITIVE"); break;
		case ResultSet.TYPE_SCROLL_SENSITIVE:
			System.out.println("Reselt set type: TYPE_SCROLL_SENSITIVE"); break;
		default:
			System.out.println("Invalid type"); break;
		}
		
		switch(rs.getConcurrency()) {
		case ResultSet.CONCUR_UPDATABLE:
			System.out.println("Result set concurrency: ResultSet.CONCUR_UPDATABLE");
			break;
		case ResultSet.CONCUR_READ_ONLY:
			System.out.println("Result set concurrency: ResultSet.CONCUR_READ_ONLY");
			break;
		default: 
			System.out.println("Invalid type"); break;
		}
	}
	
	public static void cleanup(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE EMP01 SET SAL = SAL - 500");
		//stmt.execute("COMMIT");
		stmt.close();
	}
}
