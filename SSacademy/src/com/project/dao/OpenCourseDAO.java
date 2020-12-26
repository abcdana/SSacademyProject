package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.admin.OpenCourse;
import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenCourseStudentDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dto.OpenCourseDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;
/**
 * 개설과정관련 모든 프로시저를 관리하는 DAO
 * @author 조성진
 *
 */
public class OpenCourseDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public OpenCourseDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.OpenSubject()");
			e.printStackTrace();
		}
		
	}
	

	
	/**
	 * 전체개설과정 리스트입니다.
	 */
	public ArrayList<OpenCourseListDTO> openCourseList(int page) {

		try {

			int start = 1 + (page-1) * 10;	//한페이지에서 보여줄 첫번째 rownum
			int end = 10 * page; 	//한페이지에서 마지막으로 보여줄 rownum
			
			// 전체개설과정 조회 쿼리
			String sql = "select * from vTotalOpenCourse"
					+ " where num between ? and ?";

			pstat = conn.prepareStatement(sql);
			
			//rownum 변수
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			rs = pstat.executeQuery();

			ArrayList<OpenCourseListDTO> list = new ArrayList<OpenCourseListDTO>();
			
			while(rs.next()) {
				OpenCourseListDTO oc = new OpenCourseListDTO();
				
				oc.setRownum(rs.getInt("num"));
				oc.setSeqBasicCourseInfo(rs.getString("seqBasicCourse"));
				oc.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				oc.setName(rs.getString("courseName"));
				oc.setStartDate(rs.getString("startDate"));
				oc.setEndDate(rs.getString("endDate"));
				oc.setRoom(rs.getString("room"));
				oc.setMemberCount(rs.getString("countMember"));
				oc.setState(rs.getString("courseState"));

				list.add(oc);
			}
			
			rs.close();
	
			return list;
			
		} catch (SQLException e) {
			System.out.println("ArrayList<OpenCourseListDTO> OpenCourseList()");
			e.printStackTrace();
		}

		return null;
	}
	
	//과정글 총 개수
	public int getCountCourse() {
		
		int count = 0;
		
		try {

			// 전체개설과정 총 갯수
			String sql = "select count(num) as count from vTotalOpenCourse";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt("count");
				
				rs.close();
			
				return count;
			}
		
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.getCountCourse()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 특정개설과정 정보입니다.
	 * @param 개설과정 rownum
	 */
	public ArrayList<OpenCourseListDTO> specificCourse(int rownum) {

		try {

			// 특정개설과정 조회 쿼리
			String sql = "select * from vTotalOpenCourse where num = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, rownum);
			
			rs = pstat.executeQuery();
	
			ArrayList<OpenCourseListDTO> list = new ArrayList<OpenCourseListDTO>();

			while(rs.next()) {

				OpenCourseListDTO oc = new OpenCourseListDTO();

				oc.setRownum(rs.getInt("num"));
				oc.setSeqBasicCourseInfo(rs.getString("seqBasicCourse"));
				oc.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				oc.setName(rs.getString("courseName"));
				oc.setStartDate(rs.getString("startDate"));
				oc.setEndDate(rs.getString("endDate"));
				oc.setRoom(rs.getString("room"));
				oc.setMemberCount(rs.getString("countMember"));
				oc.setState(rs.getString("courseState"));

				list.add(oc);
			}
			
			rs.close();
	
			return list;
			
		} catch (SQLException e) {
			System.out.println("ArrayList<OpenCourseListDTO>.specificCourse()");
			e.printStackTrace();
		}

		return null;
	}
	
	

	/**
	 * 특정과정조회의 교육생 리스트
	 */
	public ArrayList<OpenCourseStudentDTO> openCourseStudent(String seqOpenCourse, int page) {

		try {
			
			int start = 1 + (page-1) * 10;	//한페이지에서 보여줄 첫번째 rownum
			int end = 10 * page; 	//한페이지에서 마지막으로 보여줄 rownum
			
			String sql = "select num, seqOpenCourse, seqStudent, name, ssn, tel, registDate, state"
					+ "    from vViewStudent"
					+ "        where seqOpenCourse = ? and rownum between ? and ?";	
			
			cstat = conn.prepareCall(sql);
			
			//과정번호 넣기
			cstat.setString(1, seqOpenCourse);
			//rownum
			cstat.setInt(2, start);
			cstat.setInt(3, end);
			
			//쿼리 날리기
			rs = cstat.executeQuery();
			
			
			ArrayList<OpenCourseStudentDTO> list = new ArrayList<OpenCourseStudentDTO>();
			
			while(rs.next()) {
				
				OpenCourseStudentDTO s = new OpenCourseStudentDTO();
				
				s.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				s.setSeqStudent(rs.getString("seqStudent"));
				s.setName(rs.getString("name"));
				s.setSsn(rs.getString("ssn"));
				s.setTel(rs.getString("tel"));
				s.setRegistDate(rs.getString("registDate"));
				s.setState(rs.getString("state"));
				
				list.add(s);
			
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.openCourseStudent()");
			e.printStackTrace();
		}
	
			return null; 
	}
	
	//특정과정 교육생 총 개수
		public int getCountStudent(OpenCourseListDTO dto) {
			
			int count = 0;
			
			try {

				//해당 과정 총 교육생
				String sql = "select count(*) as count from vViewStudent where seqOpenCourse = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getSeqOpenCourse());
				rs = pstat.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("count");
					rs.close();
					return count;
				}
			
			} catch (Exception e) {
				System.out.println("OpenCourseDAO.getCountStudent()");
				e.printStackTrace();
			}
			
			return 0;
		}
		
	
	/**
	 * 개설과정등록 DAO
	 */
	public int openCourseAdd(OpenCourseDTO ocdto) {
		
		try {

			//개설과정등록 프로시저 호출
			String sql = "{ call procRegistCourse2(?, ?, ?, ?, ?) }";

			cstat = conn.prepareCall(sql);

			cstat.setString(1, ocdto.getSeqRoom());
			cstat.setString(2, ocdto.getSeqBasicCourseInfo());
			cstat.setString(3, ocdto.getStartDate());
			cstat.setString(4, ocdto.getEndDate());
			cstat.setString(5, ocdto.getMemberCount());
			
			return cstat.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("OpenCourseDAO.openCourseAdd()");
			e.printStackTrace();
		}

		return 0;
		
		
	}
	
	
	
	/**
	 * 개설과정수정 - 수정 전 개설과정 정보
	 */
	public OpenCourseListDTO normalOpenCourse(int rownum) {
		
		try {
			
			String sql = "select * from vTotalOpenCourse where num = ?"; 
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, rownum);
			
			rs = pstat.executeQuery();
			
			OpenCourseListDTO ocdto = new OpenCourseListDTO();
			
			while(rs.next()) {
				
				ocdto.setRownum(rs.getInt("num"));
				ocdto.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				ocdto.setSeqBasicCourseInfo(rs.getString("seqBasicCourse"));
				ocdto.setName(rs.getString("courseName"));
				ocdto.setStartDate(rs.getString("startDate"));
				ocdto.setEndDate(rs.getString("endDate"));
				ocdto.setRoom(rs.getString("room"));
				
				return ocdto;
			}
			
					
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.normalOpenSubject()");
		}
		
		return null;
	}

	
	
	/**
	 * 개설과목수정 DAO
	 * @return 결과값 1: 수정완료 0: 수정실패
	 */
	
	public int editOpenCourse(OpenCourseListDTO ocdto) {
		
		try {
			
			String sql = "update tblOpenCourse set seqBasicCourseInfo = ?"
					+ ", startDate = ?"
					+ ", endDate = ?"
					+ ", seqRoom = ?"
							+ "where seqOpenCourse = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, ocdto.getSeqBasicCourseInfo());
			pstat.setString(2, ocdto.getStartDate());
			pstat.setString(3, ocdto.getEndDate());
			pstat.setString(4, ocdto.getSeqRoom());
			pstat.setString(5, ocdto.getSeqOpenCourse());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("OpenSubjectDAO.editOpenCourse()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	/**
	 * 강의실 중복검사 메서드
	 * 1 강의실 사용중
	 * 0 강의실 사용가능
	 */
	
	public int checkRoom(String seqRoom) {
		
		try {

			//개설과정 강의실수정 프로시저 호출
			String sql = "{ call  checkRoomState(?, ?) }";

			cstat = conn.prepareCall(sql);

			cstat.setString(1, seqRoom);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			//1 사용불가, 0 사용가능 강의실
			
			cstat.executeUpdate();
			
			//out 결과값
			int result = cstat.getInt(3);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("OpenCourseDAO.checkRoom()");
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 날짜검사메서드 
	 * 1 날짜에러
	 * 0 날짜에러 x
	 */
	
	public int checkDate(String startDate, String endDate) {
		
		try {

			//개설과정 강의실수정 프로시저 호출
			String sql = "{ call  checkDate(?, ?, ?) }";

			cstat = conn.prepareCall(sql);

			cstat.setString(1, startDate);
			cstat.setString(2, endDate);
			//1 날짜오류, 0 날짜오류 x
			cstat.registerOutParameter(3, OracleTypes.NUMBER);
			
			cstat.executeUpdate();
			
			//out 결과값
			int result = cstat.getInt(3);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("OpenCourseDAO.checkRoom()");
			e.printStackTrace();
		}

		return 0;
	}
	
	
	
	/**
	 * 개설과정삭제 메서드입니다.
	 */
	public int openCourseDelete(String seqOpenCourse) {
		
		try {

			//개설과정삭제 프로시저 호출
			String sql = "{ call procDeleteCourse(?) }";

			cstat = conn.prepareCall(sql);

			cstat.setString(1, seqOpenCourse);
			
			int result = cstat.executeUpdate();
			
			//cstat.close();
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("OpenCourseDAO.openCourseDelete()");
			e.printStackTrace();
		}

		return 0;
	}


	
	
}	



