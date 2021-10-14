
package com.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.imfind.el.domain.ItemVO;
import com.spring.imfind.el.socket.ChatDTO;

public interface ChatMapper {
	public int createChatRoom(ChatDTO dto);

	public ChatDTO chkRoomExist(@Param("postnum") int postnum, @Param("sender") String sender,
			@Param("reciever") String receiver);

	public ItemVO getBoardInfo(ChatDTO dto);
}
