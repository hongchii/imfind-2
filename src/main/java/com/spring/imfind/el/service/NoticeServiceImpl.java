package com.spring.imfind.el.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.paging.Criteria;
import com.spring.mapper.NoticeMapper;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, Object>> getNoticeList(Criteria cri) throws Exception {

		System.out.println("---------->>>>> notice list serviceimpl----------------");
		
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		List<Map<String, Object>> noticeList = noticeMapper.getNoticeList(cri);

		return noticeList;
	}

	@Override
	public int noticeInsert(NoticeVO vo) throws Exception {
		
		System.out.println("---------->>>>> notice insert serviceImpl----------------");
		
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		int res = noticeMapper.noticeInsert(vo);
		
		return res;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public NoticeVO getNoticeInfo(int noticeBno) throws Exception {
		
		System.out.println("---------->>>>> notice info serviceImpl----------------");
		
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		NoticeVO vo = noticeMapper.getNoticeInfo(noticeBno);
		System.out.println("vo :: " + vo);
		int res = noticeMapper.noticeReadCount(noticeBno);
		System.out.println("res :: " + res);
		
		return vo;
	}

	@Override
	public int noticeModify(NoticeVO vo) throws Exception {
		
		System.out.println("---------->>>>> notice modify serviceImpl----------------");
		
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		int res = noticeMapper.noticeModify(vo);
		
		return res;
	}

	@Override
	public int noticeDelete(NoticeVO vo) throws Exception {
		
		System.out.println("---------->>>>> notice delete serviceImpl----------------");
		
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		int res = noticeMapper.noticeDelete(vo);
		
		return res;
	}

}
