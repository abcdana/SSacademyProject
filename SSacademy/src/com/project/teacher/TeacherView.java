package com.project.teacher;
/**
 * 교사 뷰 입니다.
 * @author 김다은
 *
 */
public class TeacherView {
	
	/**
	 * 교사의 메인메뉴를 출력하는 메소드이다.
	 */
	public void menu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사  메인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 강의스케줄 조회\t4. 성적 관리\t\t\t  │");
		System.out.println("\t│\t\t2. 출결 관리\t\t5. 평가 조회\t\t\t  │");
		System.out.println("\t│\t\t3. 시험 관리\t\t6. 우수훈련생 조회\t\t  │");
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}

}
