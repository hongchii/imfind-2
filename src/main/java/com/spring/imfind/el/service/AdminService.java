
package com.spring.imfind.el.service;

import java.util.List;

import com.spring.imfind.el.domain.MemberVO;
import com.spring.imfind.el.domain.PayVO;

public interface AdminService {
	public List<MemberVO> getMemberList();

	public List<PayVO> getPayList();

	public List<PayVO> getPaidList();

	public List<PayVO> getRefundList();

	public int updatePaystate(PayVO payVO);

	public List<PayVO> getCancleList();
}
