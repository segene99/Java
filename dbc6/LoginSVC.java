package dbc6;

import java.sql.*;

public class LoginSVC {
	Connection con;
	
	static { //초기화 블록 , 드라이버 준비
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() { //DB연결
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "test", "1111");
			System.out.println("Connection Success!");
		} catch (SQLException se){
			se.printStackTrace();
		}
	}
	
	public User login(String id, String passwd) { //(AADFSA, DIJDS) 값 받음
		User user = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect();
			stmt = con.createStatement();
			String sql = "select * from member where id ='"+ id +"' and passwd = '" + passwd + "'";
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) { //member 테이블의 값 받아옴
				id = rs.getString("id"); 
				passwd = rs.getString("passwd"); 
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				user = new User(id, passwd, name, age, addr, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return user;
	}
	
}




