package com.spring.imfind.el.domain;

import java.util.List;

public class FreeVO {

	private int freeBno; // 글번호
	private String freeTitle; // 글제목
	private String freeContent; // 글내용
	private String id; // 글작성자
	private String freeDate; // 글작성일
	private int readcount; // 조회수
	private String delYN; // 삭제여부
	
	private List<AttachVO> attachList;
	
	public int getFreeBno() {
		return freeBno;
	}

	public void setFreeBno(int freeBno) {
		this.freeBno = freeBno;
	}

	public String getFreeTitle() {
		return freeTitle;
	}

	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}

	public String getFreeContent() {
		return freeContent;
	}

	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreeDate() {
		return freeDate;
	}

	public void setFreeDate(String freeDate) {
		this.freeDate = freeDate;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getDelYN() {
		return delYN;
	}

	public void setDelYN(String delYN) {
		this.delYN = delYN;
	}
	
	public List<AttachVO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}


	@Override
	public String toString() {
		return "FreeVO [freeBno=" + freeBno + ", freeTitle=" + freeTitle + ", freeContent=" + freeContent + ", id=" + id
				+ ", freeDate=" + freeDate + ", readcount=" + readcount + ", delYN=" + delYN + "]";
	}

}
