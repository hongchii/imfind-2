package com.spring.imfind.el.service;

import java.util.List;

import com.spring.imfind.el.domain.NoticeVO;

public interface NoticeService {
	
	public List<NoticeVO> getNoticeList() throws Exception;
}
