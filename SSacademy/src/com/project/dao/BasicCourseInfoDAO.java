package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.ssacademy.DBUtil;

/**
 * 기초과정정보 데이터베이스와 관련된 비즈니스업무 DAO
 * @author 조혜승
 *
 */
public class BasicCourseInfoDAO {
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	CallableStatement cstat = null;
	ResultSet rs = null;
	
	public BasicCourseInfoDAO() {
		
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
