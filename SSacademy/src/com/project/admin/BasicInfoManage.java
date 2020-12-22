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
	
	private static Scanner scan = new Scanner(System.in);;
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
		
		boolean loop = true;
		
		while (loop) {
			
			view.basicInfoMenu(); //기초 정보 관리 메뉴
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				basicCourseinfo();
			} else if (num.equals("2")) {
				
			} else if (num.equals("3")) {
				
			} else if (num.equals("4")) {
				
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}
		}
	}
	
	/**
	 * 기초과정정보 조회, 추가, 수정, 삭제 메뉴 분기 메서드이다.
	 */
	private void basicCourseinfo() {
		
		boolean loop = true;
		
		while (loop) {
			
			view.courseInfoMenu();	//과정 정보 관리 메뉴(CRUD)
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				view.courseListHeader();
				courseList();
			} else if (num.equals("2")) {
				addCourseInfoMenu();
			} else if (num.equals("3")) {
				updateCourse();
			} else if (num.equals("4")) {
				deleteCourse();
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}//if	
			
		}//while
		
	}

	
	/**
	 * 과정기초정보를 조회하는 메서드이다. -> 과정소개는 제외
	 */
	//과정정보 목록 조회 헤더는 따로 분리시켜 놓았다. 수정, 삭제에서 재사용함
	private void courseList() {
		
		view.courseListHeader2();
		
		ArrayList<BasicCourseInfoDTO> list = bcidao.courseList();
		
		for(BasicCourseInfoDTO dto : list) {
			System.out.printf("\t%4s\t%-35s\t%7s일\n"
								, dto.getSeqBasicCourseInfo()
								, dto.getName()
								, dto.getPeriod());
			System.out.println("\t───────────────────────────────────────────────────────────────────────────");			
		}
	
		//pause();
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
			
			view.chooseAddOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addCourseInfo(bcidto);
				return ;
			} else {
				loop = false;
			}
		
		}//while
		
		//pause();
	}

	
	/**
	 * 새로운 과정기초정보를 추가하는 메서드이다.
	 */
	private void addCourseInfo(BasicCourseInfoDTO bcidto) {

		int result = bcidao.addCourse(bcidto);
		view.addResult(result);
		
	}

	
	/**
	 * 기존 과정정보를 수정하는 메서드이다.
	 */
	private void updateCourse() {
		
		view.updateCourseHeader();
		
		courseList(); //전체과정
		
		System.out.print("\n\t█ 과정번호 : ");
		String seqBasicCourseInfo = scan.nextLine();
		
		BasicCourseInfoDTO dto = bcidao.get(seqBasicCourseInfo);
		
		System.out.println();
		System.out.println("\t* 과정번호 : " + dto.getSeqBasicCourseInfo());
		System.out.println("\t* 과정이름 : " + dto.getName());
		System.out.println("\t* 과정기간 : " + dto.getPeriod());
		System.out.println("\t* 과정소개 : " + dto.getInfo());
		System.out.println("\n");
		
		System.out.println("\t\t  수정을 원치 않는 항목은 엔터를 입력하세요.\n");
		
		System.out.print("\t█ 수정할 과정이름 : ");
		String name = scan.nextLine();
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t█ 수정할 과정기간 : ");
		String period = scan.nextLine();
		if (period.equals("")) {
			period = dto.getPeriod();
		}
		
		System.out.print("\t█ 수정할 과정소개 : ");
		String info = scan.nextLine();
		if (info.equals("")) {
			info = dto.getInfo();
		}
		
		BasicCourseInfoDTO dto2 = new BasicCourseInfoDTO();
		
		dto2.setSeqBasicCourseInfo(seqBasicCourseInfo);
		dto2.setName(name);
		dto2.setPeriod(period);
		dto2.setInfo(info);
		
		int result = bcidao.updateCourse(dto2);
		
		view.updateResult(result);
		
		pause();
	}


	
	/**
	 * 과정정보를 삭제하는 메서드이다.
	 */
	private void deleteCourse() {
		
		view.deleteCourseHeader();
		
		courseList(); //전체과정
		
		System.out.print("\t█ 삭제할 과정번호 : ");
		String seqBasicCourseInfo = scan.nextLine();
		
		int result = bcidao.deleteCourse(seqBasicCourseInfo);
		
		view.deleteResult(result);
		
		pause();
	}

	
	private static void pause() {
		System.out.println("\n\t\t이전 페이지로 가시려면 엔터를 눌러주세요.");
		scan.nextLine();
	}

}
