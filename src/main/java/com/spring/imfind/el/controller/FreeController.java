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

import com.spring.imfind.el.domain.FreeVO;
import com.spring.imfind.el.domain.AttachVO;
import com.spring.imfind.el.paging.Criteria;
import com.spring.imfind.el.paging.PageMaker;
import com.spring.imfind.el.service.FreeService;

@Controller
public class FreeController {

	@Autowired
	private FreeService freeService;
	
	@RequestMapping(value="/free")
	public ModelAndView openBoardList(Criteria cri) throws Exception {
	        
	    ModelAndView mav = new ModelAndView("el/Board/free");
	        
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(freeService.getFreeCount());
	        
	    List<Map<String,Object>> list = freeService.getFreeList(cri);
	    mav.addObject("free", list);
	    mav.addObject("pageMaker", pageMaker);
	        
	    return mav;
	        
	}
	
	@GetMapping("/freeInsert")
	public String freeList() throws Exception {
		
		return "el/Board/freeInsert";
	}
	
	@PostMapping("/addFree")
	public String freeInsert(FreeVO vo) throws Exception {
		
		System.out.println("free insert controller ::: " + vo);
		vo.setDelYN("1");
		freeService.freeInsert(vo);
		System.out.println("----------->>>>> free insert controller--------------");
		
		return "redirect:/free";
	}
	
	@GetMapping("/freeInfo")
	public String freeInfo(int freeBno, Model model) throws Exception {
		
		System.out.println("----------->>>>> free info controller ::: " + freeBno);
		FreeVO vo = freeService.getFreeInfo(freeBno);
		System.out.println("----------->>>>> free info controller vo ::: " + vo);
		model.addAttribute("info", vo);
		
		return "el/Board/freeInfo";
	}
	
	@GetMapping("/getModifyFree") 
	public String freeModify(int freeBno, Model model) throws Exception {
		
		System.out.println("----------->>>>> free modify controller ::: " + freeBno);
		FreeVO vo = freeService.getFreeInfo(freeBno);
		System.out.println("----------->>>>> free modify controller vo ::: " + vo);
		model.addAttribute("info", vo);
		
		return "el/Board/freeModify";
	}
	
	@PostMapping("/modifyFree")
	public String freeModify(FreeVO vo) throws Exception {
		
		System.out.println("free modify controller ::: " + vo);
		freeService.freeModify(vo);
		System.out.println("----------->>>>> free modify controller--------------");
		
		return "redirect:/free";
	}
	
	@GetMapping("/deleteFree")
	public String freeDelete(FreeVO vo) throws Exception {
		
		vo.setDelYN("0");
		int res = freeService.freeDelete(vo);
		System.out.println("----------->>>>> free delete controller--------------");
		
		return "redirect:/free";
	}
	
	@RequestMapping("/deleteArrFree")
	@ResponseBody
	public Map<String, String> freeArrDelete(@RequestParam(value = "freeBno[]", required = false) String[] freeBno)
			throws Exception {

		ArrayList<String> data = new ArrayList<String>();
		Map<String, String> map = new HashMap<String,String>();
		
		for (String bno : freeBno) {
			System.out.println("freeArrDelete ::  " + bno);

			int freeBno1 = Integer.parseInt(bno);
			int res = freeService.freeArrDelete(freeBno1);

			if (res > 0) {
				System.out.println("삭제성공");
				data.add("success");
				
			} else {
				System.out.println("삭제실패");
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
	

	@GetMapping(value = "/getAttachList_f", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachVO>> getAttachList(int bno) throws Exception {
		
		System.out.println("getAttachList_free :: " + bno);
		
		return new ResponseEntity<>(freeService.getAttachList(bno), HttpStatus.OK);
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
