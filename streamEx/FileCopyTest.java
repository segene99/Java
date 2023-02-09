package streamEx;

import java.io.*;

public class FileCopyTest {

	//파일 복제 소스코드
	public static void main(String[] args) {
		long millisecond = 0;
		try {
			FileInputStream fis = new FileInputStream("a.zip");
			FileOutputStream fos = new FileOutputStream("copy.zip");
			millisecond = System.currentTimeMillis();
			int i;
			while ((i = fis.read()) != -1) { //read 입력
				fos.write(i); //write는 출력
			}
			millisecond = System.currentTimeMillis() - millisecond;
			
			fos.close();
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("파일 복사 하는데" + millisecond + "milliseconds 소요되었습니다.");
	}

}
