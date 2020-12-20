package com.project.dto;


/**
 * 교사배정정보를 저장하는 DTO
 * @author 김주혁
 *
 */
public class AssignTeacherDTO {

	private String seqAssignTeacher; //교사배정번호
	private String seqOpenCourse; //개설과정번호
	private String seqTeacher; //교사번호
	
	public String getSeqAssignTeacher() {
		return seqAssignTeacher;
	}
	public void setSeqAssignTeacher(String seqAssignTeacher) {
		this.seqAssignTeacher = seqAssignTeacher;
	}
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}
	public String getSeqTeacher() {
		return seqTeacher;
	}
	public void setSeqTeacher(String seqTeacher) {
		this.seqTeacher = seqTeacher;
	}
	
	
	
}
