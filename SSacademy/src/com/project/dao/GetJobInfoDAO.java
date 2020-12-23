package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.CompleteStudentDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.dto.GetJobInfoDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;
/**
 * 수료생취업정보 데이터베이스와 관련된 비즈니스업무 DAO
 * @author 조혜승
 *
 */
public class GetJobInfoDAO {
	
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	CallableStatement cstat = null;
	ResultSet rs = null;
	
	public GetJobInfoDAO() {
		
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<VwGetJobInfoDTO> jobList(String word) {  //수료생 취업정보 전체조회
		
		String where ="";
		try {
			if(word !=null) {
				where = String.format("where substr(getjobdate,1,4) = '%s'",word); //연도별 취업정보 조회
			}
			String sql = String.format("select * from vwgetjobinfoa %s",where);
			rs = stat.executeQuery(sql);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				
				dto.setGjseq(rs.getString("gjseq"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setCompanyName(rs.getString("companyname"));
				dto.setDuty(rs.getString("duty"));
				dto.setForm(rs.getString("form"));
				dto.setSalary(rs.getString("salary"));
				dto.setGetJobDate(rs.getString("getjobdate"));
				dto.setLocation(rs.getString("location"));
				dto.setRcseq(rs.getString("rcseq"));
				dto.setCourse(rs.getString("course"));
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			

			e.printStackTrace();
		}
		
		
		return null;
	}
	public ArrayList<VwGetJobInfoDTO> dutyList() { //업무별 취업공고 조회위한 업무목록
		try {
			String sql = "select duty from vwgetjobinfoa group by duty order by duty";
			
			rs = stat.executeQuery(sql);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setDuty(rs.getString("duty"));	
				list.add(dto);
				
				}
			return list;
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		return null;
	}
	public ArrayList<VwGetJobInfoDTO> dutySearch(String word) { //업무별 검색조회
		
	
		
		try {
			String sql = "{ call procDutyGetJobA (?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, word);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while (rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setCompanyName(rs.getString("companyname"));
				dto.setDuty(rs.getString("duty"));
				dto.setForm(rs.getString("form"));
				dto.setSalary(rs.getString("salary"));
				dto.setGetJobDate(rs.getString("getjobdate"));
				dto.setLocation(rs.getString("location"));
				dto.setRcseq(rs.getString("rcseq"));
				dto.setCourse(rs.getString("course"));
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	public ArrayList<VwGetJobInfoDTO> locationSearch(String word) { //소재지별 검색 조회
		try {
			String sql = "{ call procCityGetJobA (?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, word);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while (rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setCompanyName(rs.getString("companyname"));
				dto.setDuty(rs.getString("duty"));
				dto.setForm(rs.getString("form"));
				dto.setSalary(rs.getString("salary"));
				dto.setGetJobDate(rs.getString("getjobdate"));
				dto.setLocation(rs.getString("location"));
				dto.setRcseq(rs.getString("rcseq"));
				dto.setCourse(rs.getString("course"));
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public ArrayList<VwGetJobInfoDTO> salarySearch(String word, String word2) { //연봉별 검색조회
		try {
			String sql = "{ call procSalaryGetJobA (?,?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, word);
			cstat.setString(2, word2);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(3);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while (rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setCompanyName(rs.getString("companyname"));
				dto.setDuty(rs.getString("duty"));
				dto.setForm(rs.getString("form"));
				dto.setSalary(rs.getString("salary"));
				dto.setGetJobDate(rs.getString("getjobdate"));
				dto.setLocation(rs.getString("location"));
				dto.setRcseq(rs.getString("rcseq"));
				dto.setCourse(rs.getString("course"));
				
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<CompleteStudentDTO> completeS() { // 취업 미등록된 수료생목록
		

		
		try {
			String sql = "select * from vwCompleteStudent minus select * from vwGetJobStudent";
			rs = stat.executeQuery(sql);
			ArrayList<CompleteStudentDTO> list = new ArrayList<CompleteStudentDTO>();
			while(rs.next()) {
				CompleteStudentDTO dto = new CompleteStudentDTO();
				dto.setRcseq(rs.getString("rcseq"));
				dto.setSname(rs.getString("sname"));
				dto.setId(rs.getString("id"));
				dto.setCourse(rs.getString("course"));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int addGetJobInfo(GetJobInfoDTO dto) {
		
		
		try {
			String sql = "{ call procAddGetJobInfo (?,?,?,?,?,?,?) } ";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeqRegCourse());
			pstat.setString(2, dto.getLocation());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getDuty());
			pstat.setString(5, dto.getSalary());
			pstat.setString(6, dto.getForm());
			pstat.setString(7, dto.getGetJobDate());
	
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public ArrayList<VwGetJobInfoDTO> nameSearch(String name) {
		
		try {
			String sql = String.format("select * from vwGetJobInfoa where name = '%s'",name);
			rs = stat.executeQuery(sql);
			 ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setCompanyName(rs.getString("companyname"));
				dto.setDuty(rs.getString("duty"));
				dto.setForm(rs.getString("form"));
				dto.setSalary(rs.getString("salary"));
				dto.setGetJobDate(rs.getString("getjobdate"));
				dto.setLocation(rs.getString("location"));
				dto.setRcseq(rs.getString("rcseq"));
				dto.setCourse(rs.getString("course"));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	public int editGetJobInfo(GetJobInfoDTO dto2) {
		try {
			String sql = "{ call procUpdateGetJobInfo(?,?,?,?,?,?,?)}";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto2.getSeqGetJobInfo());
			pstat.setString(2, dto2.getLocation());
			pstat.setString(3, dto2.getName());
			pstat.setString(4, dto2.getDuty());
			pstat.setString(5, dto2.getSalary());
			pstat.setString(6, dto2.getForm());
			pstat.setString(7, dto2.getGetJobDate());
			
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public VwGetJobInfoDTO getEdit(String seqGetJobInfo) { // 수정할 한가지목록 가져오기
		
		try {
			String sql = String.format("select * from vwGetJobInfoA where gjseq = %s",seqGetJobInfo);
			rs = stat.executeQuery(sql);
			rs.next();
			VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
			dto.setRcseq(rs.getString("rcseq"));
			dto.setGjseq(rs.getString("gjseq"));
			dto.setName(rs.getString("name"));
			dto.setCompanyName(rs.getString("companyname"));
			dto.setLocation(rs.getString("location"));
			dto.setDuty(rs.getString("duty"));
			dto.setSalary(rs.getString("salary"));
			dto.setForm(rs.getString("form"));
			dto.setGetJobDate(rs.getString("getjobdate"));
			dto.setCourse(rs.getString("course"));
			
			
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}

	public int deleteJob(String seqGetJobInfo) { //취업정보 삭제 
		
		try {
			String sql = "{ call procDeleteGetJobInfo(?) }";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqGetJobInfo);
			
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
