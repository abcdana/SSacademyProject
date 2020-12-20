package com.project.dto;
/**
 * 취업 정보 현황을 저장하는 DTO
 * @author 김다은
 *
 */
public class EmpStatusDTO {
	
	private String seqEmpStatus;	//취업현황번호
	private String seqCompanyInfo;	//연계기업번호
	private String seqGetJobInfo;	//취업번호
	private String seqRegCourse;	//수강번호
	

	public String getSeqEmpStatus() {
		return seqEmpStatus;
	}
	public void setSeqEmpStatus(String seqEmpStatus) {
		this.seqEmpStatus = seqEmpStatus;
	}
	public String getSeqCompanyInfo() {
		return seqCompanyInfo;
	}
	public void setSeqCompanyInfo(String seqCompanyInfo) {
		this.seqCompanyInfo = seqCompanyInfo;
	}
	public String getSeqGetJobInfo() {
		return seqGetJobInfo;
	}
	public void setSeqGetJobInfo(String seqGetJobInfo) {
		this.seqGetJobInfo = seqGetJobInfo;
	}
	public String getSeqRegCourse() {
		return seqRegCourse;
	}
	public void setSeqRegCourse(String seqRegCourse) {
		this.seqRegCourse = seqRegCourse;
	}
	
}
