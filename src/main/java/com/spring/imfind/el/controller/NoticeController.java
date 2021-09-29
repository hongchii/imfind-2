package com.spring.imfind.el.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.service.NoticeService;

@Controller
//@Log4j
//@AllArgsConstructor
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("/notice")
	public String noticeList(Model model) throws Exception {
		
		model.addAttribute("notice", noticeService.getNoticeList());
		System.out.println("----------->>>>> notice list controller--------------");
		
		return "el/Board/notice";
	}
	
	@GetMapping("/insert")
	public String noticeList() throws Exception {
		
		return "el/Board/noticeInsert";
	}
	
	@PostMapping("/addNotice")
	public String noticeInsert(NoticeVO vo) throws Exception {
		
		System.out.println("notice insert controller ::: " + vo);
		noticeService.noticeInsert(vo);
		System.out.println("----------->>>>> notice insert controller--------------");
		
		return "redirect:/notice";
	}
	
	@GetMapping("/noticeInfo")
	public String noticeInfo(int noticeBno, Model model) throws Exception {
		
		System.out.println("----------->>>>> notice info controller ::: " + noticeBno);
		NoticeVO vo = noticeService.getNoticeInfo(noticeBno);
		System.out.println("----------->>>>> notice info controller vo ::: " + vo);
		model.addAttribute("info", vo);
		
		return "el/Board/noticeInfo";
	}
}
