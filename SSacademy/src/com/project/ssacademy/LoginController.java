package com.project.ssacademy;

import com.project.dao.LoginDAO;
/**
 * 로그인 화면을 출력하는 클래스입니다.
 * @author 김다은
 *
 */
public class LoginController {

	private LoginDAO lgDAO;
	
	/**
	 * 생성자
	 */
	public LoginController() {
		this.lgDAO = new LoginDAO();
	}

	public void login() {
		
		System.out.println();
		System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t┃\t\t\t\t   로그인\t\t\t\t  ┃");
		System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		
		lgDAO.unifiedLoginPage(); //통합 로그인
	}
	

	
}
