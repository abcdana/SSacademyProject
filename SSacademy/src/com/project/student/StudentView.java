package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.CompanyInfo;
import com.project.admin.GetJobInfo;
import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.admin.dto.VwEmpStatusDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.student.dto.StudentCourseListDTO;

/**
 * 교육생 뷰 입니다.
 * @author 김다은
 *
 */
public class StudentView {
	
	/**
	 * 교육생의 메인메뉴를 출력하는 메소드이다.
	 */
	public void menu() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교육생 메인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. My Page\t\t5. 채용공고 조회\t\t  │");
		System.out.println("\t│\t\t2. 출결 확인\t\t6. 취업현황 조회\t\t  │");
		System.out.println("\t│\t\t3. 성적 조회\t\t7. 우수훈련생 확인\t\t  │");
		System.out.println("\t│\t\t4. 강의 평가\t\t\t\t\t\t  │");
		System.out.println("\t│\t\t0. 로그 아웃\t\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}


	
	
	
	///////////////////////////////다은///////////////////////////////////////
	
	/**
	 * 교육생모드의 출결관리 메뉴를 출력하는 메서드이다.
	 */
	public void attendanceMenu() {
		
		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t1. 입퇴실 체크\t\t2. 출결 조회\t\t0. 뒤로 가기\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	
	}

	/**
	 * 입퇴실 체크 헤더를 출력하는 메서드이다.
	 */
	public void checkAttend() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 입퇴실 체크\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		System.out.print("\t█ 입/퇴실 체크를 하시겠습니까? (Y/N) : ");
	}
	
	/**
	 * 출결 조회 헤더를 출력하는 메서드이다.
	 */
	public void attendanceList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 출결 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
	}
	
	
	/**
	 * 교육생 모드의 출결관리 중 출결 조회 메뉴를 출력하는 메서드이다.
	 */
	public void attendPeriodMenu() {

		System.out.println();
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t1. 전체 조회\t\t2. 월별 조회\t\t0. 뒤로 가기\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	/**
	 * 출결 전체 조회 헤더를 출력하는 메서드이다.
	 */
	public void totAttList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 전체 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}
	
	/**
	 * 출결 월별 조회 헤더를 출력하는 메서드이다.
	 */
	public void monthAttList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t 월별 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
	}




	public void allCourseList(ArrayList<StudentCourseListDTO> sclist) {
		// TODO Auto-generated method stub
		System.out.println("교육생리스트");
	}




	/**
	 * 교육생 뷰의 출석체크의 결과를 출력하는 메소드이다.
	 * 출석체크를 성공시 "출석체크 완료"메세지를 , 실패시 "출석체크 실패" 메세지를 출력한다.
	 * @param result 0 또는 1이 저장되어 있는 변수이다.
	 */
	public void checkResult(int result) {
		
		if(result == 0) {
			System.out.println();
			System.out.println("\t\t* 출석체크 실패. *");
		} else if(result == 1) {
			System.out.println();
			System.out.println("\t\t* 출석체크 완료. *");
		}
		
	}
	
	
///////////////////////////////혜승///////////////////////////////////////
	/**
	 * 교육생 연계기업 채용공고 조회 메뉴
	 * @author 혜승
	 */
	public void menuCompanyList() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 전체 채용공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 업무별 채용공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t3. 소재지별 채용공고 조회\t\t\t\t  │");
		System.out.println("\t│\t\t4. 연봉별 채용공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 채용상태별 공고 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t6. 이전 화면으로 \t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	/**
	 * 연계기업채용공고 목록 출력 메서드
	 * @author 혜승
	 * @param list
	 */
	public void viewListCompany(ArrayList<VwCompanyInfoDTO> list) {
		int i=0;
		int j=0;
		int line = 10;
		while(true) {
			for(i =0; i < (list.size()/line)+1 ; i++) {
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("-%s page- [채용현황][번호] [회사명]\t    [채용시작일] [채용종료일] [고용형태] [연봉]    [업무]  [회사규모]\t\t[주소] \n",i+1);
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for( j=i*line; j<line+(line*i); j++ ) {
					
					if(j>=list.size()) {
						break;
					} else {
					VwCompanyInfoDTO dto = list.get(j);
					if(dto.getName().length() <= 5) {
						System.out.printf("\t%5s %-5s %-15s %10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
								dto.getState(),dto.getSeqCompanyInfo(),
								dto.getName(),dto.getStartDate(),
								dto.getEndDate(),dto.getEmploymentType(),
								dto.getSalary(),dto.getComField(),
								dto.getComSize(),dto.getAddress());
						
					} else if(dto.getName().length()>5 && dto.getName().length() <= 7) {
						System.out.printf("\t%5s %-5s %-14s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
								dto.getState(),dto.getSeqCompanyInfo(),
								dto.getName(),dto.getStartDate(),
								dto.getEndDate(),dto.getEmploymentType(),
								dto.getSalary(),dto.getComField(),
								dto.getComSize(),dto.getAddress());
					} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
						System.out.printf("\t%5s %-5s %-12s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
								dto.getState(),dto.getSeqCompanyInfo(),
								dto.getName(),dto.getStartDate(),
								dto.getEndDate(),dto.getEmploymentType(),
								dto.getSalary(),dto.getComField(),
								dto.getComSize(),dto.getAddress());
					} else if(dto.getName().length()>9) {
							System.out.printf("\t%5s %-5s %-11s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
									dto.getState(),dto.getSeqCompanyInfo(),
									dto.getName(),dto.getStartDate(),
									dto.getEndDate(),dto.getEmploymentType(),
									dto.getSalary(),dto.getComField(),
									dto.getComSize(),dto.getAddress());
					}//elseif
		
					}//if
			}//forj
		        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
				System.out.println("\t│\t\t1. 이전 페이지\t\t\t\t\t\t  │");
				System.out.println("\t│\t\t2. 다음 페이지\t\t\t\t\t\t  │");
				System.out.println("\t│\t\t3. 이전 화면으로\t\t\t\t\t  │");
				System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
				System.out.print("\t█ 원하시는 번호를 입력하세요. : ");
				Scanner scan = new Scanner(System.in);
				String num = scan.nextLine();
				
				if(num.equals("1")) {
					if(i==0){
						i-=1;
						System.out.println();
						System.out.println("\t\t**첫 페이지입니다");
				
					}else{
						i-=2;
					}
				} else if(num.equals("2")) {
					if (j>=list.size()){
						i -= 1;
						System.out.println();
						System.out.println("\t\t**페이지가 끝났습니다.");
					
					} 
				} else if(num.equals("3")) {
					CompanyInfoCheck ci = new CompanyInfoCheck();
					ci.menu();
					
				}

			}//fori
		}//while
		
}

	
	/**
	 * 교육생 수료생 취업정보 조회 메뉴
	 * @author 혜승
	 */
	public void menuGetJobInfoCheck() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 전체 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 업무별 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t3. 소재지별 취업정보 조회\t\t\t\t  │");
		System.out.println("\t│\t\t4. 연봉별 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 연도별 취업정보 조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t6. 연계기업 취업정보 조회\t\t\t\t  │");
		System.out.println("\t│\t\t7. 이전 화면으로 \t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	}
	
	/**
	 * 수료생 취업정보 조회 문 출력 형식문
	 * @author 혜승
	 * @param list
	 */
	
	public void GetJobList(ArrayList<VwGetJobInfoDTO> list) {
		
		int i=0;
		int j=0;
		int line = 10;
		while(true) {
			for(i =0; i < (list.size()/line)+1 ; i++) {
				System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("-%s page- [취업번호][이름][ID]  \t[회사명]\t\t[고용형태] \t[연봉] \t[취업일] \t[업무] \t\t\t[회사주소]\t\t\t\t[수료과정]\n",i+1);
				System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for( j=i*line; j<line+(line*i); j++ ) {
					
					if(j>=list.size()) {
						break;
					} else {
					VwGetJobInfoDTO dto = list.get(j);
				if(dto.getCompanyName().length() <= 5) {
					System.out.printf("\t%6s %-5s %-5s %-15s %-10s %-10s %-10s \t\t%-10s \t%-30s \t%-30s\n", 
							dto.getGjseq(), dto.getName(), dto.getId(),
							dto.getCompanyName(), 
							dto.getForm(), dto.getSalary(),
							dto.getGetJobDate(),dto.getDuty(),
							dto.getLocation(),dto.getCourse());
					
				} else if(dto.getCompanyName().length()>5 && dto.getName().length() <= 7) {
					System.out.printf("\t%6s %-5s %-5s %-14s %-10s %-10s %-10s \t%-10s \t%-30s \t%-30s \n", 
							dto.getGjseq(), dto.getName(), dto.getId(),
							dto.getCompanyName(), 
							dto.getForm(), dto.getSalary(),
							dto.getGetJobDate(),dto.getDuty(),
							dto.getLocation(),dto.getCourse());
				} else if(dto.getCompanyName().length()>7 && dto.getName().length() <= 9) {
					System.out.printf("\t%6s %-5s %-5s %-12s %-10s %-10s %-10s \t%-10s \t%-30s \t%-30s \n", 
							dto.getGjseq(), dto.getName(), dto.getId(),
							dto.getCompanyName(), 
							dto.getForm(), dto.getSalary(),
							dto.getGetJobDate(),dto.getDuty(),
							dto.getLocation(),dto.getCourse());
				} else if(dto.getCompanyName().length()>9) {
						System.out.printf("\t%6s %-5s %-5s %-11s %-10s %-10s %-10s \t%-10s \t%-30s \t%-30s \n", 
								dto.getGjseq(), dto.getName(), dto.getId(),
								dto.getCompanyName(), 
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getDuty(),
								dto.getLocation(),dto.getCourse());
				}
				}//if
			}//forj
				 System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
					System.out.println("\t│\t\t1. 이전 페이지\t\t\t\t\t\t  │");
					System.out.println("\t│\t\t2. 다음 페이지\t\t\t\t\t\t  │");
					System.out.println("\t│\t\t3. 이전 화면으로\t\t\t\t\t  │");
					System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
					System.out.print("\t█ 원하시는 번호를 입력하세요. : ");
					Scanner scan = new Scanner(System.in);
					String num = scan.nextLine();
					
					if(num.equals("1")) {
						if(i==0){
							i-=1;
							System.out.println();
							System.out.println("\t\t**첫 페이지입니다");
					
						}else{
							i-=2;
						}
					} else if(num.equals("2")) {
						if (j>=list.size()){
							i -= 1;
							System.out.println();
							System.out.println("\t\t**페이지가 끝났습니다.");
						
						} 
					} else if(num.equals("3")) {
						GetJobInfoCheck gj = new GetJobInfoCheck();
						gj.menu();
						
					}
			}//fori

		}//while
		
		
		
	}
	
	/**
	 * 연계기업취업정보 목록 형태 
	 * @author 혜승 
	 * @param list
	 */
	public void cjobList(ArrayList<VwEmpStatusDTO> list) {
		
		
		int i=0;
		int j=0;
		int line = 10;
		while(true) {
			for(i =0; i < (list.size()/line)+1 ; i++) {
				System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("-%s page- [등록번호][이름][ID]  \t[회사명]\t\t[채용형태] \t[연봉] \t[취업일] \t[업무] \t\t\t[회사주소]\t\t\t\t\t[수료과정]\n",i+1);
				System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for( j=i*line; j<line+(line*i); j++ ) {
					
					if(j>=list.size()) {
						break;
					} else {
					VwEmpStatusDTO dto = list.get(j);

					if(dto.getCompanyName().length() <= 5) {

						System.out.printf("\t%6s %-5s %-5s %-15s %-10s %-10s %-10s \t\t%-10s \t%-30s \t%-30s\n", 
								dto.getSeq(), dto.getName(), dto.getId(),
								dto.getCompanyName(), 
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getDuty(),
								dto.getLocation(),dto.getCourse());
						
					} else if(dto.getCompanyName().length()>5 && dto.getName().length() <= 7) {
						System.out.printf("\t%6s %-5s %-5s %-14s %-10s %-10s %-10s \t%-10s \t%-30s \t%-30s\n", 
								dto.getSeq(),dto.getName(), dto.getId(),
								dto.getCompanyName(), 
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getDuty(),
								dto.getLocation(),dto.getCourse());
					} else if(dto.getCompanyName().length()>7 && dto.getName().length() <= 9) {
						System.out.printf("\t%6s %-5s %-5s %-12s %-10s %-10s %-10s \t%-10s \t%-30s \t%-30s\n", 
								dto.getSeq(), dto.getName(), dto.getId(),
								dto.getCompanyName(), 
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getDuty(),
								dto.getLocation(),dto.getCourse());
					} else if(dto.getCompanyName().length()>9) {
							System.out.printf("\t%6s %-5s %-5s %-11s %-10s %-10s %-10s \t%-10s \t%-30s \t%-30s\n", 
									dto.getSeq(), dto.getName(), dto.getId(),
									dto.getCompanyName(), 
									dto.getForm(), dto.getSalary(),
									dto.getGetJobDate(),dto.getDuty(),
									dto.getLocation(),dto.getCourse());
					}//elseif
					
				}//if
			}//forj
				 System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
					System.out.println("\t│\t\t1. 이전 페이지\t\t\t\t\t\t  │");
					System.out.println("\t│\t\t2. 다음 페이지\t\t\t\t\t\t  │");
					System.out.println("\t│\t\t3. 이전 화면으로\t\t\t\t\t  │");
					System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
					System.out.print("\t█ 원하시는 번호를 입력하세요. : ");
					Scanner scan = new Scanner(System.in);
					String num = scan.nextLine();
					
					if(num.equals("1")) {
						if(i==0){
							i-=1;
							System.out.println();
							System.out.println("\t\t**첫 페이지입니다");
					
						}else{
							i-=2;
						}
					} else if(num.equals("2")) {
						if (j>=list.size()){
							i -= 1;
							System.out.println();
							System.out.println("\t\t**페이지가 끝났습니다.");
						
						} 
					} else if(num.equals("3")) {
						GetJobInfoCheck gj = new GetJobInfoCheck();
						gj.menu();
						
					}
			}//fori

		}//while
		
		
			
		}
		



}
