package com.project.student;

import java.util.Scanner;

import com.project.admin.CompanyInfo;
import com.project.dao.CompanyInfoDAO;
/**
 * 교육생 연계기업 채용공고 조회 클래스
 * @author 혜승
 *
 */
public class CompanyInfoCheck {


		private static Scanner scan;
		private StudentView view;
		public CompanyInfoDAO dao;
		private CompanyInfo CompanyInfo;
		
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
			CompanyInfo = new CompanyInfo();
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
			CompanyInfo.list(); //전체목록 출력
			
			CompanyInfo.pause();
		} else if (num.equals("2")) {
			CompanyInfo.comField();
			
			CompanyInfo.pause();
		} else if(num.equals("3")) {
			CompanyInfo.address();
			
			CompanyInfo.pause();
		} else if(num.equals("4")) {
			CompanyInfo.salary();
			
			CompanyInfo.pause();
		} else if(num.equals("5")) {
			CompanyInfo.state();
			
			CompanyInfo.pause();
			
		}else if(num.equals("6")) {
			loop = false;
			 
		}else {
			System.out.println("\n없는 번호입니다. 다시입력해주세요.");

		}
		
	}//while
	
		
		
	}
	
}
