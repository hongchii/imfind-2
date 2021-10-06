package com.spring.imfind.el.service;

import java.util.List;

import com.spring.imfind.el.domain.LostComVO;
import com.spring.imfind.el.domain.PetVO;
import com.spring.imfind.el.domain.replyVO;

public interface PetService {

	public int petInsert(PetVO petvo); // 애완동물 분실 등록

	public List<LostComVO> pet_commentList(int Lost_PostNum) throws Exception; // 댓글리스트

	public int pet_commentInsert(LostComVO comment) throws Exception; // 댓글추가

	public int pet_commentUpdate(LostComVO comment) throws Exception; // 댓글수정

	public int pet_commentDelete(int cno) throws Exception; // 댓글삭제

	// 동준 대댓글
	public List<replyVO> pet_replyList() throws Exception;

	public int pet_replyInsert(replyVO vo) throws Exception;

	public int pet_replyDelete(int re_num) throws Exception;

	public int pet_replyUpdate(replyVO vo) throws Exception;

	public PetVO getPetPostNum(PetVO itemvo) throws Exception;

	// PET
	public int petdelete_data(int Pet_PostNum);

	public List<PetVO> getPetservice(PetVO vo);

	public List<PetVO> getPetservice(String Pet_Title);

	public List<PetVO> getPetSido(String Pet_Loc);

	public List<PetVO> getpetdata_info(int Pet_PostNum);

	public int petupdate_data(PetVO vo);

	public int pet_like_plus(int Pet_PostNum, String id);

	public int pet_like_cancel(int Pet_PostNum, String id);

	public int pet_likeCount(int lost_PostNum);

	public int addPayPetBoardNum(PetVO vo) throws Exception;

	// 사례금 랭크(pet)
	public List<PetVO> pet_pay_rank(PetVO vo);

	// 좋아요 랭크(pet)
	public List<PetVO> pet_like_rank();
}
