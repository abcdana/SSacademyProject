package com.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.VwStudentTestScoreDTO;
import com.project.ssacademy.DBUtil;

/**
 * 관리자의 성적조회를 관리를 위해 사용하는 DAO클래스이다.
 * @author 조성진
 *
 */
public class AdminTestScoreManagementDAO {

	
	/**
	 * 성적관련 정보를 리스트로 만들어 반환하는 메서드
	 * @return 성적관련정보를 담은 리스트
	 */
	public ArrayList<VwStudentTestScoreDTO> list() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();

			String sql = String.format(
					"select distinct seqOpenSubject, roomName, teacherName,subjectName,subjectStart, subjectEnd from VwStudentTestScore order by seqOpenSubject");
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();

			while (rs.next()) {
				// 레코드 1개 -> AddressDTO 1개
				VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
				dto.setSubjectStart(rs.getString("subjectStart"));
				dto.setSubjectEnd(rs.getString("subjectEnd"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				dto.setTeacherName(rs.getString("teacherName"));
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

	public ArrayList<VwStudentTestScoreDTO> list2(String subSeq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();

			String sql = String.format(
					"select distinct stuseq, studentname, writtenscore , practicalscore, attendancescore, studentstate from vwSubjectScoreInquiry where SEQOPENSUBJECT = %s order by stuseq",subSeq);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();

			while (rs.next()) {
				// 레코드 1개 -> AddressDTO 1개
				VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
				dto.setStuSeq(rs.getString("stuseq"));
				dto.setStudentName(rs.getString("studentname"));
				dto.setWrittenScore(rs.getString("writtenscore"));
				dto.setPracticalScore(rs.getString("practicalscore"));
				dto.setAttendanceScore(rs.getString("attendancescore"));
				dto.setStudentState(rs.getString("studentstate"));
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
	
	
	public ArrayList<VwStudentTestScoreDTO> list3(String sSeq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();

			String sql = String.format(
					"select distinct stuseq, studentname, writtenscore , practicalscore, attendancescore, studentstate from vwSubjectScoreInquiry where stuseq = %s order by stuseq",sSeq);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();

			while (rs.next()) {
				// 레코드 1개 -> AddressDTO 1개
				VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
				dto.setStuSeq(rs.getString("stuseq"));
				dto.setStudentName(rs.getString("studentname"));
				dto.setWrittenScore(rs.getString("writtenscore"));
				dto.setPracticalScore(rs.getString("practicalscore"));
				dto.setAttendanceScore(rs.getString("attendancescore"));
				dto.setStudentState(rs.getString("studentstate"));
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
	
	public ArrayList<VwStudentTestScoreDTO> list4(String subSeq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();

			String sql = String.format(
					"select distinct seqOpenSubject, subjectName, writtenPercent , practicalPercent, attendancePercent from VwStudentTestScore where SEQOPENSUBJECT = %s",subSeq);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<VwStudentTestScoreDTO> list = new ArrayList<VwStudentTestScoreDTO>();

			while (rs.next()) {
				// 레코드 1개 -> AddressDTO 1개
				VwStudentTestScoreDTO dto = new VwStudentTestScoreDTO();
				dto.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setWrittenPercent(rs.getString("writtenPercent"));
				dto.setPracticalPercent(rs.getString("practicalPercent"));
				dto.setAttendancePercent(rs.getString("attendancePercent"));
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
