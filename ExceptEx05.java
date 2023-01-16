package exceptex;

public class ExceptEx05 {

	public static void main(String[] args) {
		try { 
//			해보기:
//			매개인자1개 입력하고 실행하기 :  숫자로
//			매개인자2개 입력하고 실행하기 : 숫자로, 젯수 0 으로 입력하기
//			매개인자 String로 입력하고 실행하기
			
			System.out.println("매개변수로 받은 두개의 값");
			int a = Integer.parseInt(args[0]); //문자열 값을 정수로 변환
			int b = Integer.parseInt(args[1]);
			System.out.println("a = " +a + " b = " +b);
			System.out.println("a를 b로 나눈 몫 = " + (a/b));
			System.out.println("나누셈 수행");
		} 	catch (Exception e) {
			System.out.println("===================================");
			System.out.println("Exception 처리 루틴 : ");
		} finally {
			System.out.println("===================================");
			System.out.println("finally 블럭 수행");
		}
	}

}