package com.spring.imfind.el.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.imfind.el.domain.FreeReplyVO;
import com.spring.imfind.el.service.FreeReplyService;

@Controller
public class FreeReplyController {
	
	@Autowired
	private FreeReplyService freeReplyService;
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> replyInsert(@RequestBody FreeReplyVO vo){
		
		
		return null;
	}
}
