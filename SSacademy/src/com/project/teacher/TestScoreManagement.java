package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dto.TeacherDTO;
import com.project.dto.VwSubjectInquiryDTO;
import com.project.dto.VwStudentTestScoreDTO;


/**
 * 교사가 모든 학생의 성적 정보를 관리하기위한 클래스입니다.
 * @author 조성진
 *
 */
public class TestScoreManagement {
	
	private Scanner scan;
	private TestManagementDAO managedao;
	private TestScoreManagementDAO manageScoredao;
	private static String tSeq;

	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다.
	 */
	public TestScoreManagement(TeacherDTO dto) {
		scan = new Scanner(System.in);
		managedao = new TestManagementDAO();
		manageScoredao = new TestScoreManagementDAO();
		tSeq = dto.getSeqTeacher();

	}
	

	/**
	 * 교사가 학생번호를 이용해 성적을 업데이트하는 메서드입니다.
	 * @param tSeq2 로그인한 교사번호
	 */
	public void stuScoreEdit(String tSeq2) { // 교사가 학생번호로 성적을 업데이트하는 메서드
		
		ArrayList<VwStudentTestScoreDTO> list2 = manageScoredao.list2(tSeq);
		
		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t학생별 성적관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[성적번호]\t[학생명]\t\t[과목명]\t\t[필기]\t\t[실기]\t\t[출결]\t\t  [상태]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwStudentTestScoreDTO dto : list2) {
			System.out.printf("\t    %s\t\t %s\t\t%s\t\t %s\t\t  %s\t\t  %s\t\t  %s\n", dto.getTestSeq(),
					dto.getStudentName(),(dto.getSubjectName() +"                    ").substring(0,20), dto.getWrittenScore(), dto.getPracticalScore(), dto.getAttendanceScore(),dto.getStudentState());
			System.out.print("\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
		}
		System.out.print("\t관리할 성적번호를 입력해주세요 : ");
		String testnum = scan.nextLine();
		
		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t성적 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\tEnter 입력 시 기존 점수가 그대로 들어갑니다.");

		System.out.print("\n\t필기 점수를 입력해주세요 : ");
		String wscore = scan.nextLine();

		System.out.print("\t실기 점수를 입력해주세요 : ");
		String pscore = scan.nextLine();

		System.out.print("\t출결 점수를 입력해주세요 : ");
		String ascore = scan.nextLine();


		list2 = manageScoredao.list2(tSeq);
		for (VwStudentTestScoreDTO dto : list2) {
			if (dto.getTestSeq().equals(testnum)) {
				if(wscore.equals("")) {
					wscore = dto.getWrittenScore();
				}
				if(pscore.equals("")) {
					pscore = dto.getPracticalScore();
				}
				if(ascore.equals("")) {
					ascore = dto.getAttendanceScore();
				}
			}
		}
		
		TestScoreManagementDAO dao = new TestScoreManagementDAO();
		dao.subScoreEdit2(testnum, wscore, pscore, ascore);

		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t학생별 성적관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[학생번호]\t[학생명]\t\t[필기]\t\t[실기]\t\t[출결]\t\t  [상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		list2 = manageScoredao.list2(tSeq);
		for (VwStudentTestScoreDTO dto : list2) {
			if (dto.getTestSeq().equals(testnum)) {
				System.out.printf("\t    %s\t\t %s\t\t\t  %s\t\t  %s\t\t  %s\t\t  %s\n", dto.getTestSeq(),
						dto.getStudentName(), dto.getWrittenScore(), dto.getPracticalScore(), dto.getAttendanceScore(),
						dto.getStudentState());
				System.out.print(
						"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			}
		}

		System.out.println("\t 성적 수정이 완료되었습니다.");
		pause();
		


		
		
	}// 교사가 학생번호로 성적을 업데이트하는 메서드 end
	
	

	/**
	 * 교사가 특정 과목으로 학생의 성적을 업데이트하는 메서드입니다.
	 * @param tSeq 로그인한 교사번호
	 */
public void subScoreEdit(String tSeq) { // 특정과목으로 학생성적 업데이트
		subScoreInquiry(tSeq);
		System.out.print("\t관리할 과목번호를 입력해주세요 : ");
		String subnum = scan.nextLine();

		ArrayList<VwStudentTestScoreDTO> list = manageScoredao.list(tSeq, subnum);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[학생번호]\t[학생명]\t\t[필기]\t\t[실기]\t\t[출결]\t\t  [상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwStudentTestScoreDTO dto : list) {
			System.out.printf("\t    %s\t\t %s\t\t\t  %s\t\t  %s\t\t  %s\t\t  %s\n", dto.getStuSeq(),
					dto.getStudentName(), dto.getWrittenScore(), dto.getPracticalScore(), dto.getAttendanceScore(),
					dto.getStudentState());
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}

		System.out.println();

		System.out.print("\t관리할 학생번호를 입력해주세요 : ");
		String stunum = scan.nextLine();

		System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t성적 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\tEnter 입력 시 기존 점수가 그대로 들어갑니다.");

		System.out.print("\n\t필기 점수를 입력해주세요 : ");
		String wscore = scan.nextLine();

		System.out.print("\t실기 점수를 입력해주세요 : ");
		String pscore = scan.nextLine();

		System.out.print("\t출결 점수를 입력해주세요 : ");
		String ascore = scan.nextLine();
		
		list = manageScoredao.list2(tSeq);
		for (VwStudentTestScoreDTO dto : list) {
			if (dto.getStuSeq().equals(stunum)) {
				if(wscore.equals("")) {
					wscore = dto.getWrittenScore();
				}
				if(pscore.equals("")) {
					pscore = dto.getPracticalScore();
				}
				if(ascore.equals("")) {
					ascore = dto.getAttendanceScore();
				}
			}
		} // 엔터 입력시 전에 값이 들어가게 하는 문장

		TestScoreManagementDAO dao = new TestScoreManagementDAO();

		dao.subScoreEdit(subnum, stunum, wscore, pscore, ascore);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[학생번호]\t[학생명]\t\t[필기]\t\t[실기]\t\t[출결]\t\t  [상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		list = manageScoredao.list(tSeq, subnum);
		
		for (VwStudentTestScoreDTO dto : list) {
			if (dto.getStuSeq().equals(stunum)) {
				System.out.printf("\t    %s\t\t %s\t\t\t  %s\t\t  %s\t\t  %s\t\t  %s\n", dto.getStuSeq(),
						dto.getStudentName(), dto.getWrittenScore(), dto.getPracticalScore(), dto.getAttendanceScore(),
						dto.getStudentState());
				System.out.print(
						"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");
			}
		}

		System.out.println("\t 성적 수정이 완료되었습니다.");
		pause();
	} // 특정과목 성적입력 메서드 End


	/**
	 * 특정과목에 대한 정보를 조회하는 메서드
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
	 * 진행을 잠시 막기위한 pause 메서드입니다.
	 */
	public void pause() {

		System.out.print("\t진행하시려면 Enter를 눌러주세요..");
		scan.nextLine();

	}

}
