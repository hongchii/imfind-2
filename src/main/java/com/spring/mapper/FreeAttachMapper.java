package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.domain.AttachVO;

public interface FreeAttachMapper {
	
	public void insert(AttachVO vo);
	
	public void delete(String uuid);
	
	public List<AttachVO> findByBno(int bno);
	
	public void deleteAll(int bno);
	
	public List<AttachVO> getOldFiles();
}
