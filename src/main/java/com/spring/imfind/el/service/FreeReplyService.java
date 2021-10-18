package com.spring.imfind.el.service;

import com.spring.imfind.el.domain.FreeReplyVO;

public interface FreeReplyService {
	
	public int replyInsert_f(FreeReplyVO vo);
	
	public FreeReplyVO getReply_f(int bno);
}
