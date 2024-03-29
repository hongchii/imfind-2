package com.spring.imfind.el.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.AlarmDTO;
import com.spring.mapper.AlarmMapper;

@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertAlarm(AlarmDTO dto) {
		AlarmMapper alarmMapper = sqlSession.getMapper(AlarmMapper.class);
		alarmMapper.insertAlarm(dto);
	}

	@Override
	public List<AlarmDTO> getAlarm(String id) {
		AlarmMapper alarmMapper = sqlSession.getMapper(AlarmMapper.class);
		List<AlarmDTO> list =  alarmMapper.getAlarm(id);
		return list;
	}

}
