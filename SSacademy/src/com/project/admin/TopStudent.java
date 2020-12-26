package com.project.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.dao.ScholarshipDAO;
import com.project.dao.StudentDAO;
import com.project.dao.TopStudentDAO;
import com.project.dto.AdminDTO;
import com.project.dto.ScholarshipDTO;
import com.project.dto.StudentDTO;
import com.project.dto.TopStudentDTO;
import com.project.ssacademy.DBUtil;

public class TopStudent {
	
	static AdminView view = new AdminView();
	static ScholarshipDAO dao_p = new ScholarshipDAO();
	static AdminDTO sadto = new AdminDTO();
	static Scanner scanner = new Scanner(System.in);
	static Connection conn = null;
	static Statement stat = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
	
	
	public static void main(String[] args) {
		TopStudent(null);
	}
	
	/**
	 * 우수 훈련생 / 우수훈련생 포상 목록을 조회할수 있는 메뉴를 출력하는 메소드
	 * @param adto
	 */
	public static void TopStudent(AdminDTO adto) {

		int listT;
		sadto=adto;
		
		while(true) {
			
			view.menu_topStd();
			
			System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
			String data = scanner.nextLine();
			
			if(data.equals("1")){ //우수 훈련생 목록 조회
				listT=1;
				break;
			}else if(data.equals("2")){ //우수 훈련생 포상 목록 조회
				listT=2;
				break;
			}else {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
			}
		}
		
		if (listT==1) {
			while(true) {
				view.menu_topStd_prize(1);
				
				System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
				String data = scanner.nextLine();
				
				if(data.equals("1")){ //우수 훈련생 목록 조회
					top_student_list();
				}else if(data.equals("2")){ //우수 훈련생 등록
					top_student_add();
				}else if(data.equals("3")){ //우수 훈련생 수정
					top_student_mod();
				}else if(data.equals("4")){ //우수 훈련생 삭제
					top_student_remove();
				}else if(data.equals("0")){ //뒤로가기
					AdminController AdCon = new AdminController(adto);
					AdCon.adminMain();
				}else {
					System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
				}
			}
		}else if(listT==2) {
			while(true) {
				view.menu_topStd_prize(2);
				
				System.out.print("\t█ 원하시는 메뉴를 입력하세요. : ");
				String data = scanner.nextLine();
				
				if(data.equals("1")){ //포상 목록 조회
					top_prize_list();
				}else if(data.equals("2")){ //포상 등록
					top_prize_add();
				}else if(data.equals("3")){ //포상 수정
					top_prize_mod();
				}else if(data.equals("4")){ //포상 삭제
					top_prize_remove();
				}else if(data.equals("0")){ //뒤로가기
					AdminController AdCon = new AdminController(adto);
					AdCon.adminMain();
				}else {
					System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력하세요.\n");
				}
			}
		}
	}

	/**
	 * 우수훈련생 목록을 조회할수 있는 메소드
	 */
	private static void top_student_list() {
		TopStudentDAO dao = new TopStudentDAO();
		
		System.out.println();
		ArrayList<TopStudentDTO> list = dao.getTopStudent();
		printStudentInfoList(list); //정보 리스트를 출력하는 메소드
		System.out.println();
		System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
		scanner.nextLine();
	}

	/**
	 * 우수훈련생을 추가할 수 있는 메소드
	 */
	private static void top_student_add() {
		
	}

	/**
	 * 우수훈련생을 수정할 수 있는 메소드
	 */
	private static void top_student_mod() {
	}

	/**
	 * 우수훈련생을 삭제할 수 있는 메소드
	 */
	private static void top_student_remove() {
	}

	/**
	 * 혜택 목록을 출력하는 메소드
	 */
	private static void top_prize_list() {
		ScholarshipDAO dao = new ScholarshipDAO();
		ArrayList<ScholarshipDTO> list = new ArrayList<ScholarshipDTO>();
		
		System.out.println();
		System.out.println("\t█ 우수 훈련 포상 목록");
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblScholarship";
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				ScholarshipDTO dto = new ScholarshipDTO();
				dto.setSeqScholarship(rs.getString("seqScholarship"));
				dto.setName(rs.getString("name"));
				dto.setPrize(rs.getString("prize"));
				dto.setDesc(rs.getString("descrip"));
				
				list.add(dto);
			};
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("TopScholarship.top_list()");
			e.printStackTrace();
		}
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println();
			System.out.printf(""
					+ "\t=====%s번 혜택=====\n"
					+ "\t혜택명   : %s\n"
					+ "\t혜택상품 : %s\n"
					+ "\t혜택사유 : %s\n",
					list.get(i).getSeqScholarship(),
					list.get(i).getName(),
					list.get(i).getPrize(),
					list.get(i).getDesc()
			);
		}
		
		System.out.println();
		System.out.println("\t█ 뒤로 가시려면 엔터를 입력하세요.");
		scanner.nextLine();
		TopStudent(sadto);
	}

	/**
	 * 혜택을 추가하는 메소드
	 */
	private static void top_prize_add() {
		ScholarshipDAO.addScholarship();
		TopStudent(sadto);
	}

	/**
	 * 혜택을 수정하는 메소드
	 */
	private static void top_prize_mod() {
		ScholarshipDAO.modScholarship();
		TopStudent(sadto);
	}

	/**
	 * 혜택을 삭제하는 메소드
	 */
	private static void top_prize_remove() {
		ArrayList<ScholarshipDTO> list = dao_p.ScholarshipList(null);
		printPrizeInfoList(list); //리스트를 출력하는 메소드
		
		System.out.println();
		
		System.out.print("\t█ 삭제하실 번호를 입력하세요 : ");
		String seq = scanner.nextLine();
		
		ScholarshipDTO dto = dao_p.getStudentSeq(seq);
		if (dto==null) {
			System.out.print("\t\t※ 일치하는 혜택이 없습니다.\n"
					+ "\t\t  이전 화면으로 이동합니다.");
			TopStudent(sadto);
		}
		
		printPrizeInfo(dto); //정보를 출력하는 메소드
		
		System.out.println();
		System.out.print("\t█ 삭제하시겠습니까? (y/n) : "); 
		String txt = scanner.nextLine();
		if (!txt.toUpperCase().equals("Y")) {
			System.out.println("\t취소되었습니다. 이전 메뉴로 돌아갑니다.");
			TopStudent(sadto);
		}
		
		int result = dao_p.removeScholarship(seq); //정보를 삭제하는 메소드
		
		if (result > 0) {
			System.out.println("\t\t※ 정보 삭제가 완료되었습니다.");
		} else {
			System.out.println("\t\t※ 정보 삭제에 실패했습니다.");
		}
		
		TopStudent(sadto);
	}
	
	/**
	 * 혜택정보를 출력하는 메소드
	 * @param 혜택정보
	 */
	public static void printPrizeInfo(ScholarshipDTO dto) {
		System.out.printf(""
				+ "\t=====%s번 혜택=====\n"
				+ "\t혜택명   : %s\n"
				+ "\t혜택상품 : %s\n"
				+ "\t혜택사유 : %s\n",
				dto.getSeqScholarship(),
				dto.getName(),
				dto.getPrize(),
				dto.getDesc()
		);
	}

	/**
	 * 혜택정보 목록을 출력하는 메소드
	 * @param 혜택정보 리스트
	 */
	public static void printPrizeInfoList(ArrayList<ScholarshipDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println();
			System.out.printf(""
					+ "\t=====%s번 혜택=====\n"
					+ "\t혜택명   : %s\n"
					+ "\t혜택상품 : %s\n"
					+ "\t혜택사유 : %s\n",
					list.get(i).getSeqScholarship(),
					list.get(i).getName(),
					list.get(i).getPrize(),
					list.get(i).getDesc()
			);
		}
	}
	
	/**
	 * 우수교육생을 출력하는 메소드
	 * @param 우수교육생 정보
	 */
	public static void printStudentInfo(TopStudentDTO dto) {
		System.out.printf(""
				+ "\t=====%s번 교육생=====\n"
				+ "\t시험 성적 번호 : %s\n"
				+ "\t혜택 번호 : %s\n",
				dto.getSeqTopStudent(),
				dto.getSeqTestScore(),
				dto.getSeqScholarship()
		);
	}
	
	/**
	 * 우수교육생 목록을 출력하는 메소드
	 * @param 우수교육생 리스트 정보
	 */
	public static void printStudentInfoList(ArrayList<TopStudentDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println();
			System.out.printf(""
					+ "\t=====%s번 우수생=====\n"
					+ "\t수강번호 : %s\n"
					+ "\t학생명 : %s\n"
					+ "\t혜택명 : %s\n"
					+ "\t혜택상품 : %s\n"
					+ "\t혜택내용 : %s\n",
					list.get(i).getSeqTopStudent(),
					list.get(i).getSeqRegCourse(),
					list.get(i).getStName(),
					list.get(i).getSsName(),
					list.get(i).getPrize(),
					list.get(i).getDescrip()
			);
		}
	}
}







