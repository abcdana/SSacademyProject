package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.INTERNAL;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dao.AvailableSubjectDAO;
import com.project.dao.OpenSubjectDAO;
import com.project.dto.OpenSubjectDTO;

/**
 * @author 박지현
 *
 */
public class OpenSubject {
	
	private static Scanner scan;
	private OpenSubjectDAO osdao;
	private OpenSubjectDTO osdto2;
	private OpenSubjectListDTO osdto;
	private AdminView adView;
	private AvailableSubjectDAO asdao;
	private int page;
	
	static {
		scan = new Scanner(System.in);
	}
	
	//생성자
	public OpenSubject() {
		
		this.osdto = new OpenSubjectListDTO();
		this.adView = new AdminView();
		this.osdao = new OpenSubjectDAO();
		this.asdao = new AvailableSubjectDAO();
		this.osdto2 = new OpenSubjectDTO();
		this.page = 1;
	
	}
	
	/**
	 * 개설과목조회 메인화면입니다.
	 */
	public void openSubjectStart() {
		
		//개설과목조회 메인화면
		adView.openSubjectStart();
		
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			openSubjectList();
		} else if (num.equals("2")) {
			openSubjectAdd1();
		} else if (num.equals("3")) {
			openSubjectEdit();
		} else if (num.equals("4")) {
			openSubjectDelete();
		} else if (num.equals("0")) {
			//관리자 메인
			adView.menu();
		} else {
			System.out.println("\t\t** 다시 입력해주세요. **");
			openSubjectStart(); //개설과목메인
		}
		
	}//openSubjectStart()
	
	
	
	/**
	 * 개설과목조회 리스트입니다.
	 */
	public void openSubjectTotal() {
		
		int count = osdao.getCountSubject();
		int lastPage = count/10;
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		//데이터 10개씩 보여주기
		//총갯수/10 -> 나머지가 있다면 마지막페이지는 + 1
		
		ArrayList<OpenSubjectListDTO> list = osdao.openSubjectList(osdto, page);
		
		for (OpenSubjectListDTO dto : list) {
			
			System.out.printf("\t%4s" 
						+ "\t%-40s"
						+ "\t%-25s"
						+ "\t%-10s%10s%15s\n"
							
							, dto.getRownum()
							, dto.getSubjectName()
							, dto.getBookName()
							, dto.getTeacherName()
							, dto.getStartDate()
							, dto.getEndDate());
			
			System.out.println("\t────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		}
		System.out.printf("\t 페이지 %s / %s", page, lastPage);
		
	}//openSubjectTotal()
	
	
	
	/**
	 * 전체 개설과목조회입니다.
	 */
	public void openSubjectList() {
		
		while(true) {
		
			//개설과목조회 헤더
			adView.openSubjectView1();
				
			//개설과목조회 컬럼명
			adView.openSubjectView2();
		
			//개설과목 리스트
			openSubjectTotal();
		
			//페이지 헤더
			adView.pageInfo();
			String num = scan.nextLine();
	
			if(num.equals("1")) {
				//특정개설과목 행번호 입력
				adView.openCourseView3();
				specificOpenSubject(osdto);
				break;
			} else if(num.equals("2")) {
				//이전페이지
				prevPage();
			
			} else if(num.equals("3")) {
				//다음페이지
				nextPage();
			
			} else if(num.equals("0")) {
				//개설과목관리 메인
				openSubjectStart();
				break;
			}
		}
				
	}//openSubjectList()
	
	
	
	/**
	 * 다음페이지
	 */
	public void nextPage() {
		
		int count = osdao.getCountSubject();
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

	

	/**
	 * 특정 개설과목조회입니다.
	 */
	public void specificOpenSubject(OpenSubjectListDTO osdto) {
		
		String input = scan.nextLine();
		int num = Integer.parseInt(input);
		
		//특정개설과목 조회 쿼리
		ArrayList<OpenSubjectListDTO> list = osdao.specificOpenSubject2(num);
		
		while(true) {	
		
			//특정개설과목 조회 뷰
			adView.specificOpenSubject(list);
			//뒤로가기 뷰
			adView.specificOpenSubject2();
				
			String input2 = scan.nextLine();
					
			if(input2.equals("0")) {
				openSubjectList(); //전체개설과목조회
				break;
			} else {
				pause1();
				
			}
		}
		
	}//specificOpenSubject()
	
	


	/**
	 * 개설과목등록(1) 메뉴
	 */
	private void openSubjectAdd1() {
		
		//개설과목등록 헤더
		adView.openSubjectAddView();
		
		System.out.print("\t\t* 강의가능과목번호: ");
		String seqAvailableSubject = scan.nextLine();
		
		//강의가능과목 중복검사
		//int result = asdao.checkAvailableSubject(seqAvailableSubject);
		
//		if(result == 1) {
//			System.out.println("\t\t** 현재 진행중인 과목입니다. **");
//			openSubjectAdd1();
//		} 
		
		System.out.print("\t\t* 개설과정번호: ");
		String seqOpenCourse = scan.nextLine();

		System.out.print("\t\t* 시작일(yyyy-mm-dd): ");
		String startDate = scan.nextLine();
		
		System.out.print("\t\t* 종료일(yyyy-mm-dd): ");
		String endDate = scan.nextLine();
	
		//받은 값 개설과목 DTO에 쓰기
		osdto2.setSeqAvailableSubject(seqAvailableSubject);
		osdto2.setSeqOpenCourse(seqOpenCourse);
		osdto2.setStartDate(startDate);
		osdto2.setEndDate(endDate);
		
		boolean loop = true;
		
		while(loop) {
			
			//등록 헤더
			adView.chooseAddOrNot();
			String num = scan.nextLine();
			
			//1일경우 등록
			if(num.equals("1")) {
				openSubjectAdd2(osdto2);
			} else if(num.equals("0")){
				loop = false;
				openSubjectStart();
				
			}
		}	
	}//openSubjectAdd1()
	
	
		
	/**
	 * 개설과목등록(2) 진짜등록
	 */

	public void openSubjectAdd2(OpenSubjectDTO osdto2) {
		
		//쿼리 & 쿼리 반환값
		int result = osdao.openSubjectAdd(osdto2);
		//1이면 등록완료 0이면 실패
		adView.addResult(result);
		
		System.out.println("\t\t** 개설과목관리 화면으로 이동합니다. 엔터를 눌러주세요. **");
		pause2();
		openSubjectStart();	
	
	}//openSubjectAdd2()		
		
	
	
	/**
	 * 개설과목수정 메서드입니다.
	 */
	public void openSubjectEdit() {
		
		//개설과목수정 헤더
		adView.openSubjectEdit();
		System.out.println("\t* 아래 개설과목리스트에서 수정을 원하는 번호를 입력해주세요.");
		
		while(true) {
		//개설과목리스트 컬럼명 & 리스트
		adView.openSubjectView2();
		openSubjectTotal();
		
		//개설과목 번호입력 & 이전, 다음페이지
		adView.openCourseEdit2();
		String input = scan.nextLine();
		
			if(input.equals("0")) {
				//개설과목관리 메인
				openSubjectStart();
				break;
			} else if(input.equals("1")) {
				//번호입력
				adView.openSubjectEdit2();
				editOpenSubject2(); 	//진짜수정
				break;
			} else if(input.equals("2")) {
				//이전페이지
				prevPage();
			
			} else if(input.equals("3")) {
				//다음페이지
				nextPage();
			}
		}
		
	}//openSubjectEdit()
		
	
	
	/**
	 * 개설과목수정2 
	 */
	public void editOpenSubject2() {
		
		String input = scan.nextLine();
		int num = Integer.parseInt(input);
		
		if(num == 0) {
			openSubjectStart(); //개설과목관리 메인	
		} 
		
		//개설과목 총 rownum 배열
		ArrayList<Integer> list = osdao.specificOpenSubject3();
		
		for(int i=0; i<list.size(); i++) {
			if(num == list.get(i)) {
				editOpenSubject3(num);
				break;
			} 
		}

	}
	
	
	/**
	 * 개설과목수정 - 현재과목정보 & 진짜 수정
	 */
	
	public void editOpenSubject3(int num) {
			
		//현재과목정보 헤더
		adView.openSubjectEdit3();
		
		OpenSubjectListDTO osdto = osdao.normalOpenSubject(num);
			
			System.out.println("\t─────────────────────────────────────────────────────────");
			System.out.printf("\t * 개설과목 : %s. %s\n", osdto.getSeqOpenSubject(), osdto.getSubjectName());
			System.out.printf("\t * 강의가능과목 : %s. %s - %s\n", osdto.getAvailableSubject()
															, osdto.getTeacherName()
															, osdto.getSubjectName());
			System.out.printf("\t * 개설과정 : %s. %s\n", osdto.getSeqOpenCourse(), osdto.getOpenCourseName());
			System.out.printf("\t * 시작일 : %s\n", osdto.getStartDate());
			System.out.printf("\t * 종료일 : %s\n", osdto.getEndDate());
			System.out.println("\t─────────────────────────────────────────────────────────");
			System.out.println("\t ** 수정을 원하지 않는 항목은 엔터를 입력하세요. **");
			System.out.println();
	
		
			System.out.print("\t■ 수정할 강의가능과목 번호 : ");
			String seqAvailableSubject = scan.nextLine();
			
			if(seqAvailableSubject.equals("")) {
				seqAvailableSubject = osdto.getAvailableSubject();
			}
			
			System.out.print("\t■ 수정할 개설과정 번호 : ");
			String seqOpenCourse = scan.nextLine();
			
			if(seqOpenCourse.equals("")) {
				seqOpenCourse = osdto.getSeqOpenCourse();
			}
			
			System.out.print("\t■ 수정할 시작일(yyyy-mm-dd) : ");
			String startDate = scan.nextLine();
			
			if(startDate.equals("")) {
				startDate = osdto.getStartDate();
			}
			
			System.out.print("\t■ 수정할 종료일(yyyy-mm-dd) : ");
			String endDate = scan.nextLine();
			
			if(endDate.equals("")) {
				endDate = osdto.getEndDate();
			}
			
			osdto.setSeqOpenCourse(seqOpenCourse);
			osdto.setAvailableSubject(seqAvailableSubject);
			osdto.setStartDate(startDate);
			osdto.setEndDate(endDate);
			
			int result = osdao.editOpenSubject(osdto);
			
			adView.updateResult(result);
			
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause2();
			openSubjectEdit();
			
		}
	
	
	/**
	 * 개설과목삭제 메서드입니다.
	 */
	public void openSubjectDelete() {
		
		//개설과목 삭제 헤더
		adView.openSubjectDelete();
		
		//전체개설과목조회 컬럼명
		adView.openSubjectView2();
		
		//전체개설과목리스트
		openSubjectTotal();
		
		System.out.println();
		System.out.println("\t* 뒤로가기를 원하시면 0을 입력해주세요.");
		System.out.print("\t█ 삭제할 개설과목번호: ");
		String seqOpenSubject = scan.nextLine();
		
		if(seqOpenSubject.equals("0")) {
			//개설과목관리 메인
			openSubjectStart();
		}
		
		adView.chooseDeleteOrNot();
		
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			//쿼리
			int result = osdao.openSubjectDelete(seqOpenSubject);
			
			//삭제성공 실패 확인
			adView.deleteResult(result);
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause2();
			
			//삭제메인화면 메서드
			openSubjectDelete();
		
		//삭제메인화면 메서드
		} else if(num.equals("0")){
			openSubjectDelete();
		}
		
	}//openSubjectDelete()	
	
	
	
	private void pause1() {
		System.out.println("\t\t** 다시 입력하려면 엔터를 눌러주세요. **");
		scan.nextLine();
	}
	
	private void pause2() {
		scan.nextLine();
	}
	
}
