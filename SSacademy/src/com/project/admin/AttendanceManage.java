package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.AllOpenCourseDAO;
import com.project.dao.AttendanceDAO;
import com.project.dto.AllOpenCourseDTO;
import com.project.dto.PeriodAttendListDTO;
import com.project.dto.ViewStudentDTO;

/**
 * 관리자 모드의 모든 출결관리 기능을 담당하는 클래스이다.
 * @author 김다은
 *
 */
public class AttendanceManage {
	
	private static Scanner scan = new Scanner(System.in);
	private AdminView view;
	private AllOpenCourseDAO aocdao;
	private AttendanceDAO adao;
	
	
	public AttendanceManage() {
		view = new AdminView();
		this.aocdao = new AllOpenCourseDAO();
		this.adao = new AttendanceDAO();
	}
	
	
	//임시메인
	public static void main(String[] args) {
		
		AttendanceManage am = new AttendanceManage();
		am.attendanceMain();
		
	}
	
	/**
	 * 출결관리 메인 메서드이다.
	 */
	public void attendanceMain() {
		
		view.attendanceHeader(); //출결 관리 헤더
		
		ArrayList<AllOpenCourseDTO> list = aocdao.allOpenCourseList();
		view.allOpenCourseList(list);
		
		System.out.println("\t\t출결 정보 조회를 원하는 개설 과정 번호를 입력해주세요.\n");
		System.out.print("\t█ 개설과정 번호 : ");
		String seqOpenCourse = scan.nextLine();
		
		ArrayList<ViewStudentDTO> aslist = adao.attStudentList(seqOpenCourse);
		view.allStudentList(aslist);
		
		System.out.println("\t\t출결 정보 조회를 원하는 교육생의 교육생 번호를 입력해주세요.\n");
		System.out.print("\t█ 교육생 번호 : ");
		String seqStudent = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.attendanceMenu(); //출결 관리 메뉴
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				searchPeriod(seqOpenCourse, seqStudent);
				pause();
			} else if (sel.equals("2")) {
				updateAttendState(seqOpenCourse, seqStudent);
				pause();
			} else {
				loop = false;
			}
			
		}
		
	}
	
	private static void pause() {
		System.out.println("\n\t\t이전 페이지로 가시려면 엔터를 눌러주세요.");
		scan.nextLine();
	}//pause()
	
	
	/**
	 * 출결상태를 수정하는 메서드이다.
	 * @param seqOpenCourse	개설과정 번호
	 * @param seqStudent	교육생 번호
	 */
	private void updateAttendState(String seqOpenCourse, String seqStudent) {
		
		searchPeriod(seqOpenCourse, seqStudent);
		
		view.editAttedanceHeader(); //수정 헤더
		
		System.out.println("\t\t출결 상태를 수정할 날짜를 입력해주세요.\n");
		System.out.print("\t█  년도 (ex. 2021) : ");
		String year = scan.nextLine();
		System.out.print("\t█  월 (ex. 01) : ");
		String month = scan.nextLine();
		System.out.print("\t█  일 (ex. 01) : ");
		String day = scan.nextLine();
		
		String date = year + "-" + month + "-" + day;
		
		System.out.println("\t\t수정할 출결상태를 입력해주세요.\n");
		System.out.print("\t█  출결 상태 (ex. 병가) : ");
		String attendState = scan.nextLine();
		
		int result = adao.updateAttendState(seqStudent, date, attendState);
		
		view.updateResult(result);
	}

	/**
	 * 출결조회 시 원하는 기간의 출결 목록을 조회 할 수 있다.
	 * @param seqOpenCourse
	 * @param seqStudent
	 */
	public void searchPeriod(String seqOpenCourse, String seqStudent) {
		
		view.searchPeriodHeader(); //기간별 출결조회 헤더
		
		System.out.println("\t\t구간 시작 날짜를 입력해주세요. (형식 : 2021-01-01)\n");
		System.out.print("\t█  시작 년도 (ex. 2020) : ");
		String syear = scan.nextLine();
		System.out.print("\t█  시작 월 (ex. 12) : ");
		String smonth = scan.nextLine();
		System.out.print("\t█  시작 일 (ex. 25) : ");
		String sday = scan.nextLine();
		
		String startDate = syear + "-" + smonth + "-" + sday;
		
		System.out.println();
		System.out.println("\t\t구간 종료 날짜를 입력해주세요. (형식 : 2021-01-01)\n");
		System.out.print("\t█  종료 년도 (ex. 2021) : ");
		String eyear = scan.nextLine();
		System.out.print("\t█  종료 월 (ex. 02) : ");
		String emonth = scan.nextLine();
		System.out.print("\t█  종료 일 (ex. 02) : ");
		String eday = scan.nextLine();
		
		String endDate = eyear + "-" + emonth + "-" + eday;
		System.out.println();
		
		ArrayList<PeriodAttendListDTO> list = adao.attPeriodList(seqOpenCourse, seqStudent, startDate, endDate);
		view.attendanceList(list);
	}
	

}
