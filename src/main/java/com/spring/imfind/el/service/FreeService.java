package com.spring.imfind.el.service;

import java.util.List;
import java.util.Map;

import com.spring.imfind.el.domain.FreeVO;
import com.spring.imfind.el.paging.Criteria;

public interface FreeService {
	
	public List<Map<String, Object>> getFreeList(Criteria cri) throws Exception;
	
	public int freeInsert(FreeVO vo) throws Exception;
	
	public FreeVO getFreeInfo(int freeBno) throws Exception;
	
	public int freeModify(FreeVO vo) throws Exception;
	
	public int freeDelete(FreeVO vo) throws Exception;
	
	public int getFreeCount() throws Exception;
	
	public int freeArrDelete(int freeBno) throws Exception;
}
