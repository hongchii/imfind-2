package com.spring.imfind.el.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class NoticeController {

	// @Autowired
	private NoticeService noticeService;

	@GetMapping("/notice")
	public String noticeList(Model model) throws Exception {

		System.out.println("-----------controller1--------------");
		//model.addAttribute("notice", noticeService.getNoticeList());
		List<NoticeVO> list = noticeService.getNoticeList();
		model.addAttribute("notice", list);
		System.out.println("-----------controller2--------------");
		return "el/Board/notice";
	}
}
