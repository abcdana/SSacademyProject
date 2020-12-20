package com.project.dto;
/**
 * 혜택정보를 저장하는 DTO
 * @author 김다은
 *
 */
public class ScholarshipDTO {

	private String seqScholarship;	//혜택번호
	private String name;			//혜택명
	private String prize;			//혜택상품
	private String desc;			//혜택내용
	
	
	public String getSeqScholarship() {
		return seqScholarship;
	}
	public void setSeqScholarship(String seqScholarship) {
		this.seqScholarship = seqScholarship;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
