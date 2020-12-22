package com.project.admin;
/**
 * 관리자 뷰 입니다.
 * @author 김다은
 *
 */
public class AdminView {
	
	/**
	 * 관리자의 메인메뉴를 출력하는 메소드이다.
	 */
	public void menu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t관리자 메인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 기초 정보 관리\t7. 성적 관리\t\t\t  │");
		System.out.println("\t│\t\t2. 교사 계정 관리\t8. 출결 관리\t\t\t  │");
		System.out.println("\t│\t\t3. 개설 과정 관리\t9. 취업 공고 관리\t\t  │");
		System.out.println("\t│\t\t4. 개설 과목 관리\t10. 취업 현황 관리\t\t  │");
		System.out.println("\t│\t\t5. 교육생 관리\t\t11. 평가 관리\t\t\t  │");
		System.out.println("\t│\t\t6. 시험 관리\t\t12. 우수훈련생 관리\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	public void menu_AdministerStudent() {
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교육생 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 교육생 조회\t\t2. 교육생 등록\t\t\t  │");
		System.out.println("\t│\t\t3. 교육생 수정\t\t4. 교육생 삭제\t\t\t  │");
		System.out.println("\t│\t\t5. 뒤로 가기\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
	}

	public void menu_adminStd_search() {
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교육생 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 수강번호로 조회\t2. 아이디로 조회\t\t  │");
		System.out.println("\t│\t\t3. 이름으로 조회\t4. 전체 교육생 조회\t\t  │");
		System.out.println("\t│\t\t5. 뒤로 가기\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
	}
	
}
