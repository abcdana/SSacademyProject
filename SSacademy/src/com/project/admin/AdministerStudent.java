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
	
	/**
	 * 교육생을 관리할 수 있는 메뉴 메소드
	 * @param adto
	 */
	public static void AdministerStudent(AdminDTO adto){
		sadto=adto;
		view.menu_adminStd();
		
		while(true) {
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){ //교육생 조회 메뉴
				st_search();
			}else if(data.equals("2")){ //교육생 추가
				st_add();
			}else if(data.equals("3")){ //교육생 정보 수정
				st_mod();
			}else if(data.equals("4")){ //교육생 정보 삭제
				st_remove();
			}else if(data.equals("0")){ //뒤로가기
				AdminController AdCon = new AdminController(adto);
				AdCon.adminMain();
			}else {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
	}
	
	/**
	 * 교육생을 조회하는 방법의 메뉴를 출력하는 메소드
	 */
	private static void st_search(){
		
		view.menu_adminStd_search();
		
		while(true) {
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){ //아이디로 조회
				System.out.println();
				System.out.print("\t█ id를 입력하세요 : ");
				String txt = scanner.nextLine();
				ArrayList<StudentDTO> list = dao.getStudent(txt); //정보 리스트를 저장하는 메소드
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
				
			}else if(data.equals("2")){ //교육생번호로 조회
				System.out.println();
				System.out.print("\t█ 교육생번호를 입력하세요 : ");
				String txt = scanner.nextLine();
				dto = dao.getStudentSeq(txt);
				
				showStudent();
				
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
				
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
				
			}else if(data.equals("4")){ //수강번호로 조회
				System.out.println();
				System.out.print("\t█ 수강번호를 입력하세요 : ");
				String txt = scanner.nextLine();
				dto = dao.getStudentRegSeq(txt);
				
				showStudent();
				
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
			
			}else if(data.equals("5")){ //전체 교육생 조회
				dao.getStudentAll();
				
				System.out.println();
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(sadto);
				
			}else if(data.equals("0")){ //뒤로가기
				AdministerStudent(sadto);
			}else {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
	}
	
	/**
	 * 교육생을 검색하는 메소드
	 */
	private static void showStudent() {
		
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
	
	/**
	 * 교육생을 추가하는 메소드
	 */
	private static void st_add() {
		StudentDAO.addStudent();
		AdministerStudent(sadto);
	}
	
	/**
	 * 교육생 정보를 수정하는 메소드
	 */
	private static void st_mod() {
		StudentDAO.modStudent();
		AdministerStudent(sadto);
	}
	 
	/**
	 * 교육생 정보를 삭제하는 메소드
	 */
	private static void st_remove() {
		
		ArrayList<StudentDTO> list = dao.studentList(null);
		printInfoList(list); //리스트를 출력하는 메소드
		
		System.out.println();
		
		System.out.print("\t█ 삭제하실 학생번호를 입력하세요 : ");
		String seq = scanner.nextLine();
		
		StudentDTO dto = dao.getStudentSeq(seq);
		if (dto==null) {
			System.out.print("\t\t※ 일치하는 교육생이 없습니다.\n"
					+ "\t\t  이전 화면으로 이동합니다.");
			AdministerStudent(sadto);
		}
		
		printInfo(dto); //정보를 출력하는 메소드
		
		System.out.println();
		System.out.print("\t█ 삭제하시겠습니까? (y/n) : "); 
		String txt = scanner.nextLine();
		if (!txt.toUpperCase().equals("Y")) {
			System.out.println("\t취소되었습니다. 이전 메뉴로 돌아갑니다.");
			AdministerStudent(sadto);
		}
		
		int result = dao.removeStudent(seq); //정보를 삭제하는 메소드
		
		if (result > 0) {
			System.out.println("\t\t※ 정보 삭제가 완료되었습니다.");
		} else {
			System.out.println("\t\t※ 정보 삭제에 실패했습니다.");
		}
		
		AdministerStudent(sadto);
	}
	
	/**
	 * 정보를 출력하는 메소드
	 * @param dto
	 */
	public static void printInfo(StudentDTO dto) {
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
	
	/**
	 * 리스트의 정보를 출력하는 메소드
	 * @param list
	 */
	public static void printInfoList(ArrayList<StudentDTO> list) {
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










