package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.domain.NoticeVO;

public interface NoticeMapper {

	public List<NoticeVO> getNoticeList() throws Exception;
	
	public int noticeInsert(NoticeVO vo) throws Exception;
	
	public NoticeVO getNoticeInfo(int noticeBno) throws Exception;
	
	public int noticeModify(NoticeVO vo) throws Exception;
	
	public int noticeDelete(NoticeVO vo) throws Exception;
	
	public int noticeReadCount(int noticeBno) throws Exception;
}
