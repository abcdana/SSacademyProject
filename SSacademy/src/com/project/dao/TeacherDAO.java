package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.dto.TeacherDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;


/**
 * 교사 계졍과 관련된 기능을 담당하는 DAO
 * @author 김주혁, 김다은
 *
 */
public class TeacherDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 생성자
	 */
	public TeacherDAO() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.TeacherDAO()");
			e.printStackTrace();
		}
		
	} //TeacherDAO

	
	/**
	 * 
	 * @param id
	 * @return Teacher DTO (해당 id의 교사 정보)
	 */
	public TeacherDTO getTeacher(String id) {

		try {
			
			String sql = "select * from tblTeacher where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryTeacherDAO.engetTeacher()");
			e.printStackTrace();
		}
		
		return null;
	}


	public ArrayList<TeacherDTO> list() {
		
		try {
			
			String sql = "select * from tblTeacher order by seqTeacher";
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeqTeacher(rs.getString("seqTeacher"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
				
			}
			
			rs.close();
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}


	public int add(TeacherDTO dto) {
		
		try {
					
			String sql = "{ call procAddTeacher(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getName()); //이름
			cstat.setString(2, dto.getSsn()); //주민등록번호
			cstat.setString(3, dto.getTel()); //전화번호
			cstat.registerOutParameter(4, OracleTypes.NUMBER); //교사번호(out)
			
			int result = cstat.executeUpdate();
			
			dto.setSeqTeacher(cstat.getString(4)); //교사번호 저장
			
			return result;
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
		
	}


	public TeacherDTO get(String seqTeacher) {
		
		try {
			
			String sql = "select * from tblTeacher where seqTeacher = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setNString(1, seqTeacher);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				TeacherDTO tdto = new TeacherDTO();
				
				tdto.setSeqTeacher(rs.getString("seqTeacher"));
				tdto.setName(rs.getString("name"));
				tdto.setId(rs.getString("id"));
				tdto.setSsn(rs.getString("ssn"));
				tdto.setTel(rs.getString("tel"));
				
				return tdto;
			}
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.get()");
			e.printStackTrace();
		}
		
		return null;
		
	}


	public int edit(TeacherDTO newtdto) {
		
		try {
			
			String sql = "{ call procUpdateTeacher(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, newtdto.getSeqTeacher());
			cstat.setString(2, newtdto.getName());
			cstat.setString(3, newtdto.getSsn());
			cstat.setString(4, newtdto.getTel());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
