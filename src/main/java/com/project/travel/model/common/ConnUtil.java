package com.project.travel.model.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnUtil {

	private static DataSource ds;
	
	static{
		try {			
			// JNDI를 이용하기 위해 InitialContext객체 생성
			Context initContext = new InitialContext();
			
			/*
				JNDI - 클라이언트가 이름을 통해 데이터나 객체를 이용할 수 있도록 도와주는 디렉터리 서비스에 대한 자바 API
				네이밍 서비스 - JNDI를 통해 이름을 이용하여 네이밍 서버에서 해당 자원을 찾을 수 있도록 도와주는 서비스
				lookup() - naming 서비스를 지원하는 naming서버에 등록된 자원을 찾아 Object 타입으로 반환하는 메서드 
				
				java:comp/env - tomcat서버에서 자원을 관리하는 가상의 디렉터리
			*/
			ds = (DataSource)initContext.lookup("java:comp/env/jdbc/myoracle");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		// DataSource객체를 통해 DBCP에 저장된 Connection 획득
		return ds.getConnection();
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		
		if(stmt!=null) {
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				stmt = null;
			}
		}
		
		if(conn!=null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
		
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		
		if(rs!=null) {
			try {
				if(!rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				rs = null;
			}
		}
		
		if(stmt!=null) {
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				stmt = null;
			}
		}
		
		if(conn!=null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
		
	}

}
