package com.project.ssacademy;

import java.sql.Connection;
import java.sql.DriverManager;

//자바의 주석
//1. 단일 라인 주석
/*
  2. 다중 라인 주석
 */


/**
 * DBUtil. 데이터베이스 연결 클래스입니다.
 * @author 김주혁
 *
 */
public class DBUtil {

	private static Connection conn;
/**
 * 데이터 베이스 연결 메소드입니다.
 * @return 연결된 Connection 객체
 */
	public static Connection open() {
		//java.sql.SQLException: 부적합한 Oracle URL이 지정되었습니다
		//String url = "jdbc:oracle:thin:@211.63.89.55:1521:xe";
		
		//java.sql.SQLRecoverableException: IO 오류: The Network Adapter could not establish the connection
		//Caused by: java.net.ConnectException: Connection timed out: connect
		//String url = "jdbc:oracle:thin:@211.63.89.55:1521:xe";
		
		//java.sql.SQLException: Listener refused the connection with the following error:
		//ORA-12505, TNS:listener does not currently know of SID given in connect descriptor
		//오라클 서버 꺼져있을 때 뜸 Listener refused 10에 9은 오라클 꺼져있는거
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "project";
		String pw = "project1234";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, pw);
			//conn.setAutoCommit(false);
			
			return conn;

		} catch (Exception e) {
			System.out.println("DBUtil.open()");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 데이터 베이스 연결 메소드입니다.
	 * @param server 접속할 서버 주소입니다.
	 * @param id 접속할 계정명입니다.
	 * @param pw 접속할 비밀번호입니다.
	 * @return 연결된 Connection 객체를 반환합니다.
	 */
	public static Connection open(String server, String id, String pw) {

		String url = "jdbc:oracle:thin:" + server + ":1521:xe";
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, pw);

			return conn;

		} catch (Exception e) {
			System.out.println("DBUtil.open()");
			e.printStackTrace();
		}

		return null;
	}

}
