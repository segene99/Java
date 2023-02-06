package dbc7;

import java.util.Scanner;

public class BoardMain {
	public static void main(String[] args) {
		BoardSVC boardSVC = new BoardSVC(); //boardSVC 객체 생성 초기화블록 있으면 실행
		Scanner sc = new Scanner(System.in); //입력창 생성

		while (true) {
			System.out.println("===== 게시판 =====");
			System.out.println("1. 글 등록");
			System.out.println("2. 글 목록보기");
			System.out.println("3. 글 검색하기");
			System.out.println("4. 글 삭제하기");
			System.out.println("5. 글 수정하기");
			System.out.println("6. 종료하기");
			System.out.print("메뉴를 선택하세요 : ");
			
			int menu = Integer.parseInt(sc.next()); //입력값 정수로 반환
			switch (menu) {
			case 1:
				boardSVC.writeArticle(sc); //boardSVC 클래스의 writeArticle 메소드 호출(입력값)
				break;
			case 2:
				boardSVC.showArticleList(); //boardSVC 클래스의 showArticleList 메소드 호출
				break;
			case 3:
				boardSVC.showArticle(sc);  //boardSVC 클래스의 showArticleList 메소드 호출
				break;
			case 4:
				boardSVC.deleteArticle(sc);
				break;
			case 5:
				boardSVC.updateArticle(sc);
				break;
			case 6:
				System.out.println("프로그램이 종료 되었습니다.");
				return; //감싸고 있는 메소드 종료
			default:
				System.out.println("명령 입력이 잘못되었습니다. 다시 입력해주세요.");
			}
		}
	}
}
