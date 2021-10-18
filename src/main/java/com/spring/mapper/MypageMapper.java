
package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.domain.MypageVO;

public interface MypageMapper {
	List<MypageVO> getElsedata(String id);

	List<MypageVO> getPetElsedata(String id);

	List<MypageVO> getElsedata2(String id);

	List<MypageVO> getPetElsedata2(String id);

	List<MypageVO> getElsePaydata(String id);

	List<MypageVO> getElseWhoReplied(int param);

	List<MypageVO> getElseWhoRepliedPet(int param);

	public int insertGrade(MypageVO elvo);

	public int updatePay_Grade(MypageVO elvo);

	public int updatePay_GradePet(MypageVO elvo);

	// YS 1.28일
	List<MypageVO> getStarGrade(String F_Id);
	
   //내가 좋아요한 글
   List<MypageVO> getlike_lost(String id);
   List<MypageVO> getlike_pet(String id);

}