package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.dto.TeacherDTO;
import com.project.ssacademy.DBUtil;


/**
 * 교사 계졍과 관련된 기능을 담당하는 DAO
 * @author 김주혁, 김다은
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

	
	/**
	 * 
	 * @param id
	 * @return Teacher DTO (해당 id의 교사 정보)
	 */
	public TeacherDTO getTeacher(String id) {

		try {
			
			String sql = "select * from tblTeacher where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryTeacherDAO.engetTeacher()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
