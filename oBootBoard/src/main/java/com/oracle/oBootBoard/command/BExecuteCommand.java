package com.oracle.oBootBoard.command;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dto.BDto;

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
		
		Map<String, Object> map = model.asMap();		
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
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

	public void modify(BDto board, Model model) {
		// TODO Auto-generated method stub
		int modify_result = jbcdDao.modifyBoard(board);
		model.addAttribute("result", modify_result);
		
	}
}