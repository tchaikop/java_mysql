package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	//MySQL 접속 주소
	private static final String url = "jdbc:mysql://localhost:3306/javaboard?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
	
	//mySQL 계정
	private static final String user = "root";
	private static final String pass = "12345";
	
	// 데이터 베이스 연결 메서드
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("연결성공");
			
		}catch(Exception e) {
			System.out.println("연결실패");
			e.printStackTrace();
		}
		
		return conn;
		
	}

}