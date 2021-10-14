
package com.spring.imfind.el.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.imfind.el.domain.MypageVO;
import com.spring.imfind.el.service.MypageService;

@Controller
public class MypageController {
	@Autowired
	private MypageService elService;

	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return "el/YS/mypage";
	}

	@RequestMapping(value = "/getElsedata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MypageVO> getElsedata(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginUser");

		List<MypageVO> list = elService.getElsedata(id);
		return list;
	}

	@RequestMapping(value = "/getPetElsedata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MypageVO> getPetElsedata(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginUser");
		List<MypageVO> listpet = elService.getPetElsedata(id);
		return listpet;
	}

	@RequestMapping(value = "/getElsePaydata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MypageVO> getElsePaydata(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginUser");
		List<MypageVO> list2 = elService.getElsePaydata(id);
		return list2;
	}

	@RequestMapping(value = "/getElseWhoReplied", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MypageVO> getElseWhoReplied(String params) {

		List<MypageVO> list3 = elService.getElseWhoReplied(params);
		return list3;
	}

	@RequestMapping(value = "/getElseWhoRepliedPet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<MypageVO> getElseWhoRepliedPet(String params) {

		List<MypageVO> list3 = elService.getElseWhoRepliedPet(params);
		return list3;
	}

	// 평점 입력하기 -> 1.insert grade, 2.update pay table
	@RequestMapping(value = "/insertGrade.do")
	@ResponseBody
	public Map<String, Object> insertGrade(@RequestBody HashMap<String, String> map) {

		MypageVO elvo = new MypageVO();

		elvo.setF_Id(map.get("F_Id"));

		elvo.setGrade(Integer.parseInt(map.get("grade")));
		elvo.setLost_PostNum(Integer.parseInt(map.get("Lost_PostNum")));
		elvo.setId(map.get("Id"));

		Map<String, Object> retVal = new HashMap<String, Object>();

		try {
			int res = elService.insertGrade(elvo);
			if (res == 1) {

				elvo.setDeal_State("completed");
				int res2 = elService.updatePay_Grade(elvo);

				retVal.put("res", "OK");
			}
		} catch (Exception e) {
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal;
	}

	// 평점 입력하기 -> 1.insert grade, 2.update pay table
	@RequestMapping(value = "/insertGradePet.do")
	@ResponseBody
	public Map<String, Object> insertGradePet(@RequestBody HashMap<String, String> map) {

		MypageVO elvo = new MypageVO();

		elvo.setF_Id(map.get("F_Id"));
		elvo.setGrade(Integer.parseInt(map.get("grade")));
		elvo.setPet_PostNum(Integer.parseInt(map.get("Pet_PostNum")));
		elvo.setId(map.get("Id"));

		Map<String, Object> retVal = new HashMap<String, Object>();

		try {
			int res = elService.insertGrade(elvo);
			if (res == 1) {

				elvo.setDeal_State("completed");
				int res2 = elService.updatePay_GradePet(elvo);

				retVal.put("res", "OK");
			}
		} catch (Exception e) {
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal;
	}

	@RequestMapping("/index")
	public String index2() {
		return "el/index";
	}

	// YS 1.28일
	@RequestMapping(value = "/getStarGrade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int getStarGrade(@RequestParam(value = "F_Id") String F_id) {
		List<MypageVO> list = elService.getStarGrade(F_id);
		int sum = 0;
		int avg = 0;
		for (MypageVO el : list) {
			sum += el.getGrade();
			avg = sum / list.size();
		}
		return avg;
	}
	
   @RequestMapping(value = "/getLiketo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
   @ResponseBody
   public List<MypageVO> getLiketo(HttpServletRequest request) {
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("loginUser");

      List<MypageVO> list = elService.getlike(id);
      return list;
   }


}
