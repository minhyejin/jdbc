package com.javaex.jdbc;
import java.sql.*;

public class BookSelectTest {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select b.book_id, b.title, b.pubs, to_char(b.pub_date,'YYYY/MM/DD') pub_date, b.author_id, a.author_name, a.author_desc " + 
		    		" from book b, author a " + 
		    		" where a.author_id = b.author_id ";
		    
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
		    // 4.결과처리

		    while(rs.next()) {
			    int bookId = rs.getInt("book_id");
			    String title = rs.getString("title");
			    String pubs = rs.getString("pubs");
			    String pubDate = rs.getString("pub_date");
			    int authorId = rs.getInt("author_id");
			    String authorName = rs.getString("author_name");
			    String authorDesc = rs.getString("author_desc");
			   
			    System.out.println(bookId + " . " + title +" | "+ pubs +" | "+ pubDate +" | "+ authorId +
			    	" | "+	authorName +" | "+ authorDesc);
			    System.out.println("=================================================================");
			   } 
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
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
