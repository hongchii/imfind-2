package com.spring.mapper;

import java.util.List;
import java.util.Map;

import com.spring.imfind.el.domain.FreeVO;
import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.paging.Criteria;

public interface FreeMapper {
	
	public List<Map<String, Object>> getFreeList(Criteria cri) throws Exception;
	
	public int freeInsert(FreeVO vo) throws Exception;
	
	public FreeVO getFreeInfo(int freeBno) throws Exception;
	
	public int freeModify(FreeVO vo) throws Exception;
	
	public int freeDelete(FreeVO vo) throws Exception;
	
	public int freeReadCount(int freeBno) throws Exception;
	
	public int getFreeCount() throws Exception;
	
	public int freeArrDelete(int freeBno) throws Exception;
	
	public void insertSelectKey(FreeVO vo);
}
