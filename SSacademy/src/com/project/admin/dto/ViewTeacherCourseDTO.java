package com.project.admin.dto;

/**
 * 특정 교사의 과정 정보를 담는 DTO
 * @author 김주혁
 *
 */
public class ViewTeacherCourseDTO {

	private String seqTeacher;
	private String teacherName;
	private String courseName;
	private String courseStartDate;
	private String courseEndDate;
	private String room;
	private String subjectName;
	private String subjectStartDate;
	private String subjectEndDate;
	private String lectureState;
	
	public String getSeqTeacher() {
		return seqTeacher;
	}
	public void setSeqTeacher(String seqTeacher) {
		this.seqTeacher = seqTeacher;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectStartDate() {
		return subjectStartDate;
	}
	public void setSubjectStartDate(String subjectStartDate) {
		this.subjectStartDate = subjectStartDate;
	}
	public String getSubjectEndDate() {
		return subjectEndDate;
	}
	public void setSubjectEndDate(String subjectEndDate) {
		this.subjectEndDate = subjectEndDate;
	}
	public String getLectureState() {
		return lectureState;
	}
	public void setLectureState(String lectureState) {
		this.lectureState = lectureState;
	}
	
	
	
}
