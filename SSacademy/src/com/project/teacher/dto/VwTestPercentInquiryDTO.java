package com.project.teacher.dto;


/**
 * 배점에 대한 과목조회시 교사가 강의중인 과목에대한 정보 데이터를 담는 DTO이다.
 * @author 조성진
 *
 */
public class VwTestPercentInquiryDTO {

	private String tpSeq;
	private String btSeq;
	private String subSeq;
	private String written;
	private String practical;
	private String attendance;
	private String writtenDate;
	private String practicaldate;
	
	
	public String getTpSeq() {
		return tpSeq;
	}
	public void setTpSeq(String tpSeq) {
		this.tpSeq = tpSeq;
	}
	public String getBtSeq() {
		return btSeq;
	}
	public void setBtSeq(String btSeq) {
		this.btSeq = btSeq;
	}
	public String getSubSeq() {
		return subSeq;
	}
	public void setSubSeq(String subSeq) {
		this.subSeq = subSeq;
	}
	public String getWritten() {
		return written;
	}
	public void setWritten(String written) {
		this.written = written;
	}
	public String getPractical() {
		return practical;
	}
	public void setPractical(String practical) {
		this.practical = practical;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(String writtenDate) {
		this.writtenDate = writtenDate;
	}
	public String getPracticaldate() {
		return practicaldate;
	}
	public void setPracticaldate(String practicaldate) {
		this.practicaldate = practicaldate;
	}
	
	
	
	
}
