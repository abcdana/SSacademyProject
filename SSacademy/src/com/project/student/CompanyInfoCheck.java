package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.admin.CompanyInfo;
import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.dao.CompanyInfoDAO;
/**
 * 교육생 연계기업 채용공고 조회 클래스
 * @author 혜승
 *
 */
public class CompanyInfoCheck {


		private static Scanner scan;
		private StudentView view;
		private AdminView aview;
		public CompanyInfoDAO dao;

		
		static{
			scan = new Scanner(System.in);
		}
		/**
		 * 교육생 연계기업 채용공고 조회 클래스 생성자
		 * @author 혜승
		 */
		public CompanyInfoCheck() {
			dao = new CompanyInfoDAO();
			view = new StudentView();
			aview = new AdminView();

		}



		/**
		 * 연계기업채용공고 조회 메뉴 메서드
		 * @author 혜승
		 */
	public void menu() {
		
		boolean loop = true;
	while(loop) {

		view.menuCompanyList();// 연계기업채용공고 조회 메뉴출력 문

		String num = scan.nextLine();
		
		if(num.equals("1")) {
			list(); //전체목록 출력
			
			pause();
		} else if (num.equals("2")) {
			comField();
			
			pause();
		} else if(num.equals("3")) {
			address();
			
			pause();
		} else if(num.equals("4")) {
			salary();
			
			pause();
		} else if(num.equals("5")) {
			state();
			
			pause();
			
		}else if(num.equals("6")) {
			loop = false;
			 
		}else {
			System.out.println("\n없는 번호입니다. 다시입력해주세요.");

		}
		
	}//while
	
		
		
	}

		/**
		 *교육생 연계기업 채용공고 전체조회 메서드
		 *@author 혜승 
		 * 
		 */
		private void list() {
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t연계기업 채용공고 전체조회\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
			ArrayList<VwCompanyInfoDTO> list = dao.list(null);
			view.viewListCompany(list);//목록출력메서드호출
			
		}
		/**
		 *교육생 연계기업 채용공고 채용상태별 검색 메서드
		 *@author 혜승
		 */
		public void state() {
			aview.stateSearchCompany();//채용상태별 채용공고 검색 출력화면 헤더

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

		/**
		 *교육생 연계기업 채용공고 연봉별 검색 메서드
		 *@author 혜승
		 */
		public void salary() { //연봉별 채용 공고  검색
			aview.salarySearchCompany();//연봉별 채용공고 검색 출력문 헤더
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
		 *교육생 연계기업 소재지별공고 연봉별 검색 메서드
		 *@author 혜승
		 */
		public void address() { 
			aview.addressSearchCompany();//소재지별 채용공고 검색 출력문 헤더
			
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
			aview.comFieldSearchCompany();//업무별 채용공고 검색 출력문 헤더

			ArrayList<VwCompanyInfoDTO> list2 = dao.comfield();
			for(VwCompanyInfoDTO dto : list2) {
				System.out.printf("\t\t\t\t%s  \n",dto.getComField());
			}
			System.out.println();
			aview.noinputcurve();
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
		
		public void pause() {
			System.out.println();
			System.out.println("\t█ 계속하시려면 엔터를 입력해주세요.");
			scan.nextLine();
			
		}
}
