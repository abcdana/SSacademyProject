package com.project.teacher;

import java.util.ArrayList;

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
		System.out.println("\t│\t\t1. 종료 과목 조회\t4. 출결 배점 입력\t\t  │");
		System.out.println("\t│\t\t2. 과목별 배점 관리\t5. 필기 배점 입력\t\t  │");
		System.out.println("\t│\t\t3. 교육생별 배점 관리\t6. 실기 배점 입력\t\t  │");
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
		System.out.println("\t│\t\t1. 과목 조회\t3. 학생별 성적 관리\t\t\t  │");
		System.out.println("\t│\t\t2. 과목별 성적 관리\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	

	
	
	/////////////////////////////////다은///////////////////////////////////////////
	
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
