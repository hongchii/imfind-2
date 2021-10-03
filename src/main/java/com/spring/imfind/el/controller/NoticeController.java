package com.spring.imfind.el.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.imfind.el.domain.NoticeVO;
import com.spring.imfind.el.paging.Criteria;
import com.spring.imfind.el.paging.PageMaker;
import com.spring.imfind.el.service.NoticeService;

@Controller
//@Log4j
//@AllArgsConstructor
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	/*
	@GetMapping("/notice")
	public String noticeList(Model model) throws Exception {
		
		model.addAttribute("notice", noticeService.getNoticeList());
		System.out.println("----------->>>>> notice list controller--------------");
		
		return "el/Board/notice";
	}
	*/
	
	@RequestMapping(value="/notice")
	public ModelAndView openBoardList(Criteria cri) throws Exception {
	        
	    ModelAndView mav = new ModelAndView("el/Board/notice");
	        
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(noticeService.getNoticeCount());
	        
	    List<Map<String,Object>> list = noticeService.getNoticeList(cri);
	    mav.addObject("notice", list);
	    mav.addObject("pageMaker", pageMaker);
	        
	    return mav;
	        
	}
	
	@GetMapping("/insert")
	public String noticeList() throws Exception {
		
		return "el/Board/noticeInsert";
	}
	
	@PostMapping("/addNotice")
	public String noticeInsert(NoticeVO vo) throws Exception {
		
		System.out.println("notice insert controller ::: " + vo);
		vo.setDelYN("N");
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
	
	@GetMapping("/getModifyNotice") 
	public String noticeModify(int noticeBno, Model model) throws Exception {
		
		System.out.println("----------->>>>> notice modify controller ::: " + noticeBno);
		NoticeVO vo = noticeService.getNoticeInfo(noticeBno);
		System.out.println("----------->>>>> notice modify controller vo ::: " + vo);
		model.addAttribute("info", vo);
		
		return "el/Board/noticeModify";
	}
	
	@PostMapping("/modifyNotice")
	public String noticeModify(NoticeVO vo) throws Exception {
		
		System.out.println("notice modify controller ::: " + vo);
		noticeService.noticeModify(vo);
		System.out.println("----------->>>>> notice modify controller--------------");
		
		return "redirect:/notice";
	}
	
	@GetMapping("/deleteNotice")
	public String noticeDelete(NoticeVO vo) throws Exception {
		
		vo.setDelYN("Y");
		int res = noticeService.noticeDelete(vo);
		System.out.println("----------->>>>> notice delete controller--------------");
		
		return "redirect:/notice";
	}
}
