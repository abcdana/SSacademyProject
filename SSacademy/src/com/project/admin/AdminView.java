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
	
	
	/**
	 * 관리자기능 중 기초정보관리 메뉴를 출력하는 메소드이다.
	 */
	public void basicInfoMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t기초 정보 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 과정 정보 관리\t3. 강의실 정보 관리\t\t  │");
		System.out.println("\t│\t\t2. 과목 정보 관리\t4. 교재 정보 관리\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}

	
	/**
	 * 기초 과정정보관리 메뉴를 출력하는 메소드이다.
	 */
	public void courseInfoMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과정 정보 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 과정 정보 조회\t3. 과정 정보 수정\t\t  │");
		System.out.println("\t│\t\t2. 과정 정보 추가\t4. 과정 정보 삭제\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	/**
	 * 과정 조회 헤더 출력 메소드이다. 
	 */
	public void courseListHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     과정정보 목록 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	
	}
	
	/**
	 * 과정 조회 컬럼명 출력 메소드이다. 
	 */
	public void courseListHeader2() {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t\t    [과정이름]\t\t\t\t [과정기간]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}
	
	/**
	 * 과정 추가 헤더 출력 메소드이다.
	 */
	public void addCourseHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t       새로운 과정 추가\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   새로운 과정을 추가하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	
	/**
	 * 신규 내용 등록 또는 취소를 고르는 메뉴 출력 메소드이다.
	 */
	public void chooseAddOrNot() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 신규 내용 등록\t0. 뒤로 가기\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	
	
	/**
	 * 관리자 뷰의 등록 결과를 출력하는 메소드이다.
	 * 0일 시 등록실패, 1일 시 등록 성공 문구를 출력한다.
	 * @param result 0 또는 1이 저장되어 있는 변수
	 */
	public void addResult(int result) {
		
		System.out.println();
		
		if (result == 1) {
			System.out.println("\t\t** 등록에 성공했습니다. **");
		} else {
			System.out.println("\t\t** 등록에 실패했습니다. **");
		}
	}
	
	
	
	/**
	 * 과정 추가 헤더 출력 메소드이다.
	 */
	public void updateCourseHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t       과정 정보 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   기존 과정을 수정하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	
	/**
	 * 수정 내용 등록 또는 취소를 고르는 메뉴 출력 메소드이다.
	 */
	public void chooseUpdateOrNot() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 수정 내용 등록\t0. 뒤로 가기\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	
	/**
	 * 관리자 뷰의 수정 결과를 출력하는 메소드이다.
	 * 0일 시 수정 실패, 1일 시 수정 성공 문구를 출력한다.
	 * @param result 0 또는 1이 저장되어 있는 변수
	 */
	public void updateResult(int result) {
		
		System.out.println();
		
		if (result == 1) {
			System.out.println("\t\t** 수정에 성공했습니다. **");
		} else {
			System.out.println("\t\t** 수정에 실패했습니다. **");
		}
	}
	
	
	/**
	 * 과정 삭제 헤더 출력 메소드이다.
	 */
	public void deleteCourseHeader() {
	
	System.out.println("\n");
	System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
	System.out.println("\t┃\t\t\t       기존 과정 삭제\t\t\t\t  ┃");
	System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	System.out.println();
	System.out.println("\t\t   기존 과정을 삭제하시려면 아래 항목을 작성해주세요.");
	System.out.println();
	
	}

	/**
	 * 삭제 내용 등록 또는 취소를 고르는 메뉴 출력 메소드이다.
	 */
	public void chooseDeleteOrNot() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 삭제 하기\t0. 뒤로 가기\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	
	
	/**
	 * 관리자 뷰의 삭제 결과를 출력하는 메소드이다.
	 * 0일 시 수정 실패, 1일 시 수정 성공 문구를 출력한다.
	 * @param result 0 또는 1이 저장되어 있는 변수
	 */
	public void deleteResult(int result) {
		
		System.out.println();
		
		if (result == 1) {
			System.out.println("\t\t** 삭제에 성공했습니다. **");
		} else {
			System.out.println("\t\t** 삭제에 실패했습니다. **");
		}
		
	}
	
	
	
}
