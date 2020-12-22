package com.project.admin.dto;

/**
 * @author jenny
 *
 */
public class OpenCourseListDTO {

	private String seqOpenCourse;
	private String name;
	private String startDate;
	private String endDate;
	private String room;
	private String memberCount;
	private String state;
	
	
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
	
	
	public String getRoom() {
		return room;
	}
	
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	
	public String getMemberCount() {
		return memberCount;
	}
	
	
	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}
	
	
	public String getState() {
		return state;
	}
	
	
	public void setState(String state) {
		this.state = state;
	}
	
	
}
