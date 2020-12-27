package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Scanner;


import com.project.dto.StudentDTO;
import com.project.dto.VwStudentTestScoreDTO;
import com.project.ssacademy.DBUtil;
import com.project.student.dto.StudentListDTO;
import com.project.teacher.dto.TeacherScheduleDTO;

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
	
	
	
	/**
	 * 교육생 조회
	 * @param studentName 검색할 교육생명
	 * @author 박지현
	 */
	public ArrayList<StudentListDTO> studentViewList(String studentName) {
		
		try {
			
			//쿼리
			String sql = String.format("select seqStudent"
					+ ", name"
					+ ", substr(ssn, 1, 6) as ssn"
					+ ", courseName"
					+ ", startDate"
					+ ", endDate"
					+ ", room"
					+ " from vStudentList where name like '%%%s%%'", studentName);
			
			//쿼리날리기
			pstat =  conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<StudentListDTO> list = new ArrayList<StudentListDTO>();
			
			while(rs.next()) {
				
				StudentListDTO sdto = new StudentListDTO();
				
				sdto.setSeqStudent(rs.getString("seqStudent"));
				sdto.setName(rs.getString("name"));
				sdto.setSsn(rs.getString("ssn"));
				sdto.setCourseName(rs.getString("courseName"));
				sdto.setStartDate(rs.getString("startDate"));
				sdto.setEndDate(rs.getString("endDate"));
				sdto.setRoom(rs.getString("room"));
				
				list.add(sdto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.studentViewList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 교육생전체 기본 리스트
	 * 교육생번호, 이름, 아이디, 생년월일, 등록일
	 * @author 박지현
	 */
	public ArrayList<StudentListDTO> studentTotalList(int page) {
		
		try {

			int start = 1 + (page-1) * 10;	//한페이지에서 보여줄 첫번째 rownum
			int end = 10 * page; 	//한페이지에서 마지막으로 보여줄 rownum
			
			//쿼리
			String sql = "select * from vStudentList where num2 between ? and ?";
			
			//쿼리날리기
			pstat =  conn.prepareStatement(sql);
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			rs = pstat.executeQuery();
			
			ArrayList<StudentListDTO> list = new ArrayList<StudentListDTO>();
			
			while(rs.next()) {
				
				StudentListDTO sdto = new StudentListDTO();
				
				sdto.setRownum(rs.getInt("num2"));
				sdto.setSeqStudent(rs.getString("seqStudent"));
				sdto.setName(rs.getString("name"));
				sdto.setId(rs.getString("studentId"));
				sdto.setSsn(rs.getString("ssn"));
				sdto.setFirstRegistDate(rs.getString("registDate").substring(0, 10));
				
				list.add(sdto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.studentTotalList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 교육생 조회
	 * @param rownum 검색할 교육생 행번호
	 * @author 박지현
	 */
	public ArrayList<StudentListDTO> seqStudentList(int rownum) {
		
		try {
			
			//쿼리
			String sql = String.format("select seqStudent"
					+ ", name"
					+ ", substr(ssn, 1, 6) as ssn"
					+ ", courseName"
					+ ", startDate"
					+ ", endDate"
					+ ", room"
					+ ", num2"
					+ " from vStudentList where num2 = %s", rownum);
			
			//쿼리날리기
			pstat =  conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<StudentListDTO> list = new ArrayList<StudentListDTO>();
			
			while(rs.next()) {
				
				StudentListDTO sdto = new StudentListDTO();
				
				sdto.setRownum(rs.getInt("num2"));
				sdto.setSeqStudent(rs.getString("seqStudent"));
				sdto.setName(rs.getString("name"));
				sdto.setSsn(rs.getString("ssn"));
				sdto.setCourseName(rs.getString("courseName"));
				sdto.setStartDate(rs.getString("startDate"));
				sdto.setEndDate(rs.getString("endDate"));
				sdto.setRoom(rs.getString("room"));
				
				list.add(sdto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.seqStudentList()");
			e.printStackTrace();
		}
		
		return null;
	}

	public int getCountStudent() {
		//교육생 총 수

		int count = 0;
			
		try {

			// 교육생 총 수
			String sql = "select count(*) as count from vStudentList";
				
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
				
			if(rs.next()) {
				count = rs.getInt("count");
					
				rs.close();
				
				return count;
			}
			
		} catch (Exception e) {
			System.out.println("StudentDAO.getCountStudent()");
			e.printStackTrace();
		}
			
		return 0;
	}
	
	
	/**
	 * 학생의 개인정보를 가져오는 메서드입니다.
	 * @return
	 */
	
	public ArrayList<StudentDTO> list() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.open();

			String sql = String.format("select * from  tblStudent");
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();

			while (rs.next()) {
				// 레코드 1개 -> AddressDTO 1개
				StudentDTO dto = new StudentDTO();
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("Name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setFirstRegistrationDate(rs.getString("firstRegistrationDate"));
				dto.setPhone(rs.getString("phone"));
				list.add(dto);
			}
			rs.close();
			stat.close();
			conn.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
		
}
	
	





