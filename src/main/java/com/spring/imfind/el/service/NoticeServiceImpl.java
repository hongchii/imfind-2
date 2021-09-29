package com.spring.imfind.el.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.mapper.NoticeMapper;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public List<NoticeVO> getNoticeList() throws Exception {

		System.out.println("-----------------notice list serviceimpl----------------");
		
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		List<NoticeVO> noticeList = noticeMapper.getNoticeList();

		return noticeList;
	}

	@Override
	public int noticeInsert(NoticeVO vo) throws Exception {
		
		System.out.println("-----------------notice insert serviceImpl----------------");
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		int res = noticeMapper.noticeInsert(vo);
		
		
		return res;
	}

}
