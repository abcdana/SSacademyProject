package com.project.teacher;

import java.util.Scanner;


public class TeacherController {

	private Scanner scan;
	private TeacherView view;
	
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다. 
	 */
	public TeacherController() {
		
		scan = new Scanner(System.in);
		view = new TeacherView();
		
	}
	
	/**
	 * 교사 메인입니다.
	 */
	public void teacherMain() {
		
		boolean check = true;
		while (check) {
			
			view.menu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				System.out.println("- 강의 스케줄을 조회할 수 있다. - 박지현");
				break;
			} else if (sel.equals("2")) {
				System.out.println("- 출결 관리 및 출결 조회를 할 수 있다. - 김다은");
				break;
			} else if (sel.equals("3")) {
				System.out.println("- 배점 및 시험을 관리할 수 있다. - 조성진");
				break;
			} else if (sel.equals("4")) {
				System.out.println("- 성적 정보를 관리할 수 있다. - 조성진");
				break;
			} else if (sel.equals("5")) {
				System.out.println("- 교사평가를 조회할 수 있다. - 김주혁");
				break;
			} else if (sel.equals("6")) {
				System.out.println("- 우수훈련생 여부를 확인할 수 있다. --임채원");
				break;
			} else if (sel.equals("7")) {
				System.out.println("7.	성적 관리 - 조성진");
				break;
			} else {
				check = false;
				System.out.println("\n\t\t※ 잘못된 선택입니다.");
				System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");
				System.out.println();
				teacherMain();
			}

		}
		
	}
	
}
