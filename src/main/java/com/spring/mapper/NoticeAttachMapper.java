package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.domain.NoticeAttachVO;

public interface NoticeAttachMapper {
	
	public void insert(NoticeAttachVO vo);
	
	public void delete(String uuid);
	
	public List<NoticeAttachVO> findByBno(int bno);
	
	public void deleteAll(int bno);
	
	public List<NoticeAttachVO> getOldFiles();
}
