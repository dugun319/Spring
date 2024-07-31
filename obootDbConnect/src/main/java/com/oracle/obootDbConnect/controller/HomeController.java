package com.oracle.obootDbConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	// 컨트롤러에 정의가 되어 있으면 컨트롤러의 정의를 우선적으로 따른다.
	// 아래 문장이 없을 경우, index.html을 실행한다.
	
	
	@GetMapping(value = "/")
	public String home() {
		
		System.out.println("HomeController home() is started");
		
		return "home";
	}
	

}
