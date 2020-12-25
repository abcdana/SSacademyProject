package com.project.student;

import java.util.Scanner;

import com.project.admin.GetJobInfo;

/**
 * 교육생 수료생 취업정보 조회 클래스
 * @author 혜승
 *
 */
public class GetJobInfoCheck {
		private static Scanner scan;

		public StudentView view;
		public GetJobInfo GetJobInfo;
		
		static{
			scan = new Scanner(System.in);
		}
		/**
		 * 교육생 수료생 취업정보 조회 생성자
		 * @author 혜승
		 *
		 */
		public GetJobInfoCheck() {

			view = new StudentView();
			GetJobInfo = new GetJobInfo();
		}


		/**
		 * 교육생 수료생 취업정보 조회 메뉴
		 * @author 혜승
		 */
		public void menu() {
			
			boolean loop = true;
		while(loop) {
			view.menuGetJobInfoCheck();//수료생 취업정보 관리페이지 메뉴출력문 메서드
			String num = scan.nextLine();
			
			if(num.equals("1")) {
				GetJobInfo.jobList();
				
				GetJobInfo.pause();
			} else if(num.equals("2")) {
				GetJobInfo.duty();
				
				GetJobInfo.pause();
			} else if(num.equals("3")) {
				GetJobInfo.location();
				
				GetJobInfo.pause();
			}else if(num.equals("4")) {
				GetJobInfo.salary();

				GetJobInfo.pause();
			} else if(num.equals("5")) {
				GetJobInfo.year();
				
				GetJobInfo.pause();
			} else if(num.equals("6")){
				GetJobInfo.cjobList();
				
				GetJobInfo.pause();
			} else if(num.equals("7")){
				loop = false;
			} else {
				System.out.println("\t**번호를 잘못 입력하셨습니다.");
				menu();
			}
			
		}//while
		
			
		}
}
