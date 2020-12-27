package com.project.dto;


/**
 * 
 * 성적,과목관련 정보를 담는 DTO
 * @author 조성진
 *
 */
public class VwSubjectScoreInquiryDTO {

	private String stuSeq;
	private String studentName;
	private String courseName;
	private String courseStart;
	private String courseEnd;
	private String seqOpenSubject;
	private String subjectName;
	private String subjectStart;
	private String subjectEnd;
	private String teacherName;
	private String writtenScore;
	private String practicalScore;
	private String AttendanceScore;
	private String studentState;
	private String roomName;
	
	
	public String getStuSeq() {
		return stuSeq;
	}
	public void setStuSeq(String stuSeq) {
		this.stuSeq = stuSeq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(String courseStart) {
		this.courseStart = courseStart;
	}
	public String getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(String courseEnd) {
		this.courseEnd = courseEnd;
	}
	public String getSeqOpenSubject() {
		return seqOpenSubject;
	}
	public void setSeqOpenSubject(String seqOpenSubject) {
		this.seqOpenSubject = seqOpenSubject;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectEnd() {
		return subjectEnd;
	}
	public void setSubjectEnd(String subjectEnd) {
		this.subjectEnd = subjectEnd;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getWrittenScore() {
		return writtenScore;
	}
	public void setWrittenScore(String writtenScore) {
		this.writtenScore = writtenScore;
	}
	public String getPracticalScore() {
		return practicalScore;
	}
	public void setPracticalScore(String practicalScore) {
		this.practicalScore = practicalScore;
	}
	public String getAttendanceScore() {
		return AttendanceScore;
	}
	public void setAttendanceScore(String attendanceScore) {
		AttendanceScore = attendanceScore;
	}
	public String getStudentState() {
		return studentState;
	}
	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSubjectStart() {
		return subjectStart;
	}
	public void setSubjectStart(String subjectStart) {
		this.subjectStart = subjectStart;
	}
	
	
}
