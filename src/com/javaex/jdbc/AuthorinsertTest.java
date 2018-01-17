package com.javaex.jdbc;
import java.sql.*;
public class AuthorinsertTest {

	public static void main(String[] args) {
		// 0. 
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into author values(seq_author_id.nextval, ? , ? )";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,"김영하");//첫번째 물음표 작업형 
			pstmt.setString(2, "알쓸신잡");
			//pstmt.executeUpdate();//두개 다 조합함 
			
			int count = pstmt.executeUpdate();      //insert, update, delete
			//ResultSet rs = pstmt.executeQuery(); //select

			// 4.결과처리
			
			System.out.println(count +"저장완료");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*if (rs != null) {
					rs.close();
				}*///select할때만 써줌 
				if (pstmt != null) {//null이 아니면 닫아라 
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

}
