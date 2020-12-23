package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.AllOpenCourseDTO;
import com.project.ssacademy.DBUtil;

/**
 * 출결조회에서 사용할 모든 개설과정과 관련된 프로시저를 담은 DAO이다.
 * @author 김다은
 *
 */
public class AllOpenCourseDAO {

	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	CallableStatement cstat = null;
	ResultSet rs = null;
	
	
	public AllOpenCourseDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryAllOpenCourseDAO.enAllOpenCourseDAO()");
			e.printStackTrace();
		}
	}


	/**
	 * 개설 과정 정보를 ArrayList로 반환하는 메서드이다.
	 * 개설과정번호, 개설과정명, 과정시작일, 과정종료일, 교사명, 강의실명이 포함되어있다.
	 * @return 전체 개설과정 정보
	 */
	public ArrayList<AllOpenCourseDTO> allOpenCourseList() {
		
		ArrayList<AllOpenCourseDTO> result = new ArrayList<AllOpenCourseDTO>();
		
		try {
			
			String sql = "select * from vwAllOpenCourse order by seqOpenCourse";
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				AllOpenCourseDTO aocdto = new AllOpenCourseDTO();
				
				aocdto.setSeqOpenCourse(rs.getString("seqOpenCourse"));
				aocdto.setName(rs.getString("name"));
				aocdto.setStartDate(rs.getString("startDate"));
				aocdto.setEndDate(rs.getString("endDate"));
				aocdto.setTeacherName(rs.getString("teacherName"));
				aocdto.setRoomName(rs.getString("roomName"));
				
				result.add(aocdto);
				
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryAllOpenCourseDAO.enallOpenCourseList()");
			e.printStackTrace();
		}
		
		return null;
	}
}
