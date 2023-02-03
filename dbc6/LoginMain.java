package dbc6;

import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) {
		boolean isStop = false;
		Scanner sc = new Scanner(System.in); //입력창 생성
		LoginSVC loginsvc = new LoginSVC(); //LoginSVC 클래스 객체 생성, 메모리 할당, 주소값 얻음
		
		do {
			System.out.println("로그인 화면");
			System.out.println("아이디와 비밀번호를 입력하세요.");
			System.out.println("아이디 : ");
			String id = sc.next().toLowerCase(); //id 값 입력: AADFSA, next(): 한줄 받기 , nextline(): 한줄 뜨고 받기
			System.out.println("비밀번호 : ");
			String passwd = sc.next().toLowerCase();//passwd 값 잆력: DIJDS
			User user = loginsvc.login(id, passwd); //(AADFSA, DIJDS) -> aaa	aaa	김마리	32	서울시	a@a.com
			if (user == null) {
				System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
			} else {
				System.out.println("로그인한 사용자 정보");
				System.out.println(user); //toString이 있어서 주소값대신 값 출력
				isStop = true;
			}
		} while (!isStop);
	}
}
