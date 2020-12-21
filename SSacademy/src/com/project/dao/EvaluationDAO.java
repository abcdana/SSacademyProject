package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.ssacademy.DBUtil;

//평가정보
public class EvaluationDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public EvaluationDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();			
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.EvaluationDAO()");
			e.printStackTrace();
		}
		
	}
	
}