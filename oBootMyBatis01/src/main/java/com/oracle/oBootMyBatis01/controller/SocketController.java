package com.oracle.oBootMyBatis01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SocketController {
	@GetMapping(value = "/chat")
	public ModelAndView chat() {
		
		log.info("SocketController chat() is started");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chatView");
		
		return mv;
	}
}
