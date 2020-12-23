package com.project.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.VwSubjectInquiryDTO;
import com.project.ssacademy.DBUtil;

public class TestManagementDAO {
	
//	private Connection conn;
//	private Statement stat;
//	private PreparedStatement pstat;
//	private CallableStatement cstat;
//	private ResultSet rs;
	
	
//	public TestManagementDAO() {
//		
//		try {
//			
//			this.conn = DBUtil.open();
//			this.stat = conn.createStatement();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	
	
	
	public ArrayList<VwSubjectInquiryDTO> list(String tSeq,String time) {
		
		//word -> null -> 목록 보기
		//word -> "???" -> 검색 하기
		
		//select -> ResultSet -> ArrayList<AddressDTO> -> 반환
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		CallableStatement cstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			String where = String.format("where Tseq = %s",tSeq);
			
			if(time == null) {
				time = "";
			}
			
			String sql = String.format("select * from vwSubjectInquiry %s %s",where,time);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<VwSubjectInquiryDTO> list = new ArrayList<VwSubjectInquiryDTO>();
			
			while(rs.next()) {
				//레코드 1개 -> AddressDTO 1개
				VwSubjectInquiryDTO dto = new VwSubjectInquiryDTO();
				
				dto.setCourseName(rs.getString("courseName"));
				dto.setSubName(rs.getString("subName"));
				dto.setSubStart(rs.getString("subStart"));
				dto.setSubEnd(rs.getString("subEnd"));
				dto.setSubSeq(rs.getString("subSeq"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setCourseStart(rs.getString("courseStart"));
				dto.setCourseEnd(rs.getString("courseEnd"));

				list.add(dto);
			}
			
			//list는 rs랑 동일하게 변한다.
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	
}
