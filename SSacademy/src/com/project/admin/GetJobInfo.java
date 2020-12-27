package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.CompleteStudentDTO;
import com.project.admin.dto.VwEmpStatusDTO;
import com.project.admin.dto.VwGetJobInfoDTO;
import com.project.dao.EmpStatusDAO;
import com.project.dao.GetJobInfoDAO;
import com.project.dto.CompanyInfoDTO;
import com.project.dto.EmpStatusDTO;
import com.project.dto.GetJobInfoDTO;
/**
 * 수료생 취업정보 관리 클래스
 * @author 조혜승
 *
 */
public class GetJobInfo {
	private static Scanner scan;
	public GetJobInfoDAO dao;
	public EmpStatusDAO daoE;
	public AdminView view;

	static{
		scan = new Scanner(System.in);
	}
	/**
	 * 수료생 취업정보 관리 클래스 생성자
	 * @author 조혜승
	 *
	 */
	public GetJobInfo() {
		dao = new GetJobInfoDAO();
		daoE = new EmpStatusDAO();
		view = new AdminView();
	}

	/**
	 * 수료생 취업정보 관리 메뉴 메서드
	 * @author 조혜승
	 *
	 */
	public void menu() {

		boolean loop = true;
	while(loop) {
		view.menuGetJobInfo();//수료생 취업정보 관리페이지 메뉴출력문 메서드
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
			loop = false;
	
		}
		else {
			System.out.println("\n없는 번호입니다. 다시입력해주세요.");
		}
		
	}//while
	
		
	}
	/**
	 * 수료생 취업정보 전체 조회 메서드
	 * @author 조혜승
	 *
	 */
	public void jobList() {

		view.jobListHeader();//수료생 취업정보 조회헤더
		
		ArrayList<VwGetJobInfoDTO> list = dao.jobList(null);

		view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문


	}


	/**
	 * 수료생 취업정보 검색 메뉴 메서드
	 * @author 조혜승
	 *
	 */
	private void jobSearch() {
		view.menuJobSearch(); //검색메뉴

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
	/**
	 * 수료생 취업정보 연도별 취업정보 검색 메서드
	 * @author 조혜승
	 *
	 */
	public void year() { //연도별 취업정보 검색
		view.yearSearchGet(); //연도별 취업정보 검색헤더 및 입력요청문장

		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s년도 취업정보 조회 -\n",word);
		System.out.println();
		
		ArrayList<VwGetJobInfoDTO> list = dao.jobList(word);
		

		view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문

		
		
	}
	
	/**
	 * 수료생 취업정보 연봉별 취업정보 검색 메서드
	 * @author 조혜승
	 *
	 */
	public void salary() {//연봉별 취업정보 검색
		view.salarySearchGet(); //연봉별 취업정보 검색헤더및 입력요청문장

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

		ArrayList<VwGetJobInfoDTO> list = dao.salarySearch(word, word2);
		
	
		view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문

		
		
	}
	
	/**
	 * 수료생 취업정보 소재지별 취업정보 검색 메서드
	 * @author 조혜승
	 *
	 */
	public void location() { //소재지별 취업정보 검색
		view.locationSearchGet(); //소재지별 취업정보 검색헤더및 입력요청문장

		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 소재 채용공고 조회 -\n",word);
		System.out.println();
		ArrayList<VwGetJobInfoDTO> list = dao.locationSearch(word);
		

		view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문


			
	}

	/**
	 * 수료생 취업정보 업무별 취업정보 검색 메서드
	 * @author 조혜승
	 *
	 */
	public void duty() { //업무별 취업정보 조회
		view.dutySearchGet();//업무별 취업정보 검색헤더및 업무목록출력헤더

		ArrayList<VwGetJobInfoDTO> list2 = dao.dutyList();
		for(VwGetJobInfoDTO dto : list2) {
			System.out.printf("\t\t\t\t%s  \n",dto.getDuty());
		}//for
		System.out.println();
		view.noinputcurve();//입력값없을경우 알림 작은괄호


		System.out.print("\t█ 검색 원하시는 업무를 입력하세요. : ");
		String word = scan.nextLine();
		if(word.equals("")) {
			menu();
		}
		System.out.println();
		System.out.printf("\t\t\t\t - %s 업무 취업정보 조회 -\n",word.toUpperCase());
		System.out.println();
		ArrayList<VwGetJobInfoDTO> list = dao.dutySearch(word.toUpperCase());
		

		view.GetJobList(list);//수료생 취업정보 조회 문 출력 형식문

	
		
	}
	/**
	 * 수료생 취업정보 등록 메서드
	 * @author 조혜승
	 *
	 */
	private void jobAdd() { //수료생 취업정보 등록
		view.jobAddHeader();//수료생 취업정보 등록헤더및 미등록 수료생 출력헤더

		completeStudent();
		System.out.println();
		view.noinputline();//입력값 업을경우 알림 긴라인

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
		view.addResult(result);
		
	}
	/**
	 * 취업정보 등록을위해 미등록 수료생 목록 조회 메서드
	 * @author 조혜승
	 *
	 */
	private void completeStudent() { //취업정보 등록을위해 미등록 수료생 목록 조회
		
		ArrayList<CompleteStudentDTO> list = dao.completeS();
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t[수강번호] [이름] [ID] \t\t[수료과정]");
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		for(CompleteStudentDTO dto : list) { //TODO 정리
			System.out.printf("\t   %3s    %s %s %s\n",dto.getRcseq(), dto.getSname(),dto.getId(), dto.getCourse());
			
		}
		if (list.size() == 0) {
			System.out.println("\t\t\t미취업 수료생이 없습니다.");
			pause();
			return;
		}
		
	}
	
	/**
	 * 수료생 취업정보 수정 메서드
	 * @author 조혜승
	 *
	 */
	private void jobEdit() { 
		view.jobEditHeader();

		String name = scan.nextLine();
		if(name.equals("")) {
			System.out.println("\t이전화면으로 돌아갑니다. 엔터를 입력해주세요");
			scan.nextLine();
			menu();
		}
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t-%s 학생 취업정보 목록 -\n",name);
		ArrayList<VwGetJobInfoDTO> list = dao.nameSearch(name);

		view.editGetJob(list);
		
		
	}
	/**
	 * 수료생취업정보 수정할 학생취업번호 선택 및 수정
	 * @author 혜승 
	 */
	public void jobEditSelect() {
		
		System.out.println();
		System.out.println("\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.print("\t█ 수정 원하시는 학생 취업번호를 입력하세요. : ");
		String seqGetJobInfo = scan.nextLine();
		if(seqGetJobInfo.equals("")) {
			menu();
		}
		System.out.println();
		VwGetJobInfoDTO dto = dao.getEdit(seqGetJobInfo);
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.printf("\t\t취업번호 : %s \n\t\t학생이름 : %s\n\t\t학생ID : %s\n\t\t회사명 : %s\n"
				+ "\t\t업무 : %s\n\t\t고용형태 : %s\n\t\t연봉 : %s\n\t\t취업일 : %s\n"
				+ "\t\t회사주소 : %s\n\t\t수료한과정명 : %s\n",
				dto.getGjseq(), dto.getName(),dto.getId(), 
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
		view.updateResult(result);
		
		
		
	}

	/**
	 * 수료생 취업정보 삭제 시작
	 * @author 조혜승
	 *
	 */
	private void jobDelete() {
		view.jobDeleteHeader();

		String name = scan.nextLine();
		if(name.equals("")) {
			System.out.println("\t이전화면으로 돌아갑니다. 엔터를 입력해주세요");
			scan.nextLine();
			menu();
		}
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t-%s 학생 취업정보 목록 -",name);
		System.out.println();
		ArrayList<VwGetJobInfoDTO> list = dao.nameSearch(name);
		
		System.out.println();
		view.deleteGetJob(list);
		
		
	}
	/**
	 * 수료생취업정보 삭제번호선택 및 삭제
	 * @author 혜승
	 * @param list 
	 * 
	 */
	public void jobDeleteSelect() {
	
		System.out.println();
		System.out.print("\t█ 삭제 원하시는 학생 취업번호를 입력하세요. : ");
		String seqGetJobInfo = scan.nextLine();
		if(seqGetJobInfo.equals("")) {
			menu();
		}
		System.out.println();
		VwGetJobInfoDTO dto = dao.getEdit(seqGetJobInfo);
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println();
		System.out.printf("\t\t취업번호 : %s \n\t\t학생이름 : %s\t\t학생ID : %s\n\t\t회사명 : %s\n"
				+ "\t\t업무 : %s\n\t\t고용형태 : %s\n\t\t연봉 : %s\n\t\t취업일 : %s\n"
				+ "\t\t회사주소 : %s\n\t\t수료한과정명 : %s\n\n",
				dto.getGjseq(), dto.getName(),dto.getId(), 
				dto.getCompanyName(), dto.getDuty(),
				dto.getForm(), dto.getSalary(),
				dto.getGetJobDate(),dto.getLocation(),
				dto.getCourse());
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.print("\t█정말로 삭제 하시겠습니까 ? (y|n) : ");
		String input = scan.nextLine();
		if(input.toLowerCase().equals("y")) {
			int result = dao.deleteJob(seqGetJobInfo);
			view.deleteResult(result);
		} else if (input.toLowerCase().equals("n")){
			System.out.println("\t이전화면으로 돌아갑니다. ");
			menu();
		} else {
			System.out.println("\t잘못 입력했습니다. 다시 입력해주세요.");
			jobDelete();
		}
		
	}

	/**
	 * 연계기업 취업정보 조회메서드
	 * @author 조혜승
	 *
	 */
	public void cjobList() {
		view.cjobListHeader();
	
		
		ArrayList<VwEmpStatusDTO> list = daoE.cjobList(null);
		
		view.cjobList(list);

		
	}
	/**
	 * 연계기업 취업정보 등록메서드
	 * @author 조혜승
	 *
	 */
	private void cjobAdd() {
		view.cjobAddHeader();
	
		ArrayList<CompanyInfoDTO> list = daoE.companyList();
		System.out.println();
		for(CompanyInfoDTO dto : list) {
			System.out.println("\t회사번호 : "+dto.getSeqCompanyInfo() + "\t회사이름 : " + dto.getName());
			System.out.println("\t\t\t회사주소 : " + dto.getAddress());
			System.out.println();
			
		}
		System.out.println();
		view.noinputcurve();
		System.out.println("\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.print("\t█ 등록 원하는 회사 번호를 입력해 주세요. : ");
		String seqCompanyInfo = scan.nextLine();
		
		if(seqCompanyInfo.equals("")) {
			menu();
		}
		ArrayList<VwGetJobInfoDTO> list2 = daoE.companyGetJob(seqCompanyInfo);
		System.out.println();
		System.out.printf("\t\t\t\t선택기업에 취업한 수료생 목록");
		System.out.println();
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t[취업번호][수강번호]\t[이름]  [ID]     \t[회사이름]    [취업일]\t[회사주소]");
		System.out.println("\t───────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
		for(VwGetJobInfoDTO dto : list2) { 
			System.out.printf("\t%5s \t%6s  \t%s %s    %s  %s  %s\n",
							dto.getGjseq(), dto.getRcseq(),dto.getName(),
							dto.getId(),dto.getCompanyName(),dto.getGetJobDate(),dto.getLocation());
		}
		
		if (list2.size() == 0) {
			System.out.println("\t\t\t선택한 기업에 취업한 수료생이 없습니다.");
			return;
		}
		System.out.println();
		view.noinputline();
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
		view.addResult(result);
		
		
	}

	/**
	 * 연계기업 취업정보 삭제메서드
	 * @author 조혜승
	 *
	 */
	private void cjobDelete() {
		view.cjobDeleteHeader();

		String word = scan.nextLine();
		if(word.equals("")) {
			System.out.println("\t이전화면으로 돌아갑니다. 엔터를 입력해주세요");
			scan.nextLine();
			menu();
		}
		
		ArrayList<VwEmpStatusDTO> list = daoE.cjobList(word);
		if(list.size() ==0) {
			System.out.println();
			System.out.println("\t\t\t -수료생취업정보에 존재하지 않는 학생이름입니다.-\n");
			System.out.println();
			System.out.println("\t 엔터를 입력해주세요");
		}
		System.out.println();
		System.out.printf("\t\t\t\t-%s 학생 연계기업 취업정보 목록 -\n",word);
		System.out.println();
		view.deleteCjobList(list);

		
	}
	/**
	 * 연계기업취업정보 삭제 번호 선택및 삭제
	 * @author 혜승
	 */
	public void deleteCjobSel() {

		System.out.println();
		System.out.println("\t\t**입력값이 없으면 이전화면으로 돌아갑니다.");
		System.out.print("\t█ 삭제 원하시는 학생의 번호를입력하세요 : ");
		String seq = scan.nextLine();
		if(seq.equals("")) {
			menu();
		}
			VwEmpStatusDTO dto = daoE.getList(seq);
			System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
			System.out.println("\t\t\t번호 : " + dto.getSeq());
			System.out.println("\t\t\t이름 : " + dto.getName());
			System.out.println("\t\t\tID : " + dto.getId());
			System.out.println("\t\t\t회사명 : " + dto.getCompanyName());
			System.out.println("\t\t\t취업일 : " + dto.getGetJobDate());	
			System.out.println("\t\t\t회사주소 : " + dto.getLocation());	
			System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
			System.out.print("\t█정말로 삭제 하시겠습니까 ? (y|n) : ");
			String input = scan.nextLine();
			if(input.toLowerCase().equals("y")) {
				int result = daoE.deleteEmpStatus(seq);
				view.deleteResult(result);
			} else if (input.toLowerCase().equals("n")){
				System.out.println("\t이전화면으로 돌아갑니다. ");
				menu();
			} else {
				System.out.println("\t잘못 입력했습니다. 다시 입력해주세요.");
				cjobDelete();
			}
		
		
	}

	/**
	 * 메뉴로다시돌아가는 메서드
	 */
	public void pause() {
		
		System.out.println();
		System.out.println("\t█ 계속하시려면 엔터를 입력해주세요.");
		scan.nextLine();
		
		
	}



	
	
}
