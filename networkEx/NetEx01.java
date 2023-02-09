package networkEx;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetEx01 {

	public static void main(String[] args) throws UnknownHostException {
//		로컬호스트의 InetAddress 클래스 객체를 반환함		
		InetAddress Address = InetAddress.getLocalHost();
//		getLocalHost() 메소드는 static으로 선언된 클래스 메소드임

		System.out.println("로컬 컴퓨터의 이름 : " + Address.getHostName());
		System.out.println("로컬 컴퓨터의 이름 : " + Address.getHostName());
		System.out.println("로컬 컴퓨터의 IP 주소1 : " + Address.getHostAddress());
		System.out.println("로컬 컴퓨터의 IP 주소2 : " + Address.toString());
		System.out.println();

		InetAddress [] sw1 = InetAddress.getAllByName(Address.getHostAddress());
		for(int i = 0; i < sw1.length; i++)
			System.out.println(sw1[i]);
		
		Address = InetAddress.getByName("java.sun.com");
		System.out.println("java.sun.com 컴퓨터의 이름과 IP 주소 : " + Address);
		System.out.println();
		
		System.out.println("naver도메인에 대응되는 IP를 반환함");
//		http://223.130.200.1
		InetAddress sw[] = InetAddress.getAllByName("www.naver.com");
		for(int i = 0; i < sw.length; i++)
			System.out.println(sw[i]);
	}

}
