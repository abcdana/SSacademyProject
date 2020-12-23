package com.project.admin.dto;

/**
 * @author jenny
 *
 */
public class OpenSubjectListDTO {
	
	private String seqOpenCourse;
	private String seqOpenSubject;
	private String subjectName;
	private String openCourseName;
	private String startDate;
	private String endDate;
	private String teacherName;
	private String bookName;
	private String state;
	private String availableSubject;
	
	
	public String getSubjectName() {
		return subjectName;
	}
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	
	public void setEndDate(String sndDate) {
		this.endDate = sndDate;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	public String getSeqOpenSubject() {
		return seqOpenSubject;
	}

	
	public void setSeqOpenSubject(String seqOpenSubject) {
		this.seqOpenSubject = seqOpenSubject;
	}

	
	public String getOpenCourseName() {
		return openCourseName;
	}

	
	public void setOpenCourseName(String openCourseName) {
		this.openCourseName = openCourseName;
	}

	/**
	 * @return the seqOpenCourse
	 */
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}

	/**
	 * @param seqOpenCourse the seqOpenCourse to set
	 */
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}

	/**
	 * @return the availableSubject
	 */
	public String getAvailableSubject() {
		return availableSubject;
	}

	/**
	 * @param availableSubject the availableSubject to set
	 */
	public void setAvailableSubject(String availableSubject) {
		this.availableSubject = availableSubject;
	}
	
	
	
}
