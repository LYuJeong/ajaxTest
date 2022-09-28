package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j    // Simple Logging Facade for Java

public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping("/member/list")
	public Map<String,Object> memberList() {
		log.info("========================== MemberController(/member/list) ==================================");
		Map<String,Object> map=new HashMap<>();
		try {
		List<MemberDTO> list=memberService.findMemberList();
		map.put("list", list);
		map.put("errorCode", 0);
		}catch(Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg",e.getMessage());
		}
		return map;
	}

	@GetMapping("/member/grid-list")
	public ModelAndView gridList() {
		ModelAndView mv=new ModelAndView("/member/grid-list");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/member/detail")
	public Map<String,Object> memberList(
			@RequestParam  String id
			) {
		log.info("========================== MemberController(/member/detail) ==================================");
		MemberDTO member=memberService.findMemberDetail(id);
		Map<String,Object> map=new HashMap<>();
		map.put("member", member);
		return map;
	}
	
}
