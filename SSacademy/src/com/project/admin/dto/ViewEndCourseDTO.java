package com.project.admin.dto;

/**
 * 종료된 과정 정보를 담는 DTO
 * @author 김주혁
 *
 */
public class ViewEndCourseDTO {

	private String seqOpenCourse;
	private String courseName;
	private String courseStartDate;
	private String courseEndDate;
	private String studentCount;
	private String seqTeacher;
	private String teacherName;
	private String room;
	
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
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
	public String getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
	}
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
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	
	
}
