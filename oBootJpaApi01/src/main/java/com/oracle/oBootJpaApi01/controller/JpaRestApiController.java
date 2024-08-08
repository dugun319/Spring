package com.oracle.oBootJpaApi01.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	// @RequestBody : Json(member)으로 온것을  --> Member member Setting
	// @RequestBody without annotation, (null) is entered 
	@PostMapping(value = "/restApi/v1/memberSave")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		System.out.println("JpaRestApiController CreateMemberResponse saveMemberV1() member.getName() -> " + member.getName());
		log.info("member.getName() -> {} ", member.getName());
		log.info("member.getId() -> {} ", member.getId());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);		
	}
	
	@Data
	static class CreateMemberRequest {
		@NotEmpty
		private String name;
		private Long sal;	
	}
		
	@Data
	@RequiredArgsConstructor
	class CreateMemberResponse {
		private final Long id;		
		
		/*
		public CreateMemberResponse(Long id) {
			this.id = id;
		}
		*/		
	}
	
	@PostMapping(value = "/restApi/v2/memberSave")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest cMember) {
		System.out.println("JpaRestApiController CreateMemberResponse saveMemberV2() cMember.getName() -> " + cMember.getName());
		log.info("cMember.getName() -> {} ", cMember.getName());
		log.info("cMember.getSal() -> {} ", cMember.getSal());
		
		Member member = new Member();
		member.setName(cMember.getName());
		member.setSal(cMember.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);		
	}
	
	/*
	 *   단일 Id 조회 API
	 *   URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	 *   해당 데이터가 있으면 업데이트를 하기에 
	 *   PUT요청이 여러번 실행되어도 해당 데이터는 같은 상태이기에 멱등
	 */
	@GetMapping(value = "/restApi/v15/members/{id}")
	public Member membersVer15(@PathVariable("id") Long id) {
		System.out.println("JpaRestApiController /restApi/v15/members/{id} id -> " + id);
		Member findMember = memberService.findByMember(id);
		System.out.println("JpaRestApiController /restApi/v15/members/{id} findMember -> " + findMember);
		
		return findMember;
	}
	
	@PutMapping(value = "/restApi/v21/members/{id}")
	private UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id,
												 @RequestBody @Valid UpdateMemberRequest uMember) {
		System.out.println("JpaRestApiController JpaRestApiController id -> " + id);
		System.out.println("JpaRestApiController JpaRestApiController uMember -> " + uMember);
		
		memberService.updateMember(id, uMember.getName(), uMember.getSal());
		Member findMember = memberService.findByMember(id);
		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());

	}
	
	@GetMapping(value = "/restApi/v2/members/{id}")
	public ReturnMemberRequest membersVer2(@PathVariable("id") Long id) {
		System.out.println("JpaRestApiController /restApi/v2/members/{id} id -> " + id);
		Member findMember = memberService.findByMember(id);		
		System.out.println("JpaRestApiController /restApi/v2/members/{id} findMember -> " + findMember);
		
		return new ReturnMemberRequest(findMember.getName(), findMember.getSal());
	}
	
	
	@Data
	@AllArgsConstructor
	static class ReturnMemberRequest {
		private String 	name;
		private Long	sal;
	}
	
	
	@Data
	static class UpdateMemberRequest {
		private String 	name;
		private Long	sal;
	}
	
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse{
		private Long 	id;
		private String 	name;
		private Long	sal;
	}
	
	
}
