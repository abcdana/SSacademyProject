package com.project.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.dto.CourseSearchDTO;
import com.project.admin.dto.SpecificCourseEvaluationDTO;
import com.project.dao.EvaluationDAO;

public class EvaluationManagement {

	private Scanner scan;
	private EvaluationDAO dao;
	
	public EvaluationManagement() {
		
		scan = new Scanner(System.in);
		dao = new EvaluationDAO();
		
	}

	public void main() {
		
		boolean loop = true;
		
		while (loop) {
			
			endCourseList();
			
			menu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				//특별 과정 평가 조회
				viewCourseEvaluation();
			} else if (sel.equals("2")) {
				//특정 교사 평가 조회
				viewTeacherEvaluation();
			} else if (sel.equals("0")) {
				//뒤로 가기
				loop = false;
			} else {
				//잘못 입력
				wrongInput();
				pause();
			}
			
			
			
		} //while
		
	}

	private void viewTeacherEvaluation() {
		
		System.out.print("\t█ 교사 번호를 입력하세요. : ");
		String seqTeacher = scan.nextLine();
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t해당 교사 담당 과정 목록\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[교사번호]\t[교사이름]\t[개설과정번호]\t[과정명]\t\t\t\t\t\t[과정시작일]\t[과정종료일]\t[수강인원]");
		
		ArrayList<CourseSearchDTO> list = dao.courseList(seqTeacher);
		
		for (CourseSearchDTO dto : list) {
			System.out.printf("\t    %s\t\t  %s\t      %s\t\t%s\t%s\t%s\t    %s\n"
								, dto.getSeqTeacher()
								, dto.getTeacherName()
								, dto.getSeqOpenCourse()
								, dto.getCourseName()
								, dto.getCourseStartDate()
								, dto.getCourseEndDate()
								, dto.getStudentCount());
		}
		
		System.out.println();
		viewCourseEvaluation();
		
		
	} //viewTeacherEvaluation

	private void viewCourseEvaluation() {
		
		System.out.print("\t█ 과정 번호를 입력하세요. : ");
		String seqOpenCourse = scan.nextLine();
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t과정 평가 정보\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[번호]\t[이름]\t[강의계획서 이행] [교사의 강의전달 및 이해] [교사의 소통] [강의 유익성] [전반적인 만족] [시설 만족] [사후 관리 만족]");
		
		ArrayList<SpecificCourseEvaluationDTO> list = dao.courseEvaluationList(seqOpenCourse);

		for (SpecificCourseEvaluationDTO dto : list) {
			System.out.printf("\t  %s\t%s\t\t%s\t\t   %s\t\t\t%s\t\t%s\t\t%s\t   %s\t\t  %s\n"
								, dto.getSeqStudent()
								, dto.getStudentName()
								, dto.getProcessScore()
								, dto.getUnderstandScore()
								, dto.getCommunicationScore()
								, dto.getUsefulScore()
								, dto.getSatisfactionScore()
								, dto.getFacilityScore()
								, dto.getManagementScore());

		}
		
		System.out.println();
		pause();
		
	} //viewCourseEvaluation
	

	private void endCourseList() {
		
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t종료 과정 목록\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t[교사번호]\t[교사이름]\t[개설과정번호]\t[과정명]\t\t\t\t\t\t[과정시작일]\t[과정종료일]\t[수강인원]");
		
		ArrayList<CourseSearchDTO> list = dao.courseList(null);
		
		for (CourseSearchDTO dto : list) {
			System.out.printf("\t    %s\t\t  %s\t      %s\t\t%s\t%s\t%s\t    %s\n"
								, dto.getSeqTeacher()
								, dto.getTeacherName()
								, dto.getSeqOpenCourse()
								, dto.getCourseName()
								, dto.getCourseStartDate()
								, dto.getCourseEndDate()
								, dto.getStudentCount());
		}
		
		
	} //endCourseList

	private void menu() {
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t평가 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┌─────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│\t\t\t    1. 특정 과정 평가 조회\t\t\t  │");
		System.out.println("\t│\t\t\t    2. 특정 교사 평가 조회\t\t\t  │");
		System.out.println("\t│\t\t\t    0. 뒤로 가기\t\t\t\t  │");
		System.out.println("\t└─────────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		
		System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
	}
	
	private void pause() {
		
		System.out.println("\t계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		
	} //pause

	private void wrongInput() {
		
		System.out.println("\n\t\t※ 잘못된 선택입니다.");
		System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");

	} //wrongInput
	
}
