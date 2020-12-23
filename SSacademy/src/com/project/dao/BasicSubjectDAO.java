package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.BasicSubjectDTO;
import com.project.ssacademy.DBUtil;

/**
 * 기초과목관련 모든 프로시저를 관리하는 DAO
 * @author 박지현
 *
 */
public class BasicSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public BasicSubjectDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("BasicSubjectDAO.BasicSubjectDAO()");
			e.printStackTrace();
		}

	}

}

