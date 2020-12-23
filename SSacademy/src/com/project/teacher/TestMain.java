package com.project.teacher;

import java.util.Random;

public class TestMain {
	public static void main(String[] args) {

		
//		TeacherController contrl = new TeacherController();
	int cnt = 4;
		for (int i = 4; i <= 30;i++) {
			for (int k = 0; k < 5; k++) {
				if(cnt <= 30) {
				for (int j = 10 * (i - 4) + 11; j < 10 * (i - 3) + 11; j++) {
					System.out.println(String.format(
							"insert into tblTestPercent (seqTestPercent, seqRegCourse, seqBasicTest, writtenPercent, practicalPercent, attendancePercent) "
									+ "values (seqTestPercent.nextVal, %d, %d, 40, 40, 20);",
							j,cnt ));
				}
				cnt++;
				}
			}
			
		}

//	Random rand = new Random();
//
//	for (int i = 31; i < 300; i++) {
//		int writ = 0;
//		int prac = 0;
//		int attend = 0;
//		while (writ + prac + attend != 100) {
//			writ = rand.nextInt(70)+31;
//			prac = rand.nextInt(60)+41;
//			attend = rand.nextInt(80)+21;
//		}
//		System.out.println(String.format(
//				"insert into tblTestScore (seqTestScore, seqTestPercent, writtenScore, practicalScore, attendanceScore) values (seqTestScore.nextVal, %d, %d, %d, %d);",i,writ,prac,attend));
//	}
	
	
//	int writ = 0;
//	int prac = 0;
//	int attend = 0;
//	while (writ + prac + attend != 100) {
//		
//		writ = rand.nextInt(70)+31;
//		prac = rand.nextInt(60)+41;
//		attend = rand.nextInt(80)+21;
//		System.out.println("필기: "  + writ);
//		System.out.println("실기: " + prac);
//		System.out.println("출결: " +attend);
//	}
//	System.out.println(writ);
//	System.out.println(prac);
//	System.out.println(attend);
//	
	
}
}
