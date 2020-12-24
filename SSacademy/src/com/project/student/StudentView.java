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
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}

	/**
	 * 교육생 연계기업 채용공고 조회 메뉴
	 * @author 혜승
	 */
	public void menuCompanyList() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 전체 채용공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 업무별 채용공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t3. 소재지별 채용공고 조회\t\t\t\t  │");
		System.out.println("\t│\t\t4. 연봉별 채용공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 채용상태별 공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t6. 이전 화면으로 \t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	/**
	 * 교육생 수료생 취업정보 조회 메뉴
	 * @author 혜승
	 */
	public void menuGetJobInfoCheck() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 전체 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 업무별 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t3. 소재지별 취업정보 조회\t\t\t\t  │");
		System.out.println("\t│\t\t4. 연봉별 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 연도별 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t6. 연계기업 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t7. 이전 화면으로 \t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	

}
