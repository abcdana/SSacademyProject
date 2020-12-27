package com.project.student.dto;


/**
 * 학생모드 성적 조회 시 해당 학생의 모든 데이터를 담은 DTO클래스이다.
 * @author 조성진
 *
 */
public class ViewStudentEndCourseDTO {

	private String seqStudent; //교육생번호
	private String seqRegCourse; //수강번호
	private String courseName; //과정명
	private String courseStartDate; //과정시작일
	private String courseEndDate; //과정종료일
	private String room; //강의실명
	
	public String getSeqStudent() {
		return seqStudent;
	}
	public void setSeqStudent(String seqStudent) {
		this.seqStudent = seqStudent;
	}
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public String getCourseEndDate() {
		return courseEndDate;
	}
	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	
	
}
