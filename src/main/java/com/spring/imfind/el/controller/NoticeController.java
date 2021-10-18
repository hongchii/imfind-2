package com.spring.imfind.el.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.imfind.el.domain.AttachVO;
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
		vo.setDelYN("1");
		
		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> System.out.println("확인::: " + attach));
		}
		
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
		
		vo.setDelYN("0");
		
		List<AttachVO> attachList = noticeService.getAttachList(vo.getNoticeBno());
	
		int res = noticeService.noticeDelete(vo);
		
		if (res > 0) {
			deleteFiles(attachList);
		}
		
		System.out.println("----------->>>>> notice delete controller--------------");
		
		return "redirect:/notice";
	}
	
	@RequestMapping("/deleteArrNotice")
	@ResponseBody
	public Map<String, String> noticeArrDelete(@RequestParam(value = "noticeBno[]", required = false) String[] noticeBno) throws Exception {

		ArrayList<String> data = new ArrayList<String>();
		Map<String, String> map = new HashMap<String,String>();
		
		for (String bno : noticeBno) {
			System.out.println("board_seq 컨트롤러 " + bno);

			int noticeBno1 = Integer.parseInt(bno);
			int res = noticeService.noticeArrDelete(noticeBno1);
			List<AttachVO> attachList = noticeService.getAttachList(noticeBno1);
			System.out.println("delete attachList :: " + attachList);
			deleteFiles(attachList);
			
			if (res > 0) {
				
				System.out.println("성공");
				data.add("success");
				
			} else {
				System.out.println("실패");
				data.add("fail");
			}

		}

		Iterator<String> itr = data.iterator();
		while (itr.hasNext()) {
			String result = itr.next();
			if ("fail".equals(result)) {
				map.put("RESULT", "fail");
				return map;
			}
		}
		
		map.put("RESULT", "success");
		return map;

	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachVO>> getAttachList(int bno) throws Exception {
		
		System.out.println("getAttachList :: " + bno);
		
		return new ResponseEntity<>(noticeService.getAttachList(bno), HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		System.out.println("delete attach files-----");
		System.out.println(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("/Users/hongmac/Documents/upload/temp/"+attach.getUploadPath()+"/"+attach.getUuid()+"/"+attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("/Users/hongmac/Documents/upload/temp/"+attach.getUploadPath()+"/"+attach.getUuid()+"/"+attach.getFileName());
					
					Files.delete(thumbNail);
				}
			} catch(Exception e) {
				System.out.println("delete file error" + e.getMessage());
			}
		});
	}

}
