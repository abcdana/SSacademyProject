package com.project.teacher;

import java.util.ArrayList;


import com.project.dao.TeacherDAO;
import com.project.teacher.dto.TeacherScheduleDTO;
import com.project.dto.AllOpenCourseDTO;
import com.project.teacher.dto.TeacherCourseListDTO;


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
		System.out.println("\t┃\t\t\t\t교사 메인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 강의스케줄 조회\t4. 성적 관리\t\t\t  │");
		System.out.println("\t│\t\t2. 출결 관리\t\t5. 평가 조회\t\t\t  │");
		System.out.println("\t│\t\t3. 배점 관리\t\t6. 우수훈련생 조회\t\t  │");
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	public void TestManagementMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t배점 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 종료 과목 조회\t4. 필기 시험 날짜 입력\t\t  │");
		System.out.println("\t│\t\t2. 과목 배점 관리\t5. 실기 시험 날짜 입력\t\t  │");
		System.out.println("\t│\t\t3. 시험 문제 추가\t6. 시험 문제 조회\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	public void TestScoreManagementMenu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t성적 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 과목 조회\t        3. 학생별 성적 관리\t\t  │");
		System.out.println("\t│\t\t2. 과목별 성적 관리\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	
	/**
	 * 교사강의스케줄 조회 메인입니다.
	 */
	public void scheduleMain() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t강의스케줄 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t\t1. 교사명으로 조회\t\t\t\t  │");
		System.out.println("\t│\t\t\t\t2. 교사번호로 조회\t\t\t\t  │");
		System.out.println("\t│\t\t\t\t0. 뒤로가기\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	

	/**
	 * 이름으로 강의스케줄 조회 헤더입니다.
	 */
	public void scheduleTeacherName() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사명으로 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	
		
	}
	
	/**
	 * 이름으로 강의스케줄 조회 바텀입니다.
	 */
	public void scheduleTeacherName2() {
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ * 교사명으로 조회를 원하시면 교사명을 입력해주세요.\t\t\t\t  │");
		System.out.println("\t│ * 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 교사명 : ");
		
	}
	
	
	/**
	 * 교사명으로 조회 시 강의스케줄 정보 헤더입니다.
	 */
	public void scheduleTeacherName3(String teacherName) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t검색하신 교사명 '%s'의 강의스케줄입니다.\t\t  ┃\n", teacherName);
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│%-8s%-10s%20s%33s%15s%13s │\n", "[교사번호]", "[교사명]", "[과정명]", "[시작일]", "[종료일]", "[등록인원]");
		System.out.println("\t└──────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
		
	}	
	
	
	/**
	 * 교사번호로 강의스케줄 조회 헤더입니다.
	 */
	public void scheduleSeqTeacher() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사번호로 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		
	}
	
	public void scheduleSeqTeacher3() {
	
		System.out.println("\t┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│%-8s%-10s%20s%33s%15s%13s │\n", "[교사번호]", "[교사명]", "[과정명]", "[시작일]", "[종료일]", "[등록인원]");
		System.out.println("\t└──────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	/**
	 * 교사번호로 강의스케줄 조회 바텀입니다.
	 */
	public void scheduleSeqTeacher2() {
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ * 교사번호로 조회를 원하시면 교사번호를 입력해주세요.\t\t\t\t  │");
		System.out.println("\t│ * 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 교사번호 : ");
	}
	
	/**
	 * 교사번호로 조회 시 강의스케줄 정보 헤더입니다.
	 */
	public void scheduleSeqView() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t검색하신 교사번호의 강의스케줄입니다.\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│%-8s%-10s%20s%33s%15s%13s │\n", "[교사번호]", "[교사명]", "[과정명]", "[시작일]", "[종료일]", "[등록인원]");
		System.out.println("\t└──────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
		
	}	
			
		
	public void scheduleView2() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ * 뒤로가기를 원하시면 아무키나 입력해주세요.\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 입력 : ");
		
	}
	
		
	
	/**
	 * 강의스케줄조회 번호입력
	 * @author 박지현
	 */
	public void pageInfo() {
		
		System.out.println();
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t1. 상세조회\t2. 이전페이지\t3. 다음페이지\t0. 뒤로가기\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 입력 : ");
		
	}

	
	
	/////////////////////////////////다은///////////////////////////////////////////
	
	/**
	 * 교사가 강의한 과정들의 정보를 출력하는 메서드이다.
	 * @param result
	 */
	public void allCourseList(ArrayList<TeacherCourseListDTO> result) {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t\t[과정이름]\t\t\t       [과정시작일] [과정종료일]   [강의실]  [개강상태]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		
		for (TeacherCourseListDTO dto : result) {
			
			System.out.printf("\t%3s\t%-35s\t%-10s   %-10s\t   %-10s%-10s\n"
					, dto.getSeqOpenCourse()
					, dto.getName()
					, dto.getStartDate()
					, dto.getEndDate()
					, dto.getRoomName()
					, dto.getCourseRegState());
		}
		
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		
	}
	
	
	/**
	 * 출결조회 메뉴를 출력하는 메서드이다.
	 */
	public void attendanceMenu() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 기간별 조회\t\t\t0. 뒤로 가기\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	
}
