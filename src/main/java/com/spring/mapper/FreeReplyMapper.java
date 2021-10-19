package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.imfind.el.domain.FreeReplyVO;
import com.spring.imfind.el.paging.Criteria;

public interface FreeReplyMapper {
	
	public int replyInsert_f(FreeReplyVO vo);
	
	public FreeReplyVO getReply_f(int bno);
	
	public int modify_f(FreeReplyVO vo);

	public int delete_f (int rno);
	
	public List<FreeReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") int bno);
}
