package dbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			User user = new User();
			String url = user.getUrl();
			String id = user.getUser();
			String pass = user.getPwd();
			String tname = "TB_TEST";
			Connection conn = DriverManager.getConnection(url, id, pass);
			System.out.println("데이터베이스 연결 성공");
			
			System.out.println("1111");
			PreparedStatement ps;
			ResultSet rs = null;
			
			System.out.println("2222");
			String sql = "select count(*) as cnt from USER_TABLES where " + "TABLE_NAME = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tname);
			System.out.println("3333");
			rs = ps.executeQuery();
			
			int cnt = 0;
			System.out.println("4444");
			while (rs.next()) {
				System.out.println("5555");
				cnt = rs.getInt("cnt");
			}
			
			if (cnt <= 0) {
				sql = "create table " + tname + " (test1 varchar2(10) primary key," + " test2 number not null)";
				ps.executeUpdate(sql);
				System.out.println(sql +"문 실행 완료되었다");
				System.out.println("테이블 생성 Complete");
			} else {
				System.out.println("테이블이 이미 있다");
				sql = "drop table " + tname + " purge";
				ps.executeUpdate(sql);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("예외 : 드라이버 로드 실패 " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("예외 : " + e.getMessage());
			e.printStackTrace();
		}
			
	}
}

