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

/**
 * 교사가 배점을 관리하기위해 사용하는DAO
 * @author 조성진
 *
 */
public class TestManagementDAO {
	
	
	
	/**
	 * 교사의 배점 관련 데이터를 리스트로 만들어 반환해주는 메서드 
	 * @param tSeq 로그인한 교사 번호
	 * @param time 현재 날짜 정보
	 * @return 로그인한 강사의 배점관련 정보
	 */
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
	
	
	/**
	 * 교사의 특정 과목의 배점관련 데이터를 리스트로 만들어 주는 메서드
	 * @param subSeq 로그인한 강사의 번호
	 * @return 로그인한 강사의 특정배점관련 정보
	 */
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
	
	/**
	 * 교사의 특정 과목의 배점정보를 업데이트하는 메서드
	 * @param subnum 특정과목 번호
	 * @param wpercent 사용자가 입력한 필기배점 점수
	 * @param ppercent 사용자가 입력한 실기배점 점수
	 * @param apercent 사용자가 입력한 출결배점 점수
	 * @return 업데이트가 됐는지 안됐는지 확인하기위한 넘버
	 */
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


	/**
	 * 교사의 특정 과목의 필기시험정보를 업데이트하는 메서드
	 * @param subnum 특정과목 번호
	 * @param writtenDate 사용자가 입력한 필기시험날짜
	 * @return 업데이트 확인을 위한 넘버
	 */
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

/**
 * 교사의 특정 과목의 실기시험정보를 업데이트하는 메서드
 * @param subnum 특정과목 번호
 * @param PracticalDate 사용자가 입력한 실기시험날짜 데이터
 * @return 업데이트 확인을 위한 넘버
 */
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
