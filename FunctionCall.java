import java.sql.*;

public class FunctionCall {
	public static void main(String[] args) {
		try {
			//데이터베이스 드라이버를 로드
			Class.forName("oracle.jdbc.driver.OracleDriver"); //ojdbc6.jar 라는 드라이버파일이 있는 것을 끌어다오는 형식
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","test", "1111");
			CallableStatement cstmt = con.prepareCall("{ ?=call TEST_FUNC(?)}"); // ? 바인드변수
			
			// CallableStatement 객체명 = Connection객체명.prepareCall({리턴값의자료형=call test_func(값)});
			// CallableStatement객체명.registerOutParameter(첫번째, Types.리턴자료형);
			// CallableStatement객체명.execute();
			// CallableStatement객체명.close();
			
			cstmt.registerOutParameter(1, Types.VARCHAR); 
			cstmt.setString(2,  "TURNER");
			cstmt.execute();
			
			System.out.println(cstmt.getString(1));
			
			cstmt.close();

			//PreparedStatement로 CallableStatement와 같은 값 받기
			PreparedStatement ps = con.prepareStatement("select test_func(?) from dual");
			ps.setString(1,  "TURNER");
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getString(1));
			
			rs.close();
			ps.close();
			
			
			
			con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
