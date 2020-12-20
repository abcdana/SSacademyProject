package com.project.dto;

/**
 * 교재정보를 관리하는 DTO
 * @author 박지현
 *
 */
public class BookDTO {
	
	private String seqBook;
	private String name;
	private String publisher;
	private String writer;
	
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
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
