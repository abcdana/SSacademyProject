package com.project.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.ScholarshipDAO;
import com.project.dao.TopStudentDAO;
import com.project.dto.StudentDTO;
import com.project.dto.TopStudentDTO;

public class TopStudentCheck {
	
	static ScholarshipDAO dao_p = new ScholarshipDAO();
	static StudentDTO sadto = new StudentDTO();
	static Scanner scanner = new Scanner(System.in);
	static Connection conn = null;
	static Statement stat = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
	
	
	public static void main(String[] args) {
		TopStudent(null);
	}
	
	/**
	 * 우수 훈련생을 조회할수 있는 메뉴를 출력하는 메소드
	 * @param sdto
	 */
	public static void TopStudent(StudentDTO sdto) {

		sadto=sdto;
		
		System.out.println(sadto.getId());
		
//		if (condition) {
//			
//		}
		
		top_student_list();
	}

	/**
	 * 우수훈련생 목록을 조회할수 있는 메소드
	 */
	private static void top_student_list() {
		TopStudentDAO dao = new TopStudentDAO();
		
		System.out.println();
		ArrayList<TopStudentDTO> list = dao.getTopStudent();
		printStudentInfoList(list); //정보 리스트를 출력하는 메소드
		System.out.println();
		System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
		scanner.nextLine();
	}
	
	/**
	 * 우수교육생을 출력하는 메소드
	 * @param 우수교육생 정보
	 */
	public static void printStudentInfo(TopStudentDTO dto) {
		System.out.printf(""
				+ "\t=====%s번 교육생=====\n"
				+ "\t시험 성적 번호 : %s\n"
				+ "\t혜택 번호 : %s\n",
				dto.getSeqTopStudent(),
				dto.getSeqTestScore(),
				dto.getSeqScholarship()
		);
	}
	
	/**
	 * 우수교육생 목록을 출력하는 메소드
	 * @param 우수교육생 리스트 정보
	 */
	public static void printStudentInfoList(ArrayList<TopStudentDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println();
			System.out.printf(""
					+ "\t=====%s번 우수생=====\n"
					+ "\t수강번호 : %s\n"
					+ "\t학생명 : %s\n"
					+ "\t혜택명 : %s\n"
					+ "\t혜택상품 : %s\n"
					+ "\t혜택내용 : %s\n",
					list.get(i).getSeqTopStudent(),
					list.get(i).getSeqRegCourse(),
					list.get(i).getStName(),
					list.get(i).getSsName(),
					list.get(i).getPrize(),
					list.get(i).getDescrip()
			);
		}
	}
}







