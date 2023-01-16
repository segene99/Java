package exceptex;

public class ExceptEx03 {

	public static void main(String[] args) {
		String c = null;
//		System.out.println("문자열 값은 : " + c.toString());
	
		try {
			System.out.println("문자열 값은 : " +  c.toString()); // null값이 무엇인지 모르는데 문자열로 주라고 해서
//			System.out.println("문자열 값은 : " +  c);
		} catch (NullPointerException e) {
			System.out.println("예외가 발생하여 Exception 객체가 잡음");
			System.out.println(e);
			System.out.println(e.getMessage()+", " + e.getCause());
		} finally {
			System.out.println("finally 블럭 수행");
		}
		System.out.println("정상 종료");
	}

}
