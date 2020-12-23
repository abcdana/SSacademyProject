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
	
	
	/**
	 * 
	 * @param seqBasicCourseInfo 과정 번호 
	 * @return dto 객체 반환
	 */
	public BasicCourseInfoDTO get(String seqBasicCourseInfo) {
		
		try {
			
			String sql = "select * from tblBasicCourseInfo where seqBasicCourseInfo = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqBasicCourseInfo);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				BasicCourseInfoDTO dto = new BasicCourseInfoDTO();
				
				dto.setSeqBasicCourseInfo(rs.getString("seqBasicCourseInfo"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));
				dto.setInfo(rs.getString("info"));
				
				return dto;
			}

		} catch (Exception e) {
			System.out.println("primaryBasicCourseInfoDAO.enget()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 기존 과정정보를 수정하는 메소드이다.
	 * 과정명, 과정기간, 과정설명중 원하는 항목만 수정할 수 있다.
	 * 등록 성공 여부를 return한다. 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param bcidto2 과정 데이터 정보
	 * @return 성공 여부
	 */
	public int updateCourse(BasicCourseInfoDTO dto2) {
		
		try {
			
			String sql = "update tblBasicCourseInfo set name=?, period=?, info=? "
							+ "where seqBasicCourseInfo=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getPeriod());
			pstat.setString(3, dto2.getInfo());
			pstat.setString(4, dto2.getSeqBasicCourseInfo());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBasicCourseInfoDAO.enupdateCourse()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	/**
	 * 기존 과정정보를 삭제하는 메소드이다.
	 * 삭제를 원하는 과정정보번호를 입력하면 해당 번호의 모든 데이터를 삭제한다.
	 * 등록 성공 여부를 return한다. 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param seqBasicCourseInfo 과정정보번호
	 * @return 성공 여부
	 */
	public int deleteCourse(String seqBasicCourseInfo) {
		
		try {
			
			String sql = "delete from tblBasicCourseInfo where seqBasicCourseInfo = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqBasicCourseInfo);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBasicCourseInfoDAO.endelete()");
			e.printStackTrace();
		}
		
		return 0;
	}
	


	
}
