package com.project.dto;

//교육생
public class StudentDTO {

	private String seqStudent; //교육생번호
	private String name; //교육생이름
	private String id; //교육생아이디
	private String ssn; //교육생주민번호
	private String phone; //교육생전화번호
	private String email; //교육생이메일
	private String firstRegistrationDate; //교육생최초등록일
	private String employmentField; //교육생취업분야
	public int num;
	
	
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstRegistrationDate() {
		if (firstRegistrationDate!=null) {
			return firstRegistrationDate.substring(0,10);
		}else {
			return firstRegistrationDate;
		}
	}
	public void setFirstRegistrationDate(String firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
	}
	public String getEmploymentField() {
		return employmentField;
	}
	public void setEmploymentField(String employmentField) {
		this.employmentField = employmentField;
	}

	
}
