package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

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
	private AdminView adView;
	private AvailableSubjectDAO asdao;
	
	static {
		scan = new Scanner(System.in);
	}
	
	//생성자
	public OpenSubject() {
		
		adView = new AdminView();
		osdao = new OpenSubjectDAO();
		asdao = new AvailableSubjectDAO();
		osdto2 = new OpenSubjectDTO();
	
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
		
		ArrayList<OpenSubjectListDTO> list = osdao.openSubjectList();
		
		for (OpenSubjectListDTO dto : list) {
			
			System.out.printf("\t%4s" 
						+ "\t%-40s"
						+ "\t%-25s"
						+ "\t%-10s%10s%15s\n"
			
							, dto.getSeqOpenSubject()
							, dto.getSubjectName()
							, dto.getBookName()
							, dto.getTeacherName()
							, dto.getStartDate()
							, dto.getEndDate());
			
			System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		}
		
	}//openSubjectTotal()
	
	
	
	/**
	 * 전체 개설과목조회입니다.
	 */
	public void openSubjectList() {
		//개설과목조회 헤더
		adView.openSubjectView1();
				
		//개설과목조회 컬럼명
		adView.openSubjectView2();
		
		//개설과목 리스트
		openSubjectTotal();
				
		//개설과목조회 번호입력
		adView.openSubjectView3();
		String seqOpenSubject = scan.nextLine();
		
		if(seqOpenSubject.equals("0")) {
			//개설과목메인 이동
			openSubjectStart();
		} else {
			//특정개설과목 조회 메서드
			specificOpenSubject(seqOpenSubject);
		}
				
	}//openSubjectList()
	
	

	/**
	 * 특정 개설과목조회입니다.
	 */
	public void specificOpenSubject(String seqOpenSubject) {
		
		ArrayList<OpenSubjectListDTO> list = osdao.openSubjectList();
		
		//특정개설과목 조회 메서드
		adView.specificOpenSubject(list, seqOpenSubject);
		String num = scan.nextLine();
		
		if(num.equals("0")) {
			//개설과목조회 메인
			openSubjectStart();
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
		int result = asdao.checkAvailableSubject(seqAvailableSubject);
		
		if(result == 1) {
			System.out.println("\t\t** 현재 진행중인 과목입니다. **");
			openSubjectAdd1();
		} 
		
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
		pause();
		openSubjectStart();	
	
	}//openSubjectAdd2()		
		
	
	
	/**
	 * 개설과목수정 메서드입니다.
	 */
	public void openSubjectEdit() {
		
		System.out.println();
		System.out.println("\t* 아래 개설과목리스트에서 수정을 원하는 과목번호를 입력해주세요.");
		
		//개설과목리스트 컬럼명 & 리스트
		adView.openSubjectView2();
		openSubjectTotal();
		
		//개설과목수정 바텀
		adView.openSubjectEdit2();
		String seqOpenSubject = scan.nextLine();
		
		OpenSubjectListDTO osdto2 = new OpenSubjectListDTO();
		
		osdto2 = osdao.normalOpenSubject(seqOpenSubject);
		
		if(seqOpenSubject.equals("0")) {
			openSubjectStart(); //개설과목관리 메인	
		} else if(seqOpenSubject.equals(osdto2.getSeqOpenSubject())) {
			
			//현재과목정보 헤더
			adView.openSubjectEdit3();
			
			System.out.println("\t─────────────────────────────────────────────────────────");
			System.out.printf("\t * 개설과목 : %s. %s\n", osdto2.getSeqOpenSubject(), osdto2.getSubjectName());
			System.out.printf("\t * 강의가능과목 : %s. %s - %s\n", osdto2.getAvailableSubject()
															, osdto2.getTeacherName()
															, osdto2.getSubjectName());
			System.out.printf("\t * 개설과정 : %s. %s\n", osdto2.getSeqOpenCourse(), osdto2.getOpenCourseName());
			System.out.printf("\t * 시작일 : %s\n", osdto2.getStartDate());
			System.out.printf("\t * 종료일 : %s\n", osdto2.getEndDate());
			System.out.println("\t─────────────────────────────────────────────────────────");
			System.out.println("\t ** 수정을 원하지 않는 항목은 엔터를 입력하세요. **");
			System.out.println();
			
			
			System.out.print("\t■ 수정할 강의가능과목 번호 : ");
			String seqAvailableSubject = scan.nextLine();
			
			if(seqAvailableSubject.equals("")) {
				osdto2.getAvailableSubject();
			}
			
			System.out.print("\t■ 수정할 개설과정 번호 : ");
			String seqOpenCourse = scan.nextLine();
			
			if(seqOpenCourse.equals("")) {
				osdto2.getSeqOpenCourse();
			}
			
			System.out.print("\t■ 수정할 시작일(yyyy-mm-dd) : ");
			String startDate = scan.nextLine();
			
			if(startDate.equals("")) {
				osdto2.getStartDate();
			}
			
			System.out.print("\t■ 수정할 종료일(yyyy-mm-dd) : ");
			String endDate = scan.nextLine();
			
			if(endDate.equals("")) {
				osdto2.getEndDate();
			}
			
			
			osdto2.setSeqOpenCourse(seqOpenCourse);
			osdto2.setAvailableSubject(seqAvailableSubject);
			osdto2.setStartDate(startDate);
			osdto2.setEndDate(endDate);
			
			int result = osdao.editOpenSubject(osdto2);
			
			adView.updateResult(result);
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openSubjectEdit();
			
			}
		
	}//openSubjectEdit()
		
	
	
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
			pause();
			
			//삭제메인화면 메서드
			openSubjectDelete();
		
		//삭제메인화면 메서드
		} else if(num.equals("0")){
			openSubjectDelete();
		}
		
	}//openSubjectDelete()	
	

	
	private void pause() {
		
		scan.nextLine();
	}
	
}
