package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	 * 전체 강의실 정보를 ArrayList로 반환하는 메서드이다.
	 * 한 강의실 정보에는 강의실 번호, 강의실 이름, 수용 인원이 포함되어있다.
	 * @return 강의실 정보 ArrayList
	 */
	public ArrayList<RoomDTO> roomList() {
		
		
		try {
			
			String sql = "select * from tblRoom order by seqRoom";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<RoomDTO> result = new ArrayList<RoomDTO>();
			
			while (rs.next()) {
				
				RoomDTO rdto = new RoomDTO();
				
				rdto.setSeqRoom(rs.getString("seqRoom"));
				rdto.setName(rs.getString("name"));
				rdto.setPeople(rs.getString("people"));
				
				result.add(rdto);
			}
			
			rs.close();
			return result;
			
		} catch (Exception e) {
			System.out.println("primaryRoomDAO.enroomList()");
			e.printStackTrace();
		}
		
		return null;
		
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
	 * 
	 * @param seqRoom 강의실 번호
	 * @return dto 객체
	 */
	public RoomDTO get(String seqRoom) {
		
		try {
			
			String sql = "select * from tblRoom where seqRoom = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seqRoom);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				RoomDTO dto = new RoomDTO();
				
				dto.setSeqRoom(rs.getString("seqRoom"));
				dto.setName(rs.getString("name"));
				dto.setPeople(rs.getString("people"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryRoomDAO.enget()");
			e.printStackTrace();
		} 
		return null;
	}
	
	
	//TODO update문 프로시저 사용해서 메서드 짜보기
//	/**
//	 * 수정할 강의실 번호와 수정할 이름 또는 인원을 받아와 기존의 강의실 정보를 수정하는 메서드이다.
//	 * 성공 여부는 성공 시 1, 실패 시 0으로 나타낸다.
//	 * @param seqRoom 강의실 번호
//	 * @param name	  강의실 이름
//	 * @param people  강의실 수용인원
//	 * @return 성공 여부
//	 */
//	public int updateRoom(String seqRoom, String name, String people) {
//		
//		String roomName = "";
//		String roomPeople = "";
//		
//		try {
//			
//			String sql = "select seqRoom, name, people from tblRoom where seqRoom = " + seqRoom;
//			rs = stat.executeQuery(sql);
//			
//			if (rs.next()) {
//				roomName = rs.getString("name");
//				roomPeople = rs.getString("people");
//			}
//			
//			if (!roomName.equals("")) {
//				roomName = rs.getString("name");
//			}
//			
//			if (!roomPeople.equals("")) {
//				roomPeople = rs.getString("people");
//			}
//			
//			sql = "{ call procUpdateRoom(?, ?, ?) }";
//			cstat = conn.prepareCall(sql);
//			cstat.setString(1, seqRoom);
//			cstat.setString(2, roomName);
//			cstat.setString(3, roomPeople);
//			
//			return cstat.executeUpdate();
//			
//		} catch (Exception e) {
//			System.out.println("primaryRoomDAO.enupdateRoom()");
//			e.printStackTrace();
//		}
//		return 0;
//	}
	
	
	/**
	 * 수정할 강의실 번호와 수정할 이름 또는 인원을 받아와 기존의 강의실 정보를 수정하는 메서드이다.
	 * 성공 여부는 성공 시 1, 실패 시 0으로 나타낸다.
	 * @param dto2 강의실 데이터 정보
	 * @return 성공 여부
	 */
	public int updateRoom(RoomDTO dto2) {
		
		try {
			
			String sql = "update tblRoom set name = ?, people = ? "
							+ "where seqRoom = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getPeople());
			pstat.setString(3, dto2.getSeqRoom());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryRoomDAO.enupdateRoom()");
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
