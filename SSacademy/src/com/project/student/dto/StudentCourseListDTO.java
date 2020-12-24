package com.project.student.dto;
/**
 * 학생모드 출결 조회 시 해당 학생이 수강했던, 하고있는 과정의 정보 데이터를 담는 DTO이다.
 * @author 김다은
 *
 */
public class StudentCourseListDTO {
	
	private String seqRegCourse;	//수강번호
	private String seqOpenCourse;	//개설 과정번호
	private String name;			//개설 과정명
	private String startDate;		//과정 시작일
	private String endDate;			//과정 종료일
	private String roomName;		//강의실명
	
	
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	

}
