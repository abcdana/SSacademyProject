package com.project.admin.dto;
/**
 * 취업정보 담는 DTO
 * @author 조혜승
 *
 */

public class VwGetJobInfoDTO {
	private String gjseq; //취업정보번호
	private String rcseq; //수강정보번호
	private String name; //학생이름
	private String id;  //학생아이디
	private String companyName;	//회사이름
	private String duty;	//업무
	private String form;	//고용형태
	private String salary;	//연봉
	private String getJobDate;	//취업일
	private String location;	//회사주소
	private String course;		//수료과정

	public String getGjseq() {
		return gjseq;
	}
	public void setGjseq(String gjseq) {
		this.gjseq = gjseq;
	}
	public String getRcseq() {
		return rcseq;
	}
	public void setRcseq(String rcseq) {
		this.rcseq = rcseq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getGetJobDate() {
		return getJobDate;
	}
	public void setGetJobDate(String getJobDate) {
		this.getJobDate = getJobDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
