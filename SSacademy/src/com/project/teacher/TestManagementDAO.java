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
import com.project.teacher.dto.VwTestPercentInquiryDTO;

public class TestManagementDAO {
	
	
	
	
	public ArrayList<VwSubjectInquiryDTO> list(String tSeq,String time) { 
		

		
		Connection conn = null;
		Statement stat = null;
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
	
	public ArrayList<VwTestPercentInquiryDTO> list2(String subSeq) {// 특정과목에대한 배점정보 출력
		

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			String where = String.format("where subSeq = %s",subSeq);
			
			
			String sql = String.format("select * from VwTestPercentInquiry %s",where);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<VwTestPercentInquiryDTO> list = new ArrayList<VwTestPercentInquiryDTO>();
			
				//레코드 1개 -> AddressDTO 1개
			if(rs.next()) {
				VwTestPercentInquiryDTO dto = new VwTestPercentInquiryDTO();
				dto.setTpSeq(rs.getString("tpSeq"));
				dto.setBtSeq(rs.getString("btSeq"));
				dto.setSubSeq(rs.getString("subSeq"));
				dto.setWritten(rs.getString("written"));
				dto.setPractical(rs.getString("practical"));
				dto.setAttendance(rs.getString("attendance"));
				dto.setWrittenDate(rs.getString("writtenDate"));
				dto.setPracticaldate(rs.getString("practicaldate"));
				list.add(dto);
			}
			//list는 rs랑 동일하게 변한다.
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
public int subScoreEdit(String subnum, String wpercent, String ppercent, String apercent) { // 배점관련 업데이트 프로시저
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();
			String sql = "{ call procTestPercent(?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, subnum);
			stat.setString(2, wpercent);
			stat.setString(3, ppercent);
			stat.setString(4, apercent);

			stat.executeUpdate(); 


			stat.close();
			conn.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
		
	}

public int subWrittenDateUpdate(String subnum, String writtenDate) {
	Connection conn = null;
	CallableStatement stat = null;
	
	try {
		
		conn = DBUtil.open();
		String sql = "{ call procWrittenDate(?, ?) }";
		
		stat = conn.prepareCall(sql);
		
		stat.setString(1, subnum);
		stat.setString(2, writtenDate);


		stat.executeUpdate(); 


		stat.close();
		conn.close();

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return 0;
	
}

public int subPracticalDateUpdate(String subnum, String PracticalDate) {
	Connection conn = null;
	CallableStatement stat = null;
	
	try {
		
		conn = DBUtil.open();
		String sql = "{ call procPracticalDate(?, ?) }";
		
		stat = conn.prepareCall(sql);
		
		stat.setString(1, subnum);
		stat.setString(2, PracticalDate);


		stat.executeUpdate(); 


		stat.close();
		conn.close();

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return 0;
	
}

	
}
