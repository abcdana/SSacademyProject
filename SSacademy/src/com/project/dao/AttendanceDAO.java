package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;

import com.project.dto.PeriodAttendListDTO;
import com.project.dto.ViewStudentDTO;
/**
 * 출결데이터 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class AttendanceDAO {
	
	private Connection conn;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 기본 생성자 Connection을 생성한다.
	 */
	public AttendanceDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enAttendanceDAO()");
			e.printStackTrace();
		} 		
	}
	

	

	/**
	 * 해당 과정을 수강하는 교육생들의 정보를 출력하는 메서드이다.
	 * 교육생번호, 이름, ID, PW(주민번호 뒷자리), 교육생 등록상태, 교육생 수강상태를 포함한다.
	 * @param seqOpenCourse 개설 과정 번호
	 * @return 교육생 정보 ArrayList
	 */
	public ArrayList<ViewStudentDTO> attStudentList(String seqOpenCourse) {
		
		try {
			
			ArrayList<ViewStudentDTO> result = new ArrayList<ViewStudentDTO>();
			
			String sql = "{ call procStudentlist(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setString(2, seqOpenCourse);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(1);
			
			
			while (rs.next()) {
					
				ViewStudentDTO dto = new ViewStudentDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setStudentState(rs.getString("studentState"));
					
				result.add(dto);
			}
	
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enstudent()");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 * 
	 * @param seqOpenCourse	개설 과정 번호
	 * @param seqStudent	교육생 번호
	 * @param startDate		기간별조회 (시작일)
	 * @param endDate		기간별조회 (종료일)
	 * @return result 
	 */
	public ArrayList<PeriodAttendListDTO> attPeriodList(String seqOpenCourse, String seqStudent, String startDate,
			String endDate) {

		try {
			
			ArrayList<PeriodAttendListDTO> result = new ArrayList<PeriodAttendListDTO>();
			String sql = "{ call procAttPeriodList(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setString(2, seqOpenCourse);
			cstat.setString(3, seqStudent);
			cstat.setString(4, startDate);
			cstat.setString(5, endDate);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(1);

			while (rs.next()) {
				
				PeriodAttendListDTO paldto = new PeriodAttendListDTO();
				
				paldto.setAttendDate(rs.getString("attendDate"));
				paldto.setInTime(rs.getString("inTime"));
				paldto.setOutTime(rs.getString("outTime"));
				paldto.setAttendState(rs.getString("attendState"));
				
				result.add(paldto);
				
			}
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enattPeriodList()");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 관리자모드에서 특정학생의 특정일 수강상태를 수정하는 메서드이다.
	 * @param seqStudent	교육생 번호
	 * @param attendanceDate 출결 날짜
	 * @param attendState	 출결 상태
	 * @return 성공 여부
	 */
	public int updateAttendState(String seqStudent, String attendanceDate, String attendState) {
		
		try {
			
			//ArrayList<AttendanceDTO> dto2 = new ArrayList<AttendanceDTO>();
			String sql = "{ call procUpdateAttState(?, ?, ?) }"; 
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqStudent);
			cstat.setString(2, attendanceDate);
			cstat.setString(3, attendState);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enupdateAttendState()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	/**
	 * 교육생 모드의 입퇴실 체크 기능을 담당하는 메서드이다.
	 * @param seqStudent 교육생 번호
	 * @return 성공 여부
	 */
	public int addAttendance(String seqStudent) {
		
		try {
			
			String sql = "{ call procAddAttendance(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqStudent);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enenclosing_method()");
			e.printStackTrace();
		}
		return 0;
	}



	/**
	 * 특정 교육생의 월별 출결 정보를 출력하는 메서드이다.
	 * 출결날짜, 입실시간, 퇴실시간, 출결상태를 포함한다. 
	 * @param seqStudent 교육생 번호
	 * @param year		 출결정보를 조회할 년도
	 * @param month		 출결정보를 조회할 월	
	 * @return 성공 여부
	 */
	public ArrayList<PeriodAttendListDTO> studentAttPeriodList(String seqStudent, String seqOpenCourse, String year, String month) {
		
		try {

			ArrayList<PeriodAttendListDTO> result = new ArrayList<PeriodAttendListDTO>();
			String sql = "{ call procSattListByMonth(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setString(2, seqStudent);
			cstat.setString(3, seqOpenCourse);
			cstat.setString(4, year);
			cstat.setString(5, month);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(1);
			
			while (rs.next()) {
			
				PeriodAttendListDTO paldto = new PeriodAttendListDTO();
				
				paldto.setAttendDate(rs.getString("attendDate"));
				paldto.setInTime(rs.getString("inTime"));
				paldto.setOutTime(rs.getString("outTime"));
				paldto.setAttendState(rs.getString("attendState"));
				
				result.add(paldto);
				
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enattPeriodList()");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 * 전체 조회
	 * @param seqStudent
	 * @return
	 */
	public ArrayList<PeriodAttendListDTO> attPeriodList(String seqStudent, String seqOpenCourse) {
		
		try {

			ArrayList<PeriodAttendListDTO> result = new ArrayList<PeriodAttendListDTO>();
			String sql = "{ call procAllAttList(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setString(2, seqStudent);
			cstat.setString(3, seqOpenCourse);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(1);
			
			while (rs.next()) {
			
				PeriodAttendListDTO paldto = new PeriodAttendListDTO();
				
				paldto.setAttendDate(rs.getString("attendDate"));
				paldto.setInTime(rs.getString("inTime"));
				paldto.setOutTime(rs.getString("outTime"));
				paldto.setAttendState(rs.getString("attendState"));
				
				result.add(paldto);
				
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryAttendanceDAO.enattPeriodList()");
			e.printStackTrace();
		}
		
		return null;
	}

}
