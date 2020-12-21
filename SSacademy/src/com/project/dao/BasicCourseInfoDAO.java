package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.BasicCourseInfoDTO;
import com.project.ssacademy.DBUtil;

/**
 * 기초과정정보 데이터베이스와 관련된 비즈니스업무 DAO
 * 기초과정정보 조회, 새 과정 등록, 기존과정정보 수정, 기존과정정보 삭제 기능을 포함한다.
 * @author 조혜승, 김다은
 *
 */
public class BasicCourseInfoDAO {
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	CallableStatement cstat = null;
	ResultSet rs = null;
	
	public BasicCourseInfoDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 전체 기초과정정보를 ArrayList로 반환하는 메소드이다.
	 * 기초 과정 정보에는 과정번호, 과정명, 기간(일), 과정소개가 포함된다. 
	 * @return 기초과정정보 ArrayList
	 */
	public ArrayList<BasicCourseInfoDTO> courseList() {
		
		try {
			
			String sql = "select * from tblBasicCourseInfo order by seqbasiccourseinfo";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<BasicCourseInfoDTO> list = new ArrayList<BasicCourseInfoDTO>();
			
			while(rs.next()) {
				
				BasicCourseInfoDTO dto = new BasicCourseInfoDTO();
				
				dto.setSeqBasicCourseInfo(rs.getString("seqBasicCourseInfo"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));
				dto.setInfo(rs.getString("info"));
				
				list.add(dto);
			}
			
			rs.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("primaryBasicInfoManage.enbasicCourseInfoList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 새로운 과정정보를 등록하는 메소드이다.
	 * 과정명, 과정기간, 과정소개가 담긴 데이터 정보를 받아 과정을 등록하고,
	 * 등록 성공 여부를 return한다. 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param bcidto 과정 데이터 정보
	 * @return 성공 여부
	 */
	public int addCourse(BasicCourseInfoDTO bcidto) {
				
		try {
			
			String sql = "{ call procAddBasicCourseInfo(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, bcidto.getName());	//과정명
			cstat.setString(2, bcidto.getPeriod());	//과정기간 (단위 : 일)
			cstat.setString(3, bcidto.getInfo());	//과정소개
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBasicCourseInfoDAO.enaddCourse()");
			e.printStackTrace();
		}
		
		return 0;
				
	}
	
	
	
	//public int updateCourse(String )
	


	
}
