package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.ViewTeacherCourseDTO;
import com.project.dto.TeacherDTO;
import com.project.ssacademy.DBUtil;
import com.project.teacher.dto.TeacherScheduleDTO;

import oracle.jdbc.OracleTypes;


/**
 * 교사 계졍과 관련된 기능을 담당하는 DAO
 * @author 김주혁, 김다은, 박지현
 *
 */
public class TeacherDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * TeacherDAO 클래스 생성자
	 */
	public TeacherDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.TeacherDAO()");
			e.printStackTrace();
		}
		
	} //TeacherDAO

	
	/**
	 * 
	 * @param id
	 * @return Teacher DTO (해당 id의 교사 정보)
	 */
	public TeacherDTO getTeacher(String id) {

		try {
			
			String sql = "select * from tblTeacher where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryTeacherDAO.engetTeacher()");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 교사 목록 가져오기
	 * @return 교사 정보를 저장한 ArrayList
	 */
	public ArrayList<TeacherDTO> list() {
		
		try {
			
			String sql = "select * from tblTeacher order by seqTeacher";
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
				
			}
			
			rs.close();
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 교사 추가하기
	 * @param 추가할 교사의 정보를 담은 dto
	 * @return 추가 성공 여부
	 */
	public int add(TeacherDTO dto) {
		
		try {
					
			String sql = "{ call procAddTeacher(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getName()); //이름
			cstat.setString(2, dto.getSsn()); //주민등록번호
			cstat.setString(3, dto.getTel()); //전화번호
			cstat.registerOutParameter(4, OracleTypes.NUMBER); //교사번호(out)
			
			int result = cstat.executeUpdate();
			
			dto.setSeqTeacher(cstat.getString(4)); //교사번호 저장
			
			return result;
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
		
	}


	/**
	 * 교사 정보 가져오기
	 * @param seqTeacher 교사번호에 해당하는 교사 정보
	 * @return 교사 정보를 담은 dto
	 */
	public TeacherDTO get(String seqTeacher) {
		
		try {
			
			String sql = "select * from tblTeacher where seqTeacher = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqTeacher);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				TeacherDTO tdto = new TeacherDTO();
				
				tdto.setSeqTeacher(rs.getString("seqTeacher"));
				tdto.setName(rs.getString("name"));
				tdto.setId(rs.getString("id"));
				tdto.setSsn(rs.getString("ssn"));
				tdto.setTel(rs.getString("tel"));
				
				return tdto;
			}
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.get()");
			e.printStackTrace();
		}
		
		return null;
		
	}


	/**
	 * 교사 정보 수정하기
	 * @param newtdto 수정될 교사 정보를 담은 dto
	 * @return 수정 성공 여부
	 */
	public int edit(TeacherDTO newtdto) {
		
		try {
			
			String sql = "{ call procUpdateTeacher(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, newtdto.getSeqTeacher());
			cstat.setString(2, newtdto.getName());
			cstat.setString(3, newtdto.getSsn());
			cstat.setString(4, newtdto.getTel());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 강의 여부 확인하기
	 * @param seqTeacher 해당 교사의 교사 번호
	 * @return 강의 여부 반환
	 */
	public int checkLecture(String seqTeacher) {
		
		try {
			
			String sql = "{ call procLectureState(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqTeacher);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			
			cstat.executeQuery();
			
			int result = cstat.getInt(2);
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.checkLecture()");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 교사 삭제하기
	 * @param seqTeacher 삭제할 교사의 교사 번호
	 * @return 삭제 성공 여부
	 */
	public int delete(String seqTeacher) {
		
		try {
			
			String sql = "update tblTeacher set id = null, ssn = null, tel = null where seqTeacher = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqTeacher);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.delete()");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 특정 교사 검색하기
	 * @param seqTeacher 검색할 교사의 교사번호
	 * @return 특정 교사의 강의 정보를 저장한 ArrayList
	 */
	public ArrayList<ViewTeacherCourseDTO> search(String seqTeacher) {
		
		try {
			
			String sql = "select * from vwCourseInfo where seqTeacher = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqTeacher);
			
			rs = pstat.executeQuery();
			
			ArrayList<ViewTeacherCourseDTO> list = new ArrayList<ViewTeacherCourseDTO>();
			
			while (rs.next()) {
				ViewTeacherCourseDTO dto = new ViewTeacherCourseDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setTeacherName(rs.getString("tname"));
				dto.setCourseName(rs.getString("cname"));
				dto.setCourseStartDate(rs.getString("cstartDate").substring(0, 10));
				dto.setCourseEndDate(rs.getString("cendDate").substring(0, 10));
				dto.setRoom(rs.getString("room"));
				dto.setSubjectName(rs.getString("sname"));
				dto.setSubjectStartDate(rs.getString("sstartDate").substring(0, 10));
				dto.setSubjectEndDate(rs.getString("sendDate").substring(0, 10));
				dto.setLectureState(rs.getString("lectureState"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.search()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 교사 스케줄 조회
	 * @param seqTeacher 검색할 교사의 교사번호
	 * @return 교사의 강의스케줄 정보
	 * @author 박지현
	 */
	public ArrayList<TeacherScheduleDTO> scheduleSeqTeacher(String seqTeacher) {
		
		try {
			
			//쿼리
			String sql = "{ call procTeacherSchedule(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqTeacher);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			//쿼리날리기
			cstat.executeQuery();
			
			//가져온 out값 형변환
			ResultSet rs = (ResultSet) cstat.getObject(2);
			
			ArrayList<TeacherScheduleDTO> list = new ArrayList<TeacherScheduleDTO>();
			
			//System.out.println("seqTeacher : " + seqTeacher);
			
			while(rs.next()) {
				
				TeacherScheduleDTO tdto = new TeacherScheduleDTO();
				
				tdto.setSeqTeacher(rs.getString("seqTeacher"));
				tdto.setTeahcerName(rs.getString("teacherName"));
				tdto.setCourseName(rs.getString("courseName"));
				tdto.setStartDate(rs.getString("startDate"));
				tdto.setEndDate(rs.getString("endDate"));
				tdto.setMemberCount(rs.getString("memberCount"));
				
				list.add(tdto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.scheduleSeqTeahcer()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 교사 스케줄 조회
	 * @param teacherName 검색할 교사의 교사명
	 * @return 교사의 강의스케줄 정보
	 * @author 박지현
	 */
	public ArrayList<TeacherScheduleDTO> scheduleNameTeacher(String teacherName) {
		
		try {
			
			//쿼리
			String sql = String.format("select * from vteacherSchedule where teacherName like '%%%s%%'"
					, teacherName);
			
			//쿼리날리기
			pstat =  conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<TeacherScheduleDTO> list = new ArrayList<TeacherScheduleDTO>();
			
			while(rs.next()) {
				
				TeacherScheduleDTO tdto = new TeacherScheduleDTO();
				
				tdto.setSeqTeacher(rs.getString("seqTeacher"));
				tdto.setTeahcerName(rs.getString("teacherName"));
				tdto.setCourseName(rs.getString("courseName"));
				tdto.setStartDate(rs.getString("startDate"));
				tdto.setEndDate(rs.getString("endDate"));
				tdto.setMemberCount(rs.getString("memberCount"));
				
				list.add(tdto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.scheduleNameTeacher()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
