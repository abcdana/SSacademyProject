package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.project.admin.DBUtil;
import com.project.dto.AttendanceDTO;
/**
 * 출결데이터 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class AttendanceDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat; 
	private ResultSet rs;

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public AttendanceDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enAttendanceDAO()");
			e.printStackTrace();
		} 		
	}
	
	/**
	 * 출결데이터 입력을 위한
	 */
	public int add(AttendanceDTO dto) {
		
		try {
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enadd()");
			e.printStackTrace();
		}
		
		return 0;
		
	}
}
