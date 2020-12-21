package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.ssacademy.DBUtil;


/**
 * 교사 계졍과 관련된 기능을 담당하는 DAO
 * @author 김주혁
 *
 */
public class TeacherDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 생성자
	 */
	public TeacherDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.TeacherDAO()");
			e.printStackTrace();
		}
		
	} //TeacherDAO
	
	
	
}
