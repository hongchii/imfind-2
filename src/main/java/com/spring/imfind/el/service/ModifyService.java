package com.spring.imfind.el.service;

import com.spring.imfind.el.domain.LoginDTO;
import com.spring.imfind.el.domain.MemberVO;

public interface ModifyService {
	MemberVO getMember(String id);

	public int updateMember(LoginDTO dto);
}