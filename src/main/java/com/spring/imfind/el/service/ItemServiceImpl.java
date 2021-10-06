package com.spring.imfind.el.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.MJ.IndexLostPostDTO;
import com.spring.imfind.el.domain.ComVO;
import com.spring.imfind.el.domain.ItemVO;
import com.spring.imfind.el.domain.LostComVO;
import com.spring.imfind.el.domain.MemberVO;
import com.spring.imfind.el.domain.PayVO;
import com.spring.imfind.el.domain.PetVO;
import com.spring.imfind.el.domain.replyVO;
import com.spring.mapper.ItemMapper;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int itemInsert(ItemVO itemvo) {
		ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
		int res = itemMapper.itemInsert(itemvo);

		return res;
	}

	@Override
	public List<MemberVO> getPayMember(String id) {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		List<MemberVO> list = ItemMapper.getPayMember(id);

		return list;
	}

	@Override
	public int insertPay(PayVO payVO) {

		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		int res = ItemMapper.insertPay(payVO);

		return res;
	}

	@Override
	public int commentCount() throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.commentCount();
	}

	@Override
	public List<LostComVO> commentList(int Lost_PostNum) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.commentList(Lost_PostNum);
	}

	@Override
	public int commentInsert(LostComVO comment) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.commentInsert(comment);
	}

	@Override
	public int commentUpdate(LostComVO comment) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.commentUpdate(comment);
	}

	@Override
	public int commentDelete(int Com_Num) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.commentDelete(Com_Num);
	}

	@Override
	public int commentReply(LostComVO comment) {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		ItemMapper.commentReplyupdate(comment);
		comment.setRe_Seq(comment.getRe_Seq() + 1);
		comment.setRe_Lev(comment.getRe_Lev() + 1);

		return 0;
	}

	@Override
	public int replyInsert(replyVO vo) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.replyInsert(vo);
	}

	@Override
	public List<replyVO> replyList() throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		return ItemMapper.replyList();
	}

	@Override
	public int replyDelete(int re_num) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		return ItemMapper.replyDelete(re_num);
	}

	@Override
	public int updatePay(PayVO payvo) {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		int res = ItemMapper.updatePay(payvo);

		return res;
	}

	

	@Override
	public int replyUpdate(replyVO vo) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);

		return ItemMapper.replyUpdate(vo);
	}

	// 유희 인덱스
	@Override
	public List<ItemVO> gethighsetLostPay() throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		List<ItemVO> list = ItemMapper.gethighsetLostPay();
		return list;
	}

	@Override
	public List<PetVO> gethighsetPetPay() throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		List<PetVO> list = ItemMapper.gethighsetPetPay();
		return list;
	}

	@Override
	public int addPayBoardNum(ItemVO vo) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		int res = ItemMapper.addPayBoardNum(vo);
		return res;
	}

	// YH
	@Override
	public ItemVO getPostNum(ItemVO boardvo) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		ItemVO vo = ItemMapper.getPostNum(boardvo);
		return vo;
	}
	
	@Override
	public List<ComVO> getCommentList(String id) {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		List<ComVO> commentList = ItemMapper.getCommentList(id);

		return commentList;
	}

	@Override
	public List<ComVO> getPetCommentList(String id) {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		List<ComVO> commentList = ItemMapper.getPetCommentList(id);

		return commentList;
	}

	@Override
	public int deleteMember(MemberVO membervo) throws Exception {
		ItemMapper ItemMapper = sqlSession.getMapper(ItemMapper.class);
		int res = ItemMapper.deleteMember(membervo);

		return res;
	}

	@Override
	public List<ItemVO> getItemservice(ItemVO vo) {

		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		List<ItemVO> List1 = mapper.getitem(vo);
		List<ItemVO> List2 = mapper.getitem2(vo);
		List<ItemVO> List = new ArrayList<>();
		List.addAll(List1);
		List.addAll(List2);
		return List;
	}

	@Override
	public List<ItemVO> getItemservice(String lost_Title) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		List<ItemVO> List1 = mapper.getItemservice(lost_Title);
		List<ItemVO> List2 = mapper.getItemservice2(lost_Title);
		List<ItemVO> List = new ArrayList<>();
		List.addAll(List1);
		List.addAll(List2);

		return List;

	}

	@Override
	public List<ItemVO> getSido(String lost_Loc) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		List<ItemVO> List1 = mapper.getSido(lost_Loc);
		List<ItemVO> List2 = mapper.getSido2(lost_Loc);
		List<ItemVO> List = new ArrayList<>();
		List.addAll(List1);
		List.addAll(List2);
		return List;

	}

	@Override
	public List<ItemVO> getdata_info(int lost_PostNum) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		List<ItemVO> List = mapper.getdata_info(lost_PostNum);
		return List;
	}

	// YH
	@Override
	public List<IndexLostPostDTO> getItembyDate() {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		List<IndexLostPostDTO> List = mapper.getItembyDate();
		return List;
	}

	@Override
	public List<IndexLostPostDTO> getPetItembyDate() {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		List<IndexLostPostDTO> List = mapper.getPetItembyDate();
		return List;
	}

	@Override
	public int update_data(ItemVO vo) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		int res = mapper.update_data(vo);

		return res;
	}

	@Override
	public int delete_data(int lost_PostNum) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		int res = mapper.delete_data(lost_PostNum);
		return res;
	}

	// 좋아요
	@Override
	public int like_plus(int lost_PostNum, String id) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		return mapper.like_plus(lost_PostNum, id);
	}

	@Override
	public int like_cancel(int lost_PostNum, String id) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		return mapper.like_cancel(lost_PostNum, id);
	}

	@Override
	public List<ItemVO> likeChk() {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		return mapper.likeChk();
	}

	@Override
	public int likeCount(int lost_PostNum) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		return mapper.likeCount(lost_PostNum);
	}

	// 사례금 랭크
	@Override
	public List<ItemVO> lost_pay_rank(ItemVO vo) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		return mapper.lost_pay_rank(vo);
	}

	// 좋아요 랭크
	@Override
	public List<ItemVO> lost_like_rank() {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		int lost_PostNum = mapper.lost_like_rank();
		return mapper.getdata_info(lost_PostNum);
	}

}