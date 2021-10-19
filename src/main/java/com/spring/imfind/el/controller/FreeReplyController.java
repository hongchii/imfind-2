package com.spring.imfind.el.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.imfind.el.domain.FreeReplyVO;
import com.spring.imfind.el.paging.Criteria;
import com.spring.imfind.el.service.FreeReplyService;

@Controller
public class FreeReplyController {

	@Autowired
	private FreeReplyService freeReplyService;

	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> replyInsert(@RequestBody FreeReplyVO vo) {

		System.out.println("Free Reply controller :: " + vo);

		int res = freeReplyService.replyInsert_f(vo);

		return res == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/pages/ {bno} / {page}", produces = { MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<FreeReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") int bno) {
		System.out.println("getList...");
		Criteria cri = new Criteria(page, 10);

		return new ResponseEntity<>(freeReplyService.getList(cri, bno), HttpStatus.OK);
	}

	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<FreeReplyVO> get(@PathVariable("rno") int rno) {
		System.out.println("get..." + rno);

		return new ResponseEntity<>(freeReplyService.getReply_f(rno), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> delete_f(@PathVariable("rno") int rno) {
		System.out.println("delete..." + rno);

		return freeReplyService.delete_f(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }, value="/{rno}", consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody FreeReplyVO vo, @PathVariable("rno") int rno){
		
		vo.setRno(rno);
		
		return freeReplyService.modify_f(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
