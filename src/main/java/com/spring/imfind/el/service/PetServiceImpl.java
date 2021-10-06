package com.spring.imfind.el.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.LostComVO;
import com.spring.imfind.el.domain.PetVO;
import com.spring.imfind.el.domain.replyVO;
import com.spring.mapper.ItemMapper;
import com.spring.mapper.PetMapper;

@Service("PetService")
public class PetServiceImpl implements PetService {

	@Autowired
	private SqlSession sqlSession;

	// PET
	@Override
	public int petdelete_data(int Pet_PostNum) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		int res = mapper.petdelete_data(Pet_PostNum);
		return res;
	}

	@Override
	public List<PetVO> getPetservice(PetVO vo) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		List<PetVO> List1 = mapper.getPet(vo);
		List<PetVO> List2 = mapper.getPet2(vo);
		List<PetVO> List = new ArrayList<>();
		List.addAll(List1);
		List.addAll(List2);
		return List;
	}

	@Override
	public List<PetVO> getPetservice(String Pet_Title) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		List<PetVO> List1 = mapper.getPetservice(Pet_Title);
		List<PetVO> List2 = mapper.getPetservice2(Pet_Title);
		List<PetVO> List = new ArrayList<>();
		List.addAll(List1);
		List.addAll(List2);
		return List;
	}

	@Override
	public List<PetVO> getPetSido(String Pet_Loc) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		List<PetVO> List1 = mapper.getPetSido(Pet_Loc);
		List<PetVO> List2 = mapper.getPetSido2(Pet_Loc);
		List<PetVO> List = new ArrayList<>();
		List.addAll(List1);
		List.addAll(List2);
		return List;
	}

	@Override
	public List<PetVO> getpetdata_info(int Pet_PostNum) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		List<PetVO> List = mapper.getpetdata_info(Pet_PostNum);
		return List;
	}

	@Override
	public int petupdate_data(PetVO vo) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		int res = mapper.petupdate_data(vo);
		return res;
	}

	// 좋아요(pet)
	@Override
	public int pet_like_plus(int Pet_PostNum, String id) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		return mapper.pet_like_plus(Pet_PostNum, id);
	}

	@Override
	public int pet_like_cancel(int Pet_PostNum, String id) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		return mapper.pet_like_cancel(Pet_PostNum, id);
	}

	@Override
	public int pet_likeCount(int Pet_PostNum) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		return mapper.pet_likeCount(Pet_PostNum);
	}

	// 사례금 랭크(pet)
	@Override
	public List<PetVO> pet_pay_rank(PetVO vo) {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);

		return mapper.pet_pay_rank(vo);
	}

	// 좋아요 랭크(pet)
	@Override
	public List<PetVO> pet_like_rank() {
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		int Pet_PostNum = mapper.pet_like_rank();
		return mapper.getpetdata_info(Pet_PostNum);
	}

	@Override
	public int petInsert(PetVO petvo) {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);
		int res = petMapper.petInsert(petvo);

		return res;
	}

	@Override
	public int pet_replyInsert(replyVO vo) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);

		return petMapper.pet_replyInsert(vo);
	}

	@Override
	public List<replyVO> pet_replyList() throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);
		return petMapper.pet_replyList();
	}

	@Override
	public int pet_replyDelete(int re_num) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);
		return petMapper.pet_replyDelete(re_num);
	}

	@Override
	public List<LostComVO> pet_commentList(int Lost_PostNum) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);

		return petMapper.pet_commentList(Lost_PostNum);
	}

	@Override
	public int pet_commentInsert(LostComVO comment) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);

		return petMapper.pet_commentInsert(comment);
	}

	@Override
	public int pet_commentUpdate(LostComVO comment) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);

		return petMapper.pet_commentUpdate(comment);
	}

	@Override
	public int pet_commentDelete(int Com_Num) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);

		return petMapper.pet_commentDelete(Com_Num);
	}

	@Override
	public PetVO getPetPostNum(PetVO boardvo) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);
		PetVO vo = petMapper.getPetPostNum(boardvo);
		return vo;
	}

	@Override
	public int pet_replyUpdate(replyVO vo) throws Exception {
		PetMapper petMapper = sqlSession.getMapper(PetMapper.class);

		return petMapper.pet_replyUpdate(vo);
	}

	@Override
	public int addPayPetBoardNum(PetVO vo) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		int res = ItemMapper.addPayPetBoardNum(vo);
		return res;
	}

}
