
package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.money.PayDTO;

public interface MoneyMapper {
	public List<PayDTO> getMoneyList(String id);

	public List<PayDTO> getAdjustmentList();

	public List<PayDTO> getAdjustmentList2();

	public int giveMoney(PayDTO vo);

	public int giveMoneyPet(PayDTO vo);
}
