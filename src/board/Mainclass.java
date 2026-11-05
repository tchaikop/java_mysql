package board;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dbconnection.DBConnection;

 
//회원가입
public class Mainclass {
	
	static Scanner sc = new Scanner(System.in);
	
	
	static String loginid = null;
	  
	  public static void join() {
		  
		  System.out.println("아이디 입력");
		  String userid = sc.nextLine();
		  
		  System.out.println("비밀번호 입력");
		  String password = sc.nextLine();
		  
		  System.out.println("이름 입력");
		  String username = sc.nextLine();
		  
		  String sql ="insert into member(userid,password,username) values(?,?,?)";  // ?표는 위치와 갯수를 나타낸다
		  
		  try {
			  
			  Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);  //sql 문장을 안전하게 실행하기 위한 생성 클래스
			 
			 pstmt.setString(1, userid);
			 pstmt.setString(2, password);
			 pstmt.setString(3, username);
			 
			 int result = pstmt.executeUpdate();  //select 로 할때는 Executequary로 실행한다 //sql문이 정상 작동하면 정수 1이 반환됨
			 
			 if(result ==1) {
				 System.out.println("회원가입 성공");
			 }else {
				 System.out.println("실패");
			 }
			 conn.close(); // connection 닫기
			  
		  }catch(Exception e) {
			  System.out.println("회원가입 샐패");
			  e.printStackTrace();
			  
		  }
		  
		  
		
		  
	  }
	  
	  // 회원 탈퇴
	  
	  public static void memberDelete() {
		  System.out.println("탈퇴할  아이디 입력");
		  String userid = sc.nextLine();
		  
		  String sql = "delete from member where userid = ?";
		  
		  try {
			  Connection conn = DBConnection.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 
				 pstmt.setString(1, userid);
				 
				 int result = pstmt.executeUpdate();
				 if(result ==1) {
					 System.out.println("회원탈퇴 성공");
				 }else {
					 System.out.println("실패");
				 }
				 conn.close(); // connection 닫기
			
			
		} catch (Exception e) {
			System.out.println("탍퇴 실패");
		}
		  
		  
	  }
	  
	  //회원정보 수정
	  
	  public static void memberUpdate() {
		  
		  
		  System.out.println("변경할 비밀번호 입력");
		  String password = sc.nextLine();
		  System.out.println("아이디 입력");
		  String userid = sc.nextLine();
		  
		  String sql = "update member set password = ? where userid = ?";
		  
		  try {
			  Connection conn = DBConnection.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 
				 pstmt.setString(1, password);
				 pstmt.setString(2, userid);
				 
				 int result = pstmt.executeUpdate();
				 if(result ==1) {
					 System.out.println("회원정보 변경 성공");
				 }else {
					 System.out.println("실패");
				 }
				 
				 conn.close(); // connection 닫기
			
			
		} catch (Exception e) {
			System.out.println("탍퇴 실패");
		}
		  
		  
	  }
	  
	  //특정 회원정보 출력 메서드
	  
	  public static void memberinfo() {
		  
		  System.out.println("회원 정보를출력할 아이디 입력");
		  String userid = sc.nextLine();
		
		  String sql = "select * from member where userid = ?";
		  
		  
		  try {
			  Connection conn = DBConnection.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 
				 pstmt.setString(1, userid);
				 
				 ResultSet rs = pstmt.executeQuery();  // rs는 select 문의 답을 받는 그릇이다.
				 
				 if(rs.next()) {
					 
					 System.out.println(" 회원은 존재합니다");
					 
				String mid	= rs.getString("userid");
				String mpw	= rs.getString("password");
				String mname = rs.getString("username");
				String mdate = rs.getString("regdate");
				
				System.out.println("아이디/비밀번호/이름/가입일시");
				System.out.println("---------------------");
				System.out.println(mid + "|" + mpw+ "|" + "|"+ mname +"|"+mdate);
					 
					 
				 }else {
					 System.out.println(" 회원은 존재  하지 않습니다");
				 }
				 
				 
				 rs.close(); // rs 닫기
				 conn.close(); // connection 닫기
				 
				
				 
				 
				 
			  
			  
			
		} catch (Exception e) {
			System.out.println("회원정보 조회실패");
		}
		  
	  }
	  
	  // 모든 회원 정보 조회 하는 메소드
	  
 public static void allmemberinfo() {
	 //관리자 아이디:admin, 비밀번호:8888
	 
	 if(!"admin".equals(loginid)) {  // 관리자로 록인을 하지 않은 상태
		 System.out.println("모든 정보는 관리자만 조회 가능");
		 login(); 
		 if (!"admin".equals(loginid)) {
			    System.out.println("관리자 인증 실패");
			    return;
			}//다시 로그인
	 } 
		  
		 
		
		  String sql = "select * from member ";
		  
		  
		  try {
			  Connection conn = DBConnection.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 
				 ResultSet rs = pstmt.executeQuery();  // rs는 select 문의 답을 받는 그릇이다.
				 
				 while(rs.next()) {
					 
					 System.out.println(" 회원은 존재합니다");
					 
				String mid	= rs.getString("userid");
				String mpw	= rs.getString("password");
				String mname = rs.getString("username");
				String mdate = rs.getString("regdate");
				
				System.out.println("아이디/비밀번호/이름/가입일시");
				System.out.println("---------------------");
				System.out.println(mid + "|" + mpw+ "|" + "|"+ mname +"|"+mdate);
				System.out.println("---------------------");
					 
				
				
//				 conn.close(); // connection 닫기
					 
				 }
				 
				 
							
		} catch (Exception e) {
			System.out.println("회원정보 조회실패");
		}
		  
	  }
	  
 
 //로그인 메서드
 
 public static void login() {
	 
	 System.out.println("로그인 아이디 입력");
	  String userid = sc.nextLine();
	  System.out.println("로그인 비밀번호 입력");
	  String password = sc.nextLine();
	  
	 String sql ="select * from member where userid = ? and password = ?"; 
	 
	 try {
		  Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, userid);
			 pstmt.setString(2, password);
			 
			 ResultSet rs = pstmt.executeQuery();  // 레코드가 1개 오거나 0개
			 
			 if(rs.next()) {
				 
				 System.out.println(" 로그인 성공!");
				
				 
			String mid	= rs.getString("userid");			
			String mname = rs.getString("username");
			
			loginid = mid;
		
			
			 System.out.println(mname +"님 환영합니다" );
			
				 
			 }else {
				 System.out.println(" 아이디 또는 비밀번호가 잘못 되었습니다");
			 }
			 
			 
			 rs.close(); // rs 닫기
			 conn.close(); // connection 닫기
			 
			
			 
			 
			 
		  
		  
		
	} catch (Exception e) {
		System.out.println("회원정보 조회실패");
	}
	  
 }
 
 // 로그아웃
 public static void logout() {
	 
	 loginid = null; //로그아웃
 }
 
 // 게시판 글 쓰기
 public static void boardwriter() {
	 
	 if (loginid == null) {
		 System.out.println("로그인 해주세요");
		 login();
	 }
	 if (loginid == null) return;
	 
	 
	 System.out.println("게시글 제목:");
	 String title = sc.nextLine();
	 
	 System.out.println("게시글 내용:");
	 String content = sc.nextLine();
	 
	 String sql = "insert into board(bno,title,content, writer) values(board_seq.nextval,?,?,?)";
	 
	 try {
		  
		  Connection conn = DBConnection.getConnection();
		 PreparedStatement pstmt = conn.prepareStatement(sql);  //sql 문장을 안전하게 실행하기 위한 생성 클래스
		 
		 pstmt.setString(1, title);
		 pstmt.setString(2, content);
		 pstmt.setString(3, loginid);
		 
		 int result = pstmt.executeUpdate();  //select 로 할때는 Executequary로 실행한다 //sql문이 정상 작동하면 정수 1이 반환됨
		 
		 if(result ==1) {
			 System.out.println("글쓰기 성공");
		 }else {
			 System.out.println("글쓰기 실패");
		 }
		 conn.close(); // connection 닫기
		  
	  }catch(Exception e) {
		  System.out.println("게시글 작성 샐패");
		  e.printStackTrace();
		  
	  }
	  
 
	
	  
 }
 
 // 게시판 목록 정보 조회하기
 
 public static void boardlist() {
 
 String sql = "select * from board";
 
 
 try {
	  Connection conn = DBConnection.getConnection();
		 PreparedStatement pstmt = conn.prepareStatement(sql);
		 
		 ResultSet rs = pstmt.executeQuery();  // rs는 select 문의 답을 받는 그릇이다.
		 
		 while(rs.next()) {
			 
			 System.out.println(" 회원은 존재합니다");
			 
		int bnum = rs.getInt("bno");
		String btitle	= rs.getString("title");
		String bcontent = rs.getString("content");
		String bwriter = rs.getString("writer");
		String bdate = rs.getString("regdate");
		
		
		System.out.println("---------------------");
		System.out.println("번호"+bnum );
		System.out.println("제목"+btitle );
		System.out.println("번호"+bcontent );
		
			 
		
		

			 
		 }
		 System.out.println("---------------------");
		 
					
} catch (Exception e) {
	System.out.println("회원정보 조회실패");
}
 
}

 // 게시글 삭제 메서드
 // 삭제할 게시글의 번호 입력 : 3
 // 게시글 삭제 성공!
 //<조건>
 // 1. 로그인한 유저만 삭제 가능하도록 구현
 // 2. 로그인한 유저 본인이 작성한 글만 삭제하도록 구현
 // -> 게시글이 없는 번호 이거나 본인이 작성한 글만 삭제 가능합니다
 
 
 public static void boardDelete() {
	 
	 if (loginid == null) {
		 System.out.println("로그인 해주세요");
		 login();
	 }
	 if (loginid == null) return;
	 
	 
	  System.out.println("삭제할 번호를 입력하세요");
	  String bno = sc.nextLine();
	  
	  String sql = "delete from board where bno = ? and writer = ?";
	  
	  try {
		  Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, bno);
			 pstmt.setString(2, loginid);
			 
			 int result = pstmt.executeUpdate();
			 if(result ==1) {
				 System.out.println("글 삭제 성공");
			 }else {
				 System.out.println("게시글이 없는 번호 이거나 본인이 작성한 글만 삭제 가능합니다");
			 }
			 conn.close(); // connection 닫기
		
		
	} catch (Exception e) {
		System.out.println("삭제 실패");
	}
 }
	  
   
 
 //게시글 상세 보기 메소드
 
 public static void contentView() {
	  
	  System.out.println("조회할 게시글 내용 조회 번호");
	 int bno = Integer.parseInt(sc.nextLine());  
	  // bno가 문장ㄹ이므로 정수로 변환
	 
	
	  String sql = "select * from member where userid = ?";
	  
	  
	  try {
		  Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setInt(1, bno);
			 
			 ResultSet rs = pstmt.executeQuery();  // rs는 select 문의 답을 받는 그릇이다.
			 
			 if(rs.next()) {
				 
				
				 
			int bnum = rs.getInt("bno");
			String btitle	= rs.getString("title");
//			String mname = rs.getString("username");
//			String mdate = rs.getString("regdate");
			
		
			System.out.println("---------------------");
			System.out.println(bno + "게시글 번호" + btitle + "게시글 제목");
				 
				 
			 }else {
				 System.out.println(" 게시글이 존재 하지 않는다");
			 }
			 
			 
			 rs.close(); // rs 닫기
			 conn.close(); // connection 닫기
			 
			
			 
			 
			 
		  
		  
		
	} catch (Exception e) {
		System.out.println("게시글 조회실패");
	}
	  
 }

 // 메뉴 10. 게시글  수정 기능 (회원정보 수정 참조, delete 참조)
 // 로그인한 유저만 수정 가능
 //본인이 작성한 글만 수정 가능
 // 제목과 내용만 수정 가능
 // 수정하고 싶은 글 번호: 3
 
 //실패->존재하지 않는 글번호 이거나 본인이 작성하지 않은 글
 
 // 메뉴 11 전체 게시글의 갯수 출력 (count 집계 함수 사용)
 // "게시판 전체글의 갯수는 17개 입니다"
 
 //메뉴 12 로그인한 유저의 글 목록만 조회 출력(게시글 목록 보기에서 where loginid 개념)
 // 게시글 목록 보기를 -> 수정
 // 도전 문제* 메뉴 13: 게시판 글 검색 기능 추가 ->검색할 단어를 입력하세요
 //제목 또는 내용에 "거북이"단어가 포함된 글의 목록만 출력
 
 
	 
 
//메뉴 10. 게시글  수정 기능 (회원정보 수정 참조, delete 참조)
// 로그인한 유저만 수정 가능
//본인이 작성한 글만 수정 가능
// 제목과 내용만 수정 가능
// 수정하고 싶은 글 번호: 3

//실패->존재하지 않는 글번호 이거나 본인이 작성하지 않은 글
 
 
 public static void boardUpdate() {
	 
	 if(loginid == null) {
		 System.out.println("로그인 먼저 해주세요");
		 login();
	 }
	 if(loginid == null) {
		 return;
	 }
	  
	 System.out.println("변경할 글 번호 입력");
	  String bno = sc.nextLine();
	  System.out.println("변경할 제목 입력");
	  String title = sc.nextLine();
	 
	  
	  String sql = "update board set title = ? where bno = ? and writer = ?";
	  
	  try {
		  Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, title);
			 pstmt.setString(2, bno);
			 pstmt.setString(3, loginid);
			 
			 int result = pstmt.executeUpdate();
			 if(result ==1) {
				 System.out.println("게시글 수정 성공");
			 }else {
				 System.out.println("수정하려는 글이 없다");
			 }
			 
			 conn.close(); // connection 닫기
		
		
	} catch (Exception e) {
		System.out.println("수정 실패");
	}
	  
	  
 }
//메뉴 11 전체 게시글의 갯수 출력 (count 집계 함수 사용)
// "게시판 전체글의 갯수는 17개 입니다"
 
 
	 public static void totalBoardcount() {
		 
		 if(loginid == null) {
			 System.out.println("로그인 먼저 해주세요");
			 login();
		 }
		 if(loginid == null) {
			 return;
		 }
		 
		
		  String sql = "select count(*) from board ";
		  
		  
		  try {
			  Connection conn = DBConnection.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 
				
				 
				 ResultSet rs = pstmt.executeQuery();  // rs는 select 문의 답을 받는 그릇이다.
				 
//				 int count  = 0;
				 if(rs.next()) {
					 
				int count =	 rs.getInt("count(*)");
				 System.out.println("총 게시글의 갯수:" + count);
//					count++;
					 
//				int bnum = rs.getInt("bno");
//				String btitle	= rs.getString("title");
////				String mname = rs.getString("username");
////				String mdate = rs.getString("regdate");
//				
			
//				System.out.println("---------------------");
//				System.out.println(bno + "게시글 번호" + btitle + "게시글 제목");
					 
					 
				 }
				 
//				 System.out.println("총 게시글의 갯수:" + count);
				 
				 
				 rs.close(); // rs 닫기
				 conn.close(); // connection 닫기
				 
				
				 
				 
				 
			  
			  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
	 }
		 
	//메뉴 12 로그인한 유저의 글 목록만 조회 출력(게시글 목록 보기에서 where loginid 개념)
	 // 게시글 목록 보기를 -> 수정
	 
	 
	 
	// 게시판 목록 조회 메서드에서  sql문에서 where writer = ?  
	 
 
	
	 // 도전 문제* 메뉴 13: 게시판 글 검색 기능 추가 ->검색할 단어를 입력하세요
	 //제목 또는 내용에 "거북이"단어가 포함된 글의 목록만 출력
	  
	 
	 public static void boardserch() {
		 
		 System.out.println("검색 할 키워드 입력:");
		 String keyword = sc.nextLine();
		 
		 String sql = "select * from board where title like ? or content like ?";
		 
		 
		 try {
			  Connection conn = DBConnection.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 
				 pstmt.setString(1,"%"+ keyword + "%");
				 pstmt.setString(2,"%"+ keyword + "%");
				 
				 ResultSet rs = pstmt.executeQuery();  // rs는 select 문의 답을 받는 그릇이다.
				 
				 while(rs.next()) {
					 
//					 System.out.println(" 회원은 존재합니다");
					 
				int bnum = rs.getInt("bno");
				String btitle	= rs.getString("title");
				String bcontent = rs.getString("content");
				String bwriter = rs.getString("writer");
				String bdate = rs.getString("regdate");
				
				
				System.out.println("---------------------");
				System.out.println("번호"+bnum );
				System.out.println("제목"+btitle );
				System.out.println("번호"+bcontent );
				
					 
				
				

					 
				 }
				 System.out.println("---------------------");
				 
							
		} catch (Exception e) {
			System.out.println(" 조회실패");
		}
		 
		}
	  
	
	  public static void main(String[] args) {
//		    join();
//		  memberDelete();
//		  memberUpdate();
//		  memberinfo();
//		  allmemberinfo();
//		  login();
//		  boardwriter();
//		  boardlist();
//		  boardDelete();
//		  contentView();
//		    boardUpdate();
//		    totalBoardcount();
		  boardserch();
		  
		  
		  
		  
//		  while (true) {
//				 System.out.println("");
//				 System.out.println("=========== JAVA +MySQL 게시판");
//				 System.out.println("1. 회원가입");
//				 System.out.println("2. 로그인");
//				 System.out.println("3. 회원정보수정");
//				 System.out.println("4. 회원탈퇴");
//				 System.out.println("5. 게시글 목록");
//				 System.out.println("6. 게시글 내용 보기 ");
//				 System.out.println("==================");
//				 
//				 
//				 
//				 System.out.println("메뉴 선택");
//				 String menu = sc.nextLine();
//				 
//				 if(menu.equals("1")) {
//					 join();
//				 }
				 
			 
			 
			 }
			 
		}
	


