package com.project.dto;


/**
 * 시험배점정보를 저장하는 DTO
 * @author 김주혁
 *
 */
public class TestPercentDTO {

	private String seqTestPercent; //시험배점번호
	private String seqRegCourse; //수강번호
	private String BasicTest; //기초시험정보번호
	private String writtenPercent; //필기배점
	private String practicalPercent; //실기배점
	private String attendancePercent; //출결배점
	private String writtenDate; //필기시험일
	private String practicalDate; //실기시험일
	
	
	public String getSeqTestPercent() {
		return seqTestPercent;
	}
	public void setSeqTestPercent(String seqTestPercent) {
		this.seqTestPercent = seqTestPercent;
	}
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	public String getBasicTest() {
		return BasicTest;
	}
	public void setBasicTest(String basicTest) {
		BasicTest = basicTest;
	}
	public String getWrittenPercent() {
		return writtenPercent;
	}
	public void setWrittenPercent(String writtenPercent) {
		this.writtenPercent = writtenPercent;
	}
	public String getPracticalPercent() {
		return practicalPercent;
	}
	public void setPracticalPercent(String practicalPercent) {
		this.practicalPercent = practicalPercent;
	}
	public String getAttendancePercent() {
		return attendancePercent;
	}
	public void setAttendancePercent(String attendancePercent) {
		this.attendancePercent = attendancePercent;
	}
	public String getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(String writtenDate) {
		this.writtenDate = writtenDate;
	}
	public String getPracticalDate() {
		return practicalDate;
	}
	public void setPracticalDate(String practicalDate) {
		this.practicalDate = practicalDate;
	}
	
	
	
	
}
