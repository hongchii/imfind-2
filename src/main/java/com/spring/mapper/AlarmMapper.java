package com.spring.mapper;

import java.util.List;

import com.spring.imfind.el.domain.AlarmDTO;

public interface AlarmMapper {
	void insertAlarm(AlarmDTO dto);
	List<AlarmDTO> getAlarm(String id);
}
