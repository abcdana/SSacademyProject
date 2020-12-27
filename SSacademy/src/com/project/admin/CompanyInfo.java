package com.project.admin;

import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.dao.CompanyInfoDAO;
import com.project.dto.CompanyInfoDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;



/**
 * 연계기업채용공고 관리 클래스
 * @author 조혜승
 *
 */
public class CompanyInfo {

	   private static Scanner scan;
	   private AdminView view;
	   private CompanyInfoDAO dao;

	   static{
	      scan = new Scanner(System.in);
	      
	   }
	   /**
	    * 연계기업채용공고 관리 클래스 생성자
	    * @author 조혜승
	    * @return 
	    *
	    */
	   public CompanyInfo() {
	      view = new AdminView();
	      this.dao = new CompanyInfoDAO();
	   }
	/**
	 * 연계기업채용공고 관리 메뉴 메서드
	 * @author 조혜승
	 *
	 */

	public void menu() {
		
		
		boolean loop = true;
	while(loop) {

		view.menuCompanyInfo();// 연계기업채용공고 메뉴출력 문


		String num = scan.nextLine();
		
		if(num.equals("1")) {
			list(); //전체목록 출력
			
			pause();
		} else if(num.equals("2")) {
			search();			
			
			pause();
		}else if(num.equals("3")) {
			add();
			
			pause();
		}else if(num.equals("4")) {
			edit();
			pause();
		}else if(num.equals("5")) {
			delete();
			pause();
		} else if(num.equals("6")) {
		  loop = false;
			
		}else {
			System.out.println("\n없는 번호입니다. 다시입력해주세요.");

		}
		
	}//while
	
	
	
	}//menu

	/**
	 * 연계기업채용공고 전체목록조회메서드
	 * @author 조혜승
	 *
	 */
	public void list() { //전체목록조회
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 전체조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		ArrayList<VwCompanyInfoDTO> list = dao.list(null);
		
		view.viewListCompany(list);//목록출력메서드호출
		
		
		
		}

		
		

	/**
	 * 연계기업채용공고 검색 메뉴 메서드
	 * @author 조혜승
	 *
	 */
	private void search() { //검색메뉴

		view.searchCompany(); // 검색메뉴 출력 화면 호출
		
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			comField();
			
		} else if(num.equals("2")) {
			address();
			
		} else if(num.equals("3")) {
			salary();

		} else if(num.equals("4")) {
			state();
		}else if(num.equals("5")) {
			menu();
		} else {
			System.out.println("\t잘못입력된 번호 입니다.");
		}
		
		
	}
	/**
	 * 연계기업채용공고 채용상태별 공고 검색 메뉴 메서드
	 * @author 조혜승
	 *
	 */
	public void state() {
		view.stateSearchCompany();//채용상태별 채용공고 검색 출력화면 헤더

		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 공고 조회 -\n",word);
		System.out.println();
		ArrayList<VwCompanyInfoDTO> list = dao.list(word);
		view.viewListCompany(list); //목록출력 메서드 호출
	}

	public void salary() { //연봉별 채용 공고  검색
		view.salarySearchCompany();//연봉별 채용공고 검색 출력문 헤더
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.print("\t█ 검색 원하시는 최고금액을 입력하세요. : ");
		String word2 = scan.nextLine();
		System.out.println();
		if(word2.equals("")) {
			menu();
		}
		System.out.printf("\t\t\t - 연봉 %s 이상 %s 미만 채용공고 조회 -\n",word,word2);
		System.out.println();

		ArrayList<VwCompanyInfoDTO> list = dao.salary(word,word2);

		view.viewListCompany(list); //목록출력 메서드 호출
	}
	
	/**
	 * 연계기업채용공고 소재지별별 공고 검색 메뉴 메서드
	 * @author 조혜승
	 *
	 */
	public void address() { 
		view.addressSearchCompany();//소재지별 채용공고 검색 출력문 헤더
		
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 소재 채용공고 조회 -\n",word);
		System.out.println();
		ArrayList<VwCompanyInfoDTO> list = dao.city(word);
		view.viewListCompany(list); //목록출력 메서드 호출
		
	}
	/**
	 * 연계기업채용공고 업무별 공고 검색 메뉴 메서드
	 * @author 조혜승
	 *
	 */
	public void comField() { //업무별 채용공고 검색
		view.comFieldSearchCompany();//업무별 채용공고 검색 출력문 헤더

		ArrayList<VwCompanyInfoDTO> list2 = dao.comfield();
		for(VwCompanyInfoDTO dto : list2) {
			System.out.printf("\t\t\t\t%s  \n",dto.getComField());
		}
		System.out.println();
		view.noinputcurve();
		System.out.println();
		System.out.print("\t█ 원하시는 검색어를 입력하세요. : ");
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 업무 채용공고 조회 -\n",word.toUpperCase());
		System.out.println();

		ArrayList<VwCompanyInfoDTO> list = dao.comField(word.toUpperCase());
		view.viewListCompany(list); //목록출력 메서드 호출
	}
	/**
	 * 연계기업채용공고 등록 메서드
	 * @author 조혜승
	 *
	 */
	private void add() { //채용공고 등록
		view.addCompanyInfo(); //연계기업채용공고 등록 출력문 헤더
		System.out.print("\t회사 이름 : ");
		String name = scan.nextLine();
		System.out.print("\t채용 시작일 : ");
		String startDate = scan.nextLine();
		System.out.print("\t채용 종료일 : ");
		String endDate = scan.nextLine();
		//boolean bool = seDate(startDate,endDate);
		System.out.print("\t채용 분야 : ");
		String comField = scan.nextLine();
		System.out.print("\t연봉 : ");
		String salary = scan.nextLine();
		System.out.print("\t채용 형태 : ");
		String employmentType = scan.nextLine();
		System.out.print("\t회사 규모 : ");
		String comSize = scan.nextLine();
		System.out.print("\t회사 주소 : ");
		String address = scan.nextLine();
		
		if(	name.equals("") || startDate.equals("") || endDate.equals("") || comField.equals("") || 
				salary.equals("") || employmentType.equals("") || comSize.equals("") || address.equals(""))  {
				System.out.println();
				System.out.println("\t**모든 값을 입력해야 합니다. 이전화면으로 돌아갑니다.");
				System.out.println("\t엔터를 입력해주세요.");
				scan.nextLine();
				menu();
			}
		
		CompanyInfoDTO dto = new CompanyInfoDTO();
		dto.setName(name);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setComField(comField.toUpperCase());
		dto.setSalary(salary);
		dto.setEmploymentType(employmentType);
		dto.setComSize(comSize);
		dto.setAddress(address);
		
		int result = dao.add(dto);
		
		view.addResult(result);//등록 결과를 출력하는 메소드이다.

		
		
	}

	
	/**
	 * 연계기업채용공고 수정 메서드
	 * @author 조혜승
	 *
	 */
	private void edit() { // 연계기업 채용공고 수정
		
			view.editCompany();//연계기업채용공고 수정 출력문 헤더

			ArrayList<VwCompanyInfoDTO> list = dao.list(null);
			view.editListCompany(list); //목록출력 메서드 호출
			view.noinputcurve();


		
	}
	/**
	 * 연계기업 채용공고 수정 번호선택 및 출력
	 *  @author 조혜승
	 */
	public void editSelect() {
		System.out.println();
		System.out.println("\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.print("\t█ 수정 원하시는 번호를 입력하세요. : ");
		String seq = scan.nextLine();
		if(seq.equals("")) {
			menu();
		}
		System.out.println();
		VwCompanyInfoDTO dto = dao.editGet(seq);
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t회사 이름: " + dto.getName());
		System.out.println("\t\t\t채용 시작일: " + dto.getStartDate());
		System.out.println("\t\t\t채용 종료일: " + dto.getEndDate());
		System.out.println("\t\t\t채용 분야: " + dto.getComField());
		System.out.println("\t\t\t연봉: " + dto.getSalary());
		System.out.println("\t\t\t채용 형태: " + dto.getEmploymentType());
		System.out.println("\t\t\t회사 규모: " + dto.getComSize());
		System.out.println("\t\t\t회사 주소: " + dto.getAddress());
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.println("\t**수정을 원하지 않는 항목은 엔터를 입력하세요.");
		System.out.print("\t수정할 회사 이름: ");
		String name = scan.nextLine();
		if(name.equals("")) {
			name = dto.getName();
		}
		System.out.print("\t수정할 채용 시작일: ");
		String startDate = scan.nextLine();
		if(startDate.equals("")) {
			 startDate = dto.getStartDate();
		}
		System.out.print("\t수정할 채용 종료일: ");
		String endDate = scan.nextLine();
		if(endDate.equals("")) {
			endDate = dto.getEndDate();
		}
		System.out.print("\t수정할 채용 분야: ");
		String comField = scan.nextLine();
		if(comField.equals("")) {
			comField = dto.getComField();
		}
		System.out.print("\t수정할 연봉: ");
		String salary = scan.nextLine();
		if(salary.equals("")) {
			salary = dto.getSalary();
		}
		System.out.print("\t수정할 채용 형태: ");
		String employmentType = scan.nextLine();
		if(employmentType.equals("")) {
			employmentType = dto.getEmploymentType();
		}
		System.out.print("\t수정할 회사 규모: ");
		String comSize = scan.nextLine();
		if(comSize.equals("")) {
			comSize = dto.getComSize();
		}
		System.out.print("\t수정할 회사 주소: ");
		String address = scan.nextLine();
		if(address.equals("")) {
			address = dto.getAddress();
		}
		
		CompanyInfoDTO dto2= new CompanyInfoDTO();
		dto2.setSeqCompanyInfo(seq);
		dto2.setName(name);
		dto2.setStartDate(startDate);
		dto2.setEndDate(endDate);
		dto2.setComField(comField.toUpperCase());
		dto2.setSalary(salary);
		dto2.setEmploymentType(employmentType);
		dto2.setComSize(comSize);
		dto2.setAddress(address);
		
		int result = dao.edit(dto2);
		
		view.updateResult(result); //수정완료 출력문
		
	}
	/**
	 * 연계기업채용공고 삭제 메서드
	 * @author 조혜승
	 *
	 */
	private void delete() { //채용공고 삭제
		view.deleteCompany();

		ArrayList<VwCompanyInfoDTO> list = dao.list(null);
		view.deleteListCompany(list); //목록출력 메서드 호출

		
	}
	/**
	 * 연계기업채용공고 삭제 번호선택 및 출력
	 *  @author 조혜승
	 */
	public void deleteSelect() {
		
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.print("\t█ 삭제 원하시는 번호를 입력하세요. : ");
		String seq = scan.nextLine();
		if(seq.equals("")) {
			menu();
		}
		System.out.println();
		VwCompanyInfoDTO dto = dao.editGet(seq);
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t회사 이름 : " + dto.getName());
		System.out.println("\t\t\t채용 시작일 : " + dto.getStartDate());
		System.out.println("\t\t\t채용 종료일 : " + dto.getEndDate());
		System.out.println("\t\t\t채용 분야 : " + dto.getComField());
		System.out.println("\t\t\t연봉 : " + dto.getSalary());
		System.out.println("\t\t\t채용 형태 : " + dto.getEmploymentType());
		System.out.println("\t\t\t회사 규모 : " + dto.getComSize());
		System.out.println("\t\t\t회사 주소 : " + dto.getAddress());
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t정말로 삭제 하시겠습니까 ? (y|n) : ");
		String input = scan.nextLine();
		if(input.toLowerCase().equals("y")) {
			int result = dao.delete(seq);
			view.deleteResult(result); //삭제성공유무출력
		} else if (input.toLowerCase().equals("n")){
			System.out.println("\t이전화면으로 돌아갑니다. ");
		} else {
			System.out.println("\t잘못 입력했습니다. 다시 입력해주세요.");
			delete();
		}
		
	}
	/**
	 * 메뉴로 다시돌아가는 메서드
	 * @author 혜승
	 */
	public void pause() {
		System.out.println();
		System.out.println("\t█ 계속하시려면 엔터를 입력해주세요.");
		scan.nextLine();
		
	}
	
}//AdminCompany
