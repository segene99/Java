package prog;

import static prog.db.JdbcUtil1.close;
import static prog.db.JdbcUtil1.getConnection;

import java.sql.*;
import java.util.Scanner;

public class AdminSVC {
	
	Connection con;

	public AdminVO getAdminVO(Scanner sc) {
		System.out.println("=== 상품등록 ===");
		System.out.print("상품명 : ");
		String itemName = sc.next(); 
		System.out.print("상품설명 : ");
		String itemExp = sc.next(); 
		System.out.print("영양정보 : ");
		String itemInfo = sc.next(); 
		System.out.print("가격 : ");
		int price = sc.nextInt(); 
		System.out.print("상품종류 : ");
		String itemType = sc.next(); 

		AdminVO adminVO = new AdminVO(0, itemName, itemExp, itemInfo, price, itemType); // 입력값 저장한 adminVO 객체 생성
		return adminVO; //객체 return
	}

	public void writeItem(Scanner sc) {
		AdminVO adminVO = getAdminVO(sc); //getadminVO 메소드 호출 -> 객체 return 받음
		con = getConnection(); //db 연결 객체 생성 및 계정 연결
		PreparedStatement pstmt = null; // Preparedstatement 객체 생성

		String sql = "INSERT INTO admin VALUES(admin_seq.nextval, ?, ?, ?, ?, ?)"; //ID번호 자동 넘어감 1부터 시작
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminVO.getItemName()); 
			pstmt.setString(2, adminVO.getItemExp());
			pstmt.setString(3, adminVO.getItemInfo()); 
			pstmt.setInt(4, adminVO.getPrice()); 
			pstmt.setString(5, adminVO.getItemType()); 
			
			int count = pstmt.executeUpdate(); //sql 실행 및 확정 
			
			if (count > 0) { System.out.println("상품 등록 완료"); } 
			else { System.out.println("상품 등록 실패"); }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { close(pstmt); close(con); }
	}

	public void showItemList() {
		con = getConnection(); //db 연결 객체 생성 및 계정 연결
		PreparedStatement pstmt = null;  // Preparedstatement 객체 생성
		ResultSet rs = null;  // resultset 객체 생성
		String sql = "SELECT * FROM admin";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //sql 문 실행
			System.out.println("\t 상품명 \t 상품설명 \t 영양정보 \t 가격 \t 상품종류");
			while (rs.next()) {
				System.out.println("\t" + rs.getInt("id") + "\t"
						+ rs.getString("itemExp") + "\t"
						+ rs.getString("itemInfo") + "\t"
						+ rs.getString("Price") + "\t"
						+ rs.getString("itemType") );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	public void showItem(Scanner sc) {
			System.out.println("검색할 상품 번호를 입력하세요.");
			System.out.print("상품 번호 : ");
			int id = sc.nextInt(); //int입력값 받음 
			AdminVO adminVO = getItem(id); //객체 받음
			System.out.println(adminVO);		
	}
	
	private AdminVO getItem(int id) { //int입력값 받음
		AdminVO adminVO = null; //초기화
		con = getConnection(); //db 연결 객체 생성 및 계정 연결
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM admin WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id); 
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int dbId = rs.getInt("id");
				String itemName = rs.getString("itemName");
				String itemExp = rs.getString("itemExp");
				String itemInfo = rs.getString("itemInfo");
				int price = rs.getInt("price");
				String itemType = rs.getString("itemType");
				adminVO = new AdminVO(dbId, itemName, itemExp, itemInfo, price, itemType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return adminVO;
	}

	public void deleteItem(Scanner sc) {
		System.out.println("삭제할 상품 번호를 입력하세요.");
		System.out.print("상품 번호 : ");
		int id = sc.nextInt();

		int count = deleteItem(id);
		if (count > 0) {
			System.out.println("상품 삭제 완료");
		} else {
			System.out.println("상품 삭제 실패");
		}
	}

	private int deleteItem(int id) {
		int deleteCount = 0;
		con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "DELETE admin WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return deleteCount;
	}

	public void updateItem(Scanner sc) {
		System.out.println("수정할 상품 번호를 입력하세요.");
		System.out.print("상품 번호 : ");
		int id = sc.nextInt();

		AdminVO adminVO = getItem(id);
		System.out.println("수정할 데이터를 입력하세요.");
		System.out.println("원래 상품명 : " + adminVO.getItemName());
		System.out.print("수정할 상품명 : ");
		String itemName = sc.next();

		System.out.println("원래 상품설명 : " + adminVO.getItemExp());
		System.out.print("수정할 상품설명 : ");
		String itemExp = sc.next();

		System.out.println("원래 영양정보 : " + adminVO.getItemInfo());
		System.out.print("수정할 영양정보 : ");
		String itemInfo = sc.next();

		System.out.println("원래 상품가격 : " + adminVO.getPrice());
		System.out.print("수정할 상품가격 : ");
		int price = sc.nextInt();

		System.out.println("원래 상품종류 : " + adminVO.getItemType());
		System.out.print("수정할 상품종류 : ");
		String itemType = sc.next();
		
		adminVO.setItemName(itemName);
		adminVO.setItemExp(itemExp);
		adminVO.setItemInfo(itemInfo);
		adminVO.setPrice(price);
		adminVO.setItemType(itemType);

		int count = updateItem(adminVO);
		if (count > 0) {
			System.out.println("글 수정 완료");
		} else {
			System.out.println("글 수정 실패");
		}

	}

	private int updateItem(AdminVO adminVO) {
		int updateCount = 0;
		con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE admin SET itemName = ?, itemExp = ?, itemInfo = ?, price = ?, itemType = ?"
				+ " WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminVO.getItemName());
			pstmt.setString(2, adminVO.getItemExp());
			pstmt.setString(3, adminVO.getItemInfo());
			pstmt.setInt(4, adminVO.getPrice());
			pstmt.setString(5, adminVO.getItemType());
			updateCount = pstmt.executeUpdate(); //수정 확정
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return updateCount;
	}
}
