package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dto.TeacherDTO;
import com.project.dto.VwSubjectInquiryDTO;
import com.project.dto.VwSubjectScoreInquiryDTO;

public class TeacherController {

	private Scanner scan;
	private TeacherView view;
	private TeacherDTO tdto;
	private TestScoreManagement tsm;
	private static String tSeq;
	private TestManagement tm;
	private TeacherTestQuestion tsq;

	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다.
	 */
	public TeacherController(TeacherDTO dto) {
		view = new TeacherView();
		scan = new Scanner(System.in);
		this.tdto = dto;
		tsm = new TestScoreManagement(dto);
		tSeq = dto.getSeqTeacher();
		tm = new TestManagement(dto);
		tsq = new TeacherTestQuestion();

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
				// System.out.println("- 강의 스케줄을 조회할 수 있다. - 박지현"); - 완료
				ScheduleManage sm = new ScheduleManage();
				sm.scheduleStart();
				// break;
			} else if (sel.equals("2")) {
				// System.out.println("- 출결 관리 및 출결 조회를 할 수 있다. - 김다은"); - 완료
				CheckAttendanceList cal = new CheckAttendanceList(tdto);
				cal.attendanceMain();
				// break;
			} else if (sel.equals("3")) {
				// System.out.println("- 배점 관리 및 조회를 할 수 있다. - 조성진"); - 완료
				TestManagementMain();
				break;
			} else if (sel.equals("4")) {
				// System.out.println("- 성적 관리 및 조회를 할 수 있다. - 조성진"); - 완료
				TestScoreManagementMain();
				break;
			} else if (sel.equals("5")) {
				// System.out.println("- 교사평가를 조회할 수 있다. - 김주혁"); - 완료
				TeacherEvaluationView teacherEvaluation = new TeacherEvaluationView(tdto);
				teacherEvaluation.main();
				// break;
			} else if (sel.equals("6")) {
//				System.out.println("- 우수훈련생 여부를 확인할 수 있다. --임채원");
				TopStudent.TopStudent(tdto);
				// break;
			} else if (sel.equals("7")) {
				System.out.println("7.	성적 관리 - 조성진");
				// break;
			} else if (sel.equals("0")) {
				// 로그 아웃
				System.out.println("\n\t\t**로그 아웃 되었습니다.**");
				check = false;
			} else {
				// check = false;
				System.out.println("\n\t\t※ 잘못된 선택입니다.");
				System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");
				System.out.println();
			}

		}

	}

	/**
	 * 배점관리 메인입니다.
	 */
	public void TestManagementMain() {

		boolean loop = true;

		while (loop) {

			view.TestManagementMenu();
			String num = scan.nextLine();

			switch (num) {
			case "1":
				tm.subScoreInquiry(tSeq);
				break;
			case "2":
				tm.subTestManagement(tSeq);
				break;
			case "3":
				tsq.TestQuestionInsert();
				break;
			case "4":
				tm.subWrittenDateUpdate(tSeq);
				break;
			case "5":
				tm.subPracticalDateUpdate(tSeq);
				break;
			case "6":
				tsq.TestQuestionInquiry();
				break;

			default:
				loop = false;
				System.out.println("배점 관리를 종료합니다.");
				break;
			}
		}

	}
	
	/**
	 * 성적관리 메인입니다.
	 */
	public void TestScoreManagementMain() {

		boolean loop = true;

		while (loop) {

			view.TestScoreManagementMenu();
			String num = scan.nextLine();

			switch (num) {
			case "1":
				tsm.subScoreInquiry(tSeq);
				break;
			case "2":
				tsm.subScoreEdit(tSeq);
				break;
			case "3":
				tsm.stuScoreEdit(tSeq);
				break;
			default:
				loop = false;
				System.out.println("성적 관리를 종료합니다.");
				break;
			}
		}

	}

}
