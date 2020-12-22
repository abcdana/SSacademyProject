package com.project.admin;

import java.util.ArrayList;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenCourseStudentDTO;
import com.project.admin.dto.OpenSubjectListDTO;

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
		System.out.println("\t│\t\t3. 개설 과정 관리\t9. 채용 공고 관리\t\t  │");
		System.out.println("\t│\t\t4. 개설 과목 관리\t10. 취업 현황 관리\t\t  │");
		System.out.println("\t│\t\t5. 교육생 관리\t\t11. 평가 관리\t\t\t  │");
		System.out.println("\t│\t\t6. 시험 관리\t\t12. 우수훈련생 관리\t\t  │");
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	

	public void start() {
		
		System.out.println("\t┌───────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 개설과정조회\t\t\t│");
		System.out.println("\t│\t\t2. 개설과정등록\t\t\t│");
		System.out.println("\t│\t\t3. 개설과정수정\t\t\t│");
		System.out.println("\t│\t\t4. 개설과정삭제\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────┘");
		System.out.print("\t█ 입력 : ");
	
	} 
	
	/**
	 * 개설과정조회 뷰
	 */
	public void OpenCourseView(ArrayList<OpenCourseListDTO> list) {
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설과정조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│%-5s\t%-60s%-14s%-14s%8s%10s%10s\t│\n", "[번호]", "[과정명]", "[시작일]", "[종료일]", "[강의실]", "[등록인원]", "[수료여부]");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
		for (OpenCourseListDTO dto : list) {
			int nameLength = checkTitle(dto.getName(), 60);
			
			System.out.printf("\t%-8s" 
						+ "%-" + nameLength + "s"
						+ "\t%15s\t%15s\t\t%s\t%10s\t%-10s\n"
			
							, dto.getSeqOpenCourse()
							, dto.getName()
							, dto.getStartDate()
							, dto.getEndDate()
							, dto.getRoom()
							, dto.getMemberCount()
							, dto.getState());
		
		}
		
		System.out.println("\t──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ 특정과정조회를 원하시면 과정번호를 입력해주세요.\t\t\t\t\t│");
		System.out.println("\t│ 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 과정 번호: ");
		
	}
	
	
	
	/**
	 * 특정과정조회 뷰
	 * @return 
	 */
	public String OpenSpecificCourseView(ArrayList<OpenCourseListDTO> list, String num) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     특정개설과정조회\t\t\t\t┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		for (OpenCourseListDTO dto : list) {
			
			if(num.equals(dto.getSeqOpenCourse())) {
				
				System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
				System.out.printf("\t│ 번호 : %s\t\t\t\t\t\t\t\t│\n", dto.getSeqOpenCourse());
				System.out.printf("\t│ 과정명 : %s\t\t\t│\n", dto.getName());
				System.out.printf("\t│ 시작일 : %s\t\t\t\t\t\t\t│\n", dto.getStartDate());
				System.out.printf("\t│ 종료일 : %s\t\t\t\t\t\t\t│\n", dto.getEndDate());
				System.out.printf("\t│ 강의실 : %s\t\t\t\t\t\t\t│\n", dto.getRoom());
				System.out.printf("\t│ 등록인원 : %s\t\t\t\t\t\t\t\t│\n", dto.getMemberCount() + "명");
				System.out.printf("\t│ 수료여부 : %s\t\t\t\t\t\t\t│\n", dto.getState());
				System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
				
				System.out.println();
				System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
				System.out.println("\t│ \t\t과목조회: 1\t교육생조회: 2\t뒤로가기: 0\t\t│");
				System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
				System.out.print("\t█ 입력: ");
			
				return dto.getName();
			}			
		}
		
		return null;
		
	}
	
	/**
	 * 1. 특정개설과정의 과목조회 뷰
	 */
	public void SpecificSubjectView(ArrayList<OpenSubjectListDTO> list, String openCourseName) {
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃    \"%s\"의 과목리스트입니다.\t┃\n", openCourseName);
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		//과목 앞에 붙일 변수
			int i = 1;
					
		for(OpenSubjectListDTO dto : list) {
					
			System.out.println("\t───────────────────────────────────────────────────────────────────────");
			System.out.printf("\t 과목명%d : %s\n", i, dto.getSubjectName());
			System.out.printf("\t 시작일 : %s\n", dto.getStartDate());
			System.out.printf("\t 종료일 : %s\n", dto.getEndDate());
			System.out.printf("\t 교사명 : %s\n", dto.getTeacherName());
			System.out.printf("\t 교재명 : %s\n", dto.getBookName());
			System.out.printf("\t 진행여부 : %s\n", dto.getState());
			System.out.println("\t───────────────────────────────────────────────────────────────────────");
					
			i++;
					
		}//for
		
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 입력: ");
	}
	
	/**
	 * 2. 특정개설과정의 교육생조회 뷰
	 */
	public void SpecificStudentView(ArrayList<OpenCourseStudentDTO> list, String openCourseName) {
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃    \"%s\"의 교육생리스트입니다.\t┃\n", openCourseName);
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│ %-5s%-13s%-18s%-17s%-15s%-10s│\n", "[번호]", "[이름]", "[주민번호]", "[전화번호]", "[등록일]", "[교육생상태]");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────────────────────┘");
		
		for(OpenCourseStudentDTO dto : list) {
				
			System.out.printf("\t  %-7s%-12s%-20s%-19s%-17s%-20s\n", dto.getSeqStudent()
								,dto.getName()
								,dto.getSsn()
								,dto.getTel()
								,dto.getRegistDate()
								,dto.getState());
					
		}//for
		
		System.out.println("\t──────────────────────────────────────────────────────────────────────────────────────────────────");
	
		System.out.println();
		System.out.println("\t 뒤로가기를 원하시면 0을 입력해주세요.");
		System.out.println("\t──────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("\t█ 입력: ");
		
	}
	
	
	public void openCourseAddView() {
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t\t개설과정등록\t\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
	//공통 등록 체크 메서드
	public void AddCheck() {
		
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ 1. 등록하기\t 0. 뒤로가기\t\t\t\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 입력: ");
	}
	
	
	
	
	private int checkTitle(String str, int length) {

		int result = length;
		for (int i = 0; i < str.length(); i++) {
			char c1 = str.charAt(i);
			if (c1 >= '가' && c1 <= '힣') {
				result--;
			}
		}
		
		return result;

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
		System.out.println("\t│\t\t1. 항목 삭제 하기\t0. 뒤로 가기\t\t\t  │");
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
	
	
	/**
	 * 기초 과목정보관리 메뉴를 출력하는 메소드이다.
	 */
	public void subjectInfoMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과목 정보 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 과목 정보 조회\t3. 과목 정보 수정\t\t  │");
		System.out.println("\t│\t\t2. 과목 정보 추가\t4. 과목 정보 삭제\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	
	
	
	
	/**
	 * 기초 강의실정보관리 메뉴를 출력하는 메소드이다.
	 */
	public void roomInfoMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t    강의실 정보 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 강의실 정보 조회\t3. 강의실 정보 수정\t\t  │");
		System.out.println("\t│\t\t2. 강의실 정보 추가\t4. 강의실 정보 삭제\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	
	
	/**
	 * 강의실 추가 헤더 출력 메소드이다.
	 */
	public void addSubjectHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     새로운 강의실 추가\t\t\t          ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   새로운 강의실을 추가하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	
	
	/**
	 * 기초 교재정보관리 메뉴를 출력하는 메소드이다.
	 */
	public void bookInfoMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교재 정보 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 교재 정보 조회\t3. 교재 정보 수정\t\t  │");
		System.out.println("\t│\t\t2. 교재 정보 추가\t4. 교재 정보 삭제\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	
	
}
	

