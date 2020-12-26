package com.project.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.admin.dto.OpenCourseListDTO;
import com.project.admin.dto.OpenCourseStudentDTO;
import com.project.admin.dto.OpenSubjectListDTO;
import com.project.dao.BasicCourseInfoDAO;
import com.project.dao.OpenCourseDAO;
import com.project.dao.OpenSubjectDAO;
import com.project.dto.BasicCourseInfoDTO;
import com.project.dto.OpenCourseDTO;

/**
 * 개설과정을 관리하는 클래스입니다.
 * @author 박지현
 *
 */
public class OpenCourse {
	
	private static Scanner scan;
	private OpenCourseDAO ocdao;
	private OpenSubjectDAO osdao;
	private OpenSubjectListDTO osdto2;
	private OpenCourseListDTO ocdto;
	private OpenCourseStudentDTO sdto;
	private AdminView adView;
	private BasicCourseInfoDAO bcdao;
	private int page;
	
	static {
		scan = new Scanner(System.in);
	}
	
	
	/**
	 * 개설과정 생성자입니다.
	 */
	public OpenCourse() {
		
		this.osdto2 = new OpenSubjectListDTO();
		this.sdto = new OpenCourseStudentDTO();
		this.ocdto = new OpenCourseListDTO();
		this.bcdao = new BasicCourseInfoDAO();
		this.ocdao = new OpenCourseDAO();
		this.osdao = new OpenSubjectDAO();
		adView = new AdminView();
		page = 1;
		
	}
	
	/**
	 * 개설과정관리 시작메뉴입니다.
	 */
	public void openCourseStart() {
		
		adView.openCourseStart();
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			openCourseList();  //개설과정조회
		} else if(num.equals("2")) {
			openCourseAdd();	//개설과정등록
		} else if(num.equals("3")) {
			openCourseEdit();	//개설과정수정
		} else if(num.equals("4")) {
			openCourseDelete1();	//개설과정삭제
		} else if(num.equals("0")){
			adView.menu();	//관리자 메인
		} else {
			System.out.println("\t\t** 다시 입력해주세요. **");
			openCourseStart();
		}
		
	}
	
	
	/**
	 * 전체개설과정 리스트입니다.
	 */
	public void openCourseTotal() {
		
		int count = ocdao.getCountCourse();
		int lastPage = count/10;
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		//데이터 10개씩 보여주기
		//총갯수/10 -> 나머지가 있다면 마지막페이지는 + 1
		
		ArrayList<OpenCourseListDTO> list = ocdao.openCourseList(page);

		for (OpenCourseListDTO dto : list) {
			int nameLength = checkTitle(dto.getName(), 60);
			
			System.out.printf("\t%5s" 
						+ "\t%-" + nameLength + "s"
						+ "\t%15s\t%15s\t\t%s\t%8s" + "명" + "\t%-10s\n"
			
							, dto.getRownum()
							, dto.getName()
							, dto.getStartDate()
							, dto.getEndDate()
							, dto.getRoom()
							, dto.getMemberCount()
							, dto.getState());
			
			
			System.out.println("\t ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		}
		
		System.out.printf("\t 페이지 %s / %s", page, lastPage);
		
	}
	
	
	/**
	 * A-003-1 전체개설과정 조회입니다.
	 */
	public void openCourseList() {
		
		while(true) {
			//전체개설과정조회 헤더
			adView.openCourseView1();
		
			//전체개설과정조회 컬럼명
			adView.openCourseView2();
		
			//전체개설과정리스트
			openCourseTotal();
		
			//페이지 헤더
			adView.pageInfo();
			String num = scan.nextLine();
		
			if(num.equals("1")) {
				//특정개설과정 번호 입력
				adView.openCourseView3();
				specificOpenCourse();
				break;
			} else if(num.equals("2")) {
				//이전페이지
				prevPage();
				
			} else if(num.equals("3")) {
				//다음페이지
				nextPage();
				
			} else if(num.equals("0")) {
				//개설과정관리 메인
				openCourseStart();
				break;
			}
		}
	}
	
	/**
	 * 다음페이지
	 */
	public void nextPage() {
		
		int count = ocdao.getCountCourse();
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
	 * 특정 개설과정조회 입력받는 메서드입니다.
	 */
	private void specificOpenCourse() {
		
		//rownum 입력
		String input = scan.nextLine();
		int num = Integer.parseInt(input);
				
		ArrayList<OpenCourseListDTO> list = ocdao.specificCourse(num);	
		
		if(num == 0) {
			openCourseStart(); //개설과정관리 메인
		} else if (num == list.get(0).getRownum()) {
			for (OpenCourseListDTO dto : list) {
				specificCourseManage(dto);  //특정개설과정조회
			}
		} else {
			pause1();
			openCourseList();
		}
	
	}

	
	/**
	 * 특정개설과정조회 -> 교육생, 과목조회 선택하는 메서드입니다.
	 */
	public void specificCourseManage(OpenCourseListDTO dto) {
		
		//특정개설과정 조회 쿼리
		ArrayList<OpenCourseListDTO> list = ocdao.specificCourse(dto.getRownum());
		
		//특정개설과정 조회 뷰
		adView.openSpecificCourseView(list, dto.getRownum());
		
		String input = scan.nextLine();
		
		if(input.equals("1")) {
			specificSubject(dto);	//특정개설 -> 과목조회
		} else if(input.equals("2")) {
			specificStudent(dto);	//특정개설 -> 교육생조회
		} else if(input.equals("0")) {
			openCourseList();	//전체개설과정 리스트
		} else {
			pause1();
			specificCourseManage(dto);
		}
	
	}
	
	
	/**
	 * 특정개설과정 과목조회입니다.
	 */
	public void specificSubject(OpenCourseListDTO dto) {
								
		//과정번호 넣고 과목리스트 쿼리 호출
		ArrayList<OpenSubjectListDTO> list = osdao.specificOpenSubject(dto.getSeqOpenCourse());
		
		
		if(list.size() == 0) {
			System.out.println("\n\t\t** 해당 과정의 과목이 존재하지 않습니다. **");
			pause1();
			specificSubject(dto);
		}
		
		adView.specificSubjectView(list, dto.getName());
		String input = scan.nextLine();
		
		if(input.equals("0")) {
			specificCourseManage(dto);	//특정개설과정 조회
		} else {
			pause1();
			specificSubject(dto);
		}
	}
			

	/**
	 * 특정개설과정 교육생조회입니다.
	 */
	public void specificStudent(OpenCourseListDTO dto) {

		//과정번호 넣고 교육생리스트 호출
		ArrayList<OpenCourseStudentDTO> list = ocdao.openCourseStudent(dto.getSeqOpenCourse(), page);
	
		if(list.size() == 0) {
			System.out.println("\n\t\t** 해당 과정의 교육생이 존재하지 않습니다. **");
			pause1();
			specificCourseManage(dto);
		}

		//총 교육생 수
		int count = ocdao.getCountStudent(dto);
		int lastPage = count/10;
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		//데이터 10개씩 보여주기
		//총갯수/10 -> 나머지가 있다면 마지막페이지는 + 1
		//교육생조회 헤더
		
		adView.specificStudentView(dto.getName());
		
		for(OpenCourseStudentDTO sdto : list) {
			
			System.out.printf("\t  %-7s%-12s%-20s%-19s%-17s%-20s\n", sdto.getSeqStudent()
								,sdto.getName()
								,sdto.getSsn()
								,sdto.getTel()
								,sdto.getRegistDate()
								,sdto.getState());
			
			System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────");			
		
		}//for
		
		System.out.printf("\t 페이지 %s / %s", page, lastPage);
		
		//교육생조회 바텀
		adView.specificStudentView2();
		
		String num = scan.nextLine();
		if(num.equals("0")) {
			specificCourseManage(ocdto);	//특정개설과정 조회
		} else {
			pause1();
			specificStudent(dto);
		}
		
	}
	
	
	/**
	 * 개설과정등록(1) 메뉴입니다.
	 */
	public void openCourseAdd() {
		
		//개설과정등록 헤더
		adView.openCourseAddView();
		
		System.out.print("\t\t* 강의실번호: ");
		String seqRoom = scan.nextLine();
		
		System.out.print("\t\t* 기초강좌번호: ");
		String seqBasicCourse = scan.nextLine();

		System.out.print("\t\t* 시작일(yyyy-mm-dd): ");
		String startDate = scan.nextLine();
		
		System.out.print("\t\t* 종료일(yyyy-mm-dd): ");
		String endDate = scan.nextLine();
		
		//날짜 체크 쿼리
		int result2 = ocdao.checkDate(startDate, endDate);
		
		if(result2 == 1) {
			System.out.println("\t\t날짜를 확인해주세요.");
			openCourseAdd();
		}
		
		System.out.print("\t\t* 인원: ");
		String count = scan.nextLine();
		
		//개설과정 DTO
		OpenCourseDTO ocdto = new OpenCourseDTO();
		
		ocdto.setSeqRoom(seqRoom);
		ocdto.setSeqBasicCourseInfo(seqBasicCourse);
		ocdto.setStartDate(startDate);
		ocdto.setEndDate(endDate);
		ocdto.setMemberCount(count);
		
		boolean loop = true;
		
		while(loop) {
			
			//등록 헤더
			adView.chooseAddOrNot();
			String num = scan.nextLine();
			
			//1일경우 등록
			if(num.equals("1")) {
				addOpenCourse(ocdto);
			} else if(num.equals("0")){
				loop = false;
				openCourseAdd();
			}
		}	
	}
	
	
	/**
	 * 개설과정등록(2) 진짜등록
	 */

	public void addOpenCourse(OpenCourseDTO ocdto) {
		
		//쿼리 & 쿼리 반환값
		int result = ocdao.openCourseAdd(ocdto);
		//1이면 등록완료 0이면 실패
		adView.addResult(result);
		
		System.out.println("\t\t** 개설과정관리 화면으로 이동합니다. 엔터를 눌러주세요. **");
		pause2();
		openCourseStart();
		
		
  }
  
	/**
	 * 개설과정수정 메서드입니다.
	 */
	public void openCourseEdit() {
		
		//개설과정수정 헤더
		adView.openCourseEdit();
		System.out.println("\n\t* 아래 개설과정리스트에서 수정을 원하는 번호를 입력해주세요.");
		
		while(true) {
			//개설과정보기 컬럼명 & 리스트
			adView.openCourseView2();
			openCourseTotal();
		
			//개설과정 번호입력 & 이전, 다음페이지
			adView.openCourseEdit2();
			String num = scan.nextLine();
	
			if(num.equals("0")) {
				//개설과정관리 메인
				openCourseStart();
				break;
			} else if(num.equals("1")) {
				adView.openCourseEdit3();
				editOpenCourse2();
			} else if(num.equals("2")) {
				//이전페이지
				prevPage();
			
			} else if(num.equals("3")) {
				//다음페이지
				nextPage();
			
			} 
			
		}		
	}//openCourseEdit()
	
	
	
	/**
	 * 개설과정수정2 
	 */
	public void editOpenCourse2() {
		
		String input = scan.nextLine();
		int num = Integer.parseInt(input);
		
		if(num == 0) {
			openCourseStart(); //개설과목관리 메인	
		} 
		
		OpenCourseEdit2(num);
	}	
	
	/**
	 * 개설과정수정 - 현재과목정보 & 진짜 수정
	 */
	public void OpenCourseEdit2(int num) {
		
		//현재과정정보 헤더
		adView.openCourseEdit4();
		
		OpenCourseListDTO ocdto = ocdao.normalOpenCourse(num);
		
		System.out.println("\t─────────────────────────────────────────────────────────");
		System.out.printf("\t * 과정번호 : %s\n", ocdto.getSeqOpenCourse());
		System.out.printf("\t * 개설과정 : %s\n", ocdto.getName());
		System.out.printf("\t * 기초과정번호 : %s\n", ocdto.getSeqBasicCourseInfo());
		System.out.printf("\t * 시작일 : %s\n", ocdto.getStartDate());
		System.out.printf("\t * 종료일 : %s\n", ocdto.getEndDate());
		System.out.printf("\t * 강의실 : %s\n", ocdto.getRoom());
		System.out.println("\t─────────────────────────────────────────────────────────");
		System.out.println("\t ** 수정을 원하지 않는 항목은 엔터를 입력하세요. **");
		System.out.println();
		
		System.out.print("\t■ 수정할 기초과정번호 : ");
		String seqBasicCourse = scan.nextLine();
		
		if(seqBasicCourse.equals("")) {
			seqBasicCourse = ocdto.getSeqBasicCourseInfo();
		}
		
		System.out.print("\t■ 수정할 시작일(yyyy-mm-dd) : ");
		String startDate = scan.nextLine();
		
		if(startDate.equals("")) {
			startDate = ocdto.getStartDate();
		}
		
		System.out.print("\t■ 수정할 종료일(yyyy-mm-dd) : ");
		String endDate = scan.nextLine();
		
		if(endDate.equals("")) {
			endDate = ocdto.getEndDate();
		}
		
		System.out.print("\t■ 수정할 강의실 : ");
		String room = scan.nextLine();
		
		if(room.equals("")) {
			room = ocdto.getSeqRoom();
		}
		
		ocdto.setSeqOpenCourse(ocdto.getSeqOpenCourse());
		ocdto.setSeqBasicCourseInfo(seqBasicCourse);
		ocdto.setSeqRoom(room);
		ocdto.setStartDate(startDate);
		ocdto.setEndDate(endDate);
		
		int result = ocdao.editOpenCourse(ocdto);
		
		adView.updateResult(result);
		System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
		pause2();
		openCourseEdit();
		
		
	}
	
	
	/**
	 * 개설과정삭제 메서드입니다.
	 */
	public void openCourseDelete1() {
		
		//개설과정 삭제 헤더
		adView.openBasicCourseDelete();
		
		//전체개설과정조회 컬럼명
		adView.openCourseView2();
		
		//전체개설과정리스트
		openCourseTotal();
		
		System.out.println();
		System.out.println("\t* 뒤로가기를 원하시면 0을 입력해주세요.");
		System.out.print("\t█ 삭제할 개설과정번호: ");
		String seqOpenCourse = scan.nextLine();
		
		adView.chooseDeleteOrNot();
		
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			//쿼리
			int result = ocdao.openCourseDelete(seqOpenCourse);
			
			//삭제성공 실패 확인
			adView.deleteResult(result);
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause2();
			
			//삭제메인화면 메서드
			openCourseDelete1();
		
		//삭제메인화면 메서드
		} else if(num.equals("0")){
			openCourseDelete1();
		}
		
	}	
		
	
	private void pause1() {
		System.out.println("\t\t** 다시 입력하려면 엔터를 눌러주세요. **");
		scan.nextLine();
	}
	
	private void pause2() {
		scan.nextLine();
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

