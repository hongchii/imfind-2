package com.spring.imfind.el.domain;

public class FreeReplyVO {

	private int rno; // 댓글번호
	private int bno; // 글번호
	private String reply_f; // 댓글내용
	private String replyer_f; // 댓글 작성자
	private String reply_f_date; // 댓글 작성일

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getReply_f() {
		return reply_f;
	}

	public void setReply_f(String reply_f) {
		this.reply_f = reply_f;
	}

	public String getReplyer_f() {
		return replyer_f;
	}

	public void setReplyer_f(String replyer_f) {
		this.replyer_f = replyer_f;
	}

	public String getReply_f_date() {
		return reply_f_date;
	}

	public void setReply_f_date(String reply_f_date) {
		this.reply_f_date = reply_f_date;
	}

	@Override
	public String toString() {
		return "FreeReplyVO [rno=" + rno + ", bno=" + bno + ", reply_f=" + reply_f + ", replyer_f=" + replyer_f
				+ ", reply_f_date=" + reply_f_date + "]";
	}

}
