package com.project.dto;

/**
 * 기초과목을 관리하는 DTO
 * @author 박지현
 *
 */
public class BasicSubjectDTO {
	
	private String seqBasicSubject;
	private String seqBook;
	private String name;
	private String info;
	
	public String getSeqBasicSubject() {
		return seqBasicSubject;
	}
	
	public void setSeqBasicSubject(String seqBasicSubject) {
		this.seqBasicSubject = seqBasicSubject;
	}

	public String getSeqBook() {
		return seqBook;
	}
	
	public void setSeqBook(String seqBook) {
		this.seqBook = seqBook;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
