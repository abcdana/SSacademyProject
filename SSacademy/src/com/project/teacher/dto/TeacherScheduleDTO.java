package com.project.teacher.dto;

/**
 * 교사 강의스케줄조회 DTO
 * @author 박지현
 *
 */
public class TeacherScheduleDTO {
	
	private String seqTeacher;
	private String teahcerName;
	private String seqOpenCourse;
	private String courseName;
	private String startDate;
	private String endDate;
	private String memberCount;
	
	
	
	public String getSeqTeacher() {
		return seqTeacher;
	}
	
	
	public void setSeqTeacher(String seqTeacher) {
		this.seqTeacher = seqTeacher;
	}
	
	
	public String getTeahcerName() {
		return teahcerName;
	}
	
	
	public void setTeahcerName(String teahcerName) {
		this.teahcerName = teahcerName;
	}
	
	
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	
	
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}
	
	
	public String getCourseName() {
		return courseName;
	}
	
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	
	
	public String getMemberCount() {
		return memberCount;
	}
	
	
	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}
	
	
}
