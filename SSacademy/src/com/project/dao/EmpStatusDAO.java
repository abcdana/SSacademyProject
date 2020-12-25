package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.VwEmpStatusDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.dto.CompanyInfoDTO;
import com.project.dto.EmpStatusDTO;
import com.project.ssacademy.DBUtil;
/**
 * 취업정보현황 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class EmpStatusDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat; 
	private ResultSet rs;

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public EmpStatusDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryEmpStatusDAO.enEmpStatusDAO()");
			e.printStackTrace();
		}
		
	}
	/**
	 * 교육생 이름 ** 표시해주는
	 * 연계기업에 취업된 학생 목록을 조회하는 메서드
	 * @return
	 */
	public ArrayList<VwEmpStatusDTO> cjobListS(String word) { 
		
		String where = "";
		try {
			
			if(word!=null) {
				where = String.format("where name = '%s'",word);
			}
			String sql = String.format("select * from vwSAllempStatus %s",where);
			rs = stat.executeQuery(sql);
			ArrayList<VwEmpStatusDTO> list = new ArrayList<VwEmpStatusDTO>();
			while(rs.next()) {
				VwEmpStatusDTO dto = new VwEmpStatusDTO();
				
				dto.setSeq(rs.getString("seq"));					//연계기업취업정보번호
				dto.setName(rs.getString("name"));					//학생이름
				dto.setId(rs.getString("id"));						//학생아이디
				dto.setCompanyName(rs.getString("companyname"));	//회사이름
				dto.setDuty(rs.getString("duty"));					//업무
				dto.setForm(rs.getString("form"));					//고용형태
				dto.setSalary(rs.getString("salary"));				//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));		//취업일
				dto.setLocation(rs.getString("location"));			//회사주소
				dto.setCourse(rs.getString("course"));				//수료한과정명
				
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
	 * 연계기업에 취업된 학생(이름표시) 목록을 조회하는 메서드
	 * @return
	 */
	public ArrayList<VwEmpStatusDTO> cjobList(String word) { 
		
		String where = "";
		try {
			
			if(word!=null) {
				where = String.format("where name = '%s'",word);
			}
			String sql = String.format("select * from vwAllempStatus %s",where);
			rs = stat.executeQuery(sql);
			ArrayList<VwEmpStatusDTO> list = new ArrayList<VwEmpStatusDTO>();
			while(rs.next()) {
				VwEmpStatusDTO dto = new VwEmpStatusDTO();
				
				dto.setSeq(rs.getString("seq"));					//연계기업취업정보번호
				dto.setName(rs.getString("name"));					//학생이름
				dto.setId(rs.getString("id"));						//학생아이디
				dto.setCompanyName(rs.getString("companyname"));	//회사이름
				dto.setDuty(rs.getString("duty"));					//업무
				dto.setForm(rs.getString("form"));					//고용형태
				dto.setSalary(rs.getString("salary"));				//연봉
				dto.setGetJobDate(rs.getString("getjobdate"));		//취업일
				dto.setLocation(rs.getString("location"));			//회사주소
				dto.setCourse(rs.getString("course"));				//수료한과정명
				
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
	 * 연계기업의 목록을 조회하는 메서드
	 * 연계기업 취업등록할때 출력해준다.
	 * @return
	 */
	public ArrayList<CompanyInfoDTO> companyList() { 
		
		try {
			String sql = "select seqcompanyinfo, name, address from tblCompanyInfo";
			rs = stat.executeQuery(sql);
			ArrayList<CompanyInfoDTO> list = new ArrayList<CompanyInfoDTO>();
			while(rs.next()) {
				CompanyInfoDTO dto = new CompanyInfoDTO();
				dto.setSeqCompanyInfo(rs.getString("seqcompanyinfo")); //회사정보번호
				dto.setName(rs.getString("name"));						//회사이름
				dto.setAddress(rs.getString("address"));	
				
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
	 * 특정한 연계기업에 취업한 학생을 조회하는 메서드
	 * 연계기업 취업등록할때 출력해준다.
	 * @param seqCompanyInfo 
	 * @return
	 */
	public ArrayList<VwGetJobInfoDTO> companyGetJob(String seqCompanyInfo) { 
		
		try {
			String sql = String.format("select * from vwEmpStatus where companyname = (select name from tblcompanyinfo where seqcompanyinfo = %s) order by getjobdate desc",seqCompanyInfo);
			rs = stat.executeQuery(sql);
			ArrayList<VwGetJobInfoDTO> list = new ArrayList<VwGetJobInfoDTO>();
			while(rs.next()) {
				VwGetJobInfoDTO dto = new VwGetJobInfoDTO();
				dto.setGjseq(rs.getString("gjseq")); 				//채용정보번호
				dto.setRcseq(rs.getString("rcseq")); 				//수강정보번호
				dto.setName(rs.getString("name"));  				//학생이름
				dto.setId(rs.getString("id")); 					//학생id
				dto.setCompanyName(rs.getString("companyname")); //회사이름
				dto.setGetJobDate(rs.getString("getjobdate")); //취업일
				dto.setLocation(rs.getString("location")); 		//회사주소
				
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
	 * 연계기업 취업등록을 위한 메서드
	 * @param rcseq 
	 * @param gjseq 
	 * @param seqCompanyInfo 
	 * @return
	 */
	public int addEmpStatus(EmpStatusDTO dto) {
		try {
			String sql = "{ call procAddEmpStatus(?,?,?) }";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getSeqCompanyInfo());
			pstat.setString(2, dto.getSeqGetJobInfo());
			pstat.setString(3, dto.getSeqRegCourse());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 관리자
	 * 연계기업 취업정보 삭제할 목록을다시한번 조회시켜주는 메서드
	 * @param seq
	 * @return
	 */
	public VwEmpStatusDTO getList(String seq) {
		
		try {
			String sql = String.format("select * from vwAllEmpStatus where seq = %s",seq);
			rs = stat.executeQuery(sql);
			rs.next();
			VwEmpStatusDTO dto = new VwEmpStatusDTO();
			
			dto.setSeq(rs.getString("seq"));					//연계기업취업정보번호
			dto.setName(rs.getString("name"));					//학생이름
			dto.setId(rs.getString("id"));						//학생아이디
			dto.setCompanyName(rs.getString("companyname"));	//회사이름
			dto.setDuty(rs.getString("duty"));					//업무
			dto.setForm(rs.getString("form"));					//고용형태
			dto.setSalary(rs.getString("salary"));				//연봉
			dto.setGetJobDate(rs.getString("getjobdate"));		//취업일
			dto.setLocation(rs.getString("location"));			//회사주소
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
	 * 연계기업취업정보 삭제 해주는 메서드 
	 * @param seq
	 * @return
	 */
	public int deleteEmpStatus(String seq) {

		try {
			String sql = "{ call procDeleteEmpStatus(?)}";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}


	
}
