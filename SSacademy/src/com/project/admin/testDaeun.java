package com.project.admin;

import com.project.dto.BasicCourseInfoDTO;

public class testDaeun {

	public static void main(String[] args) {
		
		
		System.out.println();
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\t  [번호]\t\t[강의실명]\t\t\t[수용인원]");
		System.out.println("\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
		
	}

	/**
	 * 기존 과정정보를 수정하는 메서드이다.
	 */
	private void updateCourseInfo() {
		
		view.updateCourseHeader();
		
		courseList(); //전체과정
		
		System.out.print("\n\t█ 과정번호 : ");
		String seqBasicCourseInfo = scan.nextLine();
		
		BasicCourseInfoDTO dto = bcidao.get(seqBasicCourseInfo);
		
		System.out.println();
		System.out.println("\t* 과정번호 : " + dto.getSeqBasicCourseInfo());
		System.out.println("\t* 과정이름 : " + dto.getName());
		System.out.println("\t* 과정기간 : " + dto.getPeriod());
		System.out.println("\t* 과정소개 : " + dto.getInfo());
		System.out.println("\n");
		
		System.out.println("\t\t  수정을 원치 않는 항목은 엔터를 입력하세요.\n");
		
		System.out.print("\t█ 수정할 과정이름 : ");
		String name = scan.nextLine();
		if (name.equals("")) {
			name = dto.getName();
		}
		
		System.out.print("\t█ 수정할 과정기간 : ");
		String period = scan.nextLine();
		if (period.equals("")) {
			period = dto.getPeriod();
		}
		
		System.out.print("\t█ 수정할 과정소개 : ");
		String info = scan.nextLine();
		if (info.equals("")) {
			info = dto.getInfo();
		}
		
		BasicCourseInfoDTO dto2 = new BasicCourseInfoDTO();
		
		dto2.setSeqBasicCourseInfo(seqBasicCourseInfo);
		dto2.setName(name);
		dto2.setPeriod(period);
		dto2.setInfo(info);
		
		int result = bcidao.updateCourse(dto2);
		
		view.updateResult(result);
		
	}//updateCourseInfo()
	
	
	
	
}
