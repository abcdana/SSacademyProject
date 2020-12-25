package com.project.student;

import java.util.ArrayList;

import com.project.dto.PeriodAttendListDTO;
import com.project.student.dto.StudentCourseListDTO;
import com.project.teacher.dto.TeacherCourseListDTO;

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
		System.out.println("\t│\t\t2. 출결 확인\t\t6. 취업현황 조회\t\t  │");
		System.out.println("\t│\t\t3. 성적 조회\t\t7. 우수훈련생 확인\t\t  │");
		System.out.println("\t│\t\t4. 강의 평가\t\t\t\t\t\t  │");
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}


	
	
	
	///////////////////////////////다은///////////////////////////////////////
	
	/**
	 * 교육생모드의 출결관리 메뉴를 출력하는 메서드이다.
	 */
	public void attendanceMenu() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t1. 입퇴실 체크\t\t2. 출결 조회\t\t0. 뒤로 가기\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	
	}

	/**
	 * 입퇴실 체크 헤더를 출력하는 메서드이다.
	 */
	public void checkAttend() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 입퇴실 체크\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		System.out.print("\t█ 입/퇴실 체크를 하시겠습니까? (Y/N) : ");
	}
	
	
	/**
	 * 교육생 뷰의 출석체크의 결과를 출력하는 메소드이다.
	 * 출석체크를 성공시 "출석체크 완료"메세지를 , 실패시 "출석체크 실패" 메세지를 출력한다.
	 * @param result 0 또는 1이 저장되어 있는 변수이다.
	 */
	public void checkResult(int result) {
		
		if(result == 0) {
			System.out.println();
			System.out.println("\t\t* 출석체크 실패. *");
		} else if(result == 1) {
			System.out.println();
			System.out.println("\t\t* 출석체크 완료. *");
		}
		
	}
	
	/**
	 * 출결 조회 헤더를 출력하는 메서드이다.
	 */
	public void attendanceList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 출결 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
	}
	
	
	/**
	 * 교육생 모드의 출결관리 중 출결 조회 메뉴를 출력하는 메서드이다.
	 */
	public void attendPeriodMenu() {

		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t1. 전체 조회\t\t2. 월별 조회\t\t0. 뒤로 가기\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	/**
	 * 출결 전체 조회 헤더를 출력하는 메서드이다.
	 */
	public void totAttList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 전체 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
	/**
	 * 출결 월별 조회 헤더를 출력하는 메서드이다.
	 */
	public void monthAttList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 월별 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}



	/**
	 * 교육생이 수강하는 과정들의 정보를 출력하는 메서드이다.
	 * @param result
	 */
	public void allCourseList(ArrayList<StudentCourseListDTO> result) {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 수강 과정 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[수강번호] [과정번호]\t\t\t   [과정명]   \t\t\t[과정시작일] [과정종료일]   [강의실]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		
		for (StudentCourseListDTO dto : result) {
			
			System.out.printf("\t%6s\t  %6s\t%-25s   %-10s   %-10s   %-10s\n"
					, dto.getSeqRegCourse()
					, dto.getSeqOpenCourse()
					, dto.getName()
					, dto.getStartDate()
					, dto.getEndDate()
					, dto.getRoomName());
		}
		
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────────────");

	}



	public void attendanceList(ArrayList<PeriodAttendListDTO> list) {
		
		
		
	}
	
	
	
	
///////////////////////////////혜승///////////////////////////////////////
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
