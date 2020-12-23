package com.project.admin;

import java.util.Scanner;

import com.project.dto.AdminDTO;

/**
 * 관리자 컨트롤러입니다.
 * --세부사항 적기
 * @author 김다은
 *
 */
public class AdminController {
	
	private Scanner scan;
	private AdminView view;
	private AdminDTO adto;

	/**

	 * 기본 생성자에서 컨트롤에 이용될 DTO들을 생성해준다. 

	 */
	public AdminController(AdminDTO dto) {
		
		scan = new Scanner(System.in);
		view = new AdminView();
		this.adto = dto;
	}
	
	
	/**
	 * 관리자 메인입니다.
	 */
	public void adminMain() {
		
		System.out.printf("\n\t\t관리자 %s님 SSacademy 접속을 환영합니다.", adto.getId());
		System.out.print("\n\t\t――――――――――――――――――――――――――――――――――――――――――――――");
		
		boolean check = true;
		while (check) {
			
			view.menu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//System.out.println("1.	기초 정보 관리 - 김다은");
				BasicInfoManage manage = new BasicInfoManage();
				manage.basicInfoMain();
			} else if (sel.equals("2")) {
				//System.out.println("2.	교사 계정 관리 - 김주혁"); - 완료
				TeacherAccountManagement teacherAccount = new TeacherAccountManagement(); //교사 계정 관리 생성
				teacherAccount.main(); //교사 계정 관리 메인
				//break;
			} else if (sel.equals("3")) {
				System.out.println("3.	개설 과정 관리 -박지현");
				//break;
			} else if (sel.equals("4")) {
				System.out.println("4.	개설 과목 관리 -박지현");
				//break;
			} else if (sel.equals("5")) {
//				System.out.println("5.	교육생 관리 - 임채원");
				AdministerStudent.AdministerStudent(adto);
				//break;
			} else if (sel.equals("6")) {
				System.out.println("6.	시험 관리 - 조성진");
				//break;
			} else if (sel.equals("7")) {
				System.out.println("7.	성적 관리 - 조성진");
				//break;
			} else if (sel.equals("8")) {
				System.out.println("8.	출결 관리 - 김다은");
				//break;
			} else if (sel.equals("9")) {
				System.out.println("9.	연계 기업 취업공고 관리 -조혜승");
				//break;
			} else if (sel.equals("10")) {
				System.out.println("10.	 수료자 취업 현황 관리 - 조혜승");
				//break;
			} else if (sel.equals("11")) {
				//System.out.println("11.  강의 및 시설 평가 조회 - 김주혁"); - 완료
				EvaluationManagement evaluation = new EvaluationManagement();
				evaluation.main();
				//break;
			} else if (sel.equals("12")) {
				System.out.println("12.  우수 훈련생 관리 - 임채원");
				//break;
			} else if (sel.equals("0")) {
				//로그아웃
				System.out.println("\n\t\t**로그 아웃 되었습니다.**");
				check = false;
			} else {
				//check = false;
				System.out.println("\n\t\t※ 잘못된 선택입니다.");
				System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");
				System.out.println();
			}
			
			
				
		}
		
	}
	
}
