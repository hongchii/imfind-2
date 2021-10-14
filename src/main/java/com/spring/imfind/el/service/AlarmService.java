package com.spring.imfind.el.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.AlarmDTO;

@Service
public interface AlarmService {
	public void insertAlarm(AlarmDTO dto);

	public List<AlarmDTO> getAlarm(String id);
}
