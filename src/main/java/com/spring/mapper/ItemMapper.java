package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.imfind.el.EJ.ComVO;
import com.spring.imfind.el.EJ.LostComVO;
import com.spring.imfind.el.EJ.MemberVO;
import com.spring.imfind.el.EJ.PayVO;
import com.spring.imfind.el.EJ.PetVO;
import com.spring.imfind.el.EJ.replyVO;
import com.spring.imfind.el.MJ.IndexLostPostDTO;
import com.spring.imfind.el.domain.ItemVO;

public interface ItemMapper {
	public int itemInsert(ItemVO vo); // 물품 분실 등록

	public List<MemberVO> getPayMember(String id);

	public int insertPay(PayVO payVO);

	public int petInsert(PetVO petvo); // 애완동물 분실 등록

	public int commentCount() throws Exception; // 댓글갯수

	public List<LostComVO> commentList(int Lost_PostNum) throws Exception; // 댓글리스트

	public int commentInsert(LostComVO comment) throws Exception; // 댓글추가

	public int commentUpdate(LostComVO comment) throws Exception; // 댓글수정

	public int commentDelete(int Com_Num) throws Exception; // 댓글삭제

	public int commentReplyupdate(LostComVO comment); // 답글에 대한 re_seq update작업

	public int commentReply(LostComVO comment); // 답글 insert작업 수행

	public int updatePay(PayVO payVO);

	// 동준 대댓글
	public List<replyVO> replyList() throws Exception;

	public int replyInsert(replyVO vo) throws Exception;

	public int replyDelete(int re_num) throws Exception;

	public int replyUpdate(replyVO vo) throws Exception;

	public List<LostComVO> pet_commentList(int Lost_PostNum) throws Exception; // 댓글리스트

	public int pet_commentInsert(LostComVO comment) throws Exception; // 댓글추가

	public int pet_commentUpdate(LostComVO comment) throws Exception; // 댓글수정

	public int pet_commentDelete(int cno) throws Exception; // 댓글삭제

	// 동준 대댓글
	public List<replyVO> pet_replyList() throws Exception;

	public int pet_replyInsert(replyVO vo) throws Exception;

	public int pet_replyDelete(int re_num) throws Exception;

	public int pet_replyUpdate(replyVO vo) throws Exception;

	// 유희 인덱스
	public List<ItemVO> gethighsetLostPay() throws Exception;

	public List<PetVO> gethighsetPetPay() throws Exception;

	public int addPayBoardNum(ItemVO vo);

	public ItemVO getPostNum(ItemVO vo) throws Exception;

	public int addPayPetBoardNum(PetVO vo) throws Exception;

	public PetVO getPetPostNum(PetVO boardvo) throws Exception;

	// 은지 마이페이지 댓글 리스트
	public List<ComVO> getCommentList(String id);

	public List<ComVO> getPetCommentList(String id);

	public int deleteMember(MemberVO membervo) throws Exception; // 회원탈퇴
	
	List<ItemVO> getitem(ItemVO vo);

	List<ItemVO> getItemservice(String lost_Title);

	List<ItemVO> getSido(String lost_Loc);

	List<ItemVO> getitem2(ItemVO vo);

	List<ItemVO> getItemservice2(String lost_Title);

	List<ItemVO> getSido2(String lost_Loc);

	List<ItemVO> getdata_info(int lost_PostNum);

	int update_data(ItemVO vo);

	int delete_data(int lost_PostNum);

	int like_plus(@Param("lost_PostNum") int lost_PostNum, @Param("id") String id);

	int like_cancel(@Param("lost_PostNum") int lost_PostNum, @Param("id") String id);

	List<ItemVO> likeChk();

	int likeCount(int lost_PostNum);

	// YH
	List<IndexLostPostDTO> getItembyDate();

	List<IndexLostPostDTO> getPetItembyDate();

	// PET
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

	// 사례금 랭크
	List<ItemVO> lost_pay_rank(ItemVO vo);

	// 사례금 랭크(pet)
	List<PetVO> pet_pay_rank(PetVO vo);

	// 좋아요 랭크
	int lost_like_rank();

	// 좋아요 랭크(pet)
	int pet_like_rank();

}
