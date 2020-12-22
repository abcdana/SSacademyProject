package com.project.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenCourseStudentDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dao.OpenCourseDAO;
import com.project.dao.OpenSubjectDAO;
import com.project.dto.BasicCourseInfoDTO;
import com.project.dto.OpenCourseDTO;

/**
 * @author jenny
 *
 */
public class OpenCourse {
	
	private static Scanner scan;
	private OpenCourseDAO oc;
	private OpenSubjectDAO os;
	private AdminView adView;
	
	static {
		scan = new Scanner(System.in);
	}
	
	//생성자
	public OpenCourse() {
		
		oc = new OpenCourseDAO();
		os = new OpenSubjectDAO();
		adView = new AdminView();
		
	}
	
	public void start() {
		
		adView.start();
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			OpenCourseList();  //개설과정조회
		} else if (num.equals("2")) {
			OpenCourseReg();	//개설과정등록
		} else if (num.equals("3")) {
			OpenCourseEdit();	//개설과정수정
		} else if (num.equals("4")) {
			OpenCourseDelete();	//개설과정삭제
		} else {
			pause();
		}
		
	}
	

	//A-003-1 전체개설과정 조회
	public void OpenCourseList() {
		
		ArrayList<OpenCourseListDTO> list = oc.OpenCourseList();
		//전체개설과정조회 뷰
		adView.OpenCourseView(list);
		
		//과정번호 입력
		String num = scan.nextLine();
		
		if(num.equals("0")) {
			pause();
		} else {
			//특정과정조회
			SpecificCourseManage(num);
		}
		
	}
		
	
	
	//A-003-1-1 특정개설과정 조회(1)
	public void SpecificCourseManage(String num) {
	
		ArrayList<OpenCourseListDTO> list = oc.OpenCourseList();
		
		//특정개설과정 조회 뷰
		String name = adView.OpenSpecificCourseView(list, num);
		
		String num2 = scan.nextLine();
		System.out.println();
		
		if(num2.equals("1")) {
			SpecificSubject(num, name);	//특정개설 -> 과목조회
		} else if(num2.equals("2")) {
			SpecificStudent(num, name);	//특정개설 -> 교육생조회
		} else {
			pause();
		}
	
	
	}
	
	
	//A-003-1-1 특정개설과정 과목조회(2)
	public void SpecificSubject(String seqOpenCourse, String openCourseName) {
									//과정번호, 과정명
		//과정번호 넣고 과목리스트 호출
		ArrayList<OpenSubjectListDTO> list = os.SpecificOpenSubject(seqOpenCourse);
		adView.SpecificSubjectView(list, openCourseName);
	}
			

	//A-003-1-1 특정개설과정 교육생조회
	public void SpecificStudent(String seqOpenCourse, String openCourseName) {
									//과정번호, 과정명
		//과정번호 넣고 교육생리스트 호출
		ArrayList<OpenCourseStudentDTO> list = oc.OpenCourseStudent(seqOpenCourse);
		adView.SpecificStudentView(list, openCourseName);
		
		
		
	}
	
	
	
	
	/**
	 * 개설과정등록
	 */
	public static void OpenCourseReg() {
		
		
		
	}
	
	public static void OpenCourseEdit() {
		
	}
	
	public static void OpenCourseDelete() {
		
	}

	private void pause() {
		
		System.out.println("게속 하시려면 엔터를 누르세요.");
		String num = scan.nextLine();
	}
	
}
