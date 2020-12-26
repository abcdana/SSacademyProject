package com.project.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.BasicTestDAO;
import com.project.dto.BasicTestDTO;

public class TeacherTestQuestion {

	private Scanner scan;
	private BasicTestDAO bt;
	
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다.
	 */
	public TeacherTestQuestion() {
		scan = new Scanner(System.in);
		bt = new BasicTestDAO();
	}
	

	/**
	 * 과목별로 시험문제를 조회하는 메서드
	 */
	public void TestQuestionInquiry() {

		System.out.print("\t조회할 과목번호를 입력해주세요 : ");
		String subSeq = scan.nextLine();
		
		ArrayList<BasicTestDTO> list = bt.list(subSeq);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[테스트 번호]\t\t[문제번호]\t\t\t\t\t [시험문제]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (BasicTestDTO dto : list) {
			System.out.printf("\t     %s\t\t\t     %s\t\t\t %-30s\n"
								,dto.getSeqBasicTest()
								,dto.getQuestionNum()
								,dto.getQuestion());
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}
		System.out.println();
		pause();
	}
	
	/**
	 * 시험문제 추가하는 메서드
	 */
	
	public void TestQuestionInsert() {
		System.out.print("\t시험문제를 추가하고싶은 과목번호를 입력해주세요 : ");
		String subSeq = scan.nextLine();
		
		System.out.print("\t문제 번호를 입력해주세요 : ");
		String questionNum = scan.nextLine();
		
		System.out.print("\t문제 내용을 입력해주세요 : ");
		String question = scan.nextLine();
		
		bt.questionAdd(subSeq,questionNum,question);
		
		ArrayList<BasicTestDTO> list = bt.list(subSeq);

		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t[테스트 번호]\t\t[문제번호]\t\t\t\t\t [시험문제]");
		System.out.println(
				"\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (BasicTestDTO dto : list) {
			System.out.printf("\t     %s\t\t\t     %s\t\t\t %-30s\n"
								,dto.getSeqBasicTest()
								,dto.getQuestionNum()
								,dto.getQuestion());
			System.out.print(
					"\t―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n");

		}
		System.out.println();
		pause();
	}




	public void pause() {

		System.out.print("\t진행하시려면 Enter를 눌러주세요..");
		scan.nextLine();

	}
	
}
