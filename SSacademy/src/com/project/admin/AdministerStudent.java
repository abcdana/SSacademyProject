package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.StudentDAO;
import com.project.dto.AdminDTO;
import com.project.dto.StudentDTO;

public class AdministerStudent {
	
	static Scanner scanner = new Scanner(System.in);
	static AdminView view = new AdminView();
	static AdminDTO sadto = new AdminDTO();
	static StudentDAO dao = new StudentDAO();
	static StudentDTO dto = new StudentDTO();
	
	public static void AdministerStudent(AdminDTO adto){ //교육생 관리 메뉴
		sadto=adto;
		view.menu_AdministerStudent();
		
		while(true) {
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){
				st_search();
			}else if(data.equals("2")){
				st_add();
			}else if(data.equals("3")){
				st_mod();
			}else if(data.equals("4")){
				st_remove();
			}else if(data.equals("5")){
				AdminController AdCon = new AdminController(adto);
				AdCon.adminMain();
			}else {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
	}
	
	
	private static void st_search(){ //교육생 조회 메뉴
		
		view.menu_adminStd_search();
		
		while(true) {
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){ //아이디로 조회
				System.out.println();
				System.out.print("\t█ id를 입력하세요 : ");
				String txt = scanner.nextLine();
				ArrayList<StudentDTO> list = dao.getStudent(txt);
				
				printInfoList(list); //정보 리스트를 출력하는 메소드
				
				System.out.println();
				if (list.size()>1) {
					System.out.printf("\t\t※ %s건 조회완료\n",list.size());
				}else if(list.size()==0){
					System.out.println("\t\t※ 일치하는 결과가 없습니다.\n"
							+ "\t\t  이전 화면으로 이동합니다.");
					st_search();
				}
				
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
//				조회에서 수정/삭제기능 추가 = 나중에
				
			}else if(data.equals("2")){ //수강번호로 조회
				System.out.println();
				System.out.print("\t█ 수강번호를 입력하세요 : ");
				String txt = scanner.nextLine();
				dto = dao.getStudentRegSeq(txt);
				
				showStudent();
				
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
//				조회에서 수정/삭제기능 추가 = 나중에
				
			}else if(data.equals("3")){ //이름으로 조회
				
				System.out.println();
				System.out.print("\t█ 이름을 입력하세요 : ");
				String txt = scanner.nextLine();
				ArrayList<StudentDTO> list = dao.getStudentName(txt);
				
				printInfoList(list); //정보 리스트를 출력하는 메소드
				
				System.out.println();
				if (list.size()>1) {
					System.out.printf("\t\t※ %s건 조회완료\n",list.size());
				}else if(list.size()==0){
					System.out.println("\t\t※ 일치하는 결과가 없습니다.\n"
							+ "\t\t  이전 화면으로 이동합니다.");
					st_search();
				}
					
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
			
			}else if(data.equals("4")){ //전체 교육생 조회
				dao.getStudentAll(dto);
				System.out.printf("\t\t※ %s건 조회완료\n",dto.num);
				System.out.println();
				
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
				
			}else if(data.equals("5")){ //뒤로가기
				AdministerStudent(sadto);
			}else {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
	}
	
	
	private static void showStudent() { //교육생 검색
		
		if (dto!=null) {
			System.out.println();
			printInfo(dto); //정보를 출력하는 메소드
			System.out.println();
		}else {
			System.out.println();
			System.out.print("\t\t※ 일치하는 교육생이 없습니다.\n"
					+ "\t\t  이전 화면으로 이동합니다.");
			st_search();
		}
	}
	
	
	private static void st_add() { //교육생 추가
		StudentDAO.addStudent();
		AdministerStudent(sadto);
	}
	
	
	private static void st_mod() { //교육생 정보 수정
		ArrayList<StudentDTO> list = dao.studentList(null);
		printInfoList(list); //리스트를 출력하는 메소드
		
		System.out.println();
		System.out.print("\t█ 수정하실 학생번호를 입력하세요 : ");
		String seq = scanner.nextLine();
		
		StudentDTO dto = dao.get(seq);
		printInfo(dto); //정보를 출력하는 메소드
		
		System.out.println();
		System.out.println("\t\t※ 수정하지 않으실 항목은 빈값으로 엔터를 입력하세요");
		
		System.out.print("\t수정할 이름을 입력하세요 : ");
		String name = scanner.nextLine();
		
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t수정할 아이디를 입력하세요 : ");
		String id = scanner.nextLine();
		
		if (id.equals("")) {
			id = dto.getId();
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
				dto2.getName(),
				dto2.getId(),
				dto2.getPhone(),
				dto2.getEmail(),
				dto2.getFirstRegistrationDate(),
				dto2.getEmploymentField()
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
			AdministerStudent(sadto);
		}
		
		int result = dao.editInfo(dto2);
		
		System.out.println();
		if (result > 0) {
			System.out.println("\t\t※ 정보 수정이 완료되었습니다.");
		} else {
			System.out.println("\t\t※ 정보 수정에 실패했습니다.");
		} 
		System.out.println();
		
		AdministerStudent(sadto);
	}
	
	
	private static void st_remove() { //교육생 정보 삭제
		
		ArrayList<StudentDTO> list = dao.studentList(null);
		printInfoList(list); //리스트를 출력하는 메소드
		
		System.out.println();
		
		System.out.print("\t█ 삭제하실 학생번호를 입력하세요 : ");
		String seq = scanner.nextLine();
		
		StudentDTO dto = dao.getStudentSeq(seq);
		
		printInfo(dto); //정보를 출력하는 메소드
		
		System.out.println();
		System.out.print("\t█ 삭제하시겠습니까? (y/n) : "); 
		String txt = scanner.nextLine();
		if (!txt.toUpperCase().equals("Y")) {
			System.out.println("\t취소되었습니다. 이전 메뉴로 돌아갑니다.");
			AdministerStudent(sadto);
		}
		
		int result = dao.removeStudent(seq);
		
		if (result > 0) {
			System.out.println("\t\t※ 정보 삭제가 완료되었습니다.");
		} else {
			System.out.println("\t\t※ 정보 삭제에 실패했습니다.");
		}
		
		AdministerStudent(sadto);
	}
	
	private static void printInfo(StudentDTO dto) {
		System.out.printf(""
				+ "\t=====%s번 교육생=====\n"
				+ "\t성명         : %s\n"
				+ "\t아이디       : %s\n"
				+ "\t휴대폰       : %s\n"
				+ "\t이메일       : %s\n"
				+ "\t가입일       : %s\n"
				+ "\t취업희망분야 : %s\n",
				dto.getSeqStudent(),
				dto.getName(),
				dto.getId(),
				dto.getPhone(),
				dto.getEmail(),
				dto.getFirstRegistrationDate(),
				dto.getEmploymentField()
		);
	}
	
	private static void printInfoList(ArrayList<StudentDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println();
			System.out.printf(""
					+ "\t=====%s번 교육생=====\n"
					+ "\t이름         : %s\n"
					+ "\t아이디       : %s\n"
					+ "\t휴대폰       : %s\n"
					+ "\t이메일       : %s\n"
					+ "\t가입일       : %s\n"
					+ "\t취업희망분야 : %s\n",
					list.get(i).getSeqStudent(),
					list.get(i).getName(),
					list.get(i).getId(),
					list.get(i).getPhone(),
					list.get(i).getEmail(),
					list.get(i).getFirstRegistrationDate(),
					list.get(i).getEmploymentField()
			);
		}
	}
	
}










