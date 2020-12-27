package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.AdminTestScoreManagementDAO;
import com.project.dao.StudentDAO;
import com.project.dto.VwStudentTestScoreDTO;


/**
 * 관리자 모드의 모든 배점관련 기능을 담당하는 클래스이다.
 * @author 조성진
 *
 */
public class AdminTestManagement {

	private Scanner scan = new Scanner(System.in);
	static AdminTestScoreManagementDAO atsm;
	static StudentDAO sdao;
	static AdminView view;
	
	static {
		view = new AdminView();
		atsm = new AdminTestScoreManagementDAO();
		sdao = new StudentDAO();
	}
	
	/**
	 * 관리자 과목별 배점 메뉴 메서드입니다.
	 */
	public void menu() {
		
		
		TestQuestionInquiry();
		
	}
	
	/**
	 * 관리자가 과목별 배점을 조회하는 메서드입니다. 
	 */
public void TestQuestionInquiry() {

		
		ArrayList<VwStudentTestScoreDTO> list = atsm.list();
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설 과목 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t   [과목번호]\t\t[교사명]\t[강의실명]\t\t           [과목명]\t\t\t\t[과목시작일]\t     [과목종료일]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwStudentTestScoreDTO dto : list) {
			String subName = dto.getSubjectName() + "                ";
			System.out.printf("\t\t%s\t\t %s\t\t%s\t\t%30s\t\t%-13s\t\t%s\n"
							,dto.getSeqOpenSubject()
							,dto.getTeacherName()
							,dto.getRoomName()
							,subName.substring(0,22)
							,dto.getSubjectStart().substring(0,10)
							,dto.getSubjectEnd().substring(0,10));
			
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}
		System.out.println();
		
		System.out.print("\t조회할 과목 번호를 입력해주세요 : ");
		String num = scan.nextLine();
		
		ArrayList<VwStudentTestScoreDTO> list4 = atsm.list4(num);
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과목별 배점 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t     [과목번호]\t\t     [과목명]\t\t\t[필기배점]\t\t[실기배점]\t\t[출결배점]\t");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwStudentTestScoreDTO dto : list4) {
			System.out.printf("\t\t %s\t\t %s\t\t   %s점\t\t\t   %s점\t\t\t   %s점\n"
							,dto.getSeqOpenSubject()
							,dto.getSubjectName()
							,dto.getWrittenPercent()
							,dto.getPracticalPercent()
							,dto.getAttendancePercent());
			
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			
		}
		 pause();
	}
	

	/**
	 * 연속진행을 막기위한 pause 메서드입니다.
	 */
public void pause() {
	
	System.out.print("계속 진행하시려면 Enter를 입력해주세요..");
	scan.nextLine();
}

}
