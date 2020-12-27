package com.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.VwStudentTestScoreDTO;
import com.project.ssacademy.DBUtil;

/**
 * 학생의 성적정보와 배점정보를 관리하기위해 사용하는 DAO클래스이다.
 * @author 조성진
 *
 */
public class VwStudentTestScoreDAO {
	
/**
 * 학생의 성적정보와 배점정보를 리스트로 만들어 반환해주는 메서드
 * @param stuSeq 학생번호
 * @return 성적정보와 배점정보 리스트
 */
public ArrayList<VwStudentTestScoreDTO> list(String stuSeq) {
		

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			String where = String.format("where stuSeq = %s",stuSeq);
			
			
			String sql = String.format("select * from vwStudentTestScore %s",where);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();
			
				//레코드 1개 -> AddressDTO 1개
			while(rs.next()) {
				VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setSubjectStart(rs.getString("subjectStart"));
				dto.setSubjectEnd(rs.getString("subjectEnd"));
				dto.setWrittenScore(rs.getString("writtenScore"));
				dto.setPracticalScore(rs.getString("practicalScore"));
				dto.setAttendanceScore(rs.getString("attendanceScore"));
				dto.setWrittenPercent(rs.getString("writtenPercent"));
				dto.setPracticalPercent(rs.getString("practicalPercent"));
				dto.setAttendancePercent(rs.getString("attendancePercent"));
				dto.setStudentState(rs.getString("studentState"));
				dto.setRoomName(rs.getString("roomName"));
				
				list.add(dto);
			}
			rs.close();
			stat.close();
			conn.close();
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
