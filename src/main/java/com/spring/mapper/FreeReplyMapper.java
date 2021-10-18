package com.spring.mapper;

import com.spring.imfind.el.domain.FreeReplyVO;

public interface FreeReplyMapper {
	
	public int replyInsert_f(FreeReplyVO vo);
	
	public FreeReplyVO getReply_f(int bno);
}
