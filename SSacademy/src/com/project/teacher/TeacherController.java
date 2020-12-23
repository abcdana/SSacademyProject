package com.project.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.dto.TeacherDTO;
import com.project.dto.VwSubjectInquiryDTO;
import com.project.dto.VwSubjectScoreInquiryDTO;
import com.project.ssacademy.DBUtil;

public class TeacherController {

	private Scanner scan;
	private TeacherView view;
	private TeacherDTO tdto;
	private TestManagementDAO managedao;
	private TestScoreManagementDAO manageScoredao;
	private static String tSeq;

	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다.
	 */
	public TeacherController(TeacherDTO dto) {
		view = new TeacherView();
		scan = new Scanner(System.in);
		this.tdto = dto;
		managedao = new TestManagementDAO();
		manageScoredao = new TestScoreManagementDAO();
		tSeq = dto.getSeqTeacher();

	}

	/**
	 * 교사 메인입니다.
	 */
	public void teacherMain() {

		System.out.printf("\n\t\t교사 %s님 SSacademy 접속을 환영합니다.", tdto.getName());
		System.out.print("\n\t\t――――――――――――――――――――――――――――――――――――――――――");

		boolean check = true;
		while (check) {

			view.menu();

			String sel = scan.nextLine();

			if (sel.equals("1")) {
				System.out.println("- 강의 스케줄을 조회할 수 있다. - 박지현");
				break;
			} else if (sel.equals("2")) {
				System.out.println("- 출결 관리 및 출결 조회를 할 수 있다. - 김다은");
				break;
			} else if (sel.equals("3")) {
				TestManagementMain();
				break;
			} else if (sel.equals("4")) {
				TestScoreManagementMain();
				break;
			} else if (sel.equals("5")) {
				System.out.println("- 교사평가를 조회할 수 있다. - 김주혁");
				break;
			} else if (sel.equals("6")) {
				System.out.println("- 우수훈련생 여부를 확인할 수 있다. --임채원");
				break;
			} else if (sel.equals("7")) {
				System.out.println("7.	성적 관리 - 조성진");
				break;
			} else {
				check = false;
				System.out.println("\n\t\t※ 잘못된 선택입니다.");
				System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");
				System.out.println();
				teacherMain();
			}

		}

	}

	public void TestManagementMain() {

		boolean loop = true;

		while (loop) {

			view.TestManagementMenu();
			String num = scan.nextLine();

			switch (num) {
			case "1":
				subEndinquiry(tSeq);
				break;
			case "2":
				break;
			case "3":
				practicalPercentEdit();
				break;
			case "4":
				writtenPercentEdit();
				break;
			case "5":
				attendancePercentEdit();
				break;
			case "6":

				break;

			default:
				loop = false;
				System.out.println("배점 관리를 종료합니다.");
				break;
			}
		}

	}

	public void TestScoreManagementMain() {

		boolean loop = true;

		while (loop) {

			view.TestScoreManagementMenu();
			String num = scan.nextLine();

			switch (num) {
			case "1":
				subScoreInquiry(tSeq);
				break;
			case "2":
				subScoreEdit(tSeq);
				break;
			case "3":

				break;
			default:
				loop = false;
				System.out.println("성적 관리를 종료합니다.");
				break;
			}
		}

	}

	private void subScoreEdit(String tSeq) {
		subScoreInquiry(tSeq);
		System.out.print("\t관리할 과목번호를 입력해주세요 : ");
		String subnum = scan.nextLine();

		ArrayList<VwSubjectScoreInquiryDTO> list = manageScoredao.list(tSeq, subnum);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[학생번호]\t[학생명]\t\t[필기]\t\t[실기]\t\t[출결]\t\t  [상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwSubjectScoreInquiryDTO dto : list) {
			System.out.printf("\t    %s\t\t %s\t\t\t  %s\t\t  %s\t\t  %s\t\t  %s\n", dto.getStuSeq(),
					dto.getStudentName(), dto.getWrittenScore(), dto.getPracticalScore(), dto.getAttendanceScore(),
					dto.getStudentState());
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}

		System.out.println();

		System.out.print("관리할 학생번호를 입력해주세요 : ");
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

		TestScoreManagementDAO dao = new TestScoreManagementDAO();

		dao.subScoreEdit(subnum, stunum, wscore, pscore, ascore);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[학생번호]\t[학생명]\t\t[필기]\t\t[실기]\t\t[출결]\t\t  [상태]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		for (VwSubjectScoreInquiryDTO dto : list) {
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
	}

	private void subScoreInquiry(String tSeq) {

		String time = "and subEnd < sysdate";
		ArrayList<VwSubjectInquiryDTO> list = managedao.list(tSeq, time);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[과목 번호]\t\t\t [과정이름]\t\t\t   [과목이름]\t\t [과목시작]\t [과목종료]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwSubjectInquiryDTO dto : list) {
			System.out.printf("\t    %s\t\t %s\t %-20s%s\t %s\n", dto.getSubSeq(), dto.getCourseName(),
					dto.getSubName(), dto.getSubStart().substring(0, 10), dto.getSubEnd().substring(0, 10));
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}
		System.out.println();
	}

	private void attendancePercentEdit() {
	}

	private void writtenPercentEdit() {
	}

	private void practicalPercentEdit() {
	}

	private void subEndinquiry(String tSeq) {

		String time = "and subEnd < sysdate";
		ArrayList<VwSubjectInquiryDTO> list = managedao.list(tSeq, time);

		// ResultSet -> 탐색 + 조작
		// ArrayList -> 탐색 + 조작

		for (VwSubjectInquiryDTO dto : list) {
			System.out.printf("%s, %s, %s, %s\n", dto.getCourseName(), dto.getSubName(), dto.getSubStart(),
					dto.getSubEnd());

		}
		System.out.println();
		pause();

	}

	private void pause() {

		System.out.print("\t진행하시려면 Enter를 눌러주세요..");
		scan.nextLine();

	}

}
