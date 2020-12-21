package com.project.ssacademy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.project.admin.AdminController;
import com.project.student.StudentController;
import com.project.teacher.TeacherController;

public class Login {

	private static Scanner scan;
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;

	static {
		scan = new Scanner(System.in);
	}

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public Login() {
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryLogin.enLogin()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 통합 로그인 페이지
	 */
	public void unifiedLoginPage() {
		
		String id = "";
		String pw = "";
		boolean check = true;
		
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t   로그인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		System.out.print("\t█ ID : ");
		id = scan.nextLine();		
		System.out.print("\t█ PW : ");
		pw = scan.nextLine();
		
		
		try {
			//통합로그인으로 구현 -> union을 이용하여 관리자, 교육생, 교사의 id, pw만 조회	
			String sql = "select id, pw, id as name from tblAdmin \n" + 
					"    union select id, substr(ssn, 8, 7), name from tblStudent\n" + 
					"        union select id, substr(ssn, 8, 7), name from tblTeacher\n" + 
					"            order by id;";
			 
			rs = stat.executeQuery(sql);
		
			while (check) {
				
				while(rs.next()) {
					if (rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
						check = false;
						break;
					} else {
						check = true;
					}
				}
				
				if (check == false) {
					
					if ((id.substring(0, 1)).equals("A")) { //아이디 A로 시작 : 관리자
						
						System.out.printf("\n\t\t관리자 %s님 SSacademy 접속을 환영합니다.", id);
						AdminController admin = new AdminController();
						admin.adminMain();
						
					} else if ((id.substring(0, 1)).equals("S")) { //아이디 S로 시작 : 교육생

						sql = "select id, substr(ssn, 8, 7) pw, name from tblStudent";
						rs = stat.executeQuery(sql);
					
						while(rs.next()) {
							if (rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
								System.out.printf("\n\t\t교육생 %s님 SSacademy 접속을 환영합니다.", rs.getString("name")); 
								StudentController student = new StudentController();
								student.studentMain();
							}
						}
						
					} else if ((id.substring(0, 1)).equals("T")) { //아이디 T로 시작 : 교사
											
						sql = "select id, substr(ssn, 8, 7) pw, name from tblTeacher";
						rs = stat.executeQuery(sql);
						
						while(rs.next()) {
							if (rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
								System.out.printf("\n\t\t교사 %s님 SSacademy 접속을 환영합니다.", rs.getString("name"));
								TeacherController teacher = new TeacherController();
								teacher.teacherMain();
							}
						}
					} 
					
				} else {
					System.out.println("\n\t\t※ 아이디또는 비밀번호가 일치하지 않습니다.");
					unifiedLoginPage();
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("primaryLogin.enUnifiedLoginPage()");
			e.printStackTrace();
		}
		
	}//unifiedLoginPage()
	
	
}
