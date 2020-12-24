package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.admin.AttendanceManage;
import com.project.dao.AllOpenCourseDAO;
import com.project.dao.AttendanceDAO;
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
			System.out.println("잘못 입력하셨습니다.");
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
		ArrayList<StudentCourseListDTO> sclist = aocdao.allOpenCourseListbyS(sdto.getSeqStudent());
		view.allCourseList(sclist);
		
		boolean loop = true;
		while (loop) {

			view.attendPeriodMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				view.totAttList();
					//전체 조회
			} else if (sel.equals("2")) {
				view.monthAttList();
					//월별 조회
			} else {
				loop = false;
			}
		
		}
	}
	
}