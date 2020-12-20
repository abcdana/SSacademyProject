package com.project.dto;

/**
 * 기초과목과 교사를 관리하는 DTO
 * @author 박지현
 *
 */
public class AvailableSubjectDTO {

	private String seqTeacher;
	private String seqBasicSubject;
	
	public String getSeqTeacher() {
		return seqTeacher;
	}
	
	public void setSeqTeacher(String seqTeacher) {
		this.seqTeacher = seqTeacher;
	}

	public String getSeqBasicSubject() {
		return seqBasicSubject;
	}

	public void setSeqBasicSubject(String seqBasicSubject) {
		this.seqBasicSubject = seqBasicSubject;
	}
	
	
}
