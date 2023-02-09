import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import java.net.*;

public class ProcedureCall {

	//CallableStatement를 통한 프로시저 호출
	public static void main(String[] args) {
		try {
			//데이터베이스 드라이버를 로드
			Class.forName("oracle.jdbc.driver.OracleDriver"); //ojdbc6.jar 라는 드라이버파일이 있는 것을 끌어다오는 형식
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","test", "1111");
			CallableStatement cstmt = con.prepareCall("{call res()}"); //
			cstmt.executeQuery();
					
			cstmt.close();
			con.close();

			System.out.print("프로시저 호출 완료");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
