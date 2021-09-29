package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.domain.NoticeVO;

public interface NoticeMapper {

	public List<NoticeVO> getNoticeList() throws Exception;
	
}
