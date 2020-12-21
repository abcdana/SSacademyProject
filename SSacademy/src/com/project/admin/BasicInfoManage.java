package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.BasicCourseInfoDAO;
import com.project.dao.BookDAO;
import com.project.dao.RoomDAO;
import com.project.dto.BasicCourseInfoDTO;

/**
 * 기초정보의 조회, 등록, 수정, 삭제 기능을 포함한다.
 * 기초정보에는 과정 기초정보, 과목 기초정보, 강의실 기초정보, 과목 기초정보가 있다. 
 * @author 김다은
 *
 */
public class BasicInfoManage {
	
	private Scanner scan = new Scanner(System.in);;
	private AdminView view;
	private BasicCourseInfoDAO bcidao;
	private RoomDAO rdao;
	private BookDAO bdao;
	
	public BasicInfoManage() {
		this.bcidao = new BasicCourseInfoDAO();
		view = new AdminView();
		this.rdao = new RoomDAO();
		this.bdao = new BookDAO();
	}

	//임시 메인
	public static void main(String[] args) {
		
		BasicInfoManage bim = new BasicInfoManage();
		bim.basicInfoMain();
		
	}
	
	/**
	 * 기초 정보 관리 메뉴 분기 메서드이다.
	 */
	public void basicInfoMain() {
		
		view.basicInfoMenu(); //기초 정보 관리 메뉴
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			basicCourseinfo();
		} else if (num.equals("2")) {
			
		} else if (num.equals("3")) {
			
		} else if (num.equals("4")) {
			
		} else {
			System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
			
		}
	}
	
	/**
	 * 기초과정정보 조회, 추가, 수정, 삭제 메뉴 분기 메서드이다.
	 */
	private void basicCourseinfo() {
		
		view.courseInfoMenu();	//과정 정보 관리 메뉴(CRUD)
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			courseList();
		} else if (num.equals("2")) {
			addCourseInfoMenu();
		} else if (num.equals("3")) {
			
		} else if (num.equals("4")) {
			
		} else {
			System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
		}
	}

	
	/**
	 * 과정기초정보를 추가하는 메서드이다.
	 */
	private void addCourseInfoMenu() {
		
		view.addCourseHeader();

		System.out.print("\t█ 과정이름 : ");
		String name = scan.nextLine();
		
		System.out.print("\t█ 과정기간 : ");
		String period = scan.nextLine();
		
		System.out.print("\t█ 과정소개 : ");
		String info = scan.nextLine();
		
		BasicCourseInfoDTO bcidto = new BasicCourseInfoDTO();
		bcidto.setName(name);
		bcidto.setPeriod(period);
		bcidto.setInfo(info);
		
		boolean loop = true;
		
		while (loop) {
			
			view.chooseUpdateOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addCourseInfo(bcidto);
				return ;
			} else {
				loop = false;
			}
			
		}
	}

	/**
	 * 새로운 과정기초정보를 추가하는 메서드이다.
	 */
	private void addCourseInfo(BasicCourseInfoDTO bcidto) {

		int result = bcidao.addCourse(bcidto);
		
		view.addResult(result);
		
	}


	/**
	 * 과정기초정보를 조회하는 메서드이다.
	 */
	private void courseList() {
		
		System.out.println("과정 조회하기");
		
		ArrayList<BasicCourseInfoDTO> list = bcidao.courseList();
		
		for(BasicCourseInfoDTO dto : list) {
			System.out.printf("%s, %s, %s, %s"
								, dto.getSeqBasicCourseInfo()
								, dto.getName()
								, dto.getPeriod()
								, dto.getInfo());
		}
	
	}


}
