package exceptex;

public class ExceptEx01 {

	public static void main(String[] args) {
		int c;
//		c= 4 / 0;
		try {
			c=4/0;
			System.out.println("1111)"); //출력안
		}catch(ArithmeticException e) {
			System.out.println("젯수(나누는 수)는 0이 될 수 없습니다. \n0이상이 되도록 변경해주세요.");
		}
	}

}
