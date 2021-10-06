package com.spring.imfind.el.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.imfind.el.domain.ItemVO;
import com.spring.imfind.el.domain.LostComVO;
import com.spring.imfind.el.domain.PetVO;
import com.spring.imfind.el.domain.replyVO;
import com.spring.imfind.el.service.PetService;

@Controller
public class PetController {

	@Autowired
	private PetService petService;

	@RequestMapping("/petboard")
	public String petboard() {

		return "el/EJ/petboard";

	}

	@RequestMapping("petInsert")
	public String petInsert(PetVO petvo) throws Exception {
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // 이미지태그 자르기
		String content = petvo.getPet_Content();
		Matcher match = pattern.matcher(content);
		String pet_Up_File = null;
		String uploadPath = "/Users/hongmac/Documents/upload/";
		// String uploadPath = "C:\\Project\\WebProject\\upload\\";

		if (match.find()) {
			pet_Up_File = match.group(0);
		}
		// if(pattern)
		petvo.setPet_Up_File(pet_Up_File); // 이미지 태그 Lost_Up_File에 삽입

		if (petvo.getPet_Up_File() == null) {
			String noimg = "0";
			petvo.setPet_Up_File(noimg);

		}
		// pet_Content부분에 있는 태그들 자르기
		petvo.setPet_Content(
				petvo.getPet_Content().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", ""));
		String replace1 = petvo.getPet_Content().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", "");
		String replace2 = replace1.replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>", "");
		// String replace3 = petvo.getPet_Pay().replaceAll(",", ""); // pay
		String replace4 = petvo.getPet_Name().replaceAll("있음,", ""); // name
		petvo.setPet_Content(replace2);
		// petvo.setPet_Pay(replace3);
		petvo.setPet_Name(replace4);

		if (petvo.getPet_Pay() == null) {
			String replace5 = "0";
			petvo.setPet_Pay(replace5);
		}

		if (petvo.getPet_Pay() != null) {
			String replace6 = petvo.getPet_Pay().replaceAll(",", "");
			petvo.setPet_Pay(replace6);
		}
		try {
			petService.petInsert(petvo);
			PetVO postNum = petService.getPetPostNum(petvo);
			petvo.setPet_PostNum(postNum.getPet_PostNum());
			petService.addPayPetBoardNum(petvo);
		} catch (Exception e) {

		}

		return "redirect:/pet";
	}
	/* 은지 - 게시판 등록 끝 */

	// Pet
	@RequestMapping("/pet")
	public String pet() {
		return "el/MJ/pet";
	}

	@RequestMapping("/petinfo")
	public String petinfo() {
		return "el/MJ/petinfo";
	}

	@RequestMapping("/petupdatepage")
	public String petupdatepage() {
		return "el/MJ/updatepet";
	}

	@RequestMapping("/petupdate.do")
	public String petupdate_data(PetVO vo) {
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // 이미지태그 자르기
		String content = vo.getPet_Content();
		Matcher match = pattern.matcher(content);
		String Lost_Up_File = null;
		// String uploadPath = "C:\\Project\\WebProject\\upload\\";
		String uploadPath = "/Users/hongmac/Documents/upload/";

		if (match.find()) {
			Lost_Up_File = match.group(0);
			vo.setPet_Up_File(Lost_Up_File); // 이미지 태그 Lost_Up_File에 삽입

		}
		// Lost_Content부분에 있는 태그들 자르기
		if (vo.getPet_Up_File() == null) {
			String noimg = "0";
			vo.setPet_Up_File(noimg);

		}
		// Lost_Content부분에 있는 태그들 자르기
		vo.setPet_Content(vo.getPet_Content().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", ""));
		String replace1 = vo.getPet_Content().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", "");
		String replace2 = replace1.replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>", "");
		// String replace4 = ItemVO.getLost_Item().replaceAll("있음,", ""); // name
		vo.setPet_Content(replace2);

		// ItemVO.setLost_Item(replace4);

		petService.petupdate_data(vo);

		return "redirect:/pet";
	}

	@RequestMapping(value = "/petdeletepage", produces = "application/json;charset=UTF-8")
	public String petdeletepage(@RequestParam(value = "Pet_PostNum") int Pet_PostNum) {
		petService.petdelete_data(Pet_PostNum);
		return "redirect:/pet";
	}

	@ResponseBody
	@RequestMapping(value = "/petlist.do", produces = "application/json;charset=UTF-8")
	public List<PetVO> pet(PetVO vo) {
		List<PetVO> list = petService.getPetservice(vo);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/petsearch.do", produces = "application/json;charset=UTF-8")
	public List<PetVO> petlist(@RequestParam(value = "Pet_Title") String Pet_Title) {
		List<PetVO> list = petService.getPetservice(Pet_Title);
		return list;
	}

	@RequestMapping(value = "/petsido.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<PetVO> petsido(@RequestParam(value = "Pet_Loc", required = false) String Pet_Loc) {
		List<PetVO> list = petService.getPetSido(Pet_Loc);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/petdatainfo.do", produces = "application/json;charset=UTF-8")
	public List<PetVO> petdatainfo(@RequestParam(value = "Pet_PostNum") int Pet_PostNum) {
		List<PetVO> list = petService.getpetdata_info(Pet_PostNum);
		return list;
	}

	/* 동준 대댓글 시작 */
	@ResponseBody
	@RequestMapping(value = "/pet_replylist", produces = "application/json;charset=UTF-8")
	private List<replyVO> pet_replyList() throws Exception {
		List<replyVO> reply_list = petService.pet_replyList();

		return reply_list;
	}

	@ResponseBody
	@RequestMapping(value = "/pet_reply_insert", produces = "application/json;charset=UTF-8")
	private int pet_replyInsert(replyVO vo, HttpSession session) throws Exception {
		vo.setId((String) session.getAttribute("loginUser"));

		return petService.pet_replyInsert(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_reply_delete", produces = "application/json;charset=UTF-8")
	private int pet_replydelete(@RequestParam(value = "re_num") int re_num) throws Exception {

		return petService.pet_replyDelete(re_num);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_reply_update", produces = "application/json;charset=UTF-8")
	private int pet_replyUpdate(replyVO vo) throws Exception {

		return petService.pet_replyUpdate(vo);
	}

	/* 동준 대댓글 끝 */
	@ResponseBody
	@RequestMapping(value = "/pet_comment_list", produces = "application/json;charset=UTF-8")
	private List<LostComVO> pet_commentList(@RequestParam int Lost_PostNum) throws Exception {
		List<LostComVO> comment_list = petService.pet_commentList(Lost_PostNum);

		return comment_list;
	}

	@ResponseBody
	@RequestMapping(value = "/pet_comment_insert", produces = "application/json;charset=UTF-8")
	private int pet_commentInsert(LostComVO comment, HttpSession session) throws Exception {
		comment.setId((String) session.getAttribute("loginUser"));

		if (comment.getSecret_Com() == null) { // 댓글 공개 설정
			comment.setSecret_Com("n");
		}

		return petService.pet_commentInsert(comment);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_comment_update", produces = "application/json;charset=UTF-8")
	private int pet_commentUpdate(LostComVO comment) throws Exception {

		return petService.pet_commentUpdate(comment);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_comment_delete", produces = "application/json;charset=UTF-8")
	private int pet_commentDelete(@RequestParam(value = "Com_Num") int Com_Num) throws Exception {
		return petService.pet_commentDelete(Com_Num);
	}

	// 좋아요 기능 (pet)
//	@ResponseBody
//	@RequestMapping(value = "/pet_likeChk", produces = "application/json;charset=UTF-8")
//	public List<ItemVO> pet_likeChk() {
//
//		List<ItemVO> list = petService.likeChk();
//		return list;
//	}

	@ResponseBody
	@RequestMapping(value = "/pet_likeCount", produces = "application/json;charset=UTF-8")
	public int pet_likeCount(@RequestParam(value = "Pet_PostNum") int Pet_PostNum) {

		int res = petService.pet_likeCount(Pet_PostNum);

		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/pet_likeplus", produces = "application/json;charset=UTF-8")
	public int pet_likeplus(@RequestParam(value = "Pet_PostNum") int Pet_PostNum,
			@RequestParam(value = "id") String id) {
		int res = petService.pet_like_plus(Pet_PostNum, id);
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/pet_likecancel", produces = "application/json;charset=UTF-8")
	public int pet_likecancel(@RequestParam(value = "Pet_PostNum") int Pet_PostNum,
			@RequestParam(value = "id") String id) {
		int res = petService.pet_like_cancel(Pet_PostNum, id);
		return res;
	}

	// 사례금 랭크(pet)
	@ResponseBody
	@RequestMapping(value = "/pet_pay_rank.do", produces = "application/json;charset=UTF-8")
	public List<PetVO> pet_pay_rank(PetVO vo) {
		List<PetVO> list = petService.pet_pay_rank(vo);
		return list;
	}

	// 좋아요 랭크(pet)
	@ResponseBody
	@RequestMapping(value = "/pet_like_rank.do", produces = "application/json;charset=UTF-8")
	public List<PetVO> pet_like_rank() {
		List<PetVO> list = petService.pet_like_rank();
		return list;
	}
}
