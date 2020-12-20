package com.project.dto;

/**
 * 개설과목을 관리하는 DTO
 * @author 박지현
 *
 */
public class OpenSubjectDTO {
	
	private String seqOpenSubject;
	private String seqAvailableSubject;
	private String seqOpenCourse;
	private String startDate;
	private String endDate;
	
	public String getSeqOpenSubject() {
		return seqOpenSubject;
	}
	
	public void setSeqOpenSubject(String seqOpenSubject) {
		this.seqOpenSubject = seqOpenSubject;
	}
	
	public String getSeqAvailableSubject() {
		return seqAvailableSubject;
	}
	
	public void setSeqAvailableSubject(String seqAvailableSubject) {
		this.seqAvailableSubject = seqAvailableSubject;
	}
	
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
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
	
	
}
