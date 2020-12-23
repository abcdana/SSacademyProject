package com.project.admin.dto;

public class VwEmpStatusDTO {
	/**
	 * 연계기업취업정보를 담는 DTO
	 * VwAllEmpStatus  
	 * @author 조혜승
	 */
	private String seq; //seqEmpStatus
	private String name;
	private String id;
	private String companyName;
	private String duty;
	private String form;
	private String salary;
	private String getJobDate;
	private String location;
	private String course;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getGetJobDate() {
		return getJobDate;
	}
	public void setGetJobDate(String getJobDate) {
		this.getJobDate = getJobDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
