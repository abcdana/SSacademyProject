package com.project.dto;
/**
 * 연계기업정보를 저장하는 DTO
 * @author 김다은
 *
 */
public class CompanyInfoDTO {
	
	private String seqCompanyInfo;	//연계기업번호
	private String name;			//연계기업명
	private String address;			//기업위치
	private String comField;		//분야
	private String comSize;			//기업단위
	private String employmentType;	//고용형태
	private String salary;			//급여
	private String startDate;		//채용시작일
	private String endDate;			//채용종료일
	
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComField() {
		return comField;
	}
	public void setComField(String comField) {
		this.comField = comField;
	}
	public String getComSize() {
		return comSize;
	}
	public void setComSize(String comSize) {
		this.comSize = comSize;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
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

	
	
}
