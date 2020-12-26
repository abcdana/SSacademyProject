package com.project.admin.dto;

/**
 * @author jenny
 *
 */
public class OpenCourseStudentDTO {

	private int num;
	private String seqOpenCourse;
	private String seqStudent;
	private String name;
	private String ssn;
	private String tel;
	private String registDate;
	private String state;
	
	
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	
	
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}
	
	
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


	public String getRegistDate() {
		return registDate;
	}


	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}
		
	
}
