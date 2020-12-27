package com.project.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.BasicTestDAO;
import com.project.dao.VwStudentTestScoreDAO;
import com.project.dto.BasicTestDTO;
import com.project.dto.VwStudentTestScoreDTO;


/**
 * 학생 모드의 모든 성적조회관련 기능을 담당하는 클래스이다.
 * @author 조성진
 *
 */
public class StudentTestScoreInquiry {
	private Scanner scan;
	private VwStudentTestScoreDAO vsto;
	
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다.
	 */
	public StudentTestScoreInquiry() {
		scan = new Scanner(System.in);
		vsto = new VwStudentTestScoreDAO();
	}
	

	/**
	 * 학생의 시험정보를 과목별로 조회하는 메서드
	 */
	public void TestQuestionInquiry() {

		System.out.print("\n\t조회할 과목번호를 입력해주세요 : ");
		String subSeq = scan.nextLine();
		
		ArrayList<VwStudentTestScoreDTO> list = vsto.list(subSeq);
		System.out.println("\n");
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t성적 조회\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t   [과목명]\t\t[강의실]\t[교사명]\t    [필기배점]\t    [실기배점]\t    [출결배점]\t    [필기점수]\t    [실기점수]\t    [출결점수]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (VwStudentTestScoreDTO dto : list) {
			System.out.printf("\t%s\t%s\t %s\t\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
							,dto.getSubjectName()
							,dto.getRoomName()
							,dto.getTeacherName()
							,dto.getWrittenPercent()
							,dto.getPracticalPercent()
							,dto.getAttendancePercent()
							,dto.getWrittenScore()
							,dto.getPracticalScore()
							,dto.getAttendanceScore());

			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}
		System.out.println();
	}
}
