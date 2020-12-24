package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.dto.CompanyInfoDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;
/**
 * 연계기업정보 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class CompanyInfoDAO {

	private Connection conn;
	private Statement stat;

	private PreparedStatement pstat; 

	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public CompanyInfoDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryCompanyInfoDAO.enCompanyInfoDAO()");
			e.printStackTrace();
		}
	}
	/**
	 * 채용공고조회 DAO 채용상태별검색 가능
	 * @param word
	 * @return
	 */
	public ArrayList<VwCompanyInfoDTO> list(String word) {

		String where = "";
	try {

		if(word !=null) {
			where = String.format("where state = '%s'",word); // 채용상태별 검색조회
		}
		String sql = String.format("select * from vwCompanyInfo %s",where);
		rs = stat.executeQuery(sql);

		ArrayList<VwCompanyInfoDTO> list = new ArrayList<VwCompanyInfoDTO>();
		while(rs.next()) {
			VwCompanyInfoDTO dto = new VwCompanyInfoDTO();
			dto.setSeqCompanyInfo(rs.getString("seqCompanyInfo"));
			dto.setName(rs.getString("name"));
			dto.setStartDate(rs.getString("startdate"));
			dto.setEndDate(rs.getString("enddate"));
			dto.setComField(rs.getString("comfield"));
			dto.setSalary(rs.getString("salary"));
			dto.setEmploymentType(rs.getString("employmenttype"));
			dto.setComSize(rs.getString("comsize"));
			dto.setAddress(rs.getString("address"));
			dto.setState(rs.getString("state"));

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
	 * 업무별 검색시 검색가능한 업무목록을 조회
	 * @param word
	 * @return
	 */
public ArrayList<VwCompanyInfoDTO> comfield() { //업무별 검색시 검색가능한 업무목록을 조회


	try {
		String sql = "select comfield from vwCompanyInfo group by comField";
		rs = stat.executeQuery(sql);

		ArrayList<VwCompanyInfoDTO> list = new ArrayList<VwCompanyInfoDTO>();
		while(rs.next()) {
			VwCompanyInfoDTO dto = new VwCompanyInfoDTO();
			dto.setComField(rs.getString("comfield"));
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
 * 소재지별 검색
 * @param word
 * @return
 */
public ArrayList<VwCompanyInfoDTO> city(String word) { //소재지별 검색


	try {
		String sql = "{ call procCityCompany (?,?) }";
		cstat = conn.prepareCall(sql);

		cstat.setString(1, word);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);

		cstat.executeQuery();
		rs = (ResultSet)cstat.getObject(2);
		ArrayList<VwCompanyInfoDTO> list = new ArrayList<VwCompanyInfoDTO>();

		while(rs.next()) {
			VwCompanyInfoDTO dto = new VwCompanyInfoDTO();
			dto.setSeqCompanyInfo(rs.getString("seqCompanyInfo"));
			dto.setName(rs.getString("name"));
			dto.setStartDate(rs.getString("startdate"));
			dto.setEndDate(rs.getString("enddate"));
			dto.setComField(rs.getString("comfield"));
			dto.setSalary(rs.getString("salary"));
			dto.setEmploymentType(rs.getString("employmenttype"));
			dto.setComSize(rs.getString("comsize"));
			dto.setAddress(rs.getString("address"));
			dto.setState(rs.getString("state"));

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
 * 연봉별 검색
 * @param word
 * @param word2
 * @return
 */
public ArrayList<VwCompanyInfoDTO> salary(String word, String word2) { //연봉별 검색


	try {
		String sql = "{ call procSalaryCompany(?,?,?) }";
		cstat = conn.prepareCall(sql);

		cstat.setString(1, word);
		cstat.setString(2, word2);
		cstat.registerOutParameter(3, OracleTypes.CURSOR);

		cstat.executeQuery();
		rs = (ResultSet)cstat.getObject(3);
		ArrayList<VwCompanyInfoDTO> list = new ArrayList<VwCompanyInfoDTO>();
		while(rs.next()) {

			VwCompanyInfoDTO dto = new VwCompanyInfoDTO();
			dto.setSeqCompanyInfo(rs.getString("seqCompanyInfo"));
			dto.setName(rs.getString("name"));
			dto.setStartDate(rs.getString("startdate"));
			dto.setEndDate(rs.getString("enddate"));
			dto.setComField(rs.getString("comfield"));
			dto.setSalary(rs.getString("salary"));
			dto.setEmploymentType(rs.getString("employmenttype"));
			dto.setComSize(rs.getString("comsize"));
			dto.setAddress(rs.getString("address"));
			dto.setState(rs.getString("state"));

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
 * 업무별 검색
 * @param word
 * @return
 */
public ArrayList<VwCompanyInfoDTO> comField(String word) { //업무별 검색


	try {
		String sql = "{ call procFieldCompany (?,?)}"; 
		cstat = conn.prepareCall(sql);

		cstat.setString(1, word);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);

		cstat.executeQuery();		
		rs = (ResultSet)cstat.getObject(2);
		ArrayList<VwCompanyInfoDTO> list = new ArrayList<VwCompanyInfoDTO>();
		while(rs.next()) {

			VwCompanyInfoDTO dto = new VwCompanyInfoDTO();
			dto.setSeqCompanyInfo(rs.getString("seqCompanyInfo"));
			dto.setName(rs.getString("name"));
			dto.setStartDate(rs.getString("startdate"));
			dto.setEndDate(rs.getString("enddate"));
			dto.setComField(rs.getString("comfield"));
			dto.setSalary(rs.getString("salary"));
			dto.setEmploymentType(rs.getString("employmenttype"));
			dto.setComSize(rs.getString("comsize"));
			dto.setAddress(rs.getString("address"));
			dto.setState(rs.getString("state"));

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
 * 채용공고 등록하는 DAO
 * @param dto
 * @return
 */
public int add(CompanyInfoDTO dto) {



	try {
		String sql = "{ call procAddCompany(?,?,?,?,?,?,?,?) }";
		cstat = conn.prepareCall(sql);

		cstat.setString(1, dto.getName());
		cstat.setString(2, dto.getStartDate());
		cstat.setString(3, dto.getEndDate());
		cstat.setString(4, dto.getComField());
		cstat.setString(5, dto.getSalary());
		cstat.setString(6, dto.getEmploymentType());
		cstat.setString(7, dto.getComSize());
		cstat.setString(8, dto.getAddress());

		return cstat.executeUpdate();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	return 0;
}

/**
 * 채용공고 수정을위해 선택한 값을 조회하는 DAO
 * @param seq
 * @return
 */
public VwCompanyInfoDTO editGet(String seq) {


	try {
		String sql = "select * from vwcompanyinfo where seqcompanyinfo = ?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, seq);
		rs = pstat.executeQuery();

		while(rs.next()) {
		VwCompanyInfoDTO dto = new VwCompanyInfoDTO();
		dto.setSeqCompanyInfo(rs.getString("seqCompanyInfo"));
		dto.setName(rs.getString("name"));
		dto.setStartDate(rs.getString("startdate"));
		dto.setEndDate(rs.getString("enddate"));
		dto.setComField(rs.getString("comfield"));
		dto.setSalary(rs.getString("salary"));
		dto.setEmploymentType(rs.getString("employmenttype"));
		dto.setComSize(rs.getString("comsize"));
		dto.setAddress(rs.getString("address"));
		dto.setState(rs.getString("state"));

			return dto;
		}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return null;

}
/**
 * 채용공고 수정 DAO
 * @param dto2
 * @return
 */
	public int edit(CompanyInfoDTO dto2) {


	try {
		String sql = "{ call procUpdateCompany (?,?,?,?,?,?,?,?,?) }";
		pstat = conn.prepareCall(sql);

		pstat.setString(1, dto2.getSeqCompanyInfo());
		pstat.setString(2, dto2.getName());
		pstat.setString(3, dto2.getStartDate());
		pstat.setString(4, dto2.getEndDate());
		pstat.setString(5, dto2.getComField());
		pstat.setString(6, dto2.getSalary());
		pstat.setString(7, dto2.getEmploymentType());
		pstat.setString(8, dto2.getComSize());
		pstat.setString(9, dto2.getAddress());

		return pstat.executeUpdate();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	return 0;
}
/**
 * 채용공고 삭제 
 * @param seq
 * @return
 */
	public int delete(String seq) {

	try {		
		String sql = String.format("delete from tblCompanyInfo where seqCompanyInfo = '%s'",seq);
		pstat = conn.prepareStatement(sql);
		return pstat.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}


}
