package com.project.student;

import java.util.Scanner;

import com.project.dto.StudentDTO;


/**
 * 교육생 컨트롤러입니다.
 * --세부사항 적기
 * @author 김다은
 *
 */
public class StudentController {

	private Scanner scan;
	private StudentView view;
	private StudentDTO sdto;
	private StudentTestScoreInquiry stsi;
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다. 
	 */
	public StudentController(StudentDTO dto) {
		
		scan = new Scanner(System.in);
		view = new StudentView();
		this.sdto = dto;
		
		
	}
	
	/**
	 * 교육생 메인입니다.
	 */
	public void studentMain() {
		
		System.out.printf("\n\t\t교육생 %s님 SSacademy 접속을 환영합니다.", sdto.getName());
		System.out.print("\n\t\t――――――――――――――――――――――――――――――――――――――――――――");
		
		boolean check = true;
		while (check) {
			
			view.menu();
		
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//System.out.println("교육생 개인 정보를 확인할 수 있다. --박지현");
//				StudentList sl = new StudentList();
//				sl.studentStart();
				//break;
				
			} else if (sel.equals("2")) {
				//System.out.println("- 출결 관리 및 출결 조회를 할 수 있다. --김다은"); - 완료
				CheckAttendance ca = new CheckAttendance(sdto);
				ca.attendacneMain();
				//break;
			} else if (sel.equals("3")) {
				//System.out.println("- 성적을 조회를 할 수있다.. --조성진");
				stsi = new StudentTestScoreInquiry();
				stsi.TestQuestionInquiry();
				//break;
			} else if (sel.equals("4")) {
				//System.out.println("- 강의 및 시설 평가를 등록, 수정, 삭제 할 수 있다. - 김주혁");
				EvaluationManagement evaluation = new EvaluationManagement(sdto);
				evaluation.main();
				//break;
			} else if (sel.equals("5")) {
				//System.out.println("- 연계 기업 채용 공고를 확인할 수 있다. --조혜승");
				CompanyInfoCheck companyInfoCheck = new CompanyInfoCheck();
				companyInfoCheck.menu();
				//break;
			} else if (sel.equals("6")) {
				//System.out.println("- 수료자 취업 현황을 확인할 수 있다.-- 조혜승");
				GetJobInfoCheck getJobInfoCheck = new GetJobInfoCheck();
				getJobInfoCheck.menu();
				//break;
			} else if (sel.equals("7")) {
//				System.out.println("- 우수훈련생 여부를 확인할 수 있다. --임채원"); -완료
				TopStudent.TopStudent(sdto);
				//break;
			} else if (sel.equals("0")) {
				//로그 아웃
				check = false;
			} else {
				//check = false;
				System.out.println("\n\t\t※ 잘못된 선택입니다.");
				System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");
				System.out.println();
			}
			
		}
		
	}
}
