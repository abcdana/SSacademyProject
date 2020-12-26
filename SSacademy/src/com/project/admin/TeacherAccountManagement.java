package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.ViewTeacherCourseDTO;
import com.project.dao.AvailableSubjectDAO;
import com.project.dao.BasicSubjectDAO;
import com.project.dao.TeacherDAO;
import com.project.dto.BasicSubjectDTO;
import com.project.dto.TeacherDTO;

/**
 * 교사 계정 관리를 담당하는 클래스
 * @author 김주혁
 *
 */
public class TeacherAccountManagement {

	private Scanner scan; //사용자 입력
	private TeacherDAO tdao; //교사 DAO
	private AvailableSubjectDAO asdao; //강의가능과목 DAO
	private BasicSubjectDAO bsdao; //과목기초정보 DAO
	
	/**
	 * 교사 계정 관리 클래스의 생성자
	 */
	public TeacherAccountManagement() {
		
		scan = new Scanner(System.in);
		tdao = new TeacherDAO();
		asdao = new AvailableSubjectDAO();
		bsdao = new BasicSubjectDAO();
		
	}
	
	/**
	 * 교사 계정 관리 클래스의 메인 메서드
	 */
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
				deleteTeacher();
			} else if (sel.equals("4")) {
				//특정 교사 검색
				searchTeacher();
			} else if (sel.equals("0")) {
				//뒤로 가기
				loop = false;
			} else {
				//잘못 입력
				wrongInput();
				pause();
			}
			
		} //while
		
		
		
	} //main
	
	
	private void viewTeacherAccountList() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t\t\t[번호]\t[이름]\t[주민등록번호]\t [전화번호]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		ArrayList<TeacherDTO> list = tdao.list();
		
		for (TeacherDTO tdto : list) {
			System.out.printf("\t\t\t  %s\t%s\t%s\t%s\n"
					, tdto.getSeqTeacher()
					, tdto.getName()
					, tdto.getSsn()
					, tdto.getTel());
			System.out.println("\t――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		}
		
	} //viewTeacherAccountList
	

	private void menu() {
		
		System.out.println();
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
	
	
	private void addTeacher() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 등록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println();
		System.out.print("\t█이름 : ");
		String name = scan.nextLine();
		
		System.out.print("\t█주민등록번호 : ");
		String ssn = scan.nextLine();
		
		System.out.print("\t█전화번호 : ");
		String tel = scan.nextLine();
		
		//교사DTO 생성
		TeacherDTO tdto = new TeacherDTO();
		tdto.setName(name);
		tdto.setSsn(ssn);
		tdto.setTel(tel);
		
		//교사 테이블에 교사 추가하기
		int result = tdao.add(tdto);
		
		if (result > 0) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t    교사 계정 등록 성공\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		} else {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t    교사 계정 등록 실패\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}

		System.out.println();
		System.out.println("\t가능 과목을 등록합니다.");
		pause();
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t가능 과목 등록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		//과목기초정보 가져오기 + 출력
		getBasicSubjectList();
		
		System.out.println();
		System.out.println("\t★★가능 과목 번호를 ,로 구분하여 입력해주세요★★");
		System.out.println("\t(ex. 1,2,3,5,7)");
		
		System.out.println();
		System.out.print("\t█가능 과목 번호 : ");
		
		String[] availableSubjectList = scan.nextLine().split(","); //가능과목번호 저장하기
		
		//교사번호, 과목번호 -> 가능 과목 등록
		int result2 = asdao.add(tdto.getSeqTeacher(), availableSubjectList);
		
		if (result2 > 0) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t    가능 과목 등록 성공\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		} else {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t    가능 과목 등록 실패\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}
		
	} //addTeacher
	
	
	private void editTeacher() {
		
		System.out.print("\t█ 수정할 교사 번호를 입력하세요. : ");
		String seqTeacher = scan.nextLine();
		
		//수정할 교사의 정보 가져오기
		TeacherDTO tdto = tdao.get(seqTeacher);
		
		//수정할 교사 번호가 없는 경우
		if (tdto == null) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t     없는 교사 번호입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 계정 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		//수정 전 교사 계정 정보 출력
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t\t\t[번호]\t[이름]\t[주민등록번호]\t [전화번호]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\t  %s\t%s\t%s\t%s\n"
								, tdto.getSeqTeacher()
								, tdto.getName()
								, tdto.getSsn()
								, tdto.getTel());
		
		System.out.println();
		System.out.println("\t★★수정을 하지 않을 항목은 그냥 엔터를 눌러주세요★★");
		
		System.out.println();
		System.out.print("\t█수정할 이름 : ");
		String name = scan.nextLine();
		
		if (name.equals("")) {
			name = tdto.getName();
		}
		
		System.out.print("\t█수정할 주민등록번호 : ");
		String ssn = scan.nextLine();
		
		if (ssn.equals("")) {
			ssn = tdto.getSsn();
		}
		
		System.out.print("\t█수정할 전화번호 : ");
		String tel = scan.nextLine();
		
		if (tel.equals("")) {
			tel = tdto.getTel();
		}
		
		//수정할 교사 정보 DTO 생성
		TeacherDTO newtdto = new TeacherDTO();
		
		newtdto.setSeqTeacher(seqTeacher);
		newtdto.setName(name);
		newtdto.setId(tdto.getId());
		newtdto.setSsn(ssn);
		newtdto.setTel(tel);
		
		int result = tdao.edit(newtdto);
		
		if (result > 0) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t    교사 정보 수정 성공\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		} else {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t    교사 정보 수정 실패\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}

		System.out.println();
		System.out.println("\t강의 가능 과목을 수정합니다.");
		pause();
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t    강의 가능 과목 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		//교사 강의 가능 과목 리스트 저장
		ArrayList<String> seqBasicSubjectList = asdao.getSeqBasicSubjectList(seqTeacher);
		
		//강의 가능 과목의 기초 정보 저장할 객체 선언
		ArrayList<BasicSubjectDTO> list = new ArrayList<BasicSubjectDTO>();
		
		//강의 가능 과목 기초 정보 가져오기
		for (int i=0; i<seqBasicSubjectList.size(); i++) {
			BasicSubjectDTO bsdto = bsdao.get(seqBasicSubjectList.get(i));
			
			//강의 가능 과목 기초 정보 저장
			list.add(bsdto);
		}
		
		//강의 가능 과목 출력
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t[과목명]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		for (BasicSubjectDTO bsdto : list) {
			System.out.printf("\t  %s\t\t%s\n"
								, bsdto.getSeqBasicSubject()
								, bsdto.getName());
			System.out.println("\t――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		}
		
		
		System.out.println();
		System.out.println("\t★★수정을 하지 않을 경우 그냥 엔터를 눌러주세요★★");
		
		System.out.println();
		System.out.print("\t█수정 전 과목번호 : ");
		String oldSeq = scan.nextLine();
		
		if (!oldSeq.equals("")) { //수정 전 과목번호 입력O
			
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t   과목 기초 정보 리스트\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
			//과목기초정보 가져오기 + 출력
			getBasicSubjectList();

			System.out.println();
			System.out.print("\t█수정 후 과목번호 : ");
			String newSeq = scan.nextLine();
			
			int result2 = 0;
			
			if (!newSeq.equals("")) { //수정 후 과목번호 입력O
				result2 = asdao.edit(seqTeacher, oldSeq, newSeq);
			} else { //수정 후 과목번호 입력X
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t강의 가능 과목 수정 취소\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
				return;
			}
			
			if (result2 > 0) {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t강의 가능 과목 수정 성공\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			} else {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t강의 가능 과목 수정 실패\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			}
			
		} else { //수정 전 과목번호 입력X
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t강의 가능 과목 수정 취소\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}	
		
	} //editTeacher
	
	
	private void deleteTeacher() {
		
		System.out.print("\t█ 삭제할 교사 번호를 입력하세요. : ");
		String seqTeacher = scan.nextLine();
		
		//삭제할 교사의 정보 가져오기
		TeacherDTO tdto = tdao.get(seqTeacher);
		
		if (tdto == null) { //교사 번호 없는 경우
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t     없는 교사 번호입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//강의중인지 확인하기
		//0이면 강의X, 1이면 강의중
		int result = tdao.checkLecture(seqTeacher);
		
		if (result == 0) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t\t교사 계정 삭제\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
			System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("\t\t\t[번호]\t[이름]\t[주민등록번호]\t[전화번호]");
			System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("\t\t\t  %s\t%s\t%s\t%s\n"
									, tdto.getSeqTeacher()
									, tdto.getName()
									, tdto.getSsn()
									, tdto.getTel());
			System.out.println();
			
			System.out.println("\t삭제하시겠습니까?");
			System.out.println("\t1. 예\t2. 아니오");
			System.out.println();
			System.out.print("\t█번호를 입력하세요. : ");
			String sel = scan.nextLine();
			
			System.out.println();
			
			if (sel.equals("1")) {
				//삭제하기(번호, 이름을 제외한 나머지 컬럼값 null)
				int result2 = tdao.delete(seqTeacher);
				
				if (result2 > 0) {
					System.out.println();
					System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("\t┃\t\t\t    교사 계정 삭제 성공\t\t\t\t  ┃");
					System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					pause();
				} else {
					System.out.println();
					System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("\t┃\t\t\t    교사 계정 삭제 실패\t\t\t\t  ┃");
					System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					pause();
					return;
				}
			} else if (sel.equals("2")) {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t    교사 계정 삭제 취소\t\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			} else {
				//잘못 입력
				wrongInput();
				pause();
			}
		} else { //강의중인 교사
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t     강의중인 교사는 삭제할 수 없습니다.\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}	
		
	} //deleteTeacher
	
	
	private void searchTeacher() {
		
		System.out.print("\t█ 검색할 교사 번호를 입력하세요. : ");
		String seqTeacher = scan.nextLine();
		
		//삭제할 교사의 정보 가져오기
		TeacherDTO tdto = tdao.get(seqTeacher);
		
		if (tdto == null) { //교사 번호 없는 경우
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t     없는 교사 번호입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//특정 교사 정보 가져오기
		//과정명, 과정시작일, 과정종료일, 과목명, 과목시작일, 과목종료일, 교재명, 강의실, 강의진행여부
		//교사번호 선택
		ArrayList<ViewTeacherCourseDTO> list = tdao.search(seqTeacher);

		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 상세 정보\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[이름]\t[과정명]\t\t\t\t\t\t[과정시작일]\t[과정종료일]\t[강의실]\t[과목명]\t\t[과목시작일]\t[과목종료일]\t[강의진행여부]");
		
		for (ViewTeacherCourseDTO dto : list) {
			System.out.printf("\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
								, dto.getTeacherName()
								, dto.getCourseName()
								, dto.getCourseStartDate()
								, dto.getCourseEndDate()
								, dto.getRoom()
								, dto.getSubjectName()
								, dto.getSubjectStartDate()
								, dto.getSubjectEndDate()
								, dto.getLectureState());
		}
		
		System.out.println();
		pause();
		
	} //searchTeacher
	
	
	private void getBasicSubjectList() {
		
		ArrayList<BasicSubjectDTO> basicSubjectList = bsdao.wholeSubjectList();
		
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[번호]\t\t[과목명]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		for (BasicSubjectDTO dto : basicSubjectList) {
			System.out.printf("\t  %s\t\t%s\n", dto.getSeqBasicSubject(), dto.getName());
			System.out.println("\t――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		}
	} //getBasicSubjectList
	

	private void pause() {
		
		System.out.println("\t계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		
	} //pause

	
	private void wrongInput() {
		
		System.out.println("\n\t\t※ 잘못된 선택입니다.");
		System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");

	} //wrongInput
	

} //TeacherAccountManagement
