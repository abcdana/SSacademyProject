package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.admin.dto.CompleteStudentDTO;
import com.project.admin.dto.VwEmpStatusDTO;
import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.dto.CompanyInfoDTO;
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
	
	/**
	 * 교육생조회 이름**표시
	 * 취업정보전체조회 및 연도별취업정보검색조회
	 * @param word
	 * @return
	 */
	
	public ArrayList<VwGetJobInfoDTO> jobListS(String word) { 
		
		String where ="";
		try {
			if(word !=null) {
				where = String.format("where substr(getjobdate,1,4) = '%s'",word); //연도별 취업정보 조회
			}
			String sql = String.format("select * from vwgetjobinfo %s",where);
			rs = stat.executeQuery(sql);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			

			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 	 교육생조회 이름**표시
	 * 업무별 취업공고 조회를 위한 업무목록을 출력해주는 메서드
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> dutyListS() { 
		try {
			String sql = "select duty from vwgetjobinfo group by duty order by duty";
			
			rs = stat.executeQuery(sql);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setDuty(rs.getString("duty"));	//업무
				list.add(dto);
				
				}
			return list;
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		return null;
	}
	/**
	 * 	교육생조회 이름**표시
	 * 취업정보 조회시 업무별로 검색해서 조회하도록 하는 메소드이다.
	 * @param word
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> dutySearchS(String word) { //업무별 검색조회
		
		try {
			String sql = "{ call procDutyGetJob (?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, word);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while (rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	/**
	 * 교육생조회 이름**표시
	 * 취업정보 조회시 소재지별로 검색할 수 있도록 하는 메서드
	 * @param word
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> locationSearchS(String word) { //소재지별 검색 조회
		try {
			String sql = "{ call procCityGetJob (?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, word);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while (rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 교육생 조회 이름**표시
	 * 취업정보 조회시 연봉의 최소값과 최대값의 사이를 입력해서 검색해주는 메소드
	 * @param word
	 * @param word2
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> salarySearchS(String word, String word2) { 
		try {
			String sql = "{ call procSalaryGetJob (?,?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, word);
			cstat.setString(2, word2);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(3);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while (rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 관리자 수료생 취업정보 전체를 조회할 수있는 메서드
	 * @param word
	 * @return
	 */

	public ArrayList<VwGetJobInfoDTO> jobList(String word) { 
		
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
				
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			

			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 관리자 업무별 취업공고 조회를 위한 업무목록을 출력해주는 메서드
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> dutyList() { 
		try {
			String sql = "select duty from vwgetjobinfoa group by duty order by duty";
			
			rs = stat.executeQuery(sql);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setDuty(rs.getString("duty"));	//업무
				list.add(dto);
				
				}
			return list;
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		return null;
	}
	/**
	 * 관리자
	 * 취업정보 조회시 업무별로 검색해서 조회하도록 하는 메소드이다.
	 * @param word
	 * @return
	 */
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
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	/**
	 * 관리자
	 * 취업정보 조회시 소재지별로 검색할 수 있도록 하는 메서드
	 * @param word
	 * @return
	 */
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
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 관리자
	 * 취업정보 조회시 연봉의 최소값과 최대값의 사이를 입력해서 검색해주는 메소드
	 * @param word
	 * @param word2
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> salarySearch(String word, String word2) { 
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
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 관리자
	 * 수료는 했으나, 아직 취업 등록되지 않은 학생목록을 불러와주는 메서드
	 * @return
	 */
	public ArrayList<CompleteStudentDTO> completeS() { 
		

		
		try {
			String sql = "select * from vwCompleteStudent minus select * from vwGetJobStudent";
			rs = stat.executeQuery(sql);
			ArrayList<CompleteStudentDTO> list = new ArrayList<CompleteStudentDTO>();
			while(rs.next()) {
				CompleteStudentDTO dto = new CompleteStudentDTO();
				dto.setRcseq(rs.getString("rcseq"));	//수강정보번호
				dto.setSname(rs.getString("sname"));	//학생이름
				dto.setId(rs.getString("id"));			//학생아이디
				dto.setCourse(rs.getString("course"));	//수료한 과정명
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 관리자
	 * 수료생의 취업정보를 등록 해주는 메서드
	 * @param dto
	 * @return
	 */

	public int addGetJobInfo(GetJobInfoDTO dto) {
		
		
		try {
			String sql = "{ call procAddGetJobInfo (?,?,?,?,?,?,?) } ";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeqRegCourse()); //수강정보번호
			pstat.setString(2, dto.getLocation());		//회사주소
			pstat.setString(3, dto.getName());			//회사이름
			pstat.setString(4, dto.getDuty());			//업무
			pstat.setString(5, dto.getSalary());		//연봉
			pstat.setString(6, dto.getForm());			//고용형태
			pstat.setString(7, dto.getGetJobDate());		//취업일
	
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	/**
	 * 관리자
	 * 취업정보를 수정, 삭제하기위해 이름을 검색해서 수정,삭제하기 위한 학생을 검색하는 메서드
	 * 동명이인이 있을수 있으므로 목록 출력해준다.
	 * @param name
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> nameSearch(String name) {
		
		try {
			String sql = String.format("select * from vwGetJobInfoa where name = '%s'",name);
			rs = stat.executeQuery(sql);
			 ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq"));		//취업정보번호
				dto.setName(rs.getString("name"));			//학생이름
				dto.setId(rs.getString("id"));				//학생아이디
				dto.setCompanyName(rs.getString("companyname"));//회사이름
				dto.setDuty(rs.getString("duty"));				//업무
				dto.setForm(rs.getString("form"));				//고용형태
				dto.setSalary(rs.getString("salary"));			//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));	//취업일
				dto.setLocation(rs.getString("location"));		//회사주소
				dto.setRcseq(rs.getString("rcseq"));			//수강정보번호
				dto.setCourse(rs.getString("course"));			//수료한과정명
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 관리자
	 * 수료생의 취업정보를 수정하는 메서드,
	 * @param dto2
	 * @return
	 */
	public int editGetJobInfo(GetJobInfoDTO dto2) {
		try {
			String sql = "{ call procUpdateGetJobInfo(?,?,?,?,?,?,?)}";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto2.getSeqRegCourse()); //수강정보번호
			pstat.setString(2, dto2.getLocation());		//회사주소
			pstat.setString(3, dto2.getName());			//회사이름
			pstat.setString(4, dto2.getDuty());			//업무
			pstat.setString(5, dto2.getSalary());		//연봉
			pstat.setString(6, dto2.getForm());			//고용형태
			pstat.setString(7, dto2.getGetJobDate());		//취업일
			
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 관리자
	 * 수정할 특정 취업정보를 가져오는 메서드
	 * @param seqGetJobInfo
	 * @return
	 */

	public VwGetJobInfoDTO getEdit(String seqGetJobInfo) { 
		
		try {
			String sql = String.format("select * from vwGetJobInfoA where gjseq = %s",seqGetJobInfo);
			rs = stat.executeQuery(sql);
			rs.next();
			VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
			dto.setRcseq(rs.getString("rcseq"));				//수강정보번호
			dto.setGjseq(rs.getString("gjseq"));				//채용정보번호
			dto.setName(rs.getString("name"));					//학생이름
			dto.setId(rs.getString("id"));						//학생ID
			dto.setCompanyName(rs.getString("companyname"));		//회사이름
			dto.setLocation(rs.getString("location"));			//회사주소
			dto.setDuty(rs.getString("duty"));					//업무
			dto.setSalary(rs.getString("salary"));					//급여
			dto.setForm(rs.getString("form"));					//고용형태
			dto.setGetJobDate(rs.getString("getjobdate"));		//취업일
			dto.setCourse(rs.getString("course"));				//수료한과정명
			
			
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}
	/**
	 * 관리자
	 * 수료생의 취업정보를 삭제하는 메서드
	 * @param seqGetJobInfo
	 * @return
	 */
	public int deleteJob(String seqGetJobInfo) { 
		
		try {
			String sql = "{ call procDeleteGetJobInfo(?) }";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqGetJobInfo); //취업정보번호
			
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}


}
