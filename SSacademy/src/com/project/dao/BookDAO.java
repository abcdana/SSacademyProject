package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.BookDTO;
import com.project.ssacademy.DBUtil;

/**
 * 교재관련 모든 프로시저를 관리하는 DAO
 * @author 박지현, 김다은
 *
 */
public class BookDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public BookDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("BookDAO.BookDAO()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 전체 교재 정보를 ArrayList로 반환하는 메서드이다.
	 * 교재 정보에는 교재 번호, 교재명, 출판자, 저자명이 포함되어있다.
	 * @return
	 */
	public ArrayList<BookDTO> bookList() {
		
		try {
			
			String sql = "select * from tblBook order by seqBook";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<BookDTO> result = new ArrayList<BookDTO>();
			
			while (rs.next()) {
				
				BookDTO bdto = new BookDTO();
				
				bdto.setSeqBook(rs.getString("seqBook"));
				bdto.setName(rs.getString("name"));
				bdto.setPublisher(rs.getString("publisher"));
				bdto.setWriter(rs.getString("writer"));
				
				result.add(bdto);
				
			}
			
			rs.close();
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryBookDAO.enbookList()");
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 새로운 교재를 등록하는 메서드이다.
	 * 교재이름, 출판사, 저자명을 담은 교재 데이터 정보를 받아와 새로운 교재로 등록한다.
	 * 등록 성공 시 1, 실패 시 0을 반환한다.
	 * @param bdto 교재 데이터 정보
	 * @return 성공여부
	 */
	public int addBook(BookDTO bdto) {
		
		try {
			
			String sql = "{ call procAddBook(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, bdto.getName());
			cstat.setString(2, bdto.getPublisher());
			cstat.setString(3, bdto.getWriter());
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBookDAO.enenclosing_method()");
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	/**
	 * 
	 * @param seqBook 교재번호
	 * @return dto 객체
	 */
	public BookDTO get(String seqBook) {
		
		try {
			
			String sql = "select * from tblBook where seqBook = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqBook);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				BookDTO dto = new BookDTO();
				
				dto.setSeqBook(rs.getString("seqBook"));
				dto.setName(rs.getString("name"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setWriter(rs.getString("writer"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryBookDAO.enget()");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 수정할 교재번호를 받아와 해당 번호의 교재의 이름, 출판사, 저자를 수정할 수 있다.
	 * 성공 여부는 성공 시 1, 실패 시 0으로 나타낸다.
	 * @param dto2 교재 데이터 정보
	 * @return 성공 여부
	 */
	public int updateBook(BookDTO dto2) {
		
		try {
			
			String sql = "update tblBook set name = ?, publisher = ?, writer = ?"
							+ "where seqBook = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getPublisher());
			pstat.setString(3, dto2.getWriter());
			pstat.setString(4, dto2.getSeqBook());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryBookDAO.enenclosing_method()");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * 기존 교재 정보를 삭제하는 메서드이다.
	 * 교재 번호를 받아와 교재 데이터를 삭제한다.
	 * 등록 성공시 1, 실패시 0을 반환한다.
	 * @param seqBook 교재 번호
	 * @return 성공 여부
	 */
	public int deleteBook(String seqBook) {

		try {
						
			String sql = "{ call procDeleteBook(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqBook);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryRoomDAO.endeleteRoom()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	

}
