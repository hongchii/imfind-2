
package com.spring.imfind.el.socket;

import com.spring.imfind.el.domain.ItemVO;

public interface ChatService {
	public int createChatRoom(ChatDTO dto);

	public ChatDTO chkRoomExist(int postnum, String sender, String receiver);

	public ItemVO getBoardInfo(ChatDTO dto);
}
