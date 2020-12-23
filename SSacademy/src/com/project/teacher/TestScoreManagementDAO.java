package com.project.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.project.dto.VwSubjectScoreInquiryDTO;
import com.project.ssacademy.DBUtil;

public class TestScoreManagementDAO {
	
public ArrayList<VwSubjectScoreInquiryDTO> list(String tSeq, String num) {
		
	
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();
			String where = String.format("where subjectEnd < sysdate and Tseq = %s and seqOpenSubject = %s",tSeq,num);
			

			String sql = String.format("select distinct * from vwSubjectScoreInquiry %s",where);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<VwSubjectScoreInquiryDTO> list = new ArrayList<VwSubjectScoreInquiryDTO>();
			
			while(rs.next()) {
				//레코드 1개 -> AddressDTO 1개
				VwSubjectScoreInquiryDTO dto = new VwSubjectScoreInquiryDTO();
				
				dto.setStuSeq(rs.getString("stuSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setWrittenScore(rs.getString("writtenScore"));
				dto.setPracticalScore(rs.getString("practicalScore"));
				dto.setAttendanceScore(rs.getString("attendanceScore"));
				dto.setStudentState(rs.getString("studentState"));
				list.add(dto);
			}
			
			//list는 rs랑 동일하게 변한다.
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	public int subScoreEdit(String subnum, String stunum, String wnum, String pnum, String anum) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();
			String sql = "{ call procScoreUpdate(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, subnum);
			stat.setString(2, stunum);
			stat.setString(3, wnum);
			stat.setString(4, pnum);
			stat.setString(5, anum);


			stat.close();
			conn.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
		
	}


}
