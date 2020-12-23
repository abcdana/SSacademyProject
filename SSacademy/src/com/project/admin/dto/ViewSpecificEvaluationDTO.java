package com.project.admin.dto;

/**
 * 특정 과정의 평가 정보를 담는 DTO
 * @author 김주혁
 *
 */
public class ViewSpecificEvaluationDTO {

	private String seqOpenCourse;
	private String seqStudent;
	private String studentName;
	private String processScore;
	private String understandScore;
	private String communicationScore;
	private String usefulScore;
	private String satisfactionScore;
	private String facilityScore;
	private String managementScore;
	
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}
	public String getSeqStudent() {
		return seqStudent;
	}
	public void setSeqStudent(String seqStudent) {
		this.seqStudent = seqStudent;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getProcessScore() {
		return processScore;
	}
	public void setProcessScore(String processScore) {
		this.processScore = processScore;
	}
	public String getUnderstandScore() {
		return understandScore;
	}
	public void setUnderstandScore(String understandScore) {
		this.understandScore = understandScore;
	}
	public String getCommunicationScore() {
		return communicationScore;
	}
	public void setCommunicationScore(String communicationScore) {
		this.communicationScore = communicationScore;
	}
	public String getUsefulScore() {
		return usefulScore;
	}
	public void setUsefulScore(String usefulScore) {
		this.usefulScore = usefulScore;
	}
	public String getSatisfactionScore() {
		return satisfactionScore;
	}
	public void setSatisfactionScore(String satisfactionScore) {
		this.satisfactionScore = satisfactionScore;
	}
	public String getFacilityScore() {
		return facilityScore;
	}
	public void setFacilityScore(String facilityScore) {
		this.facilityScore = facilityScore;
	}
	public String getManagementScore() {
		return managementScore;
	}
	public void setManagementScore(String managementScore) {
		this.managementScore = managementScore;
	}
	
	
	
}
