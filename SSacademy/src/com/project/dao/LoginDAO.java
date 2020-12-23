package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.AdminController;
import com.project.dto.AdminDTO;
import com.project.dto.StudentDTO;
import com.project.dto.TeacherDTO;
import com.project.ssacademy.DBUtil;
import com.project.student.StudentController;
import com.project.teacher.TeacherController;

/**
 * 
 * @author 김다은
 *
 */
public class LoginDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public LoginDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryLoginDAO.enLogin()");
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
		
		System.out.print("\t█ ID : ");
		id = scan.nextLine();		
		System.out.print("\t█ PW : ");
		pw = scan.nextLine();
		
		try {
			//통합로그인으로 구현 -> union을 이용하여 관리자, 교육생, 교사의 id, pw만 조회	
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
				
				if (check == false) {
					
					if ((id.substring(0, 1)).equals("A")) { //아이디 A로 시작 : 관리자
						
						//로그인 한 관리자 계정의 객체를 가져오기 위해서 DAO 객체 생성
						AdminDAO dao = new AdminDAO();
						//dao.getAdmin메서드는 로그인한 계정의 id를 매개변수로 넘겨 관리자 객체를 리턴
						AdminDTO dto = dao.getAdmin(id);
						
						//가져온 AdminDTO객체를 AdminController객체의 생성자 매개변수로 넘김
						AdminController admin = new AdminController(dto);
						// 로그인한 관리자의 객체정보를 가진 상태로 관리자 메인메뉴 메서드로 이동
						admin.adminMain();
						
					} else if ((id.substring(0, 1)).equals("S")) { //아이디 S로 시작 : 교육생
						
						StudentDAO dao = new StudentDAO();
						ArrayList<StudentDTO> list = dao.getStudent(id);
						
						StudentController student = new StudentController(list.get(0));
						student.studentMain();
						
					} else if ((id.substring(0, 1)).equals("T")) { //아이디 T로 시작 : 교사

						TeacherDAO dao = new TeacherDAO();
						TeacherDTO dto = dao.getTeacher(id);
						
						TeacherController teacher = new TeacherController(dto);
						teacher.teacherMain();
						
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
