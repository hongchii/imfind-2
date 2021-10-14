
package com.spring.mapper;

import com.spring.imfind.el.domain.LoginDTO;
import com.spring.imfind.el.domain.MemberVO;

public interface ModifyMapper {
	MemberVO getMember(String id);

	public int updateMember(LoginDTO vo);

}