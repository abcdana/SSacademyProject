package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.dto.StudentDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;

//학생정보
public class StudentDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public StudentDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.StudentDAO()");
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param id
	 * @return Student DTO (해당 id의 학생 정보)
	 */
	public StudentDTO getStudent(String id) {
		
		try {
			
			String sql = "select * from tblStudent where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				StudentDTO dto = new StudentDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setFirstRegistrationDate(rs.getString("firstRegistrationDate"));
				dto.setEmploymentField(rs.getString("employmentField"));
				
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("primaryStudentDAO.engetStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @return Student DTO (전체 학생정보)
	 */
	public StudentDTO getStudentAll() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call chkStudentAll(?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			//Oracle(cursor) -> JDBC(ResultSet)
			ResultSet rs = (ResultSet)stat.getObject(1);
			
			while (rs.next()) {
				System.out.println();
				System.out.printf("\t학생번호 : %s\n",rs.getString("seqStudent_s"));
				System.out.printf("\t성명 : %s\n",rs.getString("name_s"));
				System.out.printf("\t아이디 : %s\n",rs.getString("id")==null?"-":rs.getString("id"));
				System.out.printf("\t휴대폰 : %s\n",rs.getString("phone")==null?"-":rs.getString("phone"));
				System.out.printf("\t이메일 : %s\n",rs.getString("email")==null?"-":rs.getString("email"));
				System.out.printf("\t수강번호 : %s\n",rs.getString("seqRegCourse")==null?"-":rs.getString("seqRegCourse"));
				System.out.printf("\t취업희망분야 : %s\n",rs.getString("employmentField")==null?"-":rs.getString("employmentField"));
				System.out.printf("\t가입일 : %s\n",rs.getString("firstRegistrationDate")==null?"-":rs.getString("firstRegistrationDate"));
				System.out.printf("\t교육과정명 : %s\n",rs.getString("name_b")==null?"-":rs.getString("name_b"));
			}
			System.out.println();
			
			rs.close();
			stat.close();
			conn.close();
			
			System.out.println("뒤로가려면 아무 키나 누르세요");

		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m5()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}