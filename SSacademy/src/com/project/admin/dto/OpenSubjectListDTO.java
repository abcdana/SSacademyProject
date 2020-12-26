package com.project.admin.dto;

/**
 * @author jenny
 *
 */
public class OpenSubjectListDTO {
	
	private int rownum;	//행번호
	private String seqOpenCourse;	//과정번호
	private String seqOpenSubject;	//과목번호
	private String subjectName;	//과목명
	private String openCourseName;	//과정명
	private String startDate;	//과목시작일
	private String endDate;		//과목종료일
	private String teacherName;	//교사이름
	private String bookName;	//교재명
	private String state;	//과목상태
	private String availableSubject;	//강의가능과목
	
	
	
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	
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
