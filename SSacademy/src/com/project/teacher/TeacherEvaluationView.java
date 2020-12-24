package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.ViewEndCourseDTO;
import com.project.admin.dto.ViewSpecificEvaluationDTO;
import com.project.dao.EvaluationDAO;
import com.project.dto.TeacherDTO;

/**
 * 교사 평가 조회를 담당하는 클래스
 * @author 김주혁
 *
 */
public class TeacherEvaluationView {

	private Scanner scan;
	private EvaluationDAO dao;
	private TeacherDTO tdto;
	//종료된 개설 과정 번호를 저장하는 객체
	private ArrayList<String> seqCourses;
	
	/**
	 * 교사 평가 조회 클래스의 생성자
	 * @param tdto 로그인한 교사 정보
	 */
	public TeacherEvaluationView(TeacherDTO tdto) {
		
		scan = new Scanner(System.in);
		dao = new EvaluationDAO();
		this.tdto = tdto;
		seqCourses = new ArrayList<String>();
		
	}
	
	/**
	 * 교사 평가 조회 클래스의 메인 메서드
	 */
	public void main() {
		
		boolean loop = true;
		
		while (loop) {
			
			//교사가 강의한 과정 중에서 종료된 과정 출력
			viewCourseList();
			
			//선택 메뉴 출력
			menu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//선택한 과정 평가 조회
				viewEvaluation();
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
	
	
	private void viewCourseList() {
		
		//종료된 과정 목록을 저장하는 객체 생성
		ArrayList<ViewEndCourseDTO> list = dao.courseList(tdto.getSeqTeacher());
		
		//종료된 과정이 없는 경우
		if (list.size() == 0) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t\t종료 과정 없음\t\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//종료된 과정이 있는 경우
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t종료 과정 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[번호]\t[과정명]\t\t\t\t\t\t[과정시작일]\t[과정종료일]\t[수강인원]\t[강의실]");
		
		//종료된 과정 목록 출력 + 과정 번호 저장
		for (ViewEndCourseDTO dto : list) {
			System.out.printf("\t  %s\t%s\t%s\t%s\t   %s명\t\t%s\n"
								, dto.getSeqOpenCourse()
								, dto.getCourseName()
								, dto.getCourseStartDate()
								, dto.getCourseEndDate()
								, dto.getStudentCount()
								, dto.getRoom());
			
			seqCourses.add(dto.getSeqOpenCourse());
		}
		
	} //viewCourseList
	
	
	private void menu() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t교사 평가 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t    1. 특정 과정 평가 조회\t\t\t  │");
		System.out.println("\t│\t\t\t    0. 뒤로 가기\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
		
	} //menu
	
	
	private void viewEvaluation() {
		
		System.out.print("\t█ 과정 번호를 입력하세요. : ");
		String seqOpenCourse = scan.nextLine();
		
		//종료된 과정 번호 중에서 입력했는지 체크
		boolean isEndCourse = false;
		
		for (int i=0; i<seqCourses.size(); i++) {
			if (seqOpenCourse.equals(seqCourses.get(i))) {
				isEndCourse = true;
			}
		}
		
		//해당 과정 번호가 없는 경우
		if (isEndCourse == false) {
			System.out.println();
			System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("\t┃\t\t\t해당 과정 번호가 없습니다.\t\t\t  ┃");
			System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			pause();
			return;
		}
		
		//해당 과정 번호가 있는 경우
		ArrayList<ViewSpecificEvaluationDTO> list = dao.courseEvaluationList(seqOpenCourse);
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과정 평가 정보\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[번호]\t[이름]\t[강의계획서 이행] [교사의 강의전달 및 이해] [교사의 소통] [강의 유익성] [전반적인 만족]");
		
		
		for (ViewSpecificEvaluationDTO dto : list) {
			if (dto.getProcessScore() != null) {
				System.out.printf("\t  %s\t%s\t\t%s\t\t   %s\t\t\t%s\t\t%s\t\t%s\n"
						, dto.getSeqStudent()
						, dto.getStudentName()
						, dto.getProcessScore()
						, dto.getUnderstandScore()
						, dto.getCommunicationScore()
						, dto.getUsefulScore()
						, dto.getSatisfactionScore());
				
			} else {
				System.out.printf("\t  %s\t%s  -  평가 미실시\n"
						, dto.getSeqStudent()
						, dto.getStudentName());
			}

		}
		
		System.out.println();
		pause();
		
	} //viewEvaluation
	
	
	private void pause() {
		
		System.out.println("\t계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		
	} //pause

	private void wrongInput() {
		
		System.out.println("\n\t\t※ 잘못된 선택입니다.");
		System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");

	} //wrongInput
	

}
