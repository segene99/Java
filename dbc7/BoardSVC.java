package dbc7;

import static dbc7.db.JdbcUtil.close; //static 붙은 메소드는 import로 static 붙여서 호출 가능
import static dbc7.db.JdbcUtil.getConnection;
//import static dbc7.db.JdbcUtil.*;

import java.sql.*;
import java.util.Scanner;

public class BoardSVC {
	Connection con;

	public BoardVO getBoardVO(Scanner sc) {
		System.out.println("=== 게시물 등록 ===");
		System.out.print("작성자 : ");
		String writer = sc.next(); // 작성자 입력값
		System.out.print("비밀번호 : ");
		String passwd = sc.next(); // 비밀번호 입력값
		System.out.print("제목 : ");
		String subject = sc.next(); // 제목 입력값
		System.out.print("이메일 : ");
		String email = sc.next(); //이메일 입력값

		BoardVO boardVO = new BoardVO(0, writer, passwd, subject, email); // 입력값 저장한 boardVO 객체 생성
		return boardVO; //객체 return
	}

	public void writeArticle(Scanner sc) {
		BoardVO boardVO = getBoardVO(sc); //getboardVO 메소드 호출 -> 객체 return 받음
		con = getConnection(); //db 연결 객체 생성 및 계정 연결
		PreparedStatement pstmt = null; // Preparedstatement 객체 생성

		String sql = "INSERT INTO board VALUES(board_seq.nextval, ?, ?, ?, ?)"; //ID번호 자동 넘어감 1부터 시작
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getWriter()); //나
			pstmt.setString(2, boardVO.getPasswd()); //1111
			pstmt.setString(3, boardVO.getSubject()); //아무거나
			pstmt.setString(4, boardVO.getEmail()); //se@@dkdk.com

			int count = pstmt.executeUpdate(); //sql 실행 및 확정 
			
			if (count > 0) { System.out.println("글 등록 완료"); } 
			else { System.out.println("글 등록 실패"); }
		} catch (Exception e) { e.printStackTrace(); } 
		finally { close(pstmt); close(con); }
	}

	public void showArticleList() {
		con = getConnection(); //db 연결 객체 생성 및 계정 연결
		PreparedStatement pstmt = null;  // Preparedstatement 객체 생성
		ResultSet rs = null;  // resultset 객체 생성
		String sql = "SELECT * FROM board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //sql 문 실행
			System.out.println("\t 아이디 \t 작성자 \t 제목 \t 이메일");
			while (rs.next()) {
				System.out.println("\t" + rs.getInt("id") + "\t"
						+ rs.getString("writer") + "\t"
						+ rs.getString("subject") + "\t"
						+ rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	public void showArticle(Scanner sc) {

			System.out.println("검색할 글 번호를 입력하세요.");
			System.out.print("글 번호 : ");
			int id = sc.nextInt(); //int입력값 받음 
			BoardVO boardVO = getArticle(id); //객체 받음
			System.out.println(boardVO);		
	}
	private BoardVO getArticle(int id) { //int입력값 받음
		BoardVO boardVO = null; //초기화
		con = getConnection(); //db 연결 객체 생성 및 계정 연결
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id); 
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int dbId = rs.getInt("id");
				String writer = rs.getString("writer");
				String passwd = rs.getString("passwd");
				String subject = rs.getString("subject");
				String email = rs.getString("email");
				boardVO = new BoardVO(dbId, writer, passwd, subject, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return boardVO;
	}

	public void deleteArticle(Scanner sc) {
		System.out.println("삭제할 글 번호를 입력하세요.");
		System.out.print("글 번호 : ");
		int id = sc.nextInt();

		int count = deleteArticle(id);
		if (count > 0) {
			System.out.println("글 삭제 완료");
		} else {
			System.out.println("글 삭제 실패");
		}
	}

	private int deleteArticle(int id) {
		int deleteCount = 0;
		con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "DELETE board WHERE id = ?";
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

	public void updateArticle(Scanner sc) {
		System.out.println("수정할 글 번호를 입력하세요.");
		System.out.print("글 번호 : ");
		int id = sc.nextInt();

		BoardVO boardVO = getArticle(id);
		System.out.println("수정할 데이터를 입력하세요.");
		System.out.println("원래 작성자 : " + boardVO.getWriter());
		System.out.print("수정할 작성자 : ");
		String writer = sc.next();

		System.out.println("원래 비밀번호 : " + boardVO.getPasswd());
		System.out.print("수정할 비밀번호 : ");
		String passwd = sc.next();

		System.out.println("원래 제목 : " + boardVO.getSubject());
		System.out.print("수정할 제목 : ");
		String subject = sc.next();

		System.out.println("원래 이메일 : " + boardVO.getEmail());
		System.out.print("수정할 이메일 : ");
		String email = sc.next();

		boardVO.setWriter(writer);
		boardVO.setPasswd(passwd);
		boardVO.setSubject(subject);
		boardVO.setEmail(email);

		int count = updateArticle(boardVO);
		if (count > 0) {
			System.out.println("글 수정 완료");
		} else {
			System.out.println("글 수정 실패");
		}

	}

	private int updateArticle(BoardVO boardVO) {
		int updateCount = 0;
		con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE board SET writer = ?, passwd = ?, subject = ?, email = ?"
				+ " WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getWriter());
			pstmt.setString(2, boardVO.getPasswd());
			pstmt.setString(3, boardVO.getSubject());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setInt(5, boardVO.getId());
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
