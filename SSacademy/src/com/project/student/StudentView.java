package com.project.student;
/**
 * 교육생 뷰 입니다.
 * @author 김다은
 *
 */
public class StudentView {
	
	/**
	 * 교육생의 메인메뉴를 출력하는 메소드이다.
	 */
	public void menu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교육생 메인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. My Page\t\t5. 취업공고 조회\t\t  │");
		System.out.println("\t│\t\t2. 입퇴실 체크\t\t6. 취업현황 조회\t\t  │");
		System.out.println("\t│\t\t3. 성적 조회\t\t7. 우수훈련생 확인\t\t  │");
		System.out.println("\t│\t\t4. 강의 평가\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.println("\t█ 로그아웃 하시려면 엔터를 입력하세요.");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}

}
