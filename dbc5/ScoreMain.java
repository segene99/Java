package dbc5;

import java.util.Scanner;

public class ScoreMain {
	public static void main(String[] args) {
		String ch;
		Score ob = new Score(); //score알고리듬 소환
		Scanner sc = new Scanner(System.in); //입력란
		
		try {
			while (true) {
				System.out.print("다음 중 하나를 입력하시오. \n 1.입력 2.수정 3.삭제 4.전체출력 5.이름검색 6.종료=>");					
				ch = sc.nextLine(); //다음줄
			
				switch (ch.replace(" ","")) {
				case "1": case "입력":
					if (ob.insertDate() != 0) //행수 반환
						System.out.println("입력 성공!");
					break;
				case "2": case "수정":
					if (ob.updateData() != 0)
						System.out.println("수정 성공!");
					else
						System.out.println("수정 실패!");
					break;
				case "3": case "삭제":
					if (ob.deleteDate() != 0)
						System.out.println("삭제 성공!");
					else
						System.out.println("삭제 실패!");
					break;
				case "4": case "이름검색":
					ob.selectName();
					break;
				case "5": case "전체출력":
					ob.selectAll();
					break;
				case "6":
					System.out.println("종료되었습니다.");
					DBConn.close(); //DB데이터 종료
					System.exit(0); //실행된 프로그램 종료, 0 = 종료라는 의미의 매개인자 값
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}