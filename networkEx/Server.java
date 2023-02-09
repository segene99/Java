package networkEx;

import java.io.*; //io: in out
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		
		System.out.println("서버가 실행되었습니다.");
		
		//1. 서버 생성, 실행, 준비
		ServerSocket serverSocket = new ServerSocket(4000);
		
		//2. 대기 및 client의 접속을 허용(accept)
		Socket socket = serverSocket.accept();
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		
		try {
			while(true) {
					//3. client가 보낼 데이터를 담음 
					bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String message = bufReader.readLine();
					System.out.println("클라이언트Message : " +  message);
					
					//4. client에 데이터 전송
					Scanner sc = new Scanner(System.in);
					System.out.print("[서버 메세지] : ");
					String str = sc.nextLine();
					
					//5. client에 보낼 데이터를 담을 객체 생성
					bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//6. 클라이언트가 보낸 메세지가 "종료"라는 글자가 들어있으면
					//7. 클라이언트로 "종료"글자 보내기
					if(message.contains("종료")) str = "종료";
					bufWriter.write(str);
					bufWriter.newLine();
					//8. 클라이언트로 데이터 전송하기
					bufWriter.flush();
					if (message.contains("종료")) break;
				
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			bufReader.close();
			bufWriter.close();
			socket.close();
			serverSocket.close();
		}
				
	}

}
