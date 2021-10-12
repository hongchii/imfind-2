package com.spring.imfind.el.service;

import java.util.List;
import java.util.Map;

import com.spring.imfind.el.domain.NoticeAttachVO;
import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.paging.Criteria;

public interface NoticeService {
	
	public List<Map<String, Object>> getNoticeList(Criteria cri) throws Exception;
	
	public void noticeInsert(NoticeVO vo) throws Exception;
	
	public NoticeVO getNoticeInfo(int noticeBno) throws Exception;
	
	public int noticeModify(NoticeVO vo) throws Exception;
	
	public int noticeDelete(NoticeVO vo) throws Exception;
	
	public int getNoticeCount() throws Exception;
	
	public int noticeArrDelete(int noticeBno) throws Exception;
	
	public List<NoticeAttachVO> getAttachList(int bno) throws Exception;
}
