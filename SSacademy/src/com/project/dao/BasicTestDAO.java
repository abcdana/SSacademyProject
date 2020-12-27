package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.BasicTestDTO;
import com.project.ssacademy.DBUtil;
import com.project.teacher.dto.VwTestPercentInquiryDTO;

/**
 * 기초시험정보 데이터베이스와 관련된 비즈니스업무 DAO
 * @author 조혜승
 *
 */
public class BasicTestDAO {
	
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	
	public BasicTestDAO() {
		
	}
	
	public ArrayList<BasicTestDTO> list(String subSeq) {
		

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			String where = String.format("where seqOpenSubject = %s",subSeq);
			
			
			String sql = String.format("select * from tblBasicTest %s",where);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<BasicTestDTO> list = new ArrayList<BasicTestDTO>();
			
				//레코드 1개 -> AddressDTO 1개
			while(rs.next()) {
				BasicTestDTO dto = new BasicTestDTO();
				dto.setQuestion(rs.getString("question"));
				dto.setQuestionNum(rs.getString("questionNum"));
				dto.setSeqOpenSubject(rs.getString("seqOpenSubject"));
				dto.setSeqBasicTest(rs.getString("seqBasicTest"));
				list.add(dto);
			}
			//list는 rs랑 동일하게 변한다.
			rs.close();
			stat.close();
			conn.close();
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교사가 직접 시험문제 추가하는 메서드
	 * @param subSeq
	 * @param questionNum
	 * @param question
	 */
public void questionAdd(String subSeq, String questionNum, String question) { 
	try {
		
		conn = DBUtil.open();
		
		String sql = String.format("insert into tblBasicTest (seqBasicTest, seqOpenSubject, question, questionNum) values 			(seqBasicTest.nextVal, %s, '%s', %s)"
							,subSeq,question,questionNum);
		
		stat = conn.createStatement();
		
		int result = stat.executeUpdate(sql);
		
		stat.close();
		conn.close();
		
		System.out.println("\t문제를 추가하는데 성공했습니다.");
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
} 

}
