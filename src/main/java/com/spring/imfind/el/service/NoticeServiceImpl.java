package com.spring.imfind.el.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.mapper.NoticeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
	/*
	@Autowired
	private SqlSession sqlSession;
	*/
	
	private NoticeMapper noticeMapper;
	@Override
	public List<NoticeVO> getNoticeList() throws Exception {
		
		System.out.println("-----------------notice serviceimpl----------------");
	//	NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
	//	List<NoticeVO> noticeList = noticeMapper.getNoticeList();
		
		return noticeMapper.getNoticeList();
	}

}
