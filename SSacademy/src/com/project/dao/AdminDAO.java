package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.dto.AdminDTO;
import com.project.ssacademy.DBUtil;

/**
 * 관리자와 관련된 DAO
 * @author 임채원, 김다은
 *
 */
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

	/**
	 * 
	 * @param id 
	 * @return AdminDTO (해당 id의 관리자 정보)
	 */
	public AdminDTO getAdmin(String id) {
		try {
			
			String sql = "select * from tblAdmin where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setSeqAdmin(rs.getString("seqAdmin"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryAdminDAO.engetAdmin()");
			e.printStackTrace();
		}
		return null;
	}


	
}