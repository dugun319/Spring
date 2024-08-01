package com.oracle.oBootBoard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.oracle.oBootBoard.command.BExecuteCommand;
import com.oracle.oBootBoard.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BController {
	
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	
	private final BExecuteCommand bExecuteCommand;
	
	@Autowired
	public BController(BExecuteCommand bExecuteCommand) {
		this.bExecuteCommand = bExecuteCommand;
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		logger.info("BController list(Model model) is started");
		
		bExecuteCommand.bListCommand(model);
		
		model.addAttribute("count", "50");
		
		return "list";
	}
	
	
	@GetMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		System.out.println("BController content_view is started");		
		model.addAttribute("request", request);		
		bExecuteCommand.bContentCommand(model);
		
		return "content_view";
	}
	
	@PostMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
				
		int bId			= Integer.parseInt(request.getParameter("bId"));
		System.out.println("String modify bId " + bId);
		String bName	= request.getParameter("bName");
		System.out.println("String modify bName " + bName);
		String bTitle	= request.getParameter("bTitle");
		String bContent	= request.getParameter("bContent");
		
		BDto board		= new BDto();
		
		board.setbId(bId);
		board.setbName(bName);
		board.setbTitle(bTitle);
		board.setbContent(bContent);
		
		bExecuteCommand.modify(board, model);
		
		return "modify";
	}
	

}
