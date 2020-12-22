package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.dto.RoomDTO;
import com.project.ssacademy.DBUtil;
/**
 * 강의실 데이터베이스와 관련된 비즈니스업무 DAO
 * @author 조혜승, 김다은
 *
 */
public class RoomDAO {
	
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	CallableStatement cstat = null;
	ResultSet rs = null;
	
	public RoomDAO() {

		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 새로운 강의실을 등록하는 메서드이다.
	 * 강의실 이름과 수용인원을 담은 강의실 데이터 정보를 받아와 강의실을 등록한다.
	 * 등록 성공시 1, 실패시 0을 반환한다.
	 * @param rdto 강의실 데이터 정보
	 * @return 성공 여부
	 */
	public int addRoom(RoomDTO rdto) {
		
		try {
			
			String sql = "{ call procAddRoom(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, rdto.getName());		//강의실명
			cstat.setString(2, rdto.getPeople());	//수용인원
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryRoomDAO.enaddRoom()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	
	/**
	 * 기존 강의실정보를 삭제하는 메서드이다.
	 * 강의실 번호를 받아와 강의실 데이터를 삭제한다.
	 * 등록 성공시 1, 실패시 0을 반환한다.
	 * @param seqRoom 강의실 번호
	 * @return 성공 여부
	 */
	public int deleteRoom(String seqRoom) {

		try {
			
			String sql = "{ call procDeleteRoom(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seqRoom);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryRoomDAO.endeleteRoom()");
			e.printStackTrace();
		}
		
		return 0;
	}

}
