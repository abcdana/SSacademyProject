package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.ssacademy.DBUtil;

/**
 * 시험성적정보와 관련된 기능을 관리하는 DAO
 * @author 김주혁
 *
 */
public class TestScoreDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public TestScoreDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("TestScoreDAO.TestScoreDAO()");
			e.printStackTrace();
		}
		
	}
	
}
