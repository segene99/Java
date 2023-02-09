package networkEx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		
			System.out.println("클라이언트가 접속되었습니다.");
			//서버 접속
			//Socket socket = new Socket("내 ip를 넣으세요.", 4000);
			
			// 성현 ip : 192.168.0.54
			// 내 고정 ip: 61.40.108.149
			
			Socket socket = new Socket("61.40.108.149", 4000);
			//server에 보낸 데이터
			BufferedWriter bufWriter = null;
			BufferedReader bufReader = null;
			try {
				while(true) {
					//server에 보낼 데이터를 담는 객체 생성
					bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					Scanner sc = new Scanner(System.in);
					System.out.println("[클라이언트 메세지] : ");
					String str = sc.nextLine();
					bufWriter.write(str);
					bufWriter.newLine();
					//서버로 데이터 전송(버퍼 강제 비움 = 당장 데이터를 전송해라)
					bufWriter.flush();
					
					//server가 보낸 데이터를 담는 객체 생성
					bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String message = bufReader.readLine();
					System.out.println("서버Message : " + message);
					if (message.contains("종료")) {
						str = "종료";
						bufWriter.write(str);
						bufWriter.newLine();
						bufWriter.flush();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				socket.close();
				bufReader.close();
				bufWriter.close();
			}
			
		}
	

}
