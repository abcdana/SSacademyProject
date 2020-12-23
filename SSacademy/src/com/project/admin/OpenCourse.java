package com.project.admin;

import java.sql.SQLException;
import java.util.ArrayList;
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
	private OpenCourseListDTO ocdto;
	private AdminView adView;
	private BasicCourseInfoDAO bcdao;
	
	
	static {
		scan = new Scanner(System.in);
	}
	
	
	/**
	 * 개설과정 생성자입니다.
	 */
	public OpenCourse() {
		
		bcdao = new BasicCourseInfoDAO();
		ocdao = new OpenCourseDAO();
		osdao = new OpenSubjectDAO();
		ocdto = new OpenCourseListDTO();
		adView = new AdminView();
		
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
			//관리자 메인
			adView.menu();
		} else {
			System.out.println("\t\t** 다시 입력해주세요. **");
			openCourseStart();
		}
		
	}
	
	/**
	 * 전체개설과정 리스트입니다.
	 */
	public void openCourseTotal() {
		
		ArrayList<OpenCourseListDTO> list = ocdao.openCourseList();
		
		for (OpenCourseListDTO dto : list) {
			int nameLength = checkTitle(dto.getName(), 60);
			
			System.out.printf("\t%5s" 
						+ "\t%-" + nameLength + "s"
						+ "\t%15s\t%15s\t\t%s\t%8s" + "명" + "\t%-10s\n"
			
							, dto.getSeqOpenCourse()
							, dto.getName()
							, dto.getStartDate()
							, dto.getEndDate()
							, dto.getRoom()
							, dto.getMemberCount()
							, dto.getState());
			
			System.out.println("\t ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		}
		
		
		
	}
	
	
	/**
	 * A-003-1 전체개설과정 조회입니다.
	 */
	public void openCourseList() {
		
		//전체개설과정조회 헤더
		adView.openCourseView1();
		
		//전체개설과정조회 컬럼명
		adView.openCourseView2();
		
		//전체개설과정리스트
		openCourseTotal();
		
		//전체개설과정조회 바텀
		adView.openCourseView3();
		
		//과정번호 입력
		String num = scan.nextLine();
		
		ArrayList<OpenCourseListDTO> list = ocdao.openCourseList();
		
		for (OpenCourseListDTO dto : list) {
			
			if(num.equals("0")) {
				
				openCourseStart(); //개설과정관리 시작메뉴
			
			} else if (num.equals(dto.getSeqOpenCourse())) {
				
				SpecificCourseManage(num);  //특정개설과정조회
			
			} else {
				System.out.println();
				System.out.println("\t\t** 다시 입력하려면 엔터를 눌러주세요. **");
				pause();
				openCourseList();
			}
				
		}
		
	}
		
	
	
	/**
	 * A-003-1-1 특정개설과정 조회(1)입니다.
	 */
	public void SpecificCourseManage(String num) {
	
		ArrayList<OpenCourseListDTO> list = ocdao.openCourseList();
		
		//특정개설과정 조회 뷰
		String name = adView.openSpecificCourseView(list, num);
		
		String num2 = scan.nextLine();
		System.out.println();
		
		if(num2.equals("1")) {
			specificSubject(num, name);	//특정개설 -> 과목조회
		} else if(num2.equals("2")) {
			specificStudent(num, name);	//특정개설 -> 교육생조회
		} else if(num2.equals("0")){
			openCourseList();	//전체개설과정 리스트
		} else {
			System.out.println();
			System.out.println("\t\t** 다시 입력하려면 엔터를 눌러주세요. **");
			pause();
			SpecificCourseManage(num);
		}
	
	}
	
	
	/**
	 * A-003-1-1 특정개설과정 과목조회(2)입니다.
	 */
	public void specificSubject(String seqOpenCourse, String openCourseName) {
									//과정번호, 과정명
		//과정번호 넣고 과목리스트 쿼리 호출
		ArrayList<OpenSubjectListDTO> list = osdao.specificOpenSubject(seqOpenCourse);
		adView.specificSubjectView(list, openCourseName);
		
		String num = scan.nextLine();
		if(num.equals("0")) {
			SpecificCourseManage(seqOpenCourse);	//특정개설과정 조회
		} else {
			System.out.println();
			System.out.println("\t\t** 다시 입력하려면 엔터를 눌러주세요. **");
			pause();
			specificSubject(seqOpenCourse, openCourseName);
			
		}
	}
			

	/**
	 * A-003-1-1 특정개설과정 교육생조회입니다.
	 */
	public void specificStudent(String seqOpenCourse, String openCourseName) {
									//과정번호, 과정명

		//과정번호 넣고 교육생리스트 호출
		ArrayList<OpenCourseStudentDTO> list = ocdao.openCourseStudent(seqOpenCourse);
		adView.specificStudentView(list, openCourseName);
		
		String num = scan.nextLine();
		if(num.equals("0")) {
			SpecificCourseManage(seqOpenCourse);	//특정개설과정 조회
		} else {
			System.out.println();
			System.out.println("\t\t** 다시 입력하려면 엔터를 눌러주세요. **");
			pause();
			specificStudent(seqOpenCourse, openCourseName);
			
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
		pause();
		openCourseStart();
		
		
  }
  
  
	
	/**
	 * 개설과정수정 메서드입니다.
	 */
	public void openCourseEdit() {
		
		//개설과정수정 헤더
		adView.openCourseEdit();
		
		//전체개설과정 리스트
		openCourseTotal();
		
		//수정할 과정번호 입력
		adView.openCourseEdit2();
		String seqOpenCourse = scan.nextLine();
		
		if(seqOpenCourse.equals("0")) {
			openCourseStart();
		}
		
		//dto에 과정번호 받은거 쓰기
		ocdto.setSeqOpenCourse(seqOpenCourse);
		
		//수정할 목록들
		adView.openCourseEdit3();
		String num = scan.nextLine();
		
		ArrayList<OpenCourseListDTO> list = ocdao.openCourseList();
		
		for (OpenCourseListDTO dto : list) {
			if(num.equals("1")) {
				//1 -> 강의실 수정
				
				if(dto.getSeqOpenCourse().equals(seqOpenCourse)) {
					String roomName = dto.getRoom();
					//강의실명 넣고 뷰호출
					adView.openCourseRoomEdit(roomName);	//강의실수정헤더
					openCourseRoomEdit(dto.getSeqOpenCourse());	//강의실 수정 메서드이동
				}
			
			} else if(num.equals("2")) {
				//2 -> 날짜수정
				
				if(dto.getSeqOpenCourse().equals(seqOpenCourse)) {
					String startDate = dto.getStartDate();
					String endDate = dto.getEndDate();
					
					//날짜 수정 헤더
					adView.openCourseDateEdit(startDate, endDate);
					//날짜 수정 메서드이동
					openCourseDateEdit(dto.getSeqOpenCourse());	
				}
				
			} else if(num.equals("3")) {
				//3 -> 기초과정번호 수정
				
				if(dto.getSeqOpenCourse().equals(seqOpenCourse)) {
					String seqBasicCourse = dto.getSeqBasicCourseInfo();
					
					//기초과정번호 수정 헤더
					adView.openBasicCourseEdit(dto.getName(), dto.getSeqBasicCourseInfo());
					//기초과정번호 수정 메서드 이동
					openBasicCourseEdit(seqOpenCourse);
					
				}
				
			} else if(num.equals("0")) {
				openCourseEdit();	//과정번호선택 메서드 이동
			}
			
		}//for
	}
	


	/**
	 * 강의실 수정 메서드입니다.
	 */
	public void openCourseRoomEdit(String seqOpenCourse) {
		
		String seqRoom = scan.nextLine();	//강의실 번호 받기
		
		//dto에 강의실 번호 받은거 쓰기
		ocdto.setRoom(seqRoom);
		//쿼리 호출
		int result = ocdao.openCourseRoomEdit(seqOpenCourse, seqRoom);
		
		if(result == 1) {
			System.out.println();
			System.out.println("\t** 강의실 수정완료 **");
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openCourseEdit();
		} else {
			System.out.println();
			System.out.println("\t** 강의실 수정실패 **");
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openCourseEdit();
		}

	}
	
	
	/**
	 * 날짜 수정 메서드입니다.
	 */
	private void openCourseDateEdit(String seqOpenCourse) {
		
		System.out.print("수정할 시작일: ");
		String startDate = scan.nextLine();
		
		System.out.print("수정할 종료일: ");
		String endDate = scan.nextLine();
		
		//dto에 시작일, 종료일 받은거 쓰기
		ocdto.setStartDate(startDate);
		ocdto.setEndDate(endDate);
		
		//쿼리호출
		int result = ocdao.openCourseDateEdit(seqOpenCourse, startDate, endDate);
		
		if(result == 1) {
			System.out.println();
			System.out.println("\t** 날짜 수정완료 **");
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openCourseEdit();
			
		} else {
			System.out.println();
			System.out.println("\t** 날짜 수정실패 **");
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openCourseEdit();
		}
		
	}
	
	
	/**
	 * 기초과정번호 수정 메서드입니다.
	 */
	private void openBasicCourseEdit(String seqOpenCourse) {
		
		//기초과정리스트
		ArrayList<BasicCourseInfoDTO> list = bcdao.courseList();
		
		//기초과정리스트 헤더
		adView.courseListHeader2();
		
		for(BasicCourseInfoDTO bcdto : list) {
			
			System.out.printf("\t%4s\t%-35s\t%7s일\n"
								, bcdto.getSeqBasicCourseInfo()
								, bcdto.getName()
								, bcdto.getPeriod());
			System.out.println("\t───────────────────────────────────────────────────────────────────────────");			
		}
		
		System.out.println();
		System.out.print("\t█ 수정할 기초과정번호: ");
		String seqBasicCourse = scan.nextLine();
		
		//개설과정dto에 받은 기초과정번호 쓰기
		ocdto.setSeqBasicCourseInfo(seqBasicCourse);
		
		//쿼리호출
		int result = ocdao.openBasicCourseEdit(seqOpenCourse, seqBasicCourse);
		
		if(result == 1) {
			System.out.println();
			System.out.println("\t** 기초과정번호 수정완료 **");
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openCourseEdit();
			
		} else {
			System.out.println();
			System.out.println("\t** 기초과정번호 수정실패 **");
			System.out.println("\t\t** 이전화면으로 이동하시려면 엔터를 눌러주세요. **");
			pause();
			openCourseEdit();
		}
			
	}//openBasicCourseEdit()
		
	
	
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
			pause();
			
			//삭제메인화면 메서드
			openCourseDelete1();
		
		//삭제메인화면 메서드
		} else if(num.equals("0")){
			openCourseDelete1();
		}
		
	}	
		
	
	private void pause() {
		
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

