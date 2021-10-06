
package com.spring.imfind.el.service;

import java.util.List;

import com.spring.imfind.el.MJ.IndexLostPostDTO;
import com.spring.imfind.el.domain.ComVO;
import com.spring.imfind.el.domain.ItemVO;
import com.spring.imfind.el.domain.LostComVO;
import com.spring.imfind.el.domain.MemberVO;
import com.spring.imfind.el.domain.PayVO;
import com.spring.imfind.el.domain.PetVO;
import com.spring.imfind.el.domain.replyVO;

public interface ItemService {
	public int itemInsert(ItemVO itemvo); // 물품 분실 등록

	public List<MemberVO> getPayMember(String id);

	public int insertPay(PayVO payVO);

	public int commentCount() throws Exception; // 댓글갯수

	public List<LostComVO> commentList(int Lost_PostNum) throws Exception; // 댓글리스트

	public int commentInsert(LostComVO comment) throws Exception; // 댓글추가

	public int commentUpdate(LostComVO comment) throws Exception; // 댓글수정

	public int commentDelete(int cno) throws Exception; // 댓글삭제

	public int commentReply(LostComVO comment);

	public int updatePay(PayVO payVO);// 사례금 환불신청
	// 동준 대댓글

	public List<replyVO> replyList() throws Exception;

	public int replyInsert(replyVO vo) throws Exception;

	public int replyDelete(int re_num) throws Exception;

	public int replyUpdate(replyVO vo) throws Exception;

	// 유희 인덱스
	public List<ItemVO> gethighsetLostPay() throws Exception;

	public List<PetVO> gethighsetPetPay() throws Exception;

	public int addPayBoardNum(ItemVO vo) throws Exception;

	public ItemVO getPostNum(ItemVO itemvo) throws Exception;

	

	// 은지 마이페이지 댓글 리스트
	public List<ComVO> getCommentList(String id);

	public List<ComVO> getPetCommentList(String id);

	public int deleteMember(MemberVO membervo) throws Exception; // 회원탈퇴

	public List<ItemVO> getItemservice(ItemVO vo);

	public List<ItemVO> getItemservice(String lost_Title);

	public List<ItemVO> getSido(String lost_Loc);

	public List<ItemVO> getdata_info(int lost_PostNum);

	public int update_data(ItemVO vo);

	public int delete_data(int lost_PostNum);

	public int like_plus(int lost_PostNum, String id);

	public int like_cancel(int lost_PostNum, String id);

	public List<ItemVO> likeChk();

	public int likeCount(int lost_PostNum);

	// YH
	public List<IndexLostPostDTO> getItembyDate();

	public List<IndexLostPostDTO> getPetItembyDate();

	// 사례금 랭크
	public List<ItemVO> lost_pay_rank(ItemVO vo);

	// 좋아요 랭크
	public List<ItemVO> lost_like_rank();

}