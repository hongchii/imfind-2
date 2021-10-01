package com.spring.mapper;

import java.util.List;
import java.util.Map;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.paging.Criteria;

public interface NoticeMapper {

	public List<Map<String, Object>> getNoticeList(Criteria cri) throws Exception;
	
	public int noticeInsert(NoticeVO vo) throws Exception;
	
	public NoticeVO getNoticeInfo(int noticeBno) throws Exception;
	
	public int noticeModify(NoticeVO vo) throws Exception;
	
	public int noticeDelete(NoticeVO vo) throws Exception;
	
	public int noticeReadCount(int noticeBno) throws Exception;
}
