package com.project.student.dto;

/**
 * @author 박지현
 *
 */
public class StudentListDTO {
		
	private String seqStudent;
	private String name;
	private String ssn;
	private String CourseName;
	private String startDate;
	private String endDate;
	private String room;
	private String firstRegistDate;
	private String id;
	private int rownum;	//교육생
	
	
	
	public String getFirstRegistDate() {
		return firstRegistDate;
	}


	
	public void setFirstRegistDate(String firstRegistDate) {
		this.firstRegistDate = firstRegistDate;
	}


	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getCourseName() {
		return CourseName;
	}


	public void setCourseName(String courseName) {
		CourseName = courseName;
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


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

}
