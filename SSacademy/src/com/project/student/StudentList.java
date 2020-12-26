package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;
import com.project.dao.StudentDAO;
import com.project.dto.StudentDTO;
import com.project.student.dto.StudentListDTO;


/**
 * @author 박지현
 *
 */
public class StudentList {

	static Scanner scan;
	private StudentView sView;
	private StudentDAO sdao;
	private StudentListDTO sdto;
	private StudentDTO dto;
	private int page;
	
	//생성자
	public StudentList() {
		
		sView = new StudentView();
		scan = new Scanner(System.in);
		sdao = new StudentDAO();
		sdto = new StudentListDTO();
		dto = new StudentDTO();
		page = 1;
	}
	
	
	
	/**
	 * 교육생조회 메인입니다.
	 */
	public void studentStart() {
		
		//교육생 조회 메인헤더
		sView.StudentListMain();
		
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			//교육생명으로 조회
			searchStudentName();
		} else if(num.equals("2")) {
			//교육생번호로 조회
			searchStudentseq();
		} else if(num.equals("0")) {
			//교육생메인
			StudentController s = new StudentController(dto);
			s.studentMain();
			
		} else {
			System.out.println("\t\t** 잘못 입력했습니다. 다시 입력해주세요. ** ");
			return;
		}
	}
	
	
	/**
	 * 교육생명으로 조회 메서드입니다.
	 */
	public void searchStudentName() {
		
		//교육생명으로 조회 헤더
		sView.studentListName();
		
		//교육생명으로 조회 바텀
		sView.studentListName2();
		String name = scan.nextLine();
		
		//교육생 정보 컬럼
		sView.studentListName3(name);
		sView.studentListColunm();
		
		//교육생 리스트
		ArrayList<StudentListDTO> list = sdao.studentViewList(name);
				
		for(StudentListDTO dto : list) {
					
			System.out.printf("\t%8s\t%-8s%-8s\t%-10s\t%10s\t%10s\t%5s\n"
							, dto.getSeqStudent()
							, dto.getName()
							, dto.getSsn()
							, dto.getCourseName()
							, dto.getStartDate()
							, dto.getEndDate()
							, dto.getRoom());
					
			System.out.println("\t ────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		}
		
		//뒤로가기 뷰
		sView.back();
		String num = scan.nextLine();
		
		if(num.equals("")) {
			searchStudentName(); //교육생 이름 조회 메인
		} else {
			searchStudentName(); //교육생 이름 조회 메인
		}
	
	}
	
	
	
	/**
	 * 교육생번호로 조회 메서드입니다.
	 */
	public void searchStudentseq() {
		
		while(true) {
		//교육생번호로 조회 헤더
		sView.seqStudentList1();
		
		System.out.println("\t ** 아래 교육생 리스트에서 번호를 선택해주세요.");
		//교육생 리스트 컬럼명		
		sView.seqBasicStudent();
		allStudentList();
		
		//이전페이지, 다음페이지, 뒤로가기
		sView.pageInfo();
		String input = scan.nextLine();
		
		if(input.equals("0")) {
			//교육생조회 메인
			studentStart();
			break;
		} else if(input.equals("1")) {
			//번호입력
			specificStudent();
		} else if (input.equals("2")) {
			//이전페이지
			prevPage();
		} else if(input.equals("3")) {
			//다음페이지
			nextPage();
		}
	}//while	
	
	}
	
	
	/**
	 * 모든 교육생리스트
	 */
	public void allStudentList() {
		
		int count = sdao.getCountStudent();
		int lastPage = count/10;
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		//데이터 10개씩 보여주기
		//총갯수/10 -> 나머지가 있다면 마지막페이지는 + 1

		//기본 교육생리스트
		ArrayList<StudentListDTO> list = sdao.studentTotalList(page);
		
		for(StudentListDTO dto : list) {

			System.out.printf("\t%5s%11s%13s%16s%15s\n"
					, dto.getSeqStudent()
					, dto.getName()
					, dto.getSsn()
					, dto.getId()
					, dto.getFirstRegistDate());
			
			System.out.println("\t ───────────────────────────────────────────────────────────────");
		}
		System.out.printf("\t 페이지 %s / %s", page, lastPage);
		
	}
	

	public void specificStudent() {
		
		//교육생 행번호 입력 
		sView.seqStudentList2();
		String seqStudent = scan.nextLine();
		int num = Integer.parseInt(seqStudent);
		
		if(num == 0) {
			studentStart(); 
		} 
				
		ArrayList<StudentListDTO> list2 = sdao.seqStudentList(num);

		//검색한 교육생번호 헤더 & 컬럼
		sView.seqStudentList3(list2.get(0).getSeqStudent());
				
		for(StudentListDTO dto : list2) {
			
			System.out.println("\t─────────────────────────────────────────────────────────────────");
			System.out.printf("\t* 교육생번호 : %s\n",dto.getSeqStudent());
			System.out.printf("\t* 교육생명 : %s\n", dto.getName());
			System.out.printf("\t* 생년월일 : %s\n", dto.getSsn());
			System.out.printf("\t* 과정명 : %s\n", dto.getCourseName());
			System.out.printf("\t* 시작일 : %s\n", dto.getStartDate());
			System.out.printf("\t* 종료일 : %s\n", dto.getEndDate());
			System.out.printf("\t* 강의실 : %s\n", dto.getRoom());
			System.out.println("\t─────────────────────────────────────────────────────────────────");
		
		}//for1
				
		//뒤로가기 
		sView.back();
		String input = scan.nextLine();
		if(input.equals("")) {
			searchStudentseq();
		} else {
			searchStudentseq();
		}
		
	}
	
	/**
	 * 다음페이지
	 */
	public void nextPage() {
		
		int count = sdao.getCountStudent();
		int lastPage = count/10;
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		
		if(page == lastPage) {
			System.out.println("\t\t** 다음페이지가 없습니다. **");
			pause1();
			return;
		} else {
			page++;
		}
	}
	
	
	/*
	 * 이전페이지
	 */
	public void prevPage() {
		
		if(page == 1) {
			System.out.println("\t\t** 이전페이지가 없습니다. **");
			pause1();
			return;
		} else {
			page--;
		}
	}	

	
	
	
	public void pause1() {
		
		scan.nextLine();
	}

}
