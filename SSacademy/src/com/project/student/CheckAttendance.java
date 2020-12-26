package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.admin.AttendanceManage;
import com.project.dao.AllOpenCourseDAO;
import com.project.dao.AttendanceDAO;
import com.project.dto.PeriodAttendListDTO;
import com.project.dto.StudentDTO;
import com.project.student.dto.StudentCourseListDTO;
import com.project.teacher.TeacherView;
/**
 * 교육생 모드의 출결 기능을 담당하는 클래스이다.
 * 입실과 퇴실 입력 기능과 특정기간동안의 본인 출결 리스트를 조회하는 기능을 포함한다.
 * @author 김다은
 *
 */
public class CheckAttendance {


	private static Scanner scan = new Scanner(System.in);
	private AdminView aview;
	private TeacherView tview;
	private StudentView view;
	private StudentDTO sdto;
	private AttendanceManage am;
	private AllOpenCourseDAO aocdao;
	private AttendanceDAO adao;
	
	public CheckAttendance(StudentDTO sdto) {
		
		aview = new AdminView();
		tview = new TeacherView();
		view = new StudentView();
		am = new AttendanceManage();
		aocdao = new AllOpenCourseDAO();
		adao = new AttendanceDAO();
		this.sdto = sdto;
	}
	
	
	
	/**
	 * 교육생모드의 출결관리 메인 메서드이다.
	 */
	public void attendacneMain() {

		aview.attendanceHeader(); // 출결 관리 헤더
		
		boolean loop = true;
		while (loop) {

			view.attendanceMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				checkAttend(sdto);		//입퇴실 체크
			} else if (sel.equals("2")) {
				attendanceList();	//출결조회
			} else {
				loop = false;
			}
		}
	}
	
	
	/**
	 * 교육생 모드의 입퇴실 체크 기능을 담당하는 메서드이다.
	 */
	private void checkAttend(StudentDTO sdto) {
		
		view.checkAttend(); //입퇴실 체크 헤더
		
		String sel = scan.nextLine();
		sel = sel.toUpperCase();

		if (sel.equals("N")) {
			attendacneMain();
		} else if (sel.equals("Y")) {
			int result = adao.addAttendance(sdto.getSeqStudent());
			view.checkResult(result);
		} else {
			System.out.println("\t\t※ 잘못 입력하셨습니다.");
			checkAttend(sdto);
			
		}
		attendacneMain();
		
	}

	
	/**
	 * 교육생 모드의 출결을 조회하는 메서드이다.
	 */
	private void attendanceList() {
		
		view.attendanceList(); //출결조회 헤더 
		
		//교육생이 수강중인 과정 리스트 출력
		ArrayList<StudentCourseListDTO> result = aocdao.allOpenCourseListbyS(sdto.getSeqStudent());
		view.allCourseList(result);
		
		boolean loop = true;
		while (loop) {

			view.attendPeriodMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				allAttendanceList(sdto.getSeqStudent());   //전체 조회
			} else if (sel.equals("2")) {
				monthAttendanceList(sdto.getSeqStudent()); //월별 조회
			} else {
				loop = false;
			}
		
		}
	}


	/**
	 * 과정별 출결을 전체조회하는 메서드이다.
	 * 과정번호를 입력하면 해당 교육생이 수강하는 입력한 번호의 과정 전체 출결 기록을 보여준다. 
	 * @param seqStudent 교육생 번호
	 */
	private void allAttendanceList(String seqStudent) {

		view.totAttList();
		
		System.out.println("\t\t출결 조회를 원하시는 과정 번호를 입력해주세요.\n");
		System.out.print("\t█  과정 번호 : ");
		String seqOpenCourse = scan.nextLine();
		
		ArrayList<PeriodAttendListDTO> list = adao.attPeriodList(seqStudent, seqOpenCourse);
		aview.attendanceList(list);
		
	}


	
	/**
	 * 기간별 출결조회 메서드이다.
	 * 개설과정번호, 조회를 원하는 년도와 월을 입력하면 해당 교육생의 해당 과정, 해당 월의 출결 기록을 보여준다.
	 * @param seqStudent 교육생 번호
	 */
	private void monthAttendanceList(String seqStudent) {

		view.monthAttList();
		
		System.out.println("\t\t월별 출결 조회를 하시려면 다음 항목을 입력해주세요.\n\t\t조회를 원하시는 년도는 네자리 수, 월은 두자리 수로 입력해주세요. (ex. 2020, 01)\n");
		System.out.print("\t█  과정 번호 : ");
		String seqOpenCourse = scan.nextLine();
		
		System.out.print("\t█  년도 : ");
		String year = scan.nextLine();
		
		System.out.print("\t█  월 : ");
		String month = scan.nextLine();
		System.out.println();
		
		ArrayList<PeriodAttendListDTO> list = adao.studentAttPeriodList(seqStudent, seqOpenCourse, year, month);
		aview.attendanceList(list);
		
	}
	
	
	
}