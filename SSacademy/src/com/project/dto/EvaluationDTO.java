package com.project.dto;

//평가정보
public class EvaluationDTO {
	
	String seqEvaluation; //평가번호
	String seqRegCourse; //수강번호
	String processScore; //강의계획서 이행점수
	String understandScore; //교사의 강의전달 및 이해점수
	String communicationScore; //교사의 소통점수
	String usefulScore; //강의의 유익성 점수
	String satisfactionScore; //전반적인 만족도
	String facilityScore; //시설 만족도
	String managementScore; //사후 취업관리만족도
	
	public String getSeqEvaluation() {
		return seqEvaluation;
	}
	public void setSeqEvaluation(String seqEvaluation) {
		this.seqEvaluation = seqEvaluation;
	}
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
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
