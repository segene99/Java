package prog;

import java.util.Scanner;

public class AdminMain {
	public static void main(String[] args) {
		AdminSVC adminSVC = new AdminSVC();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("===== 관리자 메뉴 =====");
			System.out.println("1. 상품 등록");
			System.out.println("2. 상품 목록보기");
			System.out.println("3. 상품 검색하기");
			System.out.println("4. 상품 삭제하기");
			System.out.println("5. 상품 수정하기");
			System.out.println("6. 종료하기");
			System.out.print("메뉴를 선택하세요 : ");
			
			int menu = Integer.parseInt(sc.next()); 
			switch (menu) {
			case 1:
				adminSVC.writeItem(sc); 
				break;
			case 2:
				adminSVC.showItem(sc); 
				break;
			case 3:
				adminSVC.showItemList(); 
				break;
			case 4:
				adminSVC.deleteItem(sc);
				break;
			case 5:
				adminSVC.updateItem(sc);
				break;
			case 6:
				System.out.println("프로그램이 종료 되었습니다.");
				return; 
			default:
				System.out.println("명령 입력이 잘못되었습니다. 다시 입력해주세요.");
			}
		}
	}
}
