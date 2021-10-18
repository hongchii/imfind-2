
package com.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.imfind.el.Socket.ChatDTO;
import com.spring.imfind.el.domain.ItemVO;

public interface ChatMapper {
	public int createChatRoom(ChatDTO dto);

	public ChatDTO chkRoomExist(@Param("postnum") int postnum, @Param("sender") String sender,
			@Param("reciever") String receiver);

	public ItemVO getBoardInfo(ChatDTO dto);
}
