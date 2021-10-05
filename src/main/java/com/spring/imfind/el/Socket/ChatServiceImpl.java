
package com.spring.imfind.el.Socket;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.imfind.el.domain.ItemVO;
import com.spring.mapper.ChatMapper;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int createChatRoom(ChatDTO dto) {
		ChatMapper chatMapper = sqlSession.getMapper(ChatMapper.class);
		Integer res = chatMapper.createChatRoom(dto);
		return res;
	}

	@Override
	public ChatDTO chkRoomExist(int postnum, String sender, String receiver) {

		ChatMapper chatMapper = sqlSession.getMapper(ChatMapper.class);
		ChatDTO dto = chatMapper.chkRoomExist(postnum, sender, receiver);
		return dto;
	}

	@Override
	public ItemVO getBoardInfo(ChatDTO dto) {
		ChatMapper chatMapper = sqlSession.getMapper(ChatMapper.class);
		ItemVO vo = chatMapper.getBoardInfo(dto);
		return vo;
	}

}
