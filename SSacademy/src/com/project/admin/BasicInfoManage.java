package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.BasicCourseInfoDAO;
import com.project.dao.BasicSubjectDAO;
import com.project.dao.BookDAO;
import com.project.dao.RoomDAO;
import com.project.dao.ViewSubjectDAO;
import com.project.dto.BasicCourseInfoDTO;
import com.project.dto.BasicSubjectDTO;
import com.project.dto.BookDTO;
import com.project.dto.RoomDTO;
import com.project.dto.ViewSubjectDTO;

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
	private ViewSubjectDAO vsdao;
	private BasicSubjectDAO bsdao;
	private RoomDAO rdao;
	private BookDAO bdao;
	
	public BasicInfoManage() {
		view = new AdminView();
		this.bcidao = new BasicCourseInfoDAO();
		this.vsdao = new ViewSubjectDAO();
		this.bsdao = new BasicSubjectDAO();
		this.rdao = new RoomDAO();
		this.bdao = new BookDAO();
	}

//	//임시 메인
//	public static void main(String[] args) {
//		
//		BasicInfoManage bim = new BasicInfoManage();
//		bim.basicInfoMain();
//		
//	}
	
	/**
	 * 기초 정보 관리 메뉴 분기 메서드이다.
	 */
	public void basicInfoMain() {
		
		boolean loop = true;
		
		while (loop) {
			
			view.basicInfoMenu(); //기초 정보 관리 메뉴
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				courseinfoMenu();
				pause();
			} else if (num.equals("2")) {
				subjectInfoMenu();
				pause();
			} else if (num.equals("3")) {
				roomInfoMenu();
				pause();
			} else if (num.equals("4")) {
				bookInfoMenu();
				pause();
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}
		}
	}//basicInfoMain()
	

	/**
	 * 기초과정정보 조회, 추가, 수정, 삭제 메뉴 분기 메서드이다.
	 */
	private void courseinfoMenu() {
		
		boolean loop = true;
		
		while (loop) {
			
			view.courseInfoMenu();	//과정 정보 관리 메뉴(CRUD)
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				view.courseListHeader();
				courseList();
				pause();
			} else if (num.equals("2")) {
				addCourseInfoMenu();
			} else if (num.equals("3")) {
				updateCourseInfo();
				pause();
			} else if (num.equals("4")) {
				deleteCourseInfoMenu();
				pause();
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}//if	
			
		}//while
		
	}//basicCourseinfo()

	
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
	
	}//courseList()
	
	
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
				addCourseInfo(bcidto);	//과정 추가
				return ;
			} else {
				loop = false;
			}
		
		}//while
		
	}//addCourseInfoMenu()

	
	/**
	 * 새로운 과정기초정보를 추가하는 메서드이다.
	 */
	private void addCourseInfo(BasicCourseInfoDTO bcidto) {

		int result = bcidao.addCourse(bcidto);
		view.addResult(result);
		
	}//addCourseInfo(BasicCourseInfoDTO bcidto)

	
	/**
	 * 기존 과정정보를 수정하는 메서드이다.
	 */
	private void updateCourseInfo() {
		
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
		
	}//updateCourseInfo()


	
	/**
	 * 과정정보를 삭제하는 메서드이다.
	 */
	private void deleteCourseInfoMenu() {
		
		view.deleteCourseHeader();
		
		courseList(); //전체과정
		
		System.out.println();
		System.out.print("\t█ 삭제할 과정번호 : ");
		String seqBasicCourseInfo = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseDeleteOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				deleteCourseInfo(seqBasicCourseInfo); //과정 삭제
				return ;
			} else {
				loop = false;
			}
		}
	}//deleteCourseInfoMenu()

	
	/**
	 * 기존 과정정보를 삭제하는 메서드이다.
	 */
	private void deleteCourseInfo(String seqBasicCourseInfo) {

		int result = bcidao.deleteCourse(seqBasicCourseInfo);
		view.deleteResult(result);
		
	}//deleteCourseInfo(String seqBasicCourseInfo)
	
	
	private static void pause() {
		System.out.println("\n\t\t이전 페이지로 가시려면 엔터를 눌러주세요.");
		scan.nextLine();
	}//pause()

	
	/**
	 * 기초 과목정보 조회, 추가, 수정, 삭제 메뉴 분기 메서드이다.
	 */
	private void subjectInfoMenu() {
		
		boolean loop = true;
		while (loop) {
			
			view.subjectInfoMenu();	//과목 정보 관리 메뉴(CRUD)
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				view.subjectListHeader();
				subjectList();
				pause();
			} else if (num.equals("2")) {
				addSubjectInfoMenu();
			} else if (num.equals("3")) {
				updateSubjectInfo();
				pause();
			} else if (num.equals("4")) {
				deleteSubjectInfoMenu();
				pause();
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}//if	
			
		}//while
		
	}//subjectInfoMenu()
	
	
	/**
	 * 과목 기초정보를 조회하는 메서드이다.
	 */
	private void subjectList() {
		
		view.subjectListHeader2();//헤더
		
		ArrayList<ViewSubjectDTO> list = vsdao.subjectList();
		
		for (ViewSubjectDTO dto : list) {
			
			System.out.printf("\t%4s\t   %-23s\t%-20s\n"
					, dto.getSeqBasicSubject()
					, dto.getName()
					, dto.getBook());
			System.out.println("\t───────────────────────────────────────────────────────────────────────────");			
		}
		
	}//subjectList()
	
	
	/**
	 * 새로운 과목 정보를 등록하는 메뉴 메서드이다. 
	 */
	private void addSubjectInfoMenu() {
		
		view.addSubjectHeader();//헤더
		
		System.out.print("\t█ 과목이름 : ");
		String name = scan.nextLine();
		
		System.out.print("\t█ 과목소개 : ");
		String info = scan.nextLine();
				
		System.out.print("\t█ 교재번호 : ");
		String seqBook = scan.nextLine();
		
		BasicSubjectDTO bsdto = new BasicSubjectDTO();
		bsdto.setName(name);
		bsdto.setInfo(info);
		bsdto.setSeqBook(seqBook);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseAddOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addSubjectInfo(bsdto);
				return;
			} else {
				loop = false;
			}
		}
		
	}//addSubjectInfoMenu()
	
	
	/**
	 * 새로운 과목을 등록하는 메서드이다.
	 * @param bsdto
	 */
	private void addSubjectInfo(BasicSubjectDTO bsdto) {
		
		int result = bsdao.addSubject(bsdto);
		view.addResult(result);
		
	}//addSubjectInfo(BasicSubjectDTO bsdto) 
	
	
	/**
	 * 기존 과목을 수정하는 메서드이다.
	 */
	private void updateSubjectInfo() {
		
		view.updateSubjectHeader();//헤더
		
		subjectList(); //전체 과목
		
		System.out.print("\n\t█ 과목 번호 : ");
		String seqBasicSubject = scan.nextLine();
		
		ViewSubjectDTO dto = vsdao.get(seqBasicSubject);
		BasicSubjectDTO dtoBS = bsdao.get(seqBasicSubject);
		
		System.out.println();
		System.out.println("\t* 과목이름 : " + dto.getName());
		System.out.println("\t* 과목소개 : " + dto.getInfo());
		System.out.println("\t* 교재이름 : " + dto.getBook());
		System.out.println("\n");
		
		System.out.println("\t\t  수정을 원치 않는 항목은 엔터를 입력하세요.\n");
		
		view.bookListHeader();
		bookList();
		System.out.println(); //교재 목록
		
		System.out.print("\t█ 수정할 과목이름 : ");
		String name = scan.nextLine();
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t█ 수정할 과목소개 : ");
		String info = scan.nextLine();
		if (info.equals("")) {
			info = dto.getInfo();
		}
		System.out.print("\t█ 수정할 교재번호 : ");
		String book = scan.nextLine();
		if (book.equals("")) {
			book = dtoBS.getSeqBook();
		}
		
		BasicSubjectDTO dto2 = new BasicSubjectDTO();
		
		dto2.setSeqBasicSubject(seqBasicSubject);
		dto2.setName(name);
		dto2.setInfo(info);
		dto2.setSeqBook(book);
		
		int result = bsdao.updateSubject(dto2);
		
		view.updateResult(result);
		
	}
	
	
	/**
	 * 과목 삭제 메뉴 메서드이다.
	 */
	private void deleteSubjectInfoMenu() {
		
		view.deleteSubjectHeader();//헤더
		
		subjectList();
		
		System.out.print("\t█ 과목 번호 : ");
		String seqBasicSubject = scan.nextLine();
    	
		boolean loop = true;
		while (loop) {
			
			view.chooseDeleteOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				deleteBasicSubjectInfo(seqBasicSubject);
				return;
			} else {
				loop = false;
			}
		}

	}
	
	
	/**
	 * 과목 삭제 메서드이다.
	 * @param seqBasicSubject
	 */
	private void deleteBasicSubjectInfo(String seqBasicSubject) {
		
		int result = bsdao.deleteSubject(seqBasicSubject);
		view.deleteResult(result);
		
	}

	/**
	 * 기초 강의실정보 조회, 추가, 수정, 삭제 메뉴 분기 메서드이다.
	 */
	private void roomInfoMenu() {
		
		boolean loop = true;
		while (loop) {
			
			view.roomInfoMenu();	//강의실 정보 관리 메뉴(CRUD)
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				view.roomListHeader();
				roomList();
				pause();
			} else if (num.equals("2")) {
				addRoomInfoMenu();
			} else if (num.equals("3")) {
				updateRoomInfo();
				pause();
			} else if (num.equals("4")) {
				deleteRoomInfoMenu();
				pause();
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}//if	
			
		}//while
		
	}//roomInfoMenu()
	
	
	
	/**
	 * 강의실 기초정보를 조회하는 메서드이다.
	 */
	private void roomList() {

		view.roomListHeader2();
		
		ArrayList<RoomDTO> list = rdao.roomList();
		
		for (RoomDTO dto : list) {
			System.out.printf("\t%6s\t%22s\t%22s명\n"
							, dto.getSeqRoom()
							, dto.getName()
							, dto.getPeople());
			System.out.println("\t───────────────────────────────────────────────────────────────────────────");			
			
		}
	}//roomList()

	/**
	 * 기초 강의실 등록 메서드이다.
	 */
	private void addRoomInfoMenu() {
		
		view.addRoomHeader();
		
		System.out.print("\t█ 강의실명 : ");
		String name = scan.nextLine();
		
		System.out.print("\t█ 수용인원 : ");
		String people = scan.nextLine();
		
		RoomDTO rdto = new RoomDTO();
		rdto.setName(name);
		rdto.setPeople(people);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseAddOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addRoomInfo(rdto);
				return;
			} else {
				loop = false;
			}
		}
	}//addRoomMenu()
	
	
	/**
	 * 새로운 강의실을 등록하는 메서드이다.
	 * @param rdto
	 */
    private void addRoomInfo(RoomDTO rdto) {
		
    	int result = rdao.addRoom(rdto);
    	view.addResult(result);
		
	}//addRoom(RoomDTO rdto)

    
    /**
     * 기존 강의실 정보를 수정하는 메서드이다.
     */
	private void updateRoomInfo() {
		
		view.updateRoomHeader();//헤더
		
		//view
		
		roomList(); //전체 강의실
		
		System.out.print("\n\t█ 강의실 번호 : ");
		String seqRoom = scan.nextLine();
		
		RoomDTO dto = rdao.get(seqRoom);
		
		System.out.println();
		System.out.println("\t* 강의실번호 : " + dto.getSeqRoom());
		System.out.println("\t* 강의실명 : " + dto.getName());
		System.out.println("\t* 수용인원 : " + dto.getPeople());
		System.out.println("\n");
		
		System.out.println("\t\t  수정을 원치 않는 항목은 엔터를 입력하세요.\n");
		
		System.out.print("\t█ 수정할 강의실명 : ");
		String name = scan.nextLine();
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t█ 수정할 수용인원 : ");
		String people = scan.nextLine();
		if (people.equals("")) {
			people = dto.getPeople();
		}
		
		RoomDTO dto2 = new RoomDTO();
		
		dto2.setSeqRoom(seqRoom);
		dto2.setName(name);
		dto2.setPeople(people);
		
		int result = rdao.updateRoom(dto2);
		
		view.updateResult(result);
		
		
	}//updateRoomInfo()

    
    
    
    /**
     * 강의실 삭제 메뉴 메서드이다.
     */
    private void deleteRoomInfoMenu() {
    	
    	view.deleteRoomHeader();
    	
   
    	roomList(); //전체 강의실 목록
    	
		System.out.print("\t█ 강의실 번호 : ");
		String seqRoom = scan.nextLine();
    	
		boolean loop = true;
		while (loop) {
			
			view.chooseDeleteOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				deleteRoomInfo(seqRoom);
				return;
			} else {
				loop = false;
			}
		}
		
    }//deleteRoomInfoMenu()
    
    
    
	private void deleteRoomInfo(String seqRoom) {
		
		int result = rdao.deleteRoom(seqRoom);
		view.deleteResult(result);
		
	}//deleteRoomInfo(String seqRoom)
	

	/**
     * 기초 교재정보 조회, 추가, 수정, 삭제 메뉴 분기 메서드이다.
     */
	private void bookInfoMenu() {
	
		boolean loop = true;
		while (loop) {
			
			view.bookInfoMenu();	//교재 정보 관리 메뉴(CRUD)
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				view.bookListHeader();
				bookList();
				pause();
			} else if (num.equals("2")) {
				addBookInfoMenu();
			} else if (num.equals("3")) {
				updateBookInfo();
				pause();
			} else if (num.equals("4")) {
				deleteBookInfoMenu();
				pause();
			} else {
				System.out.println("\n\t\t※ 올바르지 않은 번호입니다.");
				loop = false;
			}//if	
			
		}//while
		
	}//bookInfoMenu()
	
	
	/**
	 * 기초 교재 조회 메서드이다.
	 */
	private void bookList() {
		
		view.bookListHeader2();//헤더
		
		ArrayList<BookDTO> list = bdao.bookList();
		
		for (BookDTO dto : list) {
			
			System.out.printf("\t%4s\t%-21s\t%10s\t%10s\n"
					, dto.getSeqBook()
					, dto.getName()
					, dto.getPublisher()
					, dto.getWriter());
			System.out.println("\t───────────────────────────────────────────────────────────────────────────");			
		}
		
	}
	
	
	/**
	 * 기초 교재정보 등록 메서드이다.
	 */
	private void addBookInfoMenu() {
		
		view.addBookHeader();//헤더
		
		System.out.print("\t█ 교재이름 : ");
		String name = scan.nextLine();
		
		System.out.print("\t█ 출판사 : ");
		String publisher = scan.nextLine();
		
		System.out.print("\t█ 저자명 : ");
		String writer = scan.nextLine();
		
		BookDTO bdto = new BookDTO();
		bdto.setName(name);
		bdto.setPublisher(publisher);
		bdto.setWriter(writer);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseAddOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addBookInfo(bdto);
				return;
			} else {
				loop = false;
			}

		}

	}//addBookInfoMenu()
	
	
	/**
	 * 새로운 교재를 등록하는 메서드이다.
	 * @param bdto 교재 데이터 객체
	 */
	private void addBookInfo(BookDTO bdto) {
		
		int result = bdao.addBook(bdto);
		view.addResult(result);
	}
	
	
	private void updateBookInfo() {
		
		view.updateBookHeader();//view
		
		bookList();
		
		System.out.print("\n\t█ 교재 번호 : ");
		String seqBook = scan.nextLine();
		
		BookDTO dto = bdao.get(seqBook);
		
		System.out.println();
		System.out.println("\t* 교재번호 : " + dto.getSeqBook());
		System.out.println("\t* 교재이름 : " + dto.getName());
		System.out.println("\t* 출판사 : " + dto.getPublisher());
		System.out.println("\t* 저자명 : " + dto.getWriter());
		System.out.println("\n");
		
		System.out.println("\t\t  수정을 원치 않는 항목은 엔터를 입력하세요.\n");
		
		System.out.print("\t█ 수정할 교재이름 : ");
		String name = scan.nextLine();
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t█ 수정할 출판사 : ");
		String publisher = scan.nextLine();
		if (publisher.equals("")) {
			publisher = dto.getPublisher();
		}
		
		System.out.print("\t█ 수정할 저자명 : ");
		String writer = scan.nextLine();
		if (writer.equals("")) {
			writer = dto.getWriter();
		}
		
		BookDTO dto2 = new BookDTO();
		
		dto2.setSeqBook(seqBook);
		dto2.setName(name);
		dto2.setPublisher(publisher);
		dto2.setWriter(writer);
		
		int result = bdao.updateBook(dto2);
		
		view.updateResult(result);
		
	}//updateBookInfo()
	
	
	/**
	 * 교재 삭제 메뉴 메서드이다.
	 */
	private void deleteBookInfoMenu() {
		
		view.deleteBookHeader();//헤더
		bookList();
		
		System.out.print("\t█ 교재 번호 : ");
		String seqBook = scan.nextLine();
    	
		boolean loop = true;
		while (loop) {
			view.chooseDeleteOrNot();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				deleteBookInfo(seqBook);
				return;
			} else {
				loop = false;
			}
		}
		
	}

	/**
	 * 교재 삭제 메서드이다.
	 * @param seqBook 교재 번호
	 */
	private void deleteBookInfo(String seqBook) {
		
		int result = bdao.deleteBook(seqBook);
		view.deleteResult(result);
		
	}
	
	
	
	
	
}
