package com.spring.imfind.el.domain;

import lombok.Data;

@Data
public class NoticeVO {

	private int noticeBno; // 글번호
	private String noticeTitle; // 글제목
	private String id; // 글작성자
	private String noticeDate; // 글작성일
	private int readcount; // 조회수

}
