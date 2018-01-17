package com.javaex.jdbc;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectHrTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select em.employee_id, " + 
		    		"	   em.last_name, " + 
		    		"	   to_char(em.hire_date ,'YYYY-MM-DD')hire_date , " + 
		    		"	   man.manager_id " + 
		    		" from employees em, employees man " + 
		    		" where man.hire_date > em.hire_date " + 
		    		"      and em.manager_id = man.employee_id "
		    		+ " order by em.employee_id asc " ;
		    
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
		    // 4.결과처리

		    while(rs.next()) {
			    int employeeId = rs.getInt("employee_id");
			    String lastName = rs.getString("last_name");
			    String hireDate = rs.getString("hire_date");
			    int managerId = rs.getInt("manager_id");
			  
			   
			    System.out.println( employeeId + " . " + lastName +" | "+ hireDate +" | "+ managerId );
			    System.out.println("================================");
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
