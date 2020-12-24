package com.project.student;

import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.teacher.TeacherView;

public class CheckAttendance {


	private static Scanner scan = new Scanner(System.in);
	private AdminView aview;
	private TeacherView tview;
	private StudentView view;
	
	public CheckAttendance() {
		
		aview = new AdminView();
		tview = new TeacherView();
		
	}
	
	//임시메인
	public static void main(String[] args) {
		
		CheckAttendance ca = new CheckAttendance();
		ca.attendacneMain();
		
	}
	
	
	/**
	 * 교육생모드의 출결조회 메인 메서드이다.
	 */
	private void attendacneMain() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
