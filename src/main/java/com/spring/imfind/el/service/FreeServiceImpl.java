package com.spring.imfind.el.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.imfind.el.domain.FreeVO;
import com.spring.imfind.el.domain.AttachVO;
import com.spring.imfind.el.paging.Criteria;
import com.spring.mapper.FreeAttachMapper;
import com.spring.mapper.FreeMapper;
import com.spring.mapper.NoticeAttachMapper;

@Service("freeService")
public class FreeServiceImpl implements FreeService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, Object>> getFreeList(Criteria cri) throws Exception {
		
		System.out.println("---------->>>>> free list serviceimpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		List<Map<String, Object>> freeList = freeMapper.getFreeList(cri);

		return freeList;
		
	}
	
	@Transactional
	@Override
	public void freeInsert(FreeVO vo) throws Exception {
		
		System.out.println("---------->>>>> free insert serviceImpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		FreeAttachMapper attachMapper = sqlSession.getMapper(FreeAttachMapper.class);
		
		freeMapper.insertSelectKey(vo);
		
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return;
		}
		
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getFreeBno());
			attachMapper.insert(attach);
			System.out.println("free Insert ServiceImpl");
		});
		
		
		//int res = freeMapper.freeInsert(vo);
		
		return;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public FreeVO getFreeInfo(int freeBno) throws Exception {
		
		System.out.println("---------->>>>> free info serviceImpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		FreeVO vo = freeMapper.getFreeInfo(freeBno);
		int res = freeMapper.freeReadCount(freeBno);
		
		return vo;
	}

	@Override
	public int freeModify(FreeVO vo) throws Exception {
		
		System.out.println("---------->>>>> free modify serviceImpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		int res = freeMapper.freeModify(vo);
		
		return res;
	}

	@Override
	public int freeDelete(FreeVO vo) throws Exception {
		
		System.out.println("---------->>>>> free delete serviceImpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		int res = freeMapper.freeDelete(vo);
		
		return res;
	}

	@Override
	public int getFreeCount() throws Exception {
		
		System.out.println("---------->>>>> free count serviceImpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		int res = freeMapper.getFreeCount();
		
		return res;
	}

	@Override
	public int freeArrDelete(int freeBno) throws Exception {
		
		System.out.println("---------->>>>> free ArrDelete serviceImpl----------------");
		
		FreeMapper freeMapper = sqlSession.getMapper(FreeMapper.class);
		int res = freeMapper.freeArrDelete(freeBno);
		
		return res;
	}
	
	@Override
	public List<AttachVO> getAttachList(int bno) throws Exception {
		
		System.out.println("get Attach list by bno" + bno);
		
		FreeAttachMapper attachMapper = sqlSession.getMapper(FreeAttachMapper.class);
		
		return attachMapper.findByBno(bno);
	}
}
