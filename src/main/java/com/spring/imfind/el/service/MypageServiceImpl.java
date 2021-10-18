
package com.spring.imfind.el.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.MypageVO;
import com.spring.mapper.MypageMapper;

@Service("elService")
public class MypageServiceImpl implements MypageService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MypageVO> getElsedata(String id) {

		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		List<MypageVO> elseList1 = elMapper.getElsedata(id);
		List<MypageVO> elseList2 = elMapper.getElsedata2(id);
		List<MypageVO> List = new ArrayList<>();
		List.addAll(elseList1);
		List.addAll(elseList2);
		return List;
	}

	@Override
	public List<MypageVO> getPetElsedata(String id) {
		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		List<MypageVO> elseList1 = elMapper.getPetElsedata(id);
		List<MypageVO> elseList2 = elMapper.getPetElsedata2(id);
		List<MypageVO> List = new ArrayList<>();
		List.addAll(elseList1);
		List.addAll(elseList2);
		return List;
	}

	@Override
	public List<MypageVO> getElsePaydata(String id) {
		List<MypageVO> elseList2 = null;
		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		elseList2 = elMapper.getElsePaydata(id);
		return elseList2;
	}

	@Override
	public List<MypageVO> getElseWhoReplied(String params) {
		List<MypageVO> elseList3 = null;
		int param = Integer.parseInt(params);

		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);

		elseList3 = elMapper.getElseWhoReplied(param);

		return elseList3;
	}

	@Override
	public int insertGrade(MypageVO elvo) {
		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		int res = elMapper.insertGrade(elvo);

		return res;
	}

	@Override
	public int updatePay_Grade(MypageVO elvo) {
		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		int res = elMapper.updatePay_Grade(elvo);
		return res;
	}

	@Override
	public int updatePay_GradePet(MypageVO elvo) {
		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		int res = elMapper.updatePay_GradePet(elvo);
		return res;
	}

	@Override
	public List<MypageVO> getElseWhoRepliedPet(String params) {

		List<MypageVO> elseList3 = null;
		int param = Integer.parseInt(params);

		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		elseList3 = elMapper.getElseWhoRepliedPet(param);

		return elseList3;
	}

	// YS 1.28 연수
	@Override
	public List<MypageVO> getStarGrade(String F_Id) {
		// TODO Auto-generated method stub
		MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
		List<MypageVO> list = elMapper.getStarGrade(F_Id);
		return list;
	}
	
	   @Override
	   public List<MypageVO> getlike(String id) {
	      MypageMapper elMapper = sqlSession.getMapper(MypageMapper.class);
	      List<MypageVO> List1 = elMapper.getlike_lost(id);
	      List<MypageVO> List2 = elMapper.getlike_pet(id);
	      List<MypageVO> List = new ArrayList<>();
	      List.addAll(List1);
	      List.addAll(List2);
	      return List;
	   }


}