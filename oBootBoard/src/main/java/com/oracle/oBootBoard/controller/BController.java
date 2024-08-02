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
	
	@GetMapping("list")
	public String list(Model model) {
		logger.info("BController list() is started");
		
		bExecuteCommand.bListCommand(model);
		
		model.addAttribute("count", "50");
		
		return "list";
	}
	
	
	@GetMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		System.out.println("BController content_view() is started");		
		model.addAttribute("request", request);		
		bExecuteCommand.bContentCommand(model);
		
		return "content_view";
	}
	
	@PostMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		
		logger.info("BController modify() is started");				
		model.addAttribute("request", request);
		bExecuteCommand.bModifyCommand(model);
		
		//return "redirect:list";
		return "modify";
	}
	
	@GetMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		
		logger.info("BController delete() is started");				
		model.addAttribute("request", request);
		bExecuteCommand.bDeleteCommand(model);
		
		//return "delete";
		return "redirect:list";
	}
			
	@GetMapping("write_view")
	public String write_view(HttpServletRequest request, Model model) {
		
		logger.info("BController write_view() is started");
		
		return "write_view";
	}
	
	@PostMapping("write")
	public String write(HttpServletRequest request, Model model) {
		
		logger.info("BController write() is started");				
		model.addAttribute("request", request);
		bExecuteCommand.bWriteCommand(model);
				
		//return "write";
		return "redirect:list";
	}
	
	@GetMapping("reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		
		logger.info("BController reply_view() is started");				
		model.addAttribute("request", request);
		bExecuteCommand.bReplyViewCommand(model);
		
		return "reply_view";
	}
	
	@PostMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		
		logger.info("BController reply() is started");				
		model.addAttribute("request", request);
		bExecuteCommand.bReplyCommand(model);
				
		return "redirect:list";
	}

}
