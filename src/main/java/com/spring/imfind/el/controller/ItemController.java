package com.spring.imfind.el.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.imfind.el.EJ.ComVO;
import com.spring.imfind.el.EJ.LostComVO;
import com.spring.imfind.el.EJ.MemberVO;
import com.spring.imfind.el.EJ.PayVO;
import com.spring.imfind.el.EJ.PetVO;
import com.spring.imfind.el.EJ.replyVO;
import com.spring.imfind.el.domain.ItemVO;
import com.spring.imfind.el.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/itemboard")
	public String itemboard() {

		return "el/EJ/itemboard";
	}

	@RequestMapping("/itemInsert")
	public String itemInsert(ItemVO ItemVO) throws Exception {
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // 이미지태그 자르기
		String content = ItemVO.getLost_Content();
		Matcher match = pattern.matcher(content);
		String Lost_Up_File = null;
		// String uploadPath = "/Users/hongmac/Documents/upload/";
		// String uploadPath = "C:\\JavaTPC\\WebProject\\upload\\";
		String uploadPath = "C:\\Project\\WebProject\\upload\\";

		if (match.find()) {
			Lost_Up_File = match.group(0);
		}
		// if(pattern)
		ItemVO.setLost_Up_File(Lost_Up_File); // 이미지 태그 Lost_Up_File에 삽입

		if (ItemVO.getLost_Up_File() == null) {
			String noimg = "0";
			ItemVO.setLost_Up_File(noimg);

		}

		// Lost_Content부분에 있는 태그들 자르기
		ItemVO.setLost_Content(ItemVO.getLost_Content()
				.replaceAll("<(/)?([a-zA-Z]*)(\\\\\\\\s[a-zA-Z]*=[^>]*)?(\\\\\\\\s)*(/)?>", ""));
		String replace1 = ItemVO.getLost_Content()
				.replaceAll("<(/)?([a-zA-Z]*)(\\\\\\\\s[a-zA-Z]*=[^>]*)?(\\\\\\\\s)*(/)?>", "");
		String replace2 = replace1.replaceAll("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>", "");
		ItemVO.setLost_Content(replace2);

		if (ItemVO.getLost_Pay() == null) {
			String replace4 = "0";
			ItemVO.setLost_Pay(replace4);
		}

		if (ItemVO.getLost_Pay() != null) {
			String replace3 = ItemVO.getLost_Pay().replaceAll(",", "");
			ItemVO.setLost_Pay(replace3);
		}

		// YH
		try {
			itemService.itemInsert(ItemVO);
			ItemVO postNum = itemService.getPostNum(ItemVO);
			ItemVO.setLost_PostNum(postNum.getLost_PostNum());
			itemService.addPayBoardNum(ItemVO);
		} catch (Exception e) {

		}

		return "redirect:/item";
	}

	// 썸머노트 이미지 업로드
	@ResponseBody
	@PostMapping("/profileImage")
	public void summer_image(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		// String uploadPath = "/Users/hongmac/Documents/upload/";
		// String uploadPath = "C:\\JavaTPC\\WebProject\\upload\\";
		String uploadPath = "C:\\Project\\WebProject\\upload\\";
		PrintWriter out = response.getWriter();
		String storedFileName = UUID.randomUUID().toString().replaceAll("-", "");

		file.transferTo(new File(uploadPath + storedFileName));
		out.println("/imfind/upload/" + storedFileName);
		out.close();

	}

	@RequestMapping(value = "el/paymember", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MemberVO> getMemberPay(@RequestParam(value = "id") String id) {
		List<MemberVO> list = itemService.getPayMember(id);

		return list;
	}

	@RequestMapping(value = "el/insertPay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertPay(@RequestBody Map<String, String> vo) {
		PayVO payVO = new PayVO();
		payVO.setPayCode((vo.get("PayCode")));
		payVO.setPay_Way((vo.get("Pay_Amount")));
		payVO.setPay_Amount(Integer.parseInt(vo.get("Pay_Amount")));
		payVO.setPay_State((vo.get("Pay_State")));
		payVO.setPay_Date((vo.get("Pay_Date")));
		payVO.setId(vo.get("Id"));
		int res = itemService.insertPay(payVO);

		return res;
	}

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
		// String uploadPath = "/Users/hongmac/Documents/upload/";
		String uploadPath = "C:\\Project\\WebProject\\upload\\";

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
			itemService.petInsert(petvo);
			PetVO postNum = itemService.getPetPostNum(petvo);
			petvo.setPet_PostNum(postNum.getPet_PostNum());
			itemService.addPayPetBoardNum(petvo);
		} catch (Exception e) {

		}

		return "redirect:/pet";
	}
	/* 은지 - 게시판 등록 끝 */

	/* 은지 댓글 시작 */
	@ResponseBody
	@RequestMapping(value = "/comment_list", produces = "application/json;charset=UTF-8")
	private List<LostComVO> commentList(@RequestParam int Lost_PostNum) throws Exception {
		List<LostComVO> comment_list = itemService.commentList(Lost_PostNum);

		return comment_list;
	}

	@ResponseBody
	@RequestMapping(value = "/comment_insert", produces = "application/json;charset=UTF-8")
	private int commentInsert(LostComVO comment, HttpSession session) throws Exception {
		comment.setId((String) session.getAttribute("loginUser"));

		if (comment.getSecret_Com() == null) { // 댓글 공개 설정
			comment.setSecret_Com("n");
		}

		return itemService.commentInsert(comment);
	}

	@ResponseBody
	@RequestMapping(value = "/comment_update", produces = "application/json;charset=UTF-8")
	private int commentUpdate(LostComVO comment) throws Exception {

		return itemService.commentUpdate(comment);
	}

	@ResponseBody
	@RequestMapping(value = "/comment_delete", produces = "application/json;charset=UTF-8")
	private int commentDelete(@RequestParam(value = "Com_Num") int Com_Num) throws Exception {
		return itemService.commentDelete(Com_Num);
	}

	/* 은지 댓글 끝 */

	@RequestMapping("/recomment.html")
	public String map() {

		return "el/EJ/recomment";
	}

	@RequestMapping("/commentReply")
	public String commentReply(LostComVO comment) throws Exception {
		int res = itemService.commentReply(comment);

		return "ItemService.commentInsert(comment)";
	}

	@RequestMapping(value = "el/refund", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

	@ResponseBody
	public int refund(@RequestBody HashMap<String, String> map) {
		String pay_State = "refund";
		PayVO payVO = new PayVO();
		payVO.setId(map.get("Id"));
		payVO.setPayCode(map.get("PayCode"));
		payVO.setPay_State(pay_State);

		int res = itemService.updatePay(payVO);

		return res;
	}

	/* 동준 대댓글 시작 */
	@ResponseBody
	@RequestMapping(value = "/replylist", produces = "application/json;charset=UTF-8")
	private List<replyVO> replyList() throws Exception {
		List<replyVO> reply_list = itemService.replyList();

		return reply_list;
	}

	@ResponseBody
	@RequestMapping(value = "/reply_update", produces = "application/json;charset=UTF-8")
	private int replyUpdate(replyVO vo) throws Exception {

		return itemService.replyUpdate(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/reply_insert", produces = "application/json;charset=UTF-8")
	private int replyInsert(replyVO vo, HttpSession session) throws Exception {
		vo.setId((String) session.getAttribute("loginUser"));

		return itemService.replyInsert(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/reply_delete", produces = "application/json;charset=UTF-8")
	private int replydelete(@RequestParam(value = "re_num") int re_num) throws Exception {

		return itemService.replyDelete(re_num);
	}

	/* 동준 대댓글 끝 */
	/* 동준 대댓글 시작 */
	@ResponseBody
	@RequestMapping(value = "/pet_replylist", produces = "application/json;charset=UTF-8")
	private List<replyVO> pet_replyList() throws Exception {
		List<replyVO> reply_list = itemService.pet_replyList();

		return reply_list;
	}

	@ResponseBody
	@RequestMapping(value = "/pet_reply_insert", produces = "application/json;charset=UTF-8")
	private int pet_replyInsert(replyVO vo, HttpSession session) throws Exception {
		vo.setId((String) session.getAttribute("loginUser"));

		return itemService.pet_replyInsert(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_reply_delete", produces = "application/json;charset=UTF-8")
	private int pet_replydelete(@RequestParam(value = "re_num") int re_num) throws Exception {

		return itemService.pet_replyDelete(re_num);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_reply_update", produces = "application/json;charset=UTF-8")
	private int pet_replyUpdate(replyVO vo) throws Exception {

		return itemService.pet_replyUpdate(vo);
	}

	/* 동준 대댓글 끝 */
	@ResponseBody
	@RequestMapping(value = "/pet_comment_list", produces = "application/json;charset=UTF-8")
	private List<LostComVO> pet_commentList(@RequestParam int Lost_PostNum) throws Exception {
		List<LostComVO> comment_list = itemService.pet_commentList(Lost_PostNum);

		return comment_list;
	}

	@ResponseBody
	@RequestMapping(value = "/pet_comment_insert", produces = "application/json;charset=UTF-8")
	private int pet_commentInsert(LostComVO comment, HttpSession session) throws Exception {
		comment.setId((String) session.getAttribute("loginUser"));

		if (comment.getSecret_Com() == null) { // 댓글 공개 설정
			comment.setSecret_Com("n");
		}

		return itemService.pet_commentInsert(comment);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_comment_update", produces = "application/json;charset=UTF-8")
	private int pet_commentUpdate(LostComVO comment) throws Exception {

		return itemService.pet_commentUpdate(comment);
	}

	@ResponseBody
	@RequestMapping(value = "/pet_comment_delete", produces = "application/json;charset=UTF-8")
	private int pet_commentDelete(@RequestParam(value = "Com_Num") int Com_Num) throws Exception {
		return itemService.pet_commentDelete(Com_Num);
	}

	/* 은지 마이페이지 댓글리스트 */
	@ResponseBody
	@RequestMapping(value = "/getCommentList", produces = "application/json;charset=UTF-8")
	public List<ComVO> getCommentList(@RequestParam(value = "id") String id) {
		List<ComVO> commentList = itemService.getCommentList(id);

		return commentList;
	}

	@ResponseBody
	@RequestMapping(value = "/getPetCommentList", produces = "application/json;charset=UTF-8")
	public List<ComVO> getPetCommentList(@RequestParam(value = "id") String id) {
		List<ComVO> commentList = itemService.getPetCommentList(id);

		return commentList;
	}

	// 회원탈퇴
	@RequestMapping("/delete")
	public String delete() {

		return "el/EJ/deletepage";
	}

	@ResponseBody
	@RequestMapping(value = "/deleteMember")
	private Map<String, String> deleteMember(MemberVO membervo) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		int res = itemService.deleteMember(membervo);

		if (res == 1) {
			map.put("res", "success");
			return map;
		} else {
			map.put("res", "fail");
			return map;
		}

	}

	// MJ Item 조회
		@RequestMapping("/item")
		public String item() {
			return "el/MJ/item";
		}

		@RequestMapping("/iteminfo")
		public String iteminfo() {
			return "el/MJ/iteminfo";
		}

		@RequestMapping("/updatepage")
		public String updatepage() {
			return "el/MJ/updateitem";
		}

		@RequestMapping(value = "/deletepage", produces = "application/json;charset=UTF-8")
		public String deletepage(@RequestParam(value = "lost_PostNum") int lost_PostNum) {
			itemService.delete_data(lost_PostNum);
			return "redirect:/item";
		}

		// 좋아요 기능
		@ResponseBody
		@RequestMapping(value = "/likeChk", produces = "application/json;charset=UTF-8")
		public List<ItemVO> likeChk() {

			List<ItemVO> list = itemService.likeChk();
			return list;
		}

		@ResponseBody
		@RequestMapping(value = "/likeCount", produces = "application/json;charset=UTF-8")
		public int likeCount(@RequestParam(value = "lost_PostNum") int lost_PostNum) {

			int res = itemService.likeCount(lost_PostNum);

			return res;
		}

		@ResponseBody
		@RequestMapping(value = "/likeplus", produces = "application/json;charset=UTF-8")
		public int likeplus(@RequestParam(value = "lost_PostNum") int lost_PostNum, @RequestParam(value = "id") String id) {
			int res = itemService.like_plus(lost_PostNum, id);
			return res;
		}

		@ResponseBody
		@RequestMapping(value = "/likecancel", produces = "application/json;charset=UTF-8")
		public int likecancel(@RequestParam(value = "lost_PostNum") int lost_PostNum,
				@RequestParam(value = "id") String id) {
			int res = itemService.like_cancel(lost_PostNum, id);
			return res;
		}

		// 좋아요 기능 (pet)
		@ResponseBody
		@RequestMapping(value = "/pet_likeChk", produces = "application/json;charset=UTF-8")
		public List<ItemVO> pet_likeChk() {

			List<ItemVO> list = itemService.likeChk();
			return list;
		}

		@ResponseBody
		@RequestMapping(value = "/pet_likeCount", produces = "application/json;charset=UTF-8")
		public int pet_likeCount(@RequestParam(value = "Pet_PostNum") int Pet_PostNum) {

			int res = itemService.pet_likeCount(Pet_PostNum);

			return res;
		}

		@ResponseBody
		@RequestMapping(value = "/pet_likeplus", produces = "application/json;charset=UTF-8")
		public int pet_likeplus(@RequestParam(value = "Pet_PostNum") int Pet_PostNum,
				@RequestParam(value = "id") String id) {
			int res = itemService.pet_like_plus(Pet_PostNum, id);
			return res;
		}

		@ResponseBody
		@RequestMapping(value = "/pet_likecancel", produces = "application/json;charset=UTF-8")
		public int pet_likecancel(@RequestParam(value = "Pet_PostNum") int Pet_PostNum,
				@RequestParam(value = "id") String id) {
			int res = itemService.pet_like_cancel(Pet_PostNum, id);
			return res;
		}

		@ResponseBody
		@RequestMapping(value = "/list.do", produces = "application/json;charset=UTF-8")
		public List<ItemVO> item(ItemVO vo) {
			List<ItemVO> list = itemService.getItemservice(vo);
			return list;
		}

		@ResponseBody
		@RequestMapping(value = "/search.do", produces = "application/json;charset=UTF-8")
		public List<ItemVO> list(@RequestParam(value = "lost_Title") String lost_Title) {
			List<ItemVO> list = itemService.getItemservice(lost_Title);
			return list;
		}

		@RequestMapping(value = "/sido.do", produces = "application/json; charset=utf-8")
		@ResponseBody
		public List<ItemVO> sido(@RequestParam(value = "lost_Loc", required = false) String lost_Loc) {
			List<ItemVO> list = itemService.getSido(lost_Loc);
			return list;
		}

		@ResponseBody
		@RequestMapping(value = "/datainfo.do", produces = "application/json;charset=UTF-8")
		public List<ItemVO> datainfo(@RequestParam(value = "lost_PostNum") int lost_PostNum) {
			List<ItemVO> list = itemService.getdata_info(lost_PostNum);
			return list;
		}

		@RequestMapping("/update.do")
		public String update_data(ItemVO vo) {
			Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // 이미지태그 자르기
			String content = vo.getLost_Content();
			Matcher match = pattern.matcher(content);
			String Lost_Up_File = null;
			String uploadPath = "C:\\Project\\WebProject\\upload\\";
			// String uploadPath = "/Users/hongmac/Documents/upload/";

			if (match.find()) {
				Lost_Up_File = match.group(0);
				vo.setLost_Up_File(Lost_Up_File); // 이미지 태그 Lost_Up_File에 삽입

			}
			// Lost_Content부분에 있는 태그들 자르기
			if (vo.getLost_Up_File() == null) {
				String noimg = "0";
				vo.setLost_Up_File(noimg);

			}
			// Lost_Content부분에 있는 태그들 자르기
			vo.setLost_Content(vo.getLost_Content().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", ""));
			String replace1 = vo.getLost_Content().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", "");
			String replace2 = replace1.replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>", "");
			// String replace4 = ItemVO.getLost_Item().replaceAll("있음,", ""); // name
			vo.setLost_Content(replace2);

			// ItemVO.setLost_Item(replace4);

			itemService.update_data(vo);

			return "redirect:/item";
		}

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
			String uploadPath = "C:\\Project\\WebProject\\upload\\";
			// String uploadPath = "/Users/hongmac/Documents/upload/";

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

			itemService.petupdate_data(vo);

			return "redirect:/pet";
		}

		@RequestMapping(value = "/petdeletepage", produces = "application/json;charset=UTF-8")
		public String petdeletepage(@RequestParam(value = "Pet_PostNum") int Pet_PostNum) {
			itemService.petdelete_data(Pet_PostNum);
			return "redirect:/pet";
		}

		@ResponseBody
		@RequestMapping(value = "/petlist.do", produces = "application/json;charset=UTF-8")
		public List<PetVO> pet(PetVO vo) {
			List<PetVO> list = itemService.getPetservice(vo);
			return list;
		}

		@ResponseBody
		@RequestMapping(value = "/petsearch.do", produces = "application/json;charset=UTF-8")
		public List<PetVO> petlist(@RequestParam(value = "Pet_Title") String Pet_Title) {
			List<PetVO> list = itemService.getPetservice(Pet_Title);
			return list;
		}

		@RequestMapping(value = "/petsido.do", produces = "application/json; charset=utf-8")
		@ResponseBody
		public List<PetVO> petsido(@RequestParam(value = "Pet_Loc", required = false) String Pet_Loc) {
			List<PetVO> list = itemService.getPetSido(Pet_Loc);
			return list;
		}

		@ResponseBody
		@RequestMapping(value = "/petdatainfo.do", produces = "application/json;charset=UTF-8")
		public List<PetVO> petdatainfo(@RequestParam(value = "Pet_PostNum") int Pet_PostNum) {
			List<PetVO> list = itemService.getpetdata_info(Pet_PostNum);
			return list;
		}

		// 사례금 랭크
		@ResponseBody
		@RequestMapping(value = "/lost_pay_rank.do", produces = "application/json;charset=UTF-8")
		public List<ItemVO> lost_pay_rank(ItemVO vo) {
			List<ItemVO> list = itemService.lost_pay_rank(vo);
			return list;
		}

		// 사례금 랭크(pet)
		@ResponseBody
		@RequestMapping(value = "/pet_pay_rank.do", produces = "application/json;charset=UTF-8")
		public List<PetVO> pet_pay_rank(PetVO vo) {
			List<PetVO> list = itemService.pet_pay_rank(vo);
			return list;
		}

		// 좋아요 랭크
		@ResponseBody
		@RequestMapping(value = "/lost_like_rank.do", produces = "application/json;charset=UTF-8")
		public List<ItemVO> lost_like_rank() {
			List<ItemVO> list = itemService.lost_like_rank();
			return list;
		}

		// 좋아요 랭크(pet)
		@ResponseBody
		@RequestMapping(value = "/pet_like_rank.do", produces = "application/json;charset=UTF-8")
		public List<PetVO> pet_like_rank() {
			List<PetVO> list = itemService.pet_like_rank();
			return list;
		}
}