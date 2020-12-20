package com.project.dto;

//관리자
public class AdminDTO {
	
	String seqAdmin; //관리자번호
	String id; //관리자아이디
	String pw; //관리자비밀번호
	
	public String getSeqAdmin() {
		return seqAdmin;
	}
	public void setSeqAdmin(String seqAdmin) {
		this.seqAdmin = seqAdmin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
