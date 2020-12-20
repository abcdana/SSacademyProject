package com.project.ssacademy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {

	private static Scanner scan;
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;
	private static Login lg;

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
				
			String sql = "select id, pw from tblAdmin \n" + 
					"    union select id, substr(ssn, 8, 7) pw from tblStudent\n" + 
					"        union select id, substr(ssn, 8, 7) pw from tblTeacher";
			
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
				
				rs.close();
				
				if (check == false) {
					

					if ((id.substring(0, 1)).equals("A")) {
						
						//System.out.println("관리자");
						System.out.printf("\n\t\t관리자 %s님 SSacademy 접속을 환영합니다.", id);
						
					} else if ((id.substring(0, 1)).equals("S")) {

						sql = "select id, substr(ssn, 8, 7) pw, name from tblStudent";
						rs = stat.executeQuery(sql);
						
						while(rs.next()) {
							if (rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
								System.out.printf("\n\t\t교육생 %s님 SSacademy 접속을 환영합니다.", rs.getString("name")); 
							}
						}
						
					} else if ((id.substring(0, 1)).equals("T")) {
											
						sql = "select id, substr(ssn, 8, 7) pw, name from tblTeacher";
						rs = stat.executeQuery(sql);
						
						while(rs.next()) {
							if (rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
								System.out.printf("\n\t\t교사 %s님 SSacademy 접속을 환영합니다.", rs.getString("name"));
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
