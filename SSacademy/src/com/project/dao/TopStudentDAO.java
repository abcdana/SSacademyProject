package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.dto.TopStudentDTO;
import com.project.ssacademy.DBUtil;
/**
 * 우수훈련생 정보 관련 모든 프로시저를 관리하는 DAO이다.
 * @author 김다은
 *
 */
public class TopStudentDAO {

	private static Connection conn;
	private static Statement stat;
	private static PreparedStatement pstat;
	private static CallableStatement cstat;
	private static ResultSet rs;
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public TopStudentDAO() {

		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("primaryTopStudentDAO.enTopStudentDAO()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 우수훈련생 정보
	 * @return 우수훈련생 정보 리스트
	 */
	public ArrayList<TopStudentDTO> getTopStudent() {
		try {
			String sql = "select distinct ss.seqScholarship,ss.name ssName,prize,descrip,seqTopStudent,st.id stId,st.name stName,r.seqRegCourse seqRegCourse from tblTopStudent tos" + 
					" inner join tblScholarship ss on tos.seqScholarship=ss.seqScholarship" + 
					" inner join tblTestScore ts on tos.seqTestScore=ts.seqTestScore" + 
					" inner join tblTestPercent tp on tp.seqTestPercent=ts.seqTestPercent" + 
					" inner join tblRegCourse r on r.seqRegCourse=tp.seqRegCourse" + 
					" inner join tblStudent st on st.seqStudent=r.seqStudent" +
					" order by tos.seqTopStudent";
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<TopStudentDTO> list = new ArrayList<TopStudentDTO>();
				
			while (rs.next()) {
				
				TopStudentDTO dto = new TopStudentDTO();
				
				dto.setSeqTopStudent(rs.getString("seqTopStudent"));
				dto.setStId(rs.getString("stId"));
				dto.setStName(rs.getString("stName"));
				dto.setSeqRegCourse(rs.getString("seqRegCourse"));
				dto.setSsName(rs.getString("ssName"));
				dto.setPrize(rs.getString("prize"));
				dto.setDescrip(rs.getString("descrip"));
				
				list.add(dto);
			}
			
			return list;
				
		} catch (Exception e) {
			System.out.println("primaryStudentDAO.engetStudent()");
			e.printStackTrace();
		}
		return null;
	}
}
