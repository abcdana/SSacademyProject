package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenCourseStudentDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dto.AllOpenCourseDTO;
import com.project.dto.AttendanceDTO;
import com.project.dto.OpenCourseDTO;
import com.project.dto.PeriodAttendListDTO;
import com.project.dto.ViewStudentDTO;

/**
 * 관리자 뷰 입니다.
 * @author 김다은
 *
 */
public class AdminView {
	
	
	/**
	 * 메인 SIST academy 로고 출력 메서드
	 */
	public static void showMainLogo() {
		
		System.out.println("\n" + 
				" ███████╗██╗███████╗████████╗     █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\n" + 
				" ██╔════╝██║██╔════╝╚══██╔══╝    ██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\n" + 
				" ███████╗██║███████╗   ██║       ███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \n" + 
				" ╚════██║██║╚════██║   ██║       ██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \n" + 
				" ███████║██║███████║   ██║       ██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \n" + 
				" ╚══════╝╚═╝╚══════╝   ╚═╝       ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \n" +                                               
				"");
		
	}//mainlogo
	
	
	/**
	 * 로그인 화면 헤더 출력메서드
	 */
	public static void showLoginHeader() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t  메인화면\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t┏━━━━━━━━━━━━━━━━━━┓\t┏━━━━━━━━━━━━━━━━━━┓\t\t  ┃");
		System.out.println("\t┃\t\t┃     1.로그인     ┃\t┃ 0. 프로그램 종료 ┃\t\t  ┃");
		System.out.println("\t┃\t\t┗━━━━━━━━━━━━━━━━━━┛\t┗━━━━━━━━━━━━━━━━━━┛\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 선택하세요. : ");
	}
	
	
	/**
	 * 관리자의 메인메뉴를 출력하는 메소드이다.
	 */
	public static void menu() {
		
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
		System.out.println("\t│\t\t\t\t\t\t\t\t\t  │");
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	

	//////////////////////////////////////////지현//////////////////////////////////////////
	/*
	 * 개설과정관리 시작메뉴입니다.
	 * @author 박지현
	 */
	public void openCourseStart() {
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설과정관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t1. 개설과정조회\t2. 개설과정등록\t\t\t  │");
		System.out.println("\t│\t\t\t3. 개설과정수정\t4. 개설과정삭제\t\t\t  │");
		System.out.println("\t│\t\t\t0. 뒤로가기\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");

	} 
	
	/**
	 * 개설과정조회 헤더입니다.
	 * @author 박지현
	 */
	public void openCourseView1() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설과정조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
	}
	
	
	/**
	 * 개설과정조회 컬럼명입니다.
	 * @author 박지현
	 */
	public void openCourseView2() {
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│%-5s\t%20s%45s%14s%17s%10s%10s\t│\n", "[번호]", "[과정명]", "[시작일]", "[종료일]", "[강의실]", "[등록인원]", "[수료여부]");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	
	/**
	 * 개설과정조회 번호입력
	 * @author 박지현
	 */
	public void openCourseView3() {
		
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ * 특정과정조회를 원하시면 과정번호를 입력해주세요.\t\t\t\t│");
		System.out.println("\t│ * 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 과정 번호: ");
		
	}
	
	
	/**
	 * 특정개설과정조회 뷰입니다.
	 * @author 박지현
	 */
	public String openSpecificCourseView(ArrayList<OpenCourseListDTO> list, String num) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     특정개설과정조회\t\t\t\t┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		for (OpenCourseListDTO dto : list) {
			
			if(num.equals(dto.getSeqOpenCourse())) {
				
				System.out.printf("\t 번호 : %-65s\n", dto.getSeqOpenCourse());
				System.out.printf("\t 과정명 : %-55s \n", dto.getName());
				System.out.printf("\t 시작일 : %s\n", dto.getStartDate());
				System.out.printf("\t 종료일 : %s\n", dto.getEndDate());
				System.out.printf("\t 강의실 : %s\n", dto.getRoom());
				System.out.printf("\t 등록인원 : %s\n", dto.getMemberCount() + "명");
				System.out.printf("\t 수료여부 : %s\n", dto.getState());
				
				System.out.println();
				System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
				System.out.println("\t│ \t\t1. 과목조회\t2. 교육생조회\t0. 뒤로가기\t\t│");
				System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
				System.out.print("\t█ 원하시는 메뉴를 입력하세요 : ");
			
				return dto.getName();
			}			
		}
		
		return null;
		
	}
	
	/**
	 * 1. 특정개설과정의 과목조회 뷰입니다.
	 * @author 박지현
	 */
	public void specificSubjectView(ArrayList<OpenSubjectListDTO> list, String openCourseName) {
		
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\"%s\"의 과목리스트입니다.\t\t\n", openCourseName);
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
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
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ * 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t      │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 입력 : ");
	}
	
	
	
	/**
	 * 2. 특정개설과정의 교육생조회 뷰입니다.
	 * @author 박지현
	 */
	public void specificStudentView(ArrayList<OpenCourseStudentDTO> list, String openCourseName) {
		
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
			
			System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────");			
		
		}//for
		
		
	
		System.out.println();
		System.out.println("\t* 뒤로가기를 원하시면 0을 입력해주세요.");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	/**
	 * 개설과정등록 헤더입니다.
	 * @author 박지현
	 */
	public void openCourseAddView() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t개설과정등록\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
	
	
	/**
	 * 개설과정수정 메인입니다.
	 * @author 박지현
	 */
	public void openCourseEdit() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t개설과정수정\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		
		
	}
	
	
	/**
	 * 개설과정수정에서 과정번호를 받을 뷰입니다.
	 * @author 박지현
	 */
	public void openCourseEdit2() {
		
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────┐");
		System.out.println("\t│\t * 수정할 과정번호를 입력해주세요.\t\t\t│");
		System.out.println("\t│\t * 뒤로가기를 원하시면 0을 입력해주세요.\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────┘");
		System.out.print("\t█ 입력: ");
	}

	
	/**
	 * 개설과정수정에서 수정할 목록입니다.
	 * @author 박지현
	 */
	public void openCourseEdit3() {
		
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────┐");
		System.out.println("\t│ \t\t1. 강의실수정\t\t\t\t│");
		System.out.println("\t│ \t\t2. 날짜수정\t\t\t\t│");
		System.out.println("\t│ \t\t3. 기초과정번호수정\t\t\t\t│");
		System.out.println("\t│ \t\t0. 뒤로가기\t\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────┘");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	
	/**
	 * 개설과정수정 - 강의실
	 * @author 박지현
	 */
	public void openCourseRoomEdit(String roomName) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t강의실수정\t\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.printf("\t현재 강의실은 %s 입니다.\n", roomName);
		System.out.print("\t█ 수정할 강의실 번호 : ");
		
		
	}
	
	/**
	 * 개설과정수정 - 날짜
	 * @author 박지현
	 */
	public void openCourseDateEdit(String startDate, String endDate) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t날짜수정\t\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────┐");
		System.out.printf("\t│ 해당 과정은 시작일 %s - 종료일 %s 입니다.\t│\n", startDate, endDate);
		System.out.println("\t└───────────────────────────────────────────────────────┘");
		
		
	}
	
	
	/**
	 * 개설과정수정 - 기초과정번호
	 * @author 박지현
	 */
	public void openBasicCourseEdit(String basicCourse, String seqBasicCourse) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t\t기초과정번호 수정\t\t\t\t  ┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.printf("\t* 현재 기초과정은 \"%s. %s\"입니다.\t\n", seqBasicCourse, basicCourse);
		System.out.printf("\t* 아래 기초과정리스트 중에서 과정번호를 선택해주세요.\n");

	}
	
	
	/**
	 * 개설과정삭제 헤더입니다.
	 * @author 박지현
	 */
	public void openBasicCourseDelete() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t\t개설과정삭제\t\t\t\t  ┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.printf("\t* 아래 개설과정 리스트에서 삭제를 원하는 과정번호를 선택해주세요.\n");
		System.out.println();

	}

	
	/*
	 * 개설과목관리 시작메뉴입니다.
	 * @author 박지현
	 */
	public void openSubjectStart() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설과목관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t1. 개설과목조회\t2. 개설과목등록\t\t\t  │");
		System.out.println("\t│\t\t\t3. 개설과목수정\t4. 개설과목삭제\t\t\t  │");
		System.out.println("\t│\t\t\t0. 뒤로가기\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}	

	/**
	 * 개설과목조회 헤더입니다.
	 * @author 박지현
	 */
	public void openSubjectView1() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설과목조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
	}
	
	/**
	 * 개설과목조회 컬럼명입니다.
	 * @author 박지현
	 */
	public void openSubjectView2() {
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t│%-5s\t%-47s%-30s%-10s%-13s%-12s\t\t│\n", "[번호]", "[과목명]", "[교재명]", "[교사명]", "[시작일]", "[종료일]");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	
	/**
	 * 개설과정조회 번호입력
	 * @author 박지현
	 */
	public void openSubjectView3() {
		
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│ 특정과목조회를 원하시면 과목번호를 입력해주세요.\t\t\t\t\t│");
		System.out.println("\t│ 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 과목 번호: ");
		
	}
	
	
	/**
	 * 특정개설과정조회 뷰입니다.
	 * @author 박지현
	 */
	public void specificOpenSubject(ArrayList<OpenSubjectListDTO> list, String seqOpenSubject) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     특정개설과목조회\t\t\t\t┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		for (OpenSubjectListDTO dto : list) {
			
			if(seqOpenSubject.equals(dto.getSeqOpenSubject())) {
				
				System.out.printf("\t 번호 : %-65s\n", dto.getSeqOpenSubject());
				System.out.printf("\t 과목명 : %-55s\n", dto.getSubjectName());
				System.out.printf("\t 교재명 : %-20s\n", dto.getBookName());
				System.out.printf("\t 교사명 : %-5s\n", dto.getTeacherName());
				System.out.printf("\t 시작일 : %s\n", dto.getStartDate());
				System.out.printf("\t 종료일 : %s\n", dto.getEndDate());
				
				System.out.println();
				System.out.println("\t┌───────────────────────────────────────────────────────────────────────┐");
				System.out.println("\t│ \t\t * 뒤로가기를 원하시면 0을 입력해주세요.\t\t\t│");
				System.out.println("\t└───────────────────────────────────────────────────────────────────────┘");
				System.out.print("\t█ 입력 : ");
			}			
		}
		
	}
	
	
	//////////////////////개설과목///////////////////////////
	/**
	 * 개설과목등록 헤더입니다.
	 * @author 박지현
	 */
	public void openSubjectAddView() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t개설과목등록\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
	
	
	/**
	 * 개설과목수정 헤더입니다.
	 * @author 박지현
	 */
	public void openSubjectEdit() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t개설과목수정\t\t\t┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		
		
	}
	
	
	/**
	 * 개설과목수정에서 과목번호를 받을 뷰입니다.
	 * @author 박지현
	 */
	public void openSubjectEdit2() {
		
		System.out.println();
		System.out.println("\t┌───────────────────────────────────────────────────────┐");
		System.out.println("\t│\t * 수정할 과목번호를 입력해주세요.\t\t\t│");
		System.out.println("\t│\t * 뒤로가기를 원하시면 0을 입력해주세요.\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────┘");
		System.out.print("\t█ 입력: ");
	}

	
	/**
	 * 개설과목수정 - 현재과목 정보 헤더입니다.
	 * @author 박지현
	 */
	public void openSubjectEdit3() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t현재과목정보\t\t\t┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}
	

	
	/**
	 * 개설과목삭제 헤더입니다.
	 * @author 박지현
	 */
	public void openSubjectDelete() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.printf("\t┃\t\t\t\t개설과목삭제\t\t\t\t  ┃\n");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.printf("\t* 아래 개설과목 리스트에서 삭제를 원하는 과정번호를 선택해주세요.\n");
		System.out.println();
	
	}
		
		
	/**
	 * 자리맞추기 메서드
	 */
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
		

	//////////////////////////////////////////다은///////////////////////////////////////////
	
	
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
	 * 과정 수정 헤더 출력 메소드이다.
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
	 * 과목 조회 헤더 출력 메소드이다. 
	 */
	public void subjectListHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     과목 정보 목록 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}
	
	
	/**
	 * 과목 조회 컬럼명 출력 메소드이다. 
	 */
	public void subjectListHeader2() {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t\t[과목이름]\t\t\t[교재이름]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
	}
	
	
	/**
	 * 과목 추가 헤더 출력 메소드이다.
	 */
	public void addSubjectHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     새로운 과목 추가\t\t\t          ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   새로운 과목을 추가하시려면 아래 항목을 작성해주세요.");
		System.out.println();
				
	}
	

	/**
	 * 과목 수정 헤더 출력 메소드이다.
	 */
	public void updateSubjectHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t       과목 정보 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   기존 과목을 수정하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	/**
	 * 과목 삭제 헤더 출력 메소드이다.
	 */
	public void deleteSubjectHeader() {
	
	System.out.println("\n");
	System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
	System.out.println("\t┃\t\t\t       기존 과목 삭제\t\t\t\t  ┃");
	System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	System.out.println();
	System.out.println("\t\t   기존 과목을 삭제하시려면 아래 항목을 작성해주세요.");
	System.out.println();
	
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
	 * 강의실 조회 헤더 출력 메소드이다. 
	 */
	public void roomListHeader() {
	
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t   강의실 정보 목록 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	
	}
	
	/**
	 * 강의실 조회 컬럼명 출력 메소드이다. 
	 */
	public void roomListHeader2() {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t  [번호]\t\t[강의실명]\t\t\t[수용인원]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
	}
	
	
	/**
	 * 강의실 추가 헤더 출력 메소드이다.
	 */
	public void addRoomHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     새로운 강의실 추가\t\t\t          ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   새로운 강의실을 추가하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	
	/**
	 * 강의실 수정 헤더 출력 메소드이다.
	 */
	public void updateRoomHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t      강의실 정보 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   기존 강의실을 수정하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	/**
	 * 강의실 삭제 헤더 출력 메소드이다.
	 */
	public void deleteRoomHeader() {
	
	System.out.println("\n");
	System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
	System.out.println("\t┃\t\t\t      기존 강의실 삭제\t\t\t\t  ┃");
	System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	System.out.println();
	System.out.println("\t\t   기존 강의실을 삭제하시려면 아래 항목을 작성해주세요.");
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
	
	
	/**
	 * 교재 조회 헤더 출력 메소드이다. 
	 */
	public void bookListHeader() {
	
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     교재 정보 목록 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}
	
	/**
	 * 교재 조회 컬럼명 출력 메소드이다. 
	 */
	public void bookListHeader2() {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t  [교재이름]\t\t      [출판사]\t       [저자]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}
	
	/**
	 * 교재 추가 헤더 출력 메소드이다.
	 */
	public void addBookHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t     새로운 교재 추가\t\t\t          ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   새로운 교재를 추가하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	
	/**
	 * 교재 수정 헤더 출력 메소드이다.
	 */
	public void updateBookHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t       교재 정보 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t   기존 과목을 수정하시려면 아래 항목을 작성해주세요.");
		System.out.println();
		
	}
	
	
	/**
	 * 교재 삭제 헤더 출력 메소드이다.
	 */
	public void deleteBookHeader() {
	
	System.out.println("\n");
	System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
	System.out.println("\t┃\t\t\t       기존 교재 삭제\t\t\t\t  ┃");
	System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	System.out.println();
	System.out.println("\t\t   기존 교재를 삭제하시려면 아래 항목을 작성해주세요.");
	System.out.println();
	
	}
	
	
	
	//------------------출결관리
	
	/**
	 * 출결관리 헤더를 출력하는 메서드입니다.
	 */
	public void attendanceHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 출결 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
	}
	
	/**
	 * 출결조회 헤더를 출력하는 메서드입니다.
	 */
	public void checkAttendanceHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 출결 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
	}
	
	
	/**
	 * 출결조회시 개설과정정보 리스트를 출력하는 메소드이다.
	 * @param result
	 */
	public void allOpenCourseList(ArrayList<AllOpenCourseDTO> result) {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t\t[과정이름]\t\t\t       [과정시작일] [과정종료일]   [교사]     [강의실]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		for (AllOpenCourseDTO dto : result) {
			
			System.out.printf("\t%3s\t%-35s\t%-10s   %-10s    %-5s   %-10s\n"
					, dto.getSeqOpenCourse()
					, dto.getName()
					, dto.getStartDate()
					, dto.getEndDate()
					, dto.getTeacherName()
					, dto.getRoomName());
		}
		
		System.out.println("\t────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
	}
	
	
	/**
	 * 특정 개설과정 번호 선택 시 그 과정을 수강하고 있는 모든 교육생 정보를 출력하는 메서드이다.
	 * @param aslist 특정 과정을 수강중인 교육생 정보
	 */
	public void allStudentList(ArrayList<ViewStudentDTO> aslist) {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t[이름]\t    [ID]\t [PW]\t    [등록일]\t    [수강상태]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		for(ViewStudentDTO dto : aslist) {
			System.out.printf("\t%4s\t%s\t%10s\t%-8s   %s\t %-20s\n"
										,dto.getSeqStudent()
										,dto.getName()
										,dto.getId()
										,dto.getPw()
										,dto.getRegDate()
										,dto.getStudentState());
		}	
		
		System.out.println("\t──────────────────────────────────────────────────────────────────────────");
		System.out.println();
		
	}
	
	
	/**
	 * 출결관리 메뉴를 출력하는 메서드이다.
	 */
	public void attendanceMenu() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t1. 기간별 조회\t\t2. 출결 수정\t\t0. 뒤로 가기\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	
	/**
	 * 기간별 출결조회 헤더 출력 메서드이다.
	 */
	public void searchPeriodHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t  기간별 출결 목록 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
	/**
	 * 출결 상태 수정 헤더 출력 메서드이다.
	 */
	public void editAttedanceHeader() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t       근태 상황 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
		
	
	/**
	 * 관리자 뷰의 특정 개설 과정의 특정 교육생의 특정 기간의 출결 목록을 출력하는 메소드이다.
	 * 번호, 수강날짜, 출결상황을 출력한다.
	 * @param result
	 */
	public void attendanceList(ArrayList<PeriodAttendListDTO> result) {
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t  [날짜]\t [입실]\t\t [퇴실]\t\t[상태]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (PeriodAttendListDTO dto : result) {
			
			System.out.printf("\t%s\t%-9s\t%-10s\t %-10s\n"
					, dto.getAttendDate()
					, dto.getInTime()
					, dto.getOutTime()
					, dto.getAttendState());
		}
		
		System.out.println("\t──────────────────────────────────────────────────────────────");
		System.out.println();
		
	}

	
	
	/////////////////////////////////////////////////채원///////////////////////////////////////////
	
	
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














	
		/**
	 * 교육생 관리 메뉴 출력 메소드이다.
	 */
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
	
	/**
	 * 교육생 조회 메뉴 출력 메소드이다.
	 */
	public void menu_adminStd_search() {
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교육생 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 아이디로 조회\t2. 수강번호로 조회\t\t  │");
		System.out.println("\t│\t\t3. 이름으로 조회\t4. 전체 교육생 조회\t\t  │");
		System.out.println("\t│\t\t5. 뒤로 가기\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
	}
	

	
}
	

