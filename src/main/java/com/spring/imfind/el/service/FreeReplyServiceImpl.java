package com.spring.imfind.el.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.FreeReplyVO;
import com.spring.imfind.el.paging.Criteria;
import com.spring.mapper.FreeReplyMapper;

@Service("freeReplyService")
public class FreeReplyServiceImpl implements FreeReplyService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int replyInsert_f(FreeReplyVO vo) {
		
		System.out.println("reply insert .........." + vo);
		FreeReplyMapper freeReplyMapper = sqlSession.getMapper(FreeReplyMapper.class);
		
		return freeReplyMapper.replyInsert_f(vo);
	}

	@Override
	public FreeReplyVO getReply_f(int bno) {
		
		System.out.println("reply getReply .........." + bno);
		FreeReplyMapper freeReplyMapper = sqlSession.getMapper(FreeReplyMapper.class);
		
		return freeReplyMapper.getReply_f(bno);
	}

	@Override
	public int modify_f(FreeReplyVO vo) {
		
		System.out.println("reply modify .........." + vo);
		FreeReplyMapper freeReplyMapper = sqlSession.getMapper(FreeReplyMapper.class);
		
		return freeReplyMapper.modify_f(vo);
	}

	@Override
	public int delete_f(int rno) {
		
		System.out.println("reply delete .........." + rno);
		FreeReplyMapper freeReplyMapper = sqlSession.getMapper(FreeReplyMapper.class);
		
		return freeReplyMapper.delete_f(rno);
	}

	@Override
	public List<FreeReplyVO> getList(Criteria cri, int bno) {
		
		System.out.println("reply getList .........." + bno);
		FreeReplyMapper freeReplyMapper = sqlSession.getMapper(FreeReplyMapper.class);
		
		return freeReplyMapper.getListWithPaging(cri, bno);
	}

}
