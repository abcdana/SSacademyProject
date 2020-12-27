package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dto.TeacherDTO;
import com.project.dto.VwSubjectInquiryDTO;
import com.project.dto.VwStudentTestScoreDTO;
import com.project.teacher.dto.VwTestPercentInquiryDTO;


/**
 * 교사의 모든 배점을 관리하는 클래스입니다.
 *@author 조성진
 *
 */
public class TestManagement {
	
	private Scanner scan;
	private TestManagementDAO managedao;
	private static String tSeq;

	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다.
	 */
	public TestManagement(TeacherDTO dto) {
		scan = new Scanner(System.in);
		managedao = new TestManagementDAO();
		tSeq = dto.getSeqTeacher();

	}
	
	/**
	 * 교사가 강의하는 과목들을 조회하는 메서드입니다.
	 * @param tSeq 로그인한 교사 번호
	 */
	public void subScoreInquiry(String tSeq) {

		String sSeq2 = "";
		String time = "and subEnd < sysdate";
		ArrayList<VwSubjectInquiryDTO> list = managedao.list(tSeq, time);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목 번호]\t\t\t [과정이름]\t\t\t   [과목이름]\t\t [과목시작]\t [과목종료]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwSubjectInquiryDTO dto : list) {
			String sSeq = dto.getSubSeq();
			if(!sSeq.equals(sSeq2)) {
			System.out.printf("\t    %s\t\t %s\t %-20s%s\t %s\n", dto.getSubSeq(), dto.getCourseName(),
					dto.getSubName(), dto.getSubStart().substring(0, 10), dto.getSubEnd().substring(0, 10));
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			}
			sSeq2 = dto.getSubSeq();
		}
		System.out.println();
	}
	
	
	/**
	 * 교사가 강의한 과목에 대한 배점을 업데이트하는 메서드입니다.
	 * @param tSeq 로그인한 교사 번호
	 */
	public void subTestManagement(String tSeq) { // 배점 업데이트
		subScoreInquiry(tSeq);
		System.out.print("\t관리할 과목번호를 입력해주세요 : ");
		String subnum = scan.nextLine();

		ArrayList<VwTestPercentInquiryDTO> list2 = managedao.list2(subnum);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목번호]\t[필기배점]\t[실기배점]\t[출결배점]\t[필기시험 날짜]\t\t [실기시험 날짜]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwTestPercentInquiryDTO dto : list2) {
			System.out.printf("\t    %s\t\t    %s\t\t  %s\t\t  %s\t\t\t%s\t\t\t%s\n"
					,dto.getSubSeq()
					,dto.getWritten()
					,dto.getPractical()
					,dto.getAttendance()
					,dto.getWrittenDate()
					,dto.getPracticaldate());
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}

		System.out.println();

		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t필기배점 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\tEnter 입력 시 기존 점수가 그대로 들어갑니다.");
		System.out.print("\n\t필기 점수를 입력해주세요 : ");
		String wpercent = scan.nextLine();
		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t실기배점 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\tEnter 입력 시 기존 점수가 그대로 들어갑니다.");
		System.out.print("\t실기 점수를 입력해주세요 : ");
		String ppercent = scan.nextLine();
		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t출결배점 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\tEnter 입력 시 기존 점수가 그대로 들어갑니다.");
		System.out.print("\t출결 점수를 입력해주세요 : ");
		String apercent = scan.nextLine();
		
		list2 = managedao.list2(subnum);
		for (VwTestPercentInquiryDTO dto : list2) {
			if (dto.getSubSeq().equals(subnum)) {
				if(wpercent.equals("")) {
					wpercent = dto.getAttendance();
				}
				if(ppercent.equals("")) {
					ppercent = dto.getPractical();
				}
				if(apercent.equals("")) {
					apercent = dto.getAttendance();
				}
			}
		} // 엔터 입력시 전에 값이 들어가게 하는 문장

		TestManagementDAO dao = new TestManagementDAO();

		dao.subScoreEdit(subnum, wpercent, ppercent, apercent);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목번호]\t[필기배점]\t[실기배점]\t[출결배점]\t  [필기시험 날짜]\t\t  [실기시험 날짜]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		list2 = managedao.list2(subnum);
		
		for (VwTestPercentInquiryDTO dto : list2) {
			System.out.printf("\t    %s\t\t    %s\t\t   %s\t\t   %s\t\t\t%s\t\t\t%s\n"
					,dto.getSubSeq()
					,dto.getWritten()
					,dto.getPractical()
					,dto.getAttendance()
					,dto.getWrittenDate()
					,dto.getPracticaldate());
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}

		System.out.println("\t 배점 수정이 완료되었습니다.");
		pause();
	}
	
	/**
	 * 교사가 강의한 과목에 대한 필기시험 날짜를 업데이트하는 메서드입니다.
	 * @param tSeq 로그인한 교사 번호
	 */
	public void subWrittenDateUpdate(String tSeq) { // 필기시험 날짜 업데이트
		subScoreInquiry(tSeq);
		System.out.print("\t관리할 과목번호를 입력해주세요 : ");
		String subnum = scan.nextLine();

		ArrayList<VwTestPercentInquiryDTO> list2 = managedao.list2(subnum);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목번호]\t[필기배점]\t[실기배점]\t[출결배점]\t[필기시험 날짜]\t\t [실기시험 날짜]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwTestPercentInquiryDTO dto : list2) {
			String writtenDate =dto.getWrittenDate() +"         ";
			String practicaldate =dto.getPracticaldate() +"         ";
			System.out.printf("\t    %s\t\t    %s\t\t   %s\t\t   %s\t\t %s\t\t    %s\n"
					,dto.getSubSeq()
					,dto.getWritten()
					,dto.getPractical()
					,dto.getAttendance()
					,writtenDate.substring(0, 10)
					,practicaldate.substring(0, 10));
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			}


		System.out.println();

		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t필기시험 날짜 수정\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("\n\t필기시험 날짜를 입력해주세요(Ex2020-12-25) : ");
		String writtenDate = scan.nextLine();
		
		TestManagementDAO dao = new TestManagementDAO();
//		writtenDate = String.format("'%s'", writtenDate);
		dao.subWrittenDateUpdate(subnum, writtenDate);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목번호]\t[필기배점]\t[실기배점]\t[출결배점]\t[필기시험 날짜]\t\t [실기시험 날짜]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		list2 = managedao.list2(subnum);
		
			for (VwTestPercentInquiryDTO dto : list2) {
				String writtenDate1 =dto.getWrittenDate() +"         ";
				String practicaldate =dto.getPracticaldate() +"         ";
				System.out.printf("\t    %s\t\t    %s\t\t   %s\t\t   %s\t\t %s\t\t    %s\n"
						,dto.getSubSeq()
						,dto.getWritten()
						,dto.getPractical()
						,dto.getAttendance()
						,writtenDate1.substring(0, 10)
						,practicaldate.substring(0, 10));
				System.out.print(
						"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
				}

		System.out.println("\t 필기날짜 수정이 완료되었습니다.");
		pause();
	} // 필기시험 날짜 업데이트 종료
	
	
	
	/**
	 * 	 * 교사가 강의한 과목에 대한 실기시험 날짜를 업데이트하는 메서드입니다.
	 * @param tSeq 로그인한 교사 번호
	 */
	public void subPracticalDateUpdate(String tSeq) { // 실기시험 날짜 업데이트
		subScoreInquiry(tSeq);
		System.out.print("\t관리할 과목번호를 입력해주세요 : ");
		String subnum = scan.nextLine();

		ArrayList<VwTestPercentInquiryDTO> list2 = managedao.list2(subnum);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목번호]\t[필기배점]\t[실기배점]\t[출결배점]\t[필기시험 날짜]\t\t [실기시험 날짜]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwTestPercentInquiryDTO dto : list2) {
			String writtenDate =dto.getWrittenDate() +"         ";
			String practicaldate =dto.getPracticaldate() +"         ";
			System.out.printf("\t    %s\t\t    %s\t\t   %s\t\t   %s\t\t %s\t\t    %s\n"
					,dto.getSubSeq()
					,dto.getWritten()
					,dto.getPractical()
					,dto.getAttendance()
					,writtenDate.substring(0, 10)
					,practicaldate.substring(0, 10));
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			}


		System.out.println();

		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t실기시험 날짜 수정\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("\n\t실기시험 날짜를 입력해주세요(Ex2020-12-25) : ");
		String practicalDate = scan.nextLine();
		
		TestManagementDAO dao = new TestManagementDAO();
		dao.subPracticalDateUpdate(subnum, practicalDate);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목번호]\t[필기배점]\t[실기배점]\t[출결배점]\t[필기시험 날짜]\t\t [실기시험 날짜]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		list2 = managedao.list2(subnum);
		
			for (VwTestPercentInquiryDTO dto : list2) {
				String writtenDate1 =dto.getWrittenDate() +"         ";
				String practicaldate =dto.getPracticaldate() +"         ";
				System.out.printf("\t    %s\t\t    %s\t\t   %s\t\t   %s\t\t %s\t\t    %s\n"
						,dto.getSubSeq()
						,dto.getWritten()
						,dto.getPractical()
						,dto.getAttendance()
						,writtenDate1.substring(0, 10)
						,practicaldate.substring(0, 10));
				System.out.print(
						"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
				}

		System.out.println("\t 실기날짜 수정이 완료되었습니다.");
		pause();
	}

	/**
	 * 연속된 진행을막기위한 pause메서드입니다.
	 */
	private void pause() {
		
		System.out.print("\t진행하시려면 Enter를 눌러주세요..");
		scan.nextLine();
	}



	
	
}
