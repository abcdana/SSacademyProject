package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.dto.ViewSubjectDTO;
import com.project.ssacademy.DBUtil;

/**
 * 과목 정보 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class ViewSubjectDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat; 
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public ViewSubjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryViewSubjectInfoDAO.enViewSubjectInfoDAO()");
			e.printStackTrace();
		}
		
	}

	/**
	 * 전체 과목 정보를 ArrayList로 반환하는 메서드이다.
	 * 과목 정보에는 과목 번호, 과목명, 과목소개, 교재명이 포함되어있다.
	 * @return 
	 */
	public ArrayList<ViewSubjectDTO> subjectList() {
		
		try {
			
			String sql = "select * from vwBasicSubject";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ViewSubjectDTO> result = new ArrayList<ViewSubjectDTO>();
			
			while (rs.next()) {
				
				ViewSubjectDTO vsdto = new ViewSubjectDTO();
				
				vsdto.setSeqBasicSubject(rs.getString("seqBasicSubject"));
				vsdto.setName(rs.getString("name"));
				vsdto.setInfo(rs.getString("info"));
				vsdto.setBook(rs.getString("book"));
				
				result.add(vsdto);
				
			}
			
			rs.close();
			return result;

		} catch (Exception e) {
			System.out.println("primaryViewSubjectDAO.ensubjectList()");
			e.printStackTrace();
		}

		return null;
	}
	

}
