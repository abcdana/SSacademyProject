package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.admin.AttendanceManage;
import com.project.dao.AllOpenCourseDAO;
import com.project.dao.AttendanceDAO;
import com.project.dto.TeacherDTO;
import com.project.dto.ViewStudentDTO;
import com.project.teacher.dto.TeacherCourseListDTO;
/**
 * 교사 모드의 출결 조회 기능을 담당하는 클래스이다.
 * @author 김다은
 *
 */
public class CheckAttendanceList {
	
	private static Scanner scan = new Scanner(System.in);
	private AdminView aview;
	private TeacherView view;
	private AttendanceManage am;
	private AllOpenCourseDAO aocdao;
	private AttendanceDAO adao;
	private TeacherDTO tdto;
	
	public CheckAttendanceList(TeacherDTO tdto) {
		aview = new AdminView();
		view = new TeacherView();
		am = new AttendanceManage();
		aocdao = new AllOpenCourseDAO();
		adao = new AttendanceDAO();
		this.tdto = tdto;
	}

	/**
	 * 교사모드의 출결 조회 메인 메서드이다.
	 * 계정 접속한 교사가 진행했던 과정 목록들을 보여주고
	 * 그 과정에 해당하는 교육생들의 특정기간 출결 목록을 조회할 수 있다.
	 */
	public void attendanceMain() {
		
		aview.checkAttendanceHeader(); //출결 조회 헤더
				
		ArrayList<TeacherCourseListDTO> tclist = aocdao.allOpenCourseListbyT(tdto.getSeqTeacher());
		view.allCourseList(tclist);
		
		boolean loop = true;
		while (loop) {

			view.attendanceMenu();

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				searchPeriod();	//기간별 출결조회
			} 
			else {
				loop = false;
			}
		}
	}

	
	/**
	 * 특정 개설과정을 수강하는 특정 교육생의 특정 기간 출결조회 메서드이다.
	 */
	private void searchPeriod() {

		aview.searchPeriodHeader();
		
		System.out.println("\t\t출결 정보 조회를 원하는 개설 과정 번호를 입력해주세요.\n");
		System.out.print("\t█ 개설과정 번호 : ");
		String seqOpenCourse = scan.nextLine();
		
		ArrayList<ViewStudentDTO> aslist = adao.attStudentList(seqOpenCourse);
		aview.allStudentList(aslist);
		
		System.out.println("\t\t출결 정보 조회를 원하는 교육생의 교육생 번호를 입력해주세요.\n");
		System.out.print("\t█ 교육생 번호 : ");
		String seqStudent = scan.nextLine();
		
		am.searchPeriod(seqOpenCourse, seqStudent);
		
	}
	
}
