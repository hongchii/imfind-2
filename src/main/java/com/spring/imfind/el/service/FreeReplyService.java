package com.spring.imfind.el.service;

import java.util.List;

import com.spring.imfind.el.domain.FreeReplyVO;
import com.spring.imfind.el.paging.Criteria;

public interface FreeReplyService {
	
	public int replyInsert_f(FreeReplyVO vo);
	
	public FreeReplyVO getReply_f(int bno);
	
	public int modify_f(FreeReplyVO vo);
	
	public int delete_f(int rno);
	
	public List<FreeReplyVO> getList(Criteria cri, int bno);
}
