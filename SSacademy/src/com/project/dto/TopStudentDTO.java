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

}
