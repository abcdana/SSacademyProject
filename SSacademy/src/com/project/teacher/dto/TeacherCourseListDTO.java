package com.project.teacher.dto;

/**
 * 교사모드 출결 조회시 해당 교사가 진행중인 과정의 정보 데이터를 담는 DTO이다.
 * @author 김다은
 *
 */
public class TeacherCourseListDTO {

	private String seqOpenCourse;	//개설 과정 번호
	private String name;			//개설 과정명
	private String startDate;		//과정 시작날짜
	private String endDate;			//과정 종료날짜
	private String roomName;		//강의실명
	private String courseRegState;	//강의 진행 상태
	
	
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
	public String getCourseRegState() {
		return courseRegState;
	}
	public void setCourseRegState(String courseRegState) {
		this.courseRegState = courseRegState;
	}
	
	
	
	
}
