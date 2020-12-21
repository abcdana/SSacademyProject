package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.ssacademy.DBUtil;

//관리자정보
public class AdminDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public AdminDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.AdminDAO()");
			e.printStackTrace();
		}
		
	}
	
}