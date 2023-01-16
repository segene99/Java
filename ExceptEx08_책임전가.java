package exceptex;

public class ExceptEx08_책임전가 {

	public static void main(String[] args) {
		try {
			findClass();
		} catch (Exception e) { //ClassNotFoundException 이상 처리가 되어야 함.
			System.out.println("클래스가 존재하지 않습니다.");
			System.out.println("에러 사유: " + e);
		}
	}
	
	public static void findClass() throws ClassNotFoundException {
		Class clazz = Class.forName("java.lang.WowClass");
	}
}
