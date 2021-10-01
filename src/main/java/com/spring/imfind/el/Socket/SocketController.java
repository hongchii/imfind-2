
package com.spring.imfind.el.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.imfind.el.YH.SessionName;
import com.spring.imfind.el.domain.ItemVO;

@Controller
public class SocketController implements SessionName {

	@Autowired
	ChatService chatService;

	@RequestMapping("/socket")
	public ModelAndView socket(HttpServletRequest req, HttpSession ses, @ModelAttribute ChatDTO chatDTO,
			ModelAndView modelAndView) {

		String finder = chatDTO.getSender();
		int postnum = chatDTO.getLost_postnum();
		String reciever = chatDTO.getReciever();

		System.out.println("보내는 사람 " + finder);
		System.out.println("받는 사람 " + reciever);

		ses.setAttribute(SENDER, finder);
		ses.setAttribute(LOST_POSTNUM, postnum);
		ses.setAttribute(RECIEVER, reciever);

		ChatDTO dto = chatService.chkRoomExist(postnum, finder, reciever);

		// 첫 채팅
		if (dto == null) {
			System.out.println("널"); // insert ChatDTO

			int res = chatService.createChatRoom(chatDTO);
			ItemVO board = chatService.getBoardInfo(chatDTO);
			System.out.println(board.toString());
			modelAndView.addObject("board", board);
			modelAndView.addObject("chatDTO", chatDTO);
			modelAndView.setViewName("el/Socket/test");

			return modelAndView;

		} else {
			// 채팅 기록 있음
			return null;
		}
	}

	@RequestMapping("/chat")
	public String chat() {
		return "el/Socket/test";
	}

}
