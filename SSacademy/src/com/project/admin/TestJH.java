package com.project.admin;

import com.project.dao.OpenSubjectDAO;

import oracle.sql.OffsetDST;

/**
 * @author jenny
 *
 */
public class TestJH {
	
	public static void main(String[] args) {
		
//		OpenSubjectDAO a = new OpenSubjectDAO();
//		a.SpecificOpenSubject("1");
		
		OpenCourse oc = new OpenCourse();
		oc.OpenCourseList();
		
//		
//		OpenSubject os = new OpenSubject();
//		os.OpenSubjectView();
//		
//		OpenSubjectDAO oss = new OpenSubjectDAO();
//		oss.OpenSubjectList();
		
//		System.out.printf("가나다%d라마바\n", 10);
//		System.out.printf("가나다%10d라마바\n", 10);
//		System.out.printf("가나다%-10d라마바\n", 10);
	}

}
