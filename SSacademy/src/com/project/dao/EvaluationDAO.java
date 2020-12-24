package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.ViewEndCourseDTO;
import com.project.admin.dto.ViewSpecificEvaluationDTO;
import com.project.dto.EvaluationDTO;
import com.project.ssacademy.DBUtil;
import com.project.student.dto.ViewStudentEndCourseDTO;

import oracle.jdbc.OracleTypes;

//평가정보
public class EvaluationDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public EvaluationDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();			
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.EvaluationDAO()");
			e.printStackTrace();
		}
		
	}

	public ArrayList<ViewEndCourseDTO> courseList(String word) {
		
		try {
			String sql = "";
			if (word != null) {
				sql = "select * from vwEndCourse where seqTeacher = " + word;
			} else {
				sql = "select * from vwEndCourse";
			}
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<ViewEndCourseDTO> list = new ArrayList<ViewEndCourseDTO>();
			
			while (rs.next()) {
				ViewEndCourseDTO dto = new ViewEndCourseDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setTeacherName(rs.getString("teacher"));
				dto.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				dto.setCourseName(rs.getString("course"));
				dto.setCourseStartDate(rs.getString("startDate").substring(0, 10));
				dto.setCourseEndDate(rs.getString("endDate").substring(0, 10));
				dto.setStudentCount(rs.getString("studentCount"));
				dto.setRoom(rs.getString("room"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.courseList()");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<ViewSpecificEvaluationDTO> courseEvaluationList(String seqOpenCourse) {
		
		try {
			
			String sql = "select * from vwEvaluation where seqOpenCourse = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqOpenCourse);
			
			rs = pstat.executeQuery();
			
			ArrayList<ViewSpecificEvaluationDTO> list = new ArrayList<ViewSpecificEvaluationDTO>();
			
			while (rs.next()) {
				ViewSpecificEvaluationDTO dto = new ViewSpecificEvaluationDTO();
				
				dto.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setStudentName(rs.getString("name"));
				dto.setProcessScore(rs.getString("processScore"));
				dto.setUnderstandScore(rs.getString("understandScore"));
				dto.setCommunicationScore(rs.getString("communicationScore"));
				dto.setUsefulScore(rs.getString("usefulScore"));
				dto.setSatisfactionScore(rs.getString("satisfactionScore"));
				dto.setFacilityScore(rs.getString("facilityScore"));
				dto.setManagementScore(rs.getString("managementScore"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.evaluationList()");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public ArrayList<ViewStudentEndCourseDTO> studentCourseList(String seqStudent) {
		
		try {
			
			String 	sql = "select * from vwStudentCourse where seqStudent = " + seqStudent;
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<ViewStudentEndCourseDTO> list = new ArrayList<ViewStudentEndCourseDTO>();
			
			while (rs.next()) {
				ViewStudentEndCourseDTO dto = new ViewStudentEndCourseDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setSeqRegCourse(rs.getString("seqRegCourse"));
				dto.setCourseName(rs.getString("course"));
				dto.setCourseStartDate(rs.getString("startDate").substring(0, 10));
				dto.setCourseEndDate(rs.getString("endDate").substring(0, 10));
				dto.setRoom(rs.getString("room"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.studentCourseList()");
			e.printStackTrace();
		}
		
		return null;
	}

	public int add(EvaluationDTO dto) {
		
		try {
			
			String sql = "{ call procAddEvaluation(?, ?, ?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getSeqRegCourse());
			cstat.setString(2, dto.getProcessScore());
			cstat.setString(3, dto.getUnderstandScore());
			cstat.setString(4, dto.getCommunicationScore());
			cstat.setString(5, dto.getUsefulScore());
			cstat.setString(6, dto.getSatisfactionScore());
			cstat.setString(7, dto.getFacilityScore());
			cstat.setString(8, dto.getManagementScore());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.add()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int isEvaluation(String seqRegCourse) {
		
		try {
			
			String sql = "{ call procIsEvaluation(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqRegCourse);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			
			cstat.executeQuery();
			
			int result = cstat.getInt(2);
			
			return result;
			
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.isEvaluation()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public EvaluationDTO getEvaluation(String seqRegCourse) {
		
		try {
			
			String sql = "select * from tblEvaluation where seqRegCourse = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqRegCourse);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				EvaluationDTO dto = new EvaluationDTO();
				
				dto.setSeqEvaluation(rs.getString("seqEvaluation"));
				dto.setSeqRegCourse(rs.getString("seqRegCourse"));
				dto.setProcessScore(rs.getString("processScore"));
				dto.setUnderstandScore(rs.getString("understandScore"));
				dto.setCommunicationScore(rs.getString("communicationScore"));
				dto.setUsefulScore(rs.getString("usefulScore"));
				dto.setSatisfactionScore(rs.getString("satisfactionScore"));
				dto.setFacilityScore(rs.getString("facilityScore"));
				dto.setManagementScore(rs.getString("managementScore"));
				
				return dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.getEvaluation()");
			e.printStackTrace();
		}
		
		return null;
	}

	public int edit(EvaluationDTO newdto) {
		
		try {
			
			String sql = "{ call procUpdateEvaluation(?, ?, ?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, newdto.getSeqRegCourse());
			cstat.setString(2, newdto.getProcessScore());
			cstat.setString(3, newdto.getUnderstandScore());
			cstat.setString(4, newdto.getCommunicationScore());
			cstat.setString(5, newdto.getUsefulScore());
			cstat.setString(6, newdto.getSatisfactionScore());
			cstat.setString(7, newdto.getFacilityScore());
			cstat.setString(8, newdto.getManagementScore());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int delete(String seqRegCourse) {
		
		try {
			
			String sql = "{ call procDeleteEvaluation(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setNString(1, seqRegCourse);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvaluationDAO.delete()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}