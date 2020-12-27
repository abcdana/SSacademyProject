package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.AdminTestScoreManagementDAO;
import com.project.dao.StudentDAO;
import com.project.dto.StudentDTO;
import com.project.dto.VwStudentTestScoreDTO;


/**
 * 관리자 모드의 모든 성적관련 기능을 담당하는 클래스이다.
 * @author 조성진
 *
 */
public class AdminTestScoreManagement {

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
	 * 관리자 성적관리 메뉴 메서드입니다.
	 */
	public void menu() {
		
		
		view.admin_TestScoreManagementMenu();
		System.out.println("\t(메인메뉴로 돌아가시려면 Enter를 입력해주세요..)");
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
		String num = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			if (num.equals("1")) {
				TestQuestionInquiry();
			} else if (num.equals("2")) {
				StudentInquiry();
			} else if (num.equals("")) {
				break;
			} else {
				System.out.println("제대로된 번호를 입력해주세요.");
			}

		}
	}
	
	/**
	 * 관리자가 개설과목별로 배점을조회하는 메서드입니다.
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
	
	
	
	/**
	 * 성적을 학생으로 조회하는 메서드입니다.
	 */
public void StudentInquiry() {

		int stunum = 0;
		int size = 30;
		ArrayList<StudentDTO> list = sdao.list();
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t학생 목록 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t   [학생번호]\t\t[학생이름]\t\t[주민등록번호]\t\t\t [전화번호]\t\t[과정등록일]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (StudentDTO dto : list) {
			if(size<=stunum) {
				System.out.print("\n\t다음페이지로 이동하시려면 R키 학생을 선택하시려면 S키를 입력해주세요....\n\t입력 : ");
				String str = scan.nextLine();
				
				if(str.equals("r")||str.equals("R")) {
					size+=30;
				}
				else if(str.equals("s")||str.equals("S")) {
					break;
				}
			}
			if(size>stunum){
			System.out.printf("\t\t%s\t\t  %s\t\t%s\t\t\t%s\t\t %s\n"
							,dto.getSeqStudent()
							,dto.getName()
							,dto.getSsn()
							,dto.getPhone()
							,dto.getFirstRegistrationDate());
			
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			stunum++;
			}
			
		}
		System.out.println();
		
		System.out.print("\t조회할 학생 번호를 입력해주세요 : ");
		String num = scan.nextLine();
		
		ArrayList<VwStudentTestScoreDTO> list3 = atsm.list3(num);
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t학생 성적 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t     [학생번호]\t\t[학생명]\t[필기점수]\t\t[실기점수]\t\t[출결점수]\t   [수강상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwStudentTestScoreDTO dto : list3) {
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

	/**
 	* 연속진행을 막기위한 pause 메서드입니다.
 	*/
	public void pause() {
		
		System.out.print("계속 진행하시려면 Enter를 입력해주세요..");
		scan.nextLine();
	}
	
	
	
}
