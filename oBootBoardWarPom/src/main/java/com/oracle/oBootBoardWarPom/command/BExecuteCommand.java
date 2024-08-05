package com.oracle.oBootBoardWarPom.command;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.oBootBoardWarPom.dao.BDao;
import com.oracle.oBootBoardWarPom.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BExecuteCommand {
	
	private final BDao jbcdDao;
	
	@Autowired
	public BExecuteCommand(BDao jbcdDao) {
		this.jbcdDao = jbcdDao;
	}
	
	public void bContentCommand(Model model) {
		System.out.println("BExecuteCommand bContentCommand(Model model) is started");
		
		//Model 을 MAP 으로 전환
		
		Map<String, Object> map 	= model.asMap();		
		HttpServletRequest request 	= (HttpServletRequest)map.get("request");
		
		String bId 	= request.getParameter("bId");
		System.out.println("bContentCommand bId -> " + bId);
		
		BDto board	= jbcdDao.contentView(bId);
		
		//System.out.println("bContentCommand board.getbTitle() -> " + board.getbTitle());
		
		model.addAttribute("mvc_board", board);
		
	}

	public void bListCommand(Model model) {
		
		ArrayList<BDto> boardDtoList = jbcdDao.boardList();		
		System.out.println("bListCommand(Model model) boardDtoList.size() -> " + boardDtoList.size());		
		model.addAttribute("boardList", boardDtoList);
	}

	public void bModifyCommand(Model model) {
		
		Map<String, Object> map 	= model.asMap();		
		HttpServletRequest request 	= (HttpServletRequest)map.get("request");
		
		int bId			= Integer.parseInt(request.getParameter("bId"));
		String bName	= request.getParameter("bName");
		String bTitle	= request.getParameter("bTitle");
		String bContent	= request.getParameter("bContent");
		
		BDto board		= new BDto(bId, bName, bTitle, bContent);
		
		int result = jbcdDao.modifyBoard(board);
		model.addAttribute("result", result);

	}

	public void bDeleteCommand(Model model) {
		
		Map<String, Object> map 	= model.asMap();		
		HttpServletRequest request 	= (HttpServletRequest)map.get("request");
		
		int bId			= Integer.parseInt(request.getParameter("bId"));
		int result = jbcdDao.deleteBoard(bId);
		model.addAttribute("result", result);
		
	}

		public void bWriteCommand(Model model) {
		System.out.println("BExecuteCommand bWriteCommand(Model model) is started");
		
		Map<String, Object> map 	= model.asMap();		
		HttpServletRequest request 	= (HttpServletRequest)map.get("request");
		
		String bName	= request.getParameter("bName");
		String bTitle	= request.getParameter("bTitle");
		String bContent	= request.getParameter("bContent");
		
		BDto board		= new BDto(0, bName, bTitle, bContent);		
		jbcdDao.writeBoard(board);
				
	}
		
		public void bReplyViewCommand(Model model) {
			System.out.println("BExecuteCommand bReplyViewCommand(Model model) is started");
			
			Map<String, Object> map 	= model.asMap();		
			HttpServletRequest request 	= (HttpServletRequest)map.get("request");
			String bId					= request.getParameter("bId");
			BDto reply_view 			= jbcdDao.reply_view(bId);
			
			model.addAttribute("reply_view", reply_view);
			
		}

		public void bReplyCommand(Model model) {
			System.out.println("BExecuteCommand bReplyCommand(Model model) is started");
			
			Map<String, Object> map 	= model.asMap();		
			HttpServletRequest request 	= (HttpServletRequest)map.get("request");
			
			int bId			= Integer.parseInt(request.getParameter("bId"));
			String bName	= request.getParameter("bName");
			String bTitle	= request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			Timestamp bDate = null;
			String bHit		= request.getParameter("bHit");;
			int bGroup		= Integer.parseInt(request.getParameter("bGroup"));
			int bStep		= Integer.parseInt(request.getParameter("bStep"));
			int bIndent		= Integer.parseInt(request.getParameter("bIndent"));
			
			BDto board		= new BDto(bId, bName, bTitle, bContent, bDate, 0, bGroup, bStep, bIndent);			
			jbcdDao.replyBoard(board);
		}
}