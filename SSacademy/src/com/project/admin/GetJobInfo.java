package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.CompleteStudentDTO;
import com.project.admin.dto.VwEmpStatusDTO;
import com.project.admin.dto.VwCompanyInfoDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.dao.EmpStatusDAO;
import com.project.dao.GetJobInfoDAO;
import com.project.dto.CompanyInfoDTO;
import com.project.dto.EmpStatusDTO;
import com.project.dto.GetJobInfoDTO;

public class GetJobInfo {
	private static Scanner scan;
	private static GetJobInfoDAO dao;
	private static EmpStatusDAO daoE;
	
	static{
		scan = new Scanner(System.in);
		dao = new GetJobInfoDAO();
		daoE = new EmpStatusDAO();
	}

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {
		
		boolean loop = true;
	while(loop) {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 관리페이지\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 취업정보 전체조회\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 취업정보 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t3. 취업정보 등록\t\t\t\t\t  │");
		System.out.println("\t│\t\t4. 취업정보 수정\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 취업정보 삭제\t\t\t\t\t  │");
		System.out.println("\t│\t\t6. 연계기업 취업정보 조회\t\t\t\t  │");
		System.out.println("\t│\t\t7. 연계기업 취업정보 등록\t\t\t\t  │");
		System.out.println("\t│\t\t8. 연계기업 취업정보 삭제\t\t\t\t  │");
		System.out.println("\t│\t\t9. 이전 화면으로\t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			jobList(); //전체목록 출력
			
			pause();
		} else if(num.equals("2")) {
			jobSearch();
			
			pause();
		}else if(num.equals("3")) {
			jobAdd();
			
			pause();
		}else if(num.equals("4")) {
			jobEdit();
			pause();
		}else if(num.equals("5")) {
			jobDelete();
			
			pause();
		}else if(num.equals("6")) {
			cjobList();
			
			pause();
		}else if(num.equals("7")) {
			cjobAdd();
			
			pause();
		}else if(num.equals("8")) {
			cjobDelete();
			
			pause();
		}else if(num.equals("9")) {
			
	
		}
		else {
			System.out.println("\n없는 번호입니다. 다시입력해주세요.");
		}
		
	}//while
	
		
	}

	private static void jobList() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 전체 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		ArrayList<VwGetJobInfoDTO> jobList = dao.jobList(null);
		
		for(VwGetJobInfoDTO dto : jobList) { //TODO 정리
			
			System.out.printf("%s %s %s %s %s %s %s %s\n",
								dto.getGjseq(), dto.getName(), 
								dto.getCompanyName(), dto.getDuty(),
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(), dto.getLocation());	
		}
		
	}

	private static void jobSearch() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t1. 업무별 취업정보 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t2. 소재지별 취업정보 검색\t\t\t\t  │");
		System.out.println("\t│\t\t3. 연봉별 취업정보 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t4. 연도별 취업정보 검색\t\t\t\t\t  │");
		System.out.println("\t│\t\t5. 이전 화면으로 \t\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			duty();
			
		} else if(num.equals("2")) {
			location();
			
		} else if(num.equals("3")) {
			salary();

		} else if(num.equals("4")) {
			year();
		} else if(num.equals("5")){
			menu();
		} else {
			System.out.println("\t**번호를 잘못 입력하셨습니다.");
			jobSearch();
		}
		
	}

	private static void year() { //연도별 취업정보 검색
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연도별 취업정보 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t 검색어 예): 2020 ");
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 검색 원하시는 연도를 입력하세요. : ");
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s년도 취업정보 조회 -\n",word);
		System.out.println();
		
		ArrayList<VwGetJobInfoDTO> jobList = dao.jobList(word);
		
		for(VwGetJobInfoDTO dto : jobList) { //TODO 정리
			
			System.out.printf("%s %s %s %s %s %s %s %s\n",
								dto.getGjseq(), dto.getName(), 
								dto.getCompanyName(), dto.getDuty(),
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(), dto.getLocation());	
		}//for
		if (jobList.size() == 0) {
			System.out.println("\t\t\t존재하지 않는 입력값입니다. 다시입력해주세요");
			pause();
			return;
		}
		
	}

	private static void salary() {//연봉별 취업정보 검색
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연봉별 취업정보 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t 검색어 예) 검색 최저금액 : 20000000 (이상)");
		System.out.println("\t\t\t 검색어 예) 검색 최고금액 : 30000000 (미만)");
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 검색 원하시는 최저금액을 입력하세요. : ");
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

		ArrayList<VwGetJobInfoDTO> jobList = dao.salarySearch(word, word2);
		
		for(VwGetJobInfoDTO dto : jobList ) { //TODO 정리
			
			System.out.printf("%s %s %s %s %s %s %s %s %s\n",
								dto.getGjseq(), dto.getName(), 
								dto.getCompanyName(), dto.getDuty(),
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getLocation(),
								dto.getCourse());
		}//for
		if (jobList.size() == 0) {
			System.out.println("\t\t\t존재하지 않는 입력값입니다. 다시입력해주세요");
			pause();
			return;
		}
		
	}

	private static void location() { //소재지별 취업정보 검색
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t소재지별 취업정보 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t 검색어 예) 서울 ");
		System.out.println("\t\t\t\t 검색어 예) 분당 ");
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 검색 원하시는 지역을 입력하세요. : ");
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 소재 채용공고 조회 -\n",word);
		System.out.println();
		ArrayList<VwGetJobInfoDTO> jobList = dao.locationSearch(word);
		
		for(VwGetJobInfoDTO dto : jobList ) { //TODO 정리
			
			System.out.printf("%s %s %s %s %s %s %s %s %s\n",
								dto.getGjseq(), dto.getName(), 
								dto.getCompanyName(), dto.getDuty(),
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getLocation(),
								dto.getCourse());
		}//for
		if (jobList.size() == 0) {
			System.out.println("\t\t\t검색한 지역은 채용공고에 존재하지 않습니다.");
			pause();
			return;
		}
	}

	private static void duty() { //업무별 취업정보 조회

		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t업무별 취업정보 검색\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t-검색 가능한 업무 목록-");
		System.out.println();
		ArrayList<VwGetJobInfoDTO> list = dao.dutyList();
		for(VwGetJobInfoDTO dto : list) {
			System.out.printf("\t\t\t\t%s  \n",dto.getDuty());
		}//for
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.print("\t█ 검색 원하시는 업무를 입력하세요. : ");
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 업무 취업정보 조회 -\n",word.toUpperCase());
		System.out.println();
		ArrayList<VwGetJobInfoDTO> jobList = dao.dutySearch(word.toUpperCase());
		
		for(VwGetJobInfoDTO dto : jobList ) { //TODO 정리
			
			System.out.printf("%s %s %s %s %s %s %s %s %s\n",
								dto.getGjseq(), dto.getName(), 
								dto.getCompanyName(), dto.getDuty(),
								dto.getForm(), dto.getSalary(),
								dto.getGetJobDate(),dto.getLocation(),
								dto.getCourse());
		}//for
		
	}

	private static void jobAdd() { //수료생 취업정보 등록
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 등록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t\t\t\t-미등록 수료생 목록 -");
		System.out.println();
		completeStudent();
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.print("\t█ 등록 원하시는 학생 수강번호를 입력하세요. : ");
		String seqRegCourse = scan.nextLine();
		System.out.print("\t█ 회사명 : ");
		String name = scan.nextLine();
		System.out.print("\t█ 회사주소 : ");
		String location = scan.nextLine();
		System.out.print("\t█ 업무 : ");
		String duty = scan.nextLine();
		System.out.print("\t█ 연봉 : ");
		String salary = scan.nextLine();
		System.out.print("\t█ 고용형태 : ");
		String form = scan.nextLine();
		System.out.print("\t█ 취업일 : ");
		String getJobDate = scan.nextLine();
		
		if(seqRegCourse.equals("") || name.equals("") || location.equals("") || 
			duty.equals("") || salary.equals("") || form.equals("") || getJobDate.equals(""))  {
			System.out.println();
			System.out.println("\t**모든 값을 입력해야 합니다. 이전화면으로 돌아갑니다.");
			System.out.println("\t엔터를 입력해주세요.");
			scan.nextLine();
			menu();
		}
		
		GetJobInfoDTO dto = new GetJobInfoDTO();
		dto.setSeqRegCourse(seqRegCourse);
		dto.setName(name);
		dto.setLocation(location);
		dto.setDuty(duty.toUpperCase());
		dto.setSalary(salary);
		dto.setForm(form);
		dto.setGetJobDate(getJobDate);
		
		int result = dao.addGetJobInfo(dto);
		if(result ==1 ) {
			System.out.println("\t성공적으로 등록되었습니다. ");
		} else {
			System.out.println("\t등록에 실패하였습니다.");
		}
		
	}

	private static void completeStudent() { //취업정보 등록을위해 미등록 수료생 목록 조회
		
		ArrayList<CompleteStudentDTO> list = dao.completeS();
		for(CompleteStudentDTO dto : list) { //TODO 정리
			System.out.printf("\t%s %s %s %s\n",dto.getRcseq(), dto.getSname(),dto.getId(), dto.getCourse());
			
		}
		if (list.size() == 0) {
			System.out.println("\t\t\t미취업 수료생이 없습니다.");
			pause();
			return;
		}
		
	}

	private static void jobEdit() { 
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 수정\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("\t█ 수정 원하시는 학생이름을 입력하세요. : ");
		String name = scan.nextLine();
		if(name.equals("")) {
			System.out.println("\t이전화면으로 돌아갑니다. 엔터를 입력해주세요");
			scan.nextLine();
			menu();
		}
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t-%s 학생 취업정보 목록 -",name);
		System.out.println();
		searchName(name);
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.print("\t█ 수정 원하시는 학생 취업번호를 입력하세요. : ");
		String seqGetJobInfo = scan.nextLine();
		if(seqGetJobInfo.equals("")) {
			menu();
		}
		System.out.println();
		VwGetJobInfoDTO dto = dao.getEdit(seqGetJobInfo);
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t취업번호 : %s \n\t학생이름 : %s\n\t회사명 : %s\n"
				+ "\t업무 : %s\n\t고용형태 : %s\n\t연봉 : %s\n\t취업일 : %s\n"
				+ "\t회사주소 : %s\n\t수료한과정명 : %s\n",
				dto.getGjseq(), dto.getName(), 
				dto.getCompanyName(), dto.getDuty(),
				dto.getForm(), dto.getSalary(),
				dto.getGetJobDate(),dto.getLocation(),
				dto.getCourse());
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println("\t**수정을 원하지 않는 항목은 엔터를 입력하세요.");
		System.out.println();
		System.out.print("\t█ 수정할 회사명 : ");
		String cname = scan.nextLine();
		if(cname.equals("")) {
			cname = dto.getCompanyName();
		}	
		System.out.print("\t█ 수정할 업무 : ");
		String duty = scan.nextLine();
		if(duty.equals("")) {
			duty = dto.getDuty();
		}
		System.out.print("\t█ 수정할 고용형태 : ");
		String form = scan.nextLine();
		if(form.equals("")) {
			form = dto.getForm();
		}
		System.out.print("\t█ 수정할 연봉 : ");
		String salary = scan.nextLine();
		if(salary.equals("")) {
			salary = dto.getSalary();
		}
		System.out.print("\t█ 수정할 취업일 : ");
		String getJobDate = scan.nextLine();
		if(getJobDate.equals("")) {
			getJobDate = dto.getGetJobDate();
		}
		System.out.print("\t█ 수정할 회사주소 : ");
		String location = scan.nextLine();
		if(location.equals("")) {
			location = dto.getLocation();
		}
		
		GetJobInfoDTO dto2 = new GetJobInfoDTO();
		dto2.setSeqGetJobInfo(seqGetJobInfo);
		dto2.setName(cname);
		dto2.setDuty(duty.toUpperCase());
		dto2.setForm(form);
		dto2.setSalary(salary);
		dto2.setGetJobDate(getJobDate);
		dto2.setLocation(location);


		int result = dao.editGetJobInfo(dto2);
		if(result ==1 ) {
			System.out.println("\t성공적으로 수정되었습니다. ");
		} else {
			System.out.println("\t수정에 실패하였습니다.");
		}
		
		
		
	}

	private static void searchName(String name) { //취업정보 수정및 삭제을 위한 이름검색
		ArrayList<VwGetJobInfoDTO> list = dao.nameSearch(name);
		for(VwGetJobInfoDTO dto : list) {
			System.out.printf("\t%s %s %s %s %s %s %s %s %s",
					dto.getGjseq(), dto.getName(), 
					dto.getCompanyName(), dto.getDuty(),
					dto.getForm(), dto.getSalary(),
					dto.getGetJobDate(),dto.getLocation(),
					dto.getCourse());
			if (list.size() == 0) {
				System.out.println("\t\t\t검색한 학생이름은 취업정보에 존재하지 않습니다.");
				pause();
				return;
			}
		}
		
		
	}

	private static void jobDelete() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t수료생 취업정보 삭제\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("\t█ 삭제 원하시는 학생이름을 입력하세요. : ");
		String name = scan.nextLine();
		if(name.equals("")) {
			System.out.println("\t이전화면으로 돌아갑니다. 엔터를 입력해주세요");
			scan.nextLine();
			menu();
		}
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t-%s 학생 취업정보 목록 -",name);
		System.out.println();
		searchName(name);
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.print("\t█ 삭제 원하시는 학생 취업번호를 입력하세요. : ");
		String seqGetJobInfo = scan.nextLine();
		if(seqGetJobInfo.equals("")) {
			menu();
		}
		System.out.println();
		VwGetJobInfoDTO dto = dao.getEdit(seqGetJobInfo);
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t취업번호 : %s \n\t학생이름 : %s\n\t회사명 : %s\n"
				+ "\t업무 : %s\n\t고용형태 : %s\n\t연봉 : %s\n\t취업일 : %s\n"
				+ "\t회사주소 : %s\n\t수료한과정명 : %s\n\n",
				dto.getGjseq(), dto.getName(), 
				dto.getCompanyName(), dto.getDuty(),
				dto.getForm(), dto.getSalary(),
				dto.getGetJobDate(),dto.getLocation(),
				dto.getCourse());
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█정말로 삭제 하시겠습니까 ? (y|n) : ");
		String input = scan.nextLine();
		if(input.toLowerCase().equals("y")) {
			int result = dao.deleteJob(seqGetJobInfo);
			if(result ==1) {
				System.out.println("\t성공적으로 삭제되었습니다.");
			} else {
				System.out.println("\t삭제 실패 하였습니다.");
			}
		} else if (input.toLowerCase().equals("n")){
			System.out.println("\t이전화면으로 돌아갑니다. ");
			menu();
		} else {
			System.out.println("\t잘못 입력했습니다. 다시 입력해주세요.");
			jobDelete();
		}
		
	}

	private static void cjobList() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 취업정보 전체 조회\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		ArrayList<VwEmpStatusDTO> list = daoE.cjobList(null);
		for(VwEmpStatusDTO dto : list) {
			
			System.out.printf("\t%s %s %s %s %s %s %s %s %s\n",
					dto.getSeq(), dto.getName(), 
					dto.getCompanyName(), dto.getDuty(),
					dto.getForm(), dto.getSalary(),
					dto.getGetJobDate(),dto.getLocation(),
					dto.getCourse());
			
		}//for
		
	}

	private static void cjobAdd() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 취업정보 등록\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t\t- 연계기업 목록 -");
		ArrayList<CompanyInfoDTO> list = daoE.companyList();
		System.out.println();
		for(CompanyInfoDTO dto : list) {
			System.out.println("\t\t번호 : "+dto.getSeqCompanyInfo() + "\t회사이름 : " + dto.getName());
			
		}
		System.out.println();
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█ 등록 원하는 회사 번호를 입력해 주세요. : ");
		String seqCompanyInfo = scan.nextLine();
		
		if(seqCompanyInfo.equals("")) {
			menu();
		}
		ArrayList<VwGetJobInfoDTO> list2 = daoE.companyGetJob(seqCompanyInfo);
		System.out.println();
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t[취업번호][수강번호]\t[이름]  [ID]     \t[회사이름]    [취업일]");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		for(VwGetJobInfoDTO dto : list2) { //TODO 맞추기
			System.out.printf("\t%5s \t%6s  \t%s %s    %s  %s\n",
							dto.getGjseq(), dto.getRcseq(),dto.getName(),
							dto.getId(),dto.getCompanyName(),dto.getGetJobDate());
		}
		if (list2.size() == 0) {
			System.out.println("\t\t\t선택한 기업에 취업한 수료생이 없습니다.");
			pause();
			return;
		}
		System.out.println("\t\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("\t█ 등록 원하는 학생의 취업번호를 입력해 주세요.: ");
		String gjseq = scan.nextLine();
		if(gjseq.equals("")) {
			menu();
		}
		System.out.print("\t█ 등록 원하는 학생의 수강번호를 입력해 주세요.: ");
		String rcseq = scan.nextLine();
		if(rcseq.equals("")) {
			menu();
		}
		
		EmpStatusDTO dto = new EmpStatusDTO();
		dto.setSeqCompanyInfo(seqCompanyInfo);
		dto.setSeqGetJobInfo(gjseq);
		dto.setSeqRegCourse(rcseq);
		
		int result = daoE.addEmpStatus(dto);
		if(result ==1) {
			System.out.println("\t성공적으로 등록되었습니다.");
		} else {
			System.out.println("\t등록에 실패했습니다.");
		}
		
		
	}



	private static void cjobDelete() {
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t연계기업 취업정보 삭제\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("\t█ 삭제 원하시는 학생이름을 입력하세요. : ");
		String word = scan.nextLine();
		if(word.equals("")) {
			System.out.println("\t이전화면으로 돌아갑니다. 엔터를 입력해주세요");
			scan.nextLine();
			menu();
		}
		
		ArrayList<VwEmpStatusDTO> list = daoE.cjobList(word);
		for(VwEmpStatusDTO dto : list) {
			
			System.out.printf("\t%s %s %s %s %s %s %s %s %s\n",
					dto.getSeq(), dto.getName(), 
					dto.getCompanyName(), dto.getDuty(),
					dto.getForm(), dto.getSalary(),
					dto.getGetJobDate(),dto.getLocation(),
					dto.getCourse());
			
		}//for
		System.out.print("\t█ 삭제 원하시는 학생의 번호를입력하세요 : ");
		String seq = scan.nextLine();
			VwEmpStatusDTO dto = daoE.getList(seq);
			System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
			System.out.println("\t\t\t번호 : " + dto.getSeq());
			System.out.println("\t\t\t이름 : " + dto.getName());
			System.out.println("\t\t\tID : " + dto.getId());
			System.out.println("\t\t\t회사명 : " + dto.getCompanyName());
			System.out.println("\t\t\t취업일 : " + dto.getGetJobDate());	
			System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
			System.out.print("\t█정말로 삭제 하시겠습니까 ? (y|n) : ");
			String input = scan.nextLine();
			if(input.toLowerCase().equals("y")) {
				int result = daoE.deleteEmpStatus(seq);
				if(result ==1) {
					System.out.println("\t성공적으로 삭제되었습니다.");
				} else {
					System.out.println("\t삭제 실패 하였습니다.");
				}
			} else if (input.toLowerCase().equals("n")){
				System.out.println("\t이전화면으로 돌아갑니다. ");
				menu();
			} else {
				System.out.println("\t잘못 입력했습니다. 다시 입력해주세요.");
				cjobDelete();
			}
		
	}

	private static void pause() {
		
		System.out.println();
		System.out.println("\t█ 계속하시려면 엔터를 입력해주세요.");
		scan.nextLine();
		
		
	}
	
	
}
