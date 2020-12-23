package com.project.dto;

/**
 * 출결조회시 사용할 교육생 정보를 담는 DTO
 * 교육생번호, 이름, ID, PW, 등록일, 수강상태를 포함하고있다.
 * @author 김다은
 *
 */
public class ViewStudentDTO {
	
	private String seqStudent;
	private String name;
	private String id;
	private String pw;
	private String regDate;
	private String studentState;
	
	
	public String getSeqStudent() {
		return seqStudent;
	}
	public void setSeqStudent(String seqStudent) {
		this.seqStudent = seqStudent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getStudentState() {
		return studentState;
	}
	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}
	

}
