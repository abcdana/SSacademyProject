package com.project.dto;
/**
 * 우수훈련생 정보(이력)를 저장하는 DTO
 * @author 김다은
 *
 */
public class TopStudentDTO {
	
	private String seqTopStudent;	//우수훈련생번호
	private String seqTestScore;	//시험성적번호
	private String seqScholarship;	//혜택번호
	private String ssName;	//혜택명
	private String prize;	//혜택상품
	private String descrip;	//혜택내용
	private String stId;	//학생아이디
	private String stName;	//학생이름
	private String seqRegCourse;	//수강번호
	
	public String getSeqTopStudent() {
		return seqTopStudent;
	}
	public void setSeqTopStudent(String seqTopStudent) {
		this.seqTopStudent = seqTopStudent;
	}
	public String getSeqTestScore() {
		return seqTestScore;
	}
	public void setSeqTestScore(String seqTestScore) {
		this.seqTestScore = seqTestScore;
	}
	public String getSeqScholarship() {
		return seqScholarship;
	}
	public void setSeqScholarship(String seqScholarship) {
		this.seqScholarship = seqScholarship;
	}
	public String getSsName() {
		return ssName;
	}
	public void setSsName(String ssName) {
		this.ssName = ssName;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStId() {
		return stId;
	}
	public void setStId(String stId) {
		this.stId = stId;
	}


}
