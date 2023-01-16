package exceptex;

public class ExceptEx06 {

	public static void main(String[] args) {
		
//		System.out.println("문자열 값은 : " + c.toString());
	
		try {
			String c = null;
			System.out.println("문자열 값은 : " +  c.toString()); 
//			System.out.println("문자열 값은 : " +  c);
		} catch (Exception e) {
			System.out.println(">> 예외처리 구문 <<");
			System.out.println(">> e <<");
			System.out.println(e);
			System.out.println(" >> to.String() <<");
			System.out.println(e.toString());
			System.out.println(" >> e.getMessage <<");
			System.out.println(e.getMessage());
			//발생한 예외 클래스의 인스터스에 저장된 메시지 얻어오기
			System.out.println(" >> e.printStackTrace() <<");
			e.printStackTrace();
			//예외 발생 당시의 호출 스택에 있는 메소드의 정보와 얘외 메시지를 화면에 출력
		} finally {
			System.out.println("finally 처리");
		}
		System.out.println("완료");
	}
}
