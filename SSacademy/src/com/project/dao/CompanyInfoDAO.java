package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.project.ssacademy.DBUtil;
/**
 * 연계기업정보 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class CompanyInfoDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat; 
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public CompanyInfoDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryCompanyInfoDAO.enCompanyInfoDAO()");
			e.printStackTrace();
		}
	}

}
