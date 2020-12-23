package com.project.dto;
/**
 * 특정 기간동안의 출결데이터를 담는 DTO이다.
 * @author 김다은
 *
 */
public class PeriodAttendListDTO {
	
	private String attendDate;	//출결날짜
	private String inTime;		//입실시간
	private String outTime;		//퇴실시간
	private String attendState;	//근태상태
	
	
	public String getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(String attendDate) {
		this.attendDate = attendDate;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getAttendState() {
		return attendState;
	}
	public void setAttendState(String attendState) {
		this.attendState = attendState;
	}
	

}
