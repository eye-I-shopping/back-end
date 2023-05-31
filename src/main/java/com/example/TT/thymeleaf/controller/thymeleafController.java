package com.example.TT.thymeleaf.controller;

import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TT.item.dto.itemDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping(value = "/thymeleaf")
public class thymeleafController {

	@GetMapping(value = "/ex1")
	public String ex1(Model model)
	{
		Point p = new Point(10,20);
		String home = "집갈까????????";
		model.addAttribute("data",p);
		model.addAttribute("data2", home);		
		return "thymeleaf/ex1";
	}
	@GetMapping(value = "/ex2")
	public String ex2(Model model)
	{
		itemDto itemDto = new itemDto();
		itemDto.setItemDetail("상품 상세 설명");
		itemDto.setItemNm("테스트상품1");
		itemDto.setPrice(3000);
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto",itemDto);
		return "thymeleaf/ex2";
	}
	@GetMapping(value = "/ex3")
	public String ex3(Model model)
	{
		
		List<itemDto> list = new ArrayList<itemDto>();
		
		for (int i = 1; i < 10; i++) {
			itemDto itemDto = new itemDto();
			itemDto.setItemDetail("상품 상세 설명" +i);
			itemDto.setItemNm("테스트상품"+i);
			itemDto.setPrice(3000+i);
			itemDto.setRegTime(LocalDateTime.now());
			list.add(itemDto);
		}
		
		model.addAttribute("list",list);
		return "thymeleaf/ex3";
		
		
		
	}
	@GetMapping(value = "/ex4")
	public void ex4(Model model)
	{
		
		List<itemDto> list = new ArrayList<itemDto>();
		
		for (int i = 1; i < 10; i++) {
			itemDto itemDto = new itemDto();
			itemDto.setItemDetail("상품 상세 설명" +i);
			itemDto.setItemNm("테스트상품"+i);
			itemDto.setPrice(3000+i);
			itemDto.setRegTime(LocalDateTime.now());
			list.add(itemDto);
		}
		
		model.addAttribute("list",list);				
	}
	@GetMapping(value = "/ex5")
	public String ex5(@RequestParam("param1")String p1,String param2,Model model)
	{
		log.info("============>"+ p1+","+param2);
		
		model.addAttribute("param1",p1);
		model.addAttribute("param2",param2);
		return "thymeleaf/ex5";
	}
	@GetMapping(value = {"/ex6","ex7"})
	public void ex6()
	{
		
		
		
	}
	
	
}
