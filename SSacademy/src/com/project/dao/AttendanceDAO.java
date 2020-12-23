package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;

import com.project.dto.AttendanceDTO;
import com.project.dto.PeriodAttendListDTO;
import com.project.dto.StudentDTO;
import com.project.dto.ViewStudentDTO;
/**
 * 출결데이터 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class AttendanceDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat; 
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public AttendanceDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
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



}
