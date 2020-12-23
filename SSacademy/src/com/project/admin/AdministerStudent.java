package com.project.admin;

import java.util.Scanner;

import com.project.dao.StudentDAO;
import com.project.dto.AdminDTO;

public class AdministerStudent {
	
	static Scanner scanner = new Scanner(System.in);
	static AdminView view = new AdminView();
	
	public static void AdministerStudent(AdminDTO adto){
		
		view.menu_AdministerStudent();
		
		while(true) {
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){
				AdministerStudentSearch(adto);
			}else if(data.equals("2")){
				
			}else if(data.equals("3")){
				
			}else if(data.equals("4")){
				
			}else if(data.equals("5")){
				AdminController AdCon = new AdminController(adto);
				AdCon.adminMain();
			}else {
				System.out.println("\t█ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
		
	}
	
	public static void AdministerStudentSearch(AdminDTO adto){
		
		StudentDAO dao = new StudentDAO();
		
		view.menu_adminStd_search();
		
		while(true) {
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){
				
			}else if(data.equals("2")){
				
			}else if(data.equals("3")){
				
			}else if(data.equals("4")){
				dao.getStudentAll();
//				조회에서 수정/삭제기능 추가 = 나중에
				System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
				scanner.nextLine();
				AdministerStudent(adto);
			}else {
				System.out.println("\t█ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
	}
	
}










