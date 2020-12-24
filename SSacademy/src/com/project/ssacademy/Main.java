package com.project.ssacademy;

import java.util.Scanner;

import com.project.admin.AdminView;

/**
 * 프로그램 첫 화면을 보여주고, 로그인을 시작하는 메인 클래스이다.
 * @author 김다은
 *
 */
public class Main {
	
	private static Scanner scan;
	private static LoginController lg;
	
	static {
		lg = new LoginController();
		scan = new Scanner(System.in);
	}
	
	//메인
	public static void main(String[] args) {

		while (true) {
			AdminView.showMainLogo();	//로고
			showLogin();	//로그인 메인			
		}
	}//main

	
	/**
	 * 메인 로그인/ 프로그램 종료 선택 페이지 메서드
	 */
	public static void showLogin() {
		
		Scanner scan = new Scanner(System.in);

		AdminView.showLoginHeader();		
		String num = scan.nextLine();
		
		if (num.equals("1")) {	//로그인
			lg.login();	
		} else if (num.equals("0"))  {	//프로그램 종료
			System.out.println("\n\t\t**프로그램을 종료합니다.**");
			System.exit(0);
		} else {
			System.out.println("\n\t\t※ 잘못된 선택입니다.");
			System.out.println("\t\t입력하신 번호를 다시 확인해주세요.");
			System.out.println();
			showLogin();
		}
		
	}//showLogin
	
}
