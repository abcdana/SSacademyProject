package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.admin.TopStudent;
import com.project.dto.ScholarshipDTO;
import com.project.dto.StudentDTO;
import com.project.dto.TopStudentDTO;
import com.project.ssacademy.DBUtil;
/**
 * 혜택정보 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class ScholarshipDAO {

	private static Connection conn;
	private static Statement stat;
	private static PreparedStatement pstat;
	private static CallableStatement cstat;
	private static ResultSet rs;
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public ScholarshipDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryScholarshipDAO.enScholarshipDAO()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 선택한 혜택 번호의 정보를 반환하는 메소드
	 * @param SeqRegCourse
	 * @return dto
	 */
	public ScholarshipDTO getStudentSeq(String Seq) {
		
		try {
			
			String sql = "select * from tblScholarship where seqScholarship = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, Seq);
			rs = pstat.executeQuery();
			
			return setDTO(rs);
			
		} catch (Exception e) {
			System.out.println("primaryScholarshipDAO.engetStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 해택을 추가하는 메소드
	 */
	public static void addScholarship() {
		
		System.out.println();
		System.out.print("\t- 제목을 입력하세요.(1/3) : ");
		String name = scanner.nextLine();
		System.out.print("\t- 혜택 상품을 입력하세요.(2/3) : ");
		String prize = scanner.nextLine();
		System.out.print("\t- 혜택 내용을 입력하세요.(3/3) : ");
		String descrip = scanner.nextLine();

		try {
			
			conn = DBUtil.open();
			
			String sql = "insert into tblScholarship (seqScholarship,name,prize,descrip) values (seqScholarship.nextVal,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,name);
			pstat.setString(2,prize);
			pstat.setString(3,descrip);
			
			int result = pstat.executeUpdate();
			
			System.out.println();
			pstat.close();
			
			if (result==1) {
				System.out.println("\t\t※ 해택 추가가 완료되었습니다.");
			}else {
				System.out.println("\t\t※ 해택 추가실패");
			}
			System.out.println();
			
			return;
			
		} catch (Exception e) {
			System.out.println("test.main()");
			e.printStackTrace();
		}
		
	}

	/**
	 * 해택을 수정하는 메소드
	 */
	public static void modScholarship() {
		ScholarshipDAO dao = new ScholarshipDAO();
		ScholarshipDTO dto = new ScholarshipDTO();
		
		ArrayList<ScholarshipDTO> list = dao.ScholarshipList(null);
		TopStudent.printPrizeInfoList(list); //리스트를 출력하는 메소드
		
		System.out.println();
		System.out.print("\t█ 수정하실 학생번호를 입력하세요 : ");
		String seq = scanner.nextLine();
		
		dto = dao.get(seq);
		TopStudent.printPrizeInfo(dto); //정보를 출력하는 메소드
		
		System.out.println();
		System.out.println("\t\t※ 수정하지 않으실 항목은 빈 값으로 엔터를 입력하세요.");
		
		System.out.print("\t수정할 제목을 입력하세요 : ");
		String name = scanner.nextLine();
		
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t수정할 해택상품을 입력하세요 : ");
		String prize = scanner.nextLine();
		
		if (prize.equals("")) {
			prize = dto.getPrize();
		}
		
		System.out.print("\t수정할 해택사유을 입력하세요 : ");
		String desc = scanner.nextLine();
		
		if (desc.equals("")) {
			desc = dto.getDesc();
		}
		
		
		ScholarshipDTO dto2 = new ScholarshipDTO();
		
		dto2.setSeqScholarship(dto.getSeqScholarship());
		dto2.setName(name);
		dto2.setPrize(prize);
		dto2.setDesc(desc);
		
		System.out.println();
		System.out.println("\t\t※ 정보를 확인하세요.\n");
		
		System.out.printf("\t=====%s번 교육생=====",dto.getSeqScholarship());
		System.out.println("\t<수정전>");
		System.out.printf(""
				+ "\t제목     : %s\n"
				+ "\t혜택상품 : %s\n"
				+ "\t혜택사유 : %s\n",
				dto.getName(),
				dto.getPrize(),
				dto.getDesc()
		);
		System.out.println();
		System.out.println("\t\t\t▼");
		System.out.println("\t\t\t\t<수정후>");
		System.out.printf(""
				+ "\t제목     : %s\n"
				+ "\t혜택상품 : %s\n"
				+ "\t혜택사유 : %s\n",
				dto2.getName(),
				dto2.getPrize(),
				dto2.getDesc()
		);
		System.out.println();
		System.out.print("\t█ 수정하시겠습니까? (y/n) : ");
		String txt = scanner.nextLine();
		if (!txt.toUpperCase().equals("Y")) {
			System.out.println("\t취소되었습니다. 이전 메뉴로 돌아갑니다.");
			return;
		}
		
		String sql = "update tblScholarship set name=?,prize=?,descrip=? where seqScholarship=?";	
		int result=0;
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getPrize());
			pstat.setString(3, dto2.getDesc());
			pstat.setString(4, dto2.getSeqScholarship());
			
			result = pstat.executeUpdate();
			pstat.close();
			
		} catch (Exception e) {
			System.out.println("ScholarshipDAO.modScholarship()");
			e.printStackTrace();
		}
		
		System.out.println();
		if (result > 0) {
			System.out.println("\t\t※ 정보 수정이 완료되었습니다.");
		} else {
			System.out.println("\t\t※ 정보 수정에 실패했습니다.");
		} 
		System.out.println();
		
		return;
		
	}

	/**
	 * 해택을 삭제하기 위한 메소드
	 * @param 혜택번호
	 * @return 결과값
	 */
	public int removeScholarship(String seq) {

		try {

			String sql = "alter sequence seqScholarship increment by -1";
			pstat.executeUpdate(sql);
			sql = "select seqScholarship.nextVal from dual";
			rs = pstat.executeQuery(sql);
			sql = "alter sequence seqScholarship increment by 1";
			pstat.executeUpdate(sql);
			
			sql = "delete from tblScholarship where seqScholarship = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ScholarshipDAO.removeScholarship()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 해택번호에 해당하는 정보를 리스트로 반환하는 메소드
	 * @param 혜택번호
	 * @return 혜택 정보 리스트
	 */
	public static ArrayList<ScholarshipDTO> ScholarshipList(String word) {
		try {
			
			String where = "";
			if (word != null) {
				where = String.format("where seqScholarship = '%%%s%%'", word);
			}
			
			String sql = String.format("select * from tblScholarship %s order by seqScholarship desc", where);
			rs = stat.executeQuery(sql);
			
			
			ArrayList<ScholarshipDTO> list = new ArrayList<ScholarshipDTO>();
			
				
			while (rs.next()) {
				ScholarshipDTO dto = new ScholarshipDTO();
				
				dto.setSeqScholarship(rs.getString("seqScholarship"));
				dto.setName(rs.getString("name"));
				dto.setPrize(rs.getString("prize"));
				dto.setDesc(rs.getString("descrip"));
				
				list.add(dto);
			}
			
			
			return list;
				

		} catch (Exception e) {
			System.out.println("ScholarshipDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 혜택 번호에 해당하는 혜택 정보를 반환하는 메소드
	 * @param 혜택 번호
	 * @return 혜택 정보
	 */
	public ScholarshipDTO get(String seq) {
		
		try {
			
			String sql = "select * from tblScholarship where seqScholarship = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			return setDTO(rs);

		} catch (Exception e) {
			System.out.println("ScholarshipDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생 정보 1개를 DTO클래스를 이용해 만들어진 객체에 저장하기 위한 메소드
	 * @param 교육생 정보(ResultSet)
	 * @return 교육생 정보(DTO)
	 */
	public ScholarshipDTO setDTO(ResultSet rs) {
		try {
			if (rs.next()) {
				
				ScholarshipDTO dto = new ScholarshipDTO();
				
				dto.setSeqScholarship(rs.getString("seqScholarship"));
				dto.setName(rs.getString("name"));
				dto.setPrize(rs.getString("prize"));
				dto.setDesc(rs.getString("descrip"));
				return dto;
			}
		} catch (Exception e) {
			System.out.println("ScholarshipDAO.setDTO()");
			e.printStackTrace();
		}
		return null;
	}
	

}
