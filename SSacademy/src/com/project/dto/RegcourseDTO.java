package com.project.dto;

/**
 * 수강정보를 저장하는 DTO
 * @author 김다은
 *
 */
public class RegcourseDTO {
	
	private String seqRegCourse;	//수강번호
	private String seqStudent;		//교육생번호
	private String seqOpenCourse;	//개설과정번호
	private String studentState;	//교육생 수강상태 (수료, 수강중, 중도탈락)
	private String failOrDoneDate;	//수료및 탈락일
	
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	public String getSeqStudent() {
		return seqStudent;
	}
	public void setSeqStudent(String seqStudent) {
		this.seqStudent = seqStudent;
	}
	public String getSeqOpenCourse() {
		return seqOpenCourse;
	}
	public void setSeqOpenCourse(String seqOpenCourse) {
		this.seqOpenCourse = seqOpenCourse;
	}
	public String getStudentState() {
		return studentState;
	}
	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}
	public String getFailOrDoneDate() {
		return failOrDoneDate;
	}
	public void setFailOrDoneDate(String failOrDoneDate) {
		this.failOrDoneDate = failOrDoneDate;
	}

}
