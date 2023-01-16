package exceptex;

public class ExceptEx00 {

//	public static void main(String[] args) {
//		try {
//			Class c = Class.forName("String"); // java.lang.String 이 맞음. 에러발생지점까지만 출력
//			System.out.println(c);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		System.out.println("시스템 정상 종료");
//	}
	public static void main(String[] args) throws ClassNotFoundException,
	IllegalAccessException {
		Class c1 = Class.forName("String");
		System.out.println(c1);
	}
}
