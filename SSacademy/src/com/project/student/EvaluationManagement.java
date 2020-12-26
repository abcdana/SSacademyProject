package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.EvaluationDAO;
import com.project.dto.EvaluationDTO;
import com.project.dto.StudentDTO;
import com.project.student.dto.ViewStudentEndCourseDTO;

/**
 * 교육생 평가 관리를 담당하는 클래스
 * @author 김주혁
 *
 */
public class EvaluationManagement {

	private Scanner scan;
	private EvaluationDAO dao;
	private StudentDTO sdto;
	//종료된 수강 번호를 저장하는 객체
	private ArrayList<String> seqRegCourses;
	
	/**
	 * 교육생 평가 관리 클래스의 생성자
	 * @param dto 로그인한 교육생 정보
	 */
	public EvaluationManagement(StudentDTO dto) {
		
		scan = new Scanner(System.in);
		dao = new EvaluationDAO();
		this.sdto = dto; 
		seqRegCourses = new ArrayList<String>();
		
	}

	/**
	 * 교육생 평가 관리 클래스의 메인 메서드
	 */
	public void main() {
		
		boolean loop = true;
		
		while (loop) {
			
			//교육생이 수강한 종료된 과정 정보 가져오기
			viewEndCourseList();
			
			//메뉴 출력
			menu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//평가 등록
				add();
			} else if (sel.equals("2")) {
				//평가 수정
				edit();
			} else if (sel.equals("3")) {
				//평가 삭제
				delete();
			} else if (sel.equals("4")) {
				//평가 조회
				view();
			} else if (sel.equals("0")) {
				//뒤로 가기
				loop = false;
			} else {
				//잘못 입력
				wrongInput();
				pause();
			}
			
		}
		
	} //main
	
	
	private void viewEndCourseList() {
		
		//종료된 과정 목록을 저장하는 객체 생성
		ArrayList<ViewStudentEndCourseDTO> list = dao.studentCourseList(sdto.getSeqStudent());
		
		//종료된 과정이 없는 경우
		if (list.size() == 0) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t\t종료 과정 없음\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t종료 과정 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[수강번호]\t[과정명]\t\t\t\t\t\t[과정시작일]\t[과정종료일]\t[강의실]");
		
		
		for (ViewStudentEndCourseDTO dto : list) {
			System.out.printf("\t    %s\t\t%s\t%s\t%s\t%s\n"
								, dto.getSeqRegCourse()
								, dto.getCourseName()
								, dto.getCourseStartDate()
								, dto.getCourseEndDate()
								, dto.getRoom());
			
			seqRegCourses.add(dto.getSeqRegCourse());
		}
		
	} //viewEndCourseList
	
	
	private void menu() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t    교육생 평가 관리\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t      1. 평가 등록\t\t\t\t  │");
		System.out.println("\t│\t\t\t      2. 평가 수정\t\t\t\t  │");
		System.out.println("\t│\t\t\t      3. 평가 삭제\t\t\t\t  │");
		System.out.println("\t│\t\t\t      4. 평가 조회\t\t\t\t  │");
		System.out.println("\t│\t\t\t      0. 뒤로 가기\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	} //menu
	
	
	private void add() {
		
		System.out.print("\t█ 수강 번호를 입력하세요. : ");
		String seqRegCourse = scan.nextLine();
		
		//교육생이 수강한 수강 번호를 입력했는지 확인
		boolean isEndRegCourse = false;
		
		for (int i=0; i<seqRegCourses.size(); i++) {
			if (seqRegCourse.equals(seqRegCourses.get(i))) {
				isEndRegCourse = true;
			}
		}
		
		if (isEndRegCourse == false) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t해당 수강 번호가 없습니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//평가했는지 확인
		int result = dao.isEvaluation(seqRegCourse);
		
		if (result == 0) { //평가 안함
			
			System.out.println();
			System.out.println("\t[수강번호]\t[과정명]\t\t\t\t\t\t[과정시작일]\t[과정종료일]\t[강의실]");
			
			//선택한 과정 정보 가져오기
			ArrayList<ViewStudentEndCourseDTO> list = dao.studentCourseList(sdto.getSeqStudent());
			
			//선택한 과정 정보 출력하기
			for (ViewStudentEndCourseDTO dto : list) {
				if (dto.getSeqRegCourse().equals(seqRegCourse)) {
					System.out.printf("\t  %s\t\t%s\t%s\t%s\t%s\n"
							, dto.getSeqRegCourse()
							, dto.getCourseName()
							, dto.getCourseStartDate()
							, dto.getCourseEndDate()
							, dto.getRoom());				
				}
			}
			
			//평가하기
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t\t평가 항목 입력\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t모든 항목은 5점 만점입니다.\t\t\t  ┃");
			System.out.println("\t┃\t\t1점 미만 입력시 자동으로 1점으로 입력됩니다.\t\t  ┃");
			System.out.println("\t┃\t\t5점 초과 입력시 자동으로 5점으로 입력됩니다.\t\t  ┃");
			System.out.println("\t┃\t\t입력하지 않을시 자동으로 1점으로 입력됩니다.\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
			System.out.print("\t█강의계획서 이행점수 : ");
			String processScore = scan.nextLine();
			
			if (Float.parseFloat(processScore) < 1 || processScore.equals("")) {
				processScore = "1";
			} else if (Float.parseFloat(processScore) > 5) {
				processScore = "5";
			}
			
			System.out.print("\t█교사의 강의전달 및 이해점수 : ");
			String understandScore = scan.nextLine();
			
			if (Float.parseFloat(understandScore) < 1 || understandScore.equals("")) {
				understandScore = "1";
			} else if (Float.parseFloat(understandScore) > 5) {
				understandScore = "5";
			}
			
			System.out.print("\t█교사의 소통점수 : ");
			String communicationScore = scan.nextLine();
			
			if (Float.parseFloat(communicationScore) < 1 || communicationScore.equals("")) {
				communicationScore = "1";
			} else if (Float.parseFloat(communicationScore) > 5) {
				communicationScore = "5";
			}
			
			System.out.print("\t█강의의 유익성점수 : ");
			String usefulScore = scan.nextLine();
			
			if (Float.parseFloat(usefulScore) < 1 || usefulScore.equals("")) {
				usefulScore = "1";
			} else if (Float.parseFloat(usefulScore) > 5) {
				usefulScore = "5";
			}
			
			System.out.print("\t█전반적인 만족도 : ");
			String satisfactionScore = scan.nextLine();
			
			if (Float.parseFloat(satisfactionScore) < 1 || satisfactionScore.equals("")) {
				satisfactionScore = "1";
			} else if (Float.parseFloat(satisfactionScore) > 5) {
				satisfactionScore = "5";
			}
			
			System.out.print("\t█시설 만족도 : ");
			String facilityScore = scan.nextLine();
			
			if (Float.parseFloat(facilityScore) < 1 || facilityScore.equals("")) {
				facilityScore = "1";
			} else if (Float.parseFloat(facilityScore) > 5) {
				facilityScore = "5";
			}
			
			System.out.print("\t█사후 취업관리 만족도 : ");
			String managementScore = scan.nextLine();
			
			if (Float.parseFloat(managementScore) < 1 || managementScore.equals("")) {
				managementScore = "1";
			} else if (Float.parseFloat(managementScore) > 5) {
				managementScore = "5";
			}
			
			//평가 내용을 저장할 DTO 생성
			EvaluationDTO dto = new EvaluationDTO();
			
			//생성된 DTO에 평가 내용 저장
			dto.setSeqRegCourse(seqRegCourse);
			dto.setProcessScore(processScore);
			dto.setUnderstandScore(understandScore);
			dto.setCommunicationScore(communicationScore);
			dto.setUsefulScore(usefulScore);
			dto.setSatisfactionScore(satisfactionScore);
			dto.setFacilityScore(facilityScore);
			dto.setManagementScore(managementScore);
			
			//평가 테이블에 추가
			int result2 = dao.add(dto);
			
			if (result2 > 0) {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t    과정 평가 등록 성공\t\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			} else {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t    과정 평가 등록 실패\t\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			}
			
		} else { //이미 평가함
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t   이미 평가된 과정입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}
		
		
	} //add
	
	
	private void edit() {
		
		System.out.print("\t█ 수강 번호를 입력하세요. : ");
		String seqRegCourse = scan.nextLine();
		
		//교육생이 수강한 수강 번호를 입력했는지 확인
		boolean isEndRegCourse = false;
		
		for (int i=0; i<seqRegCourses.size(); i++) {
			if (seqRegCourse.equals(seqRegCourses.get(i))) {
				isEndRegCourse = true;
			}
		}
		
		if (isEndRegCourse == false) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t해당 수강 번호가 없습니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//평가했는지 확인
		int result = dao.isEvaluation(seqRegCourse);
		
		if (result != 0) { //평가함
			
			//수강한 과정의 평가 내용 가져오기
			EvaluationDTO dto = dao.getEvaluation(seqRegCourse);
			
			//수강한 과정의 평가 내용 출력하기
			viewEvaluationInfo(dto);
			
			//평가 수정
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t\t평가 항목 수정\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t모든 항목은 5점 만점입니다.\t\t\t  ┃");
			System.out.println("\t┃\t\t1점 미만 입력시 자동으로 1점으로 입력됩니다.\t\t  ┃");
			System.out.println("\t┃\t\t5점 초과 입력시 자동으로 5점으로 입력됩니다.\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
			System.out.println("\t★★수정을 하지 않을 항목은 그냥 엔터를 눌러주세요★★");
			System.out.println();
			
			System.out.print("\t█강의계획서 이행점수 : ");			
			String processScore = scan.nextLine();
			if (processScore.equals("")) {
				processScore = dto.getProcessScore();
			}
			
			if (Float.parseFloat(processScore) < 1) {
				processScore = "1";
			} else if (Float.parseFloat(processScore) > 5) {
				processScore = "5";
			}
			
			System.out.print("\t█교사의 강의전달 및 이해점수 : ");
			String understandScore = scan.nextLine();
			if (understandScore.equals("")) {
				understandScore = dto.getUnderstandScore();
			}
			
			if (Float.parseFloat(understandScore) < 1) {
				understandScore = "1";
			} else if (Float.parseFloat(understandScore) > 5) {
				understandScore = "5";
			}
			
			System.out.print("\t█교사의 소통점수 : ");
			String communicationScore = scan.nextLine();
			if (communicationScore.equals("")) {
				communicationScore = dto.getCommunicationScore();
			}
			
			if (Float.parseFloat(communicationScore) < 1) {
				communicationScore = "1";
			} else if (Float.parseFloat(communicationScore) > 5) {
				communicationScore = "5";
			}
			
			System.out.print("\t█강의의 유익성점수 : ");
			String usefulScore = scan.nextLine();
			if (usefulScore.equals("")) {
				usefulScore = dto.getUsefulScore();
			}
			
			if (Float.parseFloat(usefulScore) < 1) {
				usefulScore = "1";
			} else if (Float.parseFloat(usefulScore) > 5) {
				usefulScore = "5";
			}
			
			System.out.print("\t█전반적인 만족도 : ");
			String satisfactionScore = scan.nextLine();
			if (satisfactionScore.equals("")) {
				satisfactionScore = dto.getSatisfactionScore();
			}
			
			if (Float.parseFloat(satisfactionScore) < 1) {
				satisfactionScore = "1";
			} else if (Float.parseFloat(satisfactionScore) > 5) {
				satisfactionScore = "5";
			}
			
			System.out.print("\t█시설 만족도 : ");
			String facilityScore = scan.nextLine();
			if (facilityScore.equals("")) {
				facilityScore = dto.getFacilityScore();
			}
			
			if (Float.parseFloat(facilityScore) < 1) {
				facilityScore = "1";
			} else if (Float.parseFloat(facilityScore) > 5) {
				facilityScore = "5";
			}
			
			System.out.print("\t█사후 취업관리 만족도 : ");
			String managementScore = scan.nextLine();
			if (managementScore.equals("")) {
				managementScore = dto.getManagementScore();
			}
			
			if (Float.parseFloat(managementScore) < 1) {
				managementScore = "1";
			} else if (Float.parseFloat(managementScore) > 5) {
				managementScore = "5";
			}
			
			//수정될 평가 내용을 저장할 dto 생성
			EvaluationDTO newdto = new EvaluationDTO();
			
			//생성된 dto에 평가 내용 저장
			newdto.setSeqEvaluation(dto.getSeqEvaluation());
			newdto.setSeqRegCourse(seqRegCourse);
			newdto.setProcessScore(processScore);
			newdto.setUnderstandScore(understandScore);
			newdto.setCommunicationScore(communicationScore);
			newdto.setUsefulScore(usefulScore);
			newdto.setSatisfactionScore(satisfactionScore);
			newdto.setFacilityScore(facilityScore);
			newdto.setManagementScore(managementScore);
			
			//평가 수정하기
			int result2 = dao.edit(newdto);
			
			if (result2 > 0) {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t    과정 평가 수정 성공\t\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			} else {
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t    과정 평가 수정 실패\t\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			}
			
		} else { //평가 안함
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t   평가하지 않은 과정입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}
		
	} //edit
	
	
	private void delete() {
		
		System.out.print("\t█ 수강 번호를 입력하세요. : ");
		String seqRegCourse = scan.nextLine();
		
		//교육생이 수강한 수강 번호를 입력했는지 확인
		boolean isEndRegCourse = false;
		
		for (int i=0; i<seqRegCourses.size(); i++) {
			if (seqRegCourse.equals(seqRegCourses.get(i))) {
				isEndRegCourse = true;
			}
		}
		
		if (isEndRegCourse == false) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t해당 수강 번호가 없습니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		
		//평가했는지 확인
		int result = dao.isEvaluation(seqRegCourse);
		
		if (result != 0) { //평가함
			
			//선택한 수강 과정의 평가 내용 가져오기
			EvaluationDTO dto = dao.getEvaluation(seqRegCourse);
			
			//선택한 수강 과정의 평가 내용 출력하기
			viewEvaluationInfo(dto);
			
			//평가 삭제하기
			System.out.println();
			System.out.println("\t삭제하시겠습니까?");
			System.out.println("\t1. 예\t2. 아니오");
			System.out.println();
			System.out.print("\t█ 번호를 입력하세요. : ");
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//삭제하기
				int result2 = dao.delete(seqRegCourse);
				
				if (result2 > 0) {
					System.out.println();
					System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("\t┃\t\t\t    과정 평가 삭제 성공\t\t\t\t  ┃");
					System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					pause();
				} else {
					System.out.println();
					System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("\t┃\t\t\t    과정 평가 삭제 실패\t\t\t\t  ┃");
					System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					pause();
				}
			} else if (sel.equals("2")) {
				//삭제 취소
				System.out.println();
				System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t┃\t\t\t    과정 평가 삭제 취소\t\t\t\t  ┃");
				System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				pause();
			} else {
				//잘못 입력
				wrongInput();
				pause();
			}
			
		} else { //평가 안함
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t   평가하지 않은 과정입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}
		
	} //delete
	
	
	private void view() {
		
		System.out.print("\t█ 수강 번호를 입력하세요. : ");
		String seqRegCourse = scan.nextLine();
		
		//교육생이 수강한 수강 번호를 입력했는지 확인
		boolean isEndRegCourse = false;
		
		for (int i=0; i<seqRegCourses.size(); i++) {
			if (seqRegCourse.equals(seqRegCourses.get(i))) {
				isEndRegCourse = true;
			}
		}
		
		if (isEndRegCourse == false) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t해당 수강 번호가 없습니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//평가했는지 확인
		int result = dao.isEvaluation(seqRegCourse);
		
		if (result != 0) { //평가함
			
			//선택한 수강 과정의 평가 내용 가져오기
			EvaluationDTO dto = dao.getEvaluation(seqRegCourse);
			
			//평가 내용 출력하기
			viewEvaluationInfo(dto);
			
			System.out.println();
			pause();
			
		} else { //평가 안함
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t   평가하지 않은 과정입니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
		}	
		
	} //view

	
	private void viewEvaluationInfo(EvaluationDTO dto) {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과정 평가 정보\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.printf("\t강의계획서 이행점수 : %s\n", dto.getProcessScore());
		System.out.printf("\t교사의 강의전달 및 이해점수 : %s\n", dto.getUnderstandScore());
		System.out.printf("\t교사의 소통점수 : %s\n", dto.getCommunicationScore());
		System.out.printf("\t강의의 유익성점수 : %s\n", dto.getUsefulScore());
		System.out.printf("\t전반적인 만족도 : %s\n", dto.getSatisfactionScore());
		System.out.printf("\t시설 만족도 : %s\n", dto.getFacilityScore());
		System.out.printf("\t사후 취업관리 만족도 : %s\n", dto.getManagementScore());
		
	} //viewEvaluationInfo

	
	private void pause() {
		
		System.out.println("\t계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		
	} //pause

	
	private void wrongInput() {
		
		System.out.println("\n\t\t※ 잘못된 선택입니다.");
		System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");

	} //wrongInput
	
	
} //EvaluationManagement
