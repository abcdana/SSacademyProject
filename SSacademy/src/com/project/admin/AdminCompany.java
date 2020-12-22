package com.project.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.dao.CompanyInfoDAO;
import com.project.dto.CompanyInfoDTO;
/**
 * 연계기업채용공고 관리페이지입니다.
 * @author 조혜승
 *
 */
public class AdminCompany {

	private static Scanner scan;
	private static CompanyInfoDAO dao;
	
	static{
		scan = new Scanner(System.in);
		dao = new CompanyInfoDAO();
		
	}
	public static void main(String[] args) {
		menu();
	}
	public static void menu() {
		
	

		
		boolean loop = true;
	while(loop) {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 관리페이지\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t\t\t\t - 전체 채용공고 조회 -");
		System.out.println();
			list(); //전체목록 출력
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 채용공고 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 채용공고 등록\t\t\t\t\t  │");
		System.out.println("\t│\t\t3. 채용공고 수정\t\t\t\t\t  │");
		System.out.println("\t│\t\t4. 채용공고 삭제\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 이전 화면으로\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			search();
			
			pause();
		} else if(num.equals("2")) {
			add();
			
			pause();
		}else if(num.equals("3")) {
			edit();
			
			pause();
		}else if(num.equals("4")) {
			delete();
			pause();
		}else if(num.equals("5")) {
			
		} else {
			System.out.println("\n없는 번호입니다. 다시입력해주세요.");
		}
		
	}//while
	
	
	
	}//menu

	private static void list() { //전체목록조회
		
		ArrayList<VwCompanyInfoDTO> list = dao.list(null);
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("[채용현황][번호] [회사명]\t    [채용시작일] [채용종료일] [채용형태] [연봉]    [채용업무]  [회사규모]\t\t[주소]");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for(VwCompanyInfoDTO dto : list) {
			
			if(dto.getName().length() <= 5) {

				System.out.printf("%5s %-5s %-15s %10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
				
			} else if(dto.getName().length()>5 && dto.getName().length() <= 7) {
				System.out.printf("%5s %-5s %-14s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
				System.out.printf("%5s %-5s %-12s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
					System.out.printf("%5s %-5s %-11s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
							dto.getState(),dto.getSeqCompanyInfo(),
							dto.getName(),dto.getStartDate(),
							dto.getEndDate(),dto.getEmploymentType(),
							dto.getSalary(),dto.getComField(),
							dto.getComSize(),dto.getAddress());
			}

			
		}
		
		
		
	}

	private static void search() { //채용공고 검색
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 업무별 채용공고 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 소재지별 채용공고 검색\t\t\t\t  │");
		System.out.println("\t│\t\t3. 연봉별 채용공고 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t4. 채용상태별 공고 검색\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			comField();
			
		} else if(num.equals("2")) {
			address();
			
		} else if(num.equals("3")) {
			salary();

		} else if(num.equals("4")) {
			state();
		}
		
		
	}

	private static void state() {//채용상태별 공고 검색
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t채용상태별 채용공고 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t 검색어 : 채용중 ");
		System.out.println("\t\t\t\t 검색어 : 채용예정 ");
		System.out.println("\t\t\t\t 검색어 : 채용마감 ");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 검색어를 입력하세요. : ");
		String word = scan.nextLine();
		System.out.println();
		System.out.printf("\t\t\t\t - %s 공고 조회 -\n",word);
		System.out.println();
		ArrayList<VwCompanyInfoDTO> list = dao.list(word);
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("[채용현황][번호] [회사명]\t    [채용시작일] [채용종료일] [채용형태] [연봉]    [채용업무]  [회사규모]\t\t[주소]");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for(VwCompanyInfoDTO dto : list) {
			
			if(dto.getName().length() <= 5) {

				System.out.printf("%5s %-5s %-15s %10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
				
			} else if(dto.getName().length()>5 && dto.getName().length() <= 7) {
				System.out.printf("%5s %-5s %-14s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
				System.out.printf("%5s %-5s %-12s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
					System.out.printf("%5s %-5s %-11s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
							dto.getState(),dto.getSeqCompanyInfo(),
							dto.getName(),dto.getStartDate(),
							dto.getEndDate(),dto.getEmploymentType(),
							dto.getSalary(),dto.getComField(),
							dto.getComSize(),dto.getAddress());
			}
		}
	}

	private static void salary() { //연봉별 채용 공고  검색
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연봉별 채용공고 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t 검색어 예) 검색 최저금액 : 20000000 (이상)");
		System.out.println("\t\t\t 검색어 예) 검색 최고금액 : 30000000 (미만)");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 검색 원하시는 최저금액을 입력하세요. : ");
		String word = scan.nextLine();
		System.out.print("\t█ 검색 원하시는 최고금액을 입력하세요. : ");
		String word2 = scan.nextLine();
		System.out.println();
		System.out.printf("\t\t\t - 연봉 %s 이상 %s 미만 채용공고 조회 -\n",word,word2);
		System.out.println();
		ArrayList<VwCompanyInfoDTO> list = dao.salary(word,word2);
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("[채용현황][번호] [회사명]\t    [채용시작일] [채용종료일] [채용형태] [연봉]    [채용업무]  [회사규모]\t\t[주소]");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for(VwCompanyInfoDTO dto : list) {
			
			if(dto.getName().length() <= 5) {

				System.out.printf("%5s %-5s %-15s %10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
				
			} else if(dto.getName().length()>5 && dto.getName().length() <= 7) {
				System.out.printf("%5s %-5s %-14s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
				System.out.printf("%5s %-5s %-12s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
					System.out.printf("%5s %-5s %-11s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
							dto.getState(),dto.getSeqCompanyInfo(),
							dto.getName(),dto.getStartDate(),
							dto.getEndDate(),dto.getEmploymentType(),
							dto.getSalary(),dto.getComField(),
							dto.getComSize(),dto.getAddress());
			}
		}
		
	}

	private static void address() { //소재지별 공고검색
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t소재지별 채용공고 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t 검색어 예) 서울 ");
		System.out.println("\t\t\t\t 검색어 예) 분당 ");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 검색어를 입력하세요. : ");
		String word = scan.nextLine();
		System.out.println();
		System.out.printf("\t\t\t\t - %s 소재 채용공고 조회 -\n",word);
		System.out.println();
		ArrayList<VwCompanyInfoDTO> list = dao.city(word);
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("[채용현황][번호] [회사명]\t    [채용시작일] [채용종료일] [채용형태] [연봉]    [채용업무]  [회사규모]\t\t[주소]");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for(VwCompanyInfoDTO dto : list) {
			
			if(dto.getName().length() <= 5) {

				System.out.printf("%5s %-5s %-15s %10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
				
			} else if(dto.getName().length()>5 && dto.getName().length() <= 7) {
				System.out.printf("%5s %-5s %-14s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
				System.out.printf("%5s %-5s %-12s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
					System.out.printf("%5s %-5s %-11s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
							dto.getState(),dto.getSeqCompanyInfo(),
							dto.getName(),dto.getStartDate(),
							dto.getEndDate(),dto.getEmploymentType(),
							dto.getSalary(),dto.getComField(),
							dto.getComSize(),dto.getAddress());
			}
		} //for
		
	}

	private static void comField() { //업무별 채용공고 검색
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t업무별 채용공고 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t-검색 가능한 업무 목록-");
		System.out.println();
		ArrayList<VwCompanyInfoDTO> list2 = dao.comfield();
		for(VwCompanyInfoDTO dto : list2) {
			System.out.printf("\t\t\t\t%s  \n",dto.getComField());
		}
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 검색어를 입력하세요. : ");
		String word = scan.nextLine();
		System.out.println();
		System.out.printf("\t\t\t\t - %s 업무 채용공고 조회 -\n",word.toUpperCase());
		System.out.println();
		
		ArrayList<VwCompanyInfoDTO> list = dao.comField(word.toUpperCase());
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("[채용현황][번호] [회사명]\t    [채용시작일] [채용종료일] [채용형태] [연봉]    [채용업무]  [회사규모]\t\t[주소]");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for(VwCompanyInfoDTO dto : list) {
			
			if(dto.getName().length() <= 5) {

				System.out.printf("%5s %-5s %-15s %10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
				
			} else if(dto.getName().length()>5 && dto.getName().length() <= 7) {
				System.out.printf("%5s %-5s %-14s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
				System.out.printf("%5s %-5s %-12s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
						dto.getState(),dto.getSeqCompanyInfo(),
						dto.getName(),dto.getStartDate(),
						dto.getEndDate(),dto.getEmploymentType(),
						dto.getSalary(),dto.getComField(),
						dto.getComSize(),dto.getAddress());
			} else if(dto.getName().length()>7 && dto.getName().length() <= 9) {
					System.out.printf("%5s %-5s %-11s %-10s ~ %-13s %-6s %-10s %-10s %-6s %s\n", 
							dto.getState(),dto.getSeqCompanyInfo(),
							dto.getName(),dto.getStartDate(),
							dto.getEndDate(),dto.getEmploymentType(),
							dto.getSalary(),dto.getComField(),
							dto.getComSize(),dto.getAddress());
			}
		} //for
		
	}

	private static void add() { //채용공고 등록
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 등록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println("\t\t 등록을 위해 아래 내용을 입력해주세요.");
		System.out.println();
		System.out.print("\t회사 이름: ");
		String name = scan.nextLine();
		System.out.print("\t채용 시작일: ");
		String startDate = scan.nextLine();
		System.out.print("\t채용 종료일: ");
		String endDate = scan.nextLine();
		//boolean bool = seDate(startDate,endDate);
		System.out.print("\t채용 분야: ");
		String comField = scan.nextLine();
		System.out.print("\t연봉: ");
		String salary = scan.nextLine();
		System.out.print("\t채용 형태: ");
		String employmentType = scan.nextLine();
		System.out.print("\t회사 규모: ");
		String comSize = scan.nextLine();
		System.out.print("\t회사 주소: ");
		String address = scan.nextLine();
		
		CompanyInfoDTO dto = new CompanyInfoDTO();
		dto.setName(name);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setComField(comField);
		dto.setSalary(salary);
		dto.setEmploymentType(employmentType);
		dto.setComSize(comSize);
		dto.setAddress(address);
		
		int result = dao.add(dto);
		
		if(result == 1) {
			System.out.println();
			System.out.println("\t성공적으로 등록 되었습니다.");
		} else {
			System.out.println();
			System.out.println("\t 등록 실패하였습니다.");
		}

		
		
	}

	private static boolean seDate(String startDate, String endDate) { //TODO 유효성 검사해야함
		Calendar scal = Calendar.getInstance();
		int sYear = Integer.parseInt(startDate.substring(0,3));
		int sMonth = Integer.parseInt(startDate.substring(5,6));
		int sDay = Integer.parseInt(startDate.substring(8,10));
		scal.set(sYear,sMonth,sDay);
		Calendar ecal = Calendar.getInstance();
		int eYear = Integer.parseInt(endDate.substring(0,3));
		int eMonth = Integer.parseInt(endDate.substring(5,6));
		int eDay = Integer.parseInt(endDate.substring(8,10));
		ecal.set(eYear,  eMonth, eDay);
		long sTick = scal.getTimeInMillis();
		long eTick = ecal.getTimeInMillis();
		long gap = eTick - sTick;
		if (gap<0) {
		
			return false;
		}
		return true;
	}

	private static void edit() { // 연계기업 채용공고 수정
		
		
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t연계기업 채용공고 수정\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.println("\t\t\t\t - 전체 채용공고 목록 - ");
			System.out.println();
			list(); // 전체 채용공고출력
			
		System.out.print("\t█ 수정 원하시는 번호를 입력하세요. : ");
		String seq = scan.nextLine();
		System.out.println();
		VwCompanyInfoDTO dto = dao.editGet(seq);
		System.out.println("\t회사 이름: " + dto.getName());
		System.out.println("\t채용 시작일: " + dto.getStartDate());
		System.out.println("\t채용 종료일: " + dto.getEndDate());
		System.out.println("\t채용 분야: " + dto.getComField());
		System.out.println("\t연봉: " + dto.getSalary());
		System.out.println("\t채용 형태: " + dto.getEmploymentType());
		System.out.println("\t회사 규모: " + dto.getComSize());
		System.out.println("\t회사 주소: " + dto.getAddress());
		System.out.println();
		
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
		dto2.setComField(comField);
		dto2.setSalary(salary);
		dto2.setEmploymentType(employmentType);
		dto2.setComSize(comSize);
		dto2.setAddress(address);
		
		int result = dao.edit(dto2);
		
		if(result == 1) {
			System.out.println("\t성공적으로 수정되었습니다. ");
		} else {
			System.out.println("\t수정에 실패하였습니다.");
		}
		
	}

	
	private static void delete() { //채용공고 삭제

		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 채용공고 삭제\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

	
		System.out.println("\t\t\t\t - 전체 채용공고 목록 - ");
		System.out.println();
			list(); // 전체채용목록 출력

		
	System.out.print("\t█ 삭제 원하시는 번호를 입력하세요. : ");
	String seq = scan.nextLine();
	System.out.println();
	VwCompanyInfoDTO dto = dao.editGet(seq);
	System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
	System.out.println("\t회사 이름: " + dto.getName());
	System.out.println("\t채용 시작일: " + dto.getStartDate());
	System.out.println("\t채용 종료일: " + dto.getEndDate());
	System.out.println("\t채용 분야: " + dto.getComField());
	System.out.println("\t연봉: " + dto.getSalary());
	System.out.println("\t채용 형태: " + dto.getEmploymentType());
	System.out.println("\t회사 규모: " + dto.getComSize());
	System.out.println("\t회사 주소: " + dto.getAddress());
	System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
	System.out.println();
	
	System.out.print("\t정말로 삭제 하시겠습니까 ? (y|n) : ");
	String input = scan.nextLine();
	if(input.toLowerCase().equals("y")) {
		int result = dao.delete(seq);
		if(result ==1) {
			System.out.println("\t성공적으로 삭제되었습니다.");
		} else {
			System.out.println("\t삭제 실패 하였습니다.");
		}
	} else if (input.toLowerCase().equals("n")){
		System.out.println("\t이전화면으로 돌아갑니다. ");
	} else {
		System.out.println("\t잘못 입력했습니다. 다시 입력해주세요.");
		delete();
	}
		
	}

	private static void pause() {
		System.out.println();
		System.out.println("\t█ 계속하시려면 엔터를 입력해주세요.");
		scan.nextLine();
		
	}
	
}//AdminCompany
