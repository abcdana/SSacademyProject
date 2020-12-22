package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.ViewEndCourseDTO;
import com.project.admin.dto.ViewSpecificEvaluationDTO;
import com.project.ssacademy.DBUtil;

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
				sql = "select * from vwRegCourse where seqTeacher = " + word;
			} else {
				sql = "select * from vwRegCourse";				
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
	
	
	
}