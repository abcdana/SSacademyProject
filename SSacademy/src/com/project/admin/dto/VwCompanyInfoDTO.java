package com.project.admin.dto;

public class VwCompanyInfoDTO {

	/**
	 * 채용공고 정보담는 DTO
	 * @author 조혜승
	 */
	private String seqCompanyInfo;
	private String name;
	private String startDate;
	private String endDate;
	private String comField;
	private String salary;
	private String employmentType;
	private String comSize;
	private String address;
	private String state;
	public String getSeqCompanyInfo() {
		return seqCompanyInfo;
	}
	public void setSeqCompanyInfo(String seqCompanyInfo) {
		this.seqCompanyInfo = seqCompanyInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getComField() {
		return comField;
	}
	public void setComField(String comField) {
		this.comField = comField;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public String getComSize() {
		return comSize;
	}
	public void setComSize(String comSize) {
		this.comSize = comSize;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
