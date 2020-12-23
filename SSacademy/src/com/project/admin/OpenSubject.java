package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dao.OpenCourseDAO;
import com.project.dao.OpenSubjectDAO;

/**
 * @author jenny
 *
 */
public class OpenSubject {
	
	private static Scanner scan;
	private OpenCourseDAO oc;
	private OpenSubjectDAO os;
	
	static {
		scan = new Scanner(System.in);
	}
	
	//생성자
	public OpenSubject() {
		
		oc = new OpenCourseDAO();
		os = new OpenSubjectDAO();
	
	}
	
	public void start() {
		
		System.out.println("\t┌───────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 개설과목조회\t\t\t│");
		System.out.println("\t│\t\t2. 개설과목등록\t\t\t│");
		System.out.println("\t│\t\t3. 개설과목수정\t\t\t│");
		System.out.println("\t│\t\t4. 개설과목삭제\t\t\t│");
		System.out.println("\t└───────────────────────────────────────────────┘");
		System.out.print("\t█ 입력 : ");
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			openSubjectView();
		} else if (num.equals("2")) {
			openSubjectAdd();
		} else if (num.equals("3")) {
			openSubjectEdit();
		} else if (num.equals("4")) {
			openSubjectDelete();
		} else {
			pause();
		}
		
	}
	


	private void openSubjectDelete() {
		// TODO Auto-generated method stub
		
	}

	private void openSubjectEdit() {
		// TODO Auto-generated method stub
		
	}

	private void openSubjectAdd() {
		// TODO Auto-generated method stub
		
	}

	//TODO 자리수 맞추기
	//A-004 전체개설과목 조회
	public void openSubjectView() {
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t개설과목조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│[번호]\t[과목명]\t\t\t\t\t[교재명]\t\t\t\t[교사명]\t[시작일]\t\t[종료일]\t\t│");
		System.out.println("\t└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
		ArrayList<OpenSubjectListDTO> list = os.OpenSubjectList();
		
		for (OpenSubjectListDTO dto : list) {
			
			int nameLength = checkTitle(dto.getSubjectName(), 50);
			int bookLength = checkTitle(dto.getBookName(), 40);
			
			System.out.printf("\t%-8s" 
							+ "%-" + nameLength +"s" 
							+ "%-" + bookLength + "s" 
							+ "%-4s"
							+ " \t%-5s"
							+ " \t\t%s\n"
							, dto.getSeqOpenSubject()
							, dto.getSubjectName()
							, dto.getBookName()
							, dto.getTeacherName()
							, dto.getStartDate()
							, dto.getEndDate());
		}
		
		System.out.println("\t──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
	
	}
	
	private void pause() {
		// TODO Auto-generated method stub
		
	}
	
	
	private int checkTitle(String str, int length) {

		int result = length;
		for (int i = 0; i < str.length(); i++) {
			char c1 = str.charAt(i);
			if (c1 >= '가' && c1 <= '힣') {
				result--;
			}
		}
		
		return result;

	}
}
