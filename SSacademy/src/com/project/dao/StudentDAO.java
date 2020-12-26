package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdministerStudent;
import com.project.dto.StudentDTO;
import com.project.ssacademy.DBUtil;

import oracle.jdbc.OracleTypes;

//학생정보
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
	 * 해당 id의 교육생 정보를 반환하는 메소드
	 * @param 교육생 아이디
	 * @return 교육생 정보 리스트
	 */
	public static ArrayList<StudentDTO> getStudent(String id) {
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
	 * 모든 교육생 정보를 출력하는 메소드
	 */
	public void getStudentAll() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		conn = DBUtil.open();
		
		try {

			String sql = "{ call chkStudentAll(?) }";
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(1);
			
			int num=0;
			while (rs.next()) {
				num++;
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

			System.out.printf("\t\t※ %s건 조회완료\n",num);
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m5()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 해당 교육생번호의 교육생 정보를 반환하는 메소드
	 * @param 교육생 번호
	 * @return 교육생 정보
	 */
	public StudentDTO getStudentSeq(String SeqRegCourse) {
		
		try {
			
			String sql = "select * from tblStudent where seqStudent = ? and id is not null";
			
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
	 * 해당 수강번호의 교육생 정보를 반환하는 메소드
	 * @param 수강번호
	 * @return 교육생 정보
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
	 * 해당 이름의 교육생 정보를 반환하는 메소드
	 * @param 이름
	 * @return 교육생 정보 리스트
	 */
	public ArrayList<StudentDTO> getStudentName(String name) {
		
		try {
			
			String sql = "select * from tblStudent where name like '%"+name+"%' and id is not null";
			
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
	 * 교육생을 등록하기 위한 메소드
	 */
	public static void addStudent() {
		
		ArrayList<StudentDTO> list = getStudent(""); //중복체크 하기위한 list
		
		System.out.println();
		String name="";
		while (true) {
			System.out.print("\t- 이름을 입력하세요.(1/6) : ");
			name = scanner.nextLine();
			if (name.equals("")) {
				System.out.println("\t\t※ 이름은 필수값입니다. 다시 입력하세요.\n");
			}else {
				break;
			}
		}
		String id="";
		while (true) {
			System.out.print("\t- 아이디를 입력하세요.(2/6) : ");
			id = scanner.nextLine();
			if (id.equals("")) {
				System.out.println("\t\t※ 아이디는 필수값입니다. 다시 입력하세요.\n");
			}else {
				boolean result=true;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId().equals(id)) {
						result=false;
						break;
					}
				}
				if (result) {
					break;
				}else {
					System.out.println("\t\t※ 중복된 아이디입니다. 다시 입력하세요.\n");
				}
			}
		}
		String ssn="";
		while (true) {
			System.out.print("\t- 주민등록번호를 입력하세요.(3/6) : ");
			ssn = scanner.nextLine();
			if (ssn.equals("")) {
				System.out.println("\t\t※ 주민등록번호는 필수값입니다. 다시 입력하세요.\n");
			}else {
				break;
			}
		}

		System.out.print("\t- 휴대폰번호를 입력하세요.(4/6) : ");
		String phone = scanner.nextLine();
		System.out.print("\t- 이메일을 입력하세요.(5/6) : ");
		String email = scanner.nextLine();
		System.out.print("\t- 희망취업분야를 입력하세요.(6/6) : ");
		String employmentField = scanner.nextLine();

		try {
			
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
			
			if (result==1) {
				System.out.println("\t\t※ 교육생 추가가 완료되었습니다.");
			}else {
				System.out.println("\t\t※ 교육생 추가실패");
			}
			System.out.println();
			
			return;
			
		} catch (Exception e) {
			System.out.println("test.main()");
			e.printStackTrace();
		}
		
	}

	/**
	 * 교육생 정보를 수정하기 위한 메소드
	 */
	public static void modStudent() {
		ArrayList<StudentDTO> list = studentList(""); //중복체크 하기위한 list
		StudentDAO dao = new StudentDAO();
		StudentDTO dto = new StudentDTO();
		
		AdministerStudent.printInfoList(list); //리스트를 출력하는 메소드
		
		System.out.println();
		System.out.print("\t█ 수정하실 학생번호를 입력하세요 : ");
		String seq = scanner.nextLine();
		
		dto = dao.get(seq);
		AdministerStudent.printInfo(dto); //정보를 출력하는 메소드
		
		System.out.println();
		System.out.println("\t\t※ 수정하지 않으실 항목은 빈 값으로 엔터를 입력하세요.");
		
		System.out.print("\t수정할 이름을 입력하세요 : ");
		String name = scanner.nextLine();
		
		if (name.equals("")) {
			name = dto.getName();
		}
		
		String id="";
		while(true) {
			System.out.print("\t수정할 아이디를 입력하세요 : ");
			id = scanner.nextLine();
			if (id.equals("")) {
				id = dto.getId();
				break;
			}else {
				boolean result=true;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId().equals(id)) {
						result=false;
						break;
					}
				}
				if (result) {
					break;
				}else {
					System.out.println("\t\t※ 중복된 아이디입니다. 다시 입력하세요.\n");
				}
			}
		}
		
		System.out.print("\t수정할 휴대폰을 입력하세요 : ");
		String phone = scanner.nextLine();
		
		if (phone.equals("")) {
			phone = dto.getPhone();
		}
		
		System.out.print("\t수정할 이메일을 입력하세요 : ");
		String email = scanner.nextLine();
		
		if (email.equals("")) {
			email = dto.getEmail();
		}
		
		System.out.print("\t수정할 가입일을 입력하세요 : ");
		String regDate = scanner.nextLine();
		
		if (regDate.equals("")) {
			regDate = dto.getFirstRegistrationDate();
		}
		
		System.out.print("\t수정할 취업희망분야를 입력하세요 : ");
		String empField = scanner.nextLine();
		
		if (empField.equals("")) {
			empField = dto.getEmploymentField();
		}
		
		StudentDTO dto2 = new StudentDTO();
		
		dto2.setSeqStudent(dto.getSeqStudent());
		dto2.setName(name);
		dto2.setId(id);
		dto2.setPhone(phone);
		dto2.setEmail(email);
		dto2.setFirstRegistrationDate(regDate);
		dto2.setEmploymentField(empField);
		
		System.out.println();
		System.out.println("\t\t※ 정보를 확인하세요.\n");
		
		System.out.printf("\t=====%s번 교육생=====",dto.getSeqStudent());
		System.out.println("\t<수정전>");
		System.out.printf(""
				+ "\t성명         : %s\n"
				+ "\t아이디       : %s\n"
				+ "\t휴대폰       : %s\n"
				+ "\t이메일       : %s\n"
				+ "\t가입일       : %s\n"
				+ "\t취업희망분야 : %s\n",
				dto.getName(),
				dto.getId(),
				dto.getPhone(),
				dto.getEmail(),
				dto.getFirstRegistrationDate(),
				dto.getEmploymentField()
		);
		System.out.println();
		System.out.println("\t\t\t▼");
		System.out.println("\t\t\t\t<수정후>");
		System.out.printf(""
				+ "\t성명         : %s\n"
				+ "\t아이디       : %s\n"
				+ "\t휴대폰       : %s\n"
				+ "\t이메일       : %s\n"
				+ "\t가입일       : %s\n"
				+ "\t취업희망분야 : %s\n",
				dto2.getName(),
				dto2.getId(),
				dto2.getPhone(),
				dto2.getEmail(),
				dto2.getFirstRegistrationDate(),
				dto2.getEmploymentField()
		);
		System.out.println();
		System.out.print("\t█ 수정하시겠습니까? (y/n) : ");
		String txt = scanner.nextLine();
		if (!txt.toUpperCase().equals("Y")) {
			System.out.println("\t취소되었습니다. 이전 메뉴로 돌아갑니다.");
			return;
		}
		
		String sql = "update tblStudent set name=?,id=?,phone=?,email=?,firstRegistrationDate=?,employmentField=? where seqStudent=?";	
		
		int result=0;
		try {
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getId());
			pstat.setString(3, dto2.getPhone());
			pstat.setString(4, dto2.getEmail());
			pstat.setString(5, dto2.getFirstRegistrationDate());
			pstat.setString(6, dto2.getEmploymentField());
			pstat.setString(7, dto2.getSeqStudent());
			
			result = pstat.executeUpdate();
			
			pstat.close();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.modStudent()");
			e.printStackTrace();
		}
		
		System.out.println();
		if (result > 0) {
			System.out.println("\t\t※ 정보 수정이 완료되었습니다.");
		} else {
			System.out.println("\t\t※ 정보 수정에 실패했습니다.");
		} 
		System.out.println();
		
		return;
		
	}

	/**
	 * 교육생 정보를 삭제하기 위한 메소드
	 * @param 교육생번호
	 * @return 결과값
	 */
	public int removeStudent(String seq) {

		try {

//			String sql = "delete from tblStudent where seqStudent = ?"; //delete로 삭제
			String sql = "update tblStudent set id='', ssn='',phone='',email='',firstRegistrationDate='',Employmentfield='' where seqStudent = ?"; //update로 삭제
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.removeStudent()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교육생 정보 리스트를 반환하는 메소드
	 * @param 교육생 아이디
	 * @return 교육생 정보 리스트
	 */
	public static ArrayList<StudentDTO> studentList(String word) {
		
		try {
			
			String where = "";
			if (word != null) {
				where = String.format("and id like '%%%s%%'", word);
			}
			
			String sql = String.format("select * from tblStudent where id is not null %s order by seqStudent desc", where);
			rs = stat.executeQuery(sql);
			return setDTOList(rs);

		} catch (Exception e) {
			System.out.println("StudentDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생 번호에 해당하는 교육생 정보를 반환하는 메소드
	 * @param 교육생 번호
	 * @return 교육생 정보
	 */
	public StudentDTO get(String seq) {
		
		try {
			
			String sql = "select * from tblStudent where seqStudent = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			return setDTO(rs);

		} catch (Exception e) {
			System.out.println("StudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생 정보 1개를 DTO클래스를 이용해 만들어진 객체에 저장하기 위한 메소드
	 * @param 교육생 정보(ResultSet)
	 * @return 교육생 정보(DTO)
	 */
	public StudentDTO setDTO(ResultSet rs) {
		try {
			if (rs.next()) {
				
				StudentDTO dto = new StudentDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
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
	
	/**
	 * 교육생 정보들을 DTO클래스를 이용해 만들어진 배열객체에 저장하기 위한 메소드
	 * @param 교육생 정보(ResultSet)
	 * @return 교육생 정보 리스트(ArrayList)
	 */
	public static ArrayList<StudentDTO> setDTOList(ResultSet rs){
		
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				
				dto.setSeqStudent(rs.getString("seqStudent"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setSsn(rs.getString("ssn"));
				dto.setPhone(rs.getString("phone")==null?"-":rs.getString("phone"));
				dto.setEmail(rs.getString("email")==null?"-":rs.getString("email"));
				dto.setFirstRegistrationDate(rs.getString("firstRegistrationDate")==null?"-":rs.getString("firstRegistrationDate"));
				dto.setEmploymentField(rs.getString("employmentField")==null?"-":rs.getString("employmentField"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("StudentDAO.setDTOList()");
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}




