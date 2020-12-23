package com.project.admin.dto;

/**
 * 수료한 학생 중 취업정보에 등록되지 않은 학생을 담는 DTO
 * @author 조혜승
 *
 */
public class CompleteStudentDTO {
	private String rcseq;
	private String sname;
	private String id;
	private String course;
	public String getRcseq() {
		return rcseq;
	}
	public void setRcseq(String rcseq) {
		this.rcseq = rcseq;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	

}
