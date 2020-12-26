package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.AdminTestScoreManagementDAO;
import com.project.dto.VwStudentTestScoreDTO;

public class AdminTestScoreManagement {

	private Scanner scan = new Scanner(System.in);
	static AdminTestScoreManagementDAO atsm;
	static AdminView view;
	
	static {
		view = new AdminView();
		atsm = new AdminTestScoreManagementDAO();
	}
	public void main() {
		
		
		view.admin_TestScoreManagementMenu();
		TestQuestionInquiry();
		System.out.println("(메인메뉴로 돌아가시려면 Enter를 입력해주세요..)");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
		String num = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			if (num.equals("1")) {
				TestQuestionInquiry();
			} else if (num.equals("2")) {
				TestQuestionInquiry();
			} else if (num.equals("")) {
				break;
			} else {
				System.out.println("제대로된 번호를 입력해주세요.");
			}

		}
	}
	
	/**
	 * 관리자가 과목별로 성적조회하는 메서드 
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
		
		System.out.print("조회할 과목 번호를 입력해주세요 : ");
		String num = scan.nextLine();
		
		ArrayList<VwStudentTestScoreDTO> list2 = atsm.list2(num);
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과목별 학생 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t     [학생번호]\t\t[학생명]\t[필기점수]\t\t[실기점수]\t\t[출결점수]\t   [수강상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwStudentTestScoreDTO dto : list2) {
			System.out.printf("\t\t %s\t\t %s\t\t   %s점\t\t\t   %s점\t\t\t   %s점\t\t     %s\n"
							,dto.getStuSeq()
							,dto.getStudentName()
							,dto.getWrittenScore()
							,dto.getPracticalScore()
							,dto.getAttendanceScore()
							,dto.getStudentState());
			
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			
		}
		 pause();
	}
	
	public void pause() {
		
		System.out.print("계속 진행하시려면 Enter를 입력해주세요..");
		scan.nextLine();
	}
	
	
	
}
