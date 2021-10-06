package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.imfind.el.domain.LostComVO;
import com.spring.imfind.el.domain.PetVO;
import com.spring.imfind.el.domain.replyVO;

public interface PetMapper {

	// PET
	public int petInsert(PetVO petvo); // 애완동물 분실 등록

	int petdelete_data(int Pet_PostNum);

	List<PetVO> getPet(PetVO vo);

	List<PetVO> getPetservice(String Pet_Title);

	List<PetVO> getPetSido(String Pet_Loc);

	List<PetVO> getPet2(PetVO vo);

	List<PetVO> getPetservice2(String Pet_Title);

	List<PetVO> getPetSido2(String Pet_Loc);

	List<PetVO> getpetdata_info(int Pet_PostNum);

	int petupdate_data(PetVO vo);

	int pet_like_plus(@Param("Pet_PostNum") int Pet_PostNum, @Param("id") String id);

	int pet_like_cancel(@Param("Pet_PostNum") int Pet_PostNum, @Param("id") String id);

	int pet_likeCount(int lost_PostNum);

	public List<LostComVO> pet_commentList(int Lost_PostNum) throws Exception; // 댓글리스트

	public int pet_commentInsert(LostComVO comment) throws Exception; // 댓글추가

	public int pet_commentUpdate(LostComVO comment) throws Exception; // 댓글수정

	public int pet_commentDelete(int cno) throws Exception; // 댓글삭제

	// 동준 대댓글
	public List<replyVO> pet_replyList() throws Exception;

	public int pet_replyInsert(replyVO vo) throws Exception;

	public int pet_replyDelete(int re_num) throws Exception;

	public int pet_replyUpdate(replyVO vo) throws Exception;

	public PetVO getPetPostNum(PetVO boardvo) throws Exception;

	// 사례금 랭크(pet)
	List<PetVO> pet_pay_rank(PetVO vo);

	// 좋아요 랭크(pet)
	int pet_like_rank();
}
