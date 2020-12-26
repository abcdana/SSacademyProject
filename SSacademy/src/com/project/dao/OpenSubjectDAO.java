package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dto.OpenCourseDTO;
import com.project.dto.OpenSubjectDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 개설과목관련 모든 프로시저를 관리하는 DAO
 * @author 박지현
 *
 */
public class OpenSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public OpenSubjectDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.OpenSubject()");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 전체과목조회
	 */
	public ArrayList<OpenSubjectListDTO> openSubjectList(OpenSubjectListDTO osdto, int page) {
			
		try {
			
			int start = 1 + (page-1) * 10;	//한페이지에서 보여줄 첫번째 rownum
			int end = 10 * page; 	//한페이지에서 마지막으로 보여줄 rownum
			
			String sql = "select * from openSubjectView where num2 between ? and ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			rs = pstat.executeQuery();
			
			ArrayList<OpenSubjectListDTO> list = new ArrayList<OpenSubjectListDTO>();
			
			while(rs.next()) {
				
				OpenSubjectListDTO dto = new OpenSubjectListDTO();
				
				dto.setRownum(rs.getInt("num2"));
				dto.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setBookName(rs.getString("bookName"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				
				list.add(dto);
			}
			
			rs.close();
			
			return list;
			
		} catch (Exception e) {
				System.out.println("OpenSubjectDAO.OpenSubjectList()");
				e.printStackTrace();
		}
		
		return null;
	}	
	
	
	//과목글 총 개수
	public int getCountSubject() {
			
		int count = 0;
			
		try {

			// 전체개설과목 총 갯수
			String sql = "select count(num2) as count from openSubjectView";
				
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
				
			if(rs.next()) {
				count = rs.getInt("count");
					
				rs.close();
				
				return count;
			}
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.getCountSubject()");
			e.printStackTrace();
		}
			
		return 0;
	}
	
	
		
	/**
	 * 과정 -> 특정과목조회에 해당하는 과목정보
	 */
	public ArrayList<OpenSubjectListDTO> specificOpenSubject(String seqOpenCourse) {

		try {
			
			//개설과목조회 프로시저 호출 sql
			String sql = "select * from vOpenSubject where seqOpenCourse = ?";	
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seqOpenCourse);
			
			//쿼리 날리기
			rs = pstat.executeQuery();
			
			ArrayList<OpenSubjectListDTO> list = new ArrayList<OpenSubjectListDTO>();
			
			while(rs.next()) {
				
				OpenSubjectListDTO os = new OpenSubjectListDTO();
				
				os.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				os.setSubjectName(rs.getString("name"));
				os.setStartDate(rs.getString("startDate"));
				os.setEndDate(rs.getString("endDate"));
				os.setTeacherName(rs.getString("teacherName"));
				os.setBookName(rs.getString("bookName"));
				os.setState(rs.getString("state"));
				
				list.add(os);
			
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.SpecificOpenSubject(String seqOpenCourse)");
		}
	
			return null; 
		
	}
	
	
	/**
	 * 특정과목조회에 해당하는 과목정보
	 */
	public ArrayList<OpenSubjectListDTO> specificOpenSubject2(int rownum) {

		try {
			
			//개설과목조회 쿼리
			String sql = "select * from openSubjectView where num2 = ?";	
			pstat = conn.prepareStatement(sql);
			
			//scanner로 받은 인자값 넣기
			pstat.setInt(1, rownum);
			
			rs = pstat.executeQuery();
			
			ArrayList<OpenSubjectListDTO> list = new ArrayList<OpenSubjectListDTO>();
			
			while(rs.next()) {
				
				OpenSubjectListDTO os = new OpenSubjectListDTO();
				
				os.setRownum(rs.getInt("num2"));
				os.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				os.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				os.setOpenCourseName(rs.getString("courseName"));
				os.setSubjectName(rs.getString("subjectName"));
				os.setStartDate(rs.getString("startDate"));
				os.setEndDate(rs.getString("endDate"));
				os.setTeacherName(rs.getString("teacherName"));
				os.setBookName(rs.getString("bookName"));
				
				list.add(os);
				
			}
			
			rs.close();
			return list;
			
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.SpecificOpenSubject2(String seqOpenCourse)");
		}
	
			return null; 
		
	}
	
	
	/**
	 * 개설과목수정시 사용 할 DAO
	 */
	public OpenSubjectListDTO normalOpenSubject(int rownum) {
		
		try {
			
			String sql = "select * from openSubjectView where num2 = ?"; 
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, rownum);
			
			rs = pstat.executeQuery();
			
			OpenSubjectListDTO osdto = new OpenSubjectListDTO();
			
			while(rs.next()) {
				
				osdto.setRownum(rs.getInt("num2"));
				osdto.setOpenCourseName(rs.getString("courseName"));
				osdto.setTeacherName(rs.getString("teacherName"));
				osdto.setSubjectName(rs.getString("subjectName"));
				osdto.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				osdto.setAvailableSubject(rs.getString("seqAvailableSubject"));
				osdto.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				osdto.setStartDate(rs.getString("startDate"));
				osdto.setEndDate(rs.getString("endDate"));
			
			}
			
			return osdto;
					
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.normalOpenSubject()");
		}
		
		return null;
		
	}

	
	/**
	 * 개설과목수정 DAO
	 * @return 결과값 1: 수정완료 0: 수정실패
	 */
	
	public int editOpenSubject(OpenSubjectListDTO osdto) {
		
		try {
			
			String sql = String.format("update tblOpenSubject set seqAvailableSubject = %s"
					+ ", seqOpenCourse = %s"
					+ ", startDate = '%s'"
					+ ", endDate = '%s' where seqOpenSubject = %s"
					, osdto.getAvailableSubject()
					, osdto.getSeqOpenCourse()
					, osdto.getStartDate()
					, osdto.getEndDate()
					, osdto.getSeqOpenSubject());
			
			stat = conn.createStatement();
			int result = stat.executeUpdate(sql);
	
			return result;
			
		} catch (Exception e) {
			
			System.out.println("OpenSubjectDAO.editOpenSubject()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 개설과목 수정 시 사용할 총 개설과목 rownum 배열
	 */
	public ArrayList<Integer> specificOpenSubject3() {

		try {
			
			//개설과목조회 쿼리
			String sql = "select num2 from openSubjectView";	
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			
			while(rs.next()) {
				list.add(rs.getInt("num2"));
			}
			
			rs.close();
			return list;
			
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.SpecificOpenSubject2(String seqOpenCourse)");
		}
	
			return null; 
		
	}
	
	
	/**
	 * 개설과목등록 DAO
	 */
	public int openSubjectAdd(OpenSubjectDTO osdto2) {
		
		try {

			//개설과목등록 프로시저 호출
			String sql = "{ call procRegistSubject2(?, ?, ?, ?) }";

			cstat = conn.prepareCall(sql);

			cstat.setString(1, osdto2.getSeqAvailableSubject());
			cstat.setString(2, osdto2.getSeqOpenCourse());
			cstat.setString(3, osdto2.getStartDate());
			cstat.setString(4, osdto2.getEndDate());
			
			
			return cstat.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("ArrayList<OpenCourseListDTO> OpenCourseList()");
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 개설과목삭제 DAO
	 */
	public int openSubjectDelete(String seqOpenSubject) {
	
		try {

			//개설과목삭제 프로시저 호출
			String sql = "{ call procDeleteSubject2(?) }";

			cstat = conn.prepareCall(sql);

			cstat.setString(1, seqOpenSubject);
		
			int result = cstat.executeUpdate();
		
			return result;
		
		} catch (SQLException e) {
			System.out.println("OpenSubjectDAO.openSubjectDelete()");
			e.printStackTrace();
		}

		return 0;
	
	}//openCourseDelete()
}
