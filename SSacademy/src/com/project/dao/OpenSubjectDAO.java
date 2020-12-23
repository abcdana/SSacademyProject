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
	private OpenSubjectListDTO dto;
	
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
	 * 특정과정조회에 해당하는 과목 리스트
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
	
}
