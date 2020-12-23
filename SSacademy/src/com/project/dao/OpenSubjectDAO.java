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
	public ArrayList<OpenSubjectListDTO> openSubjectList() {
			
		try {
			
			String sql = "select os.seqOpenSubject as 개설과목번호,"
					+ "    bs.name as 과목명,"
					+ "    b.name as 교재명,"
					+ "    t.name as 교사명,"
					+ "    to_char(os.startdate, 'yyyy-mm-dd') as 시작일,"
					+ "    to_char(os.enddate, 'yyyy-mm-dd') as 종료일"
					+ " from tblBasicSubject bs"
					+ "    inner join tblAvailableSubject asub"
					+ "        on bs.seqBasicSubject = asub.seqBasicSubject"
					+ "            inner join tblOpenSubject os"
					+ "                on asub.seqAvailableSubject = os.seqAvailableSubject"
					+ "                    inner join tblOpenCourse oc"
					+ "                        on oc.seqOpenCourse = os.seqOpenCourse"
					+ "                            inner join tblBasicCourseInfo bc"
					+ "                                on bc.seqbasiccourseinfo = oc.seqbasiccourseinfo"
					+ "                                    inner join tblBook b"
					+ "                                        on b.seqbook = bs.seqbook"
					+ "                                            inner join tblTeacher t"
					+ "                                                on t.seqTeacher = asub.seqTeacher"
					+ " order by oc.startdate asc";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<OpenSubjectListDTO> list = new ArrayList<OpenSubjectListDTO>();
			
			while(rs.next()) {
				
				OpenSubjectListDTO dto = new OpenSubjectListDTO();
				
				dto.setSeqOpenSubject(rs.getString("개설과목번호"));
				dto.setSubjectName(rs.getString("과목명"));
				dto.setBookName(rs.getString("교재명"));
				dto.setTeacherName(rs.getString("교사명"));
				dto.setStartDate(rs.getString("시작일"));
				dto.setEndDate(rs.getString("종료일"));
				
				list.add(dto);
			}
			
			return list;
				
			
		} catch (Exception e) {
				System.out.println("OpenSubjectDAO.OpenSubjectList()");
				e.printStackTrace();
		}
		
		return null;
	}	
	
	
	/**
	 * 특정과목조회에 해당하는 과목정보
	 */
	public ArrayList<OpenSubjectListDTO> specificOpenSubject(String seqOpenCourse) {

		try {
			
			//개설과목조회 프로시저 호출 sql
			String sql = "{ call procViewSubject2(?, ?) }";	
			cstat = conn.prepareCall(sql);
			
			//scanner로 받은 인자값 넣기
			cstat.setString(1, seqOpenCourse);
			//out 인자값 넣기
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			//쿼리 날리기
			cstat.executeQuery();
			
			//가져온 out값 형변환
			rs = (ResultSet) cstat.getObject(2);
			
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
	 * 개설과목수정시 사용 할 DAO
	 */
	public OpenSubjectListDTO normalOpenSubject(String seqOpenSubject) {
		
		try {
			
			String sql = "select bs.name as subjectName"
					+ "    , bc.name as courseName"
					+ "    , t.name as teacherName"
					+ "    , os.seqopensubject as seqOpenSubject"
					+ "    , os.seqopencourse as seqOpenCourse"
					+ "    , avs.seqavailablesubject as seqAvailableSubject"
					+ "    , to_char(os.startdate, 'yyyy-mm-dd') as startDate"
					+ "    , to_char(os.endDate, 'yyyy-mm-dd') as endDate"
					+ " from tblBasicSubject bs"
					+ "    inner join tblAvailableSubject avs"
					+ "        on bs.seqbasicsubject = avs.seqbasicsubject"
					+ "            inner join tblOpenSubject os"
					+ "                on os.seqavailablesubject = avs.seqavailablesubject"
					+ "                    inner join tblOpenCourse oc"
					+ "                        on oc.seqopencourse = os.seqopencourse"
					+ "                            inner join tblbasiccourseinfo bc"
					+ "                                on bc.seqbasiccourseinfo = oc.seqbasiccourseinfo"
					+ "                                    inner join tblTeacher t"
					+ "                                        on t.seqTeacher = avs.seqTeacher"
					+ "	where seqOpenSubject = ?";
			
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seqOpenSubject);
			
			rs = pstat.executeQuery();
			
			OpenSubjectListDTO osdto = new OpenSubjectListDTO();
			
			while(rs.next()) {
				
				osdto.setOpenCourseName(rs.getString("courseName"));
				osdto.setTeacherName(rs.getString("teacherName"));
				osdto.setSubjectName(rs.getString("subjectName"));
				osdto.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				osdto.setAvailableSubject(rs.getString("seqAvailableSubject"));
				osdto.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				osdto.setStartDate(rs.getString("startDate"));
				osdto.setEndDate(rs.getString("endDate"));
			
				
				return osdto;
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
	
	public int editOpenSubject(OpenSubjectListDTO osdto2) {
		
		try {
			
			String sql = "update tblOpenSubject set seqAvailableSubject = ?"
					+ ", seqOpenCourse = ?"
					+ ", startDate = ?"
					+ ", endDate = ?"
							+ "where seqOpenSubject = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, osdto2.getAvailableSubject());
			pstat.setString(2, osdto2.getSeqOpenCourse());
			pstat.setString(3, osdto2.getStartDate());
			pstat.setString(4, osdto2.getEndDate());
			pstat.setString(5, osdto2.getSeqOpenSubject());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("OpenSubjectDAO.editOpenSubject()");
			e.printStackTrace();
		}
		
		return 0;
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
