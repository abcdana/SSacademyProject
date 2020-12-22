package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.AvailableSubjectDAO;
import com.project.dao.BasicSubjectDAO;
import com.project.dao.TeacherDAO;
import com.project.dto.BasicSubjectDTO;
import com.project.dto.TeacherDTO;

public class TeacherAccountManagement {

	private Scanner scan; //사용자 입력
	private TeacherDAO tdao; //교사 DAO
	private AvailableSubjectDAO asdao; //가능과목 DAO
	private BasicSubjectDAO bsdao; //과목기초정보 DAO
	
	public TeacherAccountManagement() {
		
		scan = new Scanner(System.in);
		tdao = new TeacherDAO();
		asdao = new AvailableSubjectDAO();
		bsdao = new BasicSubjectDAO();
		
	}
	
	public void main() {
		
		boolean loop = true;
		
		while (loop) {
			
			//교사 전체 목록 보기(title)
			viewTeacherAccountList();

			//교사 계정 관리 메뉴 출력
			menu();
						
			//선택
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//신규 교사 등록
				addTeacher();
			} else if (sel.equals("2")) {
				//교사 정보 수정
				editTeacher();
			} else if (sel.equals("3")) {
				//교사 정보 삭제
			} else if (sel.equals("4")) {
				//특정 교사 검색
			} else if (sel.equals("0")) {
				//뒤로 가기
				loop = false;
			} else {
				//잘못 입력
				wrongInput();
				pause();
			}
			
		} //while
		
		
		
	} //start

	private void editTeacher() {
		
		System.out.print("\t█ 수정할 교사 번호를 입력하세요. : ");
		String seqTeacher = scan.nextLine();
		
		//수정할 교사의 정보 가져오기
		TeacherDTO tdto = tdao.get(seqTeacher);
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[번호]\t[이름]\t[주민등록번호]\t[전화번호]");
		System.out.printf("\t  %s\t%s\t%s\t%s\n"
								, tdto.getSeqTeacher()
								, tdto.getName()
								, tdto.getSsn()
								, tdto.getTel());
		
		System.out.println();
		System.out.println("\t★★수정을 하지 않을 항목은 그냥 엔터를 눌러주세요★★");
		
		System.out.print("\t수정할 이름 : ");
		String name = scan.nextLine();
		
		if (name.equals("")) {
			name = tdto.getName();
		}
		
		System.out.print("\t수정할 주민등록번호 : ");
		String ssn = scan.nextLine();
		
		if (ssn.equals("")) {
			ssn = tdto.getSsn();
		}
		
		System.out.print("\t수정할 전화번호 : ");
		String tel = scan.nextLine();
		
		if (tel.equals("")) {
			tel = tdto.getTel();
		}
		
		TeacherDTO newtdto = new TeacherDTO();
		
		newtdto.setSeqTeacher(seqTeacher);
		newtdto.setName(name);
		newtdto.setId(tdto.getId());
		newtdto.setSsn(ssn);
		newtdto.setTel(tel);
		
		int result = tdao.edit(newtdto);
		
		if (result > 0) {
			System.out.println("\t교사 정보 수정을 성공하였습니다.");
		} else {
			System.out.println("\t교사 정보 수정을 실패하였습니다.");
			pause();
			return;
		}

		System.out.println("\t가능 과목을 수정합니다.");
		pause();
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t가능 과목 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		ArrayList<BasicSubjectDTO> list = bsdao.get(seqTeacher);
		System.out.println("\t[과목 번호]\t[과목 이름]");
		
		for (BasicSubjectDTO dto : list) {
			System.out.printf("\t   %s\t\t%s\n"
								, dto.getSeqBasicSubject()
								, dto.getName());
			
		}
		
		System.out.println();
		System.out.println("\t★★수정을 하지 않을 경우 그냥 엔터를 눌러주세요★★");	
		System.out.print("\t수정 전 과목번호 : ");
		String oldSeq = scan.nextLine();
		
		//TODO 기초과목정보 목록 출력
		
		System.out.print("\t수정 후 과목번호 : ");
		String newSeq = scan.nextLine();
		
		int result2 = 0;
		
		if (!oldSeq.equals("") || !newSeq.equals("")) {
			result2 = asdao.edit(seqTeacher, oldSeq, newSeq);
		} else {
			result2 = 1;
		}
		
		if (result2 > 0) {
			System.out.println("\t강의 가능 과목 수정을 성공하였습니다.");
			pause();
		} else {
			System.out.println("\t강의 가능 과목 수정을 실패하였습니다.");
			pause();
		}
		
	} //editTeacher

	private void viewTeacherAccountList() {
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		ArrayList<TeacherDTO> list = tdao.list();
		
		System.out.println("\t[번호]\t[이름]\t[주민등록번호]\t[전화번호]");
		
		for (TeacherDTO tdto : list) {
			System.out.printf("\t  %s\t%s\t%s\t%s\n"
					, tdto.getSeqTeacher()
					, tdto.getName()
					, tdto.getSsn()
					, tdto.getTel());
		}
	} //viewTeacherAccountList

	private void addTeacher() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 등록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.print("\t이름 : ");
		String name = scan.nextLine();
		
		System.out.print("\t주민등록번호 : ");
		String ssn = scan.nextLine();
		
		System.out.print("\t전화번호 : ");
		String tel = scan.nextLine();
		
		//교사DTO 생성
		TeacherDTO tdto = new TeacherDTO();
		tdto.setName(name);
		tdto.setSsn(ssn);
		tdto.setTel(tel);
		
		//교사 테이블에 교사 추가하기
		int result = tdao.add(tdto);
		
		if (result > 0) {
			System.out.println("\t교사 등록에 성공하였습니다.");
		} else {
			System.out.println("\t교사 등록에 실패하였습니다.");
			pause();
			return;
		}

		System.out.println("\t가능 과목을 등록합니다.");
		pause();
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t가능 과목 등록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		//TODO 과목 목록 출력 추가하기 -> 과목기초정보 DAO에서 받아오기
		
		System.out.println("\t가능 과목 번호를 ,로 구분하여 입력해주세요.");
		System.out.println("\tex. 1,2,3,5,7");
		System.out.print("\t가능 과목 번호 : ");
		String[] availableSubjectList = scan.nextLine().split(","); //가능과목번호 저장하기
		
		//교사번호, 과목번호 -> 가능 과목 등록
		int result2 = asdao.add(tdto.getSeqTeacher(), availableSubjectList);
		
		if (result2 > 0) {
			System.out.println("\t과목 등록에 성공하였습니다.");
			pause();
		} else {
			System.out.println("\t과목 등록에 실패하였습니다.");
			pause();
		}
		
	} //addTeacher

	private void pause() {
		
		System.out.println("\t계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		
	} //pause

	private void wrongInput() {
		
		System.out.println("\n\t\t※ 잘못된 선택입니다.");
		System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");

	} //wrongInput

	private void menu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t       1. 신규 교사 등록\t\t\t  │");
		System.out.println("\t│\t\t\t       2. 교사 정보 수정\t\t\t  │");
		System.out.println("\t│\t\t\t       3. 교사 정보 삭제\t\t\t  │");
		System.out.println("\t│\t\t\t       4. 특정 교사 검색\t\t\t  │");
		System.out.println("\t│\t\t\t       0. 뒤로 가기\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	} //menu
	
	

} //TeacherAccountManagement