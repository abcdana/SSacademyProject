package com.project.dto;
/**
 * 출결정보를 저장하는 DTO
 * @author 김다은
 *
 */
public class AttendanceDTO {
	
	private String seqtblAttendance;//출결번호
	private String seqRegCourse;	//수강번호
	private String attendance;		//출결날짜
	private String inTime;			//입실시간
	private String outTime;			//퇴실시간
	private String attendState;		//근태현황
	
	
	public String getSeqtblAttendance() {
		return seqtblAttendance;
	}
	public void setSeqtblAttendance(String seqtblAttendance) {
		this.seqtblAttendance = seqtblAttendance;
	}
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
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
