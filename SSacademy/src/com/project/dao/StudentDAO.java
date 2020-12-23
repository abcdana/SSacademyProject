package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Scanner;


import com.project.dto.StudentDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 학생정보관련 모든 프로시저들을 담고있는 DAO이다. 
 * @author 임채원
 *
 */
public class StudentDAO {

	private static Connection conn;
	private static Statement stat;
	private static PreparedStatement pstat;
	private static CallableStatement cstat;
	private static ResultSet rs;
	private static Scanner scanner = new Scanner(System.in);
	
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
	public ArrayList<StudentDTO> getStudent(String id) {
		
		try {
			
			String sql = String.format("select * from tblStudent where id like '%%%s%%'",id);
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			return setDTOList(rs);
			
		} catch (Exception e) {
			System.out.println("primaryStudentDAO.engetStudent()");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return Student DTO (전체 교육생 정보출력)
	 */
	public StudentDTO getStudentAll(StudentDTO dto) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call chkStudentAll(?) }";
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(1);
			
			while (rs.next()) {
				dto.num++;
				System.out.println();
				System.out.printf(""
						+ "\t학생번호     : %s\n"
						+ "\t성명         : %s\n"
						+ "\t아이디       : %s\n"
						+ "\t휴대폰       : %s\n"
						+ "\t이메일       : %s\n"
						+ "\t수강번호     : %s\n"
						+ "\t취업희망분야 : %s\n"
						+ "\t가입일       : %s\n"
						+ "\t교육과정명   : %s\n",
						rs.getString("seqStudent_s"),
						rs.getString("name_s"),
						rs.getString("id")==null?"-":rs.getString("id"),
						rs.getString("phone")==null?"-":rs.getString("phone"),
						rs.getString("email")==null?"-":rs.getString("email"),
						rs.getString("seqRegCourse")==null?"-":rs.getString("seqRegCourse"),
						rs.getString("employmentField")==null?"-":rs.getString("employmentField"),
						rs.getString("firstRegistrationDate")==null?"-":rs.getString("firstRegistrationDate"),
						rs.getString("name_b")==null?"-":rs.getString("name_b")
				);
			}
			System.out.println();
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m5()");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	/**
	 * 
	 * @param 학생번호
	 * @return 해당 학생번호의 교육생 정보
	 */
	public StudentDTO getStudentSeq(String SeqRegCourse) {
		
		try {
			
			String sql = "select * from tblStudent where seqStudent = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, SeqRegCourse);
			rs = pstat.executeQuery();
			
		} catch (Exception e) {
			System.out.println("primaryStudentDAO.engetStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param 수강번호
	 * @return 해당 수강번호의 교육생 정보
	 */
	public StudentDTO getStudentRegSeq(String SeqRegCourse) {
		
		try {
			
			String sql = "select * from tblStudent s inner join tblRegCourse r on s.seqStudent=r.seqStudent where seqRegCourse = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, SeqRegCourse);
			rs = pstat.executeQuery();
			
			return setDTO(rs);
			
			
		} catch (Exception e) {
			System.out.println("primaryStudentDAO.engetStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param 이름
	 * @return 해당 이름의 교육생 정보
	 */
	public ArrayList<StudentDTO> getStudentName(String name) {
		
		try {
			
			String sql = "select * from tblStudent where name like '%"+name+"%'";
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			return setDTOList(rs);
			
		} catch (Exception e) {
			System.out.println("primaryStudentDAO.engetStudent()");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 교육생 추가
	 */
	public static void addStudent() {
		
		System.out.println();
		System.out.print("\t- 이름을 입력하세요.(1/6) : ");
		String name = scanner.nextLine();
		System.out.print("\t- 아이디를 입력하세요.(2/6) : ");
		String id = scanner.nextLine();
		System.out.print("\t- 주민등록번호를 입력하세요.(3/6) : ");
		String ssn = scanner.nextLine();
		System.out.print("\t- 휴대폰번호를 입력하세요.(4/6) : ");
		String phone = scanner.nextLine();
		System.out.print("\t- 이메일을 입력하세요.(5/6) : ");
		String email = scanner.nextLine();
		System.out.print("\t- 희망취업분야를 입력하세요.(6/6) : ");
		String employmentField = scanner.nextLine();

		try {
			
			conn = DBUtil.open();
			
			String sql = "insert into tblStudent (seqStudent,name,id,ssn,phone,email,firstRegistrationDate,employmentField) values (seqStudent.nextVal,?,?,?,?,?,sysdate,?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,name);
			pstat.setString(2,id);
			pstat.setString(3,ssn);
			pstat.setString(4,phone);
			pstat.setString(5,email);
			pstat.setString(6,employmentField);
			
			int result = pstat.executeUpdate();
			
			System.out.println();
			pstat.close();
			conn.close();
			
			if (result==1) {
				System.out.println("\t\t※ 추가가 완료되었습니다.");
			}else {
				System.out.println("\t\t※ 추가실패");
			}
			System.out.println();
			
			return;
			
		} catch (Exception e) {
			System.out.println("test.main()");
			e.printStackTrace();
		}
		
	}

	/**
	 * 교육생 정보 수정
	 * @param dto2
	 * @return result
	 */
	public int editInfo(StudentDTO dto2) {
		
		try {
			
			String sql = "update tblStudent set name=?,id=?,phone=?,email=?,firstRegistrationDate=?,employmentField=? where seqStudent=?";	
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getId());
			pstat.setString(3, dto2.getPhone());
			pstat.setString(4, dto2.getEmail());
			pstat.setString(5, dto2.getFirstRegistrationDate());
			pstat.setString(6, dto2.getEmploymentField());
			pstat.setString(7, dto2.getSeqStudent());
			
			
			int result = pstat.executeUpdate();
			
			pstat.close();
			conn.close();
			
			return result;

		} catch (Exception e) {
			System.out.println("StudentDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	/**
	 * 교육생 정보 삭제
	 * @param seq
	 * @return result
	 */
	public int removeStudent(String seq) {

		try {

			String sql = "delete from tblStudent where seqStudent = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("StudentDAO.delete()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교육생 정보 리스트
	 * @param id 일부 검색
	 * @return 배열로 반환
	 */
	public ArrayList<StudentDTO> studentList(String word) {
		
		try {
			
			String where = "";
			if (word != null) {
				where = String.format("where id like '%%%s%%'", word);
			}
			
			String sql = String.format("select * from tblStudent %s order by seqStudent desc", where);
			rs = stat.executeQuery(sql);
			return setDTOList(rs);

		} catch (Exception e) {
			System.out.println("AddressDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public StudentDTO get(String seq) {
		
		
		try {
			
			String sql = "select * from tblStudent where seqStudent = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			return setDTO(rs);

		} catch (Exception e) {
			System.out.println("AddressDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	public StudentDTO setDTO(ResultSet rs) {
		try {
			if (rs.next()) {
				
				StudentDTO dto = new StudentDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id")==null?"-":rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setPhone(rs.getString("phone")==null?"-":rs.getString("phone"));
				dto.setEmail(rs.getString("email")==null?"-":rs.getString("email"));
				dto.setFirstRegistrationDate(rs.getString("firstRegistrationDate")==null?"-":rs.getString("firstRegistrationDate"));
				dto.setEmploymentField(rs.getString("employmentField")==null?"-":rs.getString("employmentField"));
				return dto;
			}
		} catch (Exception e) {
			System.out.println("StudentDAO.setDTO()");
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<StudentDTO> setDTOList(ResultSet rs){
		
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setFirstRegistrationDate(rs.getString("firstRegistrationDate"));
				dto.setEmploymentField(rs.getString("employmentField"));
				
				list.add(dto);				
			}
		} catch (Exception e) {
			System.out.println("StudentDAO.setDTOList()");
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}




