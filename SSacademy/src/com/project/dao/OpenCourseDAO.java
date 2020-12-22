package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenCourseStudentDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dto.BasicCourseInfoDTO;
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
	
	
	public ArrayList<OpenCourseListDTO> OpenCourseList() {

		try {

			// 전체개설과정 조회 쿼리
			String sql = "select o.seqOpenCourse as 과정번호,"
					+ "        b.name as 과정명,"
					+ "        to_char(o.startdate, 'yyyy-mm-dd') as 시작일,"
					+ "        to_char(o.enddate, 'yyyy-mm-dd') as 종료일,"
					+ "        r.name as 강의실,"
					+ "        o.memberCount as 등록인원,"
					+ "    case"
					+ "        when endDate > sysdate and startDate < sysdate then '강의중'"
					+ "        when endDate < sysdate then '강의종료'"
					+ "        when startDate > sysdate then '강의예정'"
					+ "    end as 과정수료여부"
					+ " from tblOpenCourse o"
					+ "        inner join tblBasicCourseInfo b"
					+ "            on o.seqBasicCourseInfo = b.seqBasicCourseInfo"
					+ "                inner join tblRoom r"
					+ "                    on r.seqroom = o.seqroom"
					+ "                        order by seqOpenCourse asc";


			rs = stat.executeQuery(sql);

			ArrayList<OpenCourseListDTO> list = new ArrayList<OpenCourseListDTO>();

			while(rs.next()) {

				OpenCourseListDTO oc = new OpenCourseListDTO();

				oc.setSeqOpenCourse(rs.getString("과정번호"));
				oc.setName(rs.getString("과정명"));
				oc.setStartDate(rs.getString("시작일"));
				oc.setEndDate(rs.getString("종료일"));
				oc.setRoom(rs.getString("강의실"));
				oc.setMemberCount(rs.getString("등록인원"));
				oc.setState(rs.getString("과정수료여부"));

				list.add(oc);
			}
			
			rs.close();
			//stat.close();
			//conn.close();
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("ArrayList<OpenCourseListDTO> OpenCourseList()");
			e.printStackTrace();
		}

		return null;
	}
	

	/**
	 * 특정과정조회의 교육생 리스트
	 */
	public ArrayList<OpenCourseStudentDTO> OpenCourseStudent(String seqOpenCourse) {

		try {
			
			//개설과목조회 프로시저 호출 sql
			String sql = "{ call procViewStudent(?, ?) }";	
			cstat = conn.prepareCall(sql);
			
			//scanner로 받은 인자값 넣기
			cstat.setString(1, seqOpenCourse);
			//out 인자값 넣기
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			//쿼리 날리기
			cstat.executeQuery();
			
			//가져온 out값 형변환
			rs = (ResultSet) cstat.getObject(2);
			
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
			
			rs.close();
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.SpecificOpenSubject(String seqOpenCourse)");
		}
	
			return null; 
		
	}
	
	
	/**
	 * 개설과정등록 DAO
	 */
	public OpenCourseReg() {
		
		try {

			//개설과정등록 프로시저
			String sql = "select o.seqOpenCourse as 과정번호,"
					+ "        b.name as 과정명,"
					+ "        to_char(o.startdate, 'yyyy-mm-dd') as 시작일,"
					+ "        to_char(o.enddate, 'yyyy-mm-dd') as 종료일,"
					+ "        r.name as 강의실,"
					+ "        o.memberCount as 등록인원,"
					+ "    case"
					+ "        when endDate > sysdate and startDate < sysdate then '강의중'"
					+ "        when endDate < sysdate then '강의종료'"
					+ "        when startDate > sysdate then '강의예정'"
					+ "    end as 과정수료여부"
					+ " from tblOpenCourse o"
					+ "        inner join tblBasicCourseInfo b"
					+ "            on o.seqBasicCourseInfo = b.seqBasicCourseInfo"
					+ "                inner join tblRoom r"
					+ "                    on r.seqroom = o.seqroom"
					+ "                        order by seqOpenCourse asc";


			rs = stat.executeQuery(sql);

			ArrayList<OpenCourseListDTO> list = new ArrayList<OpenCourseListDTO>();

			while(rs.next()) {

				OpenCourseListDTO oc = new OpenCourseListDTO();

				oc.setSeqOpenCourse(rs.getString("과정번호"));
				oc.setName(rs.getString("과정명"));
				oc.setStartDate(rs.getString("시작일"));
				oc.setEndDate(rs.getString("종료일"));
				oc.setRoom(rs.getString("강의실"));
				oc.setMemberCount(rs.getString("등록인원"));
				oc.setState(rs.getString("과정수료여부"));

				list.add(oc);

				
			}
			
			rs.close();
			stat.close();
			conn.close();
			
			return list;
			
			
			

		} catch (SQLException e) {
			System.out.println("ArrayList<OpenCourseListDTO> OpenCourseList()");
			e.printStackTrace();
		}

		return null;
		
		
	}
}