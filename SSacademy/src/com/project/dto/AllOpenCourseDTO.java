package com.project.dto;
/**
 * 출결조회에서 보여줄 개설과정리스트의 정보를 담는 DTO이다.
 * 개설과정번호, 개설과정명, 과정시작일, 과정종료일, 교사명, 강의실명이 포함되어있다. 
 * @author 김다은
 *
 */
public class AllOpenCourseDTO {

	private String seqOpenCourse;
	private String name;
	private String startDate;
	private String endDate;
	private String teacherName;
	private String roomName;
	
	
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	
	
}
