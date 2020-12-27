package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.TeacherDAO;
import com.project.dto.TeacherDTO;
import com.project.teacher.dto.TeacherScheduleDTO;

/**
 * 교사의 강의스케줄을 조회하는 클래스 
 *@author 박지현
 *
 */
public class ScheduleManage {
	
	static Scanner scan;
	private TeacherView tView;
	private TeacherDAO tdao;
	private TeacherDTO tdto2;
	
	//생성자
	public ScheduleManage () {
		
		tView = new TeacherView();
		scan = new Scanner(System.in);
		tdao = new TeacherDAO();
		tdto2 = new TeacherDTO();
	}
	
	
	
	/**
	 * 강의스케줄조회 메인메뉴입니다.
	 */
	public void scheduleStart() {
		
		//강의스케줄 조회 메인헤더
		tView.scheduleMain();
		
		String num = scan.nextLine();
		
		if(num.equals("1")) {
			//교사명으로 조회
			scheduleTeacherName();
		} else if(num.equals("2")) {
			//교사번호로 조회
			scheduleSeqTeacher();
		} else if(num.equals("0")) {
			//교사메인
			TeacherController t = new TeacherController(tdto2);
			t.teacherMain();
			
		} else {
			System.out.println("\t\t** 잘못 입력했습니다. 다시 입력해주세요. ** ");
			scheduleStart();
		}
	}
	
	/**
	 * 교사명으로 강의스케줄 조회 메서드입니다.
	 */
	public void scheduleTeacherName() {
		
		//교사명으로 조회 헤더
		tView.scheduleTeacherName();
		
		//교사리스트 컬럼명
		System.out.println("\t ** 아래 교사 리스트에서 교사명을 선택해주세요.\n");
		

		
		//교사리스트
		ArrayList<TeacherDTO> list2 = tdao.list();
				
		for(TeacherDTO dto : list2) {
					
			System.out.printf("\t%5s%10s\t%10s\t%18s\n"
							, dto.getSeqTeacher()
							, dto.getName()
							, dto.getSsn().substring(0, 6)
							, dto.getTel());
					
			System.out.println("\t ───────────────────────────────────────────────────────────────");
		}
		
		//교사명받기
		tView.scheduleTeacherName2();		
		String name = scan.nextLine();
		
		ArrayList<TeacherScheduleDTO> list = tdao.scheduleNameTeacher(name);
		
		if(name.equals("0")) {
			scheduleStart(); //강의스케줄 조회 메인
		}
	
		tView.scheduleTeacherName3(name);
		
		for(TeacherScheduleDTO tdto : list) {
			
			//교사명 조회 결과
			System.out.printf("\t%6s%9s\t%-40s\t%-12s\t%-12s\t%-5s\n"
						, tdto.getSeqTeacher()
						, tdto.getTeahcerName()
						, tdto.getCourseName()
						, tdto.getStartDate()
						, tdto.getEndDate()
						, tdto.getMemberCount() + "명");
				
			System.out.println("\t ─────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			 
		}//for
		
		//교사명 조회 바텀
		tView.scheduleView2();
		String num = scan.nextLine();
		
		if(! num.equals("")) {
			scheduleTeacherName();
		} else {
			scheduleTeacherName();
			
		}
		
	}
	
	
	
	/**
	 * 교사번호로 강의스케줄 조회 메서드입니다.
	 */
	public void scheduleSeqTeacher() {
		
		
		//교사번호로 조회 헤더
		tView.scheduleSeqTeacher();
		
		//교사리스트 컬럼명
		System.out.println("\t ** 아래 교사 리스트에서 교사 번호를 선택해주세요.");
		System.out.println();


		//교사리스트
		ArrayList<TeacherDTO> list2 = tdao.list();
		
		for(TeacherDTO dto : list2) {

			System.out.printf("\t%5s%10s\t%10s\t%18s\n"
					, dto.getSeqTeacher()
					, dto.getName()
					, dto.getSsn()
					, dto.getTel());
			
			System.out.println("\t ───────────────────────────────────────────────────────────────");
		}
		
		//교사번호입력 
		tView.scheduleSeqTeacher2();
		String seqTeacher = scan.nextLine();
		
		if(seqTeacher.equals("0")) {
			scheduleStart(); 
		} 
			
		ArrayList<TeacherScheduleDTO> list = tdao.scheduleSeqTeacher(seqTeacher);

		tView.scheduleSeqView();
				
		for(TeacherScheduleDTO tdto : list) {
			System.out.printf("\t%6s%9s\t%-40s\t%-12s\t%-12s\t%-5s\n"
									, tdto.getSeqTeacher()
									, tdto.getTeahcerName()
									, tdto.getCourseName()
									, tdto.getStartDate()
									, tdto.getEndDate()
									, tdto.getMemberCount() + "명");
			
					System.out.println("\t ─────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			}//for1
				
		//교사번호 조회 바텀
		tView.scheduleView2();
		String num = scan.nextLine();
						
		if(! num.equals("")) {
			scheduleSeqTeacher();
		} else {
			scheduleSeqTeacher();
		}
		
	}	
	
	public void pause() {
		
		scan.nextLine();
	}

}
