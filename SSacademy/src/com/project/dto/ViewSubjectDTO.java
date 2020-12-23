package com.project.dto;
/**
 * 과목 정보를 담는 DTO
 * 과목 번호, 과목명, 과목 소개, 교재명이 포함되어있다.
 * @author 김다은
 *
 */
public class ViewSubjectDTO {
	
	private String seqBasicSubject;
	private String name;
	private String info;
	private String book;
	
	
	public String getSeqBasicSubject() {
		return seqBasicSubject;
	}
	public void setSeqBasicSubject(String seqBasicSubject) {
		this.seqBasicSubject = seqBasicSubject;
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
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	

}
