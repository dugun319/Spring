package com.oracle.oBootJpaApi01.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Controller + ResponseBody
@RestController
@Slf4j
// private static final Logger logger = LoggerFactory.getLogger(JpaRestApiController.class);
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
	
	// Test용도
	@GetMapping("/helloText")
	public String  helloText() {
		System.out.println("JpaRestApiController Start...");
        String hello = "안녕";
        //      StringConverter
		return   hello;	
	}
	
	// Bad API
	@GetMapping("/restApi/v1/members")
	public List<Member> membersVer1() {
		  System.out.println("JpaRestApiController /restApi/v1/members  start..");
		  List<Member> listMember = memberService.getListAllMember();
		  System.out.println("JpaRestApiController /restApi/v1/members listMember.size()->"+listMember.size());
		  return listMember;
	}
	
	// Good API  Easy Version
	// 목표 : 이름 & 급여 만 전송 
	@GetMapping("/restApi/v21/members")
	public Result membersVer21() {
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController restApi/v21/members  findMembers.size()->"+findMembers.size());
		List<MemberRtnDto>  resultList = new ArrayList<MemberRtnDto>();

		// 이전 목적  : 반드시 필요한 Data 만 보여준다(외부 노출 최대한 금지)
		for(Member member : findMembers) {
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			System.out.println("restApi/v21/members  getName->"+memberRtnDto.getName());
			System.out.println("restApi/v21/members  getSal->"+memberRtnDto.getSal());
			resultList.add(memberRtnDto);
		}
		System.out.println("restApi/v21/members resultList.size()->"+resultList.size());
		return new Result(resultList.size(),resultList);
	}

	// Good API  람다  Version
	// 목표 : 이름 & 급여 만 전송 
	@GetMapping("/restApi/v22/members")
	public Result membersVer22() {
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController restApi/v22/members  findMembers.size()->"+findMembers.size());
		//  자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
		List<MemberRtnDto>  memberCollect  = 
				findMembers.stream()
						   .map(m->new MemberRtnDto(m.getName(), m.getSal()))
						   .collect(Collectors.toList())
						   ;
		
		System.out.println("restApi/v22/members memberCollect.size()->"+memberCollect.size());
		return new Result(memberCollect.size(), memberCollect);
	}
	 
	
	@Data
	@AllArgsConstructor
	class Result<T> {
		private final int totCount;  // 총 인원수 추가 
		private final T  data;
	}

	@Data
	@AllArgsConstructor
	class MemberRtnDto{
		private String name;
		private Long   sal;
	}

	
}
