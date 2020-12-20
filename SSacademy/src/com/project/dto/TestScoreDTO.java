package com.project.dto;


/**
 * 시험성적정보를 저장하는 DTO
 * @author 김주혁
 *
 */
public class TestScoreDTO {

	private String seqTestScore; //시험성적번호
	private String seqTestPercent; //시험배점번호
	private String writtenScore; //필기점수
	private String practicalScore; //실기점수
	private String attendanceScore; //출결점수
	
	public String getSeqTestScore() {
		return seqTestScore;
	}
	public void setSeqTestScore(String seqTestScore) {
		this.seqTestScore = seqTestScore;
	}
	public String getSeqTestPercent() {
		return seqTestPercent;
	}
	public void setSeqTestPercent(String seqTestPercent) {
		this.seqTestPercent = seqTestPercent;
	}
	public String getWrittenScore() {
		return writtenScore;
	}
	public void setWrittenScore(String writtenScore) {
		this.writtenScore = writtenScore;
	}
	public String getPracticalScore() {
		return practicalScore;
	}
	public void setPracticalScore(String practicalScore) {
		this.practicalScore = practicalScore;
	}
	public String getAttendanceScore() {
		return attendanceScore;
	}
	public void setAttendanceScore(String attendanceScore) {
		this.attendanceScore = attendanceScore;
	}
	
	
	
}
