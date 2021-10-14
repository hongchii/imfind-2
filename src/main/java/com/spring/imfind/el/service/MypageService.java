package com.spring.imfind.el.service;
import java.util.List;

import com.spring.imfind.el.domain.MypageVO;
public interface MypageService {
	List<MypageVO> getElsedata(String id);
	List<MypageVO> getElsePaydata(String id);
	List<MypageVO> getElseWhoReplied(String params);
	List<MypageVO> getElseWhoRepliedPet(String params);
	List<MypageVO> getPetElsedata(String id);
	public int updatePay_Grade(MypageVO elvo);
	public int updatePay_GradePet(MypageVO elvo);
	public int insertGrade(MypageVO elvo);

	
	// 연수 1.28
	List<MypageVO> getStarGrade(String F_Id); 
   //내가 좋아요 한 글
   public List<MypageVO> getlike(String id);

}
