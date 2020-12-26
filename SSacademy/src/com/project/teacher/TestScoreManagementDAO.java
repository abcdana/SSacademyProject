package com.project.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.project.dto.VwStudentTestScoreDTO;
import com.project.ssacademy.DBUtil;

public class TestScoreManagementDAO {
	
public ArrayList<VwStudentTestScoreDTO> list(String tSeq, String num) {
		
	
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();
			String where = String.format("where subjectEnd < sysdate and Tseq = %s and seqOpenSubject = %s",tSeq,num);
			

			String sql = String.format("select distinct * from vwSubjectScoreInquiry %s",where);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();
			
			while(rs.next()) {
				//레코드 1개 -> AddressDTO 1개
				VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
				dto.setTestSeq(rs.getString("testSeq"));
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

public ArrayList<VwStudentTestScoreDTO> list2(String tSeq) {
	
	
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	try {
		conn = DBUtil.open();
		String where = String.format("where subjectEnd < sysdate and Tseq = %s",tSeq);
		

		String sql = String.format("select distinct * from vwSubjectScoreInquiry %s order by studentName",where);
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();
		
		while(rs.next()) {
			//레코드 1개 -> AddressDTO 1개
			VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
			dto.setTestSeq(rs.getString("testSeq"));
			dto.setStuSeq(rs.getString("stuSeq"));
			dto.setStudentName(rs.getString("studentName"));
			dto.setSubjectName(rs.getString("subjectName"));
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

			stat.executeUpdate(); 


			stat.close();
			conn.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	
public int subScoreEdit2(String testnum, String wnum, String pnum, String anum) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();
			String sql = "{ call procScoreUpdate2(?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, testnum);
			stat.setString(2, wnum);
			stat.setString(3, pnum);
			stat.setString(4, anum);

			stat.executeUpdate(); 
			
			System.out.println(stat.executeUpdate());
			
			stat.close();
			conn.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
		
	}



}
