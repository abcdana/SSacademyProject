package com.project.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.dao.ScholarshipDAO;
import com.project.dao.TopStudentDAO;
import com.project.dto.ScholarshipDTO;
import com.project.dto.TeacherDTO;
import com.project.dto.TopStudentDTO;

public class TopStudent {
	
	static AdminView view = new AdminView();
	static ScholarshipDAO dao_p = new ScholarshipDAO();
	static TeacherDTO tdto = new TeacherDTO();
	static Scanner scanner = new Scanner(System.in);
	static Connection conn = null;
	static Statement stat = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;

	
	/**
	 * 우수 훈련생 / 우수훈련생 포상 목록을 조회할수 있는 메뉴를 출력하는 메소드
	 * @param tdto
	 */
	public static void TopStudent(TeacherDTO tdto) {

		int listT;
		TopStudent.tdto=tdto;
		
		while(true) {
			
			view.menu_topStd();
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){ //우수 훈련생 목록 조회
				listT=1;
				break;
			}else if(data.equals("2")){ //우수 훈련생 포상 목록 조회
				listT=2;
				break;
			}else {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
		
		if (listT==1) {
			while(true) {
				view.menu_topStd_prize(1);
				
				System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
				String data = scanner.nextLine();
				
				if(data.equals("1")){ //우수 훈련생 목록 조회
					top_student_list();
				}else if(data.equals("2")){ //우수 훈련생 등록
					top_student_add();
				}else if(data.equals("3")){ //우수 훈련생 삭제
					top_student_remove();
				}else if(data.equals("0")){ //뒤로가기
					TeacherController AdCon = new TeacherController(tdto);
					AdCon.teacherMain();
				}else {
					System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
				}
			}
		}else if(listT==2) {
			while(true) {
				view.menu_topStd_prize(2);
				
				System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
				String data = scanner.nextLine();
				
				if(data.equals("1")){ //포상 목록 조회
					top_prize_list();
				}else if(data.equals("2")){ //포상 등록
					top_prize_add();
				}else if(data.equals("3")){ //포상 수정
					top_prize_mod();
				}else if(data.equals("4")){ //포상 삭제
					top_prize_remove();
				}else if(data.equals("0")){ //뒤로가기
					TeacherController AdCon = new TeacherController(tdto);
					AdCon.teacherMain();
				}else {
					System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
				}
			}
		}
	}

	/**
	 * 우수훈련생 목록을 조회할수 있는 메소드
	 */
	private static void top_student_list() {
		TopStudentDAO dao = new TopStudentDAO();
		
		System.out.println();
		ArrayList<TopStudentDTO> list = dao.getTopStudent(); //정보 리스트를 저장하는 메소드
		dao_p.printStudentInfoList(list); //정보 리스트를 출력하는 메소드
		if (list.size()==0) {
			System.out.println("\t\t※ 정보가 없습니다.");
		}
		System.out.println();
		System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
		scanner.nextLine();
	}

	/**
	 * 우수훈련생을 추가할 수 있는 메소드
	 */
	private static void top_student_add() {
		dao_p.addTopStudent();
		TopStudent(tdto);
	}

	/**
	 * 우수훈련생을 삭제할 수 있는 메소드
	 */
	private static void top_student_remove() {
		dao_p.removeTopStudent();
		TopStudent(tdto);
	}

	/**
	 * 혜택 목록을 출력하는 메소드
	 */
	private static void top_prize_list() {
		ArrayList<ScholarshipDTO> list = dao_p.getScholarshipList();
		dao_p.printScholarshipList(list);
		TopStudent(tdto);
	}

	/**
	 * 혜택을 추가하는 메소드
	 */
	private static void top_prize_add() {
		dao_p.addScholarship();
		TopStudent(tdto);
	}

	/**
	 * 혜택을 수정하는 메소드
	 */
	private static void top_prize_mod() {
		dao_p.modScholarship();
		TopStudent(tdto);
	}

	/**
	 * 혜택을 삭제하는 메소드
	 */
	private static void top_prize_remove() {
		dao_p.removeScholarship();
		TopStudent(tdto);
	}
	
	
}







