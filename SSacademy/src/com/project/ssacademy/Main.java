package com.project.ssacademy;

import java.util.Scanner;

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
	
	/**
	 * 메인 메소드
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		showMainLogo();	//로고
		showLogin();	//로그인 메인
		
		
		
	}//main

	
	/**
	 * 메인 SIST academy 로고 출력 메서드
	 */
	public static void showMainLogo() {
		
		System.out.println("\n" + 
				" ███████╗██╗███████╗████████╗     █████╗  ██████╗ █████╗ ██████╗ ███████╗███╗   ███╗██╗   ██╗\n" + 
				" ██╔════╝██║██╔════╝╚══██╔══╝    ██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔════╝████╗ ████║╚██╗ ██╔╝\n" + 
				" ███████╗██║███████╗   ██║       ███████║██║     ███████║██║  ██║█████╗  ██╔████╔██║ ╚████╔╝ \n" + 
				" ╚════██║██║╚════██║   ██║       ██╔══██║██║     ██╔══██║██║  ██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  \n" + 
				" ███████║██║███████║   ██║       ██║  ██║╚██████╗██║  ██║██████╔╝███████╗██║ ╚═╝ ██║   ██║   \n" + 
				" ╚══════╝╚═╝╚══════╝   ╚═╝       ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚═╝     ╚═╝   ╚═╝   \n" +                                               
				"");
		
	}//mainlogo
	
	
	/**
	 * 메인 로그인/ 프로그램 종료 선택 페이지 메서드
	 */
	public static void showLogin() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t  메인화면\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t┏━━━━━━━━━━━━━━━━━━┓\t┏━━━━━━━━━━━━━━━━━━┓\t\t  ┃");
		System.out.println("\t┃\t\t┃     1.로그인     ┃\t┃ 0. 프로그램 종료 ┃\t\t  ┃");
		System.out.println("\t┃\t\t┗━━━━━━━━━━━━━━━━━━┛\t┗━━━━━━━━━━━━━━━━━━┛\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		System.out.println();
		System.out.print("\t█ 원하시는 메뉴를 선택하세요. : ");
		
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			//System.out.println("로그인");// + 로그인 클래스
			lg.login();
		} else if (num.equals("0"))  {
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
