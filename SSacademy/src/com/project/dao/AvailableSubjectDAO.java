package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.AvailableSubjectDTO;
import com.project.dto.BasicSubjectDTO;
import com.project.ssacademy.DBUtil;
/**
 * 강의가능과목관련 모든 프로시저를 관리하는 DAO
 * @author 박지현
 *
 */
public class AvailableSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public AvailableSubjectDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("AvailableSubject.AvailableSubject()");
			e.printStackTrace();
		}

	}

	public int add(String seqTeacher, String[] availableSubjectList) {
		
		try {
			
			int result = 0;
			
			String sql = "{ call procAddAvailableSubject(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			for (int i=0; i<availableSubjectList.length; i++) {
				if (!availableSubjectList[i].equals("0")) {
					cstat.setString(1, seqTeacher);
					cstat.setString(2, availableSubjectList[i]);
					cstat.executeUpdate();
					result++;
				}
			}
			
			cstat.close();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("AvailableSubjectDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public int edit(String seqTeacher, String oldSeq, String newSeq) {
		
		try {
			
			String sql = "{ call procUpdateAvailableSubject(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqTeacher);
			cstat.setString(2, oldSeq);
			cstat.setString(3, newSeq);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AvailableSubjectDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
