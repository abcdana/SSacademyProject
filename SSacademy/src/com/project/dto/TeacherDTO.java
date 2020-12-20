package com.project.dto;


/**
 * 교사정보를 저장하는 DTO
 * @author 김주혁
 *
 */
public class TeacherDTO {

	private String seqTeacher; //교사번호
	private String name; //이름
	private String id; //아이디
	private String ssn; //주민등록번호
	private String tel; //전화번호
	
	public String getSeqTeacher() {
		return seqTeacher;
	}
	public void setSeqTeacher(String seqTeacher) {
		this.seqTeacher = seqTeacher;
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
