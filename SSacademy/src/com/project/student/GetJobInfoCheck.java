package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminView;
import com.project.admin.GetJobInfo;
import com.project.admin.dto.VwEmpStatusDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.dao.EmpStatusDAO;
import com.project.dao.GetJobInfoDAO;

/**
 * 교육생 수료생 취업정보 조회 클래스
 * @author 혜승
 *
 */
public class GetJobInfoCheck {
		private static Scanner scan;
		public AdminView Aview;
		public StudentView view;
		public GetJobInfo GetJobInfo;
		public GetJobInfoDAO dao;
		public EmpStatusDAO daoE;
		static{
			scan = new Scanner(System.in);

		}
		/**
		 * 교육생 수료생 취업정보 조회 생성자
		 * @author 혜승
		 *
		 */
		public GetJobInfoCheck() {
			Aview = new AdminView();
			view = new StudentView();
			GetJobInfo = new GetJobInfo();
			dao = new GetJobInfoDAO();
			daoE = new EmpStatusDAO();
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
				jobList();
				
				GetJobInfo.pause();
			} else if(num.equals("2")) {
				duty();
				
				GetJobInfo.pause();
			} else if(num.equals("3")) {
				location();
				
				GetJobInfo.pause();
			}else if(num.equals("4")) {
				salary();

				GetJobInfo.pause();
			} else if(num.equals("5")) {
				year();
				
				GetJobInfo.pause();
			} else if(num.equals("6")){
				cjobList();
				
				GetJobInfo.pause();
			} else if(num.equals("7")){
				loop = false;
			} else {
				System.out.println("\t**번호를 잘못 입력하셨습니다.");
				menu();
			}
			
		}//while
		

		}//menu
		
		/**
		 * 교육생
		 * 연계기업 취업정보 조회메서드
		 * @author 조혜승
		 *
		 */
		public void cjobList() {
			Aview.cjobListHeader();
		
			
			ArrayList<VwEmpStatusDTO> list = daoE.cjobListS(null);
			
			view.cjobList(list);

			
		}
		
		/**
		 * 교육생
		 * 수료생 취업정보 전체 조회 메서드
		 * @author 조혜승
		 *
		 */
		public void jobList() {

			Aview.jobListHeader();//수료생 취업정보 조회헤더
			
			ArrayList<VwGetJobInfoDTO> list = dao.jobListS(null);
			
			view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문
			
		}

		
		/**
		 * 교육생
		 * 수료생 취업정보 연도별 취업정보 검색 메서드
		 * @author 조혜승
		 *
		 */
		public void year() { //연도별 취업정보 검색
			Aview.yearSearchGet(); //연도별 취업정보 검색헤더 및 입력요청문장

			String word = scan.nextLine();
			if(word.equals("")) {
				menu();
			}
			System.out.println();
			System.out.printf("\t\t\t\t - %s년도 취업정보 조회 -\n",word);
			System.out.println();
			
			ArrayList<VwGetJobInfoDTO> list = dao.jobListS(word);
			
			view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문
			
		}
		
		/**
		 * 교육생
		 * 수료생 취업정보 연봉별 취업정보 검색 메서드
		 * @author 조혜승
		 *
		 */
		public void salary() {//연봉별 취업정보 검색
			Aview.salarySearchGet(); //연봉별 취업정보 검색헤더및 입력요청문장

			String word = scan.nextLine();
			if(word.equals("")) {
				menu();
			}
			System.out.print("\t█ 검색 원하시는 최고금액을 입력하세요. : ");
			String word2 = scan.nextLine();
			if(word2.equals("")) {
				menu();
			}
			System.out.println();
			System.out.printf("\t\t\t - 연봉 %s 이상 %s 미만 취업정보 조회 -\n",word,word2);
			System.out.println();

			ArrayList<VwGetJobInfoDTO> list = dao.salarySearchS(word, word2);
			
			view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문
			
		}
		
		/**
		 * 교육생
		 * 수료생 취업정보 소재지별 취업정보 검색 메서드
		 * @author 조혜승
		 *
		 */
		public void location() { //소재지별 취업정보 검색
			Aview.locationSearchGet(); //소재지별 취업정보 검색헤더및 입력요청문장

			String word = scan.nextLine();
			if(word.equals("")) {
				menu();
			}
			System.out.println();
			System.out.printf("\t\t\t\t - %s 소재 채용공고 조회 -\n",word);
			System.out.println();
			ArrayList<VwGetJobInfoDTO> list = dao.locationSearchS(word);
			
			view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문
		}

		/**
		 * 교육생
		 * 수료생 취업정보 업무별 취업정보 검색 메서드
		 * @author 조혜승
		 *
		 */
		public void duty() { //업무별 취업정보 조회
			Aview.dutySearchGet();//업무별 취업정보 검색헤더및 업무목록출력헤더

			ArrayList<VwGetJobInfoDTO> list2 = dao.dutyListS();
			for(VwGetJobInfoDTO dto : list2) {
				System.out.printf("\t\t\t\t%s  \n",dto.getDuty());
			}//for
			System.out.println();
			Aview.noinputcurve();//입력값없을경우 알림 작은괄호


			System.out.print("\t█ 검색 원하시는 업무를 입력하세요. : ");
			String word = scan.nextLine();
			if(word.equals("")) {
				menu();
			}
			System.out.println();
			System.out.printf("\t\t\t\t - %s 업무 취업정보 조회 -\n",word.toUpperCase());
			System.out.println();
			ArrayList<VwGetJobInfoDTO> list = dao.dutySearchS(word.toUpperCase());
			
			view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문
			
		}
}
