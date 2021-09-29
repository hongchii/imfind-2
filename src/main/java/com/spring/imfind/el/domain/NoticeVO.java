package com.spring.imfind.el.domain;

public class NoticeVO {

	private int noticeBno; // 글번호
	private String noticeTitle; // 글제목
	private String noticeContent; // 글내용
	private String id; // 글작성자
	private String noticeDate; // 글작성일
	private int readcount; // 조회수

	public int getNoticeBno() {
		return noticeBno;
	}

	public void setNoticeBno(int noticeBno) {
		this.noticeBno = noticeBno;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "NoticeVO [noticeBno=" + noticeBno + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", id=" + id + ", noticeDate=" + noticeDate + ", readcount=" + readcount + "]";
	}

}
