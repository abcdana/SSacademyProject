package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.BasicSubjectDTO;
import com.project.ssacademy.DBUtil;

/**
 * 기초과목관련 모든 프로시저를 관리하는 DAO
 * @author 박지현, 김다은
 *
 */
public class BasicSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public BasicSubjectDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("BasicSubjectDAO.BasicSubjectDAO()");
			e.printStackTrace();
		}

	}


//	public ArrayList<BasicSubjectDTO> get(String seqTeacher) {
//		
//		try {
//			
//			String sql = "select bs.* from tblAvailableSubject abs inner join tblBasicSubject bs on abs.seqBasicSubject = bs.seqBasicSubject where abs.seqTeacher = ?";
//			
//			pstat = conn.prepareStatement(sql);
//			pstat.setString(1, seqTeacher);
//			
//			rs = pstat.executeQuery();
//			
//			ArrayList<BasicSubjectDTO> list = new ArrayList<BasicSubjectDTO>();
//			
//			while (rs.next()) {
//				BasicSubjectDTO dto = new BasicSubjectDTO();
//				
//				dto.setSeqBasicSubject(rs.getString("seqBasicSubject"));
//				dto.setSeqBook(rs.getString("seqBook"));
//				dto.setName(rs.getNString("name"));
//				dto.setInfo(rs.getString("info"));
//				
//				list.add(dto);
//				
//			}
//			
//			return list;
//			
//		} catch (Exception e) {
//			System.out.println("BasicSubjectDAO.get()");
//			e.printStackTrace();
//		}
//		
//		return null;
//	}

	

	/**
	 * 
	 * @param seqBasicSubject 과목번호
	 * @return dto객체
	 */
	public BasicSubjectDTO get(String seqBasicSubject) {
		
		try {
			
			String sql = "select * from tblBasicSubject where seqBasicSubject = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqBasicSubject);
			
			rs = pstat.executeQuery();
			
			while (rs.next()) {
				
				BasicSubjectDTO dto = new BasicSubjectDTO();
				
				dto.setSeqBasicSubject(rs.getString("seqBasicSubject"));
				dto.setSeqBook(rs.getString("seqBook"));
				dto.setName(rs.getNString("name"));
				dto.setInfo(rs.getString("info"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("BasicSubjectDAO.get()");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 새로운 과목 정보를 추가하는 메서드이다.
	 * 과목명, 과목소개, 교재번호를 담은 과목 데이터 정보를 받아와 새로운 과목으로 등록한다.
	 * 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param bsdto 과목 데이터 정보
	 * @return 성공 여부
	 */
	public int addSubject(BasicSubjectDTO bsdto) {
		
		try {
			
			String sql = "{ call procAddBasicSubject(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, bsdto.getName());
			cstat.setString(2, bsdto.getInfo());
			cstat.setString(3, bsdto.getSeqBook());
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBasicSubjectDAO.enaddSubject()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 기존 과목 정보를 수정하는 메서드이다
	 * 수정할 과목 데이터 객체를 가져와 과목명, 과목소개, 교재번호를 수정할 수 있다.
	 * 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param dto2 수정할 과목 데이터 객체
	 * @return 성공 여부
	 */
	public int updateSubject(BasicSubjectDTO dto2) {
		
		try {
			
			String sql = "update tblBasicSubject set name = ?, info = ?, seqBook = ?"
							+ "where seqBasicSubject = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getInfo());
			pstat.setString(3, dto2.getSeqBook());
			pstat.setString(4, dto2.getSeqBasicSubject());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBasicSubjectDAO.enupdateSubject()");
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	/**
	 * 기존 과목 정보를 삭제하는 메서드이다.
	 * 과목 번호를 받아와 과목 데이터를 삭제한다.
	 * 삭제는 tblBasicSubject에서 일어나지만 사용자에게 보여지는 건 viewSubject이다.
	 * 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param seqBasicSubject 과목 번호
	 * @return 성공 여부 
	 */
	public int deleteSubject(String seqBasicSubject) {
		
		try {
			
			String sql = "{ call procDeleteBasicSubject(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqBasicSubject);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBasicSubjectDAO.enenclosing_method()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 과목기초정보 테이블의 레코드 전체를 가져오는 메서드
	 * @return 과목기초정보 테이블의 레코드를 담는 ArrayList
	 */
	public ArrayList<BasicSubjectDTO> wholeSubjectList() {
		
		try {
			
			String sql = "select * from tblBasicSubject order by seqBasicSubject";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<BasicSubjectDTO> list = new ArrayList<BasicSubjectDTO>();
			
			while (rs.next()) {
				BasicSubjectDTO dto = new BasicSubjectDTO();
				dto.setSeqBasicSubject(rs.getString("seqBasicSubject"));
				dto.setSeqBook(rs.getString("seqBook"));
				dto.setName(rs.getString("name"));
				dto.setInfo(rs.getString("info"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BasicSubjectDAO.wholeSubjectList()");
			e.printStackTrace();
		}
		
		return null;
	}


	
}

