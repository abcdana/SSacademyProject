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

public class TopStudent {
	
	static ScholarshipDAO dao_p = new ScholarshipDAO();
	static StudentDTO sdto = new StudentDTO();
	static Scanner scanner = new Scanner(System.in);
	static Connection conn = null;
	static Statement stat = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
	
	
	/**
	 * 우수 훈련생을 조회할수 있는 메뉴를 출력하는 메소드
	 * @param sdto
	 */
	public static void TopStudent(StudentDTO sdto) {

		TopStudent.sdto=sdto;
		
		top_student_list();
	}

	/**
	 * 우수훈련생 목록을 조회할수 있는 메소드
	 */
	private static void top_student_list() {
		TopStudentDAO dao = new TopStudentDAO();
		
		System.out.println();
		ArrayList<TopStudentDTO> list = dao.getTopStudent();
		boolean result = false;
		int eq=0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStId().equals(sdto.getId())) {
				result = true;
				eq=i;
			}
		}
		if (result) {
			TopStudentDTO dto = list.get(eq);
			System.out.printf("\t\t※ 축하합니다. %s 훈련생으로 선정되셨습니다.\n"
					+ "\t=====No.%s=====\n"
					+ "\t수강번호  : %s\n"
					+ "\t성명      : %s\n"
					+ "\t혜택 상품 : %s\n"
					+ "\t해택 사유 : %s\n",
					dto.getSsName(),
					dto.getSeqTopStudent(),
					dto.getSeqRegCourse(),
					dto.getStName(),
					dto.getPrize(),
					dto.getDescrip()
			);
		}else {
			System.out.println("\t해당 내역이 없습니다.");
		}
		
		System.out.println();
		System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
		scanner.nextLine();
	}
	

}







